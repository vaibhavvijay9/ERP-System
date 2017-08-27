import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

public class RemoveSubject extends JFrame 
{
	Connection con=DBInfo.con;
	private JPanel contentPane;

	public RemoveSubject() 
	{
		RemoveSubject r=this;
		setTitle("Remove Subject");
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
				r.dispose();
			}
		});
		
		JLabel lblChooseSubject = new JLabel("Choose Subject:");
		lblChooseSubject.setBounds(41, 48, 112, 14);
		contentPane.add(lblChooseSubject);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setForeground(Color.RED);
		label.setBounds(10, 163, 261, 14);
		contentPane.add(label);
		
		JComboBox comboBox = new JComboBox(DBInfo.getSubjects());
		comboBox.setBounds(150, 45, 145, 20);
		contentPane.add(comboBox);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFocusPainted(false);
		btnRemove.setMnemonic(KeyEvent.VK_R);
		btnRemove.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String subject=(String) comboBox.getSelectedItem();
				
				if(subject.equalsIgnoreCase("select"))
				{
					label.setText("*Choose a subject!!!");
				}
				else
				{
					String query="delete from subject where sname=?";
					try 
					{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, subject);
						ps.executeUpdate();
						
						label.setText("Subject Removed....");
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
				}
			}
		});
		btnRemove.setBounds(129, 104, 89, 23);
		contentPane.add(btnRemove);	
		
		JLabel label_1 = new JLabel(new ImageIcon("subject.jpg"));
		label_1.setBounds(0, 0, 367, 188);
		contentPane.add(label_1);
	}
}
