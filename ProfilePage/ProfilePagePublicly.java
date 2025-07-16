package ProfilPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import BaseLogger.info;
import DiscoverPage.DiscoverPage;
import IconMethods.QuestionIcon;
import ImageFilterRaul.BlurringFilter;
import ImageFilterRaul.BrightnessFilter;
import ImageFilterRaul.ContrastFilter;
import ImageFilterRaul.EdgeDetectionFilter;
import ImageFilterRaul.GrayScaleFilter;
import ImageFilterRaul.SharpeningFilter;
import SignUpPage.SignUpMethods;

public class ProfilePagePublicly extends JFrame {
	
	
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_11;
	static JPanel  photoPanel= new JPanel();
    static String nickname;
     static String password;
     static String name ;
     static String surname; 
     static String age ;
     static String email;
     static String type;
     static String mainUser;

	
	
	
	private static LinkedHashMap<Image,Boolean> imageMap= new LinkedHashMap<>();

	
	private static JLabel createLabel(String labelText) {
	    JLabel label = new JLabel(labelText);
	    label.setPreferredSize(new Dimension(150, 20)); // Adjust the size as needed
	    return label;
	}

	

	public ProfilePagePublicly (String nickname, String password, String name, String surname, String age, String email,String type,String mainUser) {
		imageMap.clear();
		ProfilePagePublicly.nickname=nickname;
		ProfilePagePublicly.password=password;
		ProfilePagePublicly.name=name;
		ProfilePagePublicly.surname=surname;
		ProfilePagePublicly.age=age;
		ProfilePagePublicly.email=email;
		ProfilePagePublicly.type=type;
		ProfilePagePublicly.mainUser=mainUser;

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1020, 800);
	    setResizable(true);
	    getContentPane().setLayout(new BorderLayout());
	    

	    JPanel leftPanel = new JPanel(new BorderLayout());
	    leftPanel.setBackground(Color.red);

	    JPanel rightPanel = new JPanel(new BorderLayout());
	    rightPanel.setBackground(Color.blue);

	   
	    JPanel fieldPanel = new JPanel();
	    fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
	    rightPanel.add(fieldPanel, BorderLayout.CENTER);  

	    JPanel nicknamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel nicknameLabel = createLabel("Nickname:");
	    nicknamePanel.add(nicknameLabel);
	    JTextField textField = new JTextField(nickname);
	    textField.setEditable(false);
	    textField.setColumns(25);
	    nicknamePanel.add(textField);
	    fieldPanel.add(nicknamePanel);

	    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel nameLabel = createLabel("Name:");
	    namePanel.add(nameLabel);
	    JTextField textField_1 = new JTextField(name);
	    textField_1.setEditable(false);
	    textField_1.setColumns(25);
	    namePanel.add(textField_1);
	    fieldPanel.add(namePanel);

	    JPanel surnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel surnameLabel = createLabel("Surname:");
	    surnamePanel.add(surnameLabel);
	    JTextField textField_11 = new JTextField(surname);
	    textField_11.setEditable(false);
	    textField_11.setColumns(25);
	    surnamePanel.add(textField_11);
	    fieldPanel.add(surnamePanel);

	    JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel ageLabel = createLabel("Age:");
	    agePanel.add(ageLabel);
	    JTextField textField_2 = new JTextField(age);
	    textField_2.setEditable(false);
		textField_2.setColumns(25);
		agePanel.add(textField_2);
		fieldPanel.add(agePanel);

		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emailLabel = createLabel("Email:");
		emailPanel.add(emailLabel);
		JTextField textField_3 = new JTextField(email);
		textField_3.setEditable(false);
		textField_3.setColumns(25);
		emailPanel.add(textField_3);
		fieldPanel.add(emailPanel);


		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel typeLabel = createLabel("Type:");
		typePanel.add(typeLabel);
		JTextField textField_5 = new JTextField(type);
		textField_5.setEditable(false);
		textField_5.setColumns(25);
		typePanel.add(textField_5);
		fieldPanel.add(typePanel);
		
		JButton discoverButton = new JButton("Discover Page");
		    discoverButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
//logging info
					info.log(String.format("%s returned Discover Page",nickname));
		            ArrayList<String> mainUser= SignUpMethods.gettingAllInformationOfUser(ProfilePagePublicly.mainUser);
		            new DiscoverPage(mainUser.get(0),mainUser.get(1),mainUser.get(2),mainUser.get(3),mainUser.get(4),mainUser.get(5),mainUser.get(6));
		            dispose();
		        }
		    });
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    buttonPanel.add(discoverButton);
	    getContentPane().add(buttonPanel, BorderLayout.NORTH);
		
		

		photoPanel.setLayout(new FlowLayout());
		photoPanel.setBackground(Color.white);
		leftPanel.add(photoPanel, BorderLayout.CENTER);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(750); 
		splitPane.setResizeWeight(0.66); 
											

		getContentPane().add(splitPane, BorderLayout.CENTER);

		int numberOfColumns = 3;
		int numberOfRows = (int) Math.ceil((double) imageMap.size() / numberOfColumns);
		photoPanel.setLayout(new GridLayout(numberOfRows, numberOfColumns));
		JScrollPane photoScrollPane = new JScrollPane(photoPanel);
		photoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		photoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftPanel.add(photoScrollPane, BorderLayout.CENTER);

		loadUserPhotos(photoPanel, nickname, password, name, surname, age, email, type);
		loadProfileImage(rightPanel, nickname);
		setVisible(true);

	}
	/**
	 * 
	 * @param panel
	 * @param nickname
	 * this method puts profile image of given user to photoPanel
	 */
	private void loadProfileImage(JPanel panel, String nickname) {
		try {
			panel.setBackground(Color.white);
			File file = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/ProfilePictures.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				String fileNickname = parts[0];
				String imagePath = parts[1];

				if (fileNickname.equals(nickname)) {
					File imageFile = new File(imagePath);
					Image profileImage = ImageIO.read(imageFile).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
					JLabel profileImageLabel = new JLabel(new ImageIcon(profileImage));

					panel.add(profileImageLabel, BorderLayout.NORTH);
					break;
				}
			}

			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param photoPanel
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param type
	 * this method is used for getting public images of user, adding them to map, and calling refresh method in order to display them in photo Panel
	 */
	
	public void loadUserPhotos(JPanel photoPanel, String nickname, String password, String name, String surname,
			String age, String email, String type) {
		try {
			
			File photosFile = new File(
					"/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt");
			Scanner scanner = new Scanner(photosFile);

			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");

				
				if (parts[0].equals(nickname) && parts[1].equals("public")) {

					BufferedImage myPicture = ImageIO.read(new File(parts[2]));
					Image scaledImage = myPicture.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
					if (imageMap.containsKey(scaledImage)==false) {
						imageMap.put(scaledImage, false);
					}

				}
			}
			refresh(photoPanel);

			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param panel
	 * this method is used for getting key values of map , which are images, converting them to JLabel, and adding them to PhotoPanel for each image.
	 */
	public static void refresh(JPanel panel) {
		panel.removeAll();

		for (Image image : imageMap.keySet()) {
			if (imageMap.get(image) == false) {
				JLabel picLabel = new JLabel(new ImageIcon(image));

				panel.add(picLabel);
			}

		}

		panel.revalidate();
		panel.repaint();
	}
}