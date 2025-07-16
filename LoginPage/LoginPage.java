package LoginPage;

/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Raul Aliyev, 0082372>
*************************************************************************/


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BaseLogger.error;
import BaseLogger.info;
import ProfilPage.NormalProfilePageWithHierarchies;
import SignUpPage.SignUp;
import SignUpPage.SignUpMethods;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPage extends JFrame {
    private JPanel contentPane;
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * This constructor creates a frame where user can log in
     */
    public LoginPage() {
    	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 600, 600);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        this.contentPane.setLayout(null);

        JLabel Nickname = new JLabel("Nickname:");
        Nickname.setBounds(121, 65, 90, 30);
        contentPane.add(Nickname);
        

        nicknameField = new JTextField();
        nicknameField.setBounds(223, 67, 200, 25);
        contentPane.add(nicknameField);
        

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(121, 100, 100, 25);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(223, 99, 200, 25);
        
        
        contentPane.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(351, 162, 100, 25);
        loginButton.addActionListener(
        		new ActionListener() {
        			
        			public void actionPerformed(ActionEvent a) {
        				
        				if (SignUpMethods.existenceOfNickname(nicknameField.getText())==false) {
        					error.log(String.format("User entered nonexist nickname"));
        					JOptionPane.showMessageDialog(null, "This nickname does not exist", "Unknown User", JOptionPane.ERROR_MESSAGE);
        				}
        				
        				else {
        					
        					if ((SignUpMethods.MapOfNicknameAndPassword().get(nicknameField.getText()).equals(String.valueOf(passwordField.getPassword())))==false){
            					error.log(String.format("User entered wrong nickname or password"));

        						JOptionPane.showMessageDialog(null, "Nickname or password is wrong","Unknown User", JOptionPane.ERROR_MESSAGE);
        						nicknameField.setText("");
        						passwordField.setText("");
        					}
        					
        					else {
        						ArrayList<String> information= SignUpMethods.gettingAllInformationOfUser(nicknameField.getText());
        						

        						new NormalProfilePageWithHierarchies(information.get(0),information.get(1),information.get(2),information.get(3),information.get(4),information.get(5),information.get(6));
        						dispose();

        					}
        				}
        				
        			}
        		});
        contentPane.add(loginButton);
        
        JButton btnNewButton = new JButton("Sign Up");
        btnNewButton.setBounds(166, 160, 117, 29);
        btnNewButton.addActionListener(
        		new ActionListener() {
        			
        			public void actionPerformed(ActionEvent a) {
        				dispose();
        				
        				new SignUp();
        			}
        		});
        
        contentPane.add(btnNewButton);
        setVisible(true);
    }
}