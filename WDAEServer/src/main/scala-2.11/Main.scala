import java.io.ObjectInputStream
import java.net.ServerSocket

object Main extends App {

  val serverSocket: ServerSocket = new ServerSocket(54412)

  while (true) {
    val socket = serverSocket.accept

    val objectInputStream: ObjectInputStream = new ObjectInputStream(socket.getInputStream)
    val request: String  = objectInputStream.readObject().toString()

    println(s"$request from " + socket.getLocalAddress + ":" + socket.getLocalPort)

    socket.close()
  }
}
