import actor.{Master, Node}
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.{ConfigFactory, ConfigValueFactory}

import scala.io.Source

object Main extends App {
  if (args.isEmpty) {
    println("Type needed: master or node")
    System.exit(1)
  }

  args(0) match {
    case "node" =>
      initAsNode()
    case "master" =>
      initAsMaster()
    case _ =>
      println("Type not recognized: master or node")
      System.exit(1)
  }

  def initAsNode() = {
    val masterAddress = ConfigFactory.load("master").getString("akka.remote.netty.tcp.hostname")
    val masterPort = ConfigFactory.load("master").getInt("akka.remote.netty.tcp.port")

    val ownAddress = args(1)
    val nodeConfig = ConfigFactory.load("node").withValue("akka.remote.netty.tcp.hostname", ConfigValueFactory.fromAnyRef(ownAddress))

    val system = ActorSystem("NodeSystem", nodeConfig)
    val remotePath = s"akka.tcp://MasterSystem@$masterAddress:$masterPort/user/master"
    val actor = system.actorOf(Props(classOf[Node], remotePath), "node")

    println(s"Node started...")
  }

  def initAsMaster() = {
    val system = ActorSystem("MasterSystem", ConfigFactory.load("master"))
    system.actorOf(Props[Master], "master")

    println(s"Master started...")
  }

  for (ln <- Source.stdin.getLines()) {
    if (ln == "exit") {
      System.exit(0)
    }
  }
}


