static ArrayList<Integer> printShortestPath(int V,
        ArrayList<ArrayList<Pair>> graph,
        int src,
        int dest) {

    int[] dis = new int[V];
    int[] path = new int[V];

    Arrays.fill(dis, (int)1e9);
    Arrays.fill(path, -1);

    dis[src] = 0;
    path[src] = src;

    PriorityQueue<Pair> pq =
        new PriorityQueue<>((a,b) -> a.first - b.first);

    pq.offer(new Pair(0, src));

    while(!pq.isEmpty()) {

        Pair curr = pq.poll();
        int cost = curr.first;
        int node = curr.sec;

        if(cost > dis[node]) continue;

        for(Pair nei : graph.get(node)) {

            int wt = nei.first;
            int next = nei.sec;

            if(cost + wt < dis[next]) {
                dis[next] = cost + wt;
                path[next] = node;
                pq.offer(new Pair(dis[next], next));
            }
        }
    }

    // If destination unreachable
    if(dis[dest] == (int)1e9) {
        return new ArrayList<>(Arrays.asList(-1));
    }

    // Reconstruct path
    ArrayList<Integer> res = new ArrayList<>();
    int curr = dest;

    while(curr != src) {
        res.add(curr);
        curr = path[curr];
    }
    res.add(src);

    Collections.reverse(res);

    return res;
}
