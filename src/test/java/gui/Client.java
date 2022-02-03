package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import drinks.Drink;
import utils.ExcelFile;

public class Client {
	
	ArrayList<JButton> buttons;
	JPanel namesPanel;
	JPanel amountPanel;
	JPanel picturePanel;
	JPanel totalPanel;
	JLabel picture;
	JLabel cijena = new JLabel();
	JComboBox types;
	JComboBox amount;
	JList namesList;
	JList totalList;
	JButton platiBtn;
	ArrayList<Drink> drinks;
	String[] amounts = {"1","2","3","4","5"};
	List<String> narucenoList = new ArrayList<>();
	List<Drink> serverList = new ArrayList<>();
	
	JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Client() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JButton naruci = new JButton("Naruči");
		naruci.setFont(new Font("Segoe UI", Font.BOLD, 32));
		naruci.setForeground(new Color(240, 100, 0));
		naruci.setBackground(new Color(250, 202, 171));
		naruci.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		naruci.setBounds(53, 499, 182, 114);
		naruci.setFocusPainted(false);
		frame.getContentPane().add(naruci);
		
		JLabel kafic_Logo = new JLabel("");
		kafic_Logo.setIcon(new ImageIcon(Client.class.getResource("/gui/imgs/default.jpg")));
		kafic_Logo.setBounds(53, 53, 200, 150);
		frame.getContentPane().add(kafic_Logo);
		
		JLabel bg = new JLabel("");
		bg.setIcon(new ImageIcon(Client.class.getResource("/gui/imgs/bg.jpg")));
		bg.setBounds(0, 0, 940, 664);
		frame.getContentPane().add(bg);
		
		frame.setBounds(100, 100, 956, 703);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		naruci.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	naruci.setBackground(new Color(230, 172, 141));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	naruci.setBackground(new Color(250, 202, 171));
		    }
		    public void	mouseClicked(java.awt.event.MouseEvent evt) {
		    	frame.getContentPane().removeAll();
		    	frame.repaint();
		    	selectionFrame();
		    }
		});
	}
	
	private void selectionFrame() {
		GridLayout gumbiLayout = new GridLayout(0,2);
		gumbiLayout.setHgap(10);
		gumbiLayout.setVgap(10);
		
		JLabel contentPane = new JLabel();
		contentPane.setIcon(new ImageIcon(Client.class.getResource("/gui/imgs/bg.jpg")));
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.setLayout(gumbiLayout);
		((JComponent) frame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(40,0,40,0));
		
		this.platiBtn = new JButton("Pošalji narudžbu");
		platiBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
		platiBtn.setForeground(Color.GRAY);
		platiBtn.setBackground(new Color(250, 202, 171));
		platiBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		platiBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				
			}
		});
		
		try {
			buttons = buttonsInit();
			for(JButton t: buttons) {
				frame.add(t);
			}
			frame.add(platiBtn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setSize(956, 703);   
		frame.setVisible(true);
	}
	
	private ArrayList<JButton> buttonsInit() throws IOException{
		ArrayList<JButton> btns = new ArrayList<>();
		ExcelFile x = new ExcelFile();
		Map<String, ArrayList> insertion = x.returnAll();
		for(String s: insertion.keySet()) {
			ImageIcon icon = new ImageIcon(Client.class.getResource("/gui/imgs/" + s + ".png"));
			JButton btn = new JButton(s, icon);
			btn.setHorizontalTextPosition(SwingConstants.LEFT);
			btn.setIconTextGap(20);
			btn.setFont(new Font("Segoe UI", Font.BOLD, 32));
			btn.setForeground(Color.BLACK);
			btn.setBackground(new Color(250, 202, 171));
			btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			btn.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					try {
						drinks = insertion.get(s);
						specificButtonInit(drinks);
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
			});
			btns.add(btn);
		}
		return btns;
	}
	
	private void specificButtonInit(ArrayList<Drink> drinks) throws IOException{
		for(JButton t: buttons) {
			frame.remove(t);
		}
		frame.remove(platiBtn);
		frame.repaint();
		String[] names = new String[drinks.size()];
		int i = 0;
		for(Drink drink: drinks) {
			names[i++] = drink.getName();
		}
		
		this.namesList = new JList(names);
		namesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        namesList.setSelectedIndex(0);
        namesList.setFont(new Font("Segoe UI", Font.BOLD, 32));
        namesList.setBackground(new Color(250, 202, 171));
		
        this.picture = new JLabel();
        picture.setIcon(new ImageIcon(Client.class.getResource("/gui/imgs/products/" + names[0] + ".jpg")));
        picture.setText(null);
        picture.setBackground(new Color(250, 202, 171));
        picture.setHorizontalAlignment(JLabel.CENTER);
		
        this.cijena = new JLabel(drinks.get(namesList.getSelectedIndex()).getPrice() + " kn", SwingConstants.CENTER);
        cijena.setFont(new Font("Segoe UI", Font.BOLD, 32));
        cijena.setOpaque(true);
        cijena.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        cijena.setForeground(Color.BLACK);
        
        namesList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				picture.setIcon(new ImageIcon(Client.class.getResource("/gui/imgs/products/" + names[namesList.getSelectedIndex()] + ".jpg")));
				cijena.setText(drinks.get(namesList.getSelectedIndex()).getPrice() + " kn");
		    	types.removeAllItems();
		        String[] x = getDrinkTypes(drinks.get(namesList.getSelectedIndex()));
		        for(String y : x) {
		        	types.addItem(y);
		        }
		        types.setSelectedIndex(0);
		    	amount.setSelectedIndex(0);
			}
        });
        
        this.totalList = new JList(narucenoList.toArray());
        totalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        totalList.setSelectedIndex(0);
        totalList.setFont(new Font("Segoe UI", Font.BOLD, 25));
        totalList.setBackground(new Color(250, 202, 171));
        
        JScrollPane totalPanel =  new JScrollPane(totalList);
        totalPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        GridLayout amountLayout = new GridLayout(0,2);
        amountLayout.setHgap(10);
        amountLayout.setVgap(10);
        JPanel amountPanel = new JPanel(amountLayout);
        amountPanel.setOpaque(false);
        
        this.types = new JComboBox(getDrinkTypes(drinks.get(namesList.getSelectedIndex())));
        types.setSelectedIndex(0);
        types.setFont(new Font("Segoe UI", Font.BOLD, 25));
        types.addItemListener(this::typesItemStateChanged);
        this.amount = new JComboBox(amounts);
        amount.setFont(new Font("Segoe UI", Font.BOLD, 25));
        amount.addItemListener(this::amountItemStateChanged);
        amount.setSelectedIndex(0);
        
        JButton addBtn = new JButton("Dodaj");
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
        addBtn.setForeground(Color.BLACK);
        addBtn.setBackground(new Color(250, 202, 171));
        addBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				String t = " "; 
				if (types.getSelectedItem() != "") {
					t = t + "(" + types.getSelectedItem() + ")";
				}
				int amt = Integer.parseInt(amounts[amount.getSelectedIndex()]); 
				int ukupnaCijena = drinks.get(namesList.getSelectedIndex()).getPrice() * amt;
				String x1 = " - " + ukupnaCijena + " kn";
				narucenoList.add("(" + amounts[amount.getSelectedIndex()] + "x) " + names[namesList.getSelectedIndex()] + t + x1);
				totalList.setListData(narucenoList.toArray());
				totalList.setSelectedIndex(narucenoList.size() - 1);
				totalPanel.setViewportView(totalList);
				
				Drink d = new Drink(drinks.get(namesList.getSelectedIndex()).getName(),
						drinks.get(namesList.getSelectedIndex()).getPrice(), (String) types.getSelectedItem(), 0);
				d.setAmount(amt);
				serverList.add(d);
			}
		});
        
        JButton removeBtn = new JButton("Ukloni");
        removeBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
        removeBtn.setForeground(Color.BLACK);
        removeBtn.setBackground(new Color(250, 202, 171));
        removeBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        removeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (narucenoList.size() > 0) {
					int ind = totalList.getSelectedIndex();
					narucenoList.remove(ind);
					totalList.setListData(narucenoList.toArray());
					if (narucenoList.size() > 0) totalList.setSelectedIndex(narucenoList.size() - 1);
					totalPanel.setViewportView(totalList);
					serverList.remove(ind);
				}
			}
		});
        
        JButton backBtn = new JButton("Natrag");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 32));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBackground(new Color(250, 202, 171));
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				frame.getContentPane().removeAll();
		    	frame.repaint();
		    	selectionFrame();
			}
		});
        
        amountPanel.add(types);
        amountPanel.add(amount);
        amountPanel.add(cijena);
        amountPanel.add(addBtn);
        amountPanel.add(removeBtn);
        amountPanel.add(backBtn);    
        
        JScrollPane namesPanel = new JScrollPane(namesList);
        namesPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        JScrollPane picturePanel =  new JScrollPane(picture);
        picturePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
    	frame.add(namesPanel);
    	frame.add(picturePanel);
    	frame.add(totalPanel);
    	frame.add(amountPanel);
		frame.setVisible(true);
	}
	
	public static String[] getDrinkTypes(Drink d) {
		String rawTypes = d.getType();
		String[] types = rawTypes.split(", ");
		return types;
	}
	
	public void typesItemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {

	    }
	}
	
	public void amountItemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        int x = Integer.parseInt((String) e.getItem());
	        cijena.setText(drinks.get(namesList.getSelectedIndex()).getPrice() * x + " kn");
	    }
	}
}
