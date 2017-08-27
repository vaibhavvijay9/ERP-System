import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class ViewAttendance_Student extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;
	static String rollno =null;
	static String name=null;
	private JTextField textfield_from;
	private JTextField textField_to;
	String fromdate="",todate="";
	JLabel label_3,label_4,label_6,label_2;
	JLabel label_13,label_14,label_15,label_16,label_17,label_18,label_19,label_20;
	JLabel label_21,label_22,label_23,label_24,label_25,label_26,label_27,label_28,label_29,label_30;
	JLabel label_31,label_32,label_33,label_34,label_35,label_36;
	private JLabel label_37;
	private JLabel label_38;
	
	
	public ViewAttendance_Student(JFrame frm) 
	{
		ViewAttendance_Student va=this;
		setTitle("Attendance");
		setResizable(false);
		setBounds(100, 100, 720, 510);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				if(frm.getClass().getName().equals("StudentRollNo"))
				{
					frm.dispose();
					va.dispose();
				}
				else
				{
					frm.setVisible(true);
					va.dispose();
				}				
			}
		});
		
		JLabel lblRollNo = new JLabel("Roll No.:");
		lblRollNo.setBounds(38, 26, 46, 14);
		contentPane.add(lblRollNo);
		
		JLabel label = new JLabel("");
		label.setBounds(107, 26, 92, 14);
		contentPane.add(label);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(38, 51, 46, 14);
		contentPane.add(lblName);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(107, 51, 154, 14);
		contentPane.add(label_1);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(38, 80, 46, 14);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(225, 80, 46, 14);
		contentPane.add(lblTo);
		
		label_2 = new JLabel("");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setBounds(38, 110, 161, 97);
		contentPane.add(label_2);
		
		JLabel lblOverallAttendance = new JLabel("Overall Attendance:");
		lblOverallAttendance.setBounds(65, 193, 119, 14);
		contentPane.add(lblOverallAttendance);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(296, 130, 65, 14);
		contentPane.add(lblTotal);
		
		JLabel lblPresent = new JLabel("Present:");
		lblPresent.setBounds(296, 160, 65, 14);
		contentPane.add(lblPresent);
		
		JLabel lblAbsent = new JLabel("Absent:");
		lblAbsent.setBounds(296, 190, 65, 14);
		contentPane.add(lblAbsent);
		
		label_3 = new JLabel("");
		label_3.setBounds(358, 130, 46, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setBounds(358, 160, 46, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(358, 180, 46, 14);
		contentPane.add(label_5);
		
		label_6 = new JLabel("");
		label_6.setBounds(358, 190, 46, 14);
		contentPane.add(label_6);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSubject.setBounds(38, 236, 46, 14);
		contentPane.add(lblSubject);
		
		JLabel label_7 = new JLabel("ADS");
		label_7.setBounds(38, 272, 46, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("OS");
		label_8.setBounds(38, 308, 46, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("CA");
		label_9.setBounds(38, 344, 46, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("DBMS");
		label_10.setBounds(38, 385, 46, 14);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("DLD");
		label_11.setBounds(38, 421, 46, 14);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("TEF");
		label_12.setBounds(38, 457, 46, 14);
		contentPane.add(label_12);
		
		JLabel lblPresent_1 = new JLabel("Present");
		lblPresent_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPresent_1.setBounds(150, 236, 46, 14);
		contentPane.add(lblPresent_1);
		
		JLabel lblAbsent_1 = new JLabel("Absent");
		lblAbsent_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAbsent_1.setBounds(248, 236, 46, 14);
		contentPane.add(lblAbsent_1);
		
		JLabel lblTotalClass = new JLabel("Total");
		lblTotalClass.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalClass.setBounds(358, 236, 105, 14);
		contentPane.add(lblTotalClass);
		
		textfield_from = new JTextField();
		textfield_from.setBounds(97, 77, 86, 20);
		contentPane.add(textfield_from);
		textfield_from.setColumns(10);
		
		textField_to = new JTextField();
		textField_to.setBounds(284, 77, 86, 20);
		contentPane.add(textField_to);
		textField_to.setColumns(10);
		
		//getting start and end date
		String queryd="select min(date),max(date) from attendance";
		try
		{
			PreparedStatement psd=con.prepareStatement(queryd);
			ResultSet res=psd.executeQuery();
			while(res.next())
			{
				fromdate=res.getString(1);
				todate=res.getString(2);
			}
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		//setting default start and end date
		textfield_from.setText(fromdate);
		textField_to.setText(todate);
		
			
		//Fetching rollno and name & displaying it
		String username=HomePage_Login.s1;
		
		String query="select rollno,name from student where username=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, username);
			
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				rollno=res.getString(1);
				name=res.getString(2);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		label.setText(String.valueOf(rollno));
		label_1.setText(name.toUpperCase());
		
		
		
		label_13 = new JLabel("");
		label_13.setBounds(150, 272, 46, 14);
		contentPane.add(label_13);
		
		label_14 = new JLabel("");
		label_14.setBounds(150, 308, 46, 14);
		contentPane.add(label_14);
		
		label_15 = new JLabel("");
		label_15.setBounds(150, 344, 46, 14);
		contentPane.add(label_15);
		
		label_16 = new JLabel("");
		label_16.setBounds(150, 385, 46, 14);
		contentPane.add(label_16);
		
		label_17 = new JLabel("");
		label_17.setBounds(150, 421, 46, 14);
		contentPane.add(label_17);
		
		label_18 = new JLabel("");
		label_18.setBounds(150, 457, 46, 14);
		contentPane.add(label_18);
		
		label_19 = new JLabel("");
		label_19.setBounds(248, 272, 46, 14);
		contentPane.add(label_19);
		
		label_20 = new JLabel("");
		label_20.setBounds(248, 308, 46, 14);
		contentPane.add(label_20);
		
		label_21 = new JLabel("");
		label_21.setBounds(248, 344, 46, 14);
		contentPane.add(label_21);
		
		label_22 = new JLabel("");
		label_22.setBounds(248, 385, 46, 14);
		contentPane.add(label_22);
		
		label_23 = new JLabel("");
		label_23.setBounds(248, 421, 46, 14);
		contentPane.add(label_23);
		
		label_24 = new JLabel("");
		label_24.setBounds(248, 457, 46, 14);
		contentPane.add(label_24);
		
		label_25 = new JLabel("");
		label_25.setBounds(358, 272, 46, 14);
		contentPane.add(label_25);
		
		label_26 = new JLabel("");
		label_26.setBounds(358, 308, 46, 14);
		contentPane.add(label_26);
		
		label_27 = new JLabel("");
		label_27.setBounds(358, 344, 46, 14);
		contentPane.add(label_27);
		
		label_28 = new JLabel("");
		label_28.setBounds(358, 385, 46, 14);
		contentPane.add(label_28);
		
		label_29 = new JLabel("");
		label_29.setBounds(358, 421, 46, 14);
		contentPane.add(label_29);
		
		label_30 = new JLabel("");
		label_30.setBounds(358, 457, 46, 14);
		contentPane.add(label_30);
		
		JLabel lblAttendance = new JLabel("Attendance");
		lblAttendance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAttendance.setBounds(490, 236, 84, 14);
		contentPane.add(lblAttendance);
		
		label_31 = new JLabel("");
		label_31.setBounds(490, 272, 65, 14);
		contentPane.add(label_31);
		
		label_32 = new JLabel("");
		label_32.setBounds(490, 308, 65, 14);
		contentPane.add(label_32);
		
		label_33 = new JLabel("");
		label_33.setBounds(490, 344, 65, 14);
		contentPane.add(label_33);
		
		label_34 = new JLabel("");
		label_34.setBounds(490, 385, 65, 14);
		contentPane.add(label_34);
		
		label_35 = new JLabel("");
		label_35.setBounds(490, 421, 65, 14);
		contentPane.add(label_35);
		
		label_36 = new JLabel("");
		label_36.setBounds(490, 457, 65, 14);
		contentPane.add(label_36);
		
		
		setLabel();					//fetching,calculating & setting all attendance data on frame
		
		
		JButton btnViewAttendance = new JButton("View Attendance");
		btnViewAttendance.setFocusPainted(false);
		btnViewAttendance.setMnemonic(KeyEvent.VK_V);
		btnViewAttendance.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				fromdate=textfield_from.getText();
				todate=textField_to.getText();
				
				setLabel();			//
			}
		});
		btnViewAttendance.setBounds(393, 76, 139, 23);
		contentPane.add(btnViewAttendance);
		
		label_37 = new JLabel(new ImageIcon("attendance2.jpg"));
		label_37.setBounds(0, 0, 545, 481);
		contentPane.add(label_37);
		
		label_38 = new JLabel(new ImageIcon("attendance3.jpg"));
		label_38.setBounds(545, -1, 168, 482);
		contentPane.add(label_38);
	}
	
	
	void setLabel()
	{
		//getting subject wise attendance
				//String s[]=DbInfo.getAttendance(118,1,4,5);  can be done(replacement)
			//ADS ----->sid=1
			label_25.setText(""+DBInfo.getTotalClass(1,fromdate,todate));	//total class	
			label_13.setText(""+DBInfo.getPresent(ViewAttendance_Student.rollno, 1,fromdate,todate));	//present							
			label_19.setText(""+(DBInfo.getTotalClass(1,fromdate,todate)-DBInfo.getPresent(ViewAttendance_Student.rollno, 1,fromdate,todate)));							
																							//absent
			double ads=(double)DBInfo.getPresent(ViewAttendance_Student.rollno, 1,fromdate,todate)/DBInfo.getTotalClass(1,fromdate,todate)*100;
			label_31.setText(""+String.format("%.2f",ads)+"%");			//attendance %
				
			//OS ----->sid=2
			label_26.setText(""+DBInfo.getTotalClass(2,fromdate,todate));	
			label_14.setText(""+DBInfo.getPresent(ViewAttendance_Student.rollno, 2,fromdate,todate));							
			label_20.setText(""+(DBInfo.getTotalClass(2,fromdate,todate)-DBInfo.getPresent(ViewAttendance_Student.rollno, 2,fromdate,todate)));							
			
			double os=(double)DBInfo.getPresent(ViewAttendance_Student.rollno, 2,fromdate,todate)/DBInfo.getTotalClass(2,fromdate,todate)*100;
			label_32.setText(""+String.format("%.2f",os)+"%");
			
			//CA ----->sid=3
			label_27.setText(""+DBInfo.getTotalClass(3,fromdate,todate));	
			label_15.setText(""+DBInfo.getPresent(ViewAttendance_Student.rollno, 3,fromdate,todate));							
			label_21.setText(""+(DBInfo.getTotalClass(3,fromdate,todate)-DBInfo.getPresent(ViewAttendance_Student.rollno, 3,fromdate,todate)));							
			
			double ca=(double)DBInfo.getPresent(ViewAttendance_Student.rollno, 3,fromdate,todate)/DBInfo.getTotalClass(3,fromdate,todate)*100;
			label_33.setText(""+String.format("%.2f",ca)+"%");
			
			//DBMS ----->sid=4
			label_28.setText(""+DBInfo.getTotalClass(4,fromdate,todate));	
			label_16.setText(""+DBInfo.getPresent(ViewAttendance_Student.rollno, 4,fromdate,todate));							
			label_22.setText(""+(DBInfo.getTotalClass(4,fromdate,todate)-DBInfo.getPresent(ViewAttendance_Student.rollno, 4,fromdate,todate)));							
			
			double dbms=(double)DBInfo.getPresent(ViewAttendance_Student.rollno, 4,fromdate,todate)/DBInfo.getTotalClass(4,fromdate,todate)*100;
			label_34.setText(""+String.format("%.2f",dbms)+"%");
			
			//DLD ----->sid=5
			label_29.setText(""+DBInfo.getTotalClass(5,fromdate,todate));	
			label_17.setText(""+DBInfo.getPresent(ViewAttendance_Student.rollno, 5,fromdate,todate));							
			label_23.setText(""+(DBInfo.getTotalClass(5,fromdate,todate)-DBInfo.getPresent(ViewAttendance_Student.rollno, 5,fromdate,todate)));							
			
			double dld=(double)DBInfo.getPresent(ViewAttendance_Student.rollno, 5,fromdate,todate)/DBInfo.getTotalClass(5,fromdate,todate)*100;
			label_35.setText(""+String.format("%.2f",dld)+"%");
			
			//TEF ----->sid=6
			label_30.setText(""+DBInfo.getTotalClass(6,fromdate,todate));	
			label_18.setText(""+DBInfo.getPresent(ViewAttendance_Student.rollno, 6,fromdate,todate));							
			label_24.setText(""+(DBInfo.getTotalClass(6,fromdate,todate)-DBInfo.getPresent(ViewAttendance_Student.rollno, 6,fromdate,todate)));							
			
			double tef=(double)DBInfo.getPresent(ViewAttendance_Student.rollno, 6,fromdate,todate)/DBInfo.getTotalClass(6,fromdate,todate)*100;
			label_36.setText(""+String.format("%.2f",tef)+"%");
				
			//getting total classes
			label_3.setText(""+DBInfo.getOverallClasses(fromdate, todate));
				
			//getting present
			label_4.setText(""+DBInfo.getOverallPresent(rollno, fromdate, todate));
			
			//calculating absent
			label_6.setText(""+(DBInfo.getOverallClasses(fromdate, todate)-DBInfo.getOverallPresent(rollno, fromdate, todate)));
			
			//calculating overall %
			double overall=(double)DBInfo.getOverallPresent(rollno, fromdate, todate)/DBInfo.getOverallClasses(fromdate, todate)*100;
			label_2.setText("   "+String.format("%.2f",overall)+"%");						
	}
}