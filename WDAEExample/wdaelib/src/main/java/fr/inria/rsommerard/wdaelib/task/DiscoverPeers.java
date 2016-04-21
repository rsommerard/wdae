package fr.inria.rsommerard.wdaelib.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.inria.rsommerard.wdaelib.WDAELib;
import fr.inria.rsommerard.wdaelib.WDAEProtocol;

public class DiscoverPeers extends AsyncTask<Void, Void, Void> {

    private final String mServerAddress = "10.0.2.2";
    private final int mServerPort = 54412;
    private final int mSocketTimeout = 1000;

    @Override
    protected Void doInBackground(final Void... params) {
        Log.d(WDAELib.TAG, "DiscoverPeers");

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(mServerAddress, mServerPort), mSocketTimeout);

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject(WDAEProtocol.DISCOVER_PEERS);
            oOStream.flush();

            Log.d(WDAELib.TAG, WDAEProtocol.DISCOVER_PEERS + " sent to " + mServerAddress + ":" +
                    mServerPort);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
