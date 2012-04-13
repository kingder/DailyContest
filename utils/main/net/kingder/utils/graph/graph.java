package net.kingder.utils.graph;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-2-21
 * Time: ÏÂÎç8:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class graph {


    public int vertexNumber;
    public int edgeNumber;

    public graph() {
    }

    public graph(int _vn, int _en) {
        vertexNumber = _vn;
        edgeNumber = _en;
    }
}
