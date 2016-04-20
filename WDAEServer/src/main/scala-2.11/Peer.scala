import java.io.ObjectOutputStream

class Peer(val name:String, val address: String) {

  def send(oOStream: ObjectOutputStream) = {
    println("Write: " + name)
    oOStream.writeObject(name)
    oOStream.flush()
    println("Write: " + address)
    oOStream.writeObject(address)
    oOStream.flush()
  }
}
