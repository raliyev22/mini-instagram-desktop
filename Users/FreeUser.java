package Users;

import java.awt.image.BufferedImage;

import Provided_Methods.ImageMatrix;
import Provided_Methods.ImageMatrix.*;

public class FreeUser extends User {

	public FreeUser(String nickname, String password, String name, String surname, String age, String email,String type) {
		super(nickname, password, name, surname, age, email,type);

	}
	
	/**
	 * 
	 * @param imageMatrix
	 * @param kernelsize
	 * @return ImageMatrix as blurred
	 * this method do some operations on given ImageMatrix in order to make it blurred
	 */
	public static ImageMatrix blurring(ImageMatrix imageMatrix, int kernelsize) {
		ImageMatrix temporaryMatrix = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		for (int i = (kernelsize - 1) / 2; i < imageMatrix.getWidth() - (kernelsize - 1) / 2; i++) {
			for (int j = (kernelsize - 1) / 2; j < imageMatrix.getHeight() - (kernelsize - 1) / 2; j++) {

				int red = 0;
				int green = 0;
				int blue = 0;

				for (int k = i - (kernelsize - 1) / 2; k < i + ((kernelsize - 1) / 2) + 1; k++) {
					for (int p = j - (kernelsize - 1) / 2; p < j + ((kernelsize - 1) / 2) + 1; p++) {
						red += imageMatrix.getRed(k, p);
						green += imageMatrix.getGreen(k, p);
						blue += imageMatrix.getBlue(k, p);
					}
				}
				int redAvg = red / (int) Math.pow(kernelsize, 2);
				int greenAvg = green / (int) Math.pow(kernelsize, 2);
				int blueAvg = blue / (int) Math.pow(kernelsize, 2);
				int newRGB = ImageMatrix.convertRGB(redAvg, greenAvg, blueAvg);
				temporaryMatrix.setRGB(i, j, newRGB);

			}
		}
		return temporaryMatrix;

	}
	/**
	 * 
	 * @param imageMatrix

	 * @return ImageMatrix as sharpened
	 * this method do some operations on given ImageMatrix in order to make it sharpened
	 */
	public static ImageMatrix sharpening(ImageMatrix imageMatrix) {
		ImageMatrix blurredImage = blurring(imageMatrix, 51);

		ImageMatrix finalimage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		for (int i = 0; i < imageMatrix.getWidth(); i++) {
			for (int j = 0; j < imageMatrix.getHeight(); j++) {
				int red = 2 * imageMatrix.getRed(i, j) - blurredImage.getRed(i, j);
				if (red < 0) {
					red = 0;
				} else if (red > 255) {
					red = 255;
				}

				int green = 2 * imageMatrix.getGreen(i, j) - blurredImage.getGreen(i, j);
				if (green < 0) {
					green = 0;
				} else if (green > 255) {
					green = 255;
				}

				int blue = 2 * imageMatrix.getBlue(i, j) - blurredImage.getBlue(i, j);
				if (blue < 0) {
					blue = 0;
				} else if (blue > 255) {
					blue = 255;
				}

				int ttt = ImageMatrix.convertRGB(red, green, blue);
				finalimage.setRGB(i, j, ttt);
			}
		}
		return finalimage;

		////////////

	}

}
