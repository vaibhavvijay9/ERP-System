import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Del_Update_Notice extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	String selected_title;

	public Del_Update_Notice() 
	{
		setResizable(false);
		setTitle("Delete/Update Notice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 524);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(172, 213, 240));
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(172, 213, 240));
		panel.setBounds(47, 271, 282, 101);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 392, 335, 14);
		panel.add(label);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(36, 11, 75, 14);
		panel.add(lblTitle);
		
		textField = new JTextField();
		textField.setBounds(121, 8, 260, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextArea ta=new JTextArea();
		
		JScrollPane scrollPane_1 = new JScrollPane(ta);
		scrollPane_1.setBounds(18, 38, 488, 318);
		panel.add(scrollPane_1);
		
		
		JButton btnUpdateNotice = new JButton("Update Notice");
		btnUpdateNotice.setFocusPainted(false);
		btnUpdateNotice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(textField.getText().length()==0)
				{
					label.setText("*Select a notice first!!!");
					label.setForeground(Color.RED);
				}
				else
				{
					String query="update notice set title=?,content=? where title=?";
					try
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1, textField.getText());
						ps.setString(2, ta.getText());
						ps.setString(3, selected_title);
						ps.executeUpdate();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
					label.setText("Notice Updated Successfully...");		//here of no use
					textField.setText(null);
					ta.setText(null);
					dispose();
					new Del_Update_Notice().setVisible(true);
					//new ViewDialog("Notice Updated.").setVisible(true);	//useful
				}
			}
		});
		btnUpdateNotice.setBounds(121, 367, 121, 23);
		panel.add(btnUpdateNotice);
		
		JButton btnDeleteNotice = new JButton("Delete Notice");
		btnDeleteNotice.setFocusPainted(false);
		btnDeleteNotice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(textField.getText().length()==0)
				{
					label.setText("*Select a notice first!!!");
					label.setForeground(Color.RED);
				}
				else
				{
					String query="delete from notice where title=?";
					try
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1, selected_title);
						ps.executeUpdate();
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					label.setText("Notice Deleted Successfully...");
					textField.setText(null);
					ta.setText(null);
					dispose();
					new Del_Update_Notice().setVisible(true);
				}
			}
		});
		btnDeleteNotice.setBounds(302, 367, 121, 23);
		panel.add(btnDeleteNotice);		
		
		JList list = new JList(DBInfo.getNoticeTitles());
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				selected_title=(String) list.getSelectedValue();
				String query="select * from notice where title=?";
				try 
				{
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					ps.setString(1, selected_title);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						textField.setText(res.getString(1));
						ta.setText(res.getString(2));		//here setText() not append()
					}
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				label.setText(null);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(31, 32, 96, 187);
		
		
		
		JSplitPane pane=new JSplitPane();
		pane.setEnabled(false);
		pane.setLeftComponent(scrollPane);
		pane.setRightComponent(panel);
		contentPane.add(pane);
		pane.setBounds(10,80,690,410);
		
		JLabel label_1 = new JLabel(new ImageIcon("notice1.jpg"));
		label_1.setBounds(0, 0, 714, 80);
		contentPane.add(label_1);
		
	}
}