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

public class Hello extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Log.d(WDAELib.TAG, "Hello");

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(WDAELib.SERVER_ADDRESS, WDAELib.SERVER_PORT),
                    WDAELib.SOCKET_TIMEOUT);

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject(WDAEProtocol.HELLO);
            oOStream.flush();

            Log.d(WDAELib.TAG, WDAEProtocol.HELLO + " sent to " + WDAELib.SERVER_ADDRESS +
                    ":" + WDAELib.SERVER_PORT);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
