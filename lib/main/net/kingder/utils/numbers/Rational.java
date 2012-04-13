package net.kingder.utils.numbers;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-2-24
 * Time: обнГ5:25
 * To change this template use File | Settings | File Templates.
 */
class Rational implements Comparable<Rational> {

    public final long numerator;
    public final long denominator;

    public Rational(long numerator, long denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException();
        long gcd = IntegerUtils.gcd(Math.abs(numerator), Math.abs(denominator));
        if (denominator > 0) {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        } else {
            this.numerator = -numerator / gcd;
            this.denominator = -denominator / gcd;
        }
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public int compareTo(Rational other) {
        return IntegerUtils.longCompare(numerator * other.denominator, denominator * other.numerator);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return denominator == rational.denominator && numerator == rational.numerator;
    }

    public int hashCode() {
        int result = (int) (numerator ^ (numerator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

}