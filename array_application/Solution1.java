package array_application;
/**
 * 计算两个大整数的和
 * 这是第一版，时间复杂度为O(n)
 */
public class Solution1 {
    public String calculateBigInteger(String str1, String str2){
        //1.确定最长的字符,以最长的长度+1来创建数组
        int size = str1.length() >= str2.length() ? str1.length() + 1 : str2.length() + 1;
        int[] result = new int[size];
        //2.将字符串转换为数组并倒序排列
        int[] arr1 = new int[size - 1];
        char[] chr1 = str1.toCharArray();
        for (int i = chr1.length - 1 ; i >= 0 ; i--) {
            arr1[chr1.length - 1 - i] = chr1[i] - '0';
        }
        int[] arr2 = new int[size - 1];
        char[] chr2 = str2.toCharArray();
        for (int i = chr2.length - 1 ; i >= 0 ; i--) {
            arr2[chr2.length - 1 - i] = chr2[i] - '0';
        }
        //3.计算和
        for (int i = 0; i < size - 1 ; i++) {
            int num = arr1[i] + arr2[i];
            if (num % 10 != 0 && num > 10){
                //进位加一
                result[i] += num % 10;
                result[i + 1] += num / 10;
            }else
                result[i] += num;
        }
        //4.拼接result数组
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = size - 1  ; i >= 0 ; i--) {
            stringBuilder.append(result[i]);
        }
        return stringBuilder.toString().startsWith("0")
                ? stringBuilder.toString().substring(1) : stringBuilder.toString();
    }
}
