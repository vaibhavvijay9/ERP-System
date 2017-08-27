import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectTable extends JFrame 
{
	static String tablename; 
	private JPanel contentPane;	

	public SelectTable(JFrame h) 
	{
		JFrame frm=this;
		setTitle("Select Table");
		setResizable(false);
		setBounds(100, 100, 394, 241);
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
				frm.dispose();
			}
		});
		
		JLabel lblChooseTableName = new JLabel("Choose Table Name:");
		lblChooseTableName.setBounds(62, 79, 118, 14);
		contentPane.add(lblChooseTableName);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setForeground(Color.RED);
		label.setBounds(10, 187, 246, 14);
		contentPane.add(label);
		
		JComboBox comboBox = new JComboBox(DBInfo.getTables());		//getTables() called
		comboBox.setBounds(190, 76, 146, 20);
		contentPane.add(comboBox);
		
		JButton btnViewTable = new JButton("View Table");
		btnViewTable.setFocusPainted(false);
		btnViewTable.setMnemonic(KeyEvent.VK_V);
		btnViewTable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				tablename=(String) comboBox.getSelectedItem();
				
				if(tablename.equals("Select"))
				{
					label.setText("*Choose a table name!!!");
				}
				else
				{
					label.setText("");
					
					DBInfo.getTableData();
				
					JFrame frame=new JFrame();
					
					frame.setSize(720, 510);
					frame.setBackground(new Color(189,215,238));
					frame.setLocationRelativeTo(frame);
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					
					JTable t=new JTable(DBInfo.data, DBInfo.header);
					t.setFillsViewportHeight(true);
					t.setRowHeight(20);
					JScrollPane pane=new JScrollPane(t);
					frame.getContentPane().add(pane);
					frame.setVisible(true);
					frm.dispose();
				}
			}
		});
		btnViewTable.setBounds(121, 148, 135, 30);
		contentPane.add(btnViewTable);
	}
}