package leetcode;

/**
 * 我们假设有一个数组 nums
 * 其中有两个元素仅出现了一次，其他元素都出现了两次
 * 如何采用O(n)的时间复杂度以及常量的空间复杂度找出这两个数字
 */

public class BitOperation {
    public int[] singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        int xor = 1;
        while((xor & res) == 0) {
            xor = xor << 1;
        }
        int res0 = 0, res1 = 0;
        for (int num : nums) {
            if((num & xor) == 1){
                res0 = res0 ^ num;
            } else {
                res1 = res1 ^ num;
            }
        }
        return new int[] {res0, res1};
    }
}
