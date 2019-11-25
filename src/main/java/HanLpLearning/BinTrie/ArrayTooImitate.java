package HanLpLearning.BinTrie;

public class ArrayTooImitate {
    public static int binarySearch(BaseNodeImitate[] branched,BaseNodeImitate node){
        int high = branched.length - 1;
        if(branched.length < 1){
            return high;
        }

        int low = 0;

        while (low<=high){
            int mid = (low + high) >>> 1;
            int  cmp = branched[mid].compareTo(node);

            if(cmp > 0){
                high = mid - 1;
            }
            else if(cmp < 0){
                low = mid + 1;
            }
            else {
                return mid;
            }
        }

        return -(low + 1);
    }

    public static int binarySearch(BaseNodeImitate[] branches, char node)
    {
        int high = branches.length - 1;
        if (branches.length < 1)
        {
            return high;
        }
        int low = 0;
        while (low <= high)
        {
            int mid = (low + high) >>> 1;
            int cmp = branches[mid].compareTo(node);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1);
    }
}
