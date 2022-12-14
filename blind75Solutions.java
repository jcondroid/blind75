import java.util.HashMap;
import java.util.HashSet;

class blind75Solutions {
    /**
     * Arrays
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
    public int[] twoSum(int[] nums, int target) {
        // Assumptions: There is only one unique solution
        for(int i = 0; i < nums.length; i++){
            // traverse nums array
            for(int j = i+1; j < nums.length; j++){
                // 
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }
}