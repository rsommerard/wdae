package actor

import akka.actor.{Actor, ActorRef, ActorSelection}
import message.{Bye, Hello, Neighbors}

class Emulator(val remotePath: String) extends Actor {

  var neighbors: Set[ActorRef] = Set()

  val machine: ActorSelection = context.actorSelection(remotePath)

  machine ! Hello

  override def receive: Receive = {
    case nghbrs: Neighbors =>
      neighbors = nghbrs.list.filterNot(n => n == self)
      println(s"me: $self")
      println(s"neighbors: $neighbors")
    case _ =>
      println("Received unknown message")
  }
}
