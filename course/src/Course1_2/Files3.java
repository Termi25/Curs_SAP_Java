package Course1_2;

import java.io.*;

public class Files3 {

    public static void listFolder(File root){
        if(root.exists() && root.isDirectory()){
            System.out.println("The directory exists");
            File[] items=root.listFiles();
            assert items != null;
            for(File item:items){
                System.out.println(item.getName()+
                        (item.isDirectory() ? " - directory": " - folder")+
                        " - path: "+
                        item.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //File class is for managing connection to File System, basically a pointer, not the file itself.
        //For accessing files you need multiple classes, due to 1 char having 2 bytes in Java, unlike the default 1 byte per char.
        //Two frameworks:
        // 1. For Text Files with 2 layers:
        //      1.1. Reader class
        //      1.2. Writer class
        // 2. For Binary Files with multiple classes for creating a pipeline:
        //      2.1. File InputStream class
        //      2.2. Buffered InputStream class
        //      2.3. Data InputStream class

        //1. Playing with the File System
        System.out.println("\nFile System Management ----------------------------------------------\n");

        File repo=new File("D:\\MARIUS\\FACULTATE\\SAP_ISM\\Curs1_1_SAP");
        listFolder(repo);

        //2. Text Files Reading & Writing
        System.out.println("\nText File ----------------------------------------------\n");

        //Reading
        System.out.println("\n  1. Reading ----------------------------------------------\n");

        File msgFile=new File("Text_C12_3.txt");
        if(!msgFile.exists()){
            //potential IO Exception bellow for creating a new file
            msgFile.createNewFile();
            System.out.println("Creating a new text file if doesn't exist.");
        }

        //reading from a text file
        FileReader fileReader=new FileReader(msgFile);
        BufferedReader bfReader=new BufferedReader(fileReader);
        System.out.println("File content: \n---------\n");
        String line;
        do {
            line=bfReader.readLine();
            System.out.println(line);
        }while(line!=null);
        //Otherwise, you can do it with bfReader.lines() and go through them with a forEach
        bfReader.close();
        //It doesn't matter which we close, fileReader or bufferedReader, as long as we close one of them, it closes the entire pipeline.

        //Writing
        System.out.println("\n  2. Writing ----------------------------------------------\n");
        System.out.println("Check message text file in root.");

        //write into the text File by replacing
        PrintWriter printWriter=new PrintWriter(msgFile);
        printWriter.println("This is a secret message.");
        printWriter.println("Don't tell anyone.");
        printWriter.close();
        System.out.println("Writing to file without append.");

        //write into text File with append mode
        FileWriter fileWriter=new FileWriter(msgFile,true);
        printWriter=new PrintWriter(fileWriter);
        printWriter.println("This is an appended message.");
        printWriter.printf("Don't tell anyone.");
        printWriter.close();
        System.out.println("Writing to file with append mode.");

        //3. Binary Files Reading & Writing
        System.out.println("\nBinary File ----------------------------------------------\n");

        //Reading
        System.out.println("\n  1. Reading ----------------------------------------------\n");

        File binaryFile=new File("Binary_C12_3.bin");
        if(!binaryFile.exists()){
            //potential IO Exception bellow for creating a new file
            binaryFile.createNewFile();
            System.out.println("Creating a new binary file if doesn't exist.");
        }

        //read binary file
        FileInputStream fis=new FileInputStream(binaryFile);
        BufferedInputStream bis=new BufferedInputStream(fis);
        DataInputStream dis=new DataInputStream(bis); //this isn't mandatory
        float fv=dis.readFloat();
        Double dv=dis.readDouble();
        int iv=dis.readInt();
        boolean bv=dis.readBoolean();
        String text=dis.readUTF();
        byte[] Bvalues=dis.readAllBytes();
        dis.close();

        System.out.println(iv+text);


        //Writing
        System.out.println("\n  2. Writing ----------------------------------------------\n");
        System.out.println("Check binary file in root.");

        float floatValue=23.5f;
        double doubleValue=10;
        int intValue=10;
        boolean flag=true;
        String txt="hello";
        byte[] values=new byte[]{(byte)0xFF,(byte)0x0A};

        FileOutputStream fos=new FileOutputStream(binaryFile);
        DataOutputStream dos=new DataOutputStream(fos);

        dos.writeFloat(floatValue);
        dos.writeDouble(doubleValue);
        dos.writeInt(intValue);
        dos.writeBoolean(flag);
        dos.writeUTF(txt);
        dos.write(values);//we forgot to put the size of the array before it
        dos.close();



        RandomAccessFile raf=new RandomAccessFile(binaryFile,"r");
        raf.seek(12); //jump the float and the double; 12 bytes
        int vb=raf.readInt();
        raf.close();
        System.out.println("Read int value with RandomAccessFile from file: "+vb);
    }
}
