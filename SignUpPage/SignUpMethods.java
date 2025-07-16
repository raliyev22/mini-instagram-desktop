package SignUpPage;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class SignUpMethods {
	


	/**
	 * 
	 * @return MAP
	 * this method goes through each line in specified text file, and creates map whose keys are nickname and values are emails.
	 */
	public static LinkedHashMap<String,String> addingNicknamesAndEmailsToArray() {
		LinkedHashMap<String,String> textToMap= new LinkedHashMap<String,String>();

		try (Scanner input = new Scanner(
				Paths.get("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/UsersInformation.txt"))) {


			while (input.hasNext()) { 
				String line = input.nextLine();
				if (line.isEmpty()) {
					continue;
				} else {
					String[] words = line.split(" ");

					String nickname = words[0];
					String email = words[5];

					textToMap.put(nickname, email);
				}
			}

			input.close(); // close the file
		}
		// IOException
		catch (FileNotFoundException io){
			System.out.println(io.getMessage());
		}
		catch (IOException io) {
			System.out.println("asdasd");
			System.err.println("Error opening inpsut file. Terminating."+io.getMessage());
			System.exit(1);
		}
		
		return textToMap;

	}
	/**
	 * 
	 * @param email
	 * @return boolean
	 * this method checks if given email exists or not
	 */
	public static boolean existenceOfEmail(String email) {
		;

		if (addingNicknamesAndEmailsToArray().containsValue(email))
			return true;
		else
			return false;

	}
	/**
	 * 
	 * @param nickname
	 * @return boolean
	 * this method checks if given nickname exists or not
	 */
	public static boolean existenceOfNickname(String nickname) {
		
		if (addingNicknamesAndEmailsToArray().containsKey(nickname))
			return true;
		else
			return false;

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
	 * this method takes arguments and writes them to specified text file.
	 */
	public static void addingUserToFile(String nickname, String password, String name, String surname, int age,
			String email, String type) {
		try (Formatter output = new Formatter(new FileOutputStream(
				"/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/UsersInformation.txt", true))) {

			output.format(String.format("%n%s %s %s %s %d %s %s", nickname, password, name, surname, age, email,type));

			output.close();

		} catch (SecurityException securityException) {
			System.err.println("Write permission denied. Terminating.");
			System.exit(1);
		}

		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
	}
	/**
	 * 
	 * @return Map
	 * this method goes through each line in specified line, and creates new map whose key values are nickname and values are password
	 */
	public static LinkedHashMap<String,String> MapOfNicknameAndPassword(){
		LinkedHashMap<String,String> Map= new LinkedHashMap<String,String>();

		try (Scanner input = new Scanner(
				Paths.get("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/UsersInformation.txt"))) {

			
			while (input.hasNext()) { 
				String line = input.nextLine();
				if (line.isEmpty()) {
					continue;
				} else {
					String[] words = line.split(" ");

					String nickname = words[0];
					String password = words[1];

					Map.put(nickname, password);
				}
			}

			input.close(); 
		}
		// IOException
		catch (IOException io) {
			System.err.println("Error opening inpsut file. Terminating.");
			System.exit(1);
		}
		
		return Map;

		
	}
	/**
	 * 
	 * @param nickname
	 * @return ArrayList of user information
	 * This method goes through eacn line in specified text file, split that line by empty space, and converts it to array, and returns this array.
	 */
	public static ArrayList<String> gettingAllInformationOfUser(String nickname) {
		ArrayList<String> information = new ArrayList<String>();

		try (Scanner input = new Scanner(
				Paths.get("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/UsersInformation.txt"))) {

			
			while (input.hasNext()) { 
				String line = input.nextLine();
				if (line.isEmpty()) {
					continue;
				} else {

					String[] words = line.split(" ");
					if (words[0].equals(nickname)) {

						information.add(words[0]); // Nickname
						information.add(words[1]); // Password
						information.add(words[2]); // Name
						information.add(words[3]); // Surname
						information.add(words[4]); // Age
						information.add(words[5]);// Email

						information.add(words[6]); // User type
					}

				}
			}

			input.close(); 
		}
		// IOException
		catch (IOException io) {
			System.err.println("Error opening inpsut file. Terminating.");
			System.exit(1);
		}

		return information;

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
	 * this method takes arguments, and uptade user information in specifiec text file. Method do this implementaton by creating temp text file and then replacing originial one with temp one.
	 */
	public static void updateUserCredentials(String nickname, String password, String name, String surname, int age, String email, String type) {
	    LinkedHashMap<String, String> usersMap = addingNicknamesAndEmailsToArray();
	    ArrayList<String> userInfo;

	    File originalFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/UsersInformation.txt");
	    File tempFile = new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/TempUsersInformation.txt");

	    try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
	         PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

	        for (Map.Entry<String, String> entry : usersMap.entrySet()) {
	            String currentNickname = entry.getKey();
	            String currentEmail = entry.getValue();

	            userInfo = gettingAllInformationOfUser(currentNickname);

	            if (currentNickname.equals(nickname)) {
	                writer.format("%s %s %s %s %d %s %s%n", nickname, password, name, surname, age, email, type);
	            } else {
	                if (userInfo.size() < 6) {
	                    continue;
	                }
	                writer.format("%s %s %s %s %s %s %s%n", userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3), userInfo.get(4), userInfo.get(5), userInfo.get(6));
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    
	    if (tempFile.renameTo(originalFile)) {
	        System.out.println("User credentials updated successfully.");
	    } else {
	        System.err.println("Failed to update user credentials.");
	    }
	}
	/**
	 * 
	 * @return boolean
	 * this method is used for checking specified text file if contains Administrator or not
	 */
	public static boolean checkFileContainsAdmin() {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/SignUpPage/UsersInformation.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Administrator")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


	
	

}
