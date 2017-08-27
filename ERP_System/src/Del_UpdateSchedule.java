import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Del_UpdateSchedule extends JFrame 
{
	private JPanel contentPane;
	
	public Del_UpdateSchedule(JFrame h) 
	{
		JFrame frm=this;
		setTitle("Delete/Update Schedule");
		setResizable(false);
		setBounds(100, 100, 720, 510);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(162,215,237));
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
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 456, 244, 14);
		contentPane.add(label);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(35, 116, 89, 14);
		contentPane.add(lblYear);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				label.setText(null);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "1", "2", "3", "4"}));
		comboBox.setBounds(153, 113, 101, 20);
		contentPane.add(comboBox);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setBounds(35, 156, 89, 14);
		contentPane.add(lblBranch);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select", "Civil Engineering", "Computer Science", "Electrical Engineering", "Electronics & Communication", "Information Technology", "Mechanical Engineering"}));
		comboBox_1.setBounds(153, 153, 196, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(35, 196, 89, 14);
		contentPane.add(lblSection);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B"}));
		comboBox_2.setBounds(153, 193, 101, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(35, 236, 89, 14);
		contentPane.add(lblDay);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Select", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}));
		comboBox_3.setBounds(153, 233, 101, 20);
		contentPane.add(comboBox_3);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(35, 276, 89, 14);
		contentPane.add(lblTime);
		
		JComboBox comboBox_4 = new JComboBox(DBInfo.getLectureTime());
		comboBox_4.setBounds(153, 273, 101, 20);
		contentPane.add(comboBox_4);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setBounds(141, 310, 219, 26);
		contentPane.add(lblUpdate);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(120, 361, 89, 14);
		contentPane.add(lblSubject);
		
		JComboBox comboBox_5 = new JComboBox(DBInfo.getSubjects());
		comboBox_5.setBounds(233, 358, 101, 20);
		contentPane.add(comboBox_5);
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String year=(String) comboBox.getSelectedItem();
				String branch=(String) comboBox_1.getSelectedItem();
				String section=(String) comboBox_2.getSelectedItem();
				String day=(String) comboBox_3.getSelectedItem();
				String time=(String) comboBox_4.getSelectedItem();
				String subject=(String) comboBox_5.getSelectedItem();
				
				if(year.equalsIgnoreCase("select") || branch.equalsIgnoreCase("select") || section.equalsIgnoreCase("select") || day.equalsIgnoreCase("select") || time.equalsIgnoreCase("select") || subject.equalsIgnoreCase("select"))
				{
					label.setText("*All fields are mandatory!!!");
					label.setForeground(Color.RED);
				}
				else
				{
					String query="update schedule set "+"`"+time+"`"+"=? where year=? and branch=? and section=? and Day=?";
					int i=0;
					try 
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1, subject);
						ps.setString(2, year);
						ps.setString(3, branch);
						ps.setString(4, section);
						ps.setString(5, day);
						i=ps.executeUpdate();
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					//	line 139 --Explanation
					//	since column name 8.00-9.00 (i.e. starts with digit) so backtick ``
					//	now another problem string enclose within backtick ``
					if(i==1)
					{
						comboBox.setSelectedIndex(0);
						comboBox_1.setSelectedIndex(0);
						comboBox_2.setSelectedIndex(0);
						comboBox_3.setSelectedIndex(0);
						comboBox_4.setSelectedIndex(0);
						comboBox_5.setSelectedIndex(0);
						label.setText("Schedule Updated Successfully...");
						label.setForeground(Color.BLACK);
					}
				}
			}
		});
		btnUpdate.setBounds(70, 414, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusPainted(false);
		btnReset.setBounds(314, 414, 89, 23);
		contentPane.add(btnReset);
		
		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setForeground(Color.RED);
		label_1.setBounds(258, 112, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setForeground(Color.RED);
		label_2.setBounds(352, 151, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setForeground(Color.RED);
		label_3.setBounds(258, 192, 46, 14);
		contentPane.add(label_3);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String year=(String) comboBox.getSelectedItem();
				String branch=(String) comboBox_1.getSelectedItem();
				String section=(String) comboBox_2.getSelectedItem();
				
				if(year.equalsIgnoreCase("select") || branch.equalsIgnoreCase("select") || section.equalsIgnoreCase("select"))
				{
					label.setText("* marked fields are mandatory!!!");
					label.setForeground(Color.RED);
				}
				else
				{
					String query="delete from schedule where year=? and branch=? and section=?";
					int i=0;
					try 
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1, year);
						ps.setString(2, branch);
						ps.setString(3, section);
						i=ps.executeUpdate();
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					if(i==1)
					{
						comboBox.setSelectedIndex(0);
						comboBox_1.setSelectedIndex(0);
						comboBox_2.setSelectedIndex(0);
						comboBox_3.setSelectedIndex(0);
						comboBox_4.setSelectedIndex(0);
						comboBox_5.setSelectedIndex(0);
						label.setText("Schedule Deleted Successfully...");
						label.setForeground(Color.BLACK);
					}
				}
			}
		});
		btnDelete.setFocusPainted(false);
		btnDelete.setBounds(192, 414, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel lblOnymarkedFields = new JLabel("(only   marked fields are to be filled for deletion )");
		lblOnymarkedFields.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOnymarkedFields.setBounds(192, 438, 296, 14);
		contentPane.add(lblOnymarkedFields);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(216, 438, 46, 14);
		contentPane.add(label_4);
		
		label_4 = new JLabel(new ImageIcon("timetable.jpg"));
		label_4.setBounds(0, 0, 714, 100);
		contentPane.add(label_4);
	}
}
