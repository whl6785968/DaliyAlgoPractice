package ReinforcementLearning;

import java.util.HashMap;
import java.util.Map;

public class Tset {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("a",127);
        map.put("b",new Integer("127"));
        map.put(null,null);
        System.out.println(map.get("a") == map.get("b"));
    }
}
