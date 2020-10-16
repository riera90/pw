package uco.i62rorid.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Algo.
 */
public class Algo {
    /**
     * Get sha 256 byte [ ].
     *
     * @param input the input
     * @return the byte [ ]
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Bin to hex string.
     *
     * @param hash the hash
     * @return the string
     */
    public static String binToHex(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    /**
     * Gets sha 256 as hex.
     *
     * @param input the input
     * @return the sha 256 as hex
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String getSHA256AsHex(String input) throws NoSuchAlgorithmException {
        return binToHex(getSHA256(input));
    }
}
