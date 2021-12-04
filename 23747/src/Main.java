import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int y;
    int x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static int r, c;
    static String[] field;
    static char[][] on;
    static int y, x;
    static String command;
    static int[] d_y = {0, 0, 1, -1};
    static int[] d_x = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        field = new String[r];
        on = new char[r][c];

        for (int i = 0 ; i < r ; i++) {
            Arrays.fill(on[i], '#');
        }

        for (int i = 0 ; i < r ; i++) {
            field[i] = br.readLine();
        }

        st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken()) - 1;
        x = Integer.parseInt(st.nextToken()) - 1;

        command = br.readLine();

        for (int i = 0 ; i < command.length() ; i++) {
            if (command.charAt(i) == 'L')
                x -= 1;
            else if (command.charAt(i) == 'D')
                y += 1;
            else if (command.charAt(i) == 'R')
                x += 1;
            else if (command.charAt(i) == 'U')
                y -= 1;
            else if (command.charAt(i) == 'W') {
                bfs(y, x);
            }
        }

        on[y][x] = '.';
        for (int i = 0 ; i < 4 ; i++) {
            if (y + d_y[i] >= 0 && y + d_y[i] < r &&
            x + d_x[i] >= 0 && x + d_x[i] < c) {
                on[y + d_y[i]][x + d_x[i]] = '.';
            }
        }

        for (int i = 0 ; i < r ; i++) {
            for (int j = 0 ; j < c ; j++) {
                bw.write(String.valueOf(on[i][j]));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    static void bfs(int initY, int initX) {
        Queue<Point> que = new LinkedList<>();

        que.add(new Point(initY, initX));
        on[initY][initX] = '.';

        while (!que.isEmpty()) {
            Point now = que.poll();
            for (int i = 0 ; i < 4 ; i++) {
                if (now.y + d_y[i] >= 0 && now.y + d_y[i] < r &&
                now.x + d_x[i] >= 0 && now.x + d_x[i] < c &&
                on[now.y + d_y[i]][now.x + d_x[i]] == '#' &&
                field[now.y].charAt(now.x) == field[now.y + d_y[i]].charAt(now.x + d_x[i])) {
                    on[now.y + d_y[i]][now.x + d_x[i]] = '.';
                    que.add(new Point(now.y + d_y[i], now.x + d_x[i]));
                }
            }

        }
    }
}
