package itisprojectfirstcourse.aisd;

import itisprojectfirstcourse.javarush.JUnit.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RedudantConnection {
    int[] parent = new int[1001];

    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        int answer[] = new int[2];
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (findSet(a) == findSet(b)) {
                answer[0] = a;
                answer[1] = b;
                break;
            } else {
                unionSets(a, b);
            }
        }
        return answer;
    }

    public int findSet(int v) {
        if (v == parent[v]) return v;
        return parent[v] = findSet(parent[v]);
    }

    public void unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a == b) return;
        parent[b] = a;
    }

    public static void main(String[] args) {
        RedudantConnection rc = new RedudantConnection();
        int[][] edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] answer = rc.findRedundantConnection(edges);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}
