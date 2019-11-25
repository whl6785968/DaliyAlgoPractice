package Tire;


import org.apache.wml.dom.WMLHeadElementImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoubleArrayTrieTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\资料\\一些系列\\data\\smalldict.txt"));
        String line ;
        List<String> wordList = new ArrayList<String>();
        Set<Character> charset = new HashSet<Character>();

        while ((line = bufferedReader.readLine())!=null){
            wordList.add(line);
            for (char c:line.toCharArray()){
                charset.add(c);
            }
        }

        bufferedReader.close();

        DoubleArrayTrieImatate dat = new DoubleArrayTrieImatate();
//        DoubleArrayTrie dat = new DoubleArrayTrie();
        System.out.println("是否错误: " + dat.build(wordList));

        List<Integer> integerList = dat.commonPrefixSearch("一举成名天下知");
        System.out.println(integerList);
        for(int index:integerList){
            System.out.println(wordList.get(index));
        }
    }
}
