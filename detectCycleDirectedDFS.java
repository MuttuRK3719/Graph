public boolean isCycle(int V, int[][] edges) {
       boolean[] visited = new boolean[V]; 

        // Perform BFS from every unvisited node
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        for(int []row:edges){
            int u=row[0] , v=row[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                
                // If BFS finds a cycle
                if (dfs(adj,-1,i, visited)) { 
                    return true;
                }
            }
        }
        
        // If no cycle is found in any component
        return false;
    }
    static boolean dfs(ArrayList<ArrayList<Integer>> adj,int parent,int start,boolean[] visited){
        visited[start]=true;
        for(int ele:adj.get(start)){
            if(!visited[ele]){
                if(dfs(adj,start,ele,visited)) return true;
            }
            else if(ele!=parent) return true;
        }
        return false;
    }
