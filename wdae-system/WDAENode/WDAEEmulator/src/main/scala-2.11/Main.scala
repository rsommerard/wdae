import actor.Emulator
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.Source

object Main extends App {
  if (args.isEmpty) {
    println("Machine IP needed!")
    System.exit(1)
  }

  val machine = args(0)
  val remotePath = s"akka.tcp://MachineSystem@$machine:2552/user/machine"

  val system = ActorSystem("EmulatorSystem", ConfigFactory.load("emulator"))
  system.actorOf(Props(classOf[Emulator], remotePath), "emulator")

  println("Emulator started...")

  for (ln <- Source.stdin.getLines()) {
    if (ln == "exit") {
      System.exit(0)
    }
  }
}
