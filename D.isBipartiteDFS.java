
https://leetcode.com/problems/is-graph-bipartite/description/
Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.

public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1 && !dfs(i, graph, color,0)) {
                return false;
            }
        }
        return true;
    }
    boolean dfs(int start,int [][]graph,int []color,int col){
        color[start]=col;
        for(int ele:graph[start]){
            if(color[ele]==-1){
                if(!dfs(ele,graph,color,1-color[start])) return false;
            }
            else if(color[start]==color[ele]) return false;
        }
        return true;
    }
