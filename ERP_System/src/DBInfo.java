import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DBInfo 
{
  static Connection con;
  static Vector<String> header;
  static Vector<Vector<String>> data;
  static
  {
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/erpsystem","root","rat");  
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  //get all subjects
  public static Vector<String> getSubjects()
  {
	  Vector<String> v=new Vector<>();
	  String query="select sname from subject order by sname";
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			v.add(res.getString(1));
		}
		v.add(0, "Select");
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  return v;
  }
  
  //get all table names in database
  public static Vector<String> getTables()
  {
	  Vector<String> v=new Vector<>();
	  String query="show tables";
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			v.add(res.getString(1).toUpperCase());
		}
		v.remove(0);
		v.add(0, "Select");
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  
	  return v;
  }
  
  
  //get a particular table data
  public static void getTableData()
  {  
	header=new Vector<>();
	data=new Vector<>();
	
	String query="select * from "+SelectTable.tablename;
	//String query="select * from ?";						//  Error1
	try 
	{
		PreparedStatement ps=con.prepareStatement(query);
		//ps.setString(1, SelectTable.tablename);			//  Error1	instead of above 2 when written error
		ResultSet res=ps.executeQuery();
		ResultSetMetaData rsmd=res.getMetaData();
		
		int colcount=rsmd.getColumnCount();
		for(int i=1;i<=colcount;i++)
		{
			String colname=rsmd.getColumnName(i);
			header.add(colname);
		}
		
		while(res.next())
		{
			Vector<String> records=new Vector<>();
			for(int i=1;i<=colcount;i++)
			{
				String value=res.getString(i);
				records.add(value);
			}
			data.add(records);
		}
	} 
	catch (SQLException e)
	{
		e.printStackTrace();
	}
  }
  
  //getting total classes
  public static int getOverallClasses(String fromdate,String todate)
  {
	  String query1="select count(*)/(select count(distinct rollno) from attendance) from attendance where date between ? and ?";
	  int totalclass=0;
	  try 
		{
			PreparedStatement ps1=con.prepareStatement(query1);
			ps1.setString(1, fromdate);
			ps1.setString(2, todate);
			ResultSet res1=ps1.executeQuery();
			while(res1.next())
			{
				totalclass=res1.getInt(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	  return totalclass;
  }
  
  //getting overall present
  public static int getOverallPresent(String rollno,String fromdate,String todate)
  {
	  String query2="select count(*) from attendance where rollno=? and attendance=1 and date between ? and ?";
	  int present=0;
	  try 
		{
			PreparedStatement ps2=con.prepareStatement(query2);
			ps2.setString(1, rollno);
			ps2.setString(2, fromdate);
			ps2.setString(3, todate);
			ResultSet res2=ps2.executeQuery();
			
			while(res2.next())
			{
				present=res2.getInt(1);
			}
		}
		catch (SQLException e) 
	  	{
			e.printStackTrace();
		}
	  return present;
  }
  
  
  
  //getting particular subject total classes
  public static int getTotalClass(int sid,String fromdate,String todate)
  {
	  String query="select count(sid)/(select count(distinct rollno) from attendance) from attendance where sid=? and date between ? and ?";
	  int subjecttotal=0;
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, sid);
		ps.setString(2, fromdate);
		ps.setString(3, todate);
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			subjecttotal=res.getInt(1);
		}
	  }
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  return subjecttotal;
  }
  
  
  //getting particular subject total present
  public static int getPresent(String roll,int sid,String fromdate,String todate)
  {
	  String query="select count(*) from attendance where rollno=? and sid=? and attendance=1 and date between ? and ?";
	  int subjectpresent=0;
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, roll);
		ps.setInt(2, sid);
		ps.setString(3, fromdate);
		ps.setString(4, todate);
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			subjectpresent=res.getInt(1);
		}
	  }
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  return subjectpresent;
  }
  
  // get notice titles
  public static Vector<String> getNoticeTitles()
  {
	  Vector<String> v=new Vector<>();
	  String query="select title from notice order by substr(title,7,4) desc,substr(title,4,2) desc,substr(title,1,2) desc";
	  try
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			v.add(res.getString(1));
		}
	  }
	  catch (SQLException e)
	  {
		e.printStackTrace();
	  }
	  return v;
  }
  
  //get holidays
  public static void getHolidays()
  {  
	header=new Vector<>();
	data=new Vector<>();
	
	String query="select * from holidays";
	try 
	{
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet res=ps.executeQuery();
		ResultSetMetaData rsmd=res.getMetaData();
		int colcount=rsmd.getColumnCount();
		
		header.add("Date");
		header.add("Name of Holidays");
		
		while(res.next())
		{
			Vector<String> records=new Vector<>();
			for(int i=1;i<=colcount;i++)
			{
				String value="";
				if(i==1)
				{
					String date=res.getString(i);
					String todate=res.getString(i+1);
					if(date.equals(todate))
						value=format(date);			//25/05/2016----->25 May
					else
						value=format(date)+" - "+format(todate); 
					records.add(value);
				}
				else if(i==2)
				{	
				}
				else
				{
					value=res.getString(i);
					records.add(value);
				}
			}
			data.add(records);
		}
	} 
	catch (SQLException e)
	{
		e.printStackTrace();
	}
  }
  //format date view 
  public static String format(String date)
	{
		String mon=date.substring(3,5);
		switch (mon) 
		{
			case "01": date=date.substring(0,2)+" January";
			break;
			case "02": date=date.substring(0,2)+" February";
			break;
			case "03": date=date.substring(0,2)+" March";
			break;
			case "04": date=date.substring(0,2)+" April";
			break;
			case "05": date=date.substring(0,2)+" May";
			break;
			case "06": date=date.substring(0,2)+" June";
			break;
			case "07": date=date.substring(0,2)+" July";
			break;
			case "08": date=date.substring(0,2)+" August";
			break;
			case "09": date=date.substring(0,2)+" September";
			break;
			case "10": date=date.substring(0,2)+" October";
			break;
			case "11": date=date.substring(0,2)+" November";
			break;
			case "12": date=date.substring(0,2)+" December";
			break;
			default:
			break;
		}
		return date;
	}
  //get all holiday names
  public static Vector<String> getHolidayNames()
  {
	  Vector<String> v=new Vector<>();
	  String query="select * from holidays order by 3";
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			v.add(res.getString(3));
		}
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  v.add(0, "Select");
	  return v;
  }
  
  //get Schedule--Student
  public static void getScheduleStudent(String year,String branch,String section)
  {
	  header=new Vector<>();
	  data=new Vector<>();
	  
	  String query="select * from schedule where year=? and branch=? and section=?";
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, year);
		ps.setString(2, branch);
		ps.setString(3, section);
		ResultSet res=ps.executeQuery();
		ResultSetMetaData rsmd=res.getMetaData();
		
		int colcount=rsmd.getColumnCount();
		for(int i=4;i<=colcount;i++)
		{
			String colname=rsmd.getColumnName(i);
			header.add(colname);
		}
		while(res.next())
		{
			Vector<String> records=new Vector<>();
			for(int i=4;i<=colcount;i++)
			{
				records.add(res.getString(i));
			}
			data.add(records);
		}
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }  
  }
  
  //get Lectures Time
  public static Vector<String> getLectureTime()
  {
	  Vector<String> v=new Vector<>();
	  String query="select * from schedule";
	  try 
	  {
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet res=ps.executeQuery();
		ResultSetMetaData rsmd=res.getMetaData();
		int colcount=rsmd.getColumnCount();
		for(int i=5;i<=colcount;i++)
		{
			v.add(rsmd.getColumnName(i));
		}
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  v.add(0, "Select");
	  return v;
  }
}
