package HanLpLearning.ShortestPathCUT;

import com.hankcs.hanlp.corpus.io.IOUtil;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class WordNetGen {


    public static void main(String[] args) throws IOException {

    }

    public List<List<String>> genWordnet(String[] wordList){
        for(int i=0;i<wordList.length-1;i++){

        }
        return null;
    }

    public static int getWeighted(TreeMap<String, Integer> treeMap,int totalFreq){



        return 0;
    }

    public static TreeMap<String, Integer> read_txt(String path) throws IOException {
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        FileReader fis = new FileReader(path);
        BufferedReader br = new BufferedReader(fis);
        String line ;
        String word;
        int freq;
        while ((line = br.readLine()) != null){
            String[] s = line.split(" ");
            word = s[0];
            freq = Integer.parseInt(s[1]);
            treeMap.put(word,freq);
        }

        return treeMap;
    }

    public static int getTotalFreq(TreeMap<String,Integer> treeMap){
        Collection<Integer> values = treeMap.values();
        int totalFreq = 0;
        for(int value : values){
            totalFreq += value;
        }

        return totalFreq;
    }




}
