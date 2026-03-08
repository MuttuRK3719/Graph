
class Dsjoin{
        int[]parent,rank;
        public Dsjoin(int n){
            rank=new int[n+1];
            parent=new int[n+1];
            for(int i=0;i<=n;i++){
                parent[i]=i;
            }
        }
        int findParent(int node){
            if(node==parent[node]) return node;
            return parent[node]=findParent(parent[node]);
        }
        void unionByRank(int u,int v){
            int ulPa_u=findParent(u);
            int ulPa_v=findParent(v);
            if(ulPa_v==ulPa_u) return ;
            if(rank[ulPa_u]<rank[ulPa_v]){
                parent[ulPa_u]=ulPa_v;
            }
            else if(rank[ulPa_v]<rank[ulPa_u]){
                parent[ulPa_v]=ulPa_u;
            }
            else{
                parent[ulPa_v]=ulPa_u;
                rank[ulPa_v]++;
            }
        }
    }
    class Edge implements Comparable<Edge>{
        int src, des, wt;
        public Edge(int src,int des,int wt){
            this.src=src;
            this.des=des;
            this.wt=wt;
        }
        public int compareTo(Edge e){
            return this.wt-e.wt;
        }
    }
public int kruskalsAlgorithm(int V,int [][]edges){
        int sum=0;
        List<Edge> edgesList=new ArrayList<>();
    //   Arrays.sort(edges,(a,b)->a[2]-b[2]);
    for(int []edge:edges){
        int u=edge[0],v=edge[1],wt=edge[2];
        edgesList.add(new Edge(u,v,wt));
        edgesList.add(new Edge(v,u,wt));
    }
    Collections.sort(edgesList);
       Dsjoin d=new Dsjoin(V);
       for(Edge e:edgesList){
           int u=e.src,v=e.des,wt=e.wt;
           if(d.findParent(u)!=d.findParent(v)){
            sum+=wt;
            d.unionByRank(u,v);
           }
       }
       return sum;
    }
