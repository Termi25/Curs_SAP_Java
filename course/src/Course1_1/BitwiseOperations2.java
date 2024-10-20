package Course1_1;

public class BitwiseOperations2 {
    public static void main(String[] args) {
        //Initialising binary and hexadecimal values
        byte value=15;
        System.out.println(value);

        value=0b00001111;
        System.out.println(value);
        //0000 1000+ <=> 1<<3 |
        //0000 0100+ <=> 1<<2 |
        //0000 0010+ <=> 1<<1 |
        //0000 0001+ <=> 1<<0 |
        //----------
        //0000 1111
        // we can use shifting
        value=1<<3 | 1<<2 | 1<<1 | 1<<0;
        System.out.println(value);

        value=0x0F;
        System.out.println(value);

        value=8;
        value= (byte) (value<<1); //multiply by 2
        System.out.println(value);

        value=(byte) (value>>1); //divide by 2
        System.out.println(value);

        value=65;
        value= (byte) (value<<1);
        System.out.println(value);

        value=-1;
        System.out.printf("%02x%n",value);

        //When shifting to the right, you preserve the sign bit => shifting -1 in binary to the right,
        //you move it and add 1, so it stays constant
        value=(byte)(value>>3);
        System.out.println(value);

        //It isn't affected by >>>, respectively moving the bit sign. The normal shift preserves the sign bit. It doesn't work on Bytes.
        value=(byte)(value>>>1);
        System.out.println(value);

        int value2=-1;
        value2=value2>>>1;//it doesn't preserve the sign bit
        System.out.println(value2);

        //Checking for bits

        //Check if a byte has the 3rd bit 1 left to right, not the index approach
        byte anyValue=39;
        //use a bit mask
        byte bitMask=0b00100000; //not recommended
        byte optimalBitMask=1<<5; //recommended
        byte result= (byte) (anyValue & optimalBitMask);
        if(result==0){
            System.out.println("3rd bit is 0");
        }else{
            System.out.println("3rd bit is 1");
        }
    }
}
