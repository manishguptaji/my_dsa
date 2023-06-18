package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dp)

        //fibonacci recursion
        //in this we have used memorization
        print(fibonacciRecusrion(30, IntArray(31)))

        //https://leetcode.com/problems/climbing-stairs/
        //todo explanation : https://www.youtube.com/watch?v=A6mOASLl2Dg&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=2
        // see the first approach only
        print(climbingStairs(2))

        //jump game
        //jumpGame(arrayOf(2, 3, 1, 4))

        //house robbery - 198
        //houseRobbery(arrayOf(2, 1, 1, 2))
    }

    //To calculate the new value we only leverage the previous two values.
    // So we don't need to use an array to store all the previous values.
    fun climbingStairs(n: Int) : Int {
        if (n <= 1) {
            return 1
        }

        var prev1 = 1
        var prev2 = 2

        for (i in 3..n) {
            val newValue = prev1 + prev2
            prev1 = prev2
            prev2 = newValue
        }

        return prev2
    }

    fun fibonacciRecusrion(n: Int, arr: IntArray) : Int {
        if(n == 0 || n == 1) {
            return n
        }

        if(arr[n] != 0) {
            return arr[n]
        }

        var left = fibonacciRecusrion(n-1, arr)
        var right = fibonacciRecusrion(n-2, arr)

        arr[n] = left + right

        return left + right
    }
}