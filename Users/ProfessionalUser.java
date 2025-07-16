package Users;

import Provided_Methods.ImageMatrix;

public class ProfessionalUser extends HobbyistUser {

	public ProfessionalUser(String nickname, String password, String name, String surname, String age, String email,String type) {
		super(nickname, password, name, surname, age, email,type);

	}
	/**
	 * 
	 * @param imageMatrix
	 * @param intensity
	 * @return ImageMatrix as grayScaled
	 * this method do some operations on given ImageMatrix in order to make it grayScaled
	 */
	public static ImageMatrix Grayscale(ImageMatrix imageMatrix) {

		ImageMatrix grayImage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		for (int i = 0; i < imageMatrix.getWidth(); i++) {
			for (int j = 0; j < imageMatrix.getHeight(); j++) {
				int red = imageMatrix.getRed(i, j);
				int green = imageMatrix.getGreen(i, j);
				int blue = imageMatrix.getBlue(i, j);
				int grayValue = (red + green + blue) / 3;
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
	public static ImageMatrix edge_detection(ImageMatrix imageMatrix) {

		
		ImageMatrix blurredImage = blurring(imageMatrix, 3);

		
		ImageMatrix grayImage = Grayscale(blurredImage);

		
		int[][] xKernel = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
		int[][] yKernel = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };

		
		ImageMatrix edgeImage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		
		for (int i = 1; i < grayImage.getWidth() - 1; i++) {
			for (int j = 1; j < grayImage.getHeight() - 1; j++) {

				
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

