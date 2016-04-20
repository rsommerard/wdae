package fr.inria.rsommerard.wdaelib.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.inria.rsommerard.wdaelib.WDAELib;
import fr.inria.rsommerard.wdaelib.WifiP2pDevice;
import fr.inria.rsommerard.wdaelib.WifiP2pDeviceList;
import fr.inria.rsommerard.wdaelib.WifiP2pManager;

public class RequestPeers extends AsyncTask<WifiP2pManager.PeerListListener, Void, Void> {

    private final String mServerAddress = "10.0.2.2";
    private final int mServerPort = 54412;
    private final int mSocketTimeout = 1000;

    @Override
    protected Void doInBackground(final WifiP2pManager.PeerListListener... params) {
        Log.d(WDAELib.TAG, "RequestPeers");

        WifiP2pManager.PeerListListener listener = params[0];

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(mServerAddress, mServerPort), mSocketTimeout);

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject(WDAELib.REQUEST_PEERS);
            oOStream.flush();

            Log.d(WDAELib.TAG, WDAELib.REQUEST_PEERS + " sent to " + mServerAddress + ":" +
                    mServerPort);

            ObjectInputStream oIStream = new ObjectInputStream(socket.getInputStream());
            String response = oIStream.readObject().toString();

            assert WDAELib.PEER.equals(response);

            WifiP2pDeviceList peers = new WifiP2pDeviceList();

            while (!WDAELib.END.equals(response)) {
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