package wdaeemulator

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{InetAddress, ServerSocket, Socket}

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import emulator.Emulator

case object DiscoverPeers
case object Hello
case object Toto
case class Neighbour(devices: Set[String])

object WDAEServer {

  val serverAddress = InetAddress.getLocalHost.getHostAddress
  var neighbours: Set[String] = Set()
  val serverSocket: ServerSocket = new ServerSocket(54412)

  val system = ActorSystem("EmulatorSystem")
  val remotePath = "akka.tcp://MachineSystem@172.17.0.1:2552/user/machine"
  val actor = system.actorOf(Props(classOf[EmulatorActor], remotePath), "emulator")
  println("EmulatorActor started...")

  def start() = {
    println(s"WDAEServer started on ${serverSocket.getInetAddress.getHostAddress}:${serverSocket.getLocalPort}")

    new Thread(new Runnable {
      override def run(): Unit = {
        while (true) {
          val socket = serverSocket.accept

          val oIStream: ObjectInputStream = new ObjectInputStream(socket.getInputStream)
          val request: String  = oIStream.readObject().toString()

          println(s"$request from " + socket.getLocalAddress + ":" + socket.getLocalPort)

          request match {
            case WDAEProtocol.DISCOVER_PEERS =>
              actor ! DiscoverPeers
              WDAEServer.discoverPeers(neighbours)
            case WDAEProtocol.STOP_DISCOVERY =>
              WDAEServer.stopDiscovery
            case WDAEProtocol.REQUEST_PEERS =>
              WDAEServer.requestPeers(socket, neighbours)
          }

          socket.close()
        }
      }
    }).start()
  }

  def stopDiscovery = {
    isDiscoverPeersEnabled = false
  }

  var isDiscoverPeersEnabled = false

  def discoverPeers(neighbours: Set[String]) = {
    if (neighbours.nonEmpty) {
      Emulator.sendWfiP2pPeersChangedAction
    }
  }

  def requestPeers(socket: Socket, neighbours: Set[String]): Unit = {
    isDiscoverPeersEnabled = true

    if (neighbours.isEmpty) {
      socket.close()
      return
    }

    val oOStream: ObjectOutputStream = new ObjectOutputStream(socket.getOutputStream)

    oOStream.writeObject(WDAEProtocol.PEER)
    oOStream.flush()
    for (device <- neighbours) {
      oOStream.writeObject("aName")
      oOStream.flush()
      oOStream.writeObject(device)
      oOStream.flush()
    }
    oOStream.writeObject(WDAEProtocol.END_PEER)
    oOStream.flush()

    socket.close()
  }
}

class EmulatorActor(val remotePath: String) extends Actor {

  val machineActor = context.actorSelection(remotePath)

  machineActor ! Hello

  override def receive: Receive = {
    case Toto =>
      println("Toto")
    case Neighbour =>
      println("Neighbour")
    /*println("Set size: " + n.devices.size)
    n.devices.foreach(d => neighbours += d)
    println(neighbours)*/
  }
}
