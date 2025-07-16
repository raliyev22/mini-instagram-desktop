package ImageFilterRaul;

import Provided_Methods.ImageMatrix;

public class GrayScaleFilter implements ImageFilterRaul{
	public ImageMatrix applyFilter(ImageMatrix imageMatrix,int intensity) {

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

}
