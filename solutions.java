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
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock 
     * and choosing a different day in the future to sell that stock.
     */
    public int maxProfit(int[] prices) {
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
}