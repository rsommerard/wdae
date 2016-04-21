import scala.sys.process.Process

object Emulator {

  val ADB = "/home/romain/android-sdk/platform-tools/adb"

  def sendWfiP2pPeersChangedAction = {
    Process(ADB + " -e shell am broadcast -a " + WDAEIntent.WIFI_P2P_PEERS_CHANGED_ACTION).run()
  }
}
