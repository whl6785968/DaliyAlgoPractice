package OtherTest.IOTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileTst {
    static int length = 0x8000000;

    public static void ratst() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\tst.txt", "rw");

        for(int i=0;i<10;i++){
            rf.writeDouble(i*3.14);
        }

        rf.close();

        rf = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\tst.txt","rw");
        rf.seek(5*8);

        rf.writeDouble(42.22);
        rf.close();

        rf = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\tst.txt","r");

        for(int i=0;i<10;i++){
            System.out.println("value" + i + ":" + rf.readDouble());
        }

        rf.close();

    }

    public static void mappingFileTst(String path) throws IOException {
        FileChannel fc = new RandomAccessFile(path, "rw").getChannel();
        MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);

        for (int i=0;i<length;i++){
            map.put((byte)'x');
        }

        System.out.println("Finished Write");

        for(int i=length/2;i<length/2+6;i++){
            System.out.println((char)map.get(i));
        }

        fc.close();
    }

    public static void main(String[] args) throws IOException {
//        ratst();
        String path = "C:\\Users\\Administrator\\Desktop\\tst.txt";
        mappingFileTst(path);
    }
}
