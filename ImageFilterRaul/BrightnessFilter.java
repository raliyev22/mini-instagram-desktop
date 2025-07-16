package ImageFilterRaul;

import Provided_Methods.ImageMatrix;

public class BrightnessFilter implements ImageFilterRaul {
	public ImageMatrix applyFilter(ImageMatrix imageMatrix, int multiplier) {
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
	
	

}
