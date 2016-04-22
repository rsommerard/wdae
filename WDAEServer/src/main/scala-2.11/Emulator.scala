import scala.sys.process.Process
import scala.util.{Failure, Success, Try}

object Emulator {

  val ADB = adbPath

  def adbPath: String = {
    Try(Process("/home/romain/android-sdk/platform-tools/adb").!) match {
      case Success(s) =>
        return "/home/romain/android-sdk/platform-tools/adb"
      case Failure(f) =>
        return "/android-sdk-linux/platform-tools/adb"
    }
  }

  def sendWfiP2pPeersChangedAction = {
    Process(ADB + " -e shell am broadcast -a " + WDAEIntent.WIFI_P2P_PEERS_CHANGED_ACTION).run()
  }
}
