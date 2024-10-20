package Course1_2;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRNGs5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //use crypto safe PRNG
        //DON'T use Random

        SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
        byte[] desKey = new byte[8];
//        sr.nextBytes(desKey);
//        //generators without seeds will generate different numbers (timestamped). If you want to sync RNGs, use seeds.
//        System.out.println(getHexStringFromByteArray(desKey));

        //Using a seed - works perfectly without generating before setting the seed. Otherwise, the seed will work w=to add entropy
        //like the Enigma Machine Plugs
        sr.setSeed(new byte[]{(byte)0xFF,(byte)0xA8});
        sr.nextBytes(desKey);
        System.out.println(getHexStringFromByteArray(desKey));
    }

    private static String getHexStringFromByteArray(byte[] values){
        StringBuilder sb=new StringBuilder();
        for(byte value:values){
            sb.append(String.format(" %02X",value));
        }
        return sb.toString();
    }
}
