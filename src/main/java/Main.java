import kz.syllabus.entity.Syllabus;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Main {
    @Nullable
    public static int[] sumTwo(int[] nums, int target) {
        HashMap map = new HashMap();
        for( int i = 0; i < nums.length; i++) {
            int k = target - nums[i];
            if(map.containsKey(k)) {
                return new int[]{map.get(k), i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        System.out.println(Arrays.toString(sumTwo(nums, target)));

    }
}
