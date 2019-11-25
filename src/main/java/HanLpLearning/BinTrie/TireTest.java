package HanLpLearning.BinTrie;

import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;

import java.util.Map;
import java.util.Set;

public class TireTest {

    public static void prefixSearchTest(){
        BinTrie<String> binTrie = new BinTrie<String>();
        binTrie.put("see","动词");
        binTrie.put("seed","名词");
        binTrie.put("seek","动词");
        binTrie.put("seem","动词");

        boolean see = binTrie.containsKey("see");
        System.out.println(see);

        Set<Map.Entry<String, String>> entrySets = binTrie.prefixSearch("see");
        for(Map.Entry<String, String> entrySet:entrySets){
            System.out.println(entrySet.getKey() + ":" +entrySet.getValue());
        }
    }

    public static void binTrieImitateTest(){
        BinTrieImitate<String> binTrieImitate = new BinTrieImitate<String>();
        binTrieImitate.put("see","动词");
        binTrieImitate.put("seed","名词");
        binTrieImitate.put("seek","动词");
        binTrieImitate.put("seem","动词");

        boolean see = binTrieImitate.containsKey("see");
        System.out.println(see);
        Object value = binTrieImitate.get("see");
        System.out.println(value);

        Set<Map.Entry<String, String>> entrySet = binTrieImitate.prefixSearch("see");

        for(Map.Entry<String, String> entry : entrySet){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    public static void main(String[] args) {
//        prefixSearchTest();
        binTrieImitateTest();
    }

}
