package com.passwordStorage.demo.service;


import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    public static String hash(String initalPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(initalPassword.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NU S-A PUTUT");
            return initalPassword;
        }
    }

    public static String hashAndPepper(String initalPassword) {
        return hash(initalPassword+"PEPPER");
    }

    public static String saltAndPepper(String initialPassword, String salt){
        return  hashAndPepper(initialPassword+salt);
    }

    public static String magicSaltAndPepperHash(String initialPassword, String salt){
        String hashedPassword = hashAndPepper(initialPassword);
        String hashedSalt = hash(salt);
        return intertwine(hashedPassword,hashedSalt);
    }

    private static String intertwine(String s1,String s2){
        int i,j;
        j=0;
        char[] magicHash = new char[64];
        for(i=0;i<=31;i++){
            magicHash[j]=s1.charAt(i);
            j++;
            magicHash[j]=s2.charAt(i);
            j++;
        }
        return String.valueOf(magicHash);
    }

}
