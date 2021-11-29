import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] answer;
    static int[] sum;
    static int m;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        answer = new int[m][m];
        sum = new int[m * 2];

        int d0, d1, d2;

        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            d0 = Integer.parseInt(st.nextToken());
            d1 = Integer.parseInt(st.nextToken());
            d2 = Integer.parseInt(st.nextToken());

            for (int j = d0 ; j < d0 + d1 ; j++) {
                sum[j] += 1;
            }
            for (int j = d0 + d1 ; j < d0 + d1 + d2 ; j++) {
                sum[j] += 2;
            }
        }

        for (int i = 0 ; i < m ; i++) {
            answer[m - i - 1][0] = sum[i];
        }
        for (int i = 1 ; i < m ; i++) {
            for (int j = 0 ; j < m ; j++) {
                answer[j][i] = sum[i + m - 1];
            }
        }

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < m - 1 ; j++) {
                bw.write((answer[i][j] + 1) + " ");
            }
            bw.write(String.valueOf(answer[i][m - 1] + 1));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}