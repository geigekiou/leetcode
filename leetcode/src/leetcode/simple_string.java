package leetcode;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class simple_string {
    public static String addbinary(String a, String b){
        int leng = a.length()-b.length()>=0?b.length():a.length();
        StringBuilder rsb = new StringBuilder();
        int flag = 0;
        int i = 1;
        System.out.println("leng = "+leng);
        while( i <= leng) {
            switch(a.charAt(a.length()-i)-'0' + b.charAt(b.length()-i)-'0'){
                case 0:
                    rsb.insert(0,(char)('0'+flag));
                    flag = 0;
                    break;
                case 1:
                    if(flag==0) rsb.insert(0,'1');
                    else{
                        rsb.insert(0,'0');
                        flag=1;
                    }
                    break;
                case 2:
                    if(flag==0) {rsb.insert(0,'0'); flag=1;}
                    else{
                        rsb.insert(0,'1');
                        flag=1;
                    }
                    break;
            }
            i++;
        }
        System.out.println("字符串："+rsb.toString()+"   这是第一阶段的flag:"+flag);

        if(a.length()!=b.length()){
            rsb.insert(0,a.length()>leng?a.substring(0,a.length()-leng):b.substring(0,b.length()-leng));
            System.out.println(rsb.toString()+"  flag:"+flag);

            if(flag!=0){
                for (int j = a.length()-b.length()>0?a.length()-leng-1:b.length()-leng-1; j >= 0 ; j--) {
                    if(rsb.toString().charAt(j)-'0'==0 && flag==1){
                        rsb.setCharAt(j,'1');
                        flag=0;
                        break;
                    }
                    else {
                        rsb.setCharAt(j,'0');
                        flag = 1;
                    }
                }
                }
            }

        if(rsb.length() == 0){
            return "0";
        }

        if(flag==1)
            rsb.insert(0,'1');

        return rsb.toString();
}

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i<=j){
            while(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }
            while(!Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }
            if(i>j) break;

            if(s.charAt(i) != s.charAt(j) &&
                    Character.toLowerCase(s.charAt(i))!= s.charAt(j) &&
                    Character.toUpperCase(s.charAt(i))!= s.charAt(j)){
                return false;
            }
            else{
                i++;
                j--;
            }
        }

        return true;
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {

        int min = 2147483647;
        List mylist2 = Arrays.asList(list2);
        List mylist1 = Arrays.asList(list1);
        Map<String,Integer> resmap = new HashMap<>();

        for (Object str : mylist1) {
            if(mylist2.contains(str) && mylist2.indexOf(str) + mylist1.indexOf(str) <= min){
                resmap.put((String) str,mylist2.indexOf(str) + mylist1.indexOf(str));
                min = mylist2.indexOf(str) + mylist1.indexOf(str);
            }
        }

        List<String> reslist = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : resmap.entrySet()) {
                         if(min == entry.getValue()){
                             reslist.add(entry.getKey());
                         }
        }
        String[] restr = new String[reslist.size()];
        for (int i = 0; i < reslist.size(); i++) {
            restr[i] = reslist.get(i);
        }
        return restr;
    }

    public static boolean isIsomorphic(String s, String t){
        if(s.length()!=t.length()) return false;
        Map<Character, Character> mymap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!mymap.containsKey(s.charAt(i)) && !mymap.containsValue(t.charAt(i))) {
                mymap.put(s.charAt(i), t.charAt(i));
            }
            else { //badc baba
                try{
                    if(mymap.get(s.charAt(i))!= t.charAt(i)){
                        return false;
                    }
                }
                catch (Exception e){
                    return false;
                }
            }
        }
        return true;
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder num1bd = new StringBuilder(num1);
        StringBuilder num2bd = new StringBuilder(num2);
        StringBuilder res = new StringBuilder();
        int carry = 0;
        num1bd.reverse();
        num2bd.reverse();
        int int1;
        int int2;

        int maxlen = Math.max(num1bd.length(),num2bd.length());

        for (int i = 0; i < maxlen; i++) {
            if(i<num1.length())
                int1 = Integer.parseInt(num1bd.charAt(i)+"");
            else int1 = 0;

            if(i<num2.length())
                int2 = Integer.parseInt(num2bd.charAt(i)+"");
            else int2 = 0;
            res.insert(i,(carry+int1+int2)%10+"");
            carry = (carry+int1+int2)/10;
        }

        if(carry!=0){
            res.insert(res.length(),"1");
        }
        System.out.println(res.reverse().toString());
        return res.reverse().toString();
    }

    public static String simplifyPath(String path) {
        String resstr  = new String();
        List<String> ressb = new ArrayList<>();

        if(!path.startsWith("/")) return null;
        String[] strarr = path.split("/");
        for (int i = 0; i < strarr.length; i++) {
            if(strarr[i].equals("") || strarr[i].equals(".")) continue;
            else if(strarr[i].equals("..")){
                if(ressb.size()==0) continue;
                else ressb.remove(ressb.size()-1);
            }
            else {
                ressb.add("/"+strarr[i]);
            }
        }
        if(ressb.size()==0) ressb.add("/");
        for (int i = 0; i < ressb.size(); i++) {
            resstr += ressb.get(i);
        }
        System.out.println(resstr);
        return resstr;
    }

    public boolean repeatedSubstringPattern(String s) {

        return true;
    }

    public int strStr(String haystack, String needle) {
        //a a b a a b a a f a
        //a a b a a f
        if(needle.length()==0) return 0;
        int[] prefix = new int[needle.length()];
        int  j = 0;
        getNext(prefix, needle);
        for(int i = 0; i<haystack.length();i++){
            while(j>=0 && haystack.charAt(i) != needle.charAt(j+1)){
                j = prefix[j];
            }
            if(haystack.charAt(i)==needle.charAt(j+1)){
                j++;
            }
            if(j==needle.length()-1){
                return (i-needle.length()+1);
            }
        }
        return j;
    }
//要在文本串：aabaabaafa 中查找是否出现过一个模式串：aabaaf

    public static void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 0; i < next.length; i++) {
            while(j>=0 && s.charAt(j+1)!=s.charAt(i)){
                j = next[j];
            }
            if(s.charAt(j+1)==s.charAt(i))
                j++;
            next[i] = j;
        }
    }

    public static boolean isValid(String s) {
        // ([{]})
        List<Character> left = new ArrayList<>();
        left.add('(');
        left.add('[');
        left.add('{');
        List<Character> right = new ArrayList<>();
        right.add(')');
        right.add(']');
        right.add('}');
        List<String> ppipei = new ArrayList<>();
        ppipei.add("()");
        ppipei.add("[]");
        ppipei.add("{}");

        Stack<Character> stackL = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(left.contains(s.charAt(i))){
                stackL.push(s.charAt(i));
            }
            else if(right.contains(s.charAt(i))){
                if(!ppipei.contains(""+stackL.pop()+s.charAt(i)))
                    return false;
            }
        }
        if(!stackL.empty()) return false;
        return true;
    }

    public static String removeDuplicates(String s) {
        //"abbaca"
        StringBuilder resstr = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(stack.empty()){
                stack.push(s.charAt(i));
                continue;
            }
            if(stack.peek() == s.charAt(i)){
                stack.pop();
            }
            else {
                stack.push(s.charAt(i));
            }
        }
        for (int i = 0; i < stack.size(); i++) {
            resstr.append(stack.pop());
        }
        return resstr.reverse().toString();
    }

    public static int evalRPN(String[] tokens) {
        List<String> optoken = new ArrayList<>();
        Collections.addAll(optoken,"+","-","*","/");
        int result = 0, op1=0, op2 = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if(optoken.contains(tokens[i])){
                op1 = stack.pop();
                op2 = stack.pop();
                switch (tokens[i]){
                    case "+":{
                        op1 += op2;
                        stack.push(op1);
                        break;
                    }
                    case "-":{
                        op1 -= op2;
                        stack.push(op1);
                        break;
                    }
                    case "*":{
                        op1 *= op2;
                        stack.push(op1);
                        break;
                    }
                    case "/":{
                        op1 /= op2;
                        stack.push(op1);
                        break;
                    }
                }
            }
            else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        result = stack.pop();
        System.out.println(result);
        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int winlen = len-k+1;
        //1,3,-1,-3,5,3,6,7 【4】
        int[] resarr = new int[winlen];
        Deque<Integer> winque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) { //5 3 2  4
            //清理超期的元素：不是通过元素而是下标
            if(!winque.isEmpty() && winque.peek() == i-k){
                winque.poll();
            }
            //维护单调性
            while(!winque.isEmpty() && nums[i] >= nums[winque.peekLast()]){
                winque.pollLast();
            }
            winque.offer(i);
            if(i+1>=k)
                resarr[i+1-k] = nums[winque.peek()];
        }
        return resarr;
    }

    public static int[] topKFrequent(int[] nums, int k) {
//        输入: nums = [1,1,1,2,2,3], k = 2
//        输出: [1,2]
        int[] resarr = new int[k];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }
            else {
                map.put(nums[i],1);
            }
        }

        PriorityQueue<Integer> pqueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1)-map.get(o2);
            }
        });

        for (Integer key : map.keySet()) {
            if(pqueue.size()<k)
                pqueue.offer(key);
            else {
                if(map.get(pqueue.peek())<map.get(key)){
                    pqueue.poll();
                    pqueue.offer(key);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            resarr[i] = pqueue.poll();
        }

        return resarr;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] resarr = new int[temperatures.length];
        Deque<Integer> monotostack = new ArrayDeque<>();
        int x = 0;
        monotostack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            if(temperatures[monotostack.peek()] >= temperatures[i]){
                monotostack.push(i);
            }
            else {
                while(!monotostack.isEmpty() && temperatures[monotostack.peek()] < temperatures[i]){
                    x = monotostack.pop();
                    resarr[x] = i - x;
                }
                monotostack.push(i);
            }
        }

        return resarr;
    }
//    输入: temperatures = [73,74,75,71,69,72,76,73]
//    输出: [1,1,4,2,1,1,0,0]

//    输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
//    输出：[-1,3,-1]
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resarr = new int[nums1.length];
        int[] subarr = new int[nums2.length];
        int x = 0;
        Deque<Integer> monotonousStack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while(!monotonousStack.isEmpty() && nums2[monotonousStack.peek()] < nums2[i]){
                x = monotonousStack.pop();
                subarr[x] = nums2[i];
            }
            monotonousStack.push(i);
        }

        while (!monotonousStack.isEmpty()){
            subarr[monotonousStack.pop()] = -1;
        }

        for (int j=0 ; j < nums1.length; j++) {
            for (int i = 0; i < nums2.length; i++) {
                if(nums2[i] == nums1[j]){
                    resarr[j] = subarr[i];
                }
            }
        }
        return resarr;
    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int x = 0;
        Arrays.fill(res, Integer.MIN_VALUE);
        Deque<Integer> monotonousStack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while(!monotonousStack.isEmpty() && nums[monotonousStack.peek()] < nums[i]){
                x = monotonousStack.pop();
                res[x] = nums[i];
            }
            monotonousStack.push(i);
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int begin = 0, temp = 0;

        for (int k = res.length-1; k >= 0; k--) {
            if(res[k] == Integer.MIN_VALUE){
                temp = nums[k];
                if(temp == max) break;
                else {
                    for (int j = begin; j < nums.length; j++) {
                        if(nums[j] > temp){
                            res[k] = nums[j];
                            begin = j;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < res.length; i++) {
            if(res[i] == Integer.MIN_VALUE){
                res[i] = -1;
            }
        }

        return res;
    }

    public static int trap(int[] height) {
        if(height.length<=2) return 0;
        int result = 0;
        Deque<Integer> upStack = new ArrayDeque<>();
        int bottom = 0;
        int width = 0;
        int surface = 0;
        int righthigh = 0;
        int lefthigh = 0;

        upStack.push(0);
        for (int index = 1; index < height.length; index++) { // 4 2 2 3 4
            righthigh = height[index];
            if (height[index] < height[upStack.peek()])
                upStack.push(index);
            else if (height[index] == height[upStack.peek()]) {
                upStack.pop();
                upStack.push(index);
            }
            else {
                while (!upStack.isEmpty() && height[index] > height[upStack.peek()]) {
                    bottom = upStack.pop();
                    if(!upStack.isEmpty()){
                        lefthigh = height[upStack.peek()];
                        surface = Math.min(righthigh, lefthigh) - height[bottom];
                        width = index - upStack.peek()-1;
                        result += surface * width;
                    }
                }
                upStack.push(index);
            }
        }
//4,2,0,3,2,4,3,4
        return result;
    }

    public static int largestRectangleArea(int[] heights) {
        if(heights.length <= 1) return heights[0];
        int[] heights2 = new int[heights.length+2];
        heights2[0] = 0;
        for (int i = 0; i < heights.length; i++) {
            heights2[i+1] = heights[i];
        }
        heights2[heights2.length-1] = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        stack.push(0);
        int highAtIndex = 0;
        int left = 0;
        int width = 0;
        int surface = 0;
        res = heights[0];
        //2,1,5,6,2,3
        for (int i = 1; i < heights2.length; i++) {
            highAtIndex = heights2[i];
            if(heights2[stack.peek()] <= highAtIndex){
                stack.push(i);
            }
            else{
                while (!stack.isEmpty() && heights2[stack.peek()] > highAtIndex){
                    int mid = stack.peek();
                    stack.pop();
                    left = stack.peek();
                    int right = i;
                    int w = right - left - 1;
                    int h = heights2[mid];
                    res = Math.max(res, w * h);
                }
                stack.push(i);
            }
        }

        return res;
    }

    public static String removeDuplicateLetters(String s) {
        //输入：s = "cbacdcbc"
        //输出："acdb"
        //"bcabc"
        //"bcbac"
        if(s.length()<=1) return s;
        String res = new String();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        char ch;
        stack.push(0);
        set.add(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if(map.containsKey(ch)) map.put(ch,map.get(ch)+1);
            else map.put(ch,1);
        }
        map.put(s.charAt(stack.peek()),map.get(s.charAt(stack.peek()))-1);

        for (int i = 1; i < s.length(); i++) {
            ch = s.charAt(i);
            map.put(ch,map.get(ch)-1);

            if(ch > s.charAt(stack.peek()) && !set.contains(ch)){
                stack.push(i);
                set.add(ch);
            }
            else if(ch < s.charAt(stack.peek()) && !set.contains(ch)){
                while (!stack.isEmpty() && ch < s.charAt(stack.peek())){
                    if(map.get(s.charAt(stack.peek())) > 0) {
                        set.remove(s.charAt(stack.pop()));
                    }
                    else break;
                }
                if(!set.contains(ch)){
                    set.add(ch);
                    stack.push(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(s.charAt(stack.pop()));
        }
        System.out.println(sb.reverse().toString());
        return res;
    }

    public static String removeKdigits(String num, int k) {
        String res = "0";
        if(k == num.length()) return res;
//        输入：num = "1432219", k = 3
//        输出："1219"
        Deque<Integer> upStack = new ArrayDeque<>();
        int len = num.length() - k;
        char charAtIndex = num.charAt(0);
        upStack.push(0);

        for (int i = 1; i < num.length(); i++) {
            charAtIndex = num.charAt(i);
            if(num.length()-i + upStack.size() > len){
                if(charAtIndex>= num.charAt(upStack.peek())){
                    if(upStack.size() < len)
                        upStack.push(i);
                }
                else {
                    while ( !upStack.isEmpty() &&
                            charAtIndex < num.charAt(upStack.peek()) &&
                            num.length()-i + upStack.size() > len)
                    {
                        upStack.pop();
                    }
                    upStack.push(i);
                }
            }
            else {
                upStack.push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!upStack.isEmpty()){
            sb.append(num.charAt(upStack.pop()));
        }
        res = sb.reverse().toString();
        while (res.charAt(0) == '0' && res.length()!=1){
            res = res.substring(1);
        }
        return res;
    }

    public static boolean maxNumberCompare(int[] stack1, int p1, int[] stack2, int p2){
        if(p1 >= stack1.length) return false;
        if(p2 >= stack2.length) return true;
        if(stack1[p1]>stack2[p2]) return true;
        else if(stack1[p1]<stack2[p2]) return false;
        else return maxNumberCompare(stack1,p1+1, stack2, p2+1);
    }

    public static int[] marge_sort(int[] stack1, int[] stack2){
        int[] resarr = new int[stack1.length+stack2.length];
        int p1=0,p2=0;
        for (int i = 0; i < resarr.length; i++) {
            if(maxNumberCompare(stack1,p1,stack2,p2)){
                resarr[i] = stack1[p1++];
            }
            else resarr[i] = stack2[p2++];
        }
        return resarr;
    }

    public static int[] maxNumberSup(int k, int[] nums){
        int[] res = new int[k];
        int j = k-1;
        Deque<Integer> downStack = new ArrayDeque<>();
        int drop_num = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            while ( !downStack.isEmpty()
                    && drop_num > 0
                    && downStack.peek() < nums[i]){
                downStack.pop();
                drop_num--;
        }
            if(downStack.size()<k) downStack.push(nums[i]);
            else --drop_num;
        }
        while (!downStack.isEmpty()){
            res[j--] = downStack.pop();
        }
        return res;
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        nums1 = [3,4,6,5] 4
//        nums2 = [9,1,2,5,8,3] 6
//        k = 5
//        输出: [9, 8, 6, 5, 3]
        int[] res = new int[k];
        int len = Math.min(k,nums1.length);
        for (int p = Math.max(k-nums2.length,0); p <= len; p++) {
            int[] temp = marge_sort(maxNumberSup(p, nums1), maxNumberSup(k - p,nums2));
                if(maxNumberCompare(temp,0,res,0)){
                    res = temp;
                }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
}


