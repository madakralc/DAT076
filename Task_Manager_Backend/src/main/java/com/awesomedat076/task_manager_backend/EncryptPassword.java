/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.security.*; 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec; 
import sun.misc.*; 

/**
 * This class is used for encrypting the passwords for all users. We do not want
 * to save clear text passwords in the database - so this is the reason why we
 * use this class.
 * 
 * @author dagf
 */
public class EncryptPassword {
   
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = new byte[] {'D','a','T','0','6','7','D','a','T','0','6','7','D','a','T','0'}; 
    
    /**
     * This method encrypts the password.
     * @param password in clear text
     * @param username in clear text for salt use
     * @return a string with the encrypted password
     * @throws Exception 
     */
    public static String encryptPassword(String password, String username) throws Exception { 
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        String toEncrypt = password + username;
        c.init(Cipher.ENCRYPT_MODE, key); 
        byte[] encValue = c.doFinal(toEncrypt.getBytes()); 
        String encryptedPassword = new BASE64Encoder().encode(encValue); 
        return encryptedPassword; 
    } 

    /**
     * This method can be used to decrypt password (and salt). Can be used for debugging, no to bee used on the application. 
     * @param encryptedPassword.
     * @return the decrypted password (including the salt).
     * @throws Exception 
     */
    public static String decryptPassword(String encryptedPassword, String username) throws Exception { 
        Key key = generateKey(); 
        Cipher c = Cipher.getInstance(ALGORITHM); 
        c.init(Cipher.DECRYPT_MODE, key); 
        byte[] decordedEncryptedPassword = new BASE64Decoder().decodeBuffer(encryptedPassword); 
        byte[] decPasswordValue = c.doFinal(decordedEncryptedPassword); 
        String decryptedValue = new String(decPasswordValue); 
        return decryptedValue; 
    } 

    /**
     * This method is user to generate the key.
     * @return the key in correct format.
     * @throws Exception 
     */
    private static Key generateKey() throws Exception { 
        Key key = new SecretKeySpec(keyValue, ALGORITHM); 
        // SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); '
        // key = keyFactory.generateSecret(new DESKeySpec(keyValue)); 
        return key; 
    } 
} 