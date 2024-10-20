package Course1_2;

import java.util.Arrays;
import java.util.Base64;

public class Encoding6 {
    public static void main(String[] args) {
        //this isn't included in the scope of the Java SAP Course, but it is essential but key transmission at the exam or evaluation
        //managing binary data as strings

        byte[] values=new byte[]{(byte)0x00,(byte)0x01,(byte)0x30,(byte)0x62};
        byte[] values2=new byte[]{(byte)0x00,(byte)0x02,(byte)0x30,(byte)0x62};

        //convert to String <- this is a BIG MISTAKE
        String stringValue=new String(values);
        String stringValue2=new String(values2);

        //the values look the same, but they have different values
        System.out.println(stringValue);
        System.out.println(stringValue2);

        //encoding in Base64
        String value1Base64= Base64.getEncoder().encodeToString(values);
        String value2Base64=Base64.getEncoder().encodeToString(values2);

        System.out.println(value1Base64);
        System.out.println(value2Base64);

        //decoding from Base64
        byte[] initialValues= Base64.getDecoder().decode(value1Base64);
        byte[] initialValues2=Base64.getDecoder().decode(value2Base64);

        System.out.println(Arrays.toString(initialValues));
        System.out.println(Arrays.toString(initialValues2));
    }
}
