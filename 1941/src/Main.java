import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int y;
    int x;

    Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static String[] student = new String[5];
    static boolean[][] vis = new boolean[5][5];
    static Queue<Pair> que = new LinkedList<>();
    static int[] d_y = {-1, 1, 0, 0};
    static int[] d_x = {0, 0, 1, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0 ; i < 5 ; i++) {
            student[i] = br.readLine();
        }

        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 0 ; j < 5 ; j++) {
                vis[i][j] = true;
                if (student[i].charAt(j) == 'Y')
                    dfs(i, j, 0, 1);
                else
                    dfs(i, j, 1, 0);
                vis[i][j] = false;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int y, int x, int cntS, int cntY) {

        if (cntS + cntY == 7) {
            if (cntS > cntY && check(y, x)) {
                answer++;
            }
            return;
        }


        for (int i = y ; i < 5 ; i++) {
            for (int j = 0 ; j < 5 ; j++) {
                if (i == y && j <= x)
                    continue;
                vis[i][j] = true;
                 if (student[i].charAt(j) == 'S')
                     dfs(i, j, cntS + 1, cntY);
                 else
                     dfs(i, j, cntS, cntY + 1);
                 vis[i][j] = false;
            }
        }

    }

    static boolean check(int y, int x) {
        boolean[][] temp_vis = new boolean[5][5];

        que.add(new Pair(y, x));
        temp_vis[y][x] = true;
        int cnt = 1;

        while (!que.isEmpty()) {
            Pair now = que.poll();

            for (int i = 0 ; i < 4 ; i++) {
                if (now.y + d_y[i] >= 0 && now.y + d_y[i] < 5 &&
                now.x + d_x[i] >= 0 && now.x + d_x[i] < 5 &&
                        vis[now.y + d_y[i]][now.x + d_x[i]] &&
                !temp_vis[now.y + d_y[i]][now.x + d_x[i]]) {
                    temp_vis[now.y + d_y[i]][now.x + d_x[i]] = true;
                    que.add(new Pair(now.y + d_y[i], now.x + d_x[i]));
                    cnt++;
                }
            }
        }

        return (cnt == 7);
    }
}
