import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class ViewNotice extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	String selected_title;
	
	public ViewNotice(JFrame h) 
	{
		JFrame frm=this;
		setResizable(false);
		setTitle("Notice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 510);
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
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				h.setVisible(true);
				frm.dispose();
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(172, 213, 240));
		panel.setBounds(47, 271, 282, 101);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(36, 11, 75, 14);
		panel.add(lblTitle);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(121, 8, 260, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextArea ta=new JTextArea();
		ta.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(ta);
		scrollPane_1.setBounds(10, 36, 512, 348);
		panel.add(scrollPane_1);
			
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
		pane.setBounds(10,80,690,390);	
		
		JLabel label = new JLabel(new ImageIcon("notice1.jpg"));
		label.setBounds(0, 0, 714, 80);
		getContentPane().add(label);
	}
}
