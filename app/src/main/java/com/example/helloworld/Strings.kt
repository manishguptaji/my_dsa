package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.StringBuilder

class Strings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strings)

        //1108. Defanging an IP Address
        defanging("1.1.1.1")

        //1528. Shuffle String
        shuffleStr("bac", arrayOf(1, 2, 0))

        //1967 - Number of Strings That Appear as Substrings in Word
        numOfStrings(arrayOf("a", "abc", "bc", "d"), "abc")

        //680. Valid Palindrome II
        validPalindrome("abca")

        //1768. Merge Strings Alternately
        mergeAlternately("ab", "cdef")

        //interview split string
        splitStr("State 1;City 1, City 2#State 2;City 1, City 2#State 3;City 1, City 2#State 4;City 1, City 2")

        //242. Valid Anagram
        isAnagram("anagram", "nagaram")

        //1047 Remove All Adjacent Duplicates In String
        removeDuplicates("abbaca")

        //3. Longest Substring Without Repeating Characters
        lengthOfLongestSubstring("abcabbaca")

        //647 longest palindrome substring
        noOfPalindromeSubString("babad")

        //5 longest palindrome substring
        longestPalindrome("babab")

        //todo Mg ==================================================================================

        //13. Roman to Integer
        romanToInt("MCMXCIV")
    }


    //Idea is start from each index and try to extend palindrome for both odd and even length.
    //explained by ayushi sharma on yTube
    var maxLen = 1
    private fun longestPalindrome(s: String): Int {
        for(i in 0..s.length - 1) { //i is mid
            extendPalindromeLength(s, i, i) //odd length
            extendPalindromeLength(s, i, i+1) //even length
        }

        return maxLen
    }

    private fun extendPalindromeLength(s: String, l:Int, r:Int) {
        var left = l
        var right = r

        while(left >=0 && right < s.length && s[left] == s[right]) {
            maxLen = maxOf(maxLen, right - left + 1)
            left--
            right++
        }
    }

    //Idea is start from each index and try to extend palindrome for both odd and even length.
    //explained by ayushi sharma on yTube
    var count = 0
    private fun noOfPalindromeSubString(s: String) : Int {
        if (s.isEmpty()) return 0

        for (i in s.indices) { // i is the mid point
            extendPalindromeCount(s, i, i) // odd length;...odd length has 1 mid
            extendPalindromeCount(s, i, i + 1) // even length....even length has 2 mids
        }

        return count
    }

    private fun extendPalindromeCount(s: String, l: Int, r: Int) {
        var left = l
        var right = r
        while (left >= 0 && right < s.length && s[left] == s[right]) {
            count++
            left--
            right++
        }
    }

    fun lengthOfLongestSubstring(s: String): Int {
        var j = 0
        var i = 0
        var max = 0
        var seen = hashSetOf<Char>()

        while(i < s.length) {
            var c = s.get(i)
            if(seen.add(c)) {
                max = maxOf(max, i - j + 1)
                i++
            } else {
                seen.remove(s.get(j))
                j++
            }
        }

        return max
    }

    fun removeDuplicates(s: String): String {
        val sb = StringBuilder()

        for(i in 0..s.length - 1) {
            if(sb.isNotEmpty() && sb.get(sb.length - 1).toString() == s.get(i).toString()) {
                sb.deleteCharAt(sb.length - 1)
            } else {
                sb.append(s[i].toString())
            }
        }

        return sb.toString()
    }

    fun isAnagram(s1: String, s2: String): Boolean {
        if(s1.length != s2.length)
            return false

        val map = hashMapOf<Char, Int>()

        for(i in 0..s1.length - 1) {
            var hashCount = map.get(s1.get(i)) ?: 0
            map.put(s1.get(i), hashCount + 1)
        }

        println("map was $map")

        for(i in 0..s2.length - 1) {
            var hashCount = map[s2[i]] ?: 0
            map[s2[i]] = hashCount - 1
        }

        println("map is $map")

        for(item in map)
            if(item.value != 0)
                return false

        return true
    }

    private fun splitStr(s: String) {
        val inputStr = s
        var arr1: List<String> = inputStr.split("#")
        var arr2 = mutableListOf<String>()
        var map = linkedMapOf<String, String>()

        for (item in arr1) {
            val item = item.split(";")
            arr2.add(item[0])
            arr2.add(item[1])
        }

        for(i in 0..arr2.size - 1 step 2) {
            map.put(arr2[i], arr2[i + 1])
        }
    }

    private fun romanToInt(s: String) : Int {
        val map = hashMapOf<String, Int>()
        map.put("I", 1)
        map.put("V", 5)
        map.put("X", 10)
        map.put("L", 50)
        map.put("C", 100)
        map.put("D", 500)
        map.put("M", 1000)

        var result = 0
        val length = s.length

        if(length < 2) {
            return map.get(s[0].toString()) ?: 0
        }

        for(i in 0..length - 1) {
            val key1 = s[i].toString()
            var value1 = map.get(key1) ?: 0
            var value2 = 0

            if(i != length - 1) {
                val key2 = s[i + 1].toString()
                value2 = map.get(key2) ?: 0

                if(value1 < value2) {
                    value1 = -1 * value1
                }
            }

            result += value1
        }

        return result
    }

    fun mergeAlternately(word1: String, word2: String): String {
        var i = 0
        var k = 0
        var j = 0

        val length2 = word2.length
        val length1 = word1.length
        val result = StringBuilder()

        while (j < length2) {
            if (k == 0) {
                if(i < length1)
                    result.append(word1.get(i))
                i++
                k = 1
            } else if (k == 1) {
                if(j < length2)
                    result.append(word2.get(j))
                j++
                k = 0
            }
        }
        return result.toString()
    }

    fun validPalindrome(s: String): Boolean {
        var i = 0
        var j = s.length - 1

        while (i < j) {
            if (s.get(i) != s.get(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1)
            }
            i++
            j--
        }

        return true
    }

    private fun isPalindrome(s: String, i: Int, j: Int): Boolean {
        var i = i
        var j = j
        while (i < j) {
            if (s[i] != s[j]) {
                return false
            }
            i++
            j--
        }
        return true
    }

    fun numOfStrings(patterns: Array<String>, word: String): Int {
        var count = 0
        for (str in patterns) {
            if (word.contains(str))
                count++
        }

        return count
    }

    private fun shuffleStr(ss: String, ii: Array<Int>): String {
        //use cyclic sort
        var i = 0
        var j = 0
        var inputStr = ss
        var indices = ii
        while (i < inputStr.length && j < indices.size) {
            val strIndex = i
            val posIndex = indices[j]

            if (strIndex == posIndex) {
                i++
                j++
            } else {
                inputStr = swapStr(inputStr, strIndex, posIndex)
                swapArray(indices, strIndex, posIndex)
            }
        }

        return inputStr
    }

    private fun swapArray(arr: Array<Int>, first: Int, second: Int) {
        val temp = arr[first]
        arr[first] = arr[second]
        arr[second] = temp
    }

    private fun swapStr(input: String, pos1: Int, pos2: Int): String {

        var char1 = input.get(pos1)
        var char2 = input.get(pos2)

        val stringBuff = StringBuffer(input)

        stringBuff.setCharAt(pos1, char2)
        stringBuff.setCharAt(pos2, char1)

        return stringBuff.toString()
    }

    private fun defanging(s: String): String {
        val builder = StringBuilder()
        val address: String = s
        for (i in 0..address.length - 1) {
            val char = address[i]
            if (char == '.') {
                builder.append("[.]")
            } else {
                builder.append(char)
            }
        }

        return builder.toString()
    }
}