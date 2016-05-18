package actor

import akka.actor.Actor
import message.Hello

class Master extends Actor {

  override def receive: Receive = {
    case Hello =>
      println(s"This is a Hello message from: $sender")
    case _ =>
      println("Received unknown message")
  }
}