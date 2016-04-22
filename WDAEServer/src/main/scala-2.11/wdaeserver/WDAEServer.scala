package wdaeserver

import java.io.ObjectOutputStream
import java.net.{InetAddress, Socket}

import emulator.{Emulator, Peer}

object WDAEServer {

  val serverAddress = InetAddress.getLocalHost.getHostAddress

  def stopDiscovery = {
    isDiscoverPeersEnabled = false
  }

  val neighbourPeers: Set[Peer] = Set(new Peer("Foo1", "bar1"), new Peer("Bar2", "foo2"))
  var isDiscoverPeersEnabled = false

  def discoverPeers = {
    if (neighbourPeers.nonEmpty) {
      Emulator.sendWfiP2pPeersChangedAction
    }
  }

  def requestPeers(socket: Socket): Unit = {
    isDiscoverPeersEnabled = true
    if (neighbourPeers.isEmpty) {
      socket.close()
      return
    }

    val oOStream: ObjectOutputStream = new ObjectOutputStream(socket.getOutputStream)

    for (peer <- neighbourPeers) {
      println("Write: " + WDAEProtocol.PEER)
      oOStream.writeObject(WDAEProtocol.PEER)
      oOStream.flush()
      peer.send(oOStream)
    }

    println("Write: " + WDAEProtocol.END_PEER)
    oOStream.writeObject(WDAEProtocol.END_PEER)
    oOStream.flush()

    socket.close()
  }
}
