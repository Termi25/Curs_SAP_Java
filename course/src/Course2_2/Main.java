package Course2_2;

import Course2_1.Utility;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static Course2_2.DigitalSignature6.getDigitalSignature;
import static Course2_2.DigitalSignature6.isDigitalSignatureValid;
import static Course2_2.TestKeyStore3.*;

public class Main {
    public static void main(String[] args) throws CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, SignatureException {
        KeyStore ks=getKeyStore("ismkeystore.ks","passks");
        printKSContent(ks);
        PublicKey ism1PK=getPublicKey(ks,"ismkey1");
        System.out.println("\nPublic key of ismkey1 pair:\n"+
                Utility.getHex(ism1PK.getEncoded()));

        PublicKey ismaseroPK=getPublicKey(ks,"ismasero");
        System.out.println("\nPublic key of ismasero pair:\n"+
                Utility.getHex(ismaseroPK.getEncoded()));

        PrivateKey ism1PVK=getPrivateKey(ks,"ismkey1","passks");
        System.out.println("\nPrivate key of ismkey1 pair:\n"+
                Utility.getHex(ism1PVK.getEncoded()));

        PublicKey x509PK=getPublicKeyFromX509("ISMCertificateX509.cer");
        System.out.println("\nPublic key of ismkey1 pair from X509 Certificate:\n"+
                Utility.getHex(x509PK.getEncoded()));

        byte[] randomAESKey=RandomKeysBest4.getSymmetricRandomKey(128,"AES");
        System.out.println("\nSymmetric AES Key:");
        System.out.println(Utility.getHex(randomAESKey));

        byte[] encAesKey=RSA5.encryptRSA(ism1PK,randomAESKey);
        System.out.println("\nAES encrypted key: "+Utility.getHex(encAesKey));

        byte[] decAesKey=RSA5.decryptRSA(ism1PVK,encAesKey);
        System.out.println("\nAES decrypted key: "+Utility.getHex(decAesKey));

        byte[] signature=getDigitalSignature("Message_C22_5.txt",ism1PVK);
        System.out.println("Message signature: "+Utility.getHex(signature));

        //at client
        if(isDigitalSignatureValid("Message_C22_5_broken.txt",signature,ism1PK)){
            System.out.println("Message is valid.");
        }else{
            System.out.println("Someone changed the message.");
        }
    }
}
