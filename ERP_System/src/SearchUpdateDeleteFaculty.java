import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
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
import java.awt.event.KeyEvent;

public class SearchUpdateDeleteFaculty extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JComboBox<String> comboBox;
	String facultyid,name,subject,mobile,email,username;	
	
	public SearchUpdateDeleteFaculty(JFrame h) 
	{
		SearchUpdateDeleteFaculty frm=this;
		setTitle("Search/Update/Delete Faculty");
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
		
		JLabel lblFacultyId = new JLabel("Faculty Id:");
		lblFacultyId.setBounds(186, 157, 114, 14);
		contentPane.add(lblFacultyId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(186, 195, 114, 14);
		contentPane.add(lblName);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(186, 233, 114, 14);
		contentPane.add(lblSubject);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setBounds(186, 271, 114, 14);
		contentPane.add(lblMobile);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(186, 309, 114, 14);
		contentPane.add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(186, 347, 114, 14);
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
					String query="select facultyid,name,subject,mobile,email,username from faculty where facultyid=?";
					int flag=0;
					try
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, id);
						ResultSet res=ps.executeQuery();
						while(res.next())
						{
							flag=1;
							facultyid=res.getString(1);
							name=res.getString(2);
							subject=res.getString(3);
							mobile=res.getString(4);
							email=res.getString(5);
							username=res.getString(6);
							
							textField.setText(facultyid);
							textField_1.setText(name);
							comboBox.setSelectedItem(subject);
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
						label.setText("No records found!!!");
					}
				}
				if(id.length()==0)
				{
					
				}
			}
		});
		textField.setBounds(340, 154, 214, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(340, 192, 214, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox(DBInfo.getSubjects());
		comboBox.setBounds(340, 230, 214, 20);
		contentPane.add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(340, 268, 214, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(340, 303, 214, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(340, 341, 214, 20);
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
				label.setText(null);
			}
		});
		btnReset.setBounds(465, 407, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFocusPainted(false);
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=textField.getText();
				String query="select facultyid,name,subject,mobile,email,username from faculty where facultyid=?";
				int flag=0;
				try
				{
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, id);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						flag=1;
						facultyid=res.getString(1);
						name=res.getString(2);
						subject=res.getString(3);
						mobile=res.getString(4);
						email=res.getString(5);
						username=res.getString(6);
						
						textField.setText(facultyid);
						textField_1.setText(name);
						comboBox.setSelectedItem(subject);
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
		btnSearch.setBounds(168, 407, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String facultyid=textField.getText();
				String name=textField_1.getText();
				String subject=(String) comboBox.getSelectedItem();
				String mobile=textField_2.getText();
				String email=textField_3.getText();
				String newusername=textField_4.getText();
				
				if(facultyid.length()==0 || name.length()==0 || subject.length()==0 || mobile.length()==0 || email.length()==0 || newusername.length()==0)
				{
					label.setText("*Please select/fill all the values!!!");
					label.setForeground(Color.RED);
				}
				else
				{
						String query="update login set username=? where username=?";
						String query1="update faculty set name=?,subject=?,mobile=?,email=?,username=? where facultyid=?";
						
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
							ps1.setString(2, subject);
							ps1.setString(3, mobile);
							ps1.setString(4, email);
							ps1.setString(5, newusername);
							ps1.setString(6, facultyid);
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
						}
						else
						{
							label.setText("Record Update Failed!!!");
						}
					}
				}
		});
		btnUpdate.setBounds(267, 407, 89, 23);
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
				String query1="delete from faculty where facultyid=?";
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
					ps1.setString(1, facultyid);
					d1=ps1.executeUpdate();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				if(d==1 && d1==1)
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
		btnDelete.setBounds(366, 407, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setForeground(Color.RED);
		label_1.setBounds(242, 157, 46, 14);
		contentPane.add(label_1);	
		
		JLabel label_2 = new JLabel(new ImageIcon("search.jpg"));
		label_2.setBounds(0, 0, 714, 126);
		contentPane.add(label_2);
	}
}