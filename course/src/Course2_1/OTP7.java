package Course2_1;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class OTP7 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String message="The requirements for tomorrow are...";
        byte[] randomKey=generateRandomKey(message.length());

        byte[] encMsg=otpEncrypt(message.getBytes(),randomKey);

        System.out.println("Cipher (Hex): "+Utility.getHex(encMsg));
        System.out.println("Cipher (Base64): "+ Base64.getEncoder().encodeToString(encMsg));

        byte[] decMsg=otpEncrypt(encMsg,randomKey);

        System.out.println("Decrypted message: "+new String(decMsg));
    }

    public static byte[] generateRandomKey(int keySizeInBytes) throws NoSuchAlgorithmException {
        SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
        byte[] random=new byte[keySizeInBytes];
        sr.nextBytes(random);
        return random;
    }

    public static byte[] otpEncrypt(byte[] plaintext,byte[]key){
        if(plaintext.length!=key.length){
            throw new UnsupportedOperationException("Message and key must be the same length.");
        }

        byte[] cipher=new byte[key.length];

        for(int i=0;i<plaintext.length;i++){
            cipher[i]= (byte) ((byte)plaintext[i] ^ (byte)key[i]);
        }

        return cipher;
    }
}
