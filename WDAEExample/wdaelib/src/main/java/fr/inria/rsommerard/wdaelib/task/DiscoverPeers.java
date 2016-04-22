package fr.inria.rsommerard.wdaelib.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.inria.rsommerard.wdaelib.WDAELib;
import fr.inria.rsommerard.wdaelib.WDAEProtocol;
import fr.inria.rsommerard.wdaelib.WifiP2pManager;

public class DiscoverPeers extends AsyncTask<WifiP2pManager.ActionListener, Void, Void> {

    @Override
    protected Void doInBackground(final WifiP2pManager.ActionListener... params) {
        Log.d(WDAELib.TAG, "DiscoverPeers");

        WifiP2pManager.ActionListener listener = params[0];

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(WDAELib.SERVER_ADDRESS, WDAELib.SERVER_PORT),
                    WDAELib.SOCKET_TIMEOUT);

            listener.onSuccess();

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject(WDAEProtocol.DISCOVER_PEERS);
            oOStream.flush();

            Log.d(WDAELib.TAG, WDAEProtocol.DISCOVER_PEERS + " sent to " + WDAELib.SERVER_ADDRESS +
                    ":" + WDAELib.SERVER_PORT);

            socket.close();
        } catch (IOException e) {
            listener.onFailure(WifiP2pManager.ERROR);
            e.printStackTrace();
        }

        return null;
    }
}
