package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Bitwise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitwise)

        //kunal question 1
        isNoOdd(23)

        //add binary
        addBinary("11", "1")

        //kunal question 2
        singleNumber(arrayOf(4,1,2,4,1))

        //191. Number of 1 Bits
        noOfOne(521)

        //kunal question 4
        findIthBit(32,4)

        //kunal question 5
        setIthBit(10, 2) // make 2nd bit as 1

        //kunal question 6
        resetIthBit(7,2) // make 2nd bit as 0

        //kunal qestion 8
        //every no. is appearing 3 times except for 1 no. .....find that single no.

        //kunal question 9
        findMagicNo(6)

        //kunal 12
        isNoPowerOf2(32)

        //kunal 14
        findSetBitCount(9)

        //kunal 15
        //findXorbw(0, 15)

        calculateSquare(5) // without using *, / and pow

        //Divide two integers without using multiplication, division and mod operator
        divideTwo(33,9)
    }

    private fun divideTwo(i: Int, n: Int) {
        var count  = 0
        while(i >= n) {
            i - n
            count++
        }
    }

    private fun calculateSquare(n: Int) {
        //just add the no. that many times
        var no = n
        var sq = 0
        while(no > 0) {
            sq = sq + n
            no--
        }

        print(sq)
    }

    private fun findSetBitCount(n: Int) {

        var count = 0

        while(n > 0) {
            var lastDigit = n.and(1)

            if(lastDigit == 1)
                count++

            n.shr(1)
        }

        print(count)
    }

    private fun isNoPowerOf2(i: Int) : Boolean {
        return i.and(i - 1) == 0
    }

    fun findMagicNo(k: Int) {
        var n = k
        var sum = 0.00
        var counter = 1.00

        while(n > 0) {
            var lastDigit = n.and(1)
            sum = sum + (lastDigit * Math.pow(5.00,counter))
            counter++
            n = n.shr(1)
        }

        print(sum)
    }

    private fun resetIthBit(n: Int, i: Int) : Int {
        val mask = 1.shl(i - 1)
        mask.inv()
        return n.or(mask)
    }

    private fun setIthBit(n: Int, i: Int) : Int {
        val mask = 1.shl(i - 1)
        return n.or(mask)
    }

    private fun findIthBit(n: Int, i: Int) : Int{
        var mask = 1.shl(i - 1)
        return n.and(mask)
    }

    // .and any no. with 1 and check if last digit is 1 or 0
    private fun isNoOdd(i: Int) : Boolean {
        return i.and(1) == 1
    }

    private fun singleNumber(nums: Array<Int>): Int {
        /*The ^ is a bitwise XOR operator. It works like this:
        00010 ^ 00010 = 00000;
        00000 ^ 10101 = 10101;
        In other words, if two integers of the same value (and bit representation) are XORed together, the expression evaluates to all 0s. If a number is XORed with all 0s, the expression evaluates to the number itself.
        These are both properties of the XOR function.
        XORing the entire array together will eliminate all the duplicates (they will all be set to 0) and leave only the single number.*/

        var result = 0
        for (i in nums) {
            result = result xor i
        }
        return result
    }

    fun noOfOne(a: Int): Int {
        var ones = 0
        var n = a
        while (n != 0) {
            ones = ones + (n and 1)
            n = n ushr 1
        }
        return ones
    }

    fun addBinary(s1: String, s2: String): String {
        var length1 = s1.length
        return ""
    }

}