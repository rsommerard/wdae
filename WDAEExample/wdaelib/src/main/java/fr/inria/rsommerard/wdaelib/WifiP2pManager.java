package fr.inria.rsommerard.wdaelib;

import android.content.Context;
import android.os.Looper;

import fr.inria.rsommerard.wdaelib.task.DiscoverPeers;
import fr.inria.rsommerard.wdaelib.task.RequestPeers;
import fr.inria.rsommerard.wdaelib.task.WaitConnection;

public class WifiP2pManager {

    public static final String WIFI_P2P_STATE_CHANGED_ACTION =
            "fr.inria.rsommerard.wdaelib.STATE_CHANGED";

    public static final String WIFI_P2P_PEERS_CHANGED_ACTION =
            "fr.inria.rsommerard.wdaelib.PEERS_CHANGED";

    public static final String WIFI_P2P_CONNECTION_CHANGED_ACTION =
            "fr.inria.rsommerard.wdaelib.CONNECTION_STATE_CHANGE";

    public static final String WIFI_P2P_THIS_DEVICE_CHANGED_ACTION =
            "fr.inria.rsommerard.wdaelib.THIS_DEVICE_CHANGED";

    public static final int WIFI_P2P_STATE_ENABLED = 2;

    public static final int ERROR = 0;

    public static final String EXTRA_WIFI_STATE = "wifi_p2p_state";

    public Channel initialize(final Context context,
                              final Looper looper,
                              final ChannelListener listener) {
        return new Channel();
    }

    public void discoverPeers(final Channel channel, final ActionListener listener) {
        new DiscoverPeers().execute(listener);
        new WaitConnection().execute();
    }

    public void requestPeers(final WifiP2pManager.Channel channel,
                             final WifiP2pManager.PeerListListener listener) {

        new RequestPeers().execute(listener);
    }

    public class Channel {}

    public interface PeerListListener {
        void onPeersAvailable(final WifiP2pDeviceList peers);
    }

    public interface ChannelListener {
        void onChannelDisconnected();
    }

    public interface ActionListener {
        void onSuccess();
        void onFailure(final int reason);
    }
}
