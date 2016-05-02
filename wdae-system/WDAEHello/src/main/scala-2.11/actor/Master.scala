package actor

import akka.actor.Actor
import message.Hello

class Master extends Actor {

  override def receive: Receive = {
    case Hello =>
      println(s"Hello received from ${sender.path.address.host}")
      sender ! Hello
  }
}
