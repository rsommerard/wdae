import java.io.ObjectOutputStream
import java.net.Socket

object WDAEServer {

  val neighbourPeers: Set[Peer] = Set(new Peer("Foo1", "bar1"), new Peer("Bar2", "foo2"))

  def discoverPeers = {
    if (neighbourPeers.nonEmpty) {
      Emulator.sendWfiP2pPeersChangedAction
    }
  }

  def requestPeers(socket: Socket): Unit = {
    if (neighbourPeers.isEmpty) {
      socket.close()
      return
    }

    val oOStream: ObjectOutputStream = new ObjectOutputStream(socket.getOutputStream)

    for (peer <- neighbourPeers) {
      println("Write: " + WDAELib.PEER)
      oOStream.writeObject(WDAELib.PEER)
      oOStream.flush()
      peer.send(oOStream)
    }

    println("Write: " + WDAELib.END)
    oOStream.writeObject(WDAELib.END)
    oOStream.flush()

    socket.close()
  }
}
