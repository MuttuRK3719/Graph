https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

class Solution {
    class Pair {
        int node, cost;

        public Pair(int n, int c) {
            node = n;
            cost = c;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (src == dst)
            return 0;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { src, 0, 0 });
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] node : flights) {
            int u = node[0];
            int v = node[1];
            int cost = node[2];
            adj.get(u).add(new Pair(v, cost));
        }
        int res = (int) 1e7;
        int[] dis = new int[n];
        Arrays.fill(dis, (int) 1e7);
        dis[src] = 0;
        while (!que.isEmpty()) {
            int[] arr = que.poll();
            int node = arr[0], cost = arr[1], stops = arr[2];
            if (stops > k)
                continue;
            for (Pair nei : adj.get(node)) {
                int node1 = nei.node, cost1 = nei.cost;
                int totalEffort = cost1 + cost;
                if (node1 == dst)
                    res = Math.min(totalEffort, res);
                if (dis[node1] > totalEffort && stops + 1 <= k) {
                    dis[node1] = totalEffort;
                    que.offer(new int[] { node1, totalEffort, stops + 1 });
                }
            }
        }
        return res == (int) 1e7 ? -1 : res;
    }
}
