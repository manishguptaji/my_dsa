package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Array2D : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_array2_d)

        //spiral matrix - 54
        spiralMatrixOne(arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)))

        //wave traversal
        //https://www.youtube.com/watch?v=_olQ9Rrnm_c&list=PL-Jc9J83PIiFkOETg2Ybq-FMuJjkZSGeH&index=5&ab_channel=Pepcoding
        waveTraversal(arrayOf(arrayOf(1,2,3), arrayOf(4,5,6)))

        //in place - transpose of a matrix
        transpose(arrayOf(arrayOf(1,2,3),arrayOf(4,5,6),arrayOf(7,8,9)))


        //search in 2d array
        search(arrayOf(arrayOf(1,2,3), arrayOf(4,5,6), arrayOf(7,8,9), arrayOf(10,11,12)), 13)
    }

    fun search(arr: Array<Array<Int>>, k: Int) {
        var row = arr.size - 1
        var col = arr[0].size - 1

        var i = 0
        var j = col

        while(i < arr.size && j >= 0) {

            if(k == arr[i][j]) {
                print(i)
                print(j)
                return
            }

            if(k < arr[i][j]) {
                j--
            } else {
                i++
            }
        }

        print("not found")
    }

    fun transpose(arr: Array<Array<Int>>) {
        var row = arr.size - 1
        var col = arr[0].size - 1

        println(arr[0].contentToString())
        println(arr[1].contentToString())
        println(arr[2].contentToString())

        println("-----------------------")

        for(i in 0..row) {
            for(j in 0..col) {
                var temp = arr[i][j]
                arr[i][j] = arr[j][i]
                arr[j][i] = temp
            }
        }

        println(arr[0].contentToString())
        println(arr[1].contentToString())
        println(arr[2].contentToString())
    }

    private fun waveTraversal(arr: Array<Array<Int>>) {
        var row = arr.size - 1
        var col = arr[0].size - 1

        for(j in 0..col) {
            if(j % 2 == 0) {
                for(i in 0..row) {
                    println(arr[i][j])
                }
            } else {
                for(i in row downTo 0) {
                    println(arr[i][j])
                }
            }
        }
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
}