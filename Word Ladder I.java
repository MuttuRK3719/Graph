https://leetcode.com/problems/word-ladder/description/

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        boolean[] vis = new boolean[n];
        if (!wordList.contains(endWord))
            return 0;
        Queue<String> que = new LinkedList<>();
        que.offer(beginWord);
        int c = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int k = 0; k < size; k++) {
                String s = que.poll();
                if (s.equals(endWord))
                    return c;
                for (int i = 0; i < n; i++) {
                    String word = wordList.get(i);
                    if (!vis[i]) {
                        int count = 0;
                        for (int j = 0; j < word.length(); j++) {
                            if (s.charAt(j) != word.charAt(j))
                                count++;
                            if (count > 1)
                                break;
                        }
                        if (count == 1) {
                            que.offer(wordList.get(i));
                            vis[i] = true;
                        }
                    }
                }
            }
            c++;
        }
        return 0;
    }
}
