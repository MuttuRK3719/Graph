https://leetcode.com/problems/minimum-height-trees/description/?envType=problem-list-v2&envId=graph


Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]


class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (edges.length == 0 || edges[0].length == 0)
            return Arrays.asList(0);
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1)
                que.offer(i);
        }
        while (n > 2) {
            int size = que.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                int leaf = que.poll();
                for (int nei : adj.get(leaf)) {
                    degree[nei]--;
                    if (degree[nei] == 1)
                        que.offer(nei);
                }
            }
        }
        return new ArrayList<>(que);

    }
}
