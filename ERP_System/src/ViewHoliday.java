import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class ViewHoliday extends JFrame 
{
	private JPanel contentPane;

	public ViewHoliday(JFrame h) 
	{
		JFrame frm=this;
		setTitle("Holidays");
		setResizable(false);
		setBounds(100, 100, 720, 510);
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
				h.setVisible(true);
				frm.dispose();
			}
		});
		
		DBInfo.getHolidays();		//
		
		JTable t=new JTable(DBInfo.data, DBInfo.header);
		t.setFillsViewportHeight(true);
		t.setRowHeight(20);
		JScrollPane pane=new JScrollPane(t);
		pane.setBounds(90, 125, 537, 267);
		contentPane.add(pane);
		
		JLabel label_1 = new JLabel(new ImageIcon("holiday2.png"));
		label_1.setBounds(0, 0, 714, 107);
		contentPane.add(label_1);
		
		JLabel label = new JLabel(new ImageIcon("holiday3.jpg"));
		label.setBounds(0, 108, 714, 378);
		contentPane.add(label);
		
		//modifying column width
		TableColumn column=null;
		column=t.getColumnModel().getColumn(1);		// 2nd column increased
		column.setPreferredWidth(300);		
	}
}
