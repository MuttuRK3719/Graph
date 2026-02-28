https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1

arr[] = {2, 5, 7}
start = 3, end = 30
Output:
2
Explanation:
Step 1: 3*2 = 6 % 100000 = 6 
Step 2: 6*5 = 30 % 100000 = 30

class Solution {
    class Pair {
        int node, steps;

        public Pair(int n, int s) {
            node = n;
            steps = s;
        }
    }

    int minimumMultiplications(int[] arr, int start, int end) {

        // Your code here

        Queue<Pair> que = new LinkedList<>();
        int[] countSteps = new int[100000];
        Arrays.fill(countSteps, (int) 1e7);
        que.offer(new Pair(start, 0));
        countSteps[start] = 0;
        while (!que.isEmpty()) {
            Pair p = que.poll();
            int node = p.node;
            int steps = p.steps;
            //  System.out.println(node);
            if (node == end)
                return steps;
            for (int ele : arr) {
                int num = (ele * node) % 100000;
                if (countSteps[num] > steps + 1) {
                    countSteps[num] = steps + 1;
                    que.offer(new Pair(num, steps + 1));
                }
            }
        }

        return countSteps[end] == (int) 1e7 ? -1 : countSteps[end];
    }
}
