package HanLpLearning;


import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.dictionary.CoreDictionary;

import java.io.IOException;
import java.util.*;

public class EasySegment {
    public static List<String> segmentFully(String text,TreeMap<String,CoreDictionary.Attribute> dictionary){
        LinkedList<String> wordList = new LinkedList<String>();
        for(int i=0;i<text.length();i++){
            for(int j=i+1;j<text.length();j++){
                String word = text.substring(i,j);
                if(dictionary.containsKey(word)){
                    wordList.add(word);
                }
            }
        }

        return wordList;
    }

    public static List<String> segmentFullyByTire(final String text,BinTrie<CoreDictionary.Attribute> dictionary){
        final LinkedList<String> wordList = new LinkedList<String>();
        dictionary.parseText(text, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
            public void hit(int begin, int end, CoreDictionary.Attribute value) {

                wordList.add(text.substring(begin,end));
            }
        });

        return wordList;
    }

    public static List<String> segmentForwardLongest(String text,Map<String ,CoreDictionary.Attribute> dictionary){
        LinkedList<String> wordList = new LinkedList<String>();

        for(int i=0;i<text.length();){
            String longestWord = text.substring(i,i+1);
            for(int j=i+1;j<=text.length();j++){
                String word = text.substring(i,j);
                if(dictionary.containsKey(word)){
                    if(word.length()>longestWord.length()){
                        longestWord = word;
                    }
                }
            }

            wordList.add(longestWord);
            i += longestWord.length();
        }
        return wordList;
    }

    public static List<String> segmentBackLongest(String text,TreeMap<String,CoreDictionary.Attribute> dictionary){
        LinkedList<String> wordList = new LinkedList<String>();
        for(int i=text.length()-1;i>=0;){
            String longestWord = text.substring(i,i+1);
            for(int j=0;j<=i;j++){
                String word = text.substring(j,i+1);
                if(dictionary.containsKey(word)){
                    if(word.length()>longestWord.length()){
                        longestWord = word;
                    }
                }
            }

            wordList.add(longestWord);
            i -= longestWord.length();
        }

        Collections.reverse(wordList);

        return wordList;
    }

    public static int countSingleWord(List<String> wordList){
        int size = 0;
        for(String word:wordList){
            if(word.length() == 1){
                size++;
            }
        }

        return size;
    }

    public static List<String> biSegmentLongest(String text,TreeMap<String,CoreDictionary.Attribute> dictionary){
        List<String> forward = segmentForwardLongest(text,dictionary);
        List<String> back = segmentBackLongest(text,dictionary);

        if(forward.size()>back.size()){
            return back;
        }
        else if(forward.size()<back.size()){
            return forward;
        }
        else {
            if(countSingleWord(forward)>countSingleWord(back)){
                return back;
            }
            else {
                return forward;
            }
        }
    }

    public static void evaluateSPeed(Map<String,CoreDictionary.Attribute> dictionary){
        String text = "王泽宇毕业于南京理工大学";
        long start;
        double costTime;
        final int pressure = 10000;

        start = System.currentTimeMillis();


        for(int i=0;i<pressure;i++){
            segmentForwardLongest(text,dictionary);
        }

        costTime = (System.currentTimeMillis() - start)/(double)1000;

        System.out.printf("%.2f万字/秒\n",text.length()*pressure/10000/costTime);
    }

    public static void evaluateSPeedByTrie(BinTrie binTrie){
        String text = "王泽宇毕业于南京理工大学";
        long start;
        double costTime;
        final int pressure = 10000;

        start = System.currentTimeMillis();


        for(int i=0;i<pressure;i++){
            segmentFullyByTire(text,binTrie);
        }

        costTime = (System.currentTimeMillis() - start)/(double)1000;

        System.out.printf("%.2f万字/秒\n",text.length()*pressure/10000/costTime);
    }

    public static void main(String[] args) throws IOException {
        TreeMap<String, CoreDictionary.Attribute> dictionary = IOUtil.loadDictionary("C:\\Users\\Administrator\\Desktop\\data\\dictionary\\CoreNatureDictionary.mini.txt");

//        System.out.printf("词典大小:%d个词条\n",dictionary.size());
//        System.out.println(dictionary.keySet().iterator().next());
        final BinTrie<CoreDictionary.Attribute> binTrie = new BinTrie<CoreDictionary.Attribute>(dictionary);
        Map<String, CoreDictionary.Attribute> binTrieMap = new Map<String, CoreDictionary.Attribute>() {
            public int size() {
                return 0;
            }

            public boolean isEmpty() {
                return false;
            }

            public boolean containsKey(Object key) {
                return false;
            }

            public boolean containsValue(Object value) {
                return false;
            }

            public CoreDictionary.Attribute get(Object key) {
                return null;
            }

            public CoreDictionary.Attribute put(String key, CoreDictionary.Attribute value) {
                return null;
            }

            public CoreDictionary.Attribute remove(Object key) {
                return null;
            }

            public void putAll(Map<? extends String, ? extends CoreDictionary.Attribute> m) {

            }

            public void clear() {

            }

            public Set<String> keySet() {
                return null;
            }

            public Collection<CoreDictionary.Attribute> values() {
                return null;
            }

            public Set<Entry<String, CoreDictionary.Attribute>> entrySet() {
                return null;
            }

            public boolean equals(Object o) {
                return false;
            }

            public int hashCode() {
                return 0;
            }
        };

        List<String> list = segmentBackLongest("王泽宇毕业于南京理工大学", dictionary);
        System.out.println(list.toString());

        evaluateSPeed(dictionary);
        evaluateSPeed(binTrieMap);
        evaluateSPeedByTrie(binTrie);



//        segmentFullyByTire("王泽宇毕业于南京理工大学",binTrie);

    }
}
