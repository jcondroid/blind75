import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

class solutions {
    /**
     * Arrays & Hashing *********************************
     */
    // Return true if any value appears at least twice in the array
    // Return false if every element is distinct
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> myNums = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!myNums.add(nums[i])){
                return true;
            }
        }
        return false;
    }
    /**
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
     * typically using all the original letters exactly once.
     */
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for(Character c : t.toCharArray()){
            charMap.put(c, charMap.getOrDefault(c,0)+1);
        }
        for(Character c : s.toCharArray()){
            if(!charMap.containsKey(c)) return false;
            
            if(charMap.get(c) == 1){
                charMap.remove(c);
            }else{
               charMap.put(c,charMap.get(c)-1); 
            }
        }
       return charMap.isEmpty();
    }
    /**
     * Return indices of two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     */
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }
    /**
     * Two Pointers *********************************
     */
    /**
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * A phrase is a palindrome if, 
     * after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, 
     * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("\\s", "").replaceAll("[^a-z0-9]", "");
        int rightCounter = s.length() - 1;
        int leftCounter = 0;
        while(leftCounter < rightCounter){
            if(s.charAt(rightCounter) != s.charAt(leftCounter)){
                return false;
            }
            rightCounter -= 1;
            leftCounter += 1;
        }
        return true;
    }
    /**
     * Sliding Window *********************************
     */
    /**
     * Return the maximum profit you can achieve from this transaction. 
     * If you cannot achieve any profit, return 0.
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock 
     * and choosing a different day in the future to sell that stock.
     */
    public int maxProfit(int[] prices) {
        /**
         * Strategy:
         * We really only need to know 2 things: 
         *  What is the largest difference (max profit)
         *  What is the minimum
         */
        int largestDif = 0;
        int minSoFar = Integer.MAX_VALUE;
        
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < minSoFar){
                minSoFar = prices[i];
            }else{
                largestDif = Math.max(largestDif, prices[i] - minSoFar);
            }
        }
        return largestDif;
    }
    /**
     * Stack *********************************
     */
    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']'. 
     * Determine if the input string is valid.
     * An input string is valid if:
     *  Open brackets must be closed by the same type of brackets.
     *  Open brackets must be closed in the correct order.
     *  Every close bracket has a corresponding open bracket of the same type.
     */
    public boolean isValid(String s) {
        Stack<Character> cStack = new Stack<Character>();
        Map<Character, Character> map = Map.of(
                                            '(', ')',
                                            '{', '}',
                                            '[', ']'
                                            );
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)){
                cStack.push(c);
            }else{
                if(cStack.isEmpty()){
                    return false;
                }else{
                    char open = cStack.pop();
                    if(map.get(open) != c){
                        return false;
                    }
                }
            }
        }
        return cStack.isEmpty();
    }
    /**
     * Linked List *********************************
     */
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     */
    /**
     * Strategy: 
     *  Set next equal to head.next (Next node so we don't lose reference)
     *  Set head.next equal to previous (null starting out)
     *  Set previous equal to current (head)
     *  Set current equal to next
     */
    public ListNode reverseList(ListNode head) {
        ListNode reversedList = null;
        while(head != null){
            ListNode next = head.next;
            head.next = reversedList;
            reversedList = head;
            head = next;
        }
        return reversedList;
    }
    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     * Merge the two lists in a one sorted list. 
     * The list should be made by splicing together the nodes of the first two lists.
     * Return the head of the merged linked list.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Keep track of the head of the merged list and the tail to add on to
        ListNode head = new ListNode();
        ListNode tail = head;
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                tail.next = list2;
                list2 = list2.next;
            }else{
                tail.next = list1;
                list1 = list1.next;
            }
            // System.out.println(tail.val);
            tail = tail.next;
        }
        tail.next = list1 == null ? list2 : list1;
        return head.next;
        // Time O(m+n)
        // Space O(1)
    }
    /**
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached 
     * again by continuously following the next pointer. Internally, pos is used to denote the index 
     * of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     * 
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode next = head.next;
        ListNode headCounter = head;
        while(next.next != null){
            if(next.next == headCounter){
                return true;
            }
            if(next.next.next != null){
                next = next.next.next;
            }else{
                return false;
            }
            headCounter = headCounter.next;
        }
        return false;
    }
    /**
     * Trees *********************************
     */
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /**
     * Invert Binary Tree - Given the root of a binary tree, invert the tree, and return its root.
     *      3
     *     / \
     *    1   4
     */
    public TreeNode invertTree(TreeNode root) {
        if(root != null){
            // invert child trees
            invertTree(root.left);
            invertTree(root.right);
            // swap children
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
        }
        return root;
    }
    /**
     * Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path from the 
     * root node down to the farthest leaf node.
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return java.lang.Math.max(left_height, right_height) + 1;
        }
    }
    /**
     * Same Tree
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }
    /**
     * Subtree of Another Tree
     * Given the roots of two binary trees root and subRoot, return true if there is a subtree 
     * of root with the same structure and node values of subRoot and false otherwise.
     * A subtree of a binary tree tree is a tree that consists of a node in tree and all of 
     * this node's descendants. The tree tree could also be considered as a subtree of itself.
     * Strategy: Convert the nodes into a string and then check if one string is contained in the other string.
     */
    HashSet<String> trees = new HashSet<>();
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String tree1 = preOrder(root, true);
        String tree2 = preOrder(subRoot, true);
        return tree1.indexOf(tree2) >= 0;
    }
    public String preOrder(TreeNode node, boolean left){
        if(node == null){
            if(left) return "lnull";
            else return "rnull";
        }
        return "#"+node.val+" "+preOrder(node.left, true)+" "+preOrder(node.right, false);
    }
    /**
     * DP (Dynamic Programming) *********************************
     */
    /**
     * Climbing Stairs
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Strategy: 
     * As we can see this problem can be broken into subproblems (classic DP problem), and it contains 
     * the optimal substructure property i.e. its optimal solution can be constructed efficiently from 
     * optimal solutions of its subproblems, we can use dynamic programming to solve this problem.
     * One can reach i^{th}ith step in one of the two ways:
     *  1. Taking a single step from (i-1)^{th}(i−1)th step. 
     *  2. Taking a step of 22 from (i-2)^{th}(i−2)th step.
     */
    public int climbStairs(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    /**
     * Intervals *********************************
     */
    /**
     * Meeting Rooms
     * Given an array of meeting time intervals where intervals[i] = [starti, endi]
     * Determine if a person could attend all meetings.
     * Strategy: 
     *  Sort the meetings by starting time. 
     *  Go through meetings one by one and check that each meeting ends before the next one starts.
     */
    public boolean canAttendMeetings(int[][] intervals) {
        /**
         * Special note about Lambda functions
         * The -> separates the parameters (left-side) from the implementation (right side).
         * The general syntax for using lambda expressions is (Parameters) -> { Body } 
         * where the -> separates parameters and lambda expression body.
         * The parameters are enclosed in parentheses which is the same way as for methods and the 
         * lambda expression body is a block of code enclosed in braces.
         * This would be similar to function(a, b){ return Integer.compare(a[0], b[0]);}
         */
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort meetings by starting time
        for(int i = 0; i < intervals.length - 1; i++){ // Go through each meeting one by one
            if(intervals[i][1] > intervals[i + 1][0]){ // each meeting ends before the next one starts
                return false;
            }
        }
        return true;
    }
    /**
     * Bit Manipulation *********************************
     */
    /**
     * Number of 1 Bits
     * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
     * Note:
     * In some languages, such as Java, there is no unsigned integer type. 
     * In this case, the input will be given as a signed integer type. 
     * It should not affect your implementation, as the integer's internal binary 
     * representation is the same, whether it is signed or unsigned.
     * 
     * In Java, the compiler represents the signed integers using 2's complement notation. 
     * Therefore, in Example 3, the input represents the signed integer. -3.
     * 
     * Strategy:
     * Instead of checking every bit of the number, we repeatedly flip the least-significant 1-bit of the number to 0
     * and add 1 to the sum. As soon as the number becomes 0 we know that it does not have any more 1-bits and we return the sum.
     * The key idea here is to realize that for any number n, doing a bit-wise AND of n and n - 1 flips the least-significant 
     * 1-bit in n to 0. 
     * Why? Consider the binary representations of n and n−1.
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0){
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
    /**
     * Counting Bits
     * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), 
     * ans[i] is the number of 1's in the binary representation of i.
     * Strategy:
     * Last set bit is the rightmost set bit. Setting that bit to zero with the bit trick, x &= x - 1, 
     * leads to the following transition function: P(x) = P(x & (x - 1) +1)
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for(int i = 1; i <= n; ++i){
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }
    /**
     * Reverse Bits - Reverse bits of a given 32 bits unsigned integer.
     */
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;
        for(int i = 0; i < 32; i++){
            ans <<= 1;
            ans = ans | (n & 1);
            n >>= 1;
        }
        return ans;
    }
    /**
     * Missing Number
     * Given an array nums containing n distinct numbers in the range [0, n], 
     * return the only number in the range that is missing from the array.
     * 
     * Strategy:
     * We can harness the fact that XOR is its own inverse to find the missing element in linear time.
     * Because we know that nums contains n numbers and that it is missing exactly one number on the range [0..n-1], 
     * we know that nn definitely replaces the missing number in nums. 
     * If we initialize an integer to nn and XOR it with every index and value, we will be left with the missing number.
     */
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for(int i = 0; i < nums.length; i++){
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}