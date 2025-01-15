import java.util.Scanner;

public class testing {
    public static void main(String[] args) {

        int result = sum_of_all_even_num(10);

    }

    static int sum_of_all_even_num(int a) {
        int sum = 0;
        for (int i = 1; i <= a; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
