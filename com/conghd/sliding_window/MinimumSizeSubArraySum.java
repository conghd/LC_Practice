package com.conghd.sliding_window;
import com.conghd.Testable;

public class MinimumSizeSubArraySum implements Testable {
    public int minSubArrayLen(int target, int[] nums) {
        // Check whether the each single element is equal or bigger than target
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return 1;
            }
        }

        if (nums.length == 1) {
            return 0;
        }

        // Accumulated sums
        int [] accSum = new int[nums.length + 1];
        accSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            accSum[i] = accSum[i-1] + nums[i-1];
        }

        int len = nums.length;
        int minLen = Integer.MAX_VALUE;
        int size = 1;
        while (len > 0) {
            len = (len + 1) / 2;
            size *= 2;

            for (int i = 0; i <= len-1; i++) {
                int start = i * size;
                int end = (i + 1) * size - 1;
                end = (end < nums.length) ? end : nums.length - 1;

                int subTotal = accSum[end + 1] - accSum[start];;
                if (subTotal == target) {
                   if (minLen > size) minLen = size; 
                } else if (subTotal > target) {
                    for (int s = size/2 + 1; s <= size; s++) {
                        if (s > minLen) break;
                        for (int j = start; j <= end - s + 1; j++) {
                            if (accSum[j + s] - accSum[j] >= target && minLen > s) {
                                minLen = s;
                                break;
                            }
                        }
                    }
                }
            }

            if (minLen != Integer.MAX_VALUE) {
                return minLen;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public void test() {
        int target = 11;
        //int[] nums = {2, 3, 1, 2, 4, 3};
        int[] nums = {1, 2, 3, 4, 5} ;

        int result = minSubArrayLen(target, nums);
        
        System.out.println("Result: " + result);
        
    }
}
