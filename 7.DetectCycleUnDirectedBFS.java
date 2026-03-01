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
                if (bfs(i, adj, visited)) { 
                    return true;
                }
            }
        }
        
        // If no cycle is found in any component
        return false;
    }

    static boolean bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        
        // Queue stores {current node, parent node}
        Queue<int[]> q = new LinkedList<>();
         
        // Start node has no parent
        q.add(new int[]{start, -1});
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.peek()[0];
            int parent = q.peek()[1];
            q.poll();

            // Traverse all neighbors of current node
            for (int neighbor : adj.get(node)) {

                // If neighbor is not visited, mark it visited and push to queue
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(new int[]{neighbor, node});
                } 
                // If neighbor is visited and not parent, a cycle is detected
                else if (neighbor != parent) {
                    return true;
                }
            }
        }
        
        // No cycle found starting from this node
        return false; 
    }
