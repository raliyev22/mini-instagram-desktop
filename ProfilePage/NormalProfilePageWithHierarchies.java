package ProfilPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import BaseLogger.error;
import BaseLogger.info;
import DiscoverPage.DiscoverPage;
import DiscoverPage.DiscoverPageForAdmin;
import IconMethods.QuestionIcon;
import ImageFilterRaul.BlurringFilter;
import ImageFilterRaul.BrightnessFilter;
import ImageFilterRaul.ContrastFilter;
import ImageFilterRaul.EdgeDetectionFilter;
import ImageFilterRaul.GrayScaleFilter;
import ImageFilterRaul.ImageFilterRaul;
import ImageFilterRaul.SharpeningFilter;
import LoginPage.LoginPage;
import Provided_Methods.ImageMatrix;
import Provided_Methods.ImageSecretary;
import SignUpPage.SignUpMethods;
import Users.FreeUser;
import Users.HobbyistUser;
import Users.ProfessionalUser;
import Users.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class NormalProfilePageWithHierarchies extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
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

	
	
	
	private static LinkedHashMap<Image,Boolean> imageMap= new LinkedHashMap<>();
	private static LinkedHashMap<Image, String> imagePathMap= new LinkedHashMap<>();
	
	private static JLabel createLabel(String labelText) {
	    JLabel label = new JLabel(labelText);
	    label.setPreferredSize(new Dimension(150, 20)); // Adjust the size as needed
	    return label;
	}

	
	/**
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param type
	 * this method creates TextFields for given arguments,photoPanel where images will be displayed,edit and "apply changes" buttons,upload button, and discover page and login page buttons which user can move on there.
	 */
	public NormalProfilePageWithHierarchies (String nickname, String password, String name, String surname, String age, String email,String type) {
//logging info
		info.log(String.format("%s successfully logged in his/her Profile Page",nickname));
		imageMap.clear();
		imagePathMap.clear();
		NormalProfilePageWithHierarchies.nickname=nickname;
		NormalProfilePageWithHierarchies.password=password;
		NormalProfilePageWithHierarchies.name=name;
		NormalProfilePageWithHierarchies.surname=surname;
		NormalProfilePageWithHierarchies.age=age;
		NormalProfilePageWithHierarchies.email=email;
		NormalProfilePageWithHierarchies.type=type;
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

	    JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel passwordLabel = createLabel("Password:");
	    passwordPanel.add(passwordLabel);
	    JTextField textField_4 = new JTextField(password);
	    textField_4.setEditable(false);
	    textField_4.setColumns(25);
	    passwordPanel.add(textField_4);
	    fieldPanel.add(passwordPanel);

	    JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel typeLabel = createLabel("Type:");
	    typePanel.add(typeLabel);
	    JTextField textField_5 = new JTextField(type);
	    textField_5.setEditable(false);
	    textField_5.setColumns(25);
	    typePanel.add(textField_5);
	    fieldPanel.add(typePanel);

		photoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
//*		
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

		JButton openEditableButton = new JButton("Edit"); 


		
		openEditableButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				
				textField_1.setEditable(true);
				textField_11.setEditable(true);
				textField_2.setEditable(true);

				textField_4.setEditable(true);
//logging info
				info.log(String.format("%s wanted to change his/her information",nickname));

			}
		});

		JButton editButton = new JButton("Apply Changes");
		JPanel editButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		
		editButtonPanel.add(editButton);
		editButtonPanel.add(openEditableButton);
		

		
		rightPanel.add(editButtonPanel, BorderLayout.SOUTH);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String newName = textField_1.getText(); // 
				String newSurname = textField_11.getText();
				String newAge = textField_2.getText();
				String newPassword = textField_4.getText();

				

				int ageAsInt = Integer.parseInt(newAge);
//logging info
				info.log(String.format("%s successfully updated his/her information",nickname));

				SignUpMethods.updateUserCredentials(NormalProfilePageWithHierarchies.nickname, newPassword, newName, newSurname, ageAsInt, NormalProfilePageWithHierarchies.email, NormalProfilePageWithHierarchies.type);

				textField_1.setEditable(false);
				textField_2.setEditable(false);
				textField_11.setEditable(false);

				textField_4.setEditable(false);

			}
		});




		JButton uploadButton = new JButton("Upload");
		uploadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				String isprivate;

				BufferedImage myPicture;
				try {

					JFileChooser fileChooser = new JFileChooser();
					fileChooser
							.setCurrentDirectory(new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/IMAGES"));
					int returnValue = fileChooser.showOpenDialog(null); // this line will show the file chooser

					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
//logging info
						info.log(String.format("%s uploaded %s image",nickname,getOnlyFileNameWithoutExtension(selectedFile.getName())));

						myPicture = ImageIO.read(selectedFile);

						Image scaledImage = myPicture.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
						imageMap.put(scaledImage, false);
						imagePathMap.put(scaledImage, selectedFile.getAbsolutePath());
						refresh(photoPanel);

						try {
							FileWriter writer = new FileWriter(
									"/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt",
									true);
							writer.write(nickname + " private " + selectedFile.getAbsolutePath() + "\n"); 
																											
																											
																											
							writer.close();

						}

						catch (FileNotFoundException fileNotFoundException) {
							System.out.println("FILE READING error");
						}
					}

				} catch (IOException e) {
					// TODO 
					e.printStackTrace();
				}
			}
		});

		
	 	JPanel uploadPanel = new JPanel(new BorderLayout());
	 	
		JPanel uploadButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		uploadButtonPanel.add(uploadButton);

	    JButton discoverButton = new JButton("Discover Page");
	    discoverButton.addActionListener(
	    		new ActionListener() {
	    			public void actionPerformed(ActionEvent a) {
//logging info
	    				info.log(String.format("%s moved to Discover Page",nickname));

	    				
	    				if (NormalProfilePageWithHierarchies.type.equals("Administrator")) {
		    				new DiscoverPageForAdmin(NormalProfilePageWithHierarchies.nickname,NormalProfilePageWithHierarchies.password,NormalProfilePageWithHierarchies.name,NormalProfilePageWithHierarchies.surname,NormalProfilePageWithHierarchies.age,NormalProfilePageWithHierarchies.email,NormalProfilePageWithHierarchies.type);
		    				NormalProfilePageWithHierarchies.this.dispose();
	    				}
	    				else {
	    					new DiscoverPage(NormalProfilePageWithHierarchies.nickname,NormalProfilePageWithHierarchies.password,NormalProfilePageWithHierarchies.name,NormalProfilePageWithHierarchies.surname,NormalProfilePageWithHierarchies.age,NormalProfilePageWithHierarchies.email,NormalProfilePageWithHierarchies.type);
	    					NormalProfilePageWithHierarchies.this.dispose();
	    					
	    				}
	    				
	    				
	    				
	    			}
	    		});

		uploadPanel.add(discoverButton,BorderLayout.WEST);
		uploadPanel.add(uploadButtonPanel,BorderLayout.CENTER);
		
		JButton loginPageButton = new JButton("Login Page");
		loginPageButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
//logging info
				info.log(String.format("%s successfully logged out",nickname));

		        dispose();
		        new LoginPage();
		    }
		});
		JPanel loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		loginButtonPanel.add(loginPageButton);
		uploadPanel.add(loginButtonPanel, BorderLayout.EAST);

		
		leftPanel.add(uploadPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	/**
	 * 
	 * @param panel
	 * @param nickname
	 * this method reads file and gets profilImage of user (nickname), and converts it to JLabel, and adds panel which is given as parameter. Also, this method adds MouseListener to JLabel so that user can change his/her profil Image
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

	                profileImageLabel.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                        JFileChooser fileChooser = new JFileChooser("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilePictures");
	                        int result = fileChooser.showOpenDialog(null);
	                        if (result == JFileChooser.APPROVE_OPTION) {
	                            File selectedFile = fileChooser.getSelectedFile();
	                            String newImagePath = selectedFile.getAbsolutePath();

	                            updateProfilePicturePath(nickname, newImagePath);
//logging out
	        	        		info.log(String.format("%s successfully updated his/her Profile Picture",nickname));
	                            
	                            try {
	                                Image newProfileImage = ImageIO.read(selectedFile).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	                                profileImageLabel.setIcon(new ImageIcon(newProfileImage));
	                            } catch (IOException ex) {
	                                ex.printStackTrace();
	                            }
	                        }
	                    }
	                });


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
	 * @param nickname
	 * @param newImagePath
	 * this method is used for when user change his/her profil image, this method updates path of new image in specified text file.
	 */
	public void updateProfilePicturePath(String nickname, String newImagePath) {
	    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/ProfilePictures.txt";
	    Path pathToFile = Paths.get(filePath);

	    try {
	        // Read all lines into a list
	        List<String> lines = Files.readAllLines(pathToFile);
	        List<String> modifiedLines = new ArrayList<String>();

	        for (String line : lines) {
	            String[] parts = line.split(" ");
	            String fileNickname = parts[0];
	            String imagePath = parts[1];

	            if (fileNickname.equals(nickname)) {
	                modifiedLines.add(fileNickname + " " + newImagePath);
	            } else {
	                modifiedLines.add(line);
	            }
	        }

	       
	        Files.write(pathToFile, modifiedLines);
	     
	    } catch (IOException ex) {
	        ex.printStackTrace();
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
	 * this method takes arguments and goes to specified text file in order to get paths of photos of specified user (nickname), and add this to map as scaled image and its path
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

				
				if (parts[0].equals(nickname)) {

					BufferedImage myPicture = ImageIO.read(new File(parts[2]));
					Image scaledImage = myPicture.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
					if (imageMap.containsKey(scaledImage)==false) {
						imageMap.put(scaledImage, false);
						imagePathMap.put(scaledImage, parts[2]);
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
	 * @param path
	 * @return only name of file
	 * this method gets path, and returns only name of image from path. Because, path can contains name of packages and classes.
	 */ 
	public static String getOnlyFileNameWithoutExtension(String path) {
		String[] parts = path.split("/");

		
		String filenameWithExtension = parts[parts.length - 1];

		
		String[] filenameParts = filenameWithExtension.split("\\.");

		
		String filenameWithoutExtension = filenameParts[0];
		return filenameWithoutExtension;
	}
	/**
	 * 
	 * @param originalString
	 * @param newFilename
	 * @return new name for image after filtering
	 * this method is used when we do filtering. It takes path of original image and add type of filterin method to the end of path
	 */
	public static String replaceImagenameInPath(String originalString, String newFilename) {
	    
	    String[] parts = originalString.split("/");

	   
	    String filenameWithExtension = parts[parts.length - 1];

	  
	    String[] filenameParts = filenameWithExtension.split("\\.");

	    
	    String newString = originalString.replace(filenameParts[0], newFilename);

	    return newString;
	}
	/**
	 * 
	 * @param panel
	 * this method basically gets all images from map, make them JLabel, and add them to the specified panel. Additionally, this method add deleting and filtering methods for each JLabel
	 */
	public static void refresh(JPanel panel) {
		panel.removeAll();

		for (Image image : imageMap.keySet()) {
			if (imageMap.get(image) == false) {
				JLabel picLabel = new JLabel(new ImageIcon(image));
				picLabel.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						String[] confirmation = { "Delete", "Filter" ,"Make Public","Make Private"};

						int choice = JOptionPane.showOptionDialog(null, "Do you want to Delete, Filter, or make the photo Public/Private?",
								"Photo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, QuestionIcon.question(), confirmation,
								null);
						super.mouseEntered(e);

//DOING FILTERING						
						String PathName = imagePathMap.get(image);
						
						//FILTERIN METHODS
						
						if (choice == 1) {
							if (type.equals("Professional") || type.equals("Administrator")) {
								String[] filters = { "Blurring", "Sharpening", "Brightness", "Contrast", "Grayscale",
										"Edge Detection" };
								int filter = JOptionPane.showOptionDialog(null, "Choose a filter for the photo",
										"Photo Filter", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, QuestionIcon.question(),
										filters, null);
								
								User user= new ProfessionalUser(nickname,password,name,surname,age,email,type);
								switch (filter) {
								case 0:
//logging info
									info.log(String.format("%s used blurring method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));

									applyingFilter(PathName, photoPanel, "Blur", user);
									break;
								case 1:
//logging info
									info.log(String.format("%s used sharpening method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));									
									applyingFilter(PathName, photoPanel, "Sharpen", user);	
									break;
								case 2:
//logging info
									info.log(String.format("%s used brightness method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));									
									applyingFilter(PathName, photoPanel, "Brightness", user);	
									break;
								case 3:
//logging info
									info.log(String.format("%s used contrast method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Contrast", user);
									break;
								case 4:
//logging info
									info.log(String.format("%s used Grayscale method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Grayscale", user);
									break;
								case 5:
//logging info
									info.log(String.format("%s used EdgeDetection method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "EdgeDetection", user);
									break;

								}
							}
							else if (type.equals("Hobbyist")) {
								User user= new HobbyistUser(nickname,password,name,surname,age,email,type);
								String[] filters = { "Blurring", "Sharpening", "Brightness", "Contrast"};
								int filter = JOptionPane.showOptionDialog(null, "Choose a filter for the photo",
										"Photo Filter", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, QuestionIcon.question(),
										filters, null);
								

								switch (filter) {
								case 0:
//logging info
									info.log(String.format("%s used Blur method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));									
									applyingFilter(PathName, photoPanel, "Blur", user);
									break;
								case 1:
//logging info
									info.log(String.format("%s used Sharpening method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Sharpen", user);
									break;
								case 2:
//logging info
									info.log(String.format("%s used Brightness method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Brightness", user);	
									break;
								case 3:
//logging info
									info.log(String.format("%s used Contrast method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Contrast", user);
									break;
								}
							}
				
							else if (type.equals("Free")) {
								User user= new FreeUser(nickname,password,name,surname,age,email,type);

								String[] filters = { "Blurring", "Sharpening"};
								int filter = JOptionPane.showOptionDialog(null, "Choose a filter for the photo",
										"Photo Filter", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, QuestionIcon.question(),
										filters, null);
								

								switch (filter) {
								case 0:
//logging info
									info.log(String.format("%s used Blur method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Blur", user);
									break;
								case 1:
//logging info
									info.log(String.format("%s used Sharpening method for %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
									applyingFilter(PathName, photoPanel, "Sharpen", user);
									break;

								}
							}
							
							
							
						}

						else if (choice == 0) {
							panel.remove(picLabel); 
							imageMap.remove(image); 
							info.log(String.format("%s deleted %s image",nickname,getOnlyFileNameWithoutExtension(PathName)));
							
							try {
								String imagePath = imagePathMap.get(image);
								
								removeImagePathFromTextFile(nickname, imagePath);
								deleteLineFromLikesDislikesFile(nickname, imagePath);
								deleteLineFromWhoLikedWhomFile(nickname,imagePath);
								
						        imagePathMap.remove(image); 

							} catch (FileNotFoundException fileNotFoundException) {
								System.out.println("Error reading the file.");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							panel.revalidate();
						    panel.repaint();

						}
						
						else if (choice==2) {
//logging info
							info.log(String.format("%s made %s image Public",nickname,getOnlyFileNameWithoutExtension(PathName)));
							changeToPublic(nickname,PathName);
							
							
						}
						else if(choice==3) {
//logging info
							info.log(String.format("%s made %s image Private",nickname,getOnlyFileNameWithoutExtension(PathName)));
							changeToPrivate(nickname,PathName);
						}

					}

				});

				panel.add(picLabel);
			}

		}

		panel.revalidate();
		panel.repaint();
	}
	/**
	 * 
	 * @param nickname
	 * @param imagePath
	 * @throws IOException
	 * this method is used when we delete image from panel. If we delete an image, we also have to delete it from text file, which this method does.
	 * This method created temp file and add information to this file. At the end, this method deletes originial text, and replace temp file name with original one.
	 */
	public static void removeImagePathFromTextFile(String nickname, String imagePath) throws IOException {
	    File file = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt");

	    File tempFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers_temp.txt");
	    FileWriter writer = new FileWriter(tempFile);

	    Scanner scanner = new Scanner(file);

	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        if (line.isEmpty()==false) {
	        if (!line.contains(nickname) || !line.contains(imagePath)) {
	            writer.write(line + System.lineSeparator()); 
	        }
	    }
	    }

	    scanner.close();
	    writer.close();

	    
	    if (!file.delete() || !tempFile.renameTo(file)) {
	        throw new IOException("Failed to update the file.");
	    }
	}
    
	/**
	 * 
	 * @param pathName
	 * @param panel
	 * @param filterType
	 * @param user
	 * this method takes arguments as filterType, and it creates new JOptionPane for given filterinType. This method gets filtered matrix, change it to image, add it to map, and finally refreshing photopanel
	 */
	public static void applyingFilter(String pathName, JPanel panel, String filterType, User user) {
	    try {
	        JSlider intensitySlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 3);
	        intensitySlider.setMajorTickSpacing(1);
	        intensitySlider.setPaintTicks(true);
	        intensitySlider.setPaintLabels(true);
	        intensitySlider.setPreferredSize(new Dimension(400, 200));

	        JPanel sliderPanel = new JPanel();
	        sliderPanel.setLayout(new BorderLayout());
	        sliderPanel.add(new JLabel("Select " + filterType + " Intensity:"), BorderLayout.NORTH);
	        sliderPanel.add(intensitySlider, BorderLayout.CENTER);

	        int option = JOptionPane.showOptionDialog(null, sliderPanel, filterType + " Intensity",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

	        if (option == JOptionPane.OK_OPTION) {
	            int filterIntensity = intensitySlider.getValue();
	            if (filterIntensity % 2 == 0) {
	                filterIntensity += 1;
	            }

	            String newFilename = getOnlyFileNameWithoutExtension(pathName) + filterType;

	            ImageMatrix matrix = ImageSecretary.readResourceImage(pathName);
	            ImageMatrix filteredMatrix = null;

	            if (filterType.equals("Blur")) {
	                filteredMatrix = User.blurring(matrix, filterIntensity);
	            } else if (filterType.equals("Sharpen")) {
	                filteredMatrix = User.sharpening(matrix, filterIntensity);
	            } else if (filterType.equals("Brightness")) {
	                filteredMatrix = User.brightness(matrix, filterIntensity);
	            } else if (filterType.equals("Contrast")) {
	                filteredMatrix = User.contrast(matrix, filterIntensity);
	            } else if (filterType.equals("Grayscale")) {
	                filteredMatrix = User.Grayscale(matrix,filterIntensity);
	            } else if (filterType.equals("EdgeDetection")) {
	                filteredMatrix = User.edge_detection(matrix,filterIntensity);
	            }
	            ImageSecretary.writeFilteredImageToResources(filteredMatrix, newFilename, "jpeg");

	            FileWriter writer = new FileWriter("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt", true);

	            File selectedFile = new File(replaceImagenameInPath(pathName, newFilename));

	            try {
	                Image filteredPicture = ImageIO.read(selectedFile).getScaledInstance(250, 250,
	                        Image.SCALE_SMOOTH);
	                imageMap.put(filteredPicture, false);
	                imagePathMap.put(filteredPicture, selectedFile.getAbsolutePath());
	                refresh(panel);
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }

	            writer.write(nickname + " " + "private" + " "
	                    + replaceImagenameInPath(pathName,
	                            newFilename)
	                    + "\n"); 

	            writer.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * 
	 * @param nickname
	 * @param path
	 * this method is used when user change the access of photo to public. If user does, we also have to change it in text file
	 */
    public static void changeToPublic(String nickname, String path) {
	        String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt";
	        Path pathToFile = Paths.get(filePath);
	        
	        try {
	            
	            List<String> lines = Files.readAllLines(pathToFile);
	            List<String> modifiedLines = new ArrayList<String>();

	            for(String line : lines) {
	                String[] parts = line.split(" ");
	                if(parts[0].equals(nickname) && parts[2].equals(path)) {
	                    parts[1] = "public";
	                    modifiedLines.add(String.join(" ", parts));
	                } else {
	                    modifiedLines.add(line);
	                }
	            }

	            
	            Files.write(pathToFile, modifiedLines);
	            System.out.println("File updated successfully");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	/**
	 * 
	 * @param nickname
	 * @param path
	 * this method is used when user change the access of photo to private. If user does, we also have to change it in text file
	 */
    public static void changeToPrivate(String nickname, String path) {
        String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt";
        Path pathToFile = Paths.get(filePath);

        try {
            // Read all lines into a list
            List<String> lines = Files.readAllLines(pathToFile);
            List<String> modifiedLines = new ArrayList<String>();

            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts[0].equals(nickname) && parts[2].equals(path)) {
                    parts[1] = "private";
                    modifiedLines.add(String.join(" ", parts));
                } else {
                    modifiedLines.add(line);
                }
            }

           
            Files.write(pathToFile, modifiedLines);
            System.out.println("File updated successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 
     * @param nickname
     * @param imagePath
     * when we delete an image, we also have to delete it from LikeOrDislike text file, which this method does. 
     */
    public static void deleteLineFromLikesDislikesFile(String nickname, String imagePath) {
        String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/LikeOrDislike.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] parts= line.split(" ");
                if ((parts[0].equals(nickname))==false && (parts[1].equals(imagePath))==false) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param nickname
     * @param imagePath
     * when we delete an image, we also have to delete it from WhoLikedWhom text file, which this method does. 
     */
    public static void deleteLineFromWhoLikedWhomFile(String nickname, String imagePath) {
        String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] parts= line.split(" ");
                if ((parts[0].equals(nickname))==false && (parts[1].equals(imagePath))==false) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	}