package Course2_1;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PBKDF2 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] pbkdfKey=getPBKDF("ism1234","PBKDF2WithHmacSHA1","rd@h1",1000,160);
        //Don't convert byte Array to String!
        System.out.println("PBKDF key value: "+Utility.getHex(pbkdfKey));

        byte[] pbkdfKey2=getPBKDF("ism1234","PBKDF2WithHmacSHA1","dfhfghj",1000,160);
        //Don't convert byte Array to String!
        System.out.println("PBKDF key 2nd value: "+Utility.getHex(pbkdfKey2));

        //benchmark PBKDF performance
        int noIterations=(int)1e7;
        double tStart=System.currentTimeMillis();
        byte[] pbkdfKey3=getPBKDF("ism1234","PBKDF2WithHmacSHA1","dfhfghj",noIterations,160);
        double tFinal=System.currentTimeMillis();
        System.out.printf("\nPBKDF with %d iterations computed in %f seconds.\n",noIterations,(tFinal-tStart)/1000);
        System.out.println("PBKDF key 3nd value: "+Utility.getHex(pbkdfKey3));

    }

    public static byte[] getPBKDF(String userPassword,
                                  String algorithm,
                                  String salt,
                                  int noIterations,
                                  int outputSize) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec=new PBEKeySpec(userPassword.toCharArray(),
                                            salt.getBytes(),
                                            noIterations,
                                            outputSize);
        SecretKeyFactory pbkdf=SecretKeyFactory.getInstance(algorithm);

        SecretKey key=pbkdf.generateSecret(pbeKeySpec);
        return key.getEncoded();
    }
}
