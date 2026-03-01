class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            revAdj.add(new ArrayList<>());
        }
        for (int i = 0; i < graph.length; i++) {
            for (int ele : graph[i])
                revAdj.get(ele).add(i);
        }
        List<Integer> res = usingTopo(revAdj);
        Collections.sort(res);
        return res;
    }

    List<Integer> usingTopo(List<List<Integer>> graph) {
        List<Integer> list = new ArrayList<>();
        int n = graph.size();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int ele : graph.get(i)) {
                inDegree[ele]++;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0)
                que.offer(i);
        }
        while (!que.isEmpty()) {
            int node = que.poll();
            list.add(node);
            for (int ele : graph.get(node)) {
                inDegree[ele]--;
                if (inDegree[ele] == 0)
                    que.offer(ele);
            }
        }
        return list;
    }
}
