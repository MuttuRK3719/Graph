https://www.geeksforgeeks.org/problems/alien-dictionary/1
Input: words[] = ["baa", "abcd", "abca", "cab", "cad"]
Output: true
Explanation: A possible corrct order of letters in the alien dictionary is "bdac".
The pair "baa" and "abcd" suggests 'b' appears before 'a' in the alien dictionary.
The pair "abcd" and "abca" suggests 'd' appears before 'a' in the alien dictionary.
The pair "abca" and "cab" suggests 'a' appears before 'c' in the alien dictionary.
The pair "cab" and "cad" suggests 'b' appears before 'd' in the alien dictionary.
So, 'b' → 'd' → 'a' → 'c' is a valid ordering.

class Solution {
    public String findOrder(String[] words) {

        List<List<Integer>> adj = new ArrayList<>();
        boolean[] present = new boolean[26];
        for (String s : words) {
            for (char c : s.toCharArray())
                present[c - 'a'] = true;
        }
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < words.length - 1; i++) {
            String s1 = words[i], s2 = words[i + 1];
            int len = Math.min(s1.length(), s2.length());
            boolean found = false;
            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    found = true;
                    break;
                }
            }
            if (!found && s1.length() > s2.length()) {
                return "";
            }
        }
        // System.out.println(sb+" "+res+" "+adj);
        return topoSort(adj, present);
    }

    String topoSort(List<List<Integer>> adj, boolean[] present) {
        int[] inDegree = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int ele : adj.get(i)) {
                inDegree[ele]++;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        int presentEle = 0;
        for (int i = 0; i < 26; i++) {
            if (present[i])
                presentEle++;
            if (inDegree[i] == 0 && present[i])
                que.offer(i);
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!que.isEmpty()) {
            count++;
            int node = que.poll();
            sb.append((char) (node + 'a'));
            for (int ele : adj.get(node)) {
                inDegree[ele]--;
                if (inDegree[ele] == 0)
                    que.offer(ele);
            }
        }
        if (count != presentEle)
            return "";
        return sb.toString();
    }
}
