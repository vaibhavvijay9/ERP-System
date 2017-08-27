import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;

public class Registration_done_popup extends JDialog implements ActionListener 
{
	JButton b1;
	public Registration_done_popup(JFrame frm) 
	{
		super(frm);
		getContentPane().setBackground(new Color(220, 220, 220));
		setTitle("Message");
		getContentPane().setLayout(null);
		setSize(350, 200);
		setLocationRelativeTo(this);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		JLabel l=new JLabel("Registration Successful.");
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		l.setBounds(30,32,280,50);
		getContentPane().add(l);
		b1=new JButton("OK");
		b1.setFocusPainted(false);
		b1.setBounds(110, 100, 100, 30);
		b1.setMnemonic(KeyEvent.VK_O);
		getContentPane().add(b1);
		b1.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==b1)
		{
			dispose();
		}
	}
}
