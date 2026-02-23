class Solution {
    class Pair {
        int first, sec;

        public Pair(int f, int s) {
            first = f;
            sec = s;
        }

        public String toString() {
            return "{cost :" + first + " Node :" + sec + "}";
        };
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        PriorityQueue<Pair> que = new PriorityQueue<>((a, b) -> {
            if (a.first != b.first)
                return a.first - b.first;
            else
                return a.sec - b.sec;
        });
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }
        int[] dis = new int[V];
        // System.out.println(adj);
        Arrays.fill(dis, (int) 1e7);
        dis[src] = 0;
        que.offer(new Pair(0, src));
        while (!que.isEmpty()) {
            Pair p = que.poll();
            int cost = p.first;
            int node = p.sec;
            if (cost > dis[node])
                continue;
            for (Pair nei : adj.get(node)) {
                if (cost + nei.first < dis[nei.sec]) {
                    dis[nei.sec] = cost + nei.first;
                    que.offer(new Pair(dis[nei.sec], nei.sec));
                }
            }
        }
        return dis;

    }
}
