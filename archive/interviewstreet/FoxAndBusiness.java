/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;


import java.util.Arrays;

public class FoxAndBusiness {
    private double eps = 1e-10;

    public double minimumCost(int K, int totalWork, int[] a, int[] p) {

        double lo = 0, hi = 1e30;
        for (int i = 0; lo < hi && i < 200; i++) {
            double md = (lo + hi) / 2;

            double[] A = new double[a.length];
            for (int j = 0; j < a.length; j++) {
                A[j] = (double) totalWork * (3600 + 1.0 * a[j] * p[j]) - md * a[j];
            }
            Arrays.sort(A);

            double sum = 0;
            for (int j = 0; j < K; j++) sum += A[j];
            if (sum <= 0) hi = md;
            else lo = md;
        }
        return (lo + hi) / 2;
    }
}

