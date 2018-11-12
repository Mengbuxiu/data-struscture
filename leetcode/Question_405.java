package leetcode;

/**
 * 删除整数的k个数字，获得删除后的最小值
 * 算法复杂度O(kn),
 */
public class Question_405 {
    public static String removeKDigits(String num, int k){
        String numNew = num;
        for (int i=0;i<k;i++){
            boolean hasCut = false;
            //从左向右遍历，找到比自己右侧数字大的数字并删除
            for (int j = 0; j < numNew.length()-1; j++) {
                if (numNew.charAt(j) > numNew.charAt(j+1)){
                    numNew = numNew.substring(0, j) +
                            numNew.substring(j+1, numNew.length());
                    hasCut = true;
                    break;
                }
            }
            //如果没有找到要删除的数字，则删除最后一个数字
            if (!hasCut){
                numNew = numNew.substring(0, numNew.length()-1);
            }

           //清除整数左侧的数字0
            numNew = removeZero(numNew);
        }
        //如果整数的所有数字都被删除了，直接返回0
        if(numNew.length() == 0){
            return "0";
        }
        return  numNew;
    }

    private static String removeZero(String num) {
        for (int i = 0; i < num.length()-1; i++) {
            if (num.charAt(i) != '0'){
                break;
            }
            num = num.substring(1, num.length());
        }
        return num;
    }
    public static void main(String[] args){
        System.out.println(removeKDigits("1593212",3));
    }
}
