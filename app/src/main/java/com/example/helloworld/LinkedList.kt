package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.nio.file.Files.size

import org.w3c.dom.Node
import java.lang.StringBuilder
import java.util.*
import java.util.LinkedList


class LinkedList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linked_list)

        // two most important concepts are
        // fast and slow
         //and reversing a LL
        // most questions use these 2 concepts only


        //linked list basic operations
        val ll1 = LinkedList<Int>()
        ll1.add(1)
        ll1.add(2)
        ll1.add(3)
        ll1.add(4)
        ll1.addFirst(0)
        ll1.addLast(5)
        basicOperations(ll1)

        main()

    }

    //todo paste this entire code in kotlin play ground
    fun main() {
        val myLL = MyLinkedList()
        myLL.addFirstElement(3)
        myLL.addFirstElement(2)
        myLL.addFirstElement(1)
        myLL.addLast(4)
        myLL.addLast(5)

        myLL.reverseRecusrionHelper2()
        myLL.printMyLL()
    }

    class Node {
        var value : Int = 0
        var next : Node? = null
    }

    class MyLinkedList {
        var head: Node? = null
        var tail: Node? = null
        var size: Int = 0

        fun addFirstElement(value: Int) {
            var newNode = Node()
            newNode.value = value
            if(size == 0) {
                newNode.next = null
                head = newNode
                tail = newNode
            } else {
                newNode.next = head
                head = newNode
            }
            size++
        }

        fun printMyLL() {
            var temp = head
            while(temp != null) {
//                print(temp.value)
//                print(" ->>> ")
                Log.e(">>>" , temp.value.toString() + " ->" + "" )
                temp = temp?.next
            }
            print("Null")
        }

        fun printSize() {
            println()
            println("size is $size")
        }

        //without using size
        fun printMid() {
            var temp1 = head
            var temp2 = head

            while(temp2 != null && temp2?.next != null) {
                temp1 = temp1?.next
                temp2 = temp2?.next?.next
            }

            println(temp1?.value)
        }

        //with size
        fun printMidSize() {
            var temp = head
            var k = size/2

            for(i in 1..k) {
                temp = temp?.next
            }

            print(temp?.value)
        }

        fun addLast(value: Int) {
            var newNode = Node()
            newNode.value = value
            newNode.next = null
            if(size == 0) {
                head = newNode
                tail = newNode
            } else {
                tail?.next = newNode
                tail = newNode
            }
            size++
        }

        fun addAtIndex(value: Int, index: Int) {
            if(index == 0) {
                addFirstElement(value)
            } else if(index >= size) {
                addLast(value)
            } else {
                var newNode = Node()
                newNode.value = value
                var temp = head

                for(i in 1..index - 1) {
                    temp = temp?.next
                }

                newNode?.next = temp?.next
                temp?.next = newNode
                size++
            }
        }

        fun deleteFirst() {
            var firstNode = head
            head = head?.next
            firstNode?.next = null
            size--
        }

        fun deleteLast() {
            var node = head
            for(i in 1..size - 2) {
                node = node?.next
            }

            tail = node
            node?.next = null
            size--
        }

        fun deleteAt(index : Int) {
            if(index == 0) {
                deleteFirst()
            } else if(index >= size) {
                deleteLast()
            } else {
                var node = head
                for(i in 1..index - 1) {
                    node = node?.next
                }

                var nodeToDelete = node?.next
                node?.next = nodeToDelete?.next
                nodeToDelete?.next = null
                size--
            }
        }

        fun getNodeAtIndex(index: Int) : Node? {
            var temp = head

            for(i in 1..index) {
                temp = temp?.next
            }

            return temp
        }

        //idea is to swap the value for first and last node
        fun reverseViaData() {
            var left = 0
            var right = size - 1

            while(left < right) {
                val leftNode = getNodeAtIndex(left)
                val rightNode = getNodeAtIndex(right)

                val tempValue = leftNode?.value
                leftNode?.value = rightNode?.value ?: 0
                rightNode?.value = tempValue ?: 0

                left++
                right--
            }
        }

        //find the Kth element from end without using size
        fun findKelement(k : Int) {
            var fast = head
            var slow = head

            //place slow at 0 and fast at k
            for(i in 0..k) {
                fast = fast?.next
            }

            //when fast reaches the end, then slow will be at k, then return slow
            while(fast != tail) {
                fast = fast?.next
                slow = slow?.next
            }

            println()
            println(slow?.value)
        }

        // slow and fast pointer
        // if slow and fast meets i.e link list has a cycle
        fun hasCycle(): Boolean {
            var fast = head
            var slow = head
            while (fast != null && fast.next != null) {
                fast = fast.next?.next
                slow = slow?.next
                if (fast === slow) {
                    return true
                }
            }
            return false
        }


        // 202 - happy number
        fun happyNumber(n: Int): Boolean {
            //brute force is to use set
            //optimal solution is to use logic of linked list cycle

            var slow = n
            var fast = n

            //keep performing square of number until fast and slow are not equal
            // when fast and slow reaches the same no. i.e cycle exist in the happy no.
            do {
                slow = happyNoHelper(n)
                fast = happyNoHelper(happyNoHelper(n))
            } while (fast != slow)

            if(slow == 1) {
                return true
            }

            return false
        }

        fun happyNoHelper(n: Int) : Int {
            var result = 0
            var number = n
            while(number > 0) {
                var lastDigit = number % 10
                result += (lastDigit * lastDigit)
                number /= 10
            }

            return result
        }

        // slow and fast pointer
        // if slow and fast meets, then keep fast pointer there and move slow 1 by 1
        // when slow and fast move again...that will be the length of cycle
        fun lengthCycle(): Int {
            var fast = head
            var slow = head
            while (fast != null && fast?.next != null) {
                fast = fast?.next?.next
                slow = slow?.next
                if (fast === slow) {
                    // calculate the length
                    var temp = slow
                    var length = 0
                    do {
                        temp = temp?.next
                        length++
                    } while (temp !== slow)
                    return length
                }
            }
            return 0
        }

        //take 3 pointers approach, previous, next and current node
        fun reverseViaPointer() {
            var currentNode = head
            var previousNode: Node? = null

            while(currentNode != null) {
                var nextNode = currentNode?.next
                currentNode?.next = previousNode
                previousNode = currentNode
                currentNode = nextNode
            }

            //swap address of head and tail
            var temp = head
            head = tail
            tail = temp
        }

        fun mergeTwoSortedLL(ll1: LinkedList<Int>, ll2: LinkedList<Int>) {
            //https://www.youtube.com/watch?v=6vFEuO1pfJA&list=PL-Jc9J83PIiF5VZmktfqW6WVU1pxBF6l_&index=32
            // make new LL and keep adding values from ll1 and ll2
        }

        //two pointer approach
        fun removeDuplicates() {
            var i = head
            var j = i?.next
            var count = 1
            while(j != null) {
                if(i?.value == j?.value) {
                    j = j?.next
                    deleteAt(count)
                } else {
                    i = j
                    j = j?.next
                    count++
                }
            }
        }

        fun oddEvenList() {
            //https://www.youtube.com/watch?v=calrG6RJ9qI&list=PL-Jc9J83PIiF5VZmktfqW6WVU1pxBF6l_&index=39
            //above approach is brute force

            //optimised
            //take previous and current pointers
            //previous = null and current = head
            //then 1 by 1 compare previous and current and move forward
        }

        fun displayReverse() {
            val temp = head
            displayReverseRecursion(temp)
        }

        fun displayReverseRecursion(node: Node?) {
            if(node == null) {
                println(null)
                return
            }

            displayReverseRecursion(node?.next)
            println(node?.value)
        }

        var i = 0
        var j = 4
        fun reverseDataRecursive() {
            if(i >= j) {
                return
            }

            var nodeI = getNodeAtIndex(i)
            var nodeJ = getNodeAtIndex(j)

            var tempValue = nodeI?.value
            nodeI?.value = nodeJ?.value ?: 0
            nodeJ?.value = tempValue ?: 0
            i++
            j--
            reverseDataRecursive()
        }

        fun findIntersection(ll1: LinkedList<Int>, ll2: LinkedList<Int>) {
            //https://www.youtube.com/watch?v=B4aqNarb0QQ&list=PL-Jc9J83PIiF5VZmktfqW6WVU1pxBF6l_&index=54

            //check size of both list
            // eg size1 = 5 and size2 = 7
            // so ll1 ke head ko 2 baar next kardo

            //then 1 by 1 ll1 and ll2 ke heads ko next karte jao
        }

        //use a stack and push all nodes in it
        // then traverse the LL again and pop the stack 1 by 1
        //
        // optimised approach watch kunals video
        // his idea is to find the mid and reverse the 2nd half of the LL....then first half and 2nd half of LL will be same
        fun isPalindrome() : Boolean {
            var node = head
            var stack = Stack<Int>()

            while(node != null) {
                stack.push(node?.value)
                node = node?.next
            }

            node = head
            while(!stack.isEmpty()) {
                if(node?.value == stack.pop()) {
                    node = node?.next
                    continue
                } else {
                    return false
                }
            }

            return true
        }


        fun reverseRecusrionHelper2() {
            var prev = null
            var current = head
            reverseLL2(prev, current)

            var temp = head
            head = tail
            tail = temp
        }

        fun reverseLL2(p: Node?, c: Node?) {
            if(c == null) {
                return
            }

            var current = c?.next
            var prev = c

            reverseLL2(prev, current)
            if(current != null) {
                current?.next = prev
            }
        }

        var index = 3
        fun insertRecursionOne(value: Int) {
            head = insertRecursion(value, head)
        }

        //watch kunals video
        fun insertRecursion(value: Int, node: Node?) : Node? {
            if (index == 0) {
                val newNode = Node()
                newNode?.value = value
                newNode?.next = node
                size++
                return newNode
            }

            index--
            node?.next = insertRecursion(value, node?.next)
            return node
        }


        //143 re order the LL

        fun reorderLL(){
            //find mid
            // and then reverse the 2nd half of LL
            // then observe the LL
        }


        //92 - reverse a LL
        //idea is to use a stack.
        //jo element ko reverse karna hai unko stack me daal do
        // then 1 by 1 pop karo and add last karte jao
        fun partialReverse(left: Int, right: Int) {
            var temp = head
            var count = 0
            val stack = Stack<Node>()
            while(count < right) {
                if(count >= left) {
                    stack.push(temp)
                }
                temp = temp?.next
                count++
            }

            count = 0
            temp = head
            val myLL2 = MyLinkedList()
            while(temp != null) {
                if(count >= left && count < right) {
                    myLL2.addLast(stack.pop()?.value ?: 0)
                } else {
                    myLL2.addLast(temp?.value)
                }
                temp = temp?.next
                count++
            }

            println()
            myLL2.printMyLL()
        }

        fun deleteWithOutHead() {
            // jo node delete hona hai usme uske next node ka data copy kardo
            // then delete the next node
        }

        fun removeDuplicate() {
            var c = head
            var n = c?.next

            while(n != null) {
                if(c?.value == n?.value) {
                    n = n?.next
                } else {
                    c?.next = n
                    c = n
                    n = n?.next
                }
            }

        }
    }

    private fun basicOperations(ll1: LinkedList<Int>) {
        Log.e("ll1", ll1.toString())
        Log.e("ll1", ll1.first.toString())
        Log.e("ll1", ll1.last.toString())
        Log.e("ll1", ll1.size.toString())
        Log.e("ll1", ll1[2].toString())
        Log.e("ll1", ll1.remove().toString())
        Log.e("ll1", ll1.toString())
        ll1.add(3,6)
        Log.e("ll1", ll1.toString())
    }
}