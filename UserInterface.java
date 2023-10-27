import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UserInterface {
	//Method to generate random color hue of the color green 
	public static String generateRandomGreenColor() {
		String[] colors = { "#e6ffe6", "#ccffcc", "#b3ffb3", "#b3ffb3", "#99ff99", "#80ff80", "#66ff66", 
							"#66ff66", "#4dff4d", "#33ff33", "#1aff1a", "#00ff00", "#00e600", "#00cc00", 
							"#00b300", "#009900", "#008000", "#006600", "#004d00", "#003300"};
		Random random = new Random();
		int randomNumber = random.nextInt(colors.length);
		return colors[randomNumber];
	}	
	public static void main(String[] args) {
		JFrame frame = new JFrame("User Interface");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JPanel contentPane = new JPanel();
		frame.setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");				
		JMenuItem showDateTime = new JMenuItem("Display Date & Time");
		JMenuItem saveToText = new JMenuItem("Save to Textbox");
		JMenuItem colorChanger = new JMenuItem("Color Changer");
		JMenuItem exitMenu = new JMenuItem("EXIT");
		JTextArea textArea = new JTextArea();
		//To print the date and time in text box 
		showDateTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd         hh:mm:ss aa ");
				String dateTime = dateFormat.format(new Date());
				textArea.setText(dateTime);
				textArea.setFont(new Font("Verdana",1,18));
			}
		});
		//To write and save the text box in a text file 
		saveToText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String content = textArea.getText();
				try {
					FileWriter fileWriter = new FileWriter("time.txt");
					BufferedWriter out = new BufferedWriter(fileWriter);
					out.write(content);
					out.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(frame, "Error Message: File could not be created.");
				}
			}
		});
		//To change the background color 
		String newColor = generateRandomGreenColor();
		colorChanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {				
				contentPane.setBackground(Color.decode(newColor));
			}
		});
		//To exit from the program 
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		menu.add(showDateTime);
		menu.add(saveToText);
		menu.add(colorChanger);
		menu.add(exitMenu);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.add(textArea);
		frame.setVisible(true);
	}
}
