package HanLpLearning.BinTrie;

public interface ITrieImitate<V> {
    boolean containsKey(String key);
    int size();
    V get(String key);
}
