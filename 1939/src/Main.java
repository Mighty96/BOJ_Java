import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair implements Comparable<Pair>{
    int end;
    int weight;

    Pair(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public int compareTo(Pair p) {
        if (this.weight > p.weight) return -1;
        return 1;
    }
}

public class Main {

    static int n, m;
    static int start, goal;
    static int[] cost;
    static ArrayList<ArrayList<Pair>> road = new ArrayList<>();
    static PriorityQueue<Pair> que = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new int[n + 1];

        for (int i = 0 ; i <= n ; i++) {
            road.add(new ArrayList<>());
        }

        int temp1, temp2, temp3;
        for (int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine());

            temp1 = Integer.parseInt(st.nextToken());
            temp2 = Integer.parseInt(st.nextToken());
            temp3 = Integer.parseInt(st.nextToken());

            road.get(temp1).add(new Pair(temp2, temp3));
            road.get(temp2).add(new Pair(temp1, temp3));
        }
        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < road.get(start).size() ; i++) {
            cost[road.get(start).get(i).end] = road.get(start).get(i).weight;
            que.add(road.get(start).get(i));
        }

        while(!que.isEmpty()) {
            Pair now = que.poll();

            if (now.end == goal) {
                System.out.println(now.weight);
                break;
            }

            for (int i = 0 ; i < road.get(now.end).size() ; i++) {
                if (cost[road.get(now.end).get(i).end] < Math.min(now.weight, road.get(now.end).get(i).weight)) {
                    cost[road.get(now.end).get(i).end] = Math.min(now.weight, road.get(now.end).get(i).weight);
                    que.add(new Pair(road.get(now.end).get(i).end, cost[road.get(now.end).get(i).end]));
                }
            }
        }

    }
}
