package DiscoverPage;

import javax.imageio.ImageIO;
import javax.swing.*;

import BaseLogger.error;
import BaseLogger.info;
import ProfilPage.NormalProfilePageWithHierarchies;
import ProfilPage.ProfilePagePublicly;
import SignUpPage.SignUpMethods;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscoverPageForAdmin extends JFrame {
    private JPanel Panel;
    private String nickname;
    private String password;
    private String name;
    private String surname;
    private String age;
    private String email;
    private String type;
    private JButton profilePageButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private JFrame frame;
    private JTextArea commentTextArea;
    private JPanel commentsPanel;

    /**
     * 
     * @param nickname
     * @param password
     * @param name
     * @param surname
     * @param age
     * @param email
     * @param type
     * this constructor creates DiscoverPage for User who logged in the app
     */
    public DiscoverPageForAdmin(String nickname, String password, String name, String surname, String age, String email, String type) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.type = type;
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        profilePageButton = new JButton("Profile Page");
        profilePageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//logging info
				info.log(String.format("%s moved to Profile Page",DiscoverPageForAdmin.this.nickname));
            	NormalProfilePageWithHierarchies frame = new NormalProfilePageWithHierarchies(DiscoverPageForAdmin.this.nickname, DiscoverPageForAdmin.this.password, DiscoverPageForAdmin.this.name, DiscoverPageForAdmin.this.surname, DiscoverPageForAdmin.this.age, DiscoverPageForAdmin.this.email, DiscoverPageForAdmin.this.type);
                dispose();
            }
        });

        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        westPanel.add(profilePageButton);
        northPanel.add(westPanel, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchTextField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchNickname = searchTextField.getText();

                if (SignUpMethods.existenceOfNickname(searchNickname)) {
                	ArrayList<String> user = SignUpMethods.gettingAllInformationOfUser(searchNickname);
                    if (user.get(0).equals(DiscoverPageForAdmin.this.nickname)) {
                        new NormalProfilePageWithHierarchies(user.get(0), user.get(1), user.get(2), user.get(3), user.get(4), user.get(5), user.get(6));
                        dispose();
                    } else {
                        new ProfilePagePublicly(user.get(0), user.get(1), user.get(2), user.get(3), user.get(4), user.get(5), user.get(6), DiscoverPageForAdmin.this.nickname);
                        dispose();
                    }
                } 
                
                
                else {
                    JOptionPane.showMessageDialog(DiscoverPageForAdmin.this, "This username does not exist.", "Username Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        northPanel.add(searchPanel, BorderLayout.CENTER);

        getContentPane().add(northPanel, BorderLayout.NORTH);

        Panel = new JPanel();
        Panel.setLayout(new GridLayout(0, 2));
        Panel.setBackground(Color.orange);

        JScrollPane scrollPane = new JScrollPane(Panel);
        scrollPane.getViewport().setBackground(Color.orange);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        populatePage();
        setSize(900, 900);
        setVisible(true);
    }

    /**
     * this method gets information of photos from text file and call addImage method
     */
    private void populatePage() {
        Panel.removeAll(); 

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilPage/PhotosOfUsers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);

                String[] parts = line.split(" ");
                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String nickname = parts[0];
                String access = parts[1];
                String path = parts[2];

                if ("public".equals(access)) {
                    addImage(nickname, path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Panel.revalidate();
        Panel.repaint();
    }

    
    /**
     * 
     * @param nickname
     * @param path
     * this method create nameLabel,imageLabel,like, dislike, no reaction button, and delete button for each image in panel and add this panel to main pane;
     */
    public void addImage(String nickname, String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image scaledImage = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
            panel.setBackground(Color.orange);

            
            JLabel nameLabel = new JLabel("@" + nickname);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  
            nameLabel.setAlignmentY(Component.CENTER_ALIGNMENT); 
            nameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ArrayList<String> user = SignUpMethods.gettingAllInformationOfUser(nickname);
                    if (user.get(0).equals(DiscoverPageForAdmin.this.nickname)) {
//logging info
                		info.log(String.format("%s moved to his/her Profile Page",user.get(0)));
                        new NormalProfilePageWithHierarchies(user.get(0), user.get(1), user.get(2), user.get(3), user.get(4), user.get(5), user.get(6));
                        dispose();
                    } else {
//logging info
                		info.log(String.format("%s moved to look at %s'Profile Page",DiscoverPageForAdmin.this.nickname,user.get(0)));
                        new ProfilePagePublicly(user.get(0), user.get(1), user.get(2), user.get(3), user.get(4), user.get(5), user.get(6), DiscoverPageForAdmin.this.nickname);
                        dispose();
                    }
                }
            });

            
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(icon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);  
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    enlargeImage(nickname, path);
                }
            });

            int[] counts;
            try {
                counts = getLikesAndDislikes(path,nickname);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            JButton likeButton = new JButton("Like " + counts[0]);
            JButton dislikeButton = new JButton("Dislike " + counts[1]);
            
            
            
			likeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						

						if (hasUserLikedImage(nickname,path,DiscoverPageForAdmin.this.nickname)==false && hasUserDislikedImage(nickname,path,DiscoverPageForAdmin.this.nickname)==false) {

							int currentLikes = Integer.parseInt(likeButton.getText().split(" ")[1]);
							int currentDislikes = Integer.parseInt(dislikeButton.getText().split(" ")[1]);
							currentLikes += 1;
//logging info
							info.log(String.format("%s liked %s's photo ",DiscoverPageForAdmin.this.nickname,nickname));
							likeButton.setText("Like " + currentLikes);
							dislikeButton.setText("Dislike " + currentDislikes);
	
							handleLikesAndDislikes("like", nickname, path, currentLikes, currentDislikes);
							handleLikesAndDislikes("dislike", nickname, path, currentLikes, currentDislikes);

							
							
							writeLikedInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
							deleteDislikeInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
	
						
						}
						
						else if (hasUserLikedImage(nickname,path,DiscoverPageForAdmin.this.nickname)==false) {

							int currentLikes = Integer.parseInt(likeButton.getText().split(" ")[1]);
							int currentDislikes = Integer.parseInt(dislikeButton.getText().split(" ")[1]);
							currentLikes += 1;
							currentDislikes-=1;
//logging info
							info.log(String.format("%s liked %s's photo ",DiscoverPageForAdmin.this.nickname,nickname));
							likeButton.setText("Like " + currentLikes);
							dislikeButton.setText("Dislike " + currentDislikes);
	
							handleLikesAndDislikes("like", nickname, path, currentLikes, currentDislikes);
							handleLikesAndDislikes("dislike", nickname, path, currentLikes, currentDislikes);

							
							writeLikedInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
							deleteDislikeInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
	
						
						}
						else {
//logging error
	                		error.log(String.format("%s again liked %s's photo twice ",DiscoverPageForAdmin.this.nickname,nickname));
							JOptionPane.showMessageDialog(null, "You already liked this image.", "Already Liked", JOptionPane.INFORMATION_MESSAGE);
						}


					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			});

            dislikeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    	if (hasUserDislikedImage(nickname,path,DiscoverPageForAdmin.this.nickname)==false && hasUserLikedImage(nickname,path,DiscoverPageForAdmin.this.nickname)==false) {
	                    	int currentLikes = Integer.parseInt(likeButton.getText().split(" ")[1]);
							int currentDislikes = Integer.parseInt(dislikeButton.getText().split(" ")[1]);
	                        currentDislikes+=1;

//logging info	                        
	                        info.log(String.format("%s disliked %s's photo ",DiscoverPageForAdmin.this.nickname,nickname));
	                        dislikeButton.setText("Dislike " + currentDislikes);
	                        likeButton.setText("Like "+currentLikes );
	                        
	                        handleLikesAndDislikes("dislike",nickname, path, currentLikes, currentDislikes);
							handleLikesAndDislikes("like", nickname, path, currentLikes, currentDislikes);

	                        
	                        writeDislikedInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
	                        deleteLikeInfo(nickname, path,DiscoverPageForAdmin.this.nickname);
                    	}
                    	else if (hasUserDislikedImage(nickname,path,DiscoverPageForAdmin.this.nickname)==false) {
	                    	int currentLikes = Integer.parseInt(likeButton.getText().split(" ")[1]);
							int currentDislikes = Integer.parseInt(dislikeButton.getText().split(" ")[1]);
	                        currentDislikes+=1;
                        	currentLikes-=1;
	                        
	
	                        dislikeButton.setText("Dislike " + currentDislikes);
	                        likeButton.setText("Like "+currentLikes );
//logging info
	                		info.log(String.format("%s disliked %s's photo ",DiscoverPageForAdmin.this.nickname,nickname));
	                        handleLikesAndDislikes("dislike",nickname, path, currentLikes, currentDislikes);
							handleLikesAndDislikes("like", nickname, path, currentLikes, currentDislikes);

	                        
	                        writeDislikedInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
	                        deleteLikeInfo(nickname, path,DiscoverPageForAdmin.this.nickname);
                    	}
                    	else {
//logging error
	                		error.log(String.format("%s again disliked %s's photo twice ",DiscoverPageForAdmin.this.nickname,nickname));
	                        JOptionPane.showMessageDialog(null, "You already Disliked this image.", "Already Disliked", JOptionPane.INFORMATION_MESSAGE);
                    		
                    	}
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            JButton noReaction= new JButton("No reaction");
            noReaction.addActionListener(
            		new ActionListener() {
            			public void actionPerformed(ActionEvent a) {
            				
            				if (hasUserDislikedImage(nickname, path, DiscoverPageForAdmin.this.nickname)==true) {
            					int dislikeNumber=Integer.parseInt(dislikeButton.getText().split(" ")[1]);
            					int likeNumber=Integer.parseInt(likeButton.getText().split(" ")[1]);
            					int newDislikeNumber=dislikeNumber-1;
            					dislikeButton.setText("Dislike "+newDislikeNumber);
//logging info
    	                		info.log(String.format("%s removed his/her dislike from %s's photo ",DiscoverPageForAdmin.this.nickname,nickname));
            					try {

									handleLikesAndDislikes("dislike", nickname, path, likeNumber, newDislikeNumber);
									deleteDislikeInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
            				}
            				else if (hasUserLikedImage(nickname, path, DiscoverPageForAdmin.this.nickname)==true) {
            					
            					int dislikeNumber=Integer.parseInt(dislikeButton.getText().split(" ")[1]);
            					int likeNumber=Integer.parseInt(likeButton.getText().split(" ")[1]);
            					int newLikeNumber=likeNumber-1;
            					likeButton.setText("Like "+newLikeNumber);
//logging info
    	                		info.log(String.format("%s removed his/her like from %s's photo ",DiscoverPageForAdmin.this.nickname,nickname));
            					try {
									handleLikesAndDislikes("like", nickname, path, newLikeNumber, dislikeNumber);
									deleteLikeInfo(nickname, path, DiscoverPageForAdmin.this.nickname);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
            				}
            				
            			}
            		});
            JButton deleteButton=new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    	NormalProfilePageWithHierarchies.removeImagePathFromTextFile(nickname, path);
                    	NormalProfilePageWithHierarchies.deleteLineFromLikesDislikesFile(nickname,path);
                    	NormalProfilePageWithHierarchies.deleteLineFromWhoLikedWhomFile(nickname, path);
                        Panel.remove(panel);
                        Panel.revalidate();
                        Panel.repaint();
//logging info
		                String nameOfDeletingImage=NormalProfilePageWithHierarchies.getOnlyFileNameWithoutExtension(path);
                		info.log(String.format("%s deleted %s's %s image from Discover Page ",DiscoverPageForAdmin.this.nickname,nickname,nameOfDeletingImage));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            
            String filenameWithoutExtension = NormalProfilePageWithHierarchies.getOnlyFileNameWithoutExtension(path);
            JLabel filenameLabel = new JLabel(filenameWithoutExtension);
            filenameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
            filenameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);  

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.DARK_GRAY);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.add(deleteButton);
            buttonPanel.add(likeButton);
            buttonPanel.add(dislikeButton);
            buttonPanel.add(noReaction);
            buttonPanel.add(Box.createHorizontalGlue());  
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);  
            buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            panel.add(Box.createVerticalGlue()); 
            panel.add(nameLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));  
            panel.add(imageLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));  
            panel.add(filenameLabel);  
            panel.add(Box.createRigidArea(new Dimension(0, 5)));  
            panel.add(buttonPanel);
            panel.add(Box.createVerticalGlue());  

            
            Panel.add(panel);
            pack(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param type
     * @param nickname
     * @param path
     * @param currentLikes
     * @param currentDislikes
     * @throws IOException
     * this method takes mentioned arguments and modify LikeOrDislike.txt file for specific information given as parameter.
     * We call this method when number of likes/dislikes changes.
     */
    private void handleLikesAndDislikes(String type, String nickname, String path, int currentLikes, int currentDislikes) throws IOException {
        File inputFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/LikeOrDislike.txt");
        File tempFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/LikeOrDislikeTemp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean lineModified = false;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4 && parts[0].equals(nickname) && parts[1].equals(path)) {
                
                int existingLikes = Integer.parseInt(parts[2]);
                int existingDislikes = Integer.parseInt(parts[3]);

                if (type.equals("like")) {
                    existingLikes = currentLikes;
                } else if (type.equals("dislike")) {
                    existingDislikes = currentDislikes;
                }

                line = parts[0] + " " + parts[1] + " " + existingLikes + " " + existingDislikes;
                lineModified = true;
            }

            writer.write(line + System.lineSeparator());
        }

        reader.close();
        writer.close();

        if (!lineModified) {
            
            FileWriter appendWriter = new FileWriter(inputFile, true);
            if (type.equals("like")) {
                appendWriter.write(nickname + " " + path + " " + currentLikes + " " + currentDislikes + System.lineSeparator());
            } else if (type.equals("dislike")) {
                appendWriter.write(nickname + " " + path + " " + currentLikes + " " + currentDislikes + System.lineSeparator());
            }
            appendWriter.close();
        } else {
   
            if (!inputFile.delete()) {
                throw new IOException("Failed to delete the original file");
            }
            if (!tempFile.renameTo(inputFile)) {
                throw new IOException("Failed to rename the temp file");
            }
        }
    }



    /**
     * 
     * @param path
     * @param nickname
     * @return Array
     * @throws IOException
     * this method return array of likes and dislikes for given path of image and nickname in LikeOrDislike.txt file
     */
    private int[] getLikesAndDislikes(String path, String nickname) throws IOException {
        File inputFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/LikeOrDislike.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;

        int likes = 0;
        int dislikes = 0;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4 && parts[1].equals(path) && parts[0].equals(nickname)) {
                int currentLikes = Integer.parseInt(parts[2]);
                int currentDislikes = Integer.parseInt(parts[3]);
                likes += currentLikes;
                dislikes += currentDislikes;
            }
        }

        reader.close();

        return new int[]{likes, dislikes};
    }




    /**
     * 
     * @param nickString
     * @param path
     * this method is used when user clicks on image ,and new frame opens displaying comments and enlarged version of image
     */
    public void enlargeImage(String nickString, String path) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
		ImageIcon icon = new ImageIcon(path);
		Image scaledImage = icon.getImage().getScaledInstance(650, 650, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel imageLabel = new JLabel(scaledIcon);
		leftPanel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);


		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setPreferredSize(new Dimension(300, 650));
		
		
		
		
	    
	    String profilePicturePath;
	    try {
	        profilePicturePath = getProfilePicturePath(nickString);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	 
	    ImageIcon profilePictureIcon = new ImageIcon(profilePicturePath);
	    Image scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    ImageIcon scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);

	  
	    JLabel profilePictureLabel = new JLabel(scaledProfilePictureIcon);
	    
		
		
	    
	    


		JLabel nicknameLabel = new JLabel("Nickname: " + nickString);
		nicknameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		Font boldFont1 = new Font(nicknameLabel.getFont().getName(), Font.BOLD, nicknameLabel.getFont().getSize());
		nicknameLabel.setFont(boldFont1);


		String commentsFilePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/Comments.txt";
		List<String> comments = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(commentsFilePath))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(";");

		        if (parts.length >= 5 && parts[1].equals(path) && parts[0].equals(nickString)) {
		            String commentTime = parts[4];
		            String commentText = parts[3];
		            comments.add("[" + commentTime + "] @" + parts[2] + ": " + commentText);
		        }
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

		
		commentsPanel = new JPanel();
		commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
		for (String comment : comments) {
		    JLabel commentLabel = new JLabel(comment);
		    commentsPanel.add(commentLabel);
		}
		JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
		rightPanel.add(commentsScrollPane); 
		

	
		commentTextArea = new JTextArea(5, 10);
		JScrollPane commentScrollPane = new JScrollPane(commentTextArea);

		
		JButton commentButton = new JButton("Add Comment");
		commentButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String comment = commentTextArea.getText();
		        if (!comment.isEmpty()) {
		            String commentLine =nickString+";" +path + ";" + nickname + ";" + comment + ";" + getCurrentTime();
		            try (BufferedWriter writer = new BufferedWriter(new FileWriter(commentsFilePath, true))) {
		                writer.write(commentLine);
		                writer.newLine();
		                writer.flush();

		                JLabel commentLabel = new JLabel("[" + getCurrentTime() + "] @" + nickname + ": " + comment);
//logging info
		                String nameOfImage=NormalProfilePageWithHierarchies.getOnlyFileNameWithoutExtension(path);
                		info.log(String.format("%s commented on %s's %s image ",DiscoverPageForAdmin.this.nickname,nickString,nameOfImage));
		                commentsPanel.add(commentLabel);
		                commentsPanel.revalidate();
		                commentsPanel.repaint();

		                commentTextArea.setText(""); 
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});

	
		rightPanel.add(profilePictureLabel);
		rightPanel.add(nicknameLabel);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
		rightPanel.add(commentScrollPane);
		rightPanel.add(commentButton);

		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(leftPanel, BorderLayout.CENTER);
		mainPanel.add(rightPanel, BorderLayout.EAST);

		frame.add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }


    /**
     * 
     * @return time
     * this method returns current time
     */
    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }

    /**
     * 
     * @param nickname
     * @return path of image
     * @throws IOException
     * this method takes nickname and return path of profile image of this nickname in ProfilePictures.txt file
     */
private String getProfilePicturePath(String nickname) throws IOException {
    String profilePicturesFilePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/ProfilePictures.txt";
    List<String> profilePictures = Files.readAllLines(Paths.get(profilePicturesFilePath));
    for (String profilePicture : profilePictures) {
        String[] parts = profilePicture.split(" ");
        if (parts.length == 2 && parts[0].equals(nickname)) {
            return parts[1];
        }
    }
    return null; 
}

/**
 * 
 * @param userPosted
 * @param imagePath
 * @param userLiked
 * This method writes to WhoLikedWhom text file indicating that which user liked which user's which photo
 */
public static void writeLikedInfo(String userPosted, String imagePath, String userLiked) {
    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        writer.write(userPosted + " " + imagePath + " "+ userLiked +" Liked\n");
        System.out.println("Information written to file successfully!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * 
 * @param userPosted
 * @param imagePath
 * @param userLiked
 * This method writes to WhoLikedWhom text file indicating that which user liked which user's which photo
 */
public static void writeDislikedInfo(String userPosted, String imagePath, String userLiked) {
    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        writer.write(userPosted + " " + imagePath + " "+ userLiked +" Disliked\n");
        System.out.println("Information written to file successfully!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
	 * 
	 * @param photoPoster
	 * @param imagePath
	 * @param photoLiker
	 * @return boolean
	 * this method checks if user liked an image or not. 
	 */
public static boolean hasUserLikedImage(String photoPoster, String imagePath, String photoLiker) {
    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4 && parts[0].equals(photoPoster) && parts[1].equals(imagePath) && parts[2].equals(photoLiker) && parts[3].equals("Liked")) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return false;
}

/**
 * 
 * @param photoPoster
 * @param imagePath
 * @param photoLiker
 * @return boolean
 * this method checks if user disliked an image or not. 
 */
public static boolean hasUserDislikedImage(String photoPoster, String imagePath, String photoLiker) {
    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4 && parts[0].equals(photoPoster) && parts[1].equals(imagePath) && parts[2].equals(photoLiker) && parts[3].equals("Disliked")) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return false;
}

/**
 * 
 * @param photoPoster
 * @param imagePath
 * @param photoLiker
 * this method deletes the information from text file by  deleting line where photoLiker disliked image of photoposter
 */
public static void deleteDislikeInfo(String photoPoster, String imagePath, String photoLiker) {
    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";
    File tempFile = new File(filePath + ".tmp");

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4 && parts[0].equals(photoPoster) && parts[1].equals(imagePath) && parts[2].equals(photoLiker) && parts[3].equals("Disliked")) {
                continue; 
            }
            writer.write(line + "\n");
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

  
    if (tempFile.renameTo(new File(filePath))) {
        System.out.println("Information deleted from file successfully!");
    } else {
        System.out.println("Failed to delete information from file.");
    }
}


/**
 * 
 * @param photoPoster
 * @param imagePath
 * @param photoLiker
 * this method deletes the information from text file by  deleting line where photoLiker liked image of photoposter
 */
public static void deleteLikeInfo(String photoPoster, String imagePath, String photoLiker) {
    String filePath = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/DiscoverPage/WhoLikedWhom.txt";
    File tempFile = new File(filePath + ".tmp");

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4 && parts[0].equals(photoPoster) && parts[1].equals(imagePath) && parts[2].equals(photoLiker) && parts[3].equals("Liked")) {
                continue; 
            }
            writer.write(line + "\n");
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

   
    if (tempFile.renameTo(new File(filePath))) {
        System.out.println("Information deleted from file successfully!");
    } else {
        System.out.println("Failed to delete information from file.");
    }
}




}
