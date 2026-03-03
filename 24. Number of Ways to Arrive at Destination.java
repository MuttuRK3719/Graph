Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6


class Solution {
    class Node {
        int node;
        long time;

        public Node(int n, long t) {
            node = n;
            time = t;
        }
    }

    public int countPaths(int n, int[][] roads) {
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            adj.get(u).add(new Node(v, time));
            adj.get(v).add(new Node(u, time));
        }
        long[] dis = new long[n], ways = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> Long.compare(a.time, b.time));
        int MOD = (int) (1e9 + 7);
        que.offer(new Node(0, 0));
        dis[0] = 0;
        ways[0] = 1;
        while (!que.isEmpty()) {
            Node curr = que.poll();
            if (curr.time > dis[curr.node])
                continue;
            for (Node nei : adj.get(curr.node)) {
                long totalTime = nei.time + curr.time;
                if (dis[nei.node] > totalTime) {
                    dis[nei.node] = totalTime;
                    ways[nei.node] = ways[curr.node];
                    que.offer(new Node(nei.node, totalTime));
                } else if (dis[nei.node] == totalTime) {
                    ways[nei.node] = (ways[nei.node] + ways[curr.node]) % MOD;
                }
            }
        }
        return (int) ways[n - 1];
    }
}
