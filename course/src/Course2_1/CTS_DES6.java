package Course2_1;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CTS_DES6 {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        encryptDES("Message_C21_13456.txt","Message_C21_6.enc","ism12345".getBytes());
        System.out.println("Encryption finished");

        decryptDES("Message_C21_6.enc","Message_C21_6.txt","ism12345".getBytes());
        System.out.println("Decryption finished");
    }

    public static void encryptDES(String inputFileName,
                                  String outputFileName,
                                  byte[] key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        File inputFile=new File(inputFileName);
        if(!inputFile.exists()){
            throw new UnsupportedOperationException("Input File does not exist.");
        }

        File outputFile=new File(outputFileName);
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }

        FileInputStream fis=new FileInputStream(inputFile);
        FileOutputStream fos=new FileOutputStream(outputFile);

        Cipher cipher=Cipher.getInstance("DES/CTS/NoPadding");
        SecretKeySpec keySpec=new SecretKeySpec(key,"DES");

        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        byte[] buffer=new byte[cipher.getBlockSize()];

        //IV values
        //1. hardcoded known value
        //2. known value or any value stored in the ciphertext

        //option 2
        //IV has the 3rd byte with all bits 1
        byte[] IV = new byte[cipher.getBlockSize()]; //for AES, IV is to be 16
        IV[2]= (byte) 0xFF;

        //write IV into file
        fos.write(IV);
        IvParameterSpec ivSpec=new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

        while(true){
            int noBytes=fis.read(buffer);

            if(noBytes==-1){
                break;
            }

            byte[] output=cipher.update(buffer,0,noBytes);
            fos.write(output);
        }
        byte[] output=cipher.doFinal();
        fos.write(output);

        fis.close();
        fos.close();
    }

    public static void decryptDES(String inputFileName,
                                  String decryptedFileName,
                                  byte[] key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        File inputFile=new File(inputFileName);
        if(!inputFile.exists()){
            throw new UnsupportedOperationException("Input file not found for decryption.");
        }

        File outputFile=new File(decryptedFileName);
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }

        FileInputStream fis=new FileInputStream(inputFile);
        FileOutputStream fos=new FileOutputStream(outputFile);

        Cipher cipher=Cipher.getInstance("DES/CTS/NoPadding");
        SecretKeySpec keySpec=new SecretKeySpec(key,"DES");

        byte[] IV=new byte[cipher.getBlockSize()];
        fis.read(IV);
        IvParameterSpec ivSpec=new IvParameterSpec(IV);

        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);

        byte[] buffer=new byte[cipher.getBlockSize()];

        while(true){
            int noBytes=fis.read(buffer);

            if(noBytes==-1){
                break;
            }

            byte[] output=cipher.update(buffer,0,noBytes);
            fos.write(output);
        }
        byte[] output=cipher.doFinal();
        fos.write(output);

        fis.close();
        fos.close();
    }
}
