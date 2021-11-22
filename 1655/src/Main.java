import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<Integer> sq = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> bq = new PriorityQueue<>();
    static int n;
    static int[] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        answer = new int[n];

        int num;
        for (int i = 0 ; i < n ; i++) {
            num = Integer.parseInt(br.readLine());
            if (!bq.isEmpty() && num >= bq.peek()) {
                bq.add(num);
            } else {
                sq.add(num);
            }

            if (sq.size() >= bq.size() + 2) {
                bq.add(sq.poll());
            } else if (bq.size() >= sq.size() + 2) {
                sq.add(bq.poll());
            }

            if (bq.size() > sq.size()) {
                answer[i] = bq.peek();
            } else {
                answer[i] = sq.peek();
            }
        }

        for (int i = 0 ; i < n ; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
