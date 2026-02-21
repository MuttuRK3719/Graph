Input: board = [
  ["X","X","X","X"],
  ["X","O","O","X"],
  ["X","X","O","X"],
  ["X","O","X","X"]]

Output: [
["X","X","X","X"],
["X","X","X","X"],
["X","X","X","X"],
["X","O","X","X"]]

class Solution {
    public void solve(char[][] board) {
        //instead of writing for time loop i written 4 verbile to travels  boandary
        int[] travels = { 0, board.length - 1, 0, board[0].length-1 };
        boolean[][] vis = new boolean[board.length][board[0].length];
        for (int i = 0; i < 4; i++) {
            if (i < 2)
                for (int j = 0; j < board[0].length; j++) {
                    if (board[travels[i]][j] == 'O'){
                        // System.out.println("HI");
                        dfs(vis, board,travels[i], j);

                    }
                }
            else {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][travels[i]] == 'O')
                        dfs(vis, board,j,travels[i]);
                }
            }
        }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'O' && !vis[i][j])
                        board[i][j] = 'X';
                }
            }
    }

    void dfs(boolean[][] visited, char[][] board, int row, int col) {
        visited[row][col] = true;
        int[][] dirs = { { 0, -1 },
                { 0, 1 },
                { -1, 0 },
                { 1, 0 } };
        for (int[] dir : dirs) {
            int nRow = dir[0] + row;
            int nCol = dir[1] + col;
            if (nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length && !visited[nRow][nCol]
                    && board[nRow][nCol] == 'O') {
                dfs(visited, board, nRow, nCol);
            }
        }
    }
}
