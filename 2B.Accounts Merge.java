https://leetcode.com/problems/accounts-merge/description/

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

class Solution {
    class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        int findParent(int node) {
            if (node == parent[node])
                return node;
            return parent[node] = findParent(parent[node]);
        }

        void unionByRank(int u, int v) {
            int uPU = findParent(u), uPV = findParent(v);
            if (uPV == uPU)
                return;
            if (rank[uPV] < rank[uPU]) {
                parent[uPV] = uPU;
            } else if (rank[uPU] < rank[uPV]) {
                parent[uPU] = uPV;
            } else {
                parent[uPU] = uPV;
                rank[uPV]++;
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet d = new DisjointSet(n);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {

            for (int j = 1; j < accounts.get(i).size(); j++) {
                String s = accounts.get(i).get(j);
                if (!map.containsKey(s)) {
                    map.put(s, i);
                } else {
                    d.unionByRank(i, map.get(s));
                }
            }
        }
        List<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergedMail[i] = new ArrayList<>();
        }
        for (var it : map.entrySet()) {
            String mail = it.getKey();
            int node = d.findParent(it.getValue());
            mergedMail[node].add(mail);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0)
                continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>(mergedMail[i]);
            temp.add(0, accounts.get(i).get(0));
            ans.add(temp);
        }
        return ans;
    }
}
