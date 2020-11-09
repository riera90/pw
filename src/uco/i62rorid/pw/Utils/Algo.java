package uco.i62rorid.pw.Utils;

import uco.i62rorid.pw.Connectors.FileConn;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

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
    public byte[] getSHA256(String input) throws NoSuchAlgorithmException {
        try {
            InputStream in = getClass().getResourceAsStream("/.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            MessageDigest md = MessageDigest.getInstance(p.getProperty("PASSWORD_HASHING_ALGORITHM"));
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NullPointerException e) {
            try {
                FileReader reader=new FileReader(".properties");
                Properties p = new Properties();
                p.load(reader);
                MessageDigest md = MessageDigest.getInstance(p.getProperty("PASSWORD_HASHING_ALGORITHM"));
                return md.digest(input.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Bin to hex string.
     *
     * @param hash the hash
     * @return the string
     */
    public String binToHex(byte[] hash){
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
    public String getSHA256AsHex(String input) throws NoSuchAlgorithmException {
        return binToHex(getSHA256(input));
    }
}
