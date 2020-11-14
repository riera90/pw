package es.uco.pw;

import es.uco.pw.business.Daemons.FlashPostDaemon;
import es.uco.pw.display.CLI.CliUserInterface;
import java.security.NoSuchAlgorithmException;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {

        FlashPostDaemon flashPostDaemon = new FlashPostDaemon("flashPostDaemon");
        flashPostDaemon.start();

        CliUserInterface.init();

        flashPostDaemon.stopThread();
    }
}
