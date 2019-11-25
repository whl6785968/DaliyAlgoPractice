package ReinforcementLearning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Q_Learning {
    public static double[][] Q = {{0.0,0.0,0.0,0.0,0.0,0.0},
            {0.0,0.0,0.0,0.0,0.0,0.0},
            {0.0,0.0,0.0,0.0,0.0,0.0},
            {0.0,0.0,0.0,0.0,0.0,0.0},
            {0.0,0.0,0.0,0.0,0.0,0.0},
            {0.0,0.0,0.0,0.0,0.0,0.0}
    };

    public static double r = 0.8;
//    public static int number = 3;
    public static Random random = new Random();

    public static double[][] R = {
            {-1.0,-1.0,-1.0,-1.0,0.0,-1.0},
            {-1.0,-1.0,-1.0,0.0,-1.0,100.0},
            {-1.0,-1.0,-1.0,0.0,-1.0,-1.0},
            {-1.0,0.0,0.0,-1.0,0.0,-1.0},
            {0.0,-1.0,-1.0,0.0,-1.0,100.0},
            {-1.0,0.0,-1.0,-1.0,0.0,100.0}
    };

    public static void transition(int number){
        while(number != 5){
            List<Integer> list = new ArrayList<Integer>();
            for (int i=0;i<R[number].length;i++){
                if(R[number][i] != -1){
                    list.add(i);
                }
            }

//            for (int i:list){
//                System.out.println(i);
//            }

            int to = list.get(random.nextInt(list.size()));
//            System.out.println("from:" + number + ":" +"to" + to);
            getQ(number, to);
            number = to;
        }
        for(int i=0;i<Q.length;i++){
            for(int j=0;j<Q[i].length;j++){
                System.out.print(Q[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void updateQ(int from,int to){

    }

    public static double getQ(int from,int to){
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<R[to].length;i++){
            if(R[to][i] != -1.0){
                list.add(i);
            }
        }

        double max = getMax(to, list);
//        System.out.println("max is " + max);
        double Q_value = R[from][to] + r*max;
//        System.out.println("Q_value is " + Q_value);
        Q[from][to] = Q_value;
        return Q_value;
    }

    public static double getMax(int to,List<Integer> list){
        double max = 0.0;

        for (Integer i : list){
            if(Q[to][i]>max){
                max = Q[to][i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        for(int i=0;i<500;i++) {
            int number = random.nextInt(100)%6;
            System.out.println("===第"+i+"次学习的初始房间是===:"+number);
//            int number = 1;
            transition(number);
        }
    }
}
