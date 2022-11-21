package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Myqueue {
    Stack<Integer> pollstack;
    Stack<Integer> offerstack;

    public Myqueue() {
            offerstack = new Stack<Integer>();
            pollstack = new Stack<Integer>();
        }

        public void push(int x) {
            offerstack.push(x);
            return;
        }

        public int pop() {
            if(!pollstack.empty()){
                return pollstack.pop();
            }
            else {
                while(!offerstack.empty())
                    pollstack.push(offerstack.pop());
                return pollstack.pop();
            }
        }

        public int peek() {
            if(pollstack.empty()){
                while(!offerstack.empty())
                    pollstack.push(offerstack.pop());
            }
            return pollstack.peek();
        }

    //你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
        public boolean empty() {
            if(offerstack.empty() && pollstack.empty())
                return true;
            else return false;
        }
}

class MyStack {
    Queue<Integer> mainQueue;
    Queue<Integer> subQueue;

    public MyStack() {
        mainQueue = new LinkedList<>();
        subQueue = new LinkedList<>();
    }

    public void push(int x) {
        mainQueue.offer(x);
    }

    public int pop() {
        while (mainQueue.size()>1){
            subQueue.offer(mainQueue.poll());
        }
        int x = mainQueue.poll();
        while (!subQueue.isEmpty()){
            mainQueue.offer(subQueue.poll());
        }
        return x;
    }

    public int top() {
        while (mainQueue.size()>1){
            subQueue.offer(mainQueue.poll());
        }
        int x = mainQueue.peek();
        subQueue.offer(mainQueue.poll());

        while (!subQueue.isEmpty()){
            mainQueue.offer(subQueue.poll());
        }
        return x;
    }

    public boolean empty() {
        return mainQueue.isEmpty();
    }
}

