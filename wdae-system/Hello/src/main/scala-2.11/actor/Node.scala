package actor

import akka.actor.Actor
import message.Hello

class Node(val remotePath: String) extends Actor {

  val master = context.actorSelection(remotePath)

  master ! Hello

  override def receive: Receive = {
    case Hello =>
      println(s"Hello received from ${sender.path.address.host}")
    case _ =>
      println("Received unknown msg ")
  }
}
