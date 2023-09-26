package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *  实现24点游戏：
 *  四个数字，通过添加任意符号使其计算结果为24即可
 */

public class Solution_679 {

    final double GAPS = 1e-6;

    final double RESULT = 24.0;

    final char[] operators= new char[]{'+', '-', '*', '/'};

    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int num : cards) {
            list.add((double) num);
        }
        return dfs(list);
    }

    boolean dfs(List<Double> list) {
        if(list.size() == 1 && Math.abs(list.get(0) - RESULT) < GAPS){
            return true;
        }

        for(int i=0; i<list.size(); i++) {
            for(int j=0; j<list.size(); j++) {
                if(i != j) {
                    List<Double> newList = new ArrayList<>();
                    for(int k=0; k<list.size(); k++) {
                        if(k != i && k != j) {
                            newList.add(list.get(k));
                        }
                    }
                    for (char operator : operators) {
                        switch (operator) {
                            case '+':
                                newList.add(list.get(i) + list.get(j));
                                break;
                            case '-':
                                newList.add(list.get(i) - list.get(j));
                                break;
                            case '*':
                                newList.add(list.get(i) * list.get(j));
                                break;
                            case '/':
                                newList.add(list.get(i) / list.get(j));
                        }
                        if(dfs(newList)) {
                            return true;
                        }
                        newList.remove(newList.size() - 1);
                    }
                }
            }
        }
        return false;
     }
}
