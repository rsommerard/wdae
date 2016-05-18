import actor.Emulator
import akka.actor.{ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import message.Bye
import wdaeemulator.WDAEServer

import scala.io.Source

object Main extends App {
  if (args.isEmpty) {
    println("Machine IP needed!")
    System.exit(1)
  }

  val remotePath = s"akka.tcp://MachineSystem@${args(0)}:2552/user/machine"
  val system = ActorSystem("EmulatorSystem", ConfigFactory.load("emulator"))

  val emulator = system.actorOf(Props(classOf[Emulator], remotePath), "emulator")

  println("Emulator started...")

  WDAEServer(emulator).start()

  for (ln <- Source.stdin.getLines()) {
    if (ln.toLowerCase == "exit") {
      System.exit(0)
    }
  }
}
