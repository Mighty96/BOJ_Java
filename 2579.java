import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr = new int[301];
    static int[] jump_1 = new int[301];
    static int[] jump_2 = new int[301];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        jump_1[1] = arr[1];
        jump_2[1] = 0;
        jump_1[2] = jump_1[1] + arr[2];
        jump_2[2] = arr[2];
        if (n==1) System.out.println(jump_1[1]);
        else if (n==2) System.out.println(jump_1[2]);
        else {
            for(int i=3;i<=n;i++) {
                jump_1[i] = jump_2[i - 1] + arr[i];
                jump_2[i] = (jump_1[i - 2] > jump_2[i - 2]) ? jump_1[i - 2] + arr[i] : jump_2[i - 2] + arr[i] ;
            }
            System.out.println((jump_1[n] > jump_2[n]) ? jump_1[n] : jump_2[n]);
        }
    }

}