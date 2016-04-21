import java.io.ObjectInputStream
import java.net.ServerSocket

object Main extends App {

  val serverSocket: ServerSocket = new ServerSocket(54412)

  println(s"WDAEServer started on ${serverSocket.getInetAddress.getHostAddress}:${serverSocket.getLocalPort}")

  while (true) {
    val socket = serverSocket.accept

    val oIStream: ObjectInputStream = new ObjectInputStream(socket.getInputStream)
    val request: String  = oIStream.readObject().toString()

    println(s"$request from " + socket.getLocalAddress + ":" + socket.getLocalPort)

    request match {
      case WDAEProtocol.DISCOVER_PEERS =>
        WDAEServer.discoverPeers
      case WDAEProtocol.STOP_DISCOVERY =>
        WDAEServer.stopDiscovery
      case WDAEProtocol.REQUEST_PEERS =>
        WDAEServer.requestPeers(socket)
    }

    socket.close()
  }
}
