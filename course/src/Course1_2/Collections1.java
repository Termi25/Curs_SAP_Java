package Course1_2;


import java.util.*;

public class Collections1 {
    public static void main(String[] args) {
        //Collections Types

        //List - very flexible (variable size arrays), ordered by insertion
        //Sets - variable size arrays with unique values
        //Map - dictionary of (key,value) pairs, where the key is unique

        //List
        System.out.println("\n\n1. Lists -------------------------\n");
        List<String> list1=new ArrayList<>();
        //When importing, use the .util import or .security
        list1.add("Keys.txt");
        list1.add("Passwords.txt");
        list1.add("Users.txt");

        for(String value:list1){
            System.out.println(value);
        }

        //Set
        System.out.println("\n\n2. Sets -------------------------\n");
        Set<String> usernames=new HashSet<String>();
        usernames.add("Alice");
        usernames.add("Bob");
        usernames.add("Constantine");
        usernames.add("Alice");

        for(String value:usernames){
            System.out.println("User: "+value);
        }

        //Map
        System.out.println("\n\n3. Maps -------------------------\n");
        HashMap<Integer,String> userdatabase=new HashMap<>();
        //In the 3rd Semester you will do a parallel programming course which will show you the thread safe classes.
        userdatabase.put(10,"Alice");
        userdatabase.put(20,"John");
        userdatabase.put(30,"Bob");
        userdatabase.put(10,"Vader");

        userdatabase.keySet().stream().sorted().forEach(id -> System.out.printf("ID: %d - user: %s\n",id,userdatabase.get(id)));


        //Test shallow copy
        System.out.println("\n\n Shallow Copy Test -------------------------\n");

        List<Byte> key=Arrays.asList((byte)0xA4,(byte)0x10,(byte)0x2F,(byte)0x22);
        Certificate2 cert=new Certificate2("Catalin",key,key.size(),"ism.ase.ro");
        System.out.println(cert);

        Certificate2 cert2=new Certificate2("PortalISM",key,key.size(),"portal.ism.ase.ro");
        System.out.println(cert2);

        key.set(1,(byte)0xFF);

        System.out.println(cert2);

        //BitSet
        System.out.println("\n\n4. BitSet -------------------------\n");

        BitSet bitSet=new BitSet(32);
        bitSet.set(0);
        bitSet.set(30);
        System.out.println(bitSet);

        BitSet register=BitSet.valueOf(new byte[]{(byte) 0xA4, (byte) 0x10, (byte) 0x2F, (byte) 0x22});
        System.out.printf("Register: ");
        for(int i=0;i< register.size();i++){
            System.out.printf("%d",register.get(i) ? 1:0);
        }

        System.out.printf("\nRegister: ");
        for(byte b: register.toByteArray()){
            System.out.printf(" %02X",b);
        }
    }
}
