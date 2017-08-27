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
import java.awt.Font;
import java.awt.event.KeyEvent;

public class AddSubject extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;
	private JTextField textField;
	
	public AddSubject() 
	{
		AddSubject asub=this;
		setTitle("Add Subject");
		setResizable(false);
		setBounds(100, 100, 373, 217);
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
				asub.dispose();
			}
		});
		
		JLabel lblSubjectName = new JLabel("Subject Name:");
		lblSubjectName.setBounds(41, 48, 112, 14);
		contentPane.add(lblSubjectName);
		
		textField = new JTextField();
		textField.setBounds(150, 45, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setForeground(Color.RED);
		label.setBounds(10, 163, 203, 14);
		contentPane.add(label);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String subject_name=textField.getText().toUpperCase();
				if(subject_name.length()==0)
				{
					label.setText("*Enter the subject name!!!");
				}
				else
				{
					String query="select sname from subject";
					String query1="insert into subject(sname) values(?)";
					
					try 
					{
						PreparedStatement ps=con.prepareStatement(query);
						ResultSet res=ps.executeQuery();
						int flag=0;
						while(res.next())
						{
							String sname=res.getString(1);
							if(sname.equalsIgnoreCase(subject_name))
							{
								flag=1;
								break;
							}
						}
						if(flag==1)
						{
							label.setText("*Subject already added!!!");
						}
						else
						{
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setString(1, subject_name);
							
							ps1.executeUpdate();
							label.setText("Subject added....");
							textField.setText(null);
						}
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
		btnAdd.setBounds(129, 104, 89, 23);
		contentPane.add(btnAdd);	
		
		JLabel label_1 = new JLabel(new ImageIcon("subject.jpg"));
		label_1.setBounds(0, 0, 372, 191);
		contentPane.add(label_1);
	}
}
