package actor

import akka.actor.Actor
import message.Hello

class Master extends Actor {

  override def receive: Receive = {
    case hello: Hello =>
      println(s"This is a Hello message with value: ${hello.value}")
      sender ! Hello(42)
    case _ =>
      println("Received unknown message")
  }
}