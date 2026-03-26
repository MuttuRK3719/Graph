https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

class Solution {
    class Dsjoint {
        int[] size, parent;
        boolean[] vis;

        public Dsjoint(int n) {
            size = new int[n];
            vis = new boolean[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            return parent[node] = findParent(parent[node]);
        }

        void unionBySize(int u, int v) {
            vis[v] = true;
            vis[u] = true;
            int UP_u = findParent(u);
            int UP_v = findParent(v);
            if (UP_v == UP_u)
                return;
            if (size[UP_u] > size[UP_v]) {
                parent[UP_v] = UP_u;
                size[UP_u] += size[UP_v];
            } else {
                parent[UP_u] = UP_v;
                size[UP_v] += size[UP_u];
            }
        }
    }

    public int removeStones(int[][] stones) {
        int row = 0, col = 0;
        for (int i = 0; i < stones.length; i++) {
            row = Math.max(stones[i][0], row);
            col = Math.max(stones[i][1], col);
        }
        Dsjoint d = new Dsjoint(row + col + 3);
        for (int i = 0; i < stones.length; i++) {
            d.unionBySize(stones[i][0], stones[i][1] + row + 1);
        }
        int count = 0;
        for (int i = 0; i < d.parent.length; i++) {
            if (i == d.parent[i] && d.vis[i])
                count++;
        }
        // System.out.println(Arrays.toString(d.parent)+"::   "+Arrays.toString(d.size));
        return stones.length - count;
    }
}
