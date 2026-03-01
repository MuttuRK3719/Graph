https://www.geeksforgeeks.org/problems/topological-sort/1

ArrayList<Integer> usingBFS(int V,int[][]edges){
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        for(int []edge:edges){
            int u=edge[0],v=edge[1];
            adj.get(u).add(v);
        }
        int[]inDegree=new int[V];
        for(int i=0;i<V;i++){
            for(int j=0;j<adj.get(i).size();j++){
                inDegree[adj.get(i).get(j)]++;
            }
        }
        Queue<Integer> que=new LinkedList<>();
        for(int i=0;i<V;i++){
            if(inDegree[i]==0) que.offer(i);
        }
        // System.out.println(Arrays.toString(inDegree));
        ArrayList<Integer> topoList=new ArrayList<>();
        while(!que.isEmpty()){
            int node=que.poll();
            topoList.add(node);
            for(int ele:adj.get(node)){
                inDegree[ele]--;
                if(inDegree[ele]==0){
                    que.offer(ele);
                } 
            }
        }
        // System.out.println(topoList);
        return topoList;
