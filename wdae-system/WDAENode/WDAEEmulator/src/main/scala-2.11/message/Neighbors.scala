package message

import akka.actor.ActorRef

case class Neighbors(list: Set[ActorRef])
