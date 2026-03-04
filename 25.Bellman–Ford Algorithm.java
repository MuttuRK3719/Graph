https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

Input: V = 5, edges[][] = [[1, 3, 2], [4, 3, -1], [2, 4, 1], [1, 2, 1], [0, 1, 5]], src = 0
Output: [0, 5, 6, 6, 7]
Explanation: Shortest Paths:
For 0 to 1 minimum distance will be 5. By following path 0 → 1
For 0 to 2 minimum distance will be 6. By following path 0 → 1  → 2
For 0 to 3 minimum distance will be 6. By following path 0 → 1  → 2 → 4 → 3 
For 0 to 4 minimum distance will be 7. By following path 0 → 1  → 2 → 4


class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int[] dis = new int[V];
        Arrays.fill(dis, (int) 1e8);
        dis[src] = 0;
        // List<List<Integer>> adj=new ArrayList<>();
        // for(int i=0;i<V;i++)adj.add(new ArrayList<>());
        for (int i = 0; i < V; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];
                if (dis[u] != (int) 1e8 && dis[u] + wt < dis[v]) {
                    dis[v] = wt + dis[u];
                }
            }
        }
        for (int i = 0; i < V; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];
                if (dis[u] != (int) 1e8 && dis[u] + wt < dis[v]) {
                    return new int[] { -1 };
                }
            }
        }
        return dis;

    }
}
