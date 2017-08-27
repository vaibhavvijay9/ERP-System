import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Mark_Attendance_Detail extends JFrame 
{
	private JPanel contentPane;
	String subject="";
	static int sid;
	
	public Mark_Attendance_Detail(JFrame h) 
	{
		JFrame frm=this;
		setTitle("Details");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 408, 253);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(197, 196, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(60, 97, 95, 14);
		contentPane.add(lblYear);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setRequestFocusEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "1", "2", "3", "4"}));
		comboBox.setBounds(156, 94, 180, 20);
		contentPane.add(comboBox);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setBounds(60, 125, 95, 14);
		contentPane.add(lblBranch);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setRequestFocusEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select", "Civil Engineering", "Computer Science", "Electrical Engineering", "Electronics & Communication", "Information Technology", "Mechanical Engineering"}));
		comboBox_1.setBounds(156, 122, 180, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(60, 153, 95, 14);
		contentPane.add(lblSection);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setRequestFocusEnabled(false);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B"}));
		comboBox_2.setBounds(156, 150, 180, 20);
		contentPane.add(comboBox_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setForeground(Color.RED);
		label_1.setBounds(10, 207, 246, 14);
		contentPane.add(label_1);
		
		String query="select subject from faculty where username=?";
		try 
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, HomePage_Login.s1);
			ResultSet res=ps.executeQuery();
			res.next();
			subject=res.getString(1);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		//getting subject id
		String query1="select sid from subject where sname=?";
		try 
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query1);
			ps.setString(1, subject);
			ResultSet res=ps.executeQuery();
			res.next();
			Mark_Attendance_Detail.sid=res.getInt(1);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String year=(String) comboBox.getSelectedItem();
				String branch=(String) comboBox_1.getSelectedItem();
				String section=(String) comboBox_2.getSelectedItem();
				if(year.equalsIgnoreCase("select") || branch.equalsIgnoreCase("select") || section.equalsIgnoreCase("select"))
				{
					label_1.setText("*Please fill all the details!!!");
				}
				else
				{
					MarkAttendance ma=new MarkAttendance(year,branch,section,subject,Mark_Attendance_Detail.sid,h);
					ma.setVisible(true);
					frm.dispose();
					h.setVisible(false);
				}
				
			}
		});
		btnProceed.setFocusPainted(false);
		btnProceed.setBounds(156, 181, 89, 23);
		contentPane.add(btnProceed);
		
		JLabel label = new JLabel(new ImageIcon("attendance.png"));
		label.setBounds(0, 0, 402, 79);
		contentPane.add(label);	
	}
}