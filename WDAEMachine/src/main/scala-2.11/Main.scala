import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

case object DiscoverPeers
case object Hello
case object Toto
case class Neighbour(devices: Set[String])

object Main extends App {
  val system = ActorSystem("MachineSystem", ConfigFactory.load("application"))
  system.actorOf(Props[MachineActor], "machine")

  println("MachineActor started...")
}

class MachineActor extends Actor {

  var devices: Set[String] = Set()
  var emulators: Set[ActorRef] = Set()

  override def receive: Receive = {
    case Hello =>
      println(s"[Hello] from ${sender.path.address.host.get}")
      emulators += sender
      sender ! Toto
    case DiscoverPeers =>
      println(s"[DiscoverPeers] from ${sender.path.address.host.get}")
      // KNN HERE
      // ----------------------------
      // some stuff
      // ----------------------------
      devices += sender.path.address.host.get

      // The next line is temp
      val neighbours = devices

      println(s"[DiscoverPeers] broadcast $neighbours")
      emulators.foreach(e => e ! Neighbour(neighbours))
  }
}
