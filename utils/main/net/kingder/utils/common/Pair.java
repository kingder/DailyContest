package net.kingder.utils.common;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-2-24
 * Time: ÏÂÎç5:04
 * To change this template use File | Settings | File Templates.
 */
public class Pair<U, V> implements Comparable<Pair<U, V>> {
    public final U first;
    public final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public static <U, V> Pair<U, V> makePair(U first, V second) {
        return new Pair<U, V>(first, second);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pair pair = (Pair) o;
        return !(first != null ? !first.equals(pair.first) : pair.first != null) &&
                !(second != null ? !second.equals(pair.second) : pair.second != null);
    }

    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        return 31 * result + (second != null ? second.hashCode() : 0);
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }
    @SuppressWarnings({"unchecked"})
    public int compareTo(Pair<U, V> o) {
        int value = ((Comparable<U>)first).compareTo(o.first);
        if (value != 0)
            return value;
        return ((Comparable<V>)second).compareTo(o.second);
    }

}
