package removeDuplicates


/************************************************************
 *  Name:         Alex Cruz
 *  Date:         6/28/23
 *  Assignment:
 *  Class Number: CIS 282
 *  Description:
 ************************************************************/


fun main() {
    //    Input: nums = [1,1,2]

//    val test = intArrayOf(1,1,2,3,4,4)
//    val solution = Solution()
//    println(solution.removeDuplicates(test))
//    val test = intArrayOf(1,3,5,6)
    val test = intArrayOf(1,3,5,6)
    val target = 2
    val solution = Solution()
    println(solution.searchInsert(test,target))



}

//class Solution {
//    fun removeDuplicates(nums: IntArray): Int {
//        var k = 0
//        var checkInts = mutableListOf<Int>()
//        for(i in nums) {
//            if (!checkInts.contains(i)) {
//                checkInts.add(i)
//                nums[k] = i
//                k++
//
//            }
//        }
//        return k
//    }
//}

class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var k = 0
        var valid = nums.contains(target)
        for(i in nums.indices){
            if (!valid) {
                if (nums[i] < target) {
                    k = i + 1
                }
            }else if(nums[i] == target) {
                k = nums.indexOf(nums[i])
                break
            }
        }
        return k
    }
}