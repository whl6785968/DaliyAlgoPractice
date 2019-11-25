package LeetCode;

public class SumOfTwoNumber {
    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int x){
            this.val = x;
        }
    }

    public static ListNode cleverSolution(ListNode l1,ListNode l2){
        ListNode pre = new ListNode(0);
        ListNode curr = pre;
        int carry = 0;
        while (l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            curr.next = new ListNode(sum);

            curr = curr.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }

        if(carry == 1){
            curr.next = new ListNode(carry);
        }

        return pre.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean isCarry = false;
        ListNode last = null;
        ListNode first = null;

        for(ListNode curr = l1;curr != null;curr = curr.next ){
            int tmp = 0;
            if(l2 == null){
                tmp = curr.val;
            }
            else {
                tmp = curr.val + l2.val;
                l2 = l2.next;
            }
            if(isCarry){
                tmp += 1;
            }

            if(tmp>=10){
                int gewei = 0;
                gewei = tmp % 10;
                isCarry = true;
                ListNode oldLast = last;
                last = new ListNode(gewei);
                if(oldLast == null){
                    first = last;
                }
                else {
                    oldLast.next = last;
                }
            }
            else{
                ListNode oldLast = last;
                last = new ListNode(tmp);
                if(oldLast == null){
                    first = last;
                }
                else {
                    oldLast.next = last;
                }
                isCarry = false;
            }
        }
        if(l2 != null){
            for(ListNode curr = l2;curr != null;curr = curr.next ){
                int tmp = curr.val;
                if(isCarry){
                    tmp += 1;
                }
                if(tmp>=10){
                    int gewei = 0;
                    gewei = tmp % 10;
                    isCarry = true;
                    ListNode oldLast = last;
                    last = new ListNode(gewei);
                    if(oldLast == null){
                        first = last;
                    }
                    else {
                        oldLast.next = last;
                    }
                }
                else{
                    ListNode oldLast = last;
                    last = new ListNode(tmp);
                    if(oldLast == null){
                        first = last;
                    }
                    else {
                        oldLast.next = last;
                    }
                    isCarry = false;
                }
            }
        }
        if(isCarry == true)
        {
            ListNode oldLast = last;
            last = new ListNode(1);
            if(oldLast == null){
                first = last;
            }
            else {
                oldLast.next = last;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(2);

//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//
        l2.next = new ListNode(7);
        l2.next.next = new ListNode(8);

        ListNode listNode = addTwoNumbers(l1, l2);
//        System.out.println(listNode.next.next.val);

    }
}
