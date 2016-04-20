import java.io.ObjectInputStream
import java.net.ServerSocket

object Main extends App {

  val serverSocket: ServerSocket = new ServerSocket(54412)

  while (true) {
    val socket = serverSocket.accept

    val oIStream: ObjectInputStream = new ObjectInputStream(socket.getInputStream)
    val request: String  = oIStream.readObject().toString()

    println(s"$request from " + socket.getLocalAddress + ":" + socket.getLocalPort)

    request match {
      case WDAELib.DISCOVER_PEERS =>
        WDAEServer.discoverPeers
      case WDAELib.REQUEST_PEERS =>
        WDAEServer.requestPeers(socket)
    }

    socket.close()
  }
}
