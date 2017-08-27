import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

public class ChangePassword extends JFrame 
{
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	
	String password,oldpassword,newpassword,confirmpassword;
	JLabel label;

	public ChangePassword() 
	{
		ChangePassword cp=this;

		setTitle("Change Password");
		setResizable(false);
		setBounds(100, 100, 450, 300);
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
				cp.dispose();
			}
		});
		
		
		JLabel lblCurrentPassword = new JLabel("Current Password:");
		lblCurrentPassword.setBounds(89, 103, 111, 14);
		contentPane.add(lblCurrentPassword);
		
		JLabel lblTypeNewPassword = new JLabel("New Password:");
		lblTypeNewPassword.setBounds(89, 133, 111, 14);
		contentPane.add(lblTypeNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password:");
		lblConfirmNewPassword.setBounds(89, 163, 179, 14);
		contentPane.add(lblConfirmNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 103, 179, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(217, 133, 179, 20);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(217, 163, 179, 20);
		contentPane.add(passwordField_2);
		
		//change
		JButton btnChange = new JButton("Change");
		btnChange.setFocusPainted(false);
		btnChange.setMnemonic(KeyEvent.VK_C);
		btnChange.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String username=HomePage_Login.s1;
				
				char ch[]=passwordField.getPassword();
				oldpassword=String.copyValueOf(ch); 	
				
				char ch1[]=passwordField_1.getPassword();
				newpassword=String.copyValueOf(ch1);
				
				char ch2[]=passwordField_2.getPassword();
				confirmpassword=String.copyValueOf(ch2);
				
				
				if(oldpassword.length()==0 || newpassword.length()==0 || confirmpassword.length()==0)
				{
					label.setText("*All fields are mandatory!!!");
				}
				else
				{ 
					if(!newpassword.equals(confirmpassword))
					{
						label.setText("*New password and Confirm do not match!!!");
					}
					else
					{
						Connection con=DBInfo.con;
						String query1="select password from login where username=?";
						try 
						{
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setString(1, HomePage_Login.s1);
							
							ResultSet res=ps1.executeQuery();
							
							while(res.next())
						    {
						    	password=res.getString(1);
						    	break;
						    }
							
							if(!password.equals(oldpassword))
							{
								label.setText("*Wrong old password!!!");
							}
							else
							{
								String query="update login set password=? where username=?";
							
								try 
								{
									PreparedStatement ps=con.prepareStatement(query);
									ps.setString(1, newpassword);
									ps.setString(2, username);
										
									ps.executeUpdate();
									
									label.setText("Password changed...!!!");
								}
								catch (SQLException e) 
								{
									e.printStackTrace();
								}
							}
						} 
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}	
					
				}
			}
		});
		btnChange.setBounds(117, 212, 89, 23);
		contentPane.add(btnChange);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusPainted(false);
		btnReset.setMnemonic(KeyEvent.VK_R);
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				passwordField.setText(null);
				passwordField_1.setText(null);
				passwordField_2.setText(null);
			}
		});
		btnReset.setBounds(226, 212, 89, 23);
		contentPane.add(btnReset);
		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 246, 373, 14);
		contentPane.add(label);		
		
		JLabel label_1 = new JLabel(new ImageIcon("password.jpg"));
		label_1.setBounds(0, 0, 444, 271);
		contentPane.add(label_1);
	}
}
