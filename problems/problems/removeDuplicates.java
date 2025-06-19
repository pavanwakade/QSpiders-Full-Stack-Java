import java.util.Arrays;

class removeDuplicates {
  public static int[] removeDuplicate(int[] nums) {
    int[] r = new int[nums.length];
    int ind=0;
    r[ind++] = nums[0];
    for (int i = 0; i < nums.length; i++) {
    

      for (int j = i; j < nums.length; j++) {
        
        if (nums[i] == nums[j]) {
          r[ind++] = -1;
        } else {
          r[ind++] = nums[j];
        }
      }
    }
    return r;
  }

  public static void main(String[] args) {
    int nums[] = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    System.out.println(Arrays.toString(removeDuplicate(nums)));
  }
}
