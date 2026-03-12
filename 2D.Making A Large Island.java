https://leetcode.com/problems/making-a-large-island/description/

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


class Solution {
    class DisjoinSet {
        int[] parent, size;

        public DisjoinSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findParent(int node) {
            if (node == parent[node])
                return node;
            return parent[node] = findParent(parent[node]);
        }

        void unionBySize(int u, int v) {
            int uPU = findParent(u);
            int uPV = findParent(v);
            if (uPU == uPV)
                return;
            if (size[uPU] < size[uPV]) {
                parent[uPU] = uPV;
                size[uPV] += size[uPU];
            } else {
                parent[uPV] = uPU;
                size[uPU] += size[uPV];
            }
        }
    }

    public int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1)
            return 1;
        int[][] dirs = {
                { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 } };

        DisjoinSet d = new DisjoinSet(m * n);
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int node = i * n + j;
                for (int[] dir : dirs) {
                    int nRow = i + dir[0];
                    int nCol = j + dir[1];
                    int adjNode = nRow * n + nCol;
                    if (isValid(nRow, nCol, m, n)) {
                        if (grid[i][j] == 1 && grid[nRow][nCol] == 1) {
                            d.unionBySize(node, adjNode);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int node = i * n + j;
                Set<Integer> component = new HashSet<>();
                for (int[] dir : dirs) {
                    int nRow = i + dir[0];
                    int nCol = j + dir[1];
                    if (isValid(nRow, nCol, m, n)) {
                        if (grid[nRow][nCol] == 1)
                            component.add(d.findParent(nRow * n + nCol));
                    }
                }
                int size = 0;
                for (int parent : component)
                    size += d.size[parent];
                if (grid[i][j] == 0)
                    size++;
                ans = Math.max(size, ans);
            }
        }
        System.out.println(Arrays.toString(d.size));
        return ans;
    }

    static boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}
