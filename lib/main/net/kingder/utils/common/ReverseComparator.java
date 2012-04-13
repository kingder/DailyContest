package net.kingder.utils.common;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-2-24
 * Time: обнГ5:25
 * To change this template use File | Settings | File Templates.
 */
class ReverseComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T o1, T o2) {
        return o2.compareTo(o1);
    }
}