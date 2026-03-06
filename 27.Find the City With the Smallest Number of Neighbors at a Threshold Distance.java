https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/


Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dis = new int[n][n];
        int max = (int) 1e8;
        for (int[] row : dis) {
            Arrays.fill(row, max);
        }
        for (int[] row : edges) {
            int u = row[0], v = row[1], wt = row[2];
            dis[u][v] = wt;
            dis[v][u] = wt;
            dis[v][v] = 0;
            dis[u][u] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dis[i][k] == max)
                    continue;
                for (int j = 0; j < n; j++) {
                    if (dis[k][j] == max)
                        continue;
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        int node = -1, countNode = (int) 1e7;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dis[i][j] == max)
                    continue;
                if (dis[i][j] <= distanceThreshold)
                    count++;
            }
            if (count < countNode) {
                countNode = count;
                node = i;
            }
            if (count == countNode) {
                node = i;
            }
        }
        System.out.println(Arrays.deepToString(dis));
        return node;
    }
}
