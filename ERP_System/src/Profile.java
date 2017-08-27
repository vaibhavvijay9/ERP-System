import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.CardLayout;

public class Profile extends JFrame 
{
	private JPanel contentPane;
	
	public Profile(JFrame h) 
	{
		Profile p=this;
		setTitle("Profile");
		setResizable(false);
		setBounds(100, 100, 720, 510);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(140, 225, 231));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				h.setVisible(true);
				p.dispose();
			}
		});
		
		
		JPanel card_1 = new JPanel();
		card_1.setBackground(new Color(140, 225, 231));
		card_1.setBounds(37, 62, 394, 272);
		
		
		JPanel card_2 = new JPanel();
		card_2.setBackground(new Color(140, 225, 231));
		card_2.setBounds(37,  62, 394, 272);
		card_2.setLayout(null);
			
			JLabel label = new JLabel("Name:");
			label.setBounds(35, 23, 46, 14);
			card_2.add(label);
			
			JLabel label_1 = new JLabel("Roll No:");
			label_1.setBounds(35, 58, 46, 14);
			card_2.add(label_1);
			
			JLabel label_2 = new JLabel("Year:");
			label_2.setBounds(35, 93, 46, 14);
			card_2.add(label_2);
			
			JLabel label_3 = new JLabel("Branch:");
			label_3.setBounds(35, 128, 46, 14);
			card_2.add(label_3);
			
			JLabel label_4 = new JLabel("Section:");
			label_4.setBounds(35, 163, 46, 14);
			card_2.add(label_4);
			
			JLabel label_5 = new JLabel("Mobile:");
			label_5.setBounds(35, 198, 46, 14);
			card_2.add(label_5);
			
			JLabel label_6 = new JLabel("Email:");
			label_6.setBounds(35, 233, 46, 14);
			card_2.add(label_6);
			
			JLabel label_7 = new JLabel("");
			label_7.setBounds(164, 23, 175, 14);
			card_2.add(label_7);
			
			JLabel label_8 = new JLabel("");
			label_8.setBounds(164, 58, 175, 14);
			card_2.add(label_8);
			
			JLabel label_9 = new JLabel("");
			label_9.setBounds(164, 93, 175, 14);
			card_2.add(label_9);
			
			JLabel label_10 = new JLabel("");
			label_10.setBounds(164, 128, 175, 14);
			card_2.add(label_10);
			
			JLabel label_11 = new JLabel("");
			label_11.setBounds(164, 163, 175, 14);
			card_2.add(label_11);
			
			JLabel label_12 = new JLabel("");
			label_12.setBounds(164, 198, 175, 14);
			card_2.add(label_12);
			
			JLabel label_13 = new JLabel("");
			label_13.setBounds(164, 233, 175, 14);
			card_2.add(label_13);
		
		JPanel card_3 = new JPanel();
		card_3.setBackground(new Color(140, 225, 231));
		card_3.setBounds(37, 62, 394, 272);
		card_3.setLayout(null);
			
			JLabel label_14 = new JLabel("Name:");
			label_14.setBounds(35, 46, 46, 14);
			card_3.add(label_14);
			
			JLabel label_15 = new JLabel("Faculty Id:");
			label_15.setBounds(35, 81, 62, 14);
			card_3.add(label_15);
			
			JLabel label_16 = new JLabel("Subject:");
			label_16.setBounds(35, 116, 46, 14);
			card_3.add(label_16);
			
			JLabel label_17 = new JLabel("Mobile:");
			label_17.setBounds(35, 151, 46, 14);
			card_3.add(label_17);
			
			JLabel label_18 = new JLabel("Email:");
			label_18.setBounds(35, 186, 46, 14);
			card_3.add(label_18);
			
			JLabel label_19 = new JLabel("");
			label_19.setBounds(151, 46, 175, 14);
			card_3.add(label_19);
			
			JLabel label_20 = new JLabel("");
			label_20.setBounds(151, 81, 178, 14);
			card_3.add(label_20);
			
			JLabel label_21 = new JLabel("");
			label_21.setBounds(151, 116, 175, 14);
			card_3.add(label_21);
			
			JLabel label_22 = new JLabel("");
			label_22.setBounds(151, 151, 175, 14);
			card_3.add(label_22);
			
			JLabel label_23 = new JLabel("");
			label_23.setBounds(151, 186, 175, 14);
			card_3.add(label_23);
		
			
			
		JPanel cards = new JPanel();
		cards.setBounds(140, 150, 450, 293);
		contentPane.add(cards);
		cards.setLayout(new CardLayout(0, 0));
		
		cards.add(card_1,"");
		cards.add(card_2,"Student");
		cards.add(card_3,"Faculty");
		
		
		//---------
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards,HomePage_Login.usertype);
		
		JLabel label_24 = new JLabel(new ImageIcon(""));
		label_24.setBounds(0, 0, 714, 481);
		contentPane.add(label_24);
		
		JLabel label_25 = new JLabel(new ImageIcon("profile.jpg"));
		label_25.setBounds(0, 0, 714, 134);
		contentPane.add(label_25);
		
		
		//Fetching and displaying student's record
		if(HomePage_Login.usertype.equalsIgnoreCase("Student"))
		{
			String username=HomePage_Login.s1;
			String query="select name,rollno,year,branch,section,mobile,email from student where username=?";
			Connection con=DBInfo.con;
			
			try
			{
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, username);
				
				ResultSet res=ps.executeQuery();
				
				while(res.next())
				  {
					  label_7.setText(res.getString(1).toUpperCase());
					  label_8.setText(res.getString(2));
					  label_9.setText(res.getString(3));
					  label_10.setText(res.getString(4).toUpperCase());
					  label_11.setText(res.getString(5));
					  label_12.setText(res.getString(6));
					  label_13.setText(res.getString(7));			  
				  }
			}
			catch(Exception w)
			{
				w.printStackTrace();
			}
		}
		
		//Fetching and displaying faculty's record
		if(HomePage_Login.usertype.equalsIgnoreCase("faculty"))
		{
			String username=HomePage_Login.s1;
			String query="select name,facultyid,subject,mobile,email from faculty where username=?";
			Connection con=DBInfo.con;
			
			try
			{
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, username);
				
				ResultSet res=ps.executeQuery();
				
				while(res.next())
				  {
					  label_19.setText(res.getString(1).toUpperCase());
					  label_20.setText(res.getString(2));
					  label_21.setText(res.getString(3));
					  label_22.setText(res.getString(4).toUpperCase());
					  label_23.setText(res.getString(5));
				  }
			}
			catch(Exception w)
			{
				w.printStackTrace();
			}
		}
	}
}