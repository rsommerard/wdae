package fr.inria.rsommerard.wdaelib.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import fr.inria.rsommerard.wdaelib.WDAELib;

public class WaitConnection extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(final Void... params) {
        Log.d(WDAELib.TAG, "WaitConnection");

        try {
            ServerSocket serverSocket = new ServerSocket(11131);
            Socket socket = serverSocket.accept();

            Log.d(WDAELib.TAG, "Socket open [" + socket.getInetAddress().getHostAddress() +
                    ":" + socket.getPort() + "]");

            ObjectInputStream oIStream = new ObjectInputStream(socket.getInputStream());
            String message = oIStream.readObject().toString();
            Log.d(WDAELib.TAG, message + " received from " + socket.getInetAddress().getHostAddress() +
                    ":" + socket.getPort());

            ObjectOutputStream oOStream = new ObjectOutputStream(socket.getOutputStream());
            oOStream.writeObject("OK");
            oOStream.flush();

            Log.d(WDAELib.TAG, "OK" + " sent to " + WDAELib.SERVER_ADDRESS +
                    ":" + WDAELib.SERVER_PORT);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
