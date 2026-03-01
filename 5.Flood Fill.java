https://leetcode.com/problems/flood-fill/description/
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2

Output: [[2,2,2],[2,2,0],[2,0,1]]

class Solution {
    int[][] moveMents = {
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 }
    };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];
        if (color == oldColor)
            return image;
        dfs(image, sr, sc, color, oldColor);
        return image;
    }

    void dfs(int[][] image, int row, int col, int newColor, int oldColor) {

        int m = image.length, n = image[0].length;
        image[row][col] = newColor;
        for (int[] dir : moveMents) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && image[newRow][newCol] == oldColor) {
                dfs(image, newRow, newCol, newColor, oldColor);
            }
        }
    }
}
