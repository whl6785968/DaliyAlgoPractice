package OtherTest;


import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.WordBasedSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;
import org.apache.jena.sparql.core.Var;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

public class VarTest {
    public static void main(String[] args) throws IOException {
//        arrayCopyTest();
        arrayCopyTest2(2,3);
    }

    public static void filet() throws IOException {
        URL resource = VarTest.class.getResource("");
        System.out.println(resource);
        URL resource1 = VarTest.class.getResource("/");
        System.out.println(resource1);
        URL resource2 = VarTest.class.getResource("VarTest.class");
        System.out.println(resource2);
        URL resource3 = VarTest.class.getResource("test1.txt");
        System.out.println(resource3);
        InputStream is = VarTest.class.getResourceAsStream("test1.txt");
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] bytes = new byte[is.available()];
        int len = 0;
        while ((len = bis.read(bytes))!=-1){
            String s = new String(bytes, 0, len);
            System.out.println(s);
        }
        URL resource4 = VarTest.class.getResource("test2.txt");
        System.out.println(resource4);

    }

    public static void arrayCopyTest(){
        int array[] = {1,3,5,7,9};
        int newArray[] = new int[array.length + 1];
        System.arraycopy(array,0,newArray,0,2);

        System.out.println(newArray.toString());
    }

    public static void arrayCopyTest2(int postion,int value){
        int array[] = {1,2,4,5};
        int newArray[] = new int[array.length + 1];

        for(int i = 0;i<postion;i++){
            newArray[i] = array[i];
        }

        for(int i=postion+1;i<newArray.length;i++){
            newArray[i] = array[i-1];
        }

        newArray[postion] = value;

        for(int v : newArray){
            System.out.println(v);
        }
    }

    public static byte[] readBytesFromFileInputStream(FileInputStream fis) throws IOException
    {
        FileChannel channel = fis.getChannel();
        int fileSize = (int) channel.size();
        ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);
        channel.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes = byteBuffer.array();
        byteBuffer.clear();
        channel.close();
        fis.close();
        return bytes;
    }

    public static void searcherTst(){
        char[] chars = "商品和服务".toCharArray();
        WordNet wordNet = new WordNet(chars);
        System.out.println(wordNet);
        char[] charArray = wordNet.charArray;
        DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher = CoreDictionary.trie.getSearcher(charArray, 0);

        while (searcher.next()){
            System.out.println(searcher.begin);
            System.out.println(searcher.length);
            System.out.println(searcher.value);
            System.out.println(searcher.index);
            System.out.println(searcher);
            wordNet.add(searcher.begin + 1,new Vertex(new String(charArray,searcher.begin,searcher.length),searcher.value,searcher.index));
        }

        System.out.println(wordNet);

//        LinkedList<Vertex>[] vertexes = wordNet.getVertexes();
//        for(int i=1;i<vertexes.length;){
//            if(vertexes[i].isEmpty()){
//                int j = i+1;
//                for(;j<vertexes.length-1;j--){
//                    if(!vertexes[j].isEmpty()) break;
//                }
////                wordNet.add(i,);
//            }
//        }

        DijkstraSegment dijkstraSegment = new DijkstraSegment();
        List<Term> list = dijkstraSegment.segSentence(chars);
        System.out.println(list);

    }
}
