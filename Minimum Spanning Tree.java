https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1

Input: V = 3, E = 3, Edges = [[0, 1, 5], [1, 2, 3], [0, 2, 1]]
 
Output: 4


class Solution {
    class Pair {
        int node, wt;

        public Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }
    public int spanningTree(int V, int[][] edges) {
        PriorityQueue<Pair> que = new PriorityQueue<>((a, b) -> a.wt - b.wt);
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] row : edges) {
            int u = row[0], v = row[1], wt = row[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }
        boolean[] vis = new boolean[V];

        que.offer(new Pair(0, 0));
        int sum = 0;
        while (!que.isEmpty()) {
            Pair p = que.poll();
            if (vis[p.node])
                continue;
            sum += p.wt;
            vis[p.node] = true;
            for (Pair nei : adj.get(p.node)) {
                que.offer(new Pair(nei.node, nei.wt));
            }
        }
        return sum;

    }
}
