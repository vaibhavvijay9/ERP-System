import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;

public class StudentSection extends JFrame 
{
	private JPanel contentPane;
	static String year,branch,section;
	
	public StudentSection(JFrame h) 
	{
		JFrame frm=this;
		setResizable(false);
		setTitle("Student");
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
				h.setVisible(true);
				frm.dispose();
				
				HomePage_Login.textField.setText(null);
				HomePage_Login.passwordField.setText(null);
				HomePage_Login.label.setText(null);
			}
		});
				
		//fetching YEAR,BRANCH,and SECTION
		String query="select year,branch,section from student where username=?";
		try
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, HomePage_Login.s1);
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				year=res.getString(1);
				branch=res.getString(2);
				section=res.getString(3);
			}
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		JButton btnViewAttendance = new JButton("View Attendance");
		btnViewAttendance.setFocusPainted(false);
		btnViewAttendance.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ViewAttendance_Student va=new ViewAttendance_Student(frm);
				va.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnViewAttendance.setMnemonic(KeyEvent.VK_V);
		btnViewAttendance.setBounds(96, 49, 178, 48);
		contentPane.add(btnViewAttendance);
		
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.setFocusPainted(false);
		btnViewProfile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Profile p=new Profile(frm);
				p.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnViewProfile.setMnemonic(KeyEvent.VK_P);
		btnViewProfile.setBounds(96, 119, 178, 48);
		contentPane.add(btnViewProfile);
		
		JButton btnViewSchedule = new JButton("View Schedule");
		btnViewSchedule.setFocusPainted(false);
		btnViewSchedule.setMnemonic(KeyEvent.VK_S);
		btnViewSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ViewSchedule_Student vs=new ViewSchedule_Student(frm);
				vs.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnViewSchedule.setBounds(96, 255, 178, 48);
		contentPane.add(btnViewSchedule);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFocusPainted(false);
		btnChangePassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ChangePassword cp=new ChangePassword();
				cp.setVisible(true);
			}
		});
		btnChangePassword.setMnemonic(KeyEvent.VK_C);
		btnChangePassword.setBounds(96, 392, 178, 48);
		contentPane.add(btnChangePassword);
		
		JButton btnListOfHolidays = new JButton("List of Holidays");
		btnListOfHolidays.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ViewHoliday vh=new ViewHoliday(frm);
				vh.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnListOfHolidays.setFocusPainted(false);
		btnListOfHolidays.setBounds(96, 187, 178, 48);
		contentPane.add(btnListOfHolidays);
		
		JButton btnViewNotice = new JButton("View Notice");
		btnViewNotice.setFocusPainted(false);
		btnViewNotice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ViewNotice vn=new ViewNotice(frm);
				vn.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnViewNotice.setBounds(96, 324, 178, 48);
		contentPane.add(btnViewNotice);
		
		JLabel label = new JLabel(new ImageIcon("student.jpg"));
		label.setBounds(0, 0, 714, 481);
		contentPane.add(label);
	}
}