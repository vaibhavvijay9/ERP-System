import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class SearchUpdateDeleteStudent extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	String rollno,name,year,branch,section,mobile,email,username;
	
	public SearchUpdateDeleteStudent(JFrame h) 
	{
		SearchUpdateDeleteStudent frm=this;
		setTitle("Search/Update/Delete Student");
		setResizable(false);
		setBounds(100, 100, 720, 510);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 199, 97));
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
		
		JLabel lblrollno = new JLabel("Roll No.:");
		lblrollno.setBounds(183, 155, 114, 14);
		contentPane.add(lblrollno);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(183, 185, 114, 14);
		contentPane.add(lblName);
		
		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setForeground(Color.RED);
		label_1.setBounds(226, 155, 46, 14);
		contentPane.add(label_1);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setBounds(183, 245, 46, 14);
		contentPane.add(lblBranch);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(183, 275, 46, 14);
		contentPane.add(lblSection);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Computer Science", "Electrical", "Mechanical"}));
		comboBox.setBounds(337, 238, 214, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B"}));
		comboBox_1.setBounds(337, 269, 214, 20);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "1", "2", "3", "4"}));
		comboBox_2.setBounds(337, 207, 214, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblSubject = new JLabel("Year:");
		lblSubject.setBounds(183, 215, 114, 14);
		contentPane.add(lblSubject);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setBounds(183, 305, 114, 14);
		contentPane.add(lblMobile);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(183, 335, 114, 14);
		contentPane.add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(183, 365, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
		label.setBounds(10, 456, 450, 14);
		contentPane.add(label);
				
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				label.setText(null);
			}
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				String id=textField.getText();
				
				if(id.length()!=0)
				{
					String query="select rollno,name,year,branch,section,mobile,email,username from student where rollno=?";
					int flag=0;
					try
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, id);
						ResultSet res=ps.executeQuery();
						while(res.next())
						{
							flag=1;
							rollno=res.getString(1);
							name=res.getString(2);
							year=res.getString(3);
							branch=res.getString(4);
							section=res.getString(5);
							mobile=res.getString(6);
							email=res.getString(7);
							username=res.getString(8);
							
							textField.setText(rollno);
							textField_1.setText(name);
							comboBox_2.setSelectedItem(year);
							comboBox.setSelectedItem(branch);
							comboBox_1.setSelectedItem(section);
							textField_2.setText(mobile);
							textField_3.setText(email);
							textField_4.setText(username);
							break;			
						}
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					if(flag==0)
					{
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						textField_3.setText(null);
						textField_4.setText(null);
						comboBox.setSelectedIndex(0);
						comboBox_1.setSelectedIndex(0);
						comboBox_2.setSelectedIndex(0);
						label.setText("No records found!!!");
					}
				}
				if(id.length()==0)
				{
					
				}
			}
		});
		textField.setBounds(337, 145, 214, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(337, 176, 214, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(337, 302, 214, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(337, 332, 214, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(337, 362, 214, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusPainted(false);
		btnReset.setMnemonic(KeyEvent.VK_R);
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_2.setSelectedIndex(0);
				label.setText(null);
			}
		});
		btnReset.setBounds(462, 422, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFocusPainted(false);
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=textField.getText();
				String query="select rollno,name,year,branch,section,mobile,email,username from student where rollno=?";
				int flag=0;
				try
				{
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, id);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						flag=1;
						rollno=res.getString(1);
						name=res.getString(2);
						year=res.getString(3);
						branch=res.getString(4);
						section=res.getString(5);
						mobile=res.getString(6);
						email=res.getString(7);
						username=res.getString(8);
						
						textField.setText(rollno);
						textField_1.setText(name);
						comboBox_2.setSelectedItem(year);
						comboBox.setSelectedItem(branch);
						comboBox_1.setSelectedItem(section);
						textField_2.setText(mobile);
						textField_3.setText(email);
						textField_4.setText(username);
						break;			
					}
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				if(flag==0)
				{
					label.setText("No records found!!!");
				}
			}
		});
		btnSearch.setBounds(165, 422, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String rollno=textField.getText();
				String name=textField_1.getText();
				String year=(String) comboBox_2.getSelectedItem();
				String branch=(String) comboBox.getSelectedItem();
				String section=(String) comboBox_1.getSelectedItem();
				String mobile=textField_2.getText();
				String email=textField_3.getText();
				String newusername=textField_4.getText();
				
				if(rollno.length()==0 || name.length()==0 || year.length()==0 ||branch.length()==0 ||section.length()==0 || mobile.length()==0 || email.length()==0 || newusername.length()==0)
				{
					label.setText("*Please select/fill all the values!!!");
					label.setForeground(Color.RED);
				}
				else
				{
						String query="update login set username=? where username=?";
						String query1="update student set name=?,year=?,branch=?,section=?,mobile=?,email=?,username=? where rollno=?";
						
						int i=0;
						try
						{
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, newusername);
							ps.setString(2, username);
							i=ps.executeUpdate();		
						}
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
						
						int j=0;
						try
						{
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setString(1, name);
							ps1.setString(2, year);
							ps1.setString(3, branch);
							ps1.setString(4, section);
							ps1.setString(5, mobile);
							ps1.setString(6, email);
							ps1.setString(7, newusername);
							ps1.setString(8, rollno);
							j=ps1.executeUpdate();
						}
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
						
						
						if(i==1 && j==1)
						{
							label.setText("Record Updated...");
							textField.setText(null);
						    textField_1.setText(null);
						    textField_2.setText(null);
						    textField_3.setText(null);
						    textField_4.setText(null);
						    comboBox.setSelectedIndex(0);
						    comboBox_1.setSelectedIndex(0);
						    comboBox_2.setSelectedIndex(0);
						}
						else
						{
							label.setText("Record Update Failed!!!");
						}
					}
				}
		});
		btnUpdate.setBounds(264, 422, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFocusPainted(false);
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=textField.getText();
				String query="delete from login where username=?";
				String query1="delete from student where rollno=?";
				String query2="delete from attendance where rollno=?";
				int d=0;
				try
				{
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, textField_4.getText());
					d=ps.executeUpdate();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				int d1=0;
				try
				{
					PreparedStatement ps1=con.prepareStatement(query1);
					ps1.setString(1, rollno);
					d1=ps1.executeUpdate();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				int d2=0;
				try
				{
					PreparedStatement ps2=con.prepareStatement(query2);
					ps2.setString(1, rollno);
					d1=ps2.executeUpdate();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				if(d==1 && d1==1 && d2==1)
				{
					label.setText("Record deleted...");
				}
				else
				{
					label.setText("*Record not deleted / *record not found!!!");
					label.setForeground(Color.RED);
				}
				
			}
		});
		btnDelete.setBounds(363, 422, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel label_2 = new JLabel(new ImageIcon("search.jpg"));
		label_2.setBounds(0, 0, 714, 126);
		contentPane.add(label_2);
	}
}