import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static long result;
    static long[][] arr = new long[1001][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr[1][0] = 1;
        arr[1][1] = 0;
        arr[1][2] = 0;
        arr[2][0] = 1;
        arr[2][1] = 1;
        arr[2][2] = 1;
        if(n==1) System.out.println(1);
        else if(n==2) System.out.println(3);
        else{
            for(int i = 3; i<=n; i++){
                arr[i][0] = arr[i - 1][0] + arr[i - 1][1] + arr[i - 1][2];
                arr[i][1] = arr[i - 2][0] + arr[i - 2][1] + arr[i - 2][2];
                arr[i][2] = arr[i - 2][0] + arr[i - 2][1] + arr[i - 2][2];
                arr[i][0] %= 10007;
                arr[i][1] %= 10007;
                arr[i][2] %= 10007;
            }
            result = (arr[n][0] + arr[n][1] + arr[n][2]) % 10007;
            System.out.println(result);
        }

    }

}
