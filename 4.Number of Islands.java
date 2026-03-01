https://leetcode.com/problems/number-of-islands/

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    bfs(Arrays.asList(i, j), visited, grid);
                }
            }
        }
        return count;
    }

    void bfs(List<Integer> rowCol, boolean[][] visited, char[][] grid) {

        int m = grid.length, n = grid[0].length;
        Queue<List<Integer>> que = new LinkedList<>();
        int row = rowCol.get(0), col = rowCol.get(1);
        System.out.println(row + " " + col);
        // visited[row][col]=true;
        int[][] g = {
                { -1, 0 },
                { 1, 0 },
                { 0, 1 },
                { 0, -1 }
        };
        que.offer(rowCol);
        while (!que.isEmpty()) {
            rowCol = que.poll();
            row = rowCol.get(0);
            col = rowCol.get(1);
            for (int[] r1 : g) {
                int nrow = row + r1[0], ncol = col + r1[1];
                if (nrow >= 0 && ncol >= 0 && nrow < m && ncol < n && !visited[nrow][ncol] && grid[nrow][ncol] == '1') {
                    visited[nrow][ncol] = true;
                    que.offer(Arrays.asList(nrow, ncol));
                }
            }
        }
    }
}
