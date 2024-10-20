package Course1_2;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class TestHash4 {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
        //checking and using different providers - BouncyCastle - package names are key for the right import and functionality
        String bouncyCastleProvider="BC";

        //check if the provider is available - this one isn't correctly loaded
        Provider provider= Security.getProvider(bouncyCastleProvider);
        if(provider ==null){
            System.out.println("Bouncy Castle isn't available.");
        }else{
            System.out.println("Bouncy Castle is available.");
        }

        // load BC provider
        Security.addProvider(new BouncyCastleProvider());
        provider= Security.getProvider(bouncyCastleProvider);
        if(provider ==null){
            System.out.println("Bouncy Castle isn't available.");
        }else{
            System.out.println("Bouncy Castle is available.");
        }

        //demonstration about automatically loaded libraries
//        provider= Security.getProvider("SUN");
//        if(provider ==null){
//            System.out.println("SUN isn't available.");
//        }else{
//            System.out.println("SUN is available.");
//        }

        //hashing a string
        MessageDigest md=MessageDigest.getInstance("SHA-1","BC");
        String message="ISM";

        //compute hash in one step when input is small enough
        byte[] hashValue=md.digest(message.getBytes());
        System.out.println("Message: \""+message+"\" with hash value in SHA-1: "+getHexStringFromByteArray(hashValue));

        //processing a file and computing the hash value of its contents - mandatory for exam subject
        File msgFile=new File("Message_C12_4.txt");
        if(!msgFile.exists()){
            System.out.println("File not found!");
        }
        FileInputStream fis=new FileInputStream(msgFile);
        BufferedInputStream bis=new BufferedInputStream(fis);

        md=MessageDigest.getInstance("MD5","BC");
        byte[] buffer=new byte[8];
        //hwne using hashSet, you can omit bits by writing .add(null,(byte)0xFF);

        //area for easy mistakes
        do{
            int noBytes=bis.read(buffer);//we try to read 8 bytes
            //if number of Bytes = -1 => end of file
            if(noBytes!=-1){
                md.update(buffer,0,noBytes);
                //common mistake above: we compute the hash of the last block with residual values from the previous block
            }else{
                break;
            }
        }while(true);
        bis.close();

        //get final hash
        hashValue=md.digest();
        System.out.println("Hash value of the text inside the file entitled \"Message_C12_4.txt\": "
                +getHexStringFromByteArray(hashValue));

    }

    private static String getHexStringFromByteArray(byte[] values){
        StringBuilder sb=new StringBuilder();
        for(byte value:values){
            sb.append(String.format(" %02X",value));
        }
        return sb.toString();
    }
}
