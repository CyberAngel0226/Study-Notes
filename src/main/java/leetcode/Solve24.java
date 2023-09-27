package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *  实现24点游戏：
 *  判断四个数字能否通过添加任意运算符和括号使其计算结果为24
 */

public class Solve24 {

    // 声明浮点数对比误差
    final double GAPS = 1e-6;

    // 声明计算结果
    final double RESULT = 24.0;

    // 声明运算符类型
    final char[] operators= new char[]{'+', '-', '*', '/'};

    // 声明运算组合链
    List<String> linked;

    // solve 类解决方案
    public boolean solve24(int[] cards) {
        List<Double> list = new ArrayList<>();
        // 初始化组合链
        linked = new ArrayList<>();
        // 由于涉及除法，将 int 转化为 double 进行计算
        for (int num : cards) {
            list.add((double) num);
        }
        return dfs(list);
    }

    // dfs 类通过深度优先搜索遍历寻找解决方案
    boolean dfs(List<Double> list) {
        // 深搜层数到最后一层判断是否为 24
        if(list.size() == 1 && Math.abs(list.get(0) - RESULT) < GAPS){
            return true;
        }

        // 双重 for 循环，每次取list中两个数字进行计算
        for(int i=0; i<list.size(); i++) {
            for(int j=0; j<list.size(); j++) {
                if(i != j) {
                    // 创建 newList 用于存储下一轮搜索的数字
                    List<Double> newList = new ArrayList<>();
                    // 将无需计算的数字直接插入 newList 中
                    for(int k=0; k<list.size(); k++) {
                        if(k != i && k != j) {
                            newList.add(list.get(k));
                        }
                    }
                    // 遍历运算符，计算两个数字组合的左右可能得到的值并加入 newList，同时记录运算链
                    for (char operator : operators) {
                        switch (operator) {
                            case '+':
                                linked.add(String.valueOf(list.get(i)));
                                linked.add(String.valueOf('+'));
                                linked.add(String.valueOf(list.get(j)));
                                newList.add(list.get(i) + list.get(j));
                                break;
                            case '-':
                                linked.add(String.valueOf(list.get(i)));
                                linked.add(String.valueOf('-'));
                                linked.add(String.valueOf(list.get(j)));
                                newList.add(list.get(i) - list.get(j));
                                break;
                            case '*':
                                linked.add(String.valueOf(list.get(i)));
                                linked.add(String.valueOf('*'));
                                linked.add(String.valueOf(list.get(j)));
                                newList.add(list.get(i) * list.get(j));
                                break;
                            case '/':
                                linked.add(String.valueOf(list.get(i)));
                                linked.add(String.valueOf('/'));
                                linked.add(String.valueOf(list.get(j)));
                                newList.add(list.get(i) / list.get(j));
                        }
                        // 进行下一轮深搜直到 list 中值仅剩下一个值
                        if(dfs(newList)) {
                            return true;
                        }
                        // 回溯，移除当前添加的运算结果以及运算链继续执行循环
                        newList.remove(newList.size() - 1);
                        linked.remove(linked.size() - 1);
                        linked.remove(linked.size() - 1);
                        linked.remove(linked.size() - 1);
                    }
                }
            }
        }
        // 未找到满足条件的运算方式且 list 仅剩下一个值直接返回 false
        return false;
    }

    // 测试方法
    public static void main(String[] args) {
        Solve24 solve = new Solve24();
        // 测试 1,3,4,6 的输出结果，其中 6 / (1 - 3 / 4) = 24，输出 true
        System.out.println(solve.solve24(new int[] {1,3,4,6}));
        // 输出运算链条
        System.out.println(solve.linked);
        // 测试 1,2,1,1 的输出结果，其无法构成 24，输出 false
        System.out.println(solve.solve24(new int[] {1,2,1,1}));
        // 输出运算链条
        System.out.println(solve.linked);
    }
}