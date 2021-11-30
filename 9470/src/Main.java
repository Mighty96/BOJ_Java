import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static int k, m, p;
    static ArrayList<Integer>[] road;
    static ArrayList<Integer>[] under;
    static int[] cnt;
    static int[] str;
    static Queue<Integer> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 1 ; t <= T ; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            road = new ArrayList[m + 1];
            under = new ArrayList[m + 1];

            for (int i = 0 ; i <= m ; i++) {
                road[i] = new ArrayList<>();
                under[i] = new ArrayList<>();
            }

            cnt = new int[m + 1];
            str = new int[m + 1];

            Arrays.fill(str, 1);

            int temp1, temp2;
            for (int i = 0 ; i < p ; i++) {
                st = new StringTokenizer(br.readLine());
                temp1 = Integer.parseInt(st.nextToken());
                temp2 = Integer.parseInt(st.nextToken());

                road[temp1].add(temp2);
                cnt[temp2]++;
            }

            for (int i = 1 ; i <= m ; i++) {
                if (cnt[i] == 0) {
                    que.add(i);
                }
            }

            int maxStr = 0;
            while (!que.isEmpty()) {
                int now = que.poll();

                int innerMaxStr = 1;
                boolean flag = false;
                if (!under[now].isEmpty()) {
                    for (int i = 0 ; i < under[now].size() ; i++) {
                        innerMaxStr = Math.max(innerMaxStr, under[now].get(i));
                    }
                    for (int i = 0 ; i < under[now].size() ; i++) {
                        if (innerMaxStr == under[now].get(i)) {
                            if (!flag) {
                                str[now] = innerMaxStr;
                                flag = true;
                            } else {
                                str[now] = innerMaxStr + 1;
                                break;
                            }
                        }
                    }
                }

                maxStr = Math.max(maxStr, str[now]);

                for (int i = 0 ; i < road[now].size() ; i++) {
                    cnt[road[now].get(i)]--;
                    under[road[now].get(i)].add(str[now]);

                    if (cnt[road[now].get(i)] == 0) {
                        que.add(road[now].get(i));
                    }
                }
            }

            bw.write(t + " " + maxStr);
            bw.write('\n');
        }

        bw.flush();
        bw.close();
        br.close();


    }
}
