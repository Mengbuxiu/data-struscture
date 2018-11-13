/**
 * 
 */
package stack_appliaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Properties;


/**
 * @author zl
 * 加密解密薪资
 * @date 2018-10-31 10:37:35
 *
 */
public class SalaryUtil {
	 private double KEY;

	    /**
	     * 加密
	     * @param money
	     * @return
	     */
	    public double encryptMoney(double money){
	        money *= 100;//去浮点化
	        money += KEY;
	        return money;
	    }

	    /**
	     * 解密
	     * @param money
	     * @return
	     */
	    public double decryptMoney(double money){
	        money -= KEY;
	        money /= 100;//浮点化
	        DecimalFormat df = new DecimalFormat("0.00");
	        String s = df.format(money);
	        money = Double.parseDouble(s);
	        return money;
	    }
	    public SalaryUtil(){
	        String PRIVATE_KEY_PATH;
	        //初始化路径
	        if (!checkSys()){
	            PRIVATE_KEY_PATH = "D:/private_key/key.txt";
	        }else {
	            PRIVATE_KEY_PATH = "/data/private_key/key.txt";
	        }
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(PRIVATE_KEY_PATH));//构造一个BufferedReader类来读取文件
	            String s;
	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	                if(s.length() < 7)
	                    throw new IllegalArgumentException("key's length is not enough, please check it");
	                KEY = Double.parseDouble(s);

	            }
	            br.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    /**
	     * 判断操作系统
	     * @return
	     */
	    public static boolean checkSys(){
	        Properties properties = System.getProperties();
	        String os = properties.getProperty("os.name");
	        if (os != null && os.toLowerCase().contains("linux")){
	            return true;
	        }
	        return false;
	    }
}
