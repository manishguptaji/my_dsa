package com.example.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*
import java.util.Stack

import java.util.HashMap
import kotlin.math.floor


class StackAndQueue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_and_queue)

        // queue and stack
        print(queueAndStack())

        // implement a stack using queue
        stackUsingQueue()

        //https://www.youtube.com/watch?v=aMPXhEdpXFA&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=3
        //duplicate brackets in a balanced equation
        print(isDuplicate("(a+b) * (c+d)"))

        //Valid Parentheses simple
        print(isValidParenthesesSimple("()())"))

        //20 valid parenthesis
        print(isValidParentheses("(){}[]"))




        //nGETR = next greater element to right
        //https://www.youtube.com/watch?v=rSf9vPtKcmI&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=8
        print(nGETR(arrayOf(5,6,2,4,10,4,7)).contentToString())



        //496 - Next Greater Element I
        nextGreaterElement(arrayOf(4,1,2), nums2 = arrayOf(1,3,4,2))


        // stack using deque
        implementStackInDeque()


        //https://practice.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1
        val queue1 : Queue<Int> = LinkedList<Int>()
        queue1.add(5)
        queue1.add(4)
        queue1.add(3)
        queue1.add(2)
        queue1.add(1)
        reverseKQueue(3, queue1)

        //next smaller element : https://www.geeksforgeeks.org/next-smaller-element/
        print(nextSmallerElement(arrayOf(3,4,2,5,6,1,4,2)).contentToString())

        //https://www.youtube.com/watch?v=0BsPlzqksZQ&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=10
        print(stockSpan(arrayOf(2,5,9,3,1,12,6)).contentToString())

        //reverse first K elements of stack
        reverseStack()


        //==========================================================================



        //sliding window maximum
        //https://www.youtube.com/watch?v=tCVOQX3lWeI&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=14
        slidingWindowMax(arrayOf(4,6,8,2,1,5,9,4,0,3,2), 4)



        //==========================================================================




        //https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
        val stack4 = Stack<Int>()
        stack4.push(45)
        stack4.push(25)
        stack4.push(2)
        stack4.push(32)
        stack4.push(12)
        // question is to sort the stack
        //Pop all elements first, then keep poping if peek is > no x.
        //and the insert the no. x itself.
        popAllElements(stack4)
        print(stack4.toString())



        //56 - merge intervals
        print(mergeIntervals(arrayOf(arrayOf(1,4), arrayOf(2,3), arrayOf(8,10), arrayOf(15,18))))
        //print(mergeIntervals(arrayOf(arrayOf(1,4), arrayOf(4,5))).toString())


        //https://www.geeksforgeeks.org/the-celebrity-problem/
        //without stack ->
        print(findTheCeleb(arrayOf(arrayOf(0, 0, 1, 0), arrayOf(0, 0, 1, 0), arrayOf(0,1,0,0), arrayOf(0,0,1,0))))
        //with stack ->
        print(findTheCelebStack(arrayOf(arrayOf(0, 0, 1, 0), arrayOf(0, 0, 1, 0), arrayOf(0,1,0,0), arrayOf(0,0,1,0))))



        //https://www.geeksforgeeks.org/length-of-the-longest-valid-substring/
        //with out stack
        print(longestValidString("()(()))))").toString())
        //with stack
        longestValidStringStack("()(()))))")


        //stack remove extra parenthesis
        removeExtraBrackets("(()(()))")

        //delete mid element of stack
        //https://www.youtube.com/watch?v=mxrBibfna7A&t=695s
        var stack = Stack<Int>()
        stack.push(1)
        stack.push(2)
        stack.push(3)
        stack.push(4)
        stack.push(5)
        deleteMid(stack, stack.size, 0)

        //reverse a queue
        val queue : Queue<Int> = LinkedList<Int>()
        queue.add(5)
        queue.add(4)
        queue.add(3)
        queue.add(2)
        reverseQueue(queue)

        //https://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
        val stack3 = Stack<Int>()
        stack3.push(15)
        stack3.push(4)
        stack3.push(93)
        stack3.push(2)
        stack3.push(22)
        stack3.push(10)
        stack3.push(32)
        findMidStack(stack3)
        print(stack3)

        // find min element in stack
        findMinInStack(stack4)
    }

    fun mergeIntervals(arr: Array<Array<Int>>): String {
        val stack = Stack<Array<Int>>()

        for(item in arr) {
            if(stack.isEmpty()) {
                stack.push(item)
                continue
            }

            var peek = stack.peek()

            if(item[0] <= peek[1]) {
                stack.pop()
                val greaterTime = maxOf(item[1], peek[1])
                stack.push(arrayOf(peek[0], greaterTime))
            } else {
                stack.push(item)
            }
        }

        return stack.toString()
    }

    private fun findTheCelebStack(arr: Array<Array<Int>>) : Int {
        val row = arr.size - 1
        var potentialCeleb = -1
        val stack = Stack<Int>()

        for(i in 0..row) {
            stack.push(i)
        }

        while(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()

            if(arr[a][b] == 1) {
                //if a knows b -> a is not a celeb, push back b
                stack.push(b)
                potentialCeleb = b
            } else {
                //if a does not knows b -> b is not a celeb, push back a
                stack.push(a)
                potentialCeleb = a
            }
        }

        if(stack.isEmpty())
            return -1

        loop@ for(i in 0..row) {
            if(i != potentialCeleb && arr[i][potentialCeleb] == 0) {
                potentialCeleb = -1
                break@loop
            }
        }

        return if(potentialCeleb == -1) -1 else 1

    }

    private fun findTheCeleb(arr: Array<Array<Int>>) : Int {
        val row = arr.size - 1
        val col = arr[0].size -1
        var potentialCeleb = -1
        var counter = 0

        outerLoop@ for(i in 0..row) {
            counter = 0
            for(j in 0..col) {
                if(arr[i][j] == 0) {
                    counter++
                } else {
                    continue@outerLoop
                }

                if(counter == col + 1) {
                    potentialCeleb = i
                    break@outerLoop
                }
            }
        }

        if(potentialCeleb == -1)
            return -1

        loop@ for(i in 0..row) {
            if(i != potentialCeleb && arr[i][potentialCeleb] == 0) {
                potentialCeleb = -1
                break@loop
            }
        }

        return if(potentialCeleb == -1) -1 else 1

    }

    fun slidingWindowMax(arr: Array<Int>, k : Int) : IntArray {
        //https://www.youtube.com/watch?v=tCVOQX3lWeI&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=14
        //todo this solution is wrong, follow above given link to solve it.
        val size = arr.size
        val counter = size - k
        val result = IntArray(counter + 1)
        var j = 0
        var i = 0
        var maxNo = arr[i]

        while(j < result.size - 1) {
            val temp = arr[i]
            maxNo = maxOf(temp, maxNo)
            result[j] = maxNo
            i++
            if(i >= k)
                j++
        }

        return result
    }

    fun longestValidStringStack(s: String) : Int {
        val stack = Stack<Char>()
        var popCount = 0
        for(item in s) {
            if(item == '(') {
                stack.push(item)
            } else {
                if(!stack.isEmpty()) {
                    stack.pop()
                    popCount++
                }
            }
        }

        return popCount * 2
    }


    fun longestValidString(str: String) : Int {

        //brute force is to find all the sub string of str
        // and then check for each if it is valid or not

        return 0
    }

    var min = 0
    fun findMinInStack(stack: Stack<Int>) {
        if(stack.isEmpty())
            return

        val x = stack.pop()
        min = minOf(x, min)
        findMinInStack(stack)
        stack.push(x)
    }

    fun popAllElements(stack: Stack<Int>) {
        if(stack.isEmpty())
            return

        var x = stack.pop()
        popAllElements(stack)
        sortStack(stack, x)
    }

    fun sortStack(stack: Stack<Int>, x: Int) {
        if(stack.isEmpty()) {
            stack.push(x)
            return
        }

        if(stack.peek() > x) { //pop all top elements if they are > next number
            val y = stack.pop()
            sortStack(stack, x)
            stack.push(y) // jis order me pop kiye the, same order me push hoga
        } else {
            stack.push(x)
            return
        }
    }

    fun nextSmallerElement(arr: Array<Int>) : IntArray {
        val size = arr.size
        val result = IntArray(size)
        val stack = Stack<Int>()

        for((index, value) in arr.withIndex()) { // stored index in stack instead of value because we started for loop
            // from left this time.
            if(stack.isEmpty()) {
                stack.push(index)
            } else {
                while(!stack.isEmpty() && arr[stack.peek()] >= value) {
                    result[stack.peek()] = value
                    stack.pop()
                }
                stack.push(index)
            }
        }

        while(!stack.isEmpty()) {
            val popedValue = stack.pop()
            result[popedValue] = -1
        }

        return result
    }

    private fun findMidStack(stack3: Stack<Int>): Int {
        var mid = 0
        val size = stack3.size
        val counter = (1 + size)/2

        for(i in 0..counter - 1) {
            mid = stack3.pop()
        }

        return mid
    }

    private fun reverseKQueue(k: Int, queue: Queue<Int>) {
        if(queue.isEmpty())
            return

//        if(k == 0)
//            Log.e("queue", queue.toString())

        val stack = Stack<Int>()

        for(i in 0..k - 1) {
            stack.push(queue.remove())
        }

        //Log.e("queue", queue.toString())
    }

    private fun reverseStack() {
        val stack = Stack<Int>()
        stack.push(5)
        stack.push(4)
        stack.push(3)
        stack.push(2)
        stack.push(1)

        //Log.e("stack1", stack.toString())

        reverseStack(stack)
        //see this tomorrow

        //Log.e("stack2", stack.toString())
    }

    private fun reverseStack(stack: Stack<Int>) {
        if(stack.isEmpty())
            return

        val x = stack.peek()
        stack.pop()
        reverseStack(stack)
        insertAtBottom(stack, x)
    }

    private fun insertAtBottom(stack: Stack<Int>, n: Int) {
        if(stack.isEmpty()) {
            stack.push(n)
            return
        }

        // remove the top element
        val topElement: Int = stack.pop()
        // insert the element at the bottom of the stack
        insertAtBottom(stack, n)
        // add the top element to the stack
        stack.push(topElement)
    }

    private fun implementStackInDeque() {
        val deque = ArrayDeque<Int>()

        pushInDeque(5, deque)
        pushInDeque(5, deque)
        pushInDeque(5, deque)
        pushInDeque(5, deque)

        popFromDeque(deque)

        peekFromDeque(deque)

        isEmptyDeque(deque)
    }

    fun pushInDeque(n: Int, deque: ArrayDeque<Int>) {
        deque.add(n)
    }

    fun popFromDeque(deque: ArrayDeque<Int>) {
        deque.removeLast()
    }

    fun peekFromDeque(deque: ArrayDeque<Int>) : Int {
        return deque.last()
    }

    fun isEmptyDeque(deque: ArrayDeque<Int>) : Boolean {
        return deque.isEmpty()
    }

    private fun reverseQueue(q: Queue<Int>) {
        if (q.isEmpty()) {
            return
        }

        val x: Int = q.peek() ?: 0
        q.remove()
        reverseQueue(q)
        q.add(x)
        //Log.e("queue1", q.toString())
    }

    private fun stackUsingQueue() {
        val queue : Queue<Int> = LinkedList<Int>()

        push(5, queue)
        push(3, queue)
        push(6, queue)
        push(1, queue)

        //top(queue)
        empty(queue)
    }

    private fun push(x: Int, queue: Queue<Int>) {
        queue.add(x)
    }

    private fun empty(queue: Queue<Int>) : Boolean {
        return queue.isEmpty()
    }

    private fun queueAndStack() {
        val queue: Queue<Int> = LinkedList<Int>()
        queue.add(3)
        queue.add(2)
        queue.add(1)

        //this will give 3
        queue.poll() // .poll is similar to .remove()

        //Log.e("queue", queue.toString())

        val stack = Stack<Int>()
        stack.push(3)
        stack.push(2)
        stack.push(1)

        //this will give 1
        stack.pop()

        //Log.e("stack", stack.toString())
    }

    private fun deleteMid(stack: Stack<Int>, size: Int, current: Int) {
        if(stack.isEmpty())
            return

        var x = stack.peek()
        stack.pop()

        deleteMid(stack, size, current + 1)

        //put all items back except for mid one
        if(current != (size/2) + 1) {
            stack.push(x)
        }
    }

    private fun removeExtraBrackets(s: String): String {
        val stack = ArrayDeque<Char>()
        val sb = StringBuilder()

        for(i in 0..s.length - 1) {
            val str = s.get(i)
            if(stack.isEmpty()) {
                stack.add(str)
                sb.append(str)
            } else {
                val top = stack.last()
                if(top == '(' && str == '(' || top == ')' && str == ')') {
                    continue
                } else {
                    stack.add(str)
                    sb.append(str)
                }
            }
        }

        return sb.toString()
    }

    fun stockSpan(arr: Array<Int>): IntArray {
        //this is next greater element on left
        val size = arr.size
        val result = IntArray(size)
        val stack = ArrayDeque<Int>()
        var topIndex = 0

        for((index, item) in arr.withIndex()) {
            if(stack.isEmpty()) {
                stack.add(item)
                topIndex = index
                result[index] = index + 1
            } else {
                while(!stack.isEmpty() && stack.last() <= item) {
                    stack.removeLast()
                    topIndex--
                }
                if(stack.isEmpty()) {
                    result[index] = index + 1
                } else {
                    result[index] = index - topIndex
                }
                stack.add(item)
                topIndex = index
            }
        }

        return result
    }

    @SuppressLint("NewApi")
    fun nextGreaterElement(nums1: Array<Int>, nums2: Array<Int>): IntArray {
        // use hash map for storing next greater element in it for every no.
        val result = IntArray(nums1.size)
        val stack = Stack<Int>()
        val map: MutableMap<Int, Int> = HashMap()

        for (num in nums2) {
            while (!stack.isEmpty() && num > stack.peek())
                map[stack.pop()] = num
            stack.push(num)
        }

        var i = 0
        for (num in nums1)
            result[i++] = map.get(num) ?: -1
        return result
    }

    fun nGETR(arr: Array<Int>) : IntArray {
        val size = arr.size
        val result = IntArray(arr.size)
        val stack = ArrayDeque<Int>()

        //operations are
        //pop if stack top is less
        //add top in result array
        //then push in stack

        // run the for loop backwards

        for(i in size - 1 downTo 0) {
            if(stack.isEmpty()) {
                result[i] = - 1
            } else {
                while(!stack.isEmpty() && stack.last() <= arr[i]) {
                    //pop all the elements which are less the the number
                        // that will leave the greater no. in stack which will be tha answer
                    stack.removeLast()
                }
                if(!stack.isEmpty())
                    result[i] = stack.last()
                else
                    result[i] = - 1
            }

            stack.add(arr[i])
        }

        return result
    }

    private fun isValidParentheses(s: String): Boolean {
        //same logic as previous question
        //if opening bracket -> we push
        //else we look for its counter part on top and pop all of them.
        val stack = ArrayDeque<Char>()
        for(i in 0..s.length - 1) {
            val str = s.get(i)
            if(str == '(' || str == '{' || str == '[' ) {
                stack.add(s.get(i))
            }
            else if(str == ')') {
                if(!stack.isEmpty() && stack.last() == '(') {
                    stack.removeLast()
                } else {
                    return false
                }
            }
            else if(str == '}') {
                if(!stack.isEmpty() && stack.last() == '{') {
                    stack.removeLast()
                } else {
                    return false
                }
            }
            else if(str == ']') {
                if(!stack.isEmpty() && stack.last() == '[') {
                    stack.removeLast()
                } else {
                    return false
                }
            }
        }

        return stack.isEmpty()
    }

    private fun isValidParenthesesSimple(s: String): Boolean {
        //idea is when we get ) as input in stack
        // then stack should have ( on top to make it balance
        var stack = ArrayDeque<Char>()
        for(i in 0..s.length - 1) {
            if(s.get(i) == ')') {
                if(stack.isNotEmpty() && stack.last() == '(') {
                    // if we get ) as input, then pop all the (
                    stack.removeLast()
                } else {
                    //if top stack is not ( then brackets are not balanced
                    return false
                }
            } else {
                //push the (
                stack.add(s.get(i))
            }
        }

        //in the end stack should be empty

        return stack.isEmpty()
    }

    fun isDuplicate(str: String) : Boolean {
        //idea is between every ( and ) there should be an expression, because its given that
        // expression is balanced.
        var stack = ArrayDeque<Char>()
        for(i in 0..str.length - 1) {
            if(str.get(i) == ')') {
                if(stack.first() == '(') {
                    // means there is no expression between opening and closing brackets
                    // so there are duplicate brackets.
                    return true
                } else {
                    while(stack.first() != '(') { // pop all the expression between ( and )
                        stack.removeFirst()
                    }
                    //then pop the opening bracket as well
                    stack.removeFirst()
                }
            } else {
                stack.addFirst(str.get(i))
            }
        }

        //in the end stack will be empty

        return false
    }
}