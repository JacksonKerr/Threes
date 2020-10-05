import java.util.Scanner;

public class checkThrees {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] data = currLine.split(" ");

            if (data.length < 4) {
                continue;
            }

            long x = Long.parseLong(data[1]);
            long y = Long.parseLong(data[2]);
            long z = Long.parseLong(data[3]);
            
            long lhs = x*x + y*y;
            long rhs = z*z*z + 1;

            if (lhs != rhs || !areCoprime(z, x, y) || z >= x || x >= y) {
                System.out.println("Bad Line "+currLine);
            }
        }
        sc.close();
    }

    public static boolean areCoprime(long a, long b, long c) {
        if (gcd(a, b) > 1) return false;
        if (gcd(b, c) > 1) return false;
        if (gcd(c, a) > 1) return false;
        return true;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a;
            a = b;
            b = t % b;
        }
        return a;
    }
}