/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.kingder.utils.graph;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-3-12
 * Time: ÏÂÎç4:25
 * To change this template use File | Settings | File Templates.
 */
public class GraphUtils {



    public static int[][] buildGraph(int vertexCount, int[] from, int[] to ,boolean isDirected ) {
        int edgeCount = from.length;
        int[] degree = new int[vertexCount];
        for (int i = 0; i < edgeCount; i++) {
            degree[from[i]]++;
            if(!isDirected)degree[to[i]]++;
        }
        int[][] graph = new int[vertexCount][];
        for (int i = 0; i < vertexCount; i++) {
            graph[i] = new int[degree[i]];
        }
        for (int i = 0; i < edgeCount; i++) {
            graph[from[i]][--degree[from[i]]] = i;
            if(!isDirected)graph[to[i]][--degree[to[i]]] = i;
        }
        return graph;
    }

    public static int otherVertex(int vertex, int from, int to) {
        return from + to - vertex;
    }

    public static int[][] buildSimpleGraph(int vertexCount, int[] from, int[] to,boolean isDirected) {
        int[][] graph = buildGraph(vertexCount, from, to,isDirected);
        simplifyGraph(graph, from, to);
        return graph;
    }
    private static void simplifyGraph(int[][] graph, int[] from, int[] to) {
        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                graph[i][j] = otherVertex(i, from[graph[i][j]], to[graph[i][j]]);
    }
}
