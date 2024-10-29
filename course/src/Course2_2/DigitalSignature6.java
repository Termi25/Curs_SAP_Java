package Course2_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;

public class DigitalSignature6 {
    public static byte[] getDigitalSignature(String file, PrivateKey privateKey) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        File inputF=new File(file);
        if(!inputF.exists()){
            throw new UnsupportedOperationException("Missing file");
        }

        FileInputStream fis=new FileInputStream(inputF);

        Signature signature=Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        //process the entire file in one round
        byte[] buffer=fis.readAllBytes();
        signature.update(buffer);
        fis.close();

        return signature.sign();

//        //no prerequisite size
//        byte[] buffer=new byte[1024];

    }

    public static boolean isDigitalSignatureValid(String fileName, byte[] signature, PublicKey publicKey) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        File inputF=new File(fileName);
        if(!inputF.exists()){
            throw new UnsupportedOperationException("Missing file");
        }

        FileInputStream fis=new FileInputStream(inputF);

        Signature sign=Signature.getInstance("SHA1withRSA");
        sign.initVerify(publicKey);

        //process the entire file in one round
        sign.update(fis.readAllBytes());

        fis.close();
        return sign.verify(signature);
    }
}
