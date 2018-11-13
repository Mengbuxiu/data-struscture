package stack_appliaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * @author zl
 * @date 2018-11-13 14:49:31
 *
 */

public class EntryAllSalary {

	public void entry(){
		 //声明Connection对象
	    Connection con;
	    //驱动程序名
	    String driver = "com.mysql.jdbc.Driver";
	    //URL指向要访问的数据库名mydata
	    String url = "jdbc:mysql://192.168.16.159:3306/eos";
	    //MySQL配置时的用户名
	    String user = "xxx";
	    //MySQL配置时的密码
	    String password = "xxx";
	    //遍历查询结果集
	    try {
	        //加载驱动程序
	        Class.forName(driver);
	        //1.getConnection()方法，连接MySQL数据库！！
	        con = DriverManager.getConnection(url, user, password);
	        if (!con.isClosed()) {
	            System.out.println("Succeeded connecting to the Database!");
	        }
	        Stack<Map> stack = new Stack<>();
	        Statement statement2 = con.createStatement();
	            String sql = "select basicSalary,reqId from hcm_salary_change_record";
	            ResultSet rs = statement2.executeQuery(sql);
	            while(rs.next()){
	            	String salary = rs.getString("basicSalary");
	            	String reqId = rs.getString("reqId");
	            	Map<String,String> map = new HashMap<>();
	            	map.put("salary", salary);
	            	map.put("reqId", reqId);
	            	stack.push(map);
	            }
	            rs.close();
	            System.out.println("栈填充数据完毕！");
	            while(!stack.isEmpty()){
	            	Map map = stack.pop();
	            	String salary = (String) map.get("salary");
	            	double entrysalary = new SalaryUtil().encryptMoney(Double.valueOf(salary)); //一个简单的加密类
	            	String reqId = (String) map.get("reqId");
	            	Statement statement3 = con.createStatement();
	            	String sql3 = "UPDATE `table` SET `basicSalary`='"+ entrysalary +"' WHERE (`reqId`='"+ reqId +"')";
	            	statement3.executeUpdate(sql3);
	            }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        System.out.println("数据库数据成功获取！！");
	    }
	}
}

