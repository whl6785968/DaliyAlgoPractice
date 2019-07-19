package chapter1;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//(1 + (2 + 3)*(4 * 5))

/***
 * 规则:
 *  1.碰到左括号不处理
 *  2.碰到运算符或者数字压入栈
 *  3.碰到右括号取出运算符栈顶的运算符和取出数字栈中倒数第一和第二个值，根据运算
 *  符计算，并将新值压入数字栈中
 *
 *  如:(1 + (2 + 3)*(4 * 5))
 *  从左到右代表栈顶到栈底
 *  1. opts(+,+) vals(3,2,1)  encounter )
 *  2. opts.pop() => +, vals.pop() => 3+2=5  vals.push(5
 *  3. current: opts(+) vals(5,1)
 *  4. recycle this process until last )
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> opts = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("(")) ;
            else if(s.equals("+")) opts.push(s);
            else if(s.equals("-")) opts.push(s);
            else if(s.equals("*")) opts.push(s);
            else if(s.equals(")")){
                String opt = opts.pop();
                Double val = vals.pop();
                if(opt.equals("+")){
                    val = val + vals.pop();
                }
                else if(opt.equals("-")){
                    val = val - vals.pop();
                }
                else if(opt.equals("*")){
                    val = val * vals.pop();
                }
                vals.push(val);
            }
            else{
                vals.push(Double.parseDouble(s));
            }
        }
        StdOut.print(vals.pop());
    }
}
