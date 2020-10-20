package uco.i62rorid.pw.Views.CLI.Auth;

import uco.i62rorid.pw.Views.CLI.User.Create.CreateUser;

import java.security.NoSuchAlgorithmException;

/**
 * The type Register.
 */
public class Register {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        CreateUser.init();
        Login.init();
    }
}
