class Solution {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        boolean[]visited=new boolean[adj.size()];
        Queue<Integer> que=new LinkedList<>();
        que.offer(0);
        visited[0]=true;
        while(!que.isEmpty()){
            int node=que.poll();
            res.add(node);
            for(int ele:adj.get(node)){
            if(!visited[ele]){
                visited[ele]=true;
                que.offer(ele);
            }
            }
        }
        return res;
    }
}
