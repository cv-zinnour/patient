package ca.uqtr.patient;

import org.jasypt.util.text.BasicTextEncryptor;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class main {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "e3ec29b7-5edc-4a32-81f1-5ab31a04c988";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        String myHash = DatatypeConverter.printHexBinary(messageDigest).toUpperCase();
        System.out.println(myHash);
    }
}
