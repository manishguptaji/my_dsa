package com.example.helloworld

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import java.util.*

class SlidingWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliding_window)

        //Maximum of All Subarrays of Size K
        //https://www.youtube.com/watch?v=li9rcswj0WM&list=PLUg9hRlm7gxRffCJYnTqtWbQ4ymk6Rhu1&ab_channel=ProgrammingTutorials
        maximumInK(intArrayOf(2,1,5,1,3,2), 3)

        //sliding window max
        slidingMax(intArrayOf(1,3,-1,-3,5,3,6,7), 3)


        //consecutive ones with at most 1 zero flip
        consecutiveOnes(intArrayOf(1,1,0,0,1,1,1,1))

        //slidingWindowDistinct
        slidingWindowDistinct(intArrayOf(1,5,9,3,3,7,3), 3)

        //find all anagram in a sub string
        slidingWindowAnagram("ababab", "ab").toString()


        //	Longest Substring with K Unique Characters and Longest Substring with at-most K Unique Characters
        print(longSubStrKChar("pmmemi", 2))

        //first -ve num in window of K
        //https://www.youtube.com/watch?v=Z5NHoo-KdxA
        firstNegativeNumK(intArrayOf(7,1,-8,2,3,-6,10,11), 3)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun slidingMax(arr: IntArray, k: Int) {
        val pq = PriorityQueue<Int>(Collections.reverseOrder())

        for(i in 0..2) {
            pq.add(arr[i])
        }

        val res = IntArray(arr.size - 2)
        var i = 0
        var j = 2

        while(j < arr.size) {
            res[i] = pq.peek() ?: -1
            i++
            j++
            pq.remove(arr[i - 1])
            pq.add(arr[j])
        }

        println(res.contentToString())
        print(pq)
    }

    fun slidingWindowDistinct(arr: IntArray, k: Int) {
        var res = IntArray(arr.size - (k - 1))

        var i = 0
        var j = 0
        var s = 0

        val hSet = hashSetOf<Int>()

        while(j < arr.size) {
            if(j < k) {
                hSet.add(arr[j])
                res[s] = hSet.size
                j++
                continue
            }

            s++
            hSet.remove(arr[i])
            hSet.add(arr[j])
            res[s] = hSet.size
            j++
            i++
        }


        print(res.contentToString())
    }

    fun consecutiveOnes(arr: IntArray) {
        var i = 0
        var count = 0
        var max = 0
        var one = 0

        while(i < arr.size) {
            if(arr[i] == 1) {
                one++
            } else {
                count++
                if(count == 1) {
                    one++
                } else  {
                    one = 0
                    count = 0
                }
            }

            max = maxOf(max, one)
            i++
        }
    }

    fun maximumInK(arr: IntArray, k: Int) {
        var start = 0
        var end = 0
        var maxSum = 0
        var sum = 0
        while(end < arr.size) {
            sum = sum + arr[end]

            if(end >= k - 1) {
                maxSum = maxOf(sum, maxSum)
                sum = sum - arr[start]
                start++
            }
            end++
        }

        print(maxSum)
    }

    fun slidingWindowAnagram(s1: String, s2: String) : StringBuilder {

        var sb = StringBuilder("")

        if(s1.length < s2.length) {
            return sb
        }

        var map2 = hashMapOf<Char, Int>()
        var map1 = hashMapOf<Char, Int>()

        for(i in 0..s2.length - 1) {
            var ch = s2.get(i)
            var freq = map2[ch] ?: 0
            map2[ch] = freq + 1
        }

        var j = 0
        var i = 0

        repeat(s2.length) {
            var ch = s1.get(j)
            var freq = map1[ch] ?: 0
            map1[ch] = freq + 1
            j++
        }



        while(j < s1.length - 1) {

            if(map1.equals(map2)) {
                sb.append("-" + i + "-")
            }

            var goneCh = s1.get(i)
            var freq1 = map1[goneCh] ?: 0
            map1[goneCh] = freq1 - 1

            if(freq1 == 1) {
                map1.remove(goneCh)
            }

            i++

            var newCh = s1.get(j)
            var freq2 = map1[newCh] ?: 0
            map1[newCh] = freq2 + 1

            j++
        }


        return sb
    }

    fun longSubStrKChar(s: String, k: Int): String {
        var result = ""
        var i = 0
        var j = 0
        val map = hashMapOf<Char, Int>()

        while(j < s.length) {
            val ch = s.get(j)
            if(map.size <= k) {
                val freq = map[ch] ?: 0
                map[ch] = freq + 1
                j++
                if(map.size <= k) {
                    result = s.substring(i,j)
                }
            } else {
                val freq2 = map[s.get(i)] ?: 0
                if(freq2 <= 1) {
                    map.remove(s.get(i))
                } else {
                    map[ch] = freq2 - 1
                }
                i++
            }

        }

        return result.toString()
    }

    fun firstNegativeNumK(arr: IntArray, k : Int) {
        var queue : Queue<Int> = LinkedList<Int>()
        var list = mutableListOf<Int>()

        var i = 0
        var j = 0

        while(j < arr.size) {
            if(j < k) {
                if(arr[j] < 0) {
                    queue.add(arr[j])
                }
                j++
                continue
            }

            list.add(queue.peek())

            i++
            if(arr[j] < 0) {
                queue.add(arr[j])
            }

            if(arr[i - 1] < 0) {
                queue.remove(arr[i - 1])
            }

            j++

        }

        print(list)
    }
}