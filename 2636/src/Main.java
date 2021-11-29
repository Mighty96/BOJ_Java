import java.io.*;
import java.util.*;

class Pair {
    int y;
    int x;

    Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static int n;
    static int m;
    static int[][] field;
    static boolean flag;
    static boolean[][] vis;
    static int[] d_x = {1, -1, 0, 0};
    static int[] d_y = {0, 0, 1, -1};
    static int answer1 = 0;
    static int answer2 = 0;

    static Queue<Pair> que1 = new LinkedList<>();
    static Queue<Pair> que2 = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        field = new int[n + 2][m + 2];
        vis = new boolean[n + 2][m + 2];
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= m ; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(true) {
            for (int i = 0 ; i <= n + 1 ; i++) {
                Arrays.fill(vis[i], false);
            }
            answer1++;
            vis[0][0] = true;
            que1.add(new Pair(0, 0));
            answer2 = 0;
            while (!que1.isEmpty()) {
                Pair now = que1.poll();

                for (int i = 0 ; i < 4 ; i++) {
                    if (now.y + d_y[i] >= 0 && now.y + d_y[i] <= n + 1 &&
                            now.x + d_x[i] >= 0 && now.x + d_x[i] <= m + 1 &&
                            !vis[now.y + d_y[i]][now.x + d_x[i]]) {
                        vis[now.y + d_y[i]][now.x + d_x[i]] = true;
                        if (field[now.y + d_y[i]][now.x + d_x[i]] == 0) {
                            que1.add(new Pair(now.y + d_y[i], now.x + d_x[i]));
                        } else {
                            que2.add(new Pair(now.y + d_y[i], now.x + d_x[i]));
                            answer2++;
                        }
                    }
                }
            }

            while(!que2.isEmpty()) {
                Pair now = que2.poll();
                field[now.y][now.x] = 0;
            }

            flag = true;
            for (int i = 1 ; i <= n ; i++) {
                for (int j = 1 ; j <= m ; j++) {
                    if (field[i][j] == 1) {
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    break;
            }

            if (flag)
                break;

        }

        bw.write(answer1 + " " + answer2);
        bw.flush();
        bw.close();
        br.close();
    }
}
