import actor.Master
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.Source

object Main extends App {

  val system = ActorSystem("MasterSystem", ConfigFactory.load("master"))
  system.actorOf(Props[Master], "master")

  println("Master started...")

  for (ln <- Source.stdin.getLines()) {
    if (ln == "exit") {
      System.exit(0)
    }
  }
}
