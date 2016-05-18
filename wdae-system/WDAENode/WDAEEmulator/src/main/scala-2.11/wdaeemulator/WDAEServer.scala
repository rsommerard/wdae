package wdaeemulator

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{InetAddress, ServerSocket, Socket}

import akka.actor.ActorRef

import scala.sys.process.Process
import scala.util.{Failure, Success, Try}

case object DiscoverPeers

object WDAEServer {
  def apply(emulator: ActorRef) = new WDAEServer(emulator)
}

class WDAEServer(emulator: ActorRef) {

  val ADB = adbPath

  private def adbPath: String = {
    Try(Process("/home/romain/android-sdk/platform-tools/adb").!) match {
      case Success(s) =>
        return "/home/romain/android-sdk/platform-tools/adb"
      case Failure(f) =>
        return "/android-sdk-linux/platform-tools/adb"
    }
  }

  def sendWifiP2pPeersChangedAction = {
    Process(ADB + " -e shell am broadcast -a " + WDAEIntent.WIFI_P2P_PEERS_CHANGED_ACTION).run()
  }

  val serverAddress = InetAddress.getLocalHost.getHostAddress
  var neighbors: Set[String] = Set()
  val serverSocket: ServerSocket = new ServerSocket(54412)

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
            case WDAEProtocol.HELLO =>
              println("HELLO received from the library emulator itself!")
            case WDAEProtocol.DISCOVER_PEERS =>
              emulator ! DiscoverPeers
              discoverPeers(neighbors)
            case WDAEProtocol.STOP_DISCOVERY =>
              stopDiscovery
            case WDAEProtocol.REQUEST_PEERS =>
              requestPeers(socket, neighbors)
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
      sendWifiP2pPeersChangedAction
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
