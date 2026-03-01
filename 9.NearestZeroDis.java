https://leetcode.com/problems/01-matrix/description/
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int[][] dirs = {
                { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 } };
        Queue<int[]> que = new LinkedList<>();
        int m = mat.length, n = mat[0].length;
        boolean[][] vis = new boolean[m][n];
        int[][] res = new int[m][n];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    que.offer(new int[] { i, j, 0 });
                    vis[i][j] = true;
                }
            }
        }
        while (!que.isEmpty()) {
            int size = que.size();
            int[] val = que.poll();
            int row = val[0], col = val[1];
            for (int dir[] : dirs) {
                int nRow = dir[0] + row, nCol = dir[1] + col;
                if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n && !vis[nRow][nCol]) {
                    res[nRow][nCol] = val[2] + 1;
                    vis[nRow][nCol] = true;
                    que.offer(new int[] { nRow, nCol, val[2] + 1 });
                }
            }
        }

        return res;
    }
}
