import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair>{
    int node;
    int len;

    Pair(int node, int len) {
        this.node = node;
        this.len = len;
    }


    @Override
    public int compareTo(Pair p) {
        if (this.len > p.len)
            return 1;
        return -1;
    }
}

public class Main {

    static int T;
    static int n, d, c;
    static int a, b, s;
    static ArrayList<ArrayList<Pair>> road;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static int[] dij;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1 ; t <= T ; t++) {
            st = new StringTokenizer(br.readLine());
            road = new ArrayList<>();

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            for (int i = 0 ; i <= n ; i++) {
                road.add(new ArrayList<>());
            }


            for (int i = 1 ; i <= d ; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());

                road.get(b).add(new Pair(a, s));
            }

            dij = new int[n + 1];
            Arrays.fill(dij, INF);
            dij[c] = 0;

            for (int i = 0 ; i < road.get(c).size() ; i++) {
                pq.add(road.get(c).get(i));
                dij[road.get(c).get(i).node] = road.get(c).get(i).len;
            }

            while (!pq.isEmpty()) {
                Pair now = pq.poll();

                if (now.len > dij[now.node])
                    continue;

                for (int i = 0 ; i < road.get(now.node).size() ; i++) {
                    if (now.len + road.get(now.node).get(i).len < dij[road.get(now.node).get(i).node]) {
                        dij[road.get(now.node).get(i).node] = now.len + road.get(now.node).get(i).len;
                        pq.add(new Pair(road.get(now.node).get(i).node, dij[road.get(now.node).get(i).node]));
                    }
                }

            }

            int maxLen = 0;
            int cnt = 0;

            for (int i = 1 ; i <= n ;i++) {
                if (dij[i] != INF) {
                    maxLen = Math.max(maxLen, dij[i]);
                    cnt++;
                }
            }

            bw.write(cnt + " " + maxLen);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
