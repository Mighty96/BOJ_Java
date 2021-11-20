import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] item;
    static int[][] road;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        item = new int[n + 1];
        road = new int[n + 1][n + 1];

        for (int i = 1 ; i <= n ; i++) {
            Arrays.fill(road[i], 987654321);
        }

        for (int i = 1 ; i <= n ; i++) {
            item[i] = Integer.parseInt(st.nextToken());
            road[i][i] = 0;
        }



        int temp1, temp2, temp3;
        for (int i = 1 ; i <= r ; i++) {
            st = new StringTokenizer(br.readLine());
            temp1 = Integer.parseInt(st.nextToken());
            temp2 = Integer.parseInt(st.nextToken());
            temp3 = Integer.parseInt(st.nextToken());
            road[temp1][temp2] = temp3;
            road[temp2][temp1] = temp3;
        }

        for (int k = 1 ; k <= n ; k++) {
            for (int i = 1 ; i <= n ; i++) {
                for (int j = 1 ; j <= n ; j++) {
                    if (road[i][j] > road[i][k] + road[k][j]) {
                        road[i][j] = road[i][k] + road[k][j];
                    }
                }
            }
        }

        for (int i = 1 ; i <= n ; i++) {
            int cnt = 0;

            for (int j = 1 ; j <= n ; j++) {
                if (road[i][j] <= m) {
                    cnt += item[j];
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
        br.close();
    }
}

