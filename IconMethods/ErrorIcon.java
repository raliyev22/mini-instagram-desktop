package IconMethods;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ErrorIcon {
	
	public static ImageIcon question() {
		
		Image image = new ImageIcon("/Users/Raul/IdeaProjects/COMP132_Project/iconPhotos/error.png").getImage();

		
		Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		
		return new ImageIcon(scaledImage);
	}

}