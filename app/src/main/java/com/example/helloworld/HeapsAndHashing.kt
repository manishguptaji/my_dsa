package com.example.helloworld

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

import java.util.PriorityQueue
import java.util.ArrayList

import java.util.HashMap
import java.util.Collections



class HeapsAndHashing : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heaps_and_hashing)

        val pq = PriorityQueue(Collections.reverseOrder<Int>())
        val pq2 = PriorityQueue { a: Pair<Int, Int>, b: Pair<Int, Int> -> b.first - a.first }


        //find duplicate elements - II
        //https://www.youtube.com/watch?v=jMwLFkI2Zbc&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=7
        findDuplicates(arrayOf(1,1,2,2,2,3,5), arrayOf(1,1,1,2,2,4,5))

        //https://www.youtube.com/watch?v=rb73tdVFjYE&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=8
        //todo important concept
        longestSequence(arrayOf(10,5,9,1,11,8,6,15,3,12,2))

        //k - largest elements
        //priority queue
        //https://www.youtube.com/watch?v=taL2G6jDLog&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=12
        findKLargestElementsBruteForce(arrayOf(4,7,3,1,6,3,4,9,10,2), 3)
        findKLargestElementsOptimal(arrayOf(4,7,3,1,6,3,4,9,10,2), 3)

        //https://www.techiedelight.com/connect-n-ropes-with-minimal-cost/
        findMinCost(intArrayOf(5, 4, 2, 8))

        //https://www.geeksforgeeks.org/find-itinerary-from-a-given-list-of-tickets/
        //print ticket itinary
        printItinary()

        //sort a k-sorted array
        //https://www.youtube.com/watch?v=pptk8cUHHUg&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=14
        //todo important concept (sliding window)
        sortNearlySorted(arrayOf(2,3,1,4,6,7,5,8,9), 2)


        findSmallestMissingPositiveBrute(intArrayOf(3,5,7,-1,-3,9,10,-1))

        //https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
        findSubArraySumZeroBrute(arrayOf(15, -2, 2, -8, 1, 7, 10, 23))

        //https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
        findDistinctInWindow(intArrayOf(1, 2, 1, 3, 4, 2, 3), 4)

        //find k closet element to given value
        //https://www.geeksforgeeks.org/find-k-closest-elements-given-value/
        kClosestElement(arrayOf(10,4,7,3,9,8,12,6,2,1,0), 5, 4)

        //todo ===============================================================================================================

        //347 - top k frequent elements
        //todo important
        topKElements(arrayOf(1,1,1,2,2,3), 2)


        //find k largest in a stream
        funFindKLargetInStream(intArrayOf(10,20,11,70,50,40,100,50), 3)
    }

    fun findMinCost(arr: IntArray) {
        var pq = PriorityQueue<Int>()
        var cost = 0

        for(item in arr) {
            pq.add(item)
        }

        while(!pq.isEmpty() && pq.size > 1) {
            var a = pq.remove()
            var b = pq.remove()

            cost = cost + (a + b)

            pq.add(a + b)
        }

        print(cost)
    }

    //using extra space
    fun findSmallestMissingPositiveBrute(arr: IntArray) {
        var pq = PriorityQueue<Int>()

        for(item in arr) {
            pq.add(item)
        }

        while(pq.peek() < 0) {
            pq.remove()
        }

        print(pq.peek() - 1)
    }

    fun funFindKLargetInStream(arr: IntArray, k: Int) {
        var pq = PriorityQueue<Int>()

        for(item in arr) {
            if(pq.size < k - 1) {
                pq.add(item)
                print("_, ")
            } else {
                pq.add(item)
                print(pq.remove())
                print(", ")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun findDistinctInWindow(arr: IntArray, K: Int) {
        // Creates an empty hashMap hM
        val hM = HashMap<Int, Int>()

        // Traverse the first window and store count
        // of every element in hash map
        for (i in 0 until K) hM[arr[i]] = hM.getOrDefault(arr[i], 0) + 1

        // Print count of first window

        // Print count of first window
        println(hM.size)

        // Traverse through the remaining array

        // Traverse through the remaining array
        for (i in K until arr.size) {

            // Remove first element of previous window
            // If there was only one occurrence
            if (hM[arr[i - K]] == 1) {
                hM.remove(arr[i - K])
            } else  // reduce count of the removed element
                hM[arr[i - K]] = hM[arr[i - K]]!! - 1

            // Add new element of current window
            // If this element appears first time,
            // set its count as 1,
            hM[arr[i]] = hM.getOrDefault(arr[i], 0) + 1

            // Print count of current window
            println(hM.size)
        }
    }

    fun findSubArraySumZeroBrute(arr: Array<Int>) : Int {
        var max = 0

        for(i in 0..arr.size - 1) {
            var sum = 0
            for(j in i..arr.size - 1) {
                sum = sum + arr[j]

                if(sum == 0) {
                    max = maxOf(max, j - i + 1)
                }
            }
        }

        print(max)
        return max
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun kClosestElement(arr: Array<Int>, x: Int, k: Int) {
        val pq = PriorityQueue { a: Pair<Int, Int>, b: Pair<Int, Int> -> b.second - a.second }

        for((index,value) in arr.withIndex()) {
            if(pq.size <= k) {
                var diff = Math.abs(value - x)
                var pair = Pair(index,diff)
                pq.add(pair)
            } else {
                var peek = pq.peek()
                var diff = Math.abs(value - x)

                if(diff < peek.second) {
                    pq.remove()
                    var pair = Pair(index,diff)
                    pq.add(pair)
                }
            }
        }

        while(!pq.isEmpty()) {
            var peek = pq.remove()
            print(arr[peek.first])
            println()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun topKElements(nums: Array<Int>, k: Int) : List<Int> {
        val map: MutableMap<Int, Int> = HashMap()
        for (n in nums) {
            var value = map[n] ?: 0
            map[n] = value + 1
        }

        val maxHeap = PriorityQueue { a: Map.Entry<Int, Int>, b: Map.Entry<Int, Int> -> b.value - a.value }
        for (entry in map.entries) {
            maxHeap.add(entry)
        }

        val res: MutableList<Int> = ArrayList()

        while (res.size < k) {
            val entry = maxHeap.poll()
            res.add(entry.key)
        }
        return res
    }


    fun longestSequence(arr: Array<Int>) : Int {
        val map = hashMapOf<Int, Boolean>()
        var max = 0

        for(item in arr) {
            map[item] = true
        }

        for(item in arr) {
            var value = map.get(item - 1)
            if(value != null) {
                map[item] = false
            }
        }

        for(item in arr) {
            if(map[item] == true) {
                var current = item + 1
                var count = 1
                while(map.contains(current)) {
                    count++
                    current++
                }
                max = maxOf(max, count)
            }
        }

        return max
    }

    private fun findDuplicates(arr1: Array<Int>, arr2: Array<Int>) {
        val freqMap = hashMapOf<Int, Int>()
        for((index, value) in arr1.withIndex()) {
            val freq = freqMap[value] ?: 0
            freqMap[value] = freq + 1
        }

        for(i in 0..arr2.size - 1) {
            val freq = freqMap[arr2[i]] ?: 0
            freqMap[arr2[i]] = freq - 1
            if(freq > 0) {
                println(arr2[i])
            }
        }
    }

    private fun findKLargestElementsBruteForce(arr: Array<Int>, k: Int) {
        val pQ = PriorityQueue<Int>()

        for(item in arr) {
            pQ.add(item)
        }

        var loopCount = arr.size - k

        for(i in 0..loopCount - 1) {
            pQ.remove()
        }
    }


    //check peek element with current element everytime before inserting
    private fun findKLargestElementsOptimal(arr: Array<Int>, k: Int) {
        val pq = PriorityQueue<Int>()

        for (item in arr) {
            if(pq.size < k) {
                pq.add(item)
            } else {
                val peek = pq.peek()
                if(item > peek) {
                    pq.remove()
                    pq.add(item)
                }
            }
        }

        print(pq)
    }

    fun sortNearlySorted(arr: Array<Int>, k : Int) {
        var start = 0
        var end = k
        var pq = PriorityQueue<Int>()

        for(i in 0..k) {
            pq.add(arr[i])
        }

        while(end < arr.size - 1) {
            print(pq.remove())
            start++
            end++
            pq.add(arr[end])
        }

        while(!pq.isEmpty()){
            print(pq.remove())
        }
    }

    fun printItinary() {
        var map = hashMapOf<String, String>()
        map["Ch"] = "Ba"
        map["Bo"] = "De"
        map["Go"] = "Ch"
        map["De"] = "Go"
        println(map)

        var stack = hashSetOf<String>()
        stack.add("Ba")
        stack.add("De")
        stack.add("Ch")
        stack.add("Go")
        println(stack)

        var peek = ""
        for(item in map.keys) {
            if(!stack.contains(item)) {
                peek = item
                break
            }
        }

        for(i in 0..map.size - 1) {
            var key = peek
            var value = map.get(key).orEmpty()
            print(key)
            print("->")
            print(value)
            println()

            peek = value
        }

        var k = "1234".toInt()
    }
}