import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AddSchedule extends JFrame
{
	private JPanel contentPane;
	Connection con=DBInfo.con;
	JComboBox comboBox,comboBox_1,comboBox_2,comboBox_3,comboBox_4,comboBox_5,comboBox_6,comboBox_7;
	JComboBox comboBox_8,comboBox_9,comboBox_10,comboBox_11,comboBox_12,comboBox_13,comboBox_14;
	JComboBox comboBox_15,comboBox_16,comboBox_17,comboBox_18,comboBox_19,comboBox_20;
	private JLabel label_4;
		
	public AddSchedule(JFrame h) 
	{
		JFrame frm=this;
		setTitle("Add Schedule");
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
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(34, 118, 46, 14);
		contentPane.add(lblYear);
		
		comboBox = new JComboBox();
		comboBox.setRequestFocusEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "1", "2", "3", "4"}));
		comboBox.setBounds(100, 112, 116, 20);
		contentPane.add(comboBox);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setBounds(34, 149, 46, 14);
		contentPane.add(lblBranch);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setRequestFocusEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select", "Civil Engineering", "Computer Science", "Electrical Engineering", "Electronics & Communication", "Information Technology", "Mechanical Engineering"}));
		comboBox_1.setBounds(100, 143, 209, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(34, 180, 46, 14);
		contentPane.add(lblSection);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setRequestFocusEnabled(false);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B"}));
		comboBox_2.setBounds(100, 174, 116, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setBounds(34, 205, 91, 14);
		contentPane.add(lblDay);
		
		JLabel label = new JLabel("8:00-9:00");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(182, 205, 84, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("9:00-10:00");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(311, 205, 84, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("10:00-11:00");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(440, 205, 84, 14);
		contentPane.add(label_2);
		
		JLabel lblMonday = new JLabel("Monday");
		lblMonday.setBounds(34, 236, 91, 14);
		contentPane.add(lblMonday);
		
		JLabel lblTuseday = new JLabel("Tuseday");
		lblTuseday.setBounds(34, 267, 91, 14);
		contentPane.add(lblTuseday);
		
		comboBox_3 = new JComboBox(DBInfo.getSubjects());
		comboBox_3.setRequestFocusEnabled(false);
		comboBox_3.setBounds(182, 236, 84, 20);
		contentPane.add(comboBox_3);
		
		comboBox_5 = new JComboBox(DBInfo.getSubjects());
		comboBox_5.setRequestFocusEnabled(false);
		comboBox_5.setBounds(440, 236, 84, 20);
		contentPane.add(comboBox_5);
		
		comboBox_7 = new JComboBox(DBInfo.getSubjects());
		comboBox_7.setRequestFocusEnabled(false);
		comboBox_7.setBounds(311, 267, 84, 20);
		contentPane.add(comboBox_7);
		
		JLabel lblWednesday = new JLabel("Wednesday");
		lblWednesday.setBounds(34, 298, 91, 14);
		contentPane.add(lblWednesday);
		
		JLabel lblThursday = new JLabel("Thursday");
		lblThursday.setBounds(34, 329, 91, 14);
		contentPane.add(lblThursday);
		
		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setBounds(34, 360, 91, 14);
		contentPane.add(lblFriday);
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.setBounds(34, 391, 91, 14);
		contentPane.add(lblSaturday);
		
		comboBox_4 = new JComboBox(DBInfo.getSubjects());
		comboBox_4.setRequestFocusEnabled(false);
		comboBox_4.setBounds(311, 236, 84, 20);
		contentPane.add(comboBox_4);
		
		comboBox_6 = new JComboBox(DBInfo.getSubjects());
		comboBox_6.setRequestFocusEnabled(false);
		comboBox_6.setBounds(182, 267, 84, 20);
		contentPane.add(comboBox_6);
		
		comboBox_8 = new JComboBox(DBInfo.getSubjects());
		comboBox_8.setRequestFocusEnabled(false);
		comboBox_8.setBounds(440, 267, 84, 20);
		contentPane.add(comboBox_8);
		
		comboBox_9 = new JComboBox(DBInfo.getSubjects());
		comboBox_9.setRequestFocusEnabled(false);
		comboBox_9.setBounds(182, 298, 84, 20);
		contentPane.add(comboBox_9);
		
		comboBox_10 = new JComboBox(DBInfo.getSubjects());
		comboBox_10.setRequestFocusEnabled(false);
		comboBox_10.setBounds(311, 298, 84, 20);
		contentPane.add(comboBox_10);
		
		comboBox_11 = new JComboBox(DBInfo.getSubjects());
		comboBox_11.setRequestFocusEnabled(false);
		comboBox_11.setBounds(440, 298, 84, 20);
		contentPane.add(comboBox_11);
		
		comboBox_12 = new JComboBox(DBInfo.getSubjects());
		comboBox_12.setRequestFocusEnabled(false);
		comboBox_12.setBounds(182, 329, 84, 20);
		contentPane.add(comboBox_12);
		
		comboBox_13 = new JComboBox(DBInfo.getSubjects());
		comboBox_13.setRequestFocusEnabled(false);
		comboBox_13.setBounds(311, 329, 84, 20);
		contentPane.add(comboBox_13);
		
		comboBox_14 = new JComboBox(DBInfo.getSubjects());
		comboBox_14.setRequestFocusEnabled(false);
		comboBox_14.setBounds(440, 329, 84, 20);
		contentPane.add(comboBox_14);
		
		comboBox_15 = new JComboBox(DBInfo.getSubjects());
		comboBox_15.setRequestFocusEnabled(false);
		comboBox_15.setBounds(182, 360, 84, 20);
		contentPane.add(comboBox_15);
		
		comboBox_16 = new JComboBox(DBInfo.getSubjects());
		comboBox_16.setRequestFocusEnabled(false);
		comboBox_16.setBounds(311, 360, 84, 20);
		contentPane.add(comboBox_16);
		
		comboBox_17 = new JComboBox(DBInfo.getSubjects());
		comboBox_17.setRequestFocusEnabled(false);
		comboBox_17.setBounds(440, 360, 84, 20);
		contentPane.add(comboBox_17);
		
		comboBox_18 = new JComboBox(DBInfo.getSubjects());
		comboBox_18.setRequestFocusEnabled(false);
		comboBox_18.setBounds(182, 391, 84, 20);
		contentPane.add(comboBox_18);
		
		comboBox_19 = new JComboBox(DBInfo.getSubjects());
		comboBox_19.setRequestFocusEnabled(false);
		comboBox_19.setBounds(311, 391, 84, 20);
		contentPane.add(comboBox_19);
		
		comboBox_20 = new JComboBox(DBInfo.getSubjects());
		comboBox_20.setRequestFocusEnabled(false);
		comboBox_20.setBounds(440, 391, 84, 20);
		contentPane.add(comboBox_20);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(10, 456, 299, 14);
		contentPane.add(label_3);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				insertRecords((String)comboBox.getSelectedItem(),(String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem(),"Monday",(String)comboBox_3.getSelectedItem(),(String)comboBox_4.getSelectedItem(),(String)comboBox_5.getSelectedItem());
				insertRecords((String)comboBox.getSelectedItem(),(String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem(),"Tuesday",(String)comboBox_6.getSelectedItem(),(String)comboBox_7.getSelectedItem(),(String)comboBox_8.getSelectedItem());
				insertRecords((String)comboBox.getSelectedItem(),(String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem(),"Wednesday",(String)comboBox_9.getSelectedItem(),(String)comboBox_10.getSelectedItem(),(String)comboBox_11.getSelectedItem());
				insertRecords((String)comboBox.getSelectedItem(),(String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem(),"Thursday",(String)comboBox_12.getSelectedItem(),(String)comboBox_13.getSelectedItem(),(String)comboBox_14.getSelectedItem());
				insertRecords((String)comboBox.getSelectedItem(),(String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem(),"Friday",(String)comboBox_15.getSelectedItem(),(String)comboBox_16.getSelectedItem(),(String)comboBox_17.getSelectedItem());
				insertRecords((String)comboBox.getSelectedItem(),(String)comboBox_1.getSelectedItem(),(String)comboBox_2.getSelectedItem(),"Saturday",(String)comboBox_18.getSelectedItem(),(String)comboBox_19.getSelectedItem(),(String)comboBox_20.getSelectedItem());
				label_3.setText("Schedule Added...");
			}
		});
		btnAdd.setBounds(192, 422, 127, 23);
		contentPane.add(btnAdd);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusPainted(false);
		btnReset.setBounds(379, 422, 127, 23);
		contentPane.add(btnReset);
		
		label_4 = new JLabel(new ImageIcon("timetable.jpg"));
		label_4.setBounds(0, 0, 714, 100);
		contentPane.add(label_4);
		
	}
	//user defined function
	public void insertRecords(String year,String branch,String section,String day,String l1,String l2,String l3)
	{
		String query="insert into schedule values(?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, year);
			ps.setString(2, branch);
			ps.setString(3, section);
			ps.setString(4, day);
			ps.setString(5, l1);
			ps.setString(6, l2);
			ps.setString(7, l3);
			ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
