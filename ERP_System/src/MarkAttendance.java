import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MarkAttendance extends JFrame implements ItemListener,ActionListener
{
	JFrame frm=this;
	JFrame fs;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JButton btnMarkAttendance;
	int count=0;
	JCheckBox[] cb;
	JPanel[] p;
	JLabel[] l;
	JPanel panel;
	String rno[]=new String[100];
	
	Date d=new Date();
	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	String date=f.format(d);
	private JLabel label_3;
	private JLabel label_4;
	
	public MarkAttendance(String year,String branch,String section,String subject,int sid,JFrame h)
	{
		fs=h;
		setTitle("Mark Attendance");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 10, 460, 473);
		setSize(720, 566);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(179,217,230));
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
				h.setVisible(true);
				frm.dispose();
			}
		});
		 		 
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(20, 27, 46, 14);
		contentPane.add(lblYear);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(76, 24, 174, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("Branch:");
		label.setBounds(281, 27, 46, 14);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(350, 24, 174, 20);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("Section:");
		label_1.setBounds(20, 59, 46, 14);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(76, 56, 174, 20);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("Subject:");
		label_2.setBounds(281, 65, 46, 14);
		contentPane.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(350, 59, 174, 20);
		contentPane.add(textField_3);
		
		//-------------------
		textField.setText(year);
		textField_1.setText(branch);
		textField_2.setText(section);
		textField_3.setText(subject);
		//-------------------------
		int x=0;
		String query2="select rollno from student where year=? and branch=? and section=?";
		try 
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query2);
			ps.setString(1, year);
			ps.setString(2, branch);
			ps.setString(3, section);
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				rno[x]=res.getString(1);
				x++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String query="select count(*) from student where year=? and branch=? and section=?";
		try 
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, year);
			ps.setString(2, branch);
			ps.setString(3, section);
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				count=Integer.parseInt(res.getString(1));
			}
			cb=new JCheckBox[count];
			p=new JPanel[count];
			l=new JLabel[count];
			panel=new JPanel();
			
			int j=60;
			int r=118;
			for(int i=0;i<count;i++)
			{
				l[i]=new JLabel(rno[i]+"                               ");	
				
				cb[i]=new JCheckBox("Present");
				cb[i].setSelected(true);	
				cb[i].addItemListener(this);
				
				p[i]=new JPanel();
				p[i].add(l[i]);
				p[i].add(cb[i]);
				p[i].setBounds(10, j, 300,30);
				if(i%2==0)
				{
					p[i].setBackground(new Color(206, 228, 216));
					cb[i].setBackground(new Color(206, 228, 216));
				}
				else
				{
					p[i].setBackground(Color.WHITE);
					cb[i].setBackground(Color.WHITE);
				}
				panel.add(p[i]);
				j=j+32;
				r++;
			}	
				JScrollPane pane=new JScrollPane(panel);
				panel.setLayout(new GridLayout(count,1));
				getContentPane().add(pane);
				pane.setBounds(20, 106, 504, 370);
				
				btnMarkAttendance = new JButton("Mark Attendance");
				btnMarkAttendance.setFocusPainted(false);
				btnMarkAttendance.setBounds(183, 487, 174, 40);
				contentPane.add(btnMarkAttendance);
				
				label_3 = new JLabel(new ImageIcon("attendance1.jpg"));
				label_3.setBounds(545, -1, 168, 538);
				contentPane.add(label_3);
				
				label_4 = new JLabel(new ImageIcon("attendance2.jpg"));
				label_4.setBounds(0, -1, 545, 538);
				contentPane.add(label_4);
				btnMarkAttendance.addActionListener(this);
				
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		for(int i=0;i<count;i++)
		{
			if(e.getSource()==cb[i])
			{
				cb[i].setText("Absent");
			}
			
			if(cb[i].isSelected())
			{
				cb[i].setText("Present");
			}
			else
			{
				cb[i].setText("Absent");
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i=0;i<count;i++)
		{	
			String roll=l[i].getText().trim();
			
			if(cb[i].isSelected())
			{
				mark(roll,textField.getText(),textField_1.getText(),textField_2.getText(),date,""+Mark_Attendance_Detail.sid,""+1);
			}
			else				//absent roll no.
			{
				mark(roll,textField.getText(),textField_1.getText(),textField_2.getText(),date,""+Mark_Attendance_Detail.sid,""+0);							
				
			}
		}
		dispose();
		fs.setVisible(true);
		ViewDialog vd=new ViewDialog("Attendance Marked.");
		vd.setVisible(true);
	}
	public void mark(String rollno,String year,String branch,String section,String date,String sid,String attendance)
	{
		String query="insert into attendance values(?,?,?,?,?,?,?)";
		try 
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, rollno);
			ps.setString(2, year);
			ps.setString(3, branch);
			ps.setString(4, section);
			ps.setString(5, date);
			ps.setString(6, sid);
			ps.setString(7, attendance);
			ps.executeUpdate();
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}