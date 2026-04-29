package SchoolWork.Xidian;

import java.util.Calendar;
import java.util.Scanner;
public class CalendarOutput
{
	public static int Days(int year,int month)
	{
		int days=0;
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		{
			days = 31;
		}
		else if(month==4||month==6||month==9||month==11)
		{
			days = 30;
		}
		else if(month==2)
		{
			if(year%4==0||year%400==0&&year%100!=0)
			{
				days = 29;
			}
			else
			{
				days = 28;
			}
		}
		return days;
	}

	// 新增：计算两个日期相差的天数
	public static int getDaysBetween(int year1, int month1, int day1, int year2, int month2, int day2)
	{
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(year1, month1-1, day1);
		cal2.set(year2, month2-1, day2);

		long time1 = cal1.getTimeInMillis();
		long time2 = cal2.getTimeInMillis();

		long diffMillis = Math.abs(time2 - time1);
		int diffDays = (int)(diffMillis / (1000 * 60 * 60 * 24));

		return diffDays;
	}

	public static void main(String[] args)
	{
		int i=0,year=0,month=0,days=0,week=0,choice=0,j=0;
		Scanner sc  = new Scanner(System.in);
		Calendar newc = Calendar.getInstance();
		while(true)
		{
			System.out.println("Menu:\n1.查询某天星期\n2.打印某年日历\n3.计算两个日期相差天数\n4.退出\n请输入数字选择");
			choice=sc.nextInt();
			if(choice==1)
			{
				System.out.println("请输入年份：");
				year=sc.nextInt();
				System.out.println("请输入月份：");
				month=sc.nextInt();
				System.out.println("请输入日期：");
				days=sc.nextInt();
				newc.set(year,month-1,days);
				week = newc.get(Calendar.DAY_OF_WEEK);
				switch(week)
				{
					case 1:
						System.out.print("星期日\n");
						break;
					case 2:
						System.out.print("星期一\n");
						break;
					case 3:
						System.out.print("星期二\n");
						break;
					case 4:
						System.out.print("星期三\n");
						break;
					case 5:
						System.out.print("星期四\n");
						break;
					case 6:
						System.out.print("星期五\n");
						break;
					case 7:
						System.out.print("星期六\n");
						break;

				}
			}
			else if(choice==2)
			{
				System.out.println("请输入年份");
				year = sc.nextInt();
				for(j=0;j<12;j++)
				{
					System.out.println("-------------"+(j+1)+"--------------");
					days = Days(year,j+1);
					newc.set(year,j,1);
					week = newc.get(Calendar.DAY_OF_WEEK)-1;
					int[] date =new int[40];
					for(i=1;i<=days;i++)
					{
						date[week]=i;
						week++;
					}
					System.out.println("日\t一\t二\t三\t四\t五\t六");
					for(i=0;i<week;i++)
					{
						if(date[i]!= 0)
						{
							System.out.print(date[i]+"\t");
							if((i+1)%7==0)
							{
								System.out.println("\n");
							}
						}
						else
						{
							System.out.print(" "+"\t");
						}

					}
					System.out.println("\n");
				}

			}
			else if(choice==4) break;
				// 新增：计算两个日期相差天数
			else if(choice==3)
			{
				int year1, month1, day1, year2, month2, day2;
				System.out.println("请输入第一个日期：");
				System.out.println("年份：");
				year1 = sc.nextInt();
				System.out.println("月份：");
				month1 = sc.nextInt();
				System.out.println("日期：");
				day1 = sc.nextInt();

				System.out.println("请输入第二个日期：");
				System.out.println("年份：");
				year2 = sc.nextInt();
				System.out.println("月份：");
				month2 = sc.nextInt();
				System.out.println("日期：");
				day2 = sc.nextInt();

				int diffDays = getDaysBetween(year1, month1, day1, year2, month2, day2);
				System.out.println(year1 + "年" + month1 + "月" + day1 + "日 与 " +
						year2 + "年" + month2 + "月" + day2 + "日 相差 " + diffDays + " 天\n");
			}
			else
			{
				System.out.println("无效选择，请重新输入！");
			}
		}
		sc.close();
	}


}