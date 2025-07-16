package ImageFilterRaul;

import Provided_Methods.ImageMatrix;

public class BlurringFilter implements ImageFilterRaul {
	
	public ImageMatrix applyFilter(ImageMatrix imageMatrix, int kernelsize) {
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

}
