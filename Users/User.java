package Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Provided_Methods.ImageMatrix;

public class User {
	static List<String> nicknames = new ArrayList<>();
	static List<String> emails = new ArrayList<>();

	final String nickname;
	private String password;
	private String name;
	String surname;
	String age;
	private String email;
	public String type;

	public User(String nickname, String password, String name, String surname, String age, String email,String type) {

		this.nickname = nickname;

		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.type=type;

		nicknames.add(nickname);
		emails.add(email);
	}


	/**
	 * 
	 * @param imageMatrix
	 * @param kernelsize
	 * @return ImageMatrix as blurred
	 * this method do some operations on given ImageMatrix in order to make it blurred
	 */
	public  static ImageMatrix blurring(ImageMatrix imageMatrix, int kernelsize) {
		
		
		
		
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
	 * @param intensity
	 * @return ImageMatrix as sharpened
	 * this method do some operations on given ImageMatrix in order to make it sharpened
	 */
	public static ImageMatrix sharpening(ImageMatrix imageMatrix, int intensity) {
		ImageMatrix blurredImage = blurring(imageMatrix, intensity);

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

	/**
	 * 
	 * @param imageMatrix
	 * @param intensity
	 * @return ImageMatrix as grayScaled
	 * this method do some operations on given ImageMatrix in order to make it grayScaled
	 */
	public static ImageMatrix Grayscale(ImageMatrix imageMatrix, int intensity) {

		ImageMatrix grayImage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		for (int i = 0; i < imageMatrix.getWidth(); i++) {
			for (int j = 0; j < imageMatrix.getHeight(); j++) {
				int red = imageMatrix.getRed(i, j);
				int green = imageMatrix.getGreen(i, j);
				int blue = imageMatrix.getBlue(i, j);
				int grayValue = (red + green + blue) / intensity;
				int convertedGray = ImageMatrix.convertRGB(grayValue, grayValue, grayValue);
				grayImage.setRGB(i, j, convertedGray);
			}
		}

		return grayImage;
	}

	/**
	 * 
	 * @param imageMatrix
	 * @param intensity
	 * @return ImageMatrix as edgeDetected
	 * this method do some operations on given ImageMatrix in order to make it edgeDetected
	 */
	public static ImageMatrix edge_detection(ImageMatrix imageMatrix,int intensity) {

		
		ImageMatrix blurredImage = blurring(imageMatrix, intensity);

		
		ImageMatrix grayImage = Grayscale(blurredImage,intensity);

		
		int[][] xKernel = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
		int[][] yKernel = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };

		
		ImageMatrix edgeImage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		
		for (int i = 1; i < grayImage.getWidth() - 1; i++) {
			for (int j = 1; j < grayImage.getHeight() - 1; j++) {

				// Initialize the gx and gy values to zero
				int gxRed = 0, gxGreen = 0, gxBlue = 0;
				int gyRed = 0, gyGreen = 0, gyBlue = 0;

				
				for (int x = -1; x <= 1; x++) {
					for (int y = -1; y <= 1; y++) {

						
						int pixel = grayImage.getRGB(i + x, j + y);
						int grayValue = (pixel >> 16) & 0xff;

						int kernelX = xKernel[x + 1][y + 1];
						int kernelY = yKernel[x + 1][y + 1];

						gxRed += kernelX * grayValue;
						gxGreen += kernelX * grayValue;
						gxBlue += kernelX * grayValue;

						gyRed += kernelY * grayValue;
						gyGreen += kernelY * grayValue;
						gyBlue += kernelY * grayValue;
					}
				}

				int magnitudeRed = (int) Math.sqrt(gxRed * gxRed + gyRed * gyRed);
				int magnitudeGreen = (int) Math.sqrt(gxGreen * gxGreen + gyGreen * gyGreen);
				int magnitudeBlue = (int) Math.sqrt(gxBlue * gxBlue + gyBlue * gyBlue);

				magnitudeRed = Math.min(magnitudeRed, 255);
				magnitudeGreen = Math.min(magnitudeGreen, 255);
				magnitudeBlue = Math.min(magnitudeBlue, 255);

				
				int newRGB = ImageMatrix.convertRGB(magnitudeRed, magnitudeGreen, magnitudeBlue);
				edgeImage.setRGB(i, j, newRGB);
			}
		}
		return edgeImage;
	}


}
