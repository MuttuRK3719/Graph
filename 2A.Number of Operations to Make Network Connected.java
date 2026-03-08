
https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1

class Solution {
    class DisjointSet {
        int[] rank, parent;

        public DisjointSet(int n) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int findUPar(int node) {
            if (node == parent[node])
                return node;
            return parent[node] = findUPar(parent[node]);
        }

        public void unionByRank(int u, int v) {
            int uLPa = findUPar(u);
            int vLPa = findUPar(v);
            if (uLPa == vLPa)
                return;
            if (rank[uLPa] < rank[vLPa]) {
                parent[uLPa] = vLPa;
            } else if (rank[vLPa] < rank[uLPa]) {
                parent[vLPa] = uLPa;
            } else {
                parent[vLPa] = uLPa;
                rank[uLPa]++;
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {
        DisjointSet d = new DisjointSet(n);
        int count = 0;
        boolean[] vis = new boolean[n + 1];
        for (int[] edge : connections) {
            int u = edge[0], v = edge[1];
            vis[u] = true;
            vis[v] = true;
            if (d.findUPar(u) == d.findUPar(v)) {
                count++;
            } else {
                d.unionByRank(u, v);
            }
        }
        int countCompu = 0;
        for (int i = 0; i < n; i++) {
            if (i == d.parent[i])
                countCompu++;
        }
        System.out.println(count + " " + countCompu + ". " + Arrays.toString(d.parent));
        return count >= countCompu - 1 ? countCompu - 1 : -1;
    }
}
