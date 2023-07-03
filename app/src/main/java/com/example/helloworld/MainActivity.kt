package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.HashMap
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //move all zeros to end
        moveAllZeros(intArrayOf(0,3,5,0,12))

        //Build Array from Permutation
        buildArrayPermutation(arrayOf(0, 2, 1, 5, 3, 4))

        //Concatenation of Array
        concatArray(arrayOf(1, 2, 1))

        //Running Sum of 1d Array
        runningSumArray(arrayOf(1, 1, 1, 1, 1))

        //kids with greatest number of candies
        candies(arrayOf(12, 1, 12), 3)

        //Richest Customer Wealth
        richestCustomer(arrayOf(arrayOf(2, 8, 7), arrayOf(7, 1, 3), arrayOf(1, 10, 5)))

        //shuffle the array 1470
        shuffletArray(arrayOf(2, 5, 1, 3, 4, 7), 3)

        //How Many Numbers Are Smaller Than the Current Number
        smallerNumbersThanCurrent(arrayOf(8, 1, 2, 2, 3))

        //numIdenticalPairs - 1512
        numIdenticalPairs(arrayOf(1, 2, 3, 1, 1, 3))

        //two sum
        twoSum(arrayOf(2, 7, 4, 5), 9)

        //Find N Unique Integers Sum up to Zero
        sumZero(4)

        //plus one - 66
        plusOne(arrayOf(9, 9))

        //26. Remove Duplicates from Sorted Array
        removeDuplicated(arrayOf(1, 1, 2))

        //989 - add to array
        addToArray(arrayOf(2, 7, 4), 181)

        //1217 move chips
        moveChips(arrayOf(2, 2, 2, 3, 3))

        //53 - max sum sub array
        //kaden algo
        maxSumSubArray(arrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))

        //arranging coins - 441
        arrangeCoins(8)

        //leetcode 1346 Check If N and Its Double Exist
        checkIfExist(arrayOf(10,2,6,0))

        //Fair Candy Swap
        fairCandySwap(arrayOf(1,1), arrayOf(2,2))

        //single-element-in-a-sorted-array - 540
        findSingleElement(arrayOf(1,1,2,3,3))

        //Find the Duplicate Number - 287
        findDuplicate(arrayOf(1,3,4,2,2))

        //check if array contains duplicate
        print(containsDuplicate(intArrayOf(1,3,4,5)))

        //https://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
        //Find the maximum repeating number in O(n) time and O(1) extra space
        val arr = intArrayOf(2, 3, 3, 5, 3, 4, 1, 7)
        val n = arr.size
        val k = 8
        println(maxRepeating(arr, n, k))

        //valid mountain array
        print(validMountainArray(intArrayOf(1,4,4,3)))

        //binarySearch
        binarySearch(arrayOf(2, 3, 4, 5, 6, 7, 8), 9)

        //31 next permutation
        nextPermutation(intArrayOf(1,2,3))

        buySellStock(intArrayOf(7,1,5,3,6,4))

        //third max no.
        thirdMaxNo(intArrayOf(4,6,7,2,3,5,6))

        //34. Find First and Last Position of Element in Sorted Array
        findFirstAndLast(arrayOf(5, 7, 7, 8, 8, 10), 8)

        //find Ceiling in a sorted array
        ceilingSearch(arrayOf(2, 3, 4, 5, 6, 7, 8, 10, 11), 9)

        //find floor in a sorted array
        floorNumber(arrayOf(2, 3, 4, 5, 6, 7, 8, 10, 11), 9)

        //smallest number - 744
        smallestNumber(arrayOf("c", "f", "j", "m", "p", "t", "v", "w", "y"), "n")

        //binary search in infinite array
        infiniteArrayBinarySearch(arrayOf(2,3,4,5,6,7,8,9,13,15,16,18,19,24,25,56,67), 24)

        //peak in mountain array
        //peak element in mountain array
        peakInMountainArray(arrayOf(2,31,14,8,6,5,3,2,1))

        //find in a mountain array - HARD
        //findInMountainArray(arrayOf(1,2,3,4,5,3,1), 3)

        //find in rotated sorted array - 33
        //https://www.youtube.com/watch?v=1uu3g_uu8O0
        findInRotatedSorted(arrayOf(13,14,7,8,9,10,11,12), 11)

        //leetcode 153 - find min in rotated sorted array
        //https://www.youtube.com/watch?v=Kcj2NGnuSNg
        findMinInSortedArray(arrayOf(2,3,4,5,1))

        //squareRoot
        findSquareRoot(36)

        //guessGame
        guessGame(35)

        //bad verison app
        findFirstBadVersion(2)

        //Valid Perfect Square
        validSquare(808201)

        //product of array except itself
        productOfArray(arrayOf(10,3,5,6,2))

        // 88 - merge two sorted arrays
        merteTwoSortedArray(arrayOf(1,2,3,0,0,0), arrayOf(2,5,6))

        // array inversion
        arrInversion(intArrayOf(1,3,5,7,9))

        //find subarray sum == 0
        //brute force = O(N^2)
        findSubArraysBrute(intArrayOf(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7))
        //optimal = O(N)
        findSubArraysOpt(intArrayOf(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7))

        //https://leetcode.com/problems/sort-colors/
        sortColor(intArrayOf(2,0,2,1,1,0))
    }

    fun sortColor(nums: IntArray): Unit {
        val map = hashMapOf<Int, Int>()

        for(item in nums) {
            val value = map.get(item) ?: 0
            map[item] = value + 1
        }

        println(map)

        var i = 0
        var j = 0

        while(i < nums.size) {
            var counter = map[j] ?: 0

            for(k in i..(i + counter) - 1) {
                nums[i] = j
                i++
            }
            j++
        }
    }

    fun findSubArraysOpt(arr: IntArray) {
        val map = HashMap<Int, ArrayList<Int>>()

        var sum = 0
        var count = 0

        for (i in arr.indices) {
            sum += arr[i]
            if (sum == 0)
                count++

            var al: ArrayList<Int>? = ArrayList()
            if (map.containsKey(sum)) {
                al = map[sum]
                for (it in al!!.indices) {
                    count++
                }
            }
            al!!.add(i)
            map[sum] = al
        }
    }

    fun findSubArraysBrute(arr: IntArray) {
        for(i in 0..arr.size - 1) {
            var sum = arr[i]
            for(j in i + 1..arr.size - 1) {
                sum = sum + arr[j]

                if(sum == 0) {
                    println(i)
                    println(j)
                    println("=====")
                }
            }
        }
    }

    fun arrInversion(arr:IntArray) {
        var count = 0

        var i = 0
        var j = arr.size - 1
        var mid = 0
        var midIndex = 0

        if(arr.size % 2 == 0) {

        } else {
            midIndex = i + (j - i)/2
            mid = arr[midIndex]
        }

        while(i <= midIndex && j >= midIndex) {
            if(arr[i] < mid) {
                arr[i]++
                count++
            }

            if(arr[j] > mid) {
                arr[j]--
                count++
            }

            if(arr[i] == mid) {
                i++
            }

            if(arr[j] == mid) {
                j--
            }
        }

        print(count)
    }

    fun merteTwoSortedArray(arr1: Array<Int>, arr2: Array<Int>) {

        var p1 = arr2.size -1
        var p2 = p1
        var i = arr1.size - 1

        while(p2 >= 0) {
            if(arr2[p2] >= arr1[p1]) {
                arr1[i] = arr2[p2]
                i--
                p2--
            } else {
                arr1[i] = arr1[p1]
                i--
                p1--
            }
        }

        print(arr1.contentToString())
    }

    fun productOfArray(arr: Array<Int>) {
        var arrR = IntArray(arr.size)
        var arrL = IntArray(arr.size)
        var totalProd = 1

        for(i in 0..arr.size - 1) {
            totalProd = totalProd * arr[i]
        }

        arrL[0] = 1
        arrR[arr.size - 1] = 1

        for(i in 1..arr.size - 1) {
            var product = arr[i - 1] * arrL[i - 1]
            arrL[i] = product
        }

        for(i in arr.size - 2 downTo 0) {
            var product = arr[i + 1] * arrR[i + 1]
            arrR[i] = product
        }


        for(i in 0..arr.size - 1) {
            println(arrL[i] * arrR[i])
        }

    }


    fun thirdMaxNo(arr: IntArray) {
        var max1 = Int.MIN_VALUE
        var max2 = Int.MIN_VALUE
        var max3 = Int.MIN_VALUE

        for(item in arr) {
            if(item > max1) {
                max3 = max2
                max2 = max1
                max1 = item
            } else if(item > max2) {
                max3 = max2
                max2 = item
            } else if(item > max3) {
                max3 = item
            }
        }

        print(max1)
        print(max2)
        print(max3)
    }

    fun validMountainArray(arr: IntArray) : Boolean {
        var d = 1
        var i = 0
        var j = 1
        var count = 0

        while(j < arr.size) {
            if(arr[i] == arr[j]) {
                return false
            }

            if(d == 1) {
                //go up

                if(arr[i] > arr[j]) {
                    d = 0
                    count++
                }
                i++
                j++
            } else {
                //go down
                if(arr[i] < arr[j]) {
                    d = 1
                    count++
                }
                i++
                j++
            }
        }

        return count == 1
    }

    fun moveAllZeros(arr: IntArray) : IntArray? {
        var i = 0
        var j = 1
        val size = arr.size

        while(j < size) {
            if(arr[i] == 0 && arr[j] != 0) {
                var temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
                i++
            } else if(arr[i] == 0 && arr[j] == 0) {
                j++
            }
        }

        print(arr.contentToString())
        return arr
    }

    fun containsDuplicate(arr: IntArray) : Boolean {
        val st = hashSetOf<Int>()

        for((index,value) in arr.withIndex()) {
            if(!st.add(value))
                return true
        }

        return false
    }

    fun maxRepeating(arr: IntArray, n: Int, k: Int): Int {
        // Iterate though input array, for every element
        // arr[i], increment arr[arr[i]%k] by k
        for (i in 0 until n) arr[arr[i] % k] += k

        // Find index of the maximum repeating element
        var max = arr[0]
        var result = 0
        for (i in 1 until n) {
            if (arr[i] > max) {
                max = arr[i]
                result = i
            }
        }

        /* Uncomment this code to get the original array back
        for (int i = 0; i< n; i++)
          arr[i] = arr[i]%k; */

        // Return index of the maximum element
        return result
    }

    private fun findDuplicate(nums: Array<Int>): Int {
        var i = 0
        var j = nums.size - 1

        while(i < nums.size) {

            if(nums[i] == nums[j])
                return nums[i]

            j--

            if(i == j) {
                i++
                j = nums.size - 1
            }
        }

        return -1
    }

    private fun findSingleElement(nums: Array<Int>): Int {
        var start = 0
        val size = nums.size
        var end = size - 1

        if(size % 2 == 0)
            return -1

        while (start <= end) {
            val mid = start + (end - start) / 2

            if(mid > 0 && nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1]){
                return mid
            }

            if(mid > 0 && nums[mid] == nums[mid - 1]) {
                if((mid - 1) % 2 == 0) {
                    //go right
                    start = mid + 1
                } else {
                    // go left
                    end = mid - 1
                }
            } else if(mid > 0 && nums[mid] == nums[mid + 1]) {
                if((mid + 1) % 2 == 0) {
                    //go left
                    end = mid - 1
                } else {
                    // go right
                    start = mid + 1
                }
            }

        }

        return -1
    }

    fun fairCandySwap(aliceSizes: Array<Int>, bobSizes: Array<Int>): IntArray {
        val result = IntArray(2)
        var aliceTotal = 0
        var bobTotal = 0

        for (item in aliceSizes) {
            aliceTotal += item
        }

        for (item in bobSizes) {
            bobTotal += item
        }

        var delta = maxOf(aliceTotal, bobTotal) - minOf(aliceTotal, bobTotal)

        result[0] = aliceSizes[0]


        return result
    }

    private fun checkIfExist(nums: Array<Int>): Boolean {
        val size = nums.size

        var i = 0
        var j = size -1

        while (i <= size - 1 && j != i) {
            if(nums[i] == 2 * nums[j] || nums[i] * 2 == nums[j]) {
                return true
            }

            if(j == i + 1) {

                j = size - 1
                i += 1

            } else {

                j--

            }

        }

        return false
    }

    private fun arrangeCoins(coins: Int): Int {
        var completed = 0
        var n = coins
        for (i in 1..n){

            n -= i

            if(n >= 0)
                completed++
            else
                break
        }

        return completed
    }

    private fun validSquare(num: Long): Boolean {
        if(num <= 1)
            return true

        var start = 1L
        var end: Long = num/2

        while (start <= end) {
            val mid : Long = start + (end - start)/2
            val square : Long = mid * mid

            if(square == num)
                return true

            if(square > num) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        return false
    }

    private fun isBadVersion(input: Int): Boolean {
        if(input == 2)
            return true

        return false
    }

    private fun findFirstBadVersion(latest: Int) : Int {
        var start = 1
        var end = latest

        while(start < end) {

            val mid = (start + end)/2

            if(isBadVersion(mid)) {
                end = mid
            } else {
                start = mid + 1
            }
        }


        return start
    }

    private fun guessGame(n: Int) : Int {
        return -1
    }

    private fun findSquareRoot(target: Int): Int {
        var start = 1
        var end = target/2
        var answer = 0

        if(target <= 1)
            return target

        while (start <= end) {
            val mid = (start + end)/2
            val square = mid * mid

            if(square <= target) {
                answer = mid
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        return answer
    }

    private fun findMinInSortedArray(nums: Array<Int>) : Int {
        var start = 0
        val size = nums.size
        var end = size - 1

        if(nums[start] <= nums[end])
            return nums[0]

        while (start <= end) {
            val mid = (start + end)/2

            if(mid != 0 && nums[mid] < nums[mid - 1])
                return nums[mid]


            when {
                nums[mid] > nums[mid + 1] -> {
                    return nums[mid+1]
                }
                nums[start] <= nums[mid] //first half is sorted, then go right
                -> {
                    start = mid + 1;
                }
                nums[mid] <= nums[end] //second half is sorted
                -> {
                    end = mid - 1
                }
            }
        }
        return -1
    }

    private fun findInRotatedSorted(nums: Array<Int>, target: Int): Int {

        var start = 0
        val size = nums.size
        var end = size - 1

        while(start <= end) {

            val mid = (start + end)/2

            if(nums[mid] == target)
                return mid

            //check if first half of array is sorted in asc order
            if(nums[start] <= nums[mid]) {
                //check if our target lies in between these 2 numbers
                if(target >= nums[start] && target < nums[mid]) {
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            } else { // then 2nd half of array is sorted
                //check if our target lies in between these 2 numbers
                if(target > nums[mid] && target <= nums[end]) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
        }

        return -1
    }

    private fun peakInMountainArray(nums: Array<Int>): Int {
        var start = 0
        var end = nums.size - 1

        while (start < end) {

            val mid = (start + end) / 2

            when {
                nums[mid] > nums[mid + 1] -> {
                    end = mid
                }
                nums[mid] < nums[mid + 1] -> {
                    start = mid + 1
                }
            }
        }

        return start
    }

    private fun infiniteArrayBinarySearch(nums: Array<Int>, target: Int) : Int{
        var startSearch = 0
        val multiplier = 2
        var endSearch = 1

        loop@do {
            if (target >= nums[startSearch] && target <= nums[endSearch]) {
                break@loop
            } else {
                startSearch = endSearch + 1
                endSearch = (startSearch + multiplier) + 1
            }
        } while (true)


        var start = startSearch
        var end = endSearch

        while (start <= end) {
            val mid = (start + end) / 2

            if (nums[mid] == target) {
                return nums[mid]
            }

            if (target > nums[mid]) {
                start = mid + 1
            } else if (target < nums[mid]) {
                end = mid - 1
            }
        }

        return -1
    }

    private fun smallestNumber(letters: Array<String>, target: String): String {
        val size = letters.size
        var start = 0
        var end = size - 1

        while (start <= end) {
            val mid = (start + end) / 2

            if (target > letters[mid]) {
                start = mid + 1
            } else if (target < letters[mid]) {
                end = mid - 1
            }
        }

        if(start == size) // element not found and return first index as per question
            return letters[0]

        return letters[start]
    }

    private fun binarySearch(nums: Array<Int>, target: Int): Int {
        val size = nums.size
        var start = 0
        var end = size - 1

        while (start <= end) {
            val mid = (start + end) / 2

            if (nums[mid] == target) {
                return nums[mid]
            }


            if (target > nums[mid]) {
                start = mid + 1
            } else if (target < nums[mid]) {
                end = mid - 1
            }
        }

        return -1
    }

    private fun ceilingSearch(nums: Array<Int>, target: Int): Int {
        val size = nums.size
        var start = 0
        var end = size - 1

        while (start <= end) {
            val mid = (start + end) / 2

            if (nums[mid] == target) {
                return mid
            }


            if (target > nums[mid]) {
                start = mid + 1
            } else if (target < nums[mid]) {
                end = mid - 1
            }
        }

        return start
    }

    private fun floorNumber(nums: Array<Int>, target: Int): Int {
        val size = nums.size
        var start = 0
        var end = size - 1

        while (start <= end) {
            val mid = (start + end) / 2

            if (nums[mid] == target) {
                return nums[mid]
            }


            if (target > nums[mid]) {
                start = mid + 1
            } else if (target < nums[mid]) {
                end = mid - 1
            }
        }

        return nums[end]
    }

    private fun findFirstAndLast(nums: Array<Int>, target: Int): IntArray {
        val result = IntArray(2)
        result[0] = -1
        result[1] = -1

        var low = 0
        var high = nums.size - 1

        //search for first Index
        while (low <= high) {
            val mid = (low + high) / 2

            when {
                nums[mid] == target -> {
                    result[0] = mid
                    high = mid - 1
                }
                nums[mid] < target -> {
                    low = mid + 1
                }
                else -> {
                    high = mid - 1
                }
            }
        }

        //search for last Index
        low = 0
        high = nums.size - 1
        while (low <= high) {
            val mid = (low + high) / 2

            when {
                nums[mid] == target -> {
                    result[1] = mid
                    low = mid + 1
                }
                nums[mid] < target -> {
                    low = mid + 1
                }
                else -> {
                    high = mid - 1
                }
            }
        }

        return result
    }

    fun spiralMatrixOne(nums: Array<Array<Int>>): List<Int> {
        val list = mutableListOf<Int>()
        val row = nums.size

        if (row == 0) {
            return list
        }

        val col = nums[0].size
        var l = 0
        var r = col - 1
        var t = 0
        var b = row - 1
        var d = 0

        while (l <= r && t <= b) {
            when (d) {
                0 -> { //l to r

                    for (i in l..r) {
                        list.add(nums[t][i])
                    }

                    d = 1
                    t++
                }
                1 -> { //t to b
                    for (i in t..b) {
                        list.add(nums[i][r])
                    }
                    d = 2
                    r--
                }
                2 -> { //r to l
                    for (i in r downTo l) {
                        list.add(nums[b][i])
                    }
                    d = 3
                    b--
                }
                3 -> { //b to t
                    for (i in b downTo t) {
                        list.add(nums[i][l])
                    }
                    d = 0
                    l++
                }
            }
        }

        return list
    }

    private fun houseRobbery(nums: Array<Int>): Int {
        var sumOdd = 0
        var sumeven = 0

        for (i in 0..nums.size - 1) {
            if (i % 2 == 0) {
                sumeven += nums[i]
                sumeven = maxOf(sumOdd, sumeven)
            } else {
                sumOdd += nums[i]
                sumOdd = maxOf(sumOdd, sumeven)
            }
        }

        return maxOf(sumOdd, sumeven)
    }

    private fun maxSumSubArray(nums: Array<Int>): Int {
        var max = Int.MIN_VALUE
        var sum = 0

        for (i in 0..nums.size - 1) {
            sum = sum + nums[i]

            if (sum > max) {
                max = sum
            }

            if (sum < 0) {
                sum = 0
            }
        }

        return max
    }

    private fun moveChips(nums: Array<Int>): Int {
        var oddCount = 0
        var evenCount = 0

        for (num in nums) {
            if (num % 2 == 0) {
                evenCount++
            } else {
                oddCount++
            }
        }

        return minOf(oddCount, evenCount)
    }

    private fun addToArray(nums: Array<Int>, number: Int): Array<Int> {
        var k = number
        mainLoop@ for (i in nums.size - 1 downTo 0) {
            var result = nums[i] + k
            if (result < 10) {
                nums[i] = result
                break@mainLoop
            } else {
                k = result / 10
                result -= (k * 10)
                nums[i] = result
            }
        }

        return nums
    }

    private fun removeDuplicated(nums: Array<Int>): Int {
        var i = 0
        var k = i + 1

        while (k < nums.size) {
            if (nums[i] == nums[k]) {
                k++
            } else {
                nums[i + 1] = nums[k]
                i++
            }
        }

        val value = i + 1
        return value
    }

    private fun plusOne(digits: Array<Int>): IntArray {
        for (i in digits.size - 1 downTo 0) {
            if (digits[i] < 9) {
                digits[i]++
                return digits.toIntArray()
            }
            digits[i] = 0
        }

        val result = IntArray(digits.size + 1)
        result[0] = 1
        return result
    }

    fun sumZero(nums: Int): IntArray {
        val result = IntArray(nums)
        var i = 0
        var j = nums - 1
        var count = nums

        while (j > i) {
            result[i] = count
            result[j] = -1 * count

            count--
            i++
            j--
        }

        return result
    }

    private fun twoSum(nums: Array<Int>, n: Int): IntArray {
        val result = IntArray(2)
        val hashMap = hashMapOf<Int, Int>()
        for (i in 0..nums.size - 1) {
            hashMap.put(nums[i], i)
        }

        for (i in 0..nums.size - 1) {
            val number = nums[i]
            val search = n - number

            if (hashMap.containsKey(search)) {
                result[0] = i
                result[1] = hashMap.get(search) ?: 0
            }
        }

        return result
    }

    /** ****** IMPORTANT QUESTION ****** */

    fun smallerNumbersThanCurrent(nums: Array<Int>): IntArray? {
        val count = IntArray(101)
        val res = IntArray(nums.size)

        //generate frequency array
        for (i in nums.indices) {
            count[nums[i]]++
        }

        //calculate running sum
        for (i in 1..100) {
            count[i] += count[i - 1]
        }

        for (i in nums.indices) {
            if (nums[i] == 0)
                res[i] = 0
            else
                res[i] = count[nums[i] - 1]
        }
        return res
    }

    /** ****** IMPORTANT QUESTION ****** */
    
    fun numIdenticalPairs(nums: Array<Int>): Int {
        val hm = HashMap<Int, Int>()

        var ans = 0

        for (friend in nums) {
            val friendCount = hm[friend] ?: 0
            ans += friendCount
            hm[friend] = friendCount + 1
        }

        return ans
    }

    fun shuffletArray(nums: Array<Int>, n: Int): IntArray {
        val result = IntArray(n * 2)

        for (i in 0..2 * n - 1) {
            if (i % 2 == 0) {
                result[i] = nums[i / 2]
            } else {
                result[i] = nums[n + i / 2]
            }
        }

        Log.e("shuffle ", result.contentToString())
        return result
    }

    fun richestCustomer(accounts: Array<Array<Int>>): Int {

        /** no of rows = arr.size - 1
         * no of cols = first array ka size - 1
         */

        var maxWealth = 0
        val rows = accounts.size - 1
        val cols = accounts[0].size - 1

        /** iterate karte jao arrays ko
         *  maxWealth will be maximum of customerWealth and maxWealth */

        for (i in 0..rows) {
            var customerWealth = 0
            for (j in 0..cols) {
                customerWealth += accounts[i][j]
            }

            maxWealth = maxOf(customerWealth, maxWealth)
        }

        Log.e("max wealth", maxWealth.toString())
        return maxWealth
    }

    private fun candies(nums: Array<Int>, extraCandy: Int): BooleanArray {
        /** first calculate max no.
         * then run 1 for loop to check ki kiske paas kax se jyada candies hain
         */

        val max = findMaxNo(nums)
        val boolArray = BooleanArray(nums.size)

        for (i in 0..nums.size - 1) {
            boolArray[i] = nums[i] + extraCandy >= max
        }

        Log.e("candies ", boolArray.contentToString())
        return boolArray
    }

    private fun findMaxNo(nums: Array<Int>): Int {
        var max = 0

        for (foo in nums) {
            if (foo > max)
                max = foo
        }

        return max
    }

    private fun runningSumArray(nums: Array<Int>): IntArray {

        /** create same size array
         * then next number hoga  ->  nums[i] + new array ka [i - 1] number
         * because new array apna running sum hold karega previous elements ka */

        var arr: IntArray = IntArray(nums.size)

        for (i in nums.indices) {

            if (i == 0)
                arr[i] = nums[i]
            else
                arr[i] = nums[i] + arr[i - 1]
        }

        Log.e("array3", arr.contentToString())
        return arr
    }

    private fun concatArray(nums: Array<Int>): IntArray {
        /** create new arr of twice the size
         *  then set 1st value at i pos and
         *  2nd value at i + nums.size position */

        var arr: IntArray = IntArray(nums.size * 2)
        for (i in nums.indices) {
            arr[i] = nums[i]
            arr[i + nums.size] = nums[i]
        }

        Log.e("array2", arr.contentToString())
        return arr
    }

    private fun buildArrayPermutation(arrayOf: Array<Int>): IntArray {
        /** create array of same size and
         set value of each element in new array to arrayOf[arrayOf[i]] */

        val arr: IntArray = IntArray(arrayOf.size)
        for (i in arrayOf.indices) {
            arr[i] = arrayOf[arrayOf[i]]
        }

        Log.e("array1", arr.contentToString())
        return arr
    }

    fun nextPermutation(arr: IntArray) {

        //find the first decline from right

        var i = arr.size - 1
        var j = i - 1

        while(j >= 0) {
            if(arr[j] >= arr[i]) {
                i--
                j--
                continue
            }

            break
        }

        println(arr[j])

        var minDiff = Int.MAX_VALUE
        var minIndex = 0

        for(k in j+1..arr.size - 1) {
            if(arr[k] > arr[j]) {
                var diff = arr[k] - arr[j]
                if(diff < minDiff) {
                    minDiff = diff
                    minIndex = k
                }
            }
        }

        var temp = arr[j]
        arr[j] = arr[minIndex]
        arr[minIndex] = temp

        print(arr.contentToString())
    }

    fun buySellStock(arr: IntArray) {
        var minPrice = Int.MAX_VALUE
        var minIndex = -1

        for((index, value) in arr.withIndex()) {
            if(value < minPrice) {
                minPrice = value
                minIndex = index
            }
        }

        var profit = -1
        for(k in minIndex..arr.size - 1) {
            var temp = arr[k] - minPrice
            profit = maxOf(profit, temp)
        }

        print(profit)
    }
}