import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class FacultySection extends JFrame 
{	
	private JPanel contentPane;
	String sroll,sname;
	static String subjectname;

	public FacultySection(JFrame h) 
	{
		JFrame frm = this;
		setResizable(false);
		setTitle("Faculty");
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
				HomePage_Login.label.setVisible(false);
			}
		});
		
		JButton btnMarkAttendance = new JButton("Mark Attendance");
		btnMarkAttendance.setFocusPainted(false);
		btnMarkAttendance.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Mark_Attendance_Detail mad=new Mark_Attendance_Detail(frm);
				mad.setVisible(true);
			}
		});
		btnMarkAttendance.setMnemonic(KeyEvent.VK_M);
		btnMarkAttendance.setBounds(456, 33, 189, 43);
		contentPane.add(btnMarkAttendance);
		
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
		btnViewProfile.setBounds(456, 102, 189, 43);
		contentPane.add(btnViewProfile);
		
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
		btnChangePassword.setBounds(456, 173, 189, 43);
		contentPane.add(btnChangePassword);
		
		JButton btnViewStudentAttendance = new JButton("View Student Attendance");
		btnViewStudentAttendance.setFocusPainted(false);
		btnViewStudentAttendance.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				StudentRollNo srn=new StudentRollNo();
				srn.setVisible(true);
			}
		});
		btnViewStudentAttendance.setMnemonic(KeyEvent.VK_V);
		btnViewStudentAttendance.setBounds(456, 243, 189, 43);
		contentPane.add(btnViewStudentAttendance);
		
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
		btnListOfHolidays.setMnemonic(KeyEvent.VK_A);
		btnListOfHolidays.setBounds(456, 309, 189, 43);
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
		btnViewNotice.setBounds(456, 379, 189, 43);
		contentPane.add(btnViewNotice);
		
		JLabel label = new JLabel(new ImageIcon("faculty.jpg"));
		label.setBounds(0, 0, 714, 481);
		contentPane.add(label);
	}
}