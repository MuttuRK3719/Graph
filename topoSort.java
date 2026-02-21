class Solution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
        }
        boolean []vis=new boolean[V];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<V;i++)
        if(!vis[i])dfs(i,vis,adj,st);
        ArrayList<Integer> res=new ArrayList<>();
        while(!st.isEmpty()){
            res.add(st.pop());
        }
        // System.out.println(res);
        return res;
        
    }
    void dfs(int start,boolean[]vis,List<List<Integer>> adj,Stack<Integer> st){
        vis[start]=true;
        for(int ele:adj.get(start)){
            if(!vis[ele])dfs(ele,vis,adj,st);
        }
        st.push(start);
    }
    
}
