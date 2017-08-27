import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class HomePage_Login extends JFrame
{
	static String usertype;
	static JLabel lblErpSystem,label_1,label_2,label;
	JButton button_1;
	private JPanel contentPane;
	static JPasswordField passwordField;
	static JTextField textField;
	static String s1,s2;
   
	//  Create the frame.
	public HomePage_Login() 
	{
		JFrame h=this;
		setResizable(false);
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 510);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 204));
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
		
		lblErpSystem = new JLabel("ERP System");
		lblErpSystem.setBounds(281, 23, 327, 41);
		lblErpSystem.setFont(new Font("Monotype Corsiva", Font.PLAIN, 36));
		
		label_1 = new JLabel("Enter Password");
		label_1.setBounds(180, 186, 114, 14);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(342, 183, 211, 20);
		
		label_2 = new JLabel("Enter Username");
		label_2.setBounds(180, 148, 104, 14);
		
		textField = new JTextField();
		textField.setBounds(342, 145, 209, 20);
		textField.setColumns(10);
		
		JButton button = new JButton("Login Now");
		button.setFocusPainted(false);
		button.setMnemonic(KeyEvent.VK_L);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				usertype="";
				s1=textField.getText();
				char ch[]=passwordField.getPassword();
				s2=String.copyValueOf(ch); 					//password
				
				String query="select * from login where username=? and password=?";
				Connection con=DBInfo.con;
				int i=0;
				if(s1.length()==0 || s2.length()==0)
				{
					label.setText("*All fields are mandatory.");
				}
				else
				{
					try
					{
				      PreparedStatement ps=con.prepareStatement(query);
				      ps.setString(1, s1);
				      ps.setString(2, s2);
				      ResultSet res=ps.executeQuery();
				      while(res.next())
				      {
				    	  i=1;
				    	  usertype=res.getString(3);
				    	  break;
				      }
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					if(i==1 && usertype.equalsIgnoreCase("admin"))
					{
				            AdminSection ad=new AdminSection(h);
				            ad.setVisible(true);
				            h.setVisible(false);
					}
					
					if(i==1 && usertype.equalsIgnoreCase("student"))
					{
						StudentSection ss=new StudentSection(h);
						ss.setVisible(true);
						h.setVisible(false);
					}
					
					if(i==1 && usertype.equalsIgnoreCase("faculty"))
					{
						FacultySection fs=new FacultySection(h);
						fs.setVisible(true);
						h.setVisible(false);
					}
					if(i==0)
					{
						label.setText("*Wrong username or password!!");
					}
				}	
			}
		});
		button.setBounds(246, 237, 102, 23);
		
		button_1 = new JButton("Reset");
		button_1.setFocusPainted(false);
		button_1.setMnemonic(KeyEvent.VK_R);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				textField.setText(null);
				passwordField.setText(null);
				label.setText(null);
			}
		});
		button_1.setBounds(388, 237, 84, 23);
		contentPane.setLayout(null);
		contentPane.add(lblErpSystem);
		contentPane.add(label_2);
		contentPane.add(textField);
		contentPane.add(label_1);
		contentPane.add(passwordField);
		contentPane.add(button);
		contentPane.add(button_1);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setForeground(Color.RED);
		label.setBounds(10, 456, 327, 14);
		contentPane.add(label);
		
		JLabel label_3 = new JLabel(new ImageIcon("home.jpg"));
		label_3.setBounds(0, 0, 720, 481);
		contentPane.add(label_3);
	}
	public static void main(String[] args) 
	{
		HomePage_Login home=new HomePage_Login();
		home.setVisible(true);
	}
}