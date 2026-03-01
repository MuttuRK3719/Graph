Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

  after completing with all possible root max val. in that we have to find min value



class Solution {
    public int minimumEffortPath(int[][] heights) {
        if (heights.length == 0 || heights[0].length == 0)
            return 0;
        int m = heights.length, n = heights[0].length;
        int[][] dis = new int[m][n];
        int[][] dirs = { { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 } };
        for (int[] d : dis) {
            Arrays.fill(d, (int) 1e7);
        }
        dis[0][0] = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        que.offer(new int[] { 0, 0, 0 });
        while (!que.isEmpty()) {
            int[] arr = que.poll();
            int cost = arr[0];
            int row = arr[1];
            int col = arr[2];
            if (row == m - 1 && col == n - 1)
                return cost;
            for (int[] dir : dirs) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];
                if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n) {
                    int edgeDiff = Math.abs(heights[nRow][nCol] - heights[row][col]);
                    int newEffort = Math.max(edgeDiff, cost);
                    if (dis[nRow][nCol] > newEffort) {
                        dis[nRow][nCol] = newEffort;
                        que.offer(new int[] { newEffort, nRow, nCol });
                    }
                }
            }
        }
        return dis[m - 1][n - 1];

    }
}
