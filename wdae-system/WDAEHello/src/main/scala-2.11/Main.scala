import java.net.InetAddress

import actor.{Master, Node}
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Main extends App {
  if (args.isEmpty) {
    println("Type needed: master or node")
    System.exit(1)
  }

  val ownAddress = InetAddress.getLocalHost.getHostAddress

  args(0) match {
    case "node" =>
      initAsNode()
    case "master" =>
      initAsMaster()
    case _ =>
      println("Type not recognized: master or node")
      System.exit(0)
  }

  def initAsNode() = {
    val masterAddress = ConfigFactory.load("master").getString("akka.remote.netty.tcp.hostname")
    val masterPort = ConfigFactory.load("master").getInt("akka.remote.netty.tcp.port")

    val system = ActorSystem("NodeSystem", ConfigFactory.load("node"))
    val remotePath = s"akka.tcp://MasterSystem@$masterAddress:$masterPort/user/master"
    val actor = system.actorOf(Props(classOf[Node], remotePath), "node")

    println(s"Node started on [$ownAddress]...")
  }

  def initAsMaster() = {
    val system = ActorSystem("MasterSystem", ConfigFactory.load("master"))
    system.actorOf(Props[Master], "master")

    println(s"Master started on [$ownAddress]...")
  }
}


