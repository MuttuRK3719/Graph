https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1

Input:
grid[][] = [[1, 1, 0, 0, 0],
            [1, 1, 0, 0, 0],
            [0, 0, 0, 1, 1],
            [0, 0, 0, 1, 1]]
Output: 1
Explanation:
grid[][] = [[1, 1, 0, 0, 0], 
            [1, 1, 0, 0, 0], 
            [0, 0, 0, 1, 1], 
            [0, 0, 0, 1, 1]]
Same colored islands are equal. We have 2 equal islands, so we have only 1 distinct island.

class Solution {

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        Set<List<String>> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    List<String> neiCords = new ArrayList<>();
                    dfs(vis, grid, i, j, i, j, neiCords);
                    set.add(neiCords);
                }
            }
        }

        return set.size();
    }

    void dfs(boolean[][] vis, int[][] grid, int row, int col, int row0, int col0, List<String> cord) {
        vis[row][col] = true;
        int[][] dirs = { { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 } };
        cord.add(toString(row - row0, col - col0));
        for (int[] dir : dirs) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if (nRow >= 0 && nRow < vis.length && nCol >= 0
                    && nCol < grid[0].length && !vis[nRow][nCol]
                    && grid[nRow][nCol] == 1) {
                dfs(vis, grid, nRow, nCol, row0, col0, cord);
            }
        }
    }

    String toString(int row, int col) {
        return row + "," + col;
    }
}
