
import java.util.*;
class DisjointSet {
    int[]rank,parent;
    public DisjointSet(int n){
        rank=new int[n+1];
        parent=new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
    }
    
    public int findUPar(int node){
        if(node==parent[node]) return node;
        return parent[node]=findUPar(parent[node]);
    }
    public void unionByRank(int u,int v){
        int uLPa=findUPar(u);
        int vLPa=findUPar(v);
        if(uLPa==vLPa) return;
        if(rank[uLPa]<rank[vLPa]){
            parent[uLPa]=vLPa;
        }
        else if(rank[vLPa]<rank[uLPa]){
            parent[vLPa]=uLPa;
        }
        else{
            parent[vLPa]=uLPa;
            rank[vLPa]++;
        }
    }
    void unionBySize(int u,int v){
        int ulPa_u=findUPar(u);
        int ulPa_v=findUPar(v);
        if(ulPa_u==ulPa_v) return ;
        else if(ulPa_u<ulPa_v){
            parent[ulPa_u]=ulPa_v;
            size[ulPa_v]+=size[ulPa_u];
        }
        else {
            parent[ulPa_v]=ulPa_u;
            size[ulPa_u]+=size[ulPa_v];
        }
    }
    public static void main(String[] args) {
         DisjointSet d=new DisjointSet(7);
        d.unionByRank(1,2);
        d.unionByRank(2,3);
        d.unionByRank(4,5);
        d.unionByRank(6,7);
        d.unionByRank(5,6);
        System.out.println(d.findUPar(3)==d.findUPar(7));
        d.unionByRank(3,7);
        System.out.println(d.findUPar(3)==d.findUPar(7));
        System.out.println(Arrays.toString(d.parent));
    }
}
