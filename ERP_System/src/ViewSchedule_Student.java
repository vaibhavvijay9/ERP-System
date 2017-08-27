import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ViewSchedule_Student extends JFrame 
{
	private JPanel contentPane;	

	public ViewSchedule_Student(JFrame h) 
	{
		JFrame frm=this;
		setTitle("Schedule");
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
		
		//getting corresponding schedule
		DBInfo.getScheduleStudent(StudentSection.year, StudentSection.branch, StudentSection.section);
		
		JTable t=new JTable(DBInfo.data, DBInfo.header);
		t.setRowHeight(50);		
		JScrollPane pane=new JScrollPane(t);
		pane.setSize(667, 328);
		pane.setLocation(25, 130);
		contentPane.add(pane);	
		
		JLabel label = new JLabel(new ImageIcon("timetable.jpg"));
		label.setBounds(0, 0, 714, 100);
		contentPane.add(label);
	}
}