https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1

Input: V = 6, E = 7, edges = [[0,1,2], [0,4,1], [4,5,4], [4,2,2], [1,2,3], [2,3,6], [5,3,1]]
Output: [0, 2, 3, 6, 1, 5]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2.
Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3. Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6.
Shortest path from 0 to 4 is 0->4 with 
edge weight 1.Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.

class Solution {
    class Pair {
        int first, sec;

        public Pair(int first, int sec) {
            this.first = first;
            this.sec = sec;
        }
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        List<List<Pair>> adj = new ArrayList<>();
        boolean[] vis = new boolean[V];
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1], wt = edges[i][2];
            adj.get(u).add(new Pair(v, wt));
        }

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                topoDFS(st, i, adj, vis);
        }
        int[] dis = new int[V];
        Arrays.fill(dis, 1000);
        dis[0] = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            for (Pair p : adj.get(node)) {
                int v = p.first;
                int wt = p.sec;
                dis[v] = Math.min(dis[node] + wt, dis[v]);
            }
        }
        for (int i = 0; i < V; i++) {
            if (dis[i] == 1000)
                dis[i] = -1;
        }
        return dis;
    }

    void topoDFS(Stack<Integer> st, int node, List<List<Pair>> adj, boolean[] vis) {
        vis[node] = true;
        for (Pair pair : adj.get(node)) {
            int nei = pair.first;
            if (!vis[nei])
                topoDFS(st, nei, adj, vis);
        }
        st.push(node);
    }
}
