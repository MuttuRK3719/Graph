https://leetcode.com/problems/course-schedule/description/

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = getDirectedAdjs(numCourses, prerequisites);
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int ele : adj.get(i)) {
                inDegree[ele]++;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                que.offer(i);
        }
        int count = 0;
        System.out.println(que);
        while (!que.isEmpty()) {
            int node = que.poll();
            count++;
            for (int ele : adj.get(node)) {
                inDegree[ele]--;
                if (inDegree[ele] == 0)
                    que.offer(ele);
            }
        }
        return count == numCourses;
    }

    List<List<Integer>> getDirectedAdjs(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(v).add(u);
        }
        return adj;
    }
}
