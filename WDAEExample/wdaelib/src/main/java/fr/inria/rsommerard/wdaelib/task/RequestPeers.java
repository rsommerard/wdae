package fr.inria.rsommerard.wdaelib.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.inria.rsommerard.wdaelib.WDAELib;
import fr.inria.rsommerard.wdaelib.WDAEProtocol;
import fr.inria.rsommerard.wdaelib.WifiP2pDevice;
import fr.inria.rsommerard.wdaelib.WifiP2pDeviceList;
import fr.inria.rsommerard.wdaelib.WifiP2pManager;

public class RequestPeers extends AsyncTask<WifiP2pManager.PeerListListener, Void, Void> {

    @Override
    protected Void doInBackground(final WifiP2pManager.PeerListListener... params) {
        Log.d(WDAELib.TAG, "RequestPeers");

        WifiP2pManager.PeerListListener listener = params[0];

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(WDAELib.SERVER_ADDRESS, WDAELib.SERVER_PORT),
                    WDAELib.SOCKET_TIMEOUT);

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject(WDAEProtocol.REQUEST_PEERS);
            oOStream.flush();

            Log.d(WDAELib.TAG, WDAEProtocol.REQUEST_PEERS + " sent to " + WDAELib.SERVER_ADDRESS +
                    ":" + WDAELib.SERVER_PORT);

            ObjectInputStream oIStream = new ObjectInputStream(socket.getInputStream());
            String response = oIStream.readObject().toString();

            WifiP2pDeviceList peers = new WifiP2pDeviceList();

            while (!WDAEProtocol.END_PEER.equals(response)) {
                String name = oIStream.readObject().toString();
                String address = oIStream.readObject().toString();

                peers.add(new WifiP2pDevice(name, address));

                response = oIStream.readObject().toString();
            }

            listener.onPeersAvailable(peers);

            socket.close();
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}