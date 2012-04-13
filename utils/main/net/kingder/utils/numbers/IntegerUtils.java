package net.kingder.utils.numbers;

import net.kingder.utils.common.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lwc626
 */
public class IntegerUtils {

    public static long gcd(long a, long b) {
        while (b != 0) {
            a = a % b;
            long t = a ; a = b ; b = t;
        }
        return a;
    }
    public static List<Pair<Integer, Integer>> factorize(int number) {
        List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                int power = 0;
                do {
                    power++;
                    number /= i;
                } while (number % i == 0);
                result.add(Pair.makePair(i, power));
            }
        }
        if (number != 1) result.add(Pair.makePair(number, 1));
        return result;

    }

    public static int longCompare(long a, long b) {
        if (a < b)
            return -1;
        if (a > b)
            return 1;
        return 0;
    }

    public static ArrayList<Integer> generatePrime(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("The parameter must be a positive integer!");
        boolean[] flag = new boolean[N + 1];
        ArrayList<Integer> Primes = new ArrayList<Integer>();
        Arrays.fill(flag, false);
        for (int i = 2; i <= N; i++)
            if (!flag[i]) {
                for (int j = i * i; j <= N; j += i) flag[j] = false;
                Primes.add(i);
            }
        return Primes;
    }

    public static ArrayList<Integer> getDivisors(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("The parameter must be a positive integer!");
        ArrayList<Integer> Prime = generatePrime((int) Math.sqrt(N + 1.0));
        ArrayList<Integer> Divisors = new ArrayList<Integer>();
        for (int i = 0; i < Prime.size() && N != 1; i++) {
            if (N % Prime.get(i) == 0) {
                while (N % Prime.get(i) == 0) {
                    Divisors.add(Prime.get(i));
                }
            }
        }
        return Divisors;
    }


}
