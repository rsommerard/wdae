package actor

import akka.actor.Actor
import message.Hello

class Machine extends Actor {

  val master = context.actorSelection("akka.tcp://MasterSystem@10.32.0.42:2552/user/master")

  master ! Hello(666)

  override def receive: Receive = {
    case hello: Hello =>
      println(s"This is a Hello message with value: ${hello.value}")
    case _ =>
      println("Received unknown message")
  }
}
