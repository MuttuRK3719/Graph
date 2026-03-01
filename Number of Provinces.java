https://leetcode.com/problems/number-of-provinces/description/

Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int v = isConnected.length;
        boolean[] visited = new boolean[v];
        int count = 0;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < v; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    list.add(i);
                    list.add(j);
                }
            }
            adjList.add(list);

        }
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(i, visited, adjList);
                count++;
            }
        }
        return count;
    }

    void dfs(int node, boolean[] visited, List<List<Integer>> isConnected) {
        visited[node] = true;
        for (int ele : isConnected.get(node)) {
            if (!visited[ele])
                dfs(ele, visited, isConnected);
        }
    }
}
