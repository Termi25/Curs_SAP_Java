package Course2_1;

public class Utility {
    public static String getHex(byte[] values){
        StringBuilder sb=new StringBuilder();
        for(byte b:values){
            sb.append(String.format(" %02X",b));
        }
        return sb.toString();
    }
}
