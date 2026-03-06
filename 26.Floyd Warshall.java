https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1

Input: dist[][] = [[0, 4, 108, 5, 108], [108, 0, 1, 108, 6], [2, 108, 0, 3, 108], [108, 108, 1, 0, 2], [1, 108, 108, 4, 0]]


Output: [[0, 4, 5, 5, 7], [3, 0, 1, 4, 6], [2, 6, 0, 3, 5], [3, 7, 1, 0, 2], [1, 5, 5, 4, 0]]
class Solution {
    public void floydWarshall(int[][] dist) {
        int max = (int) 1e8;
        int n = dist.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                // If i don't know the Candidate how to refer other to see that candidate through my reference
                if (dist[i][k] == max)
                    continue;
                for (int j = 0; j < n; j++) {
                    // I don't know even this refering candition how can trust on him
                    if (dist[k][j] == max)
                        continue;
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }
    }
}
