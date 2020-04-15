package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GuiMakerIMP extends GUIMaker {
	private Image car;
	private final static Dimension windowSize= new Dimension(500,500);
	private final static int carSize= 80, fontSize = 18;
	
	private int index;
	private JPanel animation;
	GuiMakerIMP() {
		try {
			car= ImageIO.read(new File("src/resources/car.png"));
		} catch (IOException e) {
			System.out.print("no carga imagen");
		}
	}
	@Override
	public JPanel getPanel(String[]botones, String[]extra, String text, JFrame window) {
		JPanel mainP= new JPanel();
		mainP.setLayout(new BorderLayout());
		mainP.add(getBotones(botones,window), BorderLayout.SOUTH);
		animation = getAnimation(text);
		mainP.add(animation, BorderLayout.CENTER);
		return mainP;
	}
	private JPanel getBotones(String[]botones, JFrame window) {
		JPanel bot = new JPanel();
		bot.setLayout(new GridLayout(2,3));
		for(int i=0;i<botones.length;i++) {
			JButton but = new JButton(botones[i]);
			bot.add(but);
			but.addActionListener((ActionListener) window);
		}
		return bot;
	}
	private JPanel getAnimation(String text) {
		index = windowSize.width;
		JPanel panel = new JPanel() {
			@Override
	         public void paintComponent(Graphics g) {
	        	super.paintComponent(g);
	        	Graphics2D g2d = (Graphics2D) g;
	        	//g2d.setFont(new Font("Arial", Font.PLAIN, fontSize));
	        	if(index>windowSize.width/2 - 50) g2d.drawString(text, index,windowSize.height/5);
	        	else g2d.drawString(text, windowSize.width/2 - 50 ,windowSize.height/5);
	            g2d.drawImage(car,index,windowSize.height/5,carSize,carSize, null);
	            index-=10;
	            try {
					Thread.sleep(40);
				} catch (InterruptedException e) {}
			}
	  
	      };
		return panel;
	}
	private void animateCar() {
		/*while(index>0) {
			animation.repaint();
		}*/
	}
	
	@Override
	public void configurateWindow(JFrame window) {

		window.setPreferredSize(windowSize);
		window.setTitle("Autoescuela PM");
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		animateCar();
	}
	@Override
	public void reInit() {
		// TODO Auto-generated method stub
		index = windowSize.width;
		animateCar();
	}
	@Override
	public JPanel panelAlta(String[]etiq) {
		JPanel alta = new JPanel(new FlowLayout());
		JLabel etiqgeneral;
		JCheckBox actividad = new JCheckBox("�Est� activo?",true);
		JTextField[] inputs = new JTextField[etiq.length];
		for(int i = 0; i<etiq.length;i++) {
			etiqgeneral = new JLabel(etiq[i]+": ");
			inputs[i] = new JTextField(20);
			alta.add(etiqgeneral);
			etiqgeneral.setLabelFor(inputs[i]);
			alta.add(inputs[i]);
		}
	
		alta.add(actividad);
		alta.setVisible(true);
	
		return alta;
	}
	
}
