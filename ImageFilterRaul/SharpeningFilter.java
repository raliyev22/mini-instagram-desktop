package ImageFilterRaul;

import Provided_Methods.ImageMatrix;

public class SharpeningFilter implements ImageFilterRaul {
	public ImageMatrix applyFilter(ImageMatrix imageMatrix, int intensity) {
		BlurringFilter blurring= new BlurringFilter();
		ImageMatrix blurredImage = blurring.applyFilter(imageMatrix, intensity);

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
	}

}
