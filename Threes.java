public class Threes {
    private static final int NUM_REQUIRED_SETS = 70;

    public static void main(String[] args) {
        task1();
        System.out.println();
        task2();
    }


    /***
     * We are looking for sets of positive integers that satasfy the following conditions:
     * • x² + y² = 1 + z³
     * • z < x < y
     * • x, y and z are co-prime.
     */
    public static void task1() {
        long num_found_sets = 0;

        long x = 0;
        while (num_found_sets < NUM_REQUIRED_SETS) {
            long z = 1;
            while (z < x && num_found_sets < NUM_REQUIRED_SETS) {
                long ySquared = z*z*z - x*x + 1;
                long y = perfectSquare(ySquared);
                if (y > 0) {
                    if (x < y && areCoprime(z, x, y)) {
                        num_found_sets++;
                        System.out.println(num_found_sets+" "+x+" "+y+" "+z);
                    }
                }
                z++;
            }
            x++;
        }
    }

    public static void task2() {
        /**
         *We are looking for sets of positive integers that satasfy the following conditions:
         * • x² + y² = 1 + z³
         * • z < x < y
         * • x, y and z are co-prime.
         *
         * We know x < y 
         * So,     x² < y²
         *
         * and that x² + y² = 1 + z³
         * So,      y² = z³ + 1 - x²
         *
         * By substitution:  x² < z³ + 1 - x² 
         *                = x² < (z³ + 1)/2 <-
         *                = x  < √((z³ + 1)÷2)    
         */
        long num_found_sets = 0;
        long z = 1;
        while (num_found_sets < NUM_REQUIRED_SETS) {
            long x = 1;
            while (x < Math.sqrt((z*z*z + 1)/2) && num_found_sets < NUM_REQUIRED_SETS) {
                // Need to make sure y² is a perfect square before taking the root
                long ySquared = z*z*z - x*x + 1;
                long y = perfectSquare(ySquared);
                if (y > 0) {
                    if (z < x && x < y && areCoprime(z, x, y)) {
                        num_found_sets++;
                        System.out.println(num_found_sets+" "+x+" "+y+" "+z);
                    }
                }
                x++;
            }
            z++;
        }
    }


    //If a number is a perfect square, return it's square root.
    //Otherwise returns an int < 0
    public static long perfectSquare(long num) {
        String intString = Long.toString(num);
        char lastChar = intString.charAt(intString.length() - 1);
        String possLastVals = "014569";
        if (possLastVals.indexOf(lastChar) < 1) {
            return -1;
        }

        // Negative number cannot be perfect square
        if (num < 0) return -1;

        long root = (long) Math.sqrt(num);
        if (root*root == num) return root;

        return -1;
    }

    /**
     * Returns true if a, b, c are all coprime
     */
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