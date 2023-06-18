package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Recursion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursion)

        fibo(5)

        //reverse a string using recursion
        reverseStr("manish")

        //print 5 to 0
        printFiveToOne(5)

        //print 1 to 5
        printOneToFive(5)

        //print factorial of N
        println(factorial(5))

        //print sum of 1 to N
        println(sumOf(3))

        //print sum of digits
        sumOfDigits(54897)

        //reverse a number
        reverse(1234)
        println(sum)

        //count zero in integer
        countZero(12300)
        print(count)
        countZero2(12300)

        //1342 Number of Steps to Reduce a Number to Zero
        numberOfSteps(14)

        //is array sorted
        println(isSorted(arrayOf(-3,4,5,8,10), 0))

        //linear search
        search(arrayOf(0,4,5,8,10), 10, 0)

        //search 2
        search(arrayOf(0,4,5,8,10), 10, 0, mutableListOf<Int>())

        //remove 'a' from a string
        skipA("abbccad", StringBuilder())

        //print all sub sequence of string
        subSequence("", "abc")

        palindromeStrRecursion(java.lang.StringBuilder("manish"))

        //x power n
        xPowerN(5,3)
    }

    private fun fibo(k: Int) : Int {
        if(k <= 1) {
            return k
        }

        var left = fibo(k - 1)
        var right = fibo(k - 2)

        return left + right
    }

    fun palindromeStrRecursion(str: StringBuilder) : Boolean {
        if(str.length == 0) {
            return true
        }

        var firstChar = str.get(0)
        var lastChar = str.get(str.length - 1)
        //t
        if(firstChar == lastChar) {

            if(str.length == 1) {
                return true
            }

            str.deleteCharAt(0)
            str.deleteCharAt(str.length - 1)
            return palindromeStrRecursion(str)
        } else {
            return false
        }
    }

    fun subSequence(p: String, up: String) {

        //watch kunal https://www.youtube.com/watch?v=gdifkIwCJyg&list=PL9gnSGHSqcnr_DxHsP7AW9ftq0AtAyYqJ&index=32
        //from 30 mins

        if(up.isEmpty()) {
            println(p)
            return
        }

        val firstChar = up.get(0)
        subSequence(p, up.substring(1))
        subSequence(p + firstChar, up.substring(1))
    }

    fun skipA(s: String, sb: StringBuilder) : String {
        if(s.isEmpty())
            return sb.toString()

        if(s.get(0) != 'a')
            sb.append(s.get(0))

        return skipA(s.substring(1, s.length), sb)
    }

    fun search(nums: Array<Int>, target: Int, index: Int, list: MutableList<Int>) : List<Int> {

        if(index == nums.size)
            return list

        if(nums[index] == target)
            list.add(index)

        return search(nums, target, index + 1, list)
    }

    fun search(nums: Array<Int>, target: Int, index: Int) : Int {

        if(index == nums.size)
            return -1

        if(nums[index] == target)
            return index

        return search(nums, target, index + 1)

    }

    fun isSorted(nums: Array<Int>, index: Int) : Boolean {

        if(index == nums.size - 1) // index reached last point, i.e array is sorted
            return true

        if(nums[index] > nums[index + 1]) {
            return false
        } else {
            return isSorted(nums, index + 1)
        }
    }

    var steps = 0
    fun numberOfSteps(num: Int) : Int {

        if(num <= 0)
            return steps

        return if(num % 2 == 0) {
            steps++
            numberOfSteps(num / 2)
        } else {
            steps++
            numberOfSteps(num - 1)
        }
    }

    var count = 0
    fun countZero(n : Int) {
        if(n == 0) {
            return
        }

        if(n % 10 == 0)
            count = count + 1

        countZero(n / 10)
    }

    var sum = 0
    fun reverse(n : Int) {
        if(n == 0) {
            return
        }
        sum = sum * 10 + (n % 10)
        return reverse(n / 10)
    }

    fun sumOfDigits(n : Int) : Int {
        if(n == 0) {
            return 0
        }

        return n % 10 + sumOfDigits(n/10)
    }

    fun sumOf(a: Int) : Int {
        if(a == 1)
            return 1

        return a + sumOf(a - 1)
    }

    fun factorial(a: Int) : Int {
        if(a == 1)
            return 1

        return a * factorial(a - 1)
    }

    private fun printOneToFive(a: Int) {
        if(a > 0) {
            printFiveToOne(a - 1)
            println(a)
        }
    }

    private fun printFiveToOne(a: Int) : Int {
        if(a == 1) {
            println(a)
            return 0
        }

        println(a)
        return printFiveToOne(a - 1)
    }

    fun reverseStr(str: String) {

        if(str.isEmpty()) {
            return
        }

        print(str.get(str.length - 1))
        reverseStr(str.substring(0, str.length - 1))
    }

    fun countZero2(i: Int) : Int {
        if(i == 0)
            return 1

        if(i < 10)
            return 0

        if(i % 10 == 0)
            return 1 + countZero2(i/10)
        else
            return countZero2(i/10)
    }

    fun xPowerN(x: Int, n: Int) : Int {
        if(n == 1) {
            return x
        }

        return x * xPowerN(x, n-1)
    }
}