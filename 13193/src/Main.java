import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair>{
    int node;
    int cnt;

    Pair(int node, int cnt) {
        this.node = node;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Pair p) {
        if (this.cnt > p.cnt)
            return 1;
        return -1;
    }
}


public class Main {

    static int n, k;
    static final int INF = 987654321;
    static Stack<Integer> answer = new Stack<>();
    static int[] prev = new int[200001];
    static int[] cnt = new int[200001];
    static Queue<Pair> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Arrays.fill(cnt, INF);

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        que.add(new Pair(n, 0));
        prev[n] = -1;
        cnt[n] = 0;

        while (!que.isEmpty()) {
            Pair now = que.poll();
            if (now.node == k)
                break;

            if (now.cnt > cnt[now.node])
                continue;

            if (now.node + 1 <= k && cnt[now.node + 1] > now.cnt + 1) {
                cnt[now.node + 1] = now.cnt + 1;
                prev[now.node + 1] = now.node;
                que.add(new Pair(now.node + 1, now.cnt + 1));
            }

            if (now.node - 1 >= 0 && cnt[now.node - 1] > now.cnt + 1) {
                cnt[now.node - 1] = now.cnt + 1;
                prev[now.node - 1] = now.node;
                que.add(new Pair(now.node - 1, now.cnt + 1));
            }

            if (now.node < k && cnt[now.node * 2] > now.cnt + 1) {
                cnt[now.node * 2] = now.cnt + 1;
                prev[now.node * 2] = now.node;
                que.add(new Pair(now.node * 2, now.cnt + 1));
            }
        }

        int idx = k;

        while(idx != -1) {
            answer.push(idx);
            idx = prev[idx];
        }

        bw.write(String.valueOf(cnt[k]));
        bw.newLine();
        while (!answer.empty()) {
            bw.write(answer.peek() + " ");
            answer.pop();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
