https://leetcode.com/problems/is-graph-bipartite/description/

Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.

class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1 && !bfs(i, graph, color)) {
                return false;
            }
        }
        return true;
    }

    boolean bfs(int start, int[][] graph, int[] color) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        color[start] = 0;
        while (!que.isEmpty()) {
            int node = que.poll();
            for (int ele : graph[node]) {
                if (color[ele] == -1) {
                    color[ele] = 1 - color[node];
                    que.offer(ele);
                } else if (color[ele] == color[node])
                    return false;
            }
        }
        return true;
    }
}
