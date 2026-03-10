https://www.naukri.com/code360/problems/number-of-islands-ii_1266048?leftPanelTabValue=SUBMISSION


import java.util.*;

public class Solution {

    static class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int findParent(int node) {
            if (node == parent[node]) {
                return node;
            }
            return parent[node] = findParent(parent[node]); // path compression
        }

        void unionByRank(int u, int v) {
            int pu = findParent(u);
            int pv = findParent(v);

            if (pu == pv) return;

            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } 
            else if (rank[pv] < rank[pu]) {
                parent[pv] = pu;
            } 
            else {
                parent[pv] = pu;
                rank[pu]++;
            }
        }
    }

    public static int[] numOfIslandsII(int n, int m, int[][] q) {

        int[][] dirs = {
            {-1,0},{1,0},
            {0,-1},{0,1},
            // {-1,-1},{-1,1},
            // {1,-1},{1,1}
        };

        boolean[][] vis = new boolean[n][m];

        DisjointSet ds = new DisjointSet(n * m);

        int[] ans = new int[q.length];
        int count = 0;

        for (int i = 0; i < q.length; i++) {

            int row = q[i][0];
            int col = q[i][1];

            if (vis[row][col]) {
                ans[i] = count;
                continue;
            }

            vis[row][col] = true;
            count++;

            int nodeNo = row * m + col;

            for (int[] dir : dirs) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && vis[newRow][newCol]) {

                    int adjNode = newRow * m + newCol;

                    if (ds.findParent(nodeNo) != ds.findParent(adjNode)) {
                        ds.unionByRank(nodeNo, adjNode);
                        count--;
                    }
                }
            }

            ans[i] = count;
        }

        return ans;
    }
}
