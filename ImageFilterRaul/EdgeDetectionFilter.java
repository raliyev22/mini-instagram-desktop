package ImageFilterRaul;

import Provided_Methods.ImageMatrix;

public class EdgeDetectionFilter implements ImageFilterRaul {
	public ImageMatrix applyFilter(ImageMatrix imageMatrix ,int intensity) {
		BlurringFilter blurring=new BlurringFilter();
		GrayScaleFilter Grayscale=new GrayScaleFilter();

		// Step 1: Blur the image to reduce noise
		ImageMatrix blurredImage = blurring.applyFilter(imageMatrix, intensity);

		// Step 2: Convert the blurred image to grayscale
		ImageMatrix grayImage = Grayscale.applyFilter(blurredImage,intensity);

		// Step 3: Define the kernels for x and y directions using Sobel operators
		int[][] xKernel = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
		int[][] yKernel = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };

		// Step 4: Define a new image matrix to store the result
		ImageMatrix edgeImage = new ImageMatrix(imageMatrix.getWidth(), imageMatrix.getHeight());

		// Step 5: Loop through each pixel in the gray image, except the edges, and
		// apply the Sobel kernels
		for (int i = 1; i < grayImage.getWidth() - 1; i++) {
			for (int j = 1; j < grayImage.getHeight() - 1; j++) {

				// Initialize the gx and gy values to zero
				int gxRed = 0, gxGreen = 0, gxBlue = 0;
				int gyRed = 0, gyGreen = 0, gyBlue = 0;

				// Apply the Sobel kernels to the 3x3 neighborhood of the current pixel in the
				// gray image
				for (int x = -1; x <= 1; x++) {
					for (int y = -1; y <= 1; y++) {

						// Get the gray value of the pixel in the neighborhood
						int pixel = grayImage.getRGB(i + x, j + y);
						int grayValue = (pixel >> 16) & 0xff;

						// Get the x and y kernel values for this position in the kernel
						int kernelX = xKernel[x + 1][y + 1];
						int kernelY = yKernel[x + 1][y + 1];

						// Add the product of the kernel value and gray value to the gx and gy values
						gxRed += kernelX * grayValue;
						gxGreen += kernelX * grayValue;
						gxBlue += kernelX * grayValue;

						gyRed += kernelY * grayValue;
						gyGreen += kernelY * grayValue;
						gyBlue += kernelY * grayValue;
					}
				}

				// Calculate the magnitude of the gradient for the red, green, and blue channels
				int magnitudeRed = (int) Math.sqrt(gxRed * gxRed + gyRed * gyRed);
				int magnitudeGreen = (int) Math.sqrt(gxGreen * gxGreen + gyGreen * gyGreen);
				int magnitudeBlue = (int) Math.sqrt(gxBlue * gxBlue + gyBlue * gyBlue);

				// Cap the magnitude values at 255 to avoid overflow
				magnitudeRed = Math.min(magnitudeRed, 255);
				magnitudeGreen = Math.min(magnitudeGreen, 255);
				magnitudeBlue = Math.min(magnitudeBlue, 255);

				// Combine the magnitude values for the red, green, and blue channels and set
				// the pixel in the edge image
				int newRGB = ImageMatrix.convertRGB(magnitudeRed, magnitudeGreen, magnitudeBlue);
				edgeImage.setRGB(i, j, newRGB);
			}
		}
		return edgeImage;
	}

}
