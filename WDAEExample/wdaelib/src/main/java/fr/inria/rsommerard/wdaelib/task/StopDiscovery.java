package fr.inria.rsommerard.wdaelib.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.inria.rsommerard.wdaelib.WDAELib;

public class StopDiscovery extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Log.d(WDAELib.TAG, "StopDiscovery");

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("10.0.2.2", 54412), 1000);

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject(WDAELib.STOP_DISCOVERY);
            oOStream.flush();

            Log.d(WDAELib.TAG, WDAELib.STOP_DISCOVERY + " sent to " + socket.getLocalAddress() + ":" + socket.getLocalPort());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}