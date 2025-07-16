
package SignUpPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BaseLogger.error;
import BaseLogger.info;
import LoginPage.LoginPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField ageField;
	private JTextField emailField;
	private JButton signUpButton;
	
	private File selectedFile=null;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this method is used for putting JLabels and JTextFields where user can input their information in order to sign up.
	 */
	public SignUp() {
		getContentPane().setLayout(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(65, 65, 90, 30);
		contentPane.add(lblNickname);

		nicknameField = new JTextField();
		nicknameField.setBounds(150, 67, 200, 25);
		contentPane.add(nicknameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(65, 101, 100, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 100, 200, 25);
		contentPane.add(passwordField);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(65, 138, 50, 25);
		contentPane.add(lblName);

		nameField = new JTextField();
		nameField.setBounds(150, 135, 200, 25);
		contentPane.add(nameField);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(65, 171, 70, 25);
		contentPane.add(lblSurname);

		surnameField = new JTextField();
		surnameField.setBounds(150, 170, 200, 25);
		contentPane.add(surnameField);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(65, 206, 30, 25);
		contentPane.add(lblAge);

		ageField = new JTextField();
		ageField.setBounds(150, 205, 200, 25);
		contentPane.add(ageField);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(65, 241, 40, 25);
		contentPane.add(lblEmail);

		emailField = new JTextField();
		emailField.setBounds(150, 240, 200, 25);
		contentPane.add(emailField);

		signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(379, 358, 100, 25);
		contentPane.add(signUpButton);
		
		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setBounds(65, 278, 61, 16);
		contentPane.add(lblNewLabel);
		
		
		
		String[] types= {"Free User","Hobbyist User","Professional User","Administrator"};
		JComboBox comboBox = new JComboBox(types);
		comboBox.setBounds(150, 277, 200, 27);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.setBounds(150, 316, 117, 29);
		
		//UPLOADING Pofile Picture
		//
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent a) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setCurrentDirectory(new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilePictures"));
		        int returnValue = fileChooser.showOpenDialog(null); 

		        
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            selectedFile = fileChooser.getSelectedFile();
		        }
		    }
		});

		contentPane.add(btnNewButton);
		
		
		JLabel lblNewLabel_1 = new JLabel("Profile Image");
		lblNewLabel_1.setBounds(50, 321, 105, 16);
		contentPane.add(lblNewLabel_1);

		signUpButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nickname = nicknameField.getText();
		        String password = new String(passwordField.getPassword());
		        String name = nameField.getText();
		        String surname = surnameField.getText();
		        String age = ageField.getText();
		        int ageInteger=-1;
		        
		        String email = emailField.getText();
		        String type=comboBox.getSelectedItem().toString();
		        
		        if (selectedFile == null) {
		            
		            selectedFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/ProfilePictures/DefaultPicture.jpeg");
		        }

		        boolean check = true;

		        if (nickname.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || age.isEmpty()
		                || email.isEmpty()) {
		            check = false;
		            JOptionPane.showMessageDialog(null, "Please fill in all the boxes");
// logging	error	            
		            error.log(String.format("User did not filled all the boxes"));
		        } else if (password.length() < 6) {
		            check = false;
// logging error         
		            error.log(String.format("User chose password whose length is less than 6"));
		            JOptionPane.showMessageDialog(null, "Password should be at least 6 characters long.",
		                    "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        
		        else if (!isValidEmail(email)) {
		            check = false;
// loggin error
		            error.log(String.format("User entered invalid email format"));
		            JOptionPane.showMessageDialog(null, "Invalid email format!", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            emailField.setText("");
		        }
		        else {
		            try {
		                ageInteger = Integer.parseInt(age);
		                if (ageInteger <= 0) {
		                    check = false;
//logging error
		                    error.log(String.format("User chose negative value for age"));
		                    JOptionPane.showMessageDialog(null, "Age should be a positive integer!", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                    ageField.setText("");
		                }
		            } catch (NumberFormatException ex) {
		                check = false;
		                
//logging error
		                error.log(String.format("User chose non-integer value for age"));
		                JOptionPane.showMessageDialog(null, "Age should be a positive integer!", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		                ageField.setText("");
		            }
		        }
		            
		            
		        
		        
		        
		        

		        if (check == true) {

		            if (SignUpMethods.existenceOfNickname(nickname) == true) {
//logging error
		            	error.log(String.format("User chose used nickname"));
		            	JOptionPane.showMessageDialog(null, "This nickname is already used !", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		                nicknameField.setText("");

		            }

		            else if (SignUpMethods.existenceOfEmail(email) == true) {
//logging error
		            	error.log(String.format("User chose used email"));
		                JOptionPane.showMessageDialog(null, "This email is already used !", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		                emailField.setText("");

		            } 
		            else if (SignUpMethods.checkFileContainsAdmin()==true && comboBox.getSelectedItem().equals("Administrator")) {
//logging error
		            	error.log(String.format("User wanted to be Administrator while ther is one"));
		            	JOptionPane.showMessageDialog(null, "System can have at most one Administrator !", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            	
		            }
		            
		            else {
		                SignUpMethods.addingUserToFile(nickname, password, name, surname, ageInteger, email,type);
//logging info
		                info.log(String.format("%s succesfully created his/her account",nickname));
		                JOptionPane.showMessageDialog(null, "Sign-up successful!", "Success",
		                        JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		                
		                try {
		                    FileWriter writer = new FileWriter("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/ProfilePictures.txt", true);
		                    writer.write(nickname + " " + selectedFile.getAbsolutePath() + "\n");
		                    writer.close();
		                
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		                
		                
		                new LoginPage();
		                nicknameField.setText("");
		                passwordField.setText("");
		                nameField.setText("");
		                surnameField.setText("");
		                ageField.setText("");
		                emailField.setText("");
		            }
		        }

		    }
		});

		
		this.setVisible(true);
	}
	
	
	/**
	 * 
	 * @param email
	 * @return boolean
	 *  this method is used for checking if email is valid or not.
	 */
	public boolean isValidEmail(String email) {
	    String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
}          
