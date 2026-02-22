https://leetcode.com/problems/course-schedule-ii/description/

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = getAdjs(numCourses, prerequisites);
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < adj.size(); i++) {
            for (int ele : adj.get(i))
                inDegree[ele]++;
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                que.offer(i);
        }
        int[] res = new int[numCourses];

        int i = 0;
        while (!que.isEmpty()) {
            int node = que.poll();
            res[i++] = node;
            for (int ele : adj.get(node)) {
                inDegree[ele]--;
                if (inDegree[ele] == 0)
                    que.offer(ele);
            }
        }
        if (i < numCourses)
            return new int[0];
        return res;
    }

    List<List<Integer>> getAdjs(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }
}
