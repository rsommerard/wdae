package actor

import akka.actor.{Actor, ActorRef}
import message.{Hello, Neighbors}

class Machine extends Actor {

  var emulators: Set[ActorRef] = Set()

  val master = context.actorSelection("akka.tcp://MasterSystem@10.32.0.42:2552/user/master")

  master ! Hello

  override def receive: Receive = {
    case Hello =>
      println(s"Hello from: $sender")
      emulators += sender
      println(s"emulators: $emulators")

      // For the moment, send new emulators as neighbor
      emulators.foreach(e => e ! Neighbors(emulators))
    case _ =>
      println("Received unknown message")
  }
}
