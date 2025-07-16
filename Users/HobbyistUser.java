package Users;

import Provided_Methods.ImageMatrix;

public class HobbyistUser extends FreeUser {

	public HobbyistUser(String nickname, String password, String name, String surname, String age, String email,String type) {
		super(nickname, password, name, surname, age, email,type);

	}
	/**
	 * 
	 * @param imageMatrix
	 * @param multiplier
	 * @return ImageMatrix as brightened
	 * this method do some operations on given ImageMatrix in order to make it brightened
	 */
	public static ImageMatrix brightness(ImageMatrix imageMatrix, int multiplier) {
		ImageMatrix finalimage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		for (int i = 0; i < imageMatrix.getWidth(); i++) {
			for (int j = 0; j < imageMatrix.getHeight(); j++) {
				int red = multiplier * imageMatrix.getRed(i, j);
				if (red < 0) {
					red = 0;
				} else if (red > 255) {
					red = 255;
				}

				int green = multiplier * imageMatrix.getGreen(i, j);
				if (green < 0) {
					green = 0;
				} else if (green > 255) {
					green = 255;
				}

				int blue = multiplier * imageMatrix.getBlue(i, j);
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

	}
	/**
	 * 
	 * @param imageMatrix
	 * @param multiplier
	 * @return ImageMatrix as contrasted
	 * this method do some operations on given ImageMatrix in order to make it contrasted
	 */
	public static ImageMatrix contrast(ImageMatrix imageMatrix, double multiplier) {
		ImageMatrix finalimage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		
		int sumRed = 0;
		int sumGreen = 0;
		int sumBlue = 0;
		int numPixels = 0;

		for (int i = 0; i < imageMatrix.getWidth(); i++) {
			for (int j = 0; j < imageMatrix.getHeight(); j++) {
				sumRed += imageMatrix.getRed(i, j);
				sumGreen += imageMatrix.getGreen(i, j);
				sumBlue += imageMatrix.getBlue(i, j);

				numPixels++;
			}
		}

		int averageRed = sumRed / numPixels;
		int averageGreen = sumGreen / numPixels;
		int averageBlue = sumBlue / numPixels;

		for (int i = 0; i < imageMatrix.getWidth(); i++) {
			for (int j = 0; j < imageMatrix.getHeight(); j++) {
				int red = imageMatrix.getRed(i, j);
				int green = imageMatrix.getGreen(i, j);
				int blue = imageMatrix.getBlue(i, j);

				
				red = (int) (multiplier * (red - averageRed) + averageRed);
				if (red < 0) {
					red = 0;
				} else if (red > 255) {
					red = 255;
				}
				green = (int) (multiplier * (green - averageGreen) + averageGreen);
				if (green < 0) {
					green = 0;
				} else if (green > 255) {
					green = 255;
				}
				blue = (int) (multiplier * (blue - averageBlue) + averageBlue);
				if (blue < 0) {
					blue = 0;
				} else if (blue > 255) {
					blue = 255;
				}

				int rgb = ImageMatrix.convertRGB(red, green, blue);
				finalimage.setRGB(i, j, rgb);
			}
		}

		return finalimage;
	}

}
