package Course2_1;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ECB_AES3 {
    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        encrypt("Message_C21_13456.txt","Message_C21_3.enc", "ism12345password".getBytes(),"AES");
        System.out.println("Encryption finalized.");

        decrypt("Message_C21_3.enc","Message_C21_3.txt", "ism12345password".getBytes(),"AES");
        System.out.println("Decryption finalized.");
    }

    public static void encrypt(String inputFileName,
                               String encryptedFileName,
                               byte[] key,
                               String algorithm) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        File inputFile=new File(inputFileName);
        if(!inputFile.exists()){
            throw new UnsupportedOperationException("Input File Missing");
        }

        File outputFile=new File(encryptedFileName);
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }

        FileInputStream fis=new FileInputStream(inputFile);
        BufferedInputStream bis=new BufferedInputStream(fis);

        FileOutputStream fos=new FileOutputStream(outputFile);
        BufferedOutputStream bos=new BufferedOutputStream(fos);

        Cipher cipher=Cipher.getInstance(algorithm+"/ECB/PKCS5Padding");

        SecretKeySpec keySpec=new SecretKeySpec(key,algorithm);

        cipher.init(Cipher.ENCRYPT_MODE,keySpec);

        byte[] buffer=new byte[cipher.getBlockSize()];

        while(true) {
            int noBytes = bis.read(buffer);

            if (noBytes == -1) {
                break;
            }

            byte[] output = cipher.update(buffer, 0, noBytes);
            bos.write(output);
        }
        byte[] output=cipher.doFinal();
        bos.write(output);

        bis.close();
        bos.close();
    }

    public static void decrypt(String inputFileName,
                               String decryptedFileName,
                               byte[] key,
                               String algorithm) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        File inputFile=new File(inputFileName);
        File outputFile=new File(decryptedFileName);

        if(!outputFile.exists()){
            outputFile.createNewFile();
        }

        FileInputStream fis=new FileInputStream(inputFile);
        FileOutputStream fos=new FileOutputStream(outputFile);

        Cipher cipher=Cipher.getInstance(algorithm+"/ECB/PKCS5Padding");
        SecretKeySpec secretKey=new SecretKeySpec(key,algorithm);

        cipher.init(Cipher.DECRYPT_MODE,secretKey);

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

        fos.close();
        fis.close();
    }
}
