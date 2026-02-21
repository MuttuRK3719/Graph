https://leetcode.com/problems/number-of-enclaves/description/

You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.


class Solution {
    public int numEnclaves(int[][] grid) {
        int[] boarders = { 0, grid.length - 1, 0, grid[0].length - 1 };
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                for (int j = 0; j < n; j++) {
                    if (grid[boarders[i]][j] == 1 && !vis[boarders[i]][j])
                        dfs(vis, grid, boarders[i], j);
                }
            } else {
                for (int j = 0; j < m; j++) {
                    if (grid[j][boarders[i]] == 1 && !vis[j][boarders[i]])
                        dfs(vis, grid, j, boarders[i]);
                }
            }
        }
        System.out.println(Arrays.deepToString(vis));
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    count++;
                }
            }
        }
        System.out.println(Arrays.deepToString(vis));
        return count;
    }

    void dfs(boolean[][] vis, int[][] grid, int row, int col) {
        int[][] dirs = { { -1, 0 },
                { 1, 0 },
                { 0, 1 },
                { 0, -1 } };
        vis[row][col] = true;
        for (int[] dir : dirs) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if (nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && !vis[nRow][nCol]
                    && grid[nRow][nCol] == 1) {
                dfs(vis, grid, nRow, nCol);
            }
        }
    }
}
