package Course1_1;

public class Conversions1 {
    public static void main(String[] args) {
        stringComparingIssue();
        System.out.println("\n---------------------------------------------\n");
        certificationJavaDataStuff();
        System.out.println("\n---------------------------------------------\n");
        //The following is very important
        convertingNumbersToStrings();
        System.out.println("\n---------------------------------------------\n");
        baseSixtyFour();
    }

    private static void baseSixtyFour() {

    }

    public static void stringComparingIssue(){
        //Comparing Strings
        String file1 = "Keys.txt";
        String file2 = "Keys.txt";

        if(file1 == file2){
            System.out.println("The file names are the same.");
        }else{
            System.out.println("The file names are different.");
        }
        //Mistake: comparing with == instead of using String.equals() method

        file2=new String("Keys.txt");
        if(file1 == file2){
            System.out.println("The file names are the same.");
        }else{
            System.out.println("The file names are different.");
        }
        // Reason: In Java, they said hey, what's the mostly costly stuff in Java? Creating objects. In terms of performance llet's speed up.
        // Everytime when you create objects in String, it allocates memory in Heap, which, if they have the same value, it shouldn't store
        // the same duplicated values.

        //Java Constant Strings Pool = location for hardcoded strings which store a value once, no duplicates => Side Effect:
        //any hardcoded object refferences the same value; When Using new String(), we specifically ask the PC to create a new String
        //Refference

        // How do we compare the content, not the address?

        if(file1.equals(file2)){
            System.out.println("The file names are the same.");
        }else{
            System.out.println("The file names are different.");
        }
    }

    public static void certificationJavaDataStuff(){
        int vb=10;
        Integer intVb=127;
        Integer intVb2=127;
        if(intVb == intVb2){
            System.out.println("The numbers are the same.");
        }else{
            System.out.println("The numbers are different.");
        }

        Integer intVb3=128;
        Integer intVb4=128;
        if(intVb3 == intVb4){
            System.out.println("The numbers are the same.");
        }else{
            System.out.println("The numbers are different.");
        }

        //Different behaviour for number between 0 and 127 and for over 128 differ.
        //In Java all the values are signed.
        //For numbers between 0 and 127, the Integer has small Int
        //For bigger numbers from 128 the objects are different types.
        //Conclusion: Always use equals for String, Double and Integer
    }

    private static void convertingNumbersToStrings() {
        int value=33;
        String stringBin=Integer.toBinaryString(value);
        String stringHex=Integer.toHexString(value);

        System.out.println(value+":(Decimal) -> (Binary): "+Integer.toBinaryString(value));
        System.out.println(value+":(Decimal) -> (Hexadecimal): "+Integer.toHexString(value));

        System.out.println();
        byte smallValue=127;
        System.out.println("Binary string: "+Integer.toBinaryString(smallValue));
        System.out.println("HEX string: "+Integer.toHexString(smallValue));

        System.out.println();
        byte smallValue2=-23;
        System.out.println("Binary string: "+Integer.toBinaryString(smallValue2));
        //Issue: when converting, we receive the 32bit value, not 8bit value + verifica jurnal ptr conversie valoare negativa la binar
        System.out.println("HEX string: "+Integer.toHexString(smallValue2));

        //byte to string conversion
        System.out.println("Byte to Binary String "+Integer.toBinaryString(Byte.toUnsignedInt(smallValue2)));
        System.out.println("Byte to Hex String "+Integer.toHexString(Byte.toUnsignedInt(smallValue2)));


        byte[] hash ={(byte)23,(byte)-23,(byte)10,(byte)5};
        //Wrong way of doing this
        String hashHexString="";
        for(int i=0;i<hash.length;i++){
            hashHexString+=Integer.toHexString(hash[i]);
        }
        System.out.println("\n"+hashHexString);

        //Immutable = the object cannot change it's value (uses constant values)

        //StringBuilder and StringBuffer, one of them is thread safe
        //The right way of doing this
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<hash.length;i++){
            if(Integer.toHexString(Byte.toUnsignedInt(hash[i])).length()==1){
                sb.append("0");
            }
            sb.append(Integer.toHexString(Byte.toUnsignedInt(hash[i])));
            if(i!=hash.length-1){
                sb.append("-");
            }
        }
        System.out.println(sb.toString().toUpperCase());
        System.out.println(getHexStringFromByteArray(hash));

        //Convert from string to numbers
        Integer initialValue=Integer.parseInt(stringHex,16);
        System.out.println("\nInitial Value: "+initialValue);
        initialValue=Integer.parseInt(stringBin,2);
        System.out.println("\nInitial Value: "+initialValue);
    }

    private static String getHexStringFromByteArray(byte[] values){
        StringBuilder sb=new StringBuilder();
        for(byte value:values){
            sb.append(String.format(" %02X",value));
        }
        return sb.toString();
    }

}