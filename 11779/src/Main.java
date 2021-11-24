import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair>{
    int node;
    int cost;

    Pair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }


    @Override
    public int compareTo(Pair p) {
        if (this.cost > p.cost) {
            return 1;
        }
        return -1;
    }
}

public class Main {

    static int n, m;
    static int start, goal;
    static ArrayList<Pair>[] road;
    static int[] d;
    static final int INF = 987654321;
    static int answer;
    static boolean[] vis;
    static int[] prev;
    static Stack<Integer> route = new Stack<>();
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        d = new int[n + 1];
        vis = new boolean[n + 1];
        road = new ArrayList[n + 1];
        prev = new int[n + 1];

        Arrays.fill(d, INF);
        for (int i = 1 ; i <= n ; i++) {
            road[i] = new ArrayList<>();
        }


        int[] temp = new int[3];
        for (int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < 3 ; j++) {
                temp[j] = Integer.parseInt(st.nextToken());
            }

            road[temp[0]].add(new Pair(temp[1], temp[2]));
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        d[start] = 0;
        prev[start] = 0;

        for (int i = 0 ; i < road[start].size() ; i++) {
            if (d[road[start].get(i).node] > road[start].get(i).cost) {
                d[road[start].get(i).node] = road[start].get(i).cost;
                prev[road[start].get(i).node] = start;
                pq.add(road[start].get(i));
            }
        }

        while(!pq.isEmpty()) {
            Pair now = pq.poll();

            if (now.node == goal) {
                answer = now.cost;
                break;
            }

            if (now.cost > d[now.node])
                continue;

            for (int i = 0 ; i < road[now.node].size() ; i++) {
                if (now.cost + road[now.node].get(i).cost < d[road[now.node].get(i).node]) {
                    d[road[now.node].get(i).node] = now.cost + road[now.node].get(i).cost;
                    prev[road[now.node].get(i).node] = now.node;
                    pq.add(new Pair(road[now.node].get(i).node, d[road[now.node].get(i).node]));
                }
            }
        }

        int prevIdx = goal;

        while(prevIdx != 0) {
            route.add(prevIdx);
            prevIdx = prev[prevIdx];
        }


        bw.write(String.valueOf(answer) + '\n');
        int stSize = route.size();
        bw.write(String.valueOf(stSize) + '\n');

        for (int i = 0 ; i < stSize ; i++) {
            bw.write(route.pop() + " ");
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
