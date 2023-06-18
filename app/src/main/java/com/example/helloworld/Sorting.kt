package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Sorting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorting)

        //missingNumber - 268
        missingNumber(arrayOf(9,6,4,2,3,5,7,0,1))

        //majority element 169
        majorityElement(arrayOf(2,2,1,1,1,2,2))

        //349. Intersection of Two Arrays
        interSection(arrayOf(4,9,5), arrayOf(9,4,9,8,4))

        //350. Intersection of Two Arrays II
        interSection2(arrayOf(4,9,5), arrayOf(9,4,9,8,4))

        //628 Maximum Product of Three Numbers
        maxThreeNo(arrayOf(1,2,3,4))

        //922 - parity 2
        parityTwo(arrayOf(4,2,5,7))

        //1331 - ran transformation
        rankTransformation(arrayOf(40,10,20,30,30))

        //1200 - mini diff
        miniDiff(arrayOf(4,2,1,3))

        //1464 - Maximum Product of Two Elements in an Array
        maxProduct(arrayOf(4,2,1,3))

        //1502 Can Make Arithmetic Progression From Sequence
        canMakeArithmeticProgression(arrayOf(4,2,1,3,5,6,7))

        //215 - kth largest element
        kLargestElement(arrayOf(3,2,1,5,6,4), 4)

        //442 - Find All Duplicates in an Array
        findDuplicates(arrayOf(4,3,2,7,8,2,3,1))

        //561 - array partition 1
        arrayPartitionOne(arrayOf(1,4,3,2))

        //167 - Two Sum II - Input Array Is Sorted
        twoSumTwo(arrayOf(2,7,11,15), 9)

        //283 move zeros
        moveZeros(arrayOf(0,1,0,3,12))

        //1221 - relative sort array
        relativeSorting(arrayOf(28, 6, 22, 8, 44, 6, 8), arrayOf(22, 28, 8, 6))

        //countTriplets
        //https://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/
        countTriplets(intArrayOf(-2, 0, 1, 3), 2)
    }

    fun countTriplets(arr: IntArray, t: Int) {
        var count = 0

        arr.sort()

        for(i in 0..arr.size - 2) {
            var j = i + 1
            var k = arr.size - 1

            while(j < k) {
                if(arr[i] + arr[j] + arr[k] >= t) {
                    k--
                } else {
                    count += (k - j)
                    j++
                }
            }
        }

        print(count)
    }

    fun moveZeros(arr: Array<Int>): Unit {
        val size = arr.size
        var i = 0
        var j = i + 1

        while (j <= size -1) {

            if(arr[i] == 0) {

                var temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp

                j++

            }  else {

                i++
                j = i + 1

            }

        }
    }

    private fun twoSumTwo(numbers: Array<Int>, target: Int): IntArray {
        var start = 0
        var end: Int = numbers.size - 1
        while (start < end) {
            if (numbers.get(start) + numbers.get(end) == target) break
            if (numbers.get(start) + numbers.get(end) < target) start++
            else end--
        }
        return intArrayOf(start + 1, end + 1)
    }

    private fun arrayPartitionOne(nums: Array<Int>): Int {
        val sortedArray = nums.sortedArray()
        var sum = 0
        for (i in 0..sortedArray.size - 1 step 2) {
            sum += sortedArray[i]
        }

        return sum
    }

    private fun findDuplicates(nums: Array<Int>) : List<Int> {
        val sortedArray = cyclicSort(nums)

        val list = mutableListOf<Int>()
        var i = 0

        while (i < sortedArray.size - 1) {
            if(i + 1 != sortedArray[i]) {
                list.add(sortedArray[i])
            }
            i++
        }

        return list
    }

    private fun kLargestElement(nums: Array<Int>, k: Int) : Int {
        val size = nums.size
        val index = size - k

        val sortedArray = nums.sortedArray()
        return sortedArray[index]
    }

    fun canMakeArithmeticProgression(arr: Array<Int>): Boolean {
        val sortedArray = arr.sortedArray()
        val diff = sortedArray[0] - sortedArray[1]

        for(i in 2..sortedArray.size - 2) {
            if(sortedArray[i] - sortedArray[i + 1] == diff){
                continue
            } else {
                return false
            }
        }

        return true
    }

    private fun maxProduct(nums: Array<Int>): Int {
        //Find the max 2 numbers.
        var mx1 = Int.MIN_VALUE
        var mx2 = mx1
        for (n in nums) {
            if (n > mx1) {
                mx2 = mx1
                mx1 = n
            } else if (n > mx2) {
                mx2 = n
            }
        }
        return (mx1 - 1) * (mx2 - 1)
    }

    private fun miniDiff(arr: Array<Int>) : MutableList<List<Int>> {
        var diff = Int.MAX_VALUE
        val sortedArray = arr.sortedArray()
        val result =  mutableListOf<List<Int>>()

        for (i in 0..sortedArray.size - 2) {
            if(sortedArray[i + 1] - sortedArray[i] < diff)
                diff = sortedArray[i + 1] - sortedArray[i]
        }

        for (i in 0..sortedArray.size - 2) {
            if(sortedArray[i + 1] - sortedArray[i] == diff){
                val newList = mutableListOf<Int>()
                newList.add(sortedArray[i])
                newList.add(sortedArray[i+1])
                result.add(newList)
            }
        }

        return result
    }

    private fun relativeSorting(arr1: Array<Int>, arr2: Array<Int>) : IntArray {
        val size1 = arr1.size
        val result = IntArray(size1)

        //create frequency map
        val frequencyMapTemp = linkedMapOf<Int, Int>()
        for (item in arr1) {
            frequencyMapTemp[item] = frequencyMapTemp[item]?.plus(1) ?: 1
        }

        val frequencyMap = frequencyMapTemp.toSortedMap()

        var resultIndex = 0
        for (map in frequencyMap) {
            frequencyMap[map.key] = frequencyMap[map.key]?.minus(1) ?: 0
            result[resultIndex] = map.key
            resultIndex++

            val frequency = frequencyMap[map.key] ?: 0
            if(frequency < 1) {
                frequencyMap.remove(map.key)
            }
        }

        for (map in frequencyMap) {
            frequencyMap[map.key] = frequencyMap[map.key]?.minus(1) ?: 0
            result[resultIndex] = map.key
            resultIndex++
        }

        return result
    }

    private fun rankTransformation(nums: Array<Int>) : IntArray {
        val originalIndex = mutableMapOf<Int, Int>()
        val resultArray = IntArray(nums.size)
        val sortedArray = nums.sortedArray()

        for (i in 0..nums.size - 1) {
            originalIndex.put(nums[i], i)
        }

        for (i in 0..sortedArray.size - 1) {
            val originalIndexOfElement = originalIndex[sortedArray[i]] ?: 0
            resultArray[originalIndexOfElement] = i + 1
        }

        return resultArray
    }

    private fun parityTwo(nums: Array<Int>) : IntArray {
        val result = IntArray(nums.size)
        val size = nums.size
        var i = 0

        //traverse array 2 times
        // 1 for even and 2 of odd

        return result
    }

    private fun maxThreeNo(nums: Array<Int>): Int {
        val size = nums.size


        val sortedArray = nums.sortedArray()
        var lastThreeProduct = 1
        val first = sortedArray[0]
        val second = sortedArray[1]
        val last = sortedArray[size - 1]

        for(i in size - 1 downTo size - 3) {
            lastThreeProduct *= sortedArray[i]
        }

        val edgeCaseProduct = first * second * last

        return maxOf(lastThreeProduct, edgeCaseProduct)
    }

    private fun interSection2(nums1: Array<Int>, nums2: Array<Int>): IntArray {
        // sort the bigger array
        // then iterate on small array and search in bigger array using binary search
        // and add numbers if they are present in int array

        val size1 = nums1.size
        val size2 = nums2.size
        val result = hashSetOf<Int>()

        if(size1 >= size2) {
            val sortedArray = nums1.sortedArray()
            for (item in nums2) {
                val searchResult = binarySearchArray(sortedArray,sortedArray.size - 1, item)
                if(searchResult != -1)
                    result.add(searchResult)
            }
        } else {
            val sortedArray = nums2.sortedArray()
            for (item in nums1) {
                val searchResult = binarySearchArray(sortedArray,sortedArray.size - 1, item)
                if(searchResult != -1)
                    result.add(searchResult)
            }
        }

        return result.toIntArray()
    }

    private fun binarySearchArray(nums: Array<Int>, endIndex: Int, targetNo: Int): Int {
        var start = 0
        var end = endIndex


        while(start <= end) {
            val mid = start + (end - start)/2

            if(nums[mid] == targetNo)
                return nums[mid]

            if(targetNo < nums[mid]) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        return -1
    }

    private fun interSection(nums1: Array<Int>, nums2: Array<Int>): IntArray {
        val set1 = hashSetOf<Int>()
        for (item in nums1)
            set1.add(item)

        val set2 = hashSetOf<Int>()
        for (item in nums2)
            set2.add(item)

        val resultArray = mutableListOf<Int>()

        for (item in set1){
            if(set2.contains(item)){
                resultArray.add(item)
            }
        }

        return resultArray.toIntArray()
    }

    private fun majorityElement(nums: Array<Int>) : Int {
        val frequencyMap = hashMapOf<Int, Int>()

        for (item in nums) {
            val currentFrequency = frequencyMap[item]?.plus(1) ?: 1

            if(currentFrequency > nums.size/2)
                return item

            frequencyMap[item] = currentFrequency
        }

        return -1
    }

    fun missingNumber(arr: Array<Int>): Int {
        var i = 0
        while (i < arr.size) {
            val correct = arr[i]
            if (arr[i] < arr.size && arr[i] != arr[correct]) {
                swap(arr, i, correct)
            } else {
                i++
            }
        }

        // search for first missing number
        for (index in arr.indices) {
            if (arr[index] != index) {
                return index
            }
        }

        // case 2 where last number is missing
        return arr.size
    }

    fun swap(arr: Array<Int>, first: Int, second: Int) {
        val temp = arr[first]
        arr[first] = arr[second]
        arr[second] = temp
    }

    fun cyclicSort(array: Array<Int>): Array<Int> {
        var i = 0
        while (i < array.size) {
            val correct = array[i] - 1
            if (array[i] != array[correct]) {
                swap(array, i, correct)
            } else {
                i++
            }
        }

        return array
    }

    fun swap(arr: IntArray, first: Int, second: Int) {
        val temp = arr[first]
        arr[first] = arr[second]
        arr[second] = temp
    }

}