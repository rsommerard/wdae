import actor.Machine
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.{ConfigFactory, ConfigValueFactory}

import scala.io.Source

object Main extends App {
  if (args.isEmpty) {
    println("Machine IP needed!")
    System.exit(1)
  }

  val me = args(0)
  val config = ConfigFactory.load("machine").withValue("akka.remote.netty.tcp.hostname",
    ConfigValueFactory.fromAnyRef(me))

  val system = ActorSystem("MachineSystem", config)
  system.actorOf(Props[Machine], "machine")

  println("Machine started...")

  for (ln <- Source.stdin.getLines()) {
    if (ln == "exit") {
      System.exit(0)
    }
  }
}
