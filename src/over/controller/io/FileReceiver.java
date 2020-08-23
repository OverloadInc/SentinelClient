package over.controller.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * <code>FileReceiver</code> class implements the mechanism to receive a file from another computer via Sockets.
 *
 * @author Overload Inc.
 * @version 1.0, 20 Aug 2020
 */
public class FileReceiver {

    /**
     * The target local directory to locate the incoming files.
     */
    private static String localDirectory;

    /**
     * The origin server IP.
     */
    private static String IP;

    /**
     * Set the target local directory to locate the incoming files.
     * @param localDirectory the local directory.
     */
    public static void setLocalDirectory(String localDirectory) {
        FileReceiver.localDirectory = localDirectory;
    }

    /**
     * Sets the origin server IP.
     * @param IP the origin server IP.
     */
    public static void setIP(String IP) {
        FileReceiver.IP = IP;
    }

    /**
     * Gets the current target local directory where the incoming files are located.
     * @return the current target local directory.
     */
    public static String getLocalDirectory() {
        return localDirectory;
    }

    /**
     * Gets the current origin server IP.
     * @return the origin server IP.
     */
    public static String getIP() {
        return IP;
    }

    /**
     * Receives a specific file and creates a copy of it.
     * @param fileName the file name.
     * @return the file name if the process is successfully completed. <code>Null</code> otherwise.
     */
    public static String receive(String fileName) {
        try {
            int fileSize = 2022386;
            int bytesRead;
            int currentTot = 0;
            byte byteArray[] = new byte[fileSize];

            Socket socket = new Socket(IP, 15123);

            InputStream input = socket.getInputStream();

            FileOutputStream output = new FileOutputStream(localDirectory + fileName);

            new BufferedOutputStream(output);

            bytesRead = input.read(byteArray, 0, byteArray.length);
            currentTot = bytesRead;

            do {
                bytesRead = input.read(byteArray, currentTot, (byteArray.length - currentTot));

                if(bytesRead >= 0)
                    currentTot += bytesRead;
            }while(bytesRead > -1);

            output.write(byteArray, 0, currentTot);
            output.flush();
            output.close();

            socket.close();
        }
        catch (Exception e) {
            fileName = null;
        }

        return fileName;
    }
}