package Provided_Methods;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * ImageSecretary is used for basic I/O operations in terms of images.
 * 
 * @author osman.yasal
 *
 */
public class ImageSecretary {

    public static final String IMAGE_LOCATION = "./images/";
	private ImageSecretary() {

	}

	/**
	 * Reads the image from your resources.
	 * 
	 * @param imName    name of the file
	 * @param extension of the file
	 * @return new ImageMatrix
	 * @throws IOException
	 */
	public static ImageMatrix readResourceImage(String pathName) throws IOException {
		return new ImageMatrix(ImageIO.read(new File(pathName)));
	}

	/**
	 * Writes the rendered image to your resources with the given name and extension
	 * 
	 * @param image     rendered image
	 * @param name      of the file
	 * @param extension of the file
	 * @return
	 */
	public static boolean writeFilteredImageToResources(ImageMatrix image, String name, String extension) {
		return writeImageToResources(image.getBufferedImage(), name, extension);
	}

	public static List<String> getRawImageNames() {
		List<String> res = new ArrayList<>();
		File[] files = new File(IMAGE_LOCATION).listFiles();

		for (File file : files) {
			if (file.isFile() && !file.getName().contains("_")) {
				res.add(file.getName());
			}
		}
		return res;
	}

	/**
	 * Writes the rendered image to your resources with the given name as jpg
	 * 
	 * @param image
	 * @param name
	 * @return
	 */
	private static boolean writeImageToResources(RenderedImage image, String name, String extension) {
		boolean result = true;
		try {
			System.out.println("/Users/SamiR/COMP132-workspace/COMP132-Project/IMAGES/" + name +"."+ extension);
			ImageIO.write(image, "jpg", new File("/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/IMAGES/" + name +"."+ extension));
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

}
