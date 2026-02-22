https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
Input: V = 4, edges[][] = [[0, 1], [1, 2], [2, 0], [2, 3]]
Output: true
Explanation: The diagram clearly shows a cycle 0 → 1 → 2 → 0
class Solution {
    public boolean isCyclic(int V, int[][] edges) {
        // code here
        boolean []vis=new boolean[V],paths=new boolean[V];
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
           int  u=edges[i][0];
            int v=edges[i][1];
            adj.get(u).add(v);
        }
        for(int i=0;i<V;i++){
            if(!vis[i]&&dfs(i,adj,vis,paths)) return true;
        }
        return false;
    }
    boolean dfs(int node,List<List<Integer>> adj,boolean[] vis,boolean[]paths){
        vis[node]=true;
        paths[node]=true;
        
        for(int ele:adj.get(node)){
            if(!vis[ele]){
                if(dfs(ele,adj,vis,paths)) return true;
            }
            else if(paths[ele]) return true;
        }
        paths[node]=false;
        return false;
        
    }
}
