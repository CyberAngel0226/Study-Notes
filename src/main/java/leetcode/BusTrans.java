package leetcode;

import javafx.util.Pair;

import java.util.*;

public class BusTrans {
    // 公交班号和线路图
    Map<Integer, List<Integer>> BUS_FOR_ROUTES = new HashMap<>();

    // 站点连接记录
    Map<Integer, List<Integer>> TO_ARRIVE = new HashMap<>();

    // 记录走过站点
    Map<Integer, Boolean> VIS = new HashMap<>();

    // 记录站点间公交车班次
    Map<Pair<Integer, Integer>, Integer>BUS;

    void initRoutes(int[][] routes) {

        // 初始化公交车班号以及对应路径
        BUS_FOR_ROUTES.put(101, new ArrayList<>(Arrays.asList(1, 2, 4, 5, 8)));
        BUS_FOR_ROUTES.put(102, new ArrayList<>(Arrays.asList(9, 6, 10, 12)));
        BUS_FOR_ROUTES.put(103, new ArrayList<>(Arrays.asList(3, 4, 7, 9)));
        BUS = new HashMap<>();

        // 初始化路线
        for(List<Integer>list : BUS_FOR_ROUTES.values()) {
            for(int i=0; i<list.size(); i++) {
                List<Integer> arrive = TO_ARRIVE.getOrDefault(list.get(i), new ArrayList<>());
                boolean b = i == list.size() - 1 ? arrive.add(list.get(0)) : arrive.add(list.get(i + 1));
                TO_ARRIVE.put(list.get(i), arrive);
            }
        }

        for(Integer busId : BUS_FOR_ROUTES.keySet()) {
            List<Integer> list = BUS_FOR_ROUTES.get(busId);
            for(int i=0; i<list.size(); i++) {
                if(i == list.size() - 1) {
                    BUS.put(new Pair<>(list.get(i), list.get(0)), busId);
                }
                else {
                    BUS.put(new Pair<>(list.get(i), list.get(i+1)), busId);
                }
            }
        }
    }

    // 记录路径链路
    class Node {
        int station;
        List<Integer> route;

        Node(Integer station, List<Integer> route) {
            this.station = station;
            this.route = new ArrayList<>(route);
        }
    }

    // 广度优先搜索找到起始站点到目标站点的最短路径
    Node bfsRoute(int start, int end) {
        Node cur = new Node(start, new ArrayList<>());
        cur.route.add(start);
        // 搜索队列
        Deque<Node> q = new LinkedList<>();
        VIS.put(start, true);
        q.offer(cur);
        while(!q.isEmpty()) {
            cur = q.poll();
            if(cur.station == end) {
                return cur;
            }
            List<Integer> next_stations = TO_ARRIVE.get(cur.station);
            // 遍历可达站点
            for(int next_station : next_stations) {
                if(!VIS.containsKey(next_station)) {
                    Node next = new Node(next_station, cur.route);
                    next.route.add(next_station);
                    q.offer(next);
                    // 标记站点
                    VIS.put(next_station, true);
                }
            }
        }
        // 没有找到有效路径，返回 -1
        return new Node(-1, new ArrayList<>());
    }

    // 根据搜索到的最短路径寻找换乘公交车班次
    List<Integer> findTranBus(List<Integer> route) {
        List<Integer> buses = new ArrayList<>();
        buses.add(BUS.get(new Pair<>(route.get(0), route.get(1))));
        for(int i=1; i<route.size()-1; i++) {
            if(!Objects.equals(buses.get(buses.size() - 1), BUS.get(new Pair<>(route.get(i), route.get(i + 1))))) {
                buses.add(BUS.get(new Pair<>(route.get(i), route.get(i+1))));
            }
        }
        return buses;
    }


    public static void main(String[] args) {
        BusTrans bts = new BusTrans();
        bts.initRoutes(new int[1][1]);
        Node route = bts.bfsRoute(1,12);
        // 输出最短路径
        System.out.println(route.route);
        List<Integer> buses = bts.findTranBus(route.route);
        // 输出换乘班次
        System.out.println(buses);
    }
}
