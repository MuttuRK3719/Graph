https://leetcode.com/problems/find-eventual-safe-states/description/

Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> list=new ArrayList<>();
        int n=graph.length;
        boolean[]vis=new boolean[n],paths=new boolean[n],safer=new boolean[n];
        for(int i=0;i<graph.length;i++){
            if(!vis[i])dfs(i,graph,vis,paths,safer);
        }
        for(int i=0;i<n;i++){
            if(safer[i]) list.add(i);
        }
        return list;
    }
    boolean dfs(int start,int[][]graph,boolean[]vis,boolean[]paths,boolean[]safer){
        vis[start]=true;
        paths[start]=true;
        for(int node:graph[start]){
         if(!vis[node]){
            if(dfs(node,graph,vis,paths,safer)) return true;
         }
         else if(paths[node]) return true;   
        }
        paths[start]=false;
        safer[start]=true;
        return false;

    }
