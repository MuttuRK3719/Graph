class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int n=adj.size();
        ArrayList<Integer> res=new ArrayList<>();
        boolean []visited=new boolean[n];
        getDFS(0,adj,visited,res);
        return res;
    }
    void getDFS(int node,ArrayList<ArrayList<Integer>> adj,boolean []visited,ArrayList<Integer> res){
        visited[node]=true;
        res.add(node);
        for(int ele:adj.get(node)){
            if(!visited[ele]){
                getDFS(ele,adj,visited,res);
            }
        }
    }
}
