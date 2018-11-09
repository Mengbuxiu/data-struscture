package link.staticlink;

import java.util.Scanner;

/*
 *	link 中保存着两个链表,一个是已经放入数据的链表(表头由head记录)，一个是空闲未放入数据的链表(表头由current记录)
 */
public class StaticLink {

    element[] link = null;		//保存数据的数组
    int current = 0;			//记录下一个可用空闲容器的下标
    int head = 0;				//记录当前静态链表头的下标
    int length = 0;				//记录当前装有用数据的大小
    int MAXSIZE = 0;			//记录静态链表可装的最大空间

    class element				//相当于链表中的空间单元
    {
        int data;				//记录存入的数据
        int cur;				//记录下一个数据的下标
    }

    public StaticLink(int size)		//初始化静态列表
    {
        link = new element[size];

        for(int i = 0; i < size; i++)
        {
            link[i] = new element();
            link[i].data = -1;
            link[i].cur = i + 1;	//下标0的单元指向下标1的单元
        }
        current = 0;
        head = 0;
        length = 0;
        MAXSIZE = size;

        System.out.println("初始化静态链表...");
    }

    public int add(int data)		//向当前静态链表的后面添加一个数据
    {
        if(length < MAXSIZE)		//检查是否还有空闲空间可以分配数据
        {
            link[current].data = data;		//将当前空闲单元存入数据
            current = link[current].cur;	//将current指向下一个空闲单元
            length++;						//实际使用空间增加1
            System.out.println("成功添加数据:" + data);
            return 0;
        }
        System.out.println("添加数据失败");
        return -1;
    }

    public int delete()								//删除当前静态链表最后一个数据
    {
        if(length > 0 && link != null)				//判断是否有数据可以删除
        {
            int temp = current;						//暂时记录当前空闲单元的下标
            current = getTrulyIndex(length - 1);	//将要删除的使用单元加入到空闲单元链中
            link[current].cur = temp;				//将这个刚加入的单元链接上空闲空间
            length--;								//实际使用空间自减1
            System.out.println("成功删除数据:" + link[current].data);
            return 0;
        }
        System.out.println("删除数据失败");
        return -1;
    }

    public int insert(int data, int index)			//从指定的下标的地方插入数据
    {
        if(length < MAXSIZE && link != null && index < length && index >= 0)	//判断是否有空间可以插入新数据和判断当前输入的下标是否在范围内
        {
            int tCurrent = current;					//保存当前空闲单元的下标
            current = link[current].cur;			//将current指向下一个空闲单元
            //上面这两个语句。相当于在空闲链中拿出一个单元出来储存数据

            if(index == 0)							//当要插入的位置在最前面
            {
                link[tCurrent].data = data;			//将拿出的空闲单元存入数据
                link[tCurrent].cur = head;			//将这个单元指向原来的头位置下标
                head = tCurrent;					//重新设置head指向的位置
            }
            else if(index == length - 1)			//当要插入的是在静态链表末端
            {
                link[tCurrent].data = data;			//放入数据
            }
            else									//要插入的位置前后都有空间单元的时候
            {
                int preIndex = getTrulyIndex(index - 1);	//获取要插入的前一个空间单元的index

                link[tCurrent].cur = link[preIndex].cur;	//将要插入位置的前一个单元原来的cur赋值给现在要插入的单元的cur(cur:保存下一个单元的位置下标)
                link[tCurrent].data = data;					//放入数据
                link[preIndex].cur = tCurrent;				//将要插入位置的前一个单元指向现在要插入单元的位置
            }
            length++;										//大小自增1
            System.out.println("成功在 " + index + " 插入数据: " + data);
            return 0;
        }
        System.out.println("在" + index + "插入数据失败");
        return -1;
    }

    public int delete(int index)						//删除给定位置的数据
    {
        if(length > 0 && link != null && index >= 0 && index <= length - 1)		//判断是否有数据可以删除
        {
            if(index == 0)								//判断是否要删除第一个单元
            {
                int tHead = head;						//保存当前的head
                head = link[head].cur;					//将head指向一下单元
                link[tHead].cur = current;				//将删除的单元加入空闲链表
                current = tHead;						//将current指向空闲链的第一个单元
            }
            else if(index == (length - 1))				//判断是否要删除最后一个单元
            {
                int last = getTrulyIndex(length - 1);	//获取最后一个单元的位置
                if(last != -1)							//如果获取位置成功
                {
                    link[last].cur = current;			//将删除的单元加入空闲链中
                    current = last;						//将current指向空闲链的第一个单元
                }
            }
            else									//判断是否要删除第一个到最后一个(单元)之间的单元
            {
                int preIndex = getTrulyIndex(index - 1); //获取要删除单元的前一个单元的位置
                int temp = current;						 //保存current的值
                current = link[preIndex].cur;			 //将要删除的单元链入空闲链
                link[preIndex].cur = link[current].cur;	 //将上一个单元重新指向到下一个单元,例如A、B、C 删除 B ,这个语句相当于 ##将 A 从指向 B 重新指向为 C ##(即改变A的指向)
                link[current].cur = temp;				 //将删除单元链入空闲链
            }
            length--;									 //大小自减1
            System.out.println("成功在 " + index + " 删除数据: " + link[current].data);
            return 0;
        }
        System.out.println("在" + index + "删除数据失败");
        return -1;
    }



    public int getTrulyIndex(int index)				//获取给定下标的实际下标
    {
        int i = 0;
        int tHead = head;
        if(link != null)
        {
            while(i < index)						//这个是实现的重要代码
            {
                tHead = link[tHead].cur;			//每次执行这个语句的时候获取下一个真正的位置下标
                i++;
            }
            if(i == index)							//判断否是获得真正的下标
            {
                return tHead;						//返回真正的下标
            }
        }

        return -1;
    }

    public int get(int index)						//获取给定下标储存数据
    {
        int tIndex = getTrulyIndex(index);
        if(tIndex != -1 && link != null)
        {
            return link[tIndex].data;
        }

        return -1;
    }


    public static void main(String[] args)			//这里的测试代码,与静态链表没关系
    {
        int size = -1;
        int[] original = null;
        if(args != null && args.length == 1)
        {
            size = Integer.valueOf(args[0]);
        }
        else
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("输入测试大小<大于 10以上 >: ");
            size = sc.nextInt();
        }
        while(size == -1)
        {
            System.out.print("输入错误,请重新输入: ");
            Scanner sc = new Scanner(System.in);
            size = sc.nextInt();
        }

        original = new int[size - 5];

        StaticLink sl = new StaticLink(size);
        for(int i = 0; i < size - 5; i++)
        {
            sl.add(i);
            original[i] = i;
        }

        sl.delete();
        if(size > 3)
        {
            sl.insert(20, 2);
            sl.delete(1);
        }
        else
        {
            sl.insert(20, 0);
            sl.delete(0);
        }

        System.out.print("\n操作前的链表内容: ");
        for(int i = 0; i < original.length; i++)
        {
            System.out.printf("%d ",original[i]);
        }

        System.out.print("\n操作后的链表内容: ");
        for(int j = 0; j < sl.length; j++)
        {
            System.out.printf("%d ",sl.get(j));
        }
    }

}
/*
---------------------
        作者：failure01
        来源：CSDN
        原文：https://blog.csdn.net/failure01/article/details/8668025?utm_source=copy
        版权声明：本文为博主原创文章，转载请附上博文链接！
*/
