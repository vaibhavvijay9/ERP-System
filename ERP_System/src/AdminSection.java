import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AdminSection extends JFrame 
{
	private JPanel contentPane;
	
	public AdminSection(JFrame h) 
	{
		JFrame frm=this;		
		setTitle("Admin Section");
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
				h.setVisible(true);
				frm.dispose();
				
				HomePage_Login.textField.setText(null);
				HomePage_Login.passwordField.setText(null);
				HomePage_Login.label.setVisible(false);
			}
		});
		
		JButton btnAddSubject = new JButton("Add Subject");
		btnAddSubject.setFocusPainted(false);
		btnAddSubject.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				AddSubject as=new AddSubject();
				as.setVisible(true);
			}
		});
		btnAddSubject.setMnemonic(KeyEvent.VK_A);
		btnAddSubject.setBounds(91, 216, 217, 48);
		contentPane.add(btnAddSubject);
		
		JButton btnDeleteStudentRecord = new JButton("Update/Delete/Search Student");
		btnDeleteStudentRecord.setFocusPainted(false);
		btnDeleteStudentRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				SearchUpdateDeleteStudent ss=new SearchUpdateDeleteStudent(frm);
				ss.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnDeleteStudentRecord.setMnemonic(KeyEvent.VK_S);
		btnDeleteStudentRecord.setBounds(379, 21, 217, 49);
		contentPane.add(btnDeleteStudentRecord);
		
		JButton btnDeleteFacultyRecord = new JButton("Update/Delete/Search Faculty");
		btnDeleteFacultyRecord.setFocusPainted(false);
		btnDeleteFacultyRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				SearchUpdateDeleteFaculty sf=new SearchUpdateDeleteFaculty(frm);
				sf.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnDeleteFacultyRecord.setMnemonic(KeyEvent.VK_F);
		btnDeleteFacultyRecord.setBounds(91, 21, 217, 49);
		contentPane.add(btnDeleteFacultyRecord);
		
		JButton btnRemoveSubject = new JButton("Remove Subject");
		btnRemoveSubject.setFocusPainted(false);
		btnRemoveSubject.setMnemonic(KeyEvent.VK_R);
		btnRemoveSubject.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				RemoveSubject rs=new RemoveSubject();
				rs.setVisible(true);
			}
		});
		btnRemoveSubject.setBounds(379, 216, 217, 48);
		contentPane.add(btnRemoveSubject);
		
		JButton btnChangeSelfPassword = new JButton("Change Self Password");
		btnChangeSelfPassword.setFocusPainted(false);
		btnChangeSelfPassword.setMnemonic(KeyEvent.VK_C);
		btnChangeSelfPassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ChangePassword cp=new ChangePassword();
				cp.setVisible(true);
			}
		});
		btnChangeSelfPassword.setBounds(91, 151, 217, 48);
		contentPane.add(btnChangeSelfPassword);
		
		JButton btnViewTables = new JButton("View Tables");
		btnViewTables.setFocusPainted(false);
		btnViewTables.setMnemonic(KeyEvent.VK_T);
		btnViewTables.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				SelectTable st=new SelectTable(frm);
				st.setVisible(true);
			}
		});
		btnViewTables.setBounds(379, 86, 217, 48);
		contentPane.add(btnViewTables);
		
		JButton btnRegisterUser = new JButton("Register User");
		btnRegisterUser.setFocusPainted(false);
		btnRegisterUser.setMnemonic(KeyEvent.VK_U);
		btnRegisterUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Registration r=new Registration(frm);
				r.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnRegisterUser.setBounds(91, 86, 217, 48);
		contentPane.add(btnRegisterUser);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setFocusPainted(false);
		btnResetPassword.setMnemonic(KeyEvent.VK_P);
		btnResetPassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ResetPassword rp=new ResetPassword();
				rp.setVisible(true);
			}
		});
		btnResetPassword.setBounds(379, 151, 216, 48);
		contentPane.add(btnResetPassword);
		
		JButton btnAddNotice = new JButton("Add Notice");
		btnAddNotice.setFocusPainted(false);
		btnAddNotice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Add_Notice an=new Add_Notice(frm);
				an.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnAddNotice.setBounds(91, 281, 217, 48);
		contentPane.add(btnAddNotice);
		
		JButton btnUpdatedeleteNotice = new JButton("Update/Delete Notice");
		btnUpdatedeleteNotice.setFocusPainted(false);
		btnUpdatedeleteNotice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Del_Update_Notice dun=new Del_Update_Notice();
				dun.setVisible(true);
			}
		});
		btnUpdatedeleteNotice.setBounds(379, 281, 217, 48);
		contentPane.add(btnUpdatedeleteNotice);
		
		JButton btnAddSchedule = new JButton("Add Schedule");
		btnAddSchedule.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				AddSchedule as=new AddSchedule(frm);
				as.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnAddSchedule.setBounds(91, 346, 217, 48);
		contentPane.add(btnAddSchedule);
		
		JButton btnUpdatedeleteSchedule = new JButton("Update/Delete Schedule");
		btnUpdatedeleteSchedule.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Del_UpdateSchedule dusch=new Del_UpdateSchedule(frm);
				dusch.setVisible(true);
				frm.setVisible(false);
			}
		});
		btnUpdatedeleteSchedule.setBounds(379, 346, 217, 48);
		contentPane.add(btnUpdatedeleteSchedule);
		
		JButton btnAddHoliday = new JButton("Add Holiday");
		btnAddHoliday.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				AddHoliday ah=new AddHoliday();
				ah.setVisible(true);
			}
		});
		btnAddHoliday.setFocusPainted(false);
		btnAddHoliday.setBounds(91, 411, 217, 48);
		contentPane.add(btnAddHoliday);
		
		JButton btnUpdatedeleteHoliday = new JButton("Update/Delete Holiday");
		btnUpdatedeleteHoliday.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Del_Update_Holiday duh=new Del_Update_Holiday();
				duh.setVisible(true);
			}
		});
		btnUpdatedeleteHoliday.setFocusPainted(false);
		btnUpdatedeleteHoliday.setBounds(379, 411, 217, 48);
		contentPane.add(btnUpdatedeleteHoliday);
		
		JLabel label = new JLabel(new ImageIcon("admin.jpg"));
		label.setBounds(0, 0, 714, 481);
		contentPane.add(label);
	}
}