public boolean isCyclic(int V, int[][] edges) {
        // code here
        // return usingDFS(V,edges);
        return usingBFS(V,edges);
    }
    boolean usingBFS(int V, int[][] edges){
        int[]inDegree=new int[V];
        List<List<Integer>> adj=getDirectedAdjs(V,edges);
        for(int i=0;i<V;i++){
            for(int j=0;j<adj.get(i).size();j++){
                inDegree[adj.get(i).get(j)]++;
            }
        }
        Queue<Integer> que=new LinkedList<>();
        for(int i=0;i<V;i++){
            if(inDegree[i]==0)que.offer(i);
        }
        int count=0;
        while(!que.isEmpty()){
            int node=que.poll();
            count++;
            for(int ele:adj.get(node)){
                inDegree[ele]--;
                if(inDegree[ele]==0) que.offer(ele);
            }
            
        }
        return count==V?false:true;
        
        
    }
    List<List<Integer>> getDirectedAdjs(int V,int[][]edges){
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
           int  u=edges[i][0];
            int v=edges[i][1];
            adj.get(u).add(v);
        }
        return adj;
    }
