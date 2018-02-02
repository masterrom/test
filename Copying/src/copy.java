import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class copy implements ActionListener	 {
	
	private static JLabel location;
	private static JLabel phone;
	private static JLabel category;
	private static JLabel postal;
	private static JLabel price;
	private static JLabel brand;
	private static JLabel screenSize;
	
	private static JButton description;
	private static JButton heading ;
	private static JButton Postal ;
	private static JButton Price;
	private static JButton nextAd;
	private static String full, headings, cpostal,cprice;
	private static ArrayList<String> DocNames;
	private static int total;

	public copy() {
		// TODO Auto-generated constructor stub
		DocNames = new ArrayList<String>();
		this.total = 0;
		DocNames.add("src/kijijiDes");
		DocNames.add("src/kijijiDes2");
		DocNames.add("src/kijijiDes3");
		DocNames.add("src/kijijiDes4");
		DocNames.add("src/kijijiDes5");
		
		
		this.location = new JLabel();
		this.location.setFont(new Font("Serif", Font.PLAIN, 40));
		this.phone = new JLabel();
		this.phone.setFont(new Font("Serif", Font.PLAIN, 40));
		this.category = new JLabel();
		this.category.setFont(new Font("Serif", Font.PLAIN, 40));
		this.postal = new JLabel();
		this.postal.setFont(new Font("Serif", Font.PLAIN, 40));
		this.price = new JLabel();
		this.price.setFont(new Font("Serif", Font.PLAIN, 40));
		this.brand  = new JLabel();
		this.brand.setFont(new Font("Serif", Font.PLAIN, 40));
		this.screenSize = new JLabel();
		this.screenSize.setFont(new Font("Serif", Font.PLAIN, 40));
		
		this.description = new JButton("Copy Description");
		this.description.addActionListener(this);
		this.description.setFont(new Font("Serif", Font.PLAIN, 40));
		this.heading  = new JButton("Copy Heading");
		this.heading.setFont(new Font("Serif", Font.PLAIN, 40));
		this.heading.addActionListener(this);
		this.Postal = new JButton ("Copy Postal");
		this.Postal.setFont(new Font("Serif", Font.PLAIN, 40));
		this.Postal.addActionListener(this);
		this.Price = new JButton ("Copy Price");
		this.Price.setFont(new Font("Serif", Font.PLAIN, 40));
		this.Price.addActionListener(this);
		this.nextAd = new JButton("Next Ad");
		this.nextAd.setFont(new Font("Serif", Font.PLAIN, 40));
		this.nextAd.addActionListener(this);
		
		
		this.full = "";
		this.headings= "";
		this.cpostal = "";
		this.cprice = "";
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new GridLayout(12,0));
		
		
		frame.getContentPane().add(this.location);
		frame.getContentPane().add(this.phone);
		frame.getContentPane().add(this.category);
		frame.getContentPane().add(this.postal);
		frame.getContentPane().add(this.price);
		frame.getContentPane().add(this.brand);
		frame.getContentPane().add(this.screenSize);
		
//		frame.getContentPane().add(new JPanel());
//		frame.getContentPane().add(new JPanel());
//		frame.getContentPane().add(new JPanel());
//		frame.getContentPane().add(new JPanel());
//		
		
		frame.getContentPane().add(this.Postal);
		frame.getContentPane().add(this.Price);
		frame.getContentPane().add(this.description);
		frame.getContentPane().add(this.heading);
		frame.getContentPane().add(this.nextAd);
		
		frame.setSize(new Dimension(1000,700));
		//frame.pack();
		frame.setVisible(true);
		
	}
	
	
	public void newDoc(String docName) {
		copy c = new copy();
		Pattern num = Pattern.compile("^Number:\\s(\\d)*[-](\\d)*[-](\\d)*$");
		Pattern pos = Pattern.compile("^Postal\\sCode:\\s(.)*$");
		Pattern pri = Pattern.compile("^Price:\\s(.)*$");
		Pattern loc = Pattern.compile("^Location:\\s(.)*$");
		Pattern cat = Pattern.compile("^Category:\\s(.)*$");
		Pattern brn = Pattern.compile("^Brand:\\s(.)*$");
		Pattern scrSize = Pattern.compile("^Screen Size:\\s(.)*$");
		
		Matcher m;
		
		try {
			FileReader file  = new FileReader(docName);

			BufferedReader lineInput = new BufferedReader(file);
			String line = "";
			
			int count=0;
			int state = 0;
			// while ((line = lineInput.readLine()) != null) {
			while(true) {
				line = lineInput.readLine();
				if(line == null)break;
				//System.out.println("State: " + state);
				//System.out.println(line);
				System.out.println(full);
				System.out.println("--------------------");
				switch(state){
					case 0:
						m = num.matcher(line);
						if (m.matches()) {
							phone.setText(line);
							state = 1;
							full = "";
							headings = "";
							cpostal = "";
							cprice = "";
							
							break;
						}
					
					case 1:
						m = pos.matcher(line);
						if (m.matches()) {
							postal.setText(line);
							state = 2;
							full = "";
							headings = "";
							cpostal = line;
							cprice = "";
							
							break;
						}
					
					case 2:
						m = pri.matcher(line);
						if (m.matches()) {
							price.setText(line);
							state = 3;	
							full = "";
							headings = "";
							cpostal = "";
							cprice = line;
							
							break;
						}
					case 3:
						m = loc.matcher(line);
						if (m.matches()) {
							location.setText(line);
							state = 4;
							full = "";
							headings = "";
							cpostal = "";
							break;
						}
					case 4:	
						m = cat.matcher(line);
						if (m.matches()) {
							category.setText(line);
							state = 5;
							full = "";
							headings = "";

							break;
						}
					case 5:	
						m = brn.matcher(line);
						if (m.matches()) {
							brand.setText(line);
							state = 6;
							full = "";
							headings = "";

							break;
						}
					case 6:
						Pattern p = Pattern.compile("^aaaaaaaa$");
						m = p.matcher(line);
						if (m.matches()) {
							state = 7;
							full = "";
							headings = "";
							
							break;
						}
					case 7:
						Pattern p1 = Pattern.compile("^bbbbbbb$");
						m = p1.matcher(line);
						if (m.matches()) {
							state = 8;
							break;
						}else {
							headings = headings + line;
							break;
						}
						
						
					case 8:
						
						full = full + line + "\n";
						break;
						
				}
				
				//System.out.println(line);
				count++;
				
				
			}
			
			System.out.println(count);
			System.out.println(full);
			file.close();
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String [] args) {
		copy c = new copy();
		Pattern num = Pattern.compile("^Number:\\s(\\d)*[-](\\d)*[-](\\d)*$");
		Pattern pos = Pattern.compile("^Postal\\sCode:\\s(.)*$");
		Pattern pri = Pattern.compile("^Price:\\s(.)*$");
		Pattern loc = Pattern.compile("^Location:\\s(.)*$");
		Pattern cat = Pattern.compile("^Category:\\s(.)*$");
		Pattern brn = Pattern.compile("^Brand:\\s(.)*$");
		Pattern scrSize = Pattern.compile("^Screen Size:\\s(.)*$");
		
		Matcher m;
		
		try {
			FileReader file  = new FileReader("src/kijijiDes");

			BufferedReader lineInput = new BufferedReader(file);
			String line = "";
			
			int count=0;
			int state = 0;
			// while ((line = lineInput.readLine()) != null) {
			while(true) {
				line = lineInput.readLine();
				if(line == null)break;
				//System.out.println("State: " + state);
				//System.out.println(line);
				System.out.println(full);
				System.out.println("--------------------");
				switch(state){
					case 0:
						m = num.matcher(line);
						if (m.matches()) {
							phone.setText(line);
							state = 1;
							full = "";
							headings = "";
							cpostal = "";
							cprice = "";
							
							break;
						}
					
					case 1:
						m = pos.matcher(line);
						if (m.matches()) {
							postal.setText(line);
							state = 2;
							full = "";
							headings = "";
							cpostal = line;
							cprice = "";
							
							break;
						}
					
					case 2:
						m = pri.matcher(line);
						if (m.matches()) {
							price.setText(line);
							state = 3;	
							full = "";
							headings = "";
							cpostal = "";
							cprice = line;
							
							break;
						}
					case 3:
						m = loc.matcher(line);
						if (m.matches()) {
							location.setText(line);
							state = 4;
							full = "";
							headings = "";
							cpostal = "";
							break;
						}
					case 4:	
						m = cat.matcher(line);
						if (m.matches()) {
							category.setText(line);
							state = 5;
							full = "";
							headings = "";

							break;
						}
					case 5:	
						m = brn.matcher(line);
						if (m.matches()) {
							brand.setText(line);
							state = 6;
							full = "";
							headings = "";

							break;
						}
					case 6:
						Pattern p = Pattern.compile("^aaaaaaaa$");
						m = p.matcher(line);
						if (m.matches()) {
							state = 7;
							full = "";
							headings = "";
							
							break;
						}
					case 7:
						Pattern p1 = Pattern.compile("^bbbbbbb$");
						m = p1.matcher(line);
						if (m.matches()) {
							state = 8;
							break;
						}else {
							headings = headings + line;
							break;
						}
						
						
					case 8:
						
						full = full + line + "\n";
						break;
						
				}
				
				//System.out.println(line);
				count++;
				
				
			}
			
			System.out.println(count);
			System.out.println(full);
			file.close();
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Copy Description") {
			StringSelection stringSelection = new StringSelection(full);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}else if (e.getActionCommand() == "Copy Heading") {
			StringSelection stringSelection = new StringSelection(headings);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}else if (e.getActionCommand() == "Copy Postal") {
			StringSelection stringSelection = new StringSelection(cpostal);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}else if (e.getActionCommand() == "Copy Price") {
			StringSelection stringSelection = new StringSelection(cprice);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}else if (e.getActionCommand() == "Next Ad") {
			if (total < 5) {
				newDoc(this.DocNames.get(total));
				
				total ++;
			}
			
		}
		
		
	}

}
