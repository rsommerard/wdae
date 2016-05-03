import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

val socket = new Socket("172.17.0.2", 11131)

val oOStream: ObjectOutputStream = new ObjectOutputStream(socket.getOutputStream)
oOStream.writeObject("This is a test!")
oOStream.flush()

val oIStream: ObjectInputStream = new ObjectInputStream(socket.getInputStream)
val message: String  = oIStream.readObject().toString

println(s"$message from " + socket.getLocalAddress + ":" + socket.getLocalPort)

/*val thread1: Thread = new Thread(new Runnable {
  override def run(): Unit = {
    val serverSocket = new ServerSocket(7987)
    println("[thread1] Waiting on 0.0.0.0:7987...")

    val socket = serverSocket.accept()
    println("[thread1] ServerSocket new connection accepted...")

    val oIStream: ObjectInputStream = new ObjectInputStream(socket.getInputStream)
    val message: String  = oIStream.readObject().toString
    println(s"[thread1] $message from " + socket.getLocalAddress + ":" + socket.getLocalPort)

    socket.close()
  }
})

val thread12: Thread = new Thread(new Runnable {
  override def run(): Unit = {
    val socket1 = new Socket("0.0.0.0", 7987)
    println("[thread12] Connection OK...")

    val serverSocket = new ServerSocket(3214)
    println("[thread12] Waiting on 0.0.0.0:3214...")

    val socket2 = serverSocket.accept()
    println("[thread12] ServerSocket new connection accepted...")

    IOUtils.copy(socket1.getInputStream, socket2.getOutputStream)

    Thread.sleep(10000)

    socket1.close()
    socket2.close()
  }
})

thread1.start()

thread12.start()

val thread2: Thread = new Thread(new Runnable {
  override def run(): Unit = {
    println("[thread2] Connection to 0.0.0.0:3214...")
    val socket = new Socket("0.0.0.0", 3214)
    println("[thread2] Connection OK, sending message...")

    val oOStream: ObjectOutputStream = new ObjectOutputStream(socket.getOutputStream)
    oOStream.writeObject("LOLILOL")
    oOStream.flush()
    socket.close()
  }
})



Thread.sleep(1000)


thread2.start()


Thread.sleep(3000)
thread2.interrupt()
thread12.interrupt()
thread1.interrupt()*/
