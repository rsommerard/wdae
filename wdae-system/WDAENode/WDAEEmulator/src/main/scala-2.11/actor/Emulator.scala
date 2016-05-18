package actor

import akka.actor.Actor
import message.Hello

class Emulator(val remotePath: String) extends Actor {

  val machine = context.actorSelection(remotePath)

  machine ! Hello(7)

  override def receive: Receive = {
    case hello: Hello =>
      println(s"This is a Hello message with value: ${hello.value}")
    case _ =>
      println("Received unknown message")
  }
}
