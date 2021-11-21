import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int a;
    static int b;
    static int[] input;
    static int[] tree;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        input = new int[a + 1];
        tree = new int[4 * (a + 1)];
        answer = new int[b + 1];

        for (int i = 1 ; i <= a ; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        make_tree(1, a, 1);

        int front, back;
        for (int i = 1 ; i <= b ; i++) {
            st = new StringTokenizer(br.readLine());
            front = Integer.parseInt(st.nextToken());
            back = Integer.parseInt(st.nextToken());
            answer[i] = find_tree(1, a, front, back, 1);
        }

        for (int i = 1 ; i <= b ; i++) {
            System.out.println(answer[i]);
        }

    }

    static int make_tree(int start, int end, int node) {
        if (start == end) {
            tree[node] = input[start];
            return tree[node];
        }

        int mid = (start + end) / 2;

        return tree[node] = Math.min(make_tree(start, mid, node * 2), make_tree(mid + 1, end, node * 2 + 1));
    }

    static int find_tree(int start, int end, int front, int back, int node) {
        if (start > back || end < front)
            return Integer.MAX_VALUE;

        if (start >= front && end <= back) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return Math.min(find_tree(start, mid, front, back, node * 2), find_tree(mid + 1, end, front, back, node * 2 + 1));
    }
}
