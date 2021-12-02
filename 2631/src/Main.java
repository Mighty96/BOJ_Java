import java.io.*;

public class Main {

    static int[] input;
    static int[] d;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        input = new int[n + 1];
        d = new int[n + 1];

        for (int i = 1 ; i <= n ; i++) {
             input[i] = Integer.parseInt(br.readLine());
        }

        int maxIdx = 0;

        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= n ; j++) {
                if (d[j] == 0) {
                    d[j] = input[i];
                    maxIdx = j;
                    break;
                } else if (d[j] > input[i]) {
                    d[j] = input[i];
                    break;
                }
            }
        }

        bw.write(String.valueOf(n - maxIdx));
        bw.flush();
        bw.close();
        br.close();
    }
}