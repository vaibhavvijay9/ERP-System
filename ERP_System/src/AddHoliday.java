import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddHoliday extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblTo;
	private JTextField textField_1;
	private JLabel lblNameOfHoliday;
	private JTextField textField_2;
	private JLabel label;
	private JButton btnAdd;
	Connection con=DBInfo.con;
	private JLabel label_1;

	public AddHoliday() 
	{
		setTitle("Add Holiday");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 240);
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
		
		JLabel lblDate = new JLabel("Date: (dd/mm/yyyy)");
		lblDate.setBounds(40, 46, 109, 14);
		contentPane.add(lblDate);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				label.setText(null);
			}
		});
		textField.setBounds(173, 43, 101, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblTo = new JLabel("to");
		lblTo.setBounds(281, 46, 26, 14);
		contentPane.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(300, 43, 101, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNameOfHoliday = new JLabel("Name of Holiday:");
		lblNameOfHoliday.setBounds(40, 86, 109, 14);
		contentPane.add(lblNameOfHoliday);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 83, 228, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 186, 251, 14);
		contentPane.add(label);
		
		btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String date=textField.getText();
				String todate=textField_1.getText();
				String event=textField_2.getText();
				
				if(date.length()==0 || todate.length()==0 || event.length()==0)
				{
					label.setText("*All fields are mandatory!!!");
					label.setForeground(Color.RED);
				}
				else if(!date.substring(0,3).endsWith("/") || !date.substring(3,6).endsWith("/") || !todate.substring(0,3).endsWith("/") || !todate.substring(3,6).endsWith("/") || !(date.length()==10) || !(todate.length()==10))
				{
					label.setText("*Wrong date format!!!");
					label.setForeground(Color.RED);
				}
				else
				{
					
					String query="insert into holidays values(?,?,?)";
					int i=0;
					try
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, date);	
						ps.setString(2, todate);
						ps.setString(3, event);
						i=ps.executeUpdate();
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					if(i==1)
					{
						label.setText("Holiday Added...");
						label.setForeground(Color.BLACK);
						label.setFont(new Font("Tahoma", Font.PLAIN, 12));
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
					}
				}
			}
		});
		btnAdd.setBounds(173, 134, 101, 23);
		contentPane.add(btnAdd);		
		
		label_1 = new JLabel(new ImageIcon("holiday.jpg"));
		label_1.setBounds(0, 0, 466, 211);
		contentPane.add(label_1);
	}
}
