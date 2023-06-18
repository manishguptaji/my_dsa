package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.LinkedList
import java.util.*

class BinaryTree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_tree)

        //https://www.youtube.com/watch?v=-DzowlcaUmE   (apna college binary tree lecture)
        var bt = BinaryTree()
        var arr = arrayOf(1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1)
        var node = bt.buildTree(arr)
        println(bt.getNodeCount(node))
    }

    class Node (value: Int) {
        var value: Int = 0
        var left: Node? = null
        var right: Node? = null

        init{
            this.value = value
        }
    }

    class BinaryTree {
        var index = -1

        fun buildTree(arr: Array<Int>) : Node? {
            index++

            val value = arr[index]
            if(value == -1)
                return null

            var newNode = Node(value)
            newNode?.left = buildTree(arr)
            newNode?.right = buildTree(arr)

            return newNode
        }

        fun getPreOrder(root: Node?) {
            if(root == null) {
                print(" " + -1 + " ")
                return
            }

            print(" " +root?.value + " ")
            getPreOrder(root?.left)
            getPreOrder(root?.right)
        }

        fun getPostOrder(root: Node?) {
            if(root == null) {
                print(" " + -1 + " ")
                return
            }

            getPostOrder(root?.left)
            getPostOrder(root?.right)
            print(" " +root?.value + " ")
        }

        fun getInOrder(root: Node?) {
            if(root == null) {
                print(" " + -1 + " ")
                return
            }

            getInOrder(root?.left)
            print(" " +root?.value + " ")
            getInOrder(root?.right)
        }

        fun levelOrder(root: Node?){
            var queue: Queue<Node> = LinkedList<Node>()
            queue.add(root)
            queue.add(null)

            while(!queue.isEmpty()) {
                var n = queue.remove()

                if(n != null) {
                    print("" +n?.value + " ")

                    if(n?.left != null)
                        queue.add(n?.left)

                    if(n?.right != null)
                        queue.add(n?.right)
                } else {
                    println()

                    if(queue.isEmpty())
                        break

                    queue.add(null)
                }
            }
        }

        fun getNodeCount(root: Node?) : Int {
            if(root == null) {
                return 0
            }

            var leftCount = getNodeCount(root?.left)
            var rightCount = getNodeCount(root?.right)

            return leftCount + rightCount + 1
        }
    }
}