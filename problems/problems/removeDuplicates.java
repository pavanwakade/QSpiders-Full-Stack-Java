
class removeDuplicates {
    public int[] removeDuplicate(int[] nums) 
    {
        int[] r=new int[nums.length]
       for(int i =0 ;i<nums.length;i++)
       {
        
        for(int j= i ;j<nums.length;j++){

        if(nums[i]==nums[j]){
        r[i]=-1;
       }
       else{
        r[i]=nums[j];
       }
   }

        return result; 
    }

    public static void main(String[] args) {
        int nums[]={0,0,1,1,1,2,2,3,3,4};
        removeDuplicate(nums);
    }
}