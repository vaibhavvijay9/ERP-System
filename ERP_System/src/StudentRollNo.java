import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
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

public class StudentRollNo extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;
	private JLabel lblUsername;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;	
	
	public StudentRollNo() 
	{
		JFrame frm=this;
		setTitle("Information");
		setResizable(false);
		setBounds(100, 100, 331, 197);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(179,217,230));
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
				frm.dispose();
			}
		});
		
		lblUsername = new JLabel("Enter Roll No. ");
		lblUsername.setBounds(43, 50, 89, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(142, 47, 147, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("");
		label_1.setForeground(Color.RED);
		label_1.setBounds(10, 148, 239, 14);
		contentPane.add(label_1);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(textField.getText().length()==0)
				{
					label_1.setText("Please enter the roll no.!!!");
				}
				else
				{
					String sroll=textField.getText();
					String sname="";
					String query="select name from student where rollno=?";
					try 
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1, sroll);
						ResultSet res=ps.executeQuery();
						while(res.next())
						{
							sname=res.getString(1);
						}
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					ViewAttendance_Student.rollno=sroll;
					ViewAttendance_Student.name=sname;
					
					ViewAttendance_Student va=new ViewAttendance_Student(frm);
					frm.dispose();
					va.setVisible(true);
				}
			}
		});
		btnProceed.setBounds(108, 99, 89, 23);
		contentPane.add(btnProceed);
	}
}
