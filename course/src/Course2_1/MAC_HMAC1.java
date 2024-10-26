package Course2_1;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_HMAC1 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] hmac=getHMAC("Message_C21_1.txt","HmacSHA1","ism1234");
        System.out.println("HMAC result: "+Utility.getHex(hmac));

        //password changed
        byte[] hmac2=getHMAC("Message_C21_1.txt","HmacSHA1","ism12345");
        System.out.println("HMAC result: "+Utility.getHex(hmac2));
    }

    public static byte[] getHMAC(String filename, String algorithm,String password)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        Mac hmac=Mac.getInstance(algorithm);
        hmac.init(new SecretKeySpec(password.getBytes(),algorithm));

        //read the file in chunks of bits and process it
        File file=new File(filename);
        FileInputStream fis=new FileInputStream(file);
        BufferedInputStream bis=new BufferedInputStream(fis);
        byte[] buffer=new byte[8];

        while(true){
            int noBytes=bis.read(buffer);

            if(noBytes==-1){
                break;
            }

            hmac.update(buffer,0,noBytes);
        }
        bis.close();

        return hmac.doFinal();
    }


}
