import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Add_Notice extends JFrame
{
	private JTextField textField;
	private JTextField textField_1;
	
	Date d=new Date();
	SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
	String date=f.format(d);
	
	public Add_Notice(JFrame h) 
	{
		JFrame frm=this;
		setResizable(false);
		getContentPane().setBackground(new Color(102, 204, 204));
		setTitle("Notice");
		setSize(720, 521);
		setLocationRelativeTo(this);
		getContentPane().setLayout(null);
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
		
		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 474, 244, 14);
		getContentPane().add(label_2);
		
		JLabel lblChooseFile = new JLabel("Choose File:");
		lblChooseFile.setBounds(116, 83, 89, 14);
		getContentPane().add(lblChooseFile);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(215, 80, 250, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(116, 111, 89, 14);
		getContentPane().add(lblTitle);
		
		textField = new JTextField();
		textField.setBounds(215, 111, 250, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(141, 111, 46, 14);
		getContentPane().add(label_1);
		
		JTextArea ta=new JTextArea(5,20);
		ta.setMargin(new Insets(5, 5, 5, 5));
		
		JScrollPane pane=new JScrollPane(ta);
		getContentPane().add(pane);
		pane.setBounds(118, 145, 470, 297);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setFocusPainted(false);
		btnUpload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				label_2.setText(null);
				JFileChooser ch=new JFileChooser();
				FileNameExtensionFilter filter=new FileNameExtensionFilter("Text Files", "txt");
				ch.addChoosableFileFilter(filter);
				ch.setAcceptAllFileFilterUsed(false);
				int result=ch.showOpenDialog(null);
				File obj=ch.getSelectedFile();
				
				if(result==JFileChooser.APPROVE_OPTION)
				{
					textField_1.setText(String.valueOf(obj));	
					StringBuffer sb=new StringBuffer();
					try 
					{
						FileReader fr=new FileReader(obj.getPath());
						BufferedReader br=new BufferedReader(fr);
						String str="";
						while((str=br.readLine())!=null)
						{
							sb.append(str+"\n");
						}
						ta.setText(String.valueOf(sb));
						fr.close();
						br.close();
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else if(result==JFileChooser.CANCEL_OPTION)
				{
					textField_1.setText("No file selected");
					label_2.setText("*No file selected");
					label_2.setForeground(Color.RED);
				}
			}
		});
		btnUpload.setBounds(484, 79, 89, 23);
		getContentPane().add(btnUpload);
		
		
		JButton btnAddNotice = new JButton("Add Notice");
		btnAddNotice.setFocusPainted(false);
		btnAddNotice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String title=date+"-"+textField.getText();
				String content=ta.getText();
				int i=0;
				
				if(textField_1.getText().equalsIgnoreCase("no file selected") || textField_1.getText().length()==0)
				{
					label_2.setText("*Please upload a file!!!");
					label_2.setForeground(Color.RED);
				}
				else if(textField.getText().length()==0)
				{
					label_2.setText("*Please provide a suitable title!!!");
					label_2.setForeground(Color.RED);
				}
				else if(textField.getText().length()>16)
				{
					label_2.setText("*title length should not exceed 16 characters!!!");
					label_2.setForeground(Color.RED);
				}
				else
				{
					String query="insert into notice values(?,?)";
					try
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1, title);
						ps.setString(2, content);
						i=ps.executeUpdate();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					if(i==1)
					{
						label_2.setText("Notice added....");
						label_2.setForeground(Color.BLACK);
						
						textField.setText(null);
						textField_1.setText(null);
						ta.setText(null);
					}
					
				}				
			}
		});
		btnAddNotice.setBounds(254, 453, 175, 35);
		getContentPane().add(btnAddNotice);
		
		JLabel label = new JLabel(new ImageIcon("notice.png"));
		label.setBounds(0, 0, 714, 551);
		getContentPane().add(label);
	}
}