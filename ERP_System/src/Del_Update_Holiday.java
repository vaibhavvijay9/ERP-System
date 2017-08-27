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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Del_Update_Holiday extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblTo;
	private JTextField textField_1;
	private JLabel lblNameOfHoliday;
	private JTextField textField_2;
	private JLabel label;
	private JButton btnUpdate;
	Connection con=DBInfo.con;
	String str,date,todate,event;
	private JLabel label_1;

	public Del_Update_Holiday() 
	{
		setTitle("Delete/Update Holiday");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 272);
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
		
		JLabel lblSelectHoliday = new JLabel("Select Holiday:");
		lblSelectHoliday.setBounds(40, 39, 109, 14);
		contentPane.add(lblSelectHoliday);
		
		JLabel lblDate = new JLabel("Date: (dd/mm/yyyy)");
		lblDate.setBounds(40, 85, 109, 14);
		contentPane.add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(173, 82, 101, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblTo = new JLabel("to");
		lblTo.setBounds(281, 85, 26, 14);
		contentPane.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(300, 82, 101, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNameOfHoliday = new JLabel("Name of Holiday:");
		lblNameOfHoliday.setBounds(40, 125, 109, 14);
		contentPane.add(lblNameOfHoliday);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 122, 228, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 218, 251, 14);
		contentPane.add(label);
		
		JComboBox comboBox = new JComboBox(DBInfo.getHolidayNames());
		comboBox.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				str=(String) comboBox.getSelectedItem();
				if(str.equalsIgnoreCase("select"))
				{
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
				}
				else
				{
					String query="select * from holidays where event=?";
					try  
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, str);
						ResultSet res=ps.executeQuery();
						while(res.next())
						{
							textField.setText(res.getString(1));
							textField_1.setText(res.getString(2));
							textField_2.setText(res.getString(3));
						}
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
		comboBox.setBounds(173, 36, 228, 20);
		contentPane.add(comboBox);

		
		btnUpdate = new JButton("Update");
		btnUpdate.setFocusPainted(false);
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				date=textField.getText();
				todate=textField_1.getText();
				event=textField_2.getText();
				
				if(date.length()==0 || todate.length()==0 || event.length()==0 || str.equalsIgnoreCase("select"))
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
					
					String query="update holidays set from_date=?,to_date=?,event=? where event=?";
					int i=0;
					try
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, date);	
						ps.setString(2, todate);
						ps.setString(3, event);
						ps.setString(4, str);
						i=ps.executeUpdate();
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					if(i==1)
					{
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						comboBox.setSelectedIndex(0);
					}
					dispose();
					new Del_Update_Holiday().setVisible(true);
					new ViewDialog("Holiday Updated.").setVisible(true);
				}
			}
		});
		btnUpdate.setBounds(88, 173, 101, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(str==null)
				{
					label.setText("*Please select a Holiday Name!!!");
					label.setForeground(Color.RED);
				}
				else
				{
					String query="delete from holidays where event=?";
					int i=0;
					try
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, str);
						i=ps.executeUpdate();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					if(i==1)
					{
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						comboBox.setSelectedIndex(0);
					}
					dispose();
					new Del_Update_Holiday().setVisible(true);
					new ViewDialog("Holiday Deleted.").setVisible(true);
				}
			}
		});
		btnDelete.setFocusPainted(false);
		btnDelete.setBounds(245, 173, 101, 23);
		contentPane.add(btnDelete);		
		
		label_1 = new JLabel(new ImageIcon("holiday1.jpg"));
		label_1.setBounds(0, 0, 466, 243);
		contentPane.add(label_1);
	}
}