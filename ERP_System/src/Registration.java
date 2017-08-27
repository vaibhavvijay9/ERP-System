import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;

public class Registration extends JFrame implements ItemListener
{	
	private JPanel contentPane;
	private JComboBox<String> comboBox,comboBox_1,comboBox_2,comboBox_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel label,label_1,label_2,label_3;
	private JTextField textField_4;
	private JLabel label_4;
	private JTextField textField_5;
	private JLabel label_5;
	private JComboBox comboBox_4;
	private JLabel label_7;
	private JPanel card_1,card_2,card_3,cards;
	
	public Registration(JFrame h) 
	{
		Registration frm=this;
		setResizable(false);
		setTitle("Registration page");
		setBounds(100, 100, 720, 519);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(171, 123, 48, 14);
		
		textField = new JTextField();
		textField.setBounds(325, 120, 214, 20);
		textField.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(171, 151, 74, 14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(325, 148, 214, 20);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(171, 179, 74, 14);
		
		textField_2 = new JTextField();
		textField_2.setBounds(325, 176, 214, 20);
		textField_2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(171, 207, 74, 14);
		
		textField_3 = new JTextField();
		textField_3.setBounds(325, 204, 214, 20);
		textField_3.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(171, 235, 74, 14);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(325, 232, 214, 20);
		
		JLabel lblRepassword = new JLabel("RePassword");
		lblRepassword.setBounds(171, 263, 74, 14);
		
		JLabel lblUsertype = new JLabel("UserType");
		lblUsertype.setBounds(171, 291, 74, 14);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(325, 260, 214, 20);
		
		
		card_2 = new JPanel();
		 card_2.setBackground(new Color(220, 220, 220));
	     card_2.setBounds(47, 339, 455, 201);
		 card_2.setLayout(null);
		  
		  label = new JLabel("Roll No.");
		  label.setBounds(66, 14, 83, 14);
		  card_2.add(label);
		  
		  textField_4 = new JTextField();
		  textField_4.setBounds(221, 11, 208, 20);
		  card_2.add(textField_4);
		  textField_4.setColumns(10);
		  
		  label_1 = new JLabel("Year");
		  label_1.setBounds(66, 42, 83, 14);
		  card_2.add(label_1);
		  
		  comboBox_1 = new JComboBox(new Object[]{});
		  comboBox_1.setBounds(221, 39, 208, 20);
		  card_2.add(comboBox_1);
		  comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select", "1", "2", "3", "4"}));
		   
		  label_2 = new JLabel("Branch");
		  label_2.setBounds(66, 70, 83, 14);
		  card_2.add(label_2);
		   
		  comboBox_2 = new JComboBox(new Object[]{});
		  comboBox_2.setBounds(221, 67, 208, 20);
		  card_2.add(comboBox_2);
		  comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "Civil Engineering", "Computer Science", "Electrical Engineering", "Electronics & Communication", "Information Technology", "Mechanical Engineering"}));
		   
		  label_3 = new JLabel("Section");
		  label_3.setBounds(66, 98, 83, 14);
		  card_2.add(label_3);
		   
		  comboBox_3 = new JComboBox(new Object[]{});
		  comboBox_3.setBounds(221, 95, 208, 20);
		  card_2.add(comboBox_3);
		  comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Select", "A", "B"}));
		
			
		JButton btnSave = new JButton("SAVE");
		btnSave.setFocusPainted(false);
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setBounds(243, 432, 122, 37);
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int flag=0,flag1=0;
				String name=textField.getText();
				String mobile=textField_1.getText();
				String email=textField_2.getText();
				String username=textField_3.getText();
				String pass=String.copyValueOf(passwordField.getPassword());
				String repass=String.copyValueOf(passwordField_1.getPassword());
				String usertype=(String)comboBox.getSelectedItem();
							
				
				//student's detail fields
				String rollno=textField_4.getText().toUpperCase();
				String year=(String)comboBox_1.getSelectedItem();
				String branch=(String)comboBox_2.getSelectedItem();
				String section=(String)comboBox_3.getSelectedItem();
				
				//faculty's detail fields
				String facultyid=textField_5.getText();
				String subject=(String)comboBox_4.getSelectedItem();
				
				
				//checking whether username already exists
				String query0="select username from login";
				String uname="";
				int exist=0;
				try 
				{
					Connection con=DBInfo.con;
					PreparedStatement ps0=con.prepareStatement(query0);
					ResultSet res0=ps0.executeQuery();
					while(res0.next())
					{
						uname=res0.getString(1);
						if(uname.equals(username))
						{
							exist=1;
							break;
						}
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				
				//checking whether roll no already exists
				int rollexist=0;
				String checkquery="select rollno from student";
				try
				{
					PreparedStatement psc=DBInfo.con.prepareStatement(checkquery);
					ResultSet res=psc.executeQuery();
					while(res.next())
					{
						if(rollno.equals(res.getString(1)))
						{
							rollexist=1;
							break;
						}
					}
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				
				
				if(name.length()==0  || mobile.length()==0 || email.length()==0 || username.length()==0 || pass.length()==0 || repass.length()==0 || usertype.equalsIgnoreCase("Select") )
				{
					label_7.setText("*All fields are mandatory");
				}
				else
				{
					 if(!pass.equals(repass))
					 {
						 label_7.setText("*Passwords doesn't match");
					 }
					 else if(exist==1)
					 {
						 label_7.setText("*Username already exists");
					 }
					 
					 //Student registration
					 else if(usertype.equalsIgnoreCase("Student"))
					 {
						if(rollno.length()==0 || year.equalsIgnoreCase("Select") || branch.equalsIgnoreCase("Select") || section.equalsIgnoreCase("Select") )
						{
							label_7.setText("*All fields are mandatory");
						}
						
						else if(rollexist==1)
						{
							label_7.setText("*Roll no. already exists!!!");
						}
						else
						{		
							String query1="insert into login values(?,?,?)";
							String query="insert into student values(?,?,?,?,?,?,?,?,?)";
							Connection con=DBInfo.con;
							
							try
							{
							
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setString(1, username);
							ps1.setString(2, pass);
							ps1.setString(3, usertype);
							flag1=ps1.executeUpdate();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							try
							{
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, rollno);
							ps.setString(2, name);
							ps.setString(3, year);
							ps.setString(4, branch);
							ps.setString(5, section);
							ps.setString(6, mobile);
							ps.setString(7, email);
							ps.setString(8, usertype);
							ps.setString(9, username);
							flag=ps.executeUpdate();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							
							if(flag!=0 && flag1!=0)
							{
								new Registration_done_popup(Registration.this).setVisible(true);
							label_7.setText(null);
							textField.setText(null);
							textField_1.setText(null);
							textField_2.setText(null);
							textField_3.setText(null);
							textField_4.setText(null);
							passwordField.setText(null);
							passwordField_1.setText(null);
							comboBox.setSelectedIndex(0);
						      comboBox_1.setSelectedIndex(0);
						      comboBox_2.setSelectedIndex(0);
						      comboBox_3.setSelectedIndex(0);
							}
							else
							{
								label_7.setText("*Registration failed!!");
							}
						}
					}
					 //Faculty registration
					 else if(usertype.equalsIgnoreCase("Faculty"))
					 {
						 if(facultyid.length()==0 || subject.equalsIgnoreCase("Select"))
							{
							 label_7.setText("*All fields are mandatory");
							}
						else
						{
							String query="insert into faculty values(?,?,?,?,?,?,?)";
							String query1="insert into login values(?,?,?)";
							Connection con=DBInfo.con;
							
							try
							{
							
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setString(1, username);
							ps1.setString(2, pass);
							ps1.setString(3, usertype);
							flag1=ps1.executeUpdate();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							try
							{
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, facultyid);
							ps.setString(2, name);
							ps.setString(3, subject);
							ps.setString(4, mobile);
							ps.setString(5, email);
							ps.setString(6, usertype);
							ps.setString(7, username);
							flag=ps.executeUpdate();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							if(flag!=0 && flag1!=0)
							{
							
							new Registration_done_popup(Registration.this).setVisible(true);
							label_7.setText(null);
							textField.setText(null);
							textField_1.setText(null);
							textField_2.setText(null);
							textField_3.setText(null);
							textField_4.setText(null);
							textField_5.setText(null);
							passwordField.setText(null);
							passwordField_1.setText(null);
							comboBox.setSelectedIndex(0);
						    comboBox_1.setSelectedIndex(0);
						    comboBox_2.setSelectedIndex(0);
						    comboBox_3.setSelectedIndex(0);
						    comboBox_4.setSelectedIndex(0);
							}
							else
							{
								label_7.setText("*Registration failed.....!");
							}
						}
					 }
				}
			}
		});
		
		JButton btnReset = new JButton("RESET");
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
				textField_5.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
				comboBox.setSelectedIndex(0);
			      comboBox_1.setSelectedIndex(0);
			      comboBox_2.setSelectedIndex(0);
			      comboBox_3.setSelectedIndex(0);
			      comboBox_4.setSelectedIndex(0);
			}
		});
		btnReset.setBounds(401, 432, 122, 37);
		
		comboBox = new JComboBox(new Object[]{});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Student", "Faculty"}));
		comboBox.addItemListener(this); 
				
		comboBox.setBounds(325, 288, 214, 20);
		
		contentPane.setLayout(null);
		contentPane.add(btnSave);
		contentPane.add(lblName);
		contentPane.add(lblMobile);
		contentPane.add(lblEmail);
		contentPane.add(lblUsername);
		contentPane.add(lblPassword);
		contentPane.add(lblRepassword);
		contentPane.add(lblUsertype);
		contentPane.add(btnReset);
		contentPane.add(passwordField_1);
		contentPane.add(passwordField);
		contentPane.add(textField_3);
		contentPane.add(textField_2);
		contentPane.add(textField_1);
		contentPane.add(textField);
		contentPane.add(comboBox);
		
		label_7 = new JLabel("");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setForeground(Color.RED);
		label_7.setBounds(10, 472, 310, 14);
		contentPane.add(label_7);
		
		 card_1 = new JPanel();
		 card_1.setBackground(new Color(220, 220, 220));
	     card_1.setBounds(47, 339, 455, 201);
	     card_1.setLayout(null);
		 
		 
		   
		  
		  
		  
   		 card_3 = new JPanel();
		 card_3.setBackground(new Color(220, 220, 220));
		 card_3.setBounds(47, 339, 455, 201);
		 card_3.setLayout(null);
			 
		  label_4 = new JLabel("Faculty Id");
		  label_4.setBounds(66, 31, 83, 14);
	      card_3.add(label_4);
			 
		  textField_5 = new JTextField();
		  textField_5.setBounds(221, 28, 208, 20);
		  card_3.add(textField_5);
		  textField_5.setColumns(10);
			 
		  label_5 = new JLabel("Subject");
		  label_5.setBounds(66, 59, 83, 14);
	 	  card_3.add(label_5);
			 
	      comboBox_4 = new JComboBox(DBInfo.getSubjects());
		  comboBox_4.setBounds(221, 56, 208, 20);
		  card_3.add(comboBox_4);
			  
		   
        cards = new JPanel();
	    cards.setBackground(new Color(220, 220, 220));
		cards.setBounds(106, 305, 519, 126);
		contentPane.add(cards);
		cards.setLayout(new CardLayout(0, 0));
			
		cards.add(card_1, "Select");
		cards.add(card_2, "Student");
		cards.add(card_3, "Faculty");		
		
		JLabel label_6 = new JLabel(new ImageIcon("registration.jpg"));
		label_6.setBounds(0, 0, 714, 114);
		contentPane.add(label_6);
	}
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		CardLayout cl=(CardLayout) cards.getLayout();
		cl.show(cards, (String)e.getItem());
	}
}
