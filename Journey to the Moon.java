static class Disjoint{
        int[]parent,size;
        public Disjoint(int n){
            parent=new int[n];
            size=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }
        int findParent(int node){
            if(parent[node]==node){
                return node;
            }
            return parent[node]=findParent(parent[node]);
        }
        void unionBySize(int u,int v){
            int ULP_u=findParent(u);
            int ULP_v=findParent(v);
            if(ULP_u==ULP_v) return ;
            if(size[ULP_u]<size[ULP_v]){
                parent[ULP_u]=ULP_v;
                size[ULP_v]+=size[ULP_u];
            }
            else {
                parent[ULP_v]=ULP_u;
                size[ULP_u]+=size[ULP_v];
            }
        }
    }
    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
    // Write your code here
        Disjoint d=new Disjoint(n);
        for(List<Integer> group:astronaut){
            int u=group.get(0);
            int v=group.get(1);
            d.unionBySize(u, v);
        }
        Map<Integer,Integer> components=new HashMap<>();
        for(int i=0;i<n;i++){ 
            int parent=d.findParent(i);
            components.put(parent, components.getOrDefault(parent, 0)+1);
        }
        long sum=0;
        long result=0;
        for(int size:components.values()){
            result+=sum*size;
            sum+=size;
        }
    return result;
    }
