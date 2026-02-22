
https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1

Input: V = 9, E = 10, 
edges[][] = [[0, 1], [0, 3], [1, 2], [3, 4], [4, 5], [2, 6], [5, 6], [6, 7], [6, 8], [7, 8]], src = 0
Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]

class Solution {
    public int[] shortestPath(int V, int[][] edges, int src) {
        // // code here
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        Queue<int[]> que = new LinkedList<>();
        int[] dis = new int[V];
        Arrays.fill(dis, -1);
        dis[src] = 0;
        que.offer(new int[] { src, 0 });
        while (!que.isEmpty()) {
            int[] vertex = que.poll();
            int node = vertex[0];
            for (int ele : adj.get(node)) {
                if (dis[node] + 1 < dis[ele] || dis[ele] == -1) {
                    dis[ele] = dis[node] + 1;
                    que.offer(new int[] { ele, dis[ele] });
                }
            }
        }
        return dis;
    }
}
