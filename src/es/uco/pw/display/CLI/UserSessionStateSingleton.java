package es.uco.pw.display.CLI;

import es.uco.pw.data.dto.user.DTOUser;

/**
 * The type User session state singleton.
 */
public class UserSessionStateSingleton {
    private static UserSessionStateSingleton instance = null;
    /**
     * The Logged user.
     */
    public DTOUser LoggedUser = null;

    private UserSessionStateSingleton(){}

    /**
     * Get instance user session state singleton.
     *
     * @return the user session state singleton
     */
    public static UserSessionStateSingleton getInstance(){
        if (instance != null)
            return instance;
        instance = new UserSessionStateSingleton();
        return instance;
    }

}
