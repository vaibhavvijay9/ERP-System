import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ResetPassword extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;
	private JLabel lblUsername;
	private JTextField textField;
	private JButton btnChange;
	private JLabel label;
	private JLabel label_1;	
	private JLabel label_2;

	public ResetPassword() 
	{
		JFrame rp=this;
		setTitle("Reset Password");
		setResizable(false);
		setBounds(100, 100, 331, 197);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(189,215,238));
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
				rp.dispose();
			}
		});
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(43, 34, 68, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(142, 31, 147, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("");
		label_1.setForeground(Color.RED);
		label_1.setBounds(10, 148, 239, 14);
		contentPane.add(label_1);
		
		btnChange = new JButton("Change");
		btnChange.setFocusPainted(false);
		btnChange.setMnemonic(KeyEvent.VK_C);
		btnChange.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String username=textField.getText();
				String query="select username from login where username=?";				
				try
				{
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, username);
					ResultSet res=ps.executeQuery();
					int i=0;
					while(res.next())
					{
						String query1="update login set password=? where username=?";
						PreparedStatement ps1=con.prepareStatement(query1);
						ps1.setString(1, username);
						ps1.setString(2, username);
						i=ps1.executeUpdate();
					}
					if(i==0)
					{
						label_1.setForeground(Color.RED);
						label_1.setText("*Wrong username!!!");
					}
					else
					{
						label_1.setText("Password changed...");
						label_1.setForeground(Color.BLACK);
					}
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnChange.setBounds(101, 92, 89, 23);
		contentPane.add(btnChange);
		
		label_2 = new JLabel(new ImageIcon("password1.png"));
		label_2.setBounds(201, 55, 124, 114);
		contentPane.add(label_2);
	}
}
