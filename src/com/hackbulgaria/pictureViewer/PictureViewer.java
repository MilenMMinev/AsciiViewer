package com.hackbulgaria.pictureViewer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PictureViewer {

	final static int COLUMNS = 100;
	final static int ROWS = 40;

	private static BufferedImage resizeImage(BufferedImage originalImage) {

		int ratio = originalImage.getHeight() / COLUMNS;
		BufferedImage resizedImage = new BufferedImage(COLUMNS, ROWS, 1);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, COLUMNS, ROWS, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1){
		System.out.println("usage: run.jar <ABSOLUTE_PATH_TO_FILE>");
			System.exit(-1);
		}

		final String PATH = args[0];
		File file = new File(PATH);
		BufferedImage img = ImageIO.read(file);
		img = resizeImage(img);
		int pixel;
		for (int i = 0; i < img.getHeight(); i++)
		{
			for (int j = 0; j < img.getWidth(); j++)
			{
				pixel = img.getRGB(j, i);
				Color c = new Color(pixel, false);
				System.out.print(colorToString(c));
			}
			System.out.println();
			
		}
	}

	public static char colorToString(Color c) {
		int intensity = (c.getRed() + c.getGreen() + c.getBlue() / 3);
		if (intensity > 240)
			return ' ';
		else if ((intensity > 200) && (intensity < 240))
			return '.';
		else if ((intensity > 160) && (intensity < 200))
			return '*';
		else if ((intensity > 120) && (intensity < 160))
			return '+';
		else if ((intensity > 80) && (intensity < 120))
			return '@';
		else
			return '#';
	}
}
		
