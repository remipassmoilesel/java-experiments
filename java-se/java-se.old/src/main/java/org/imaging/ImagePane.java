package org.imaging;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.imaging.ImageReadException;

public class ImagePane extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * L'image à peindre
	 */
	private BufferedImage imageToPaint;

	/**
	 * La région à aller chercher dans l'image
	 */
	private Rectangle sourceRegion;

	private ImageReader reader;

	public ImagePane(String path) {
		super();

		try {
			// chemin de l'image
			Path ip = Paths.get(path);
			this.sourceRegion = new Rectangle(0, 0, 100, 100);

			// ouvrir un stream
			ImageInputStream stream = ImageIO.createImageInputStream(ip.toFile());
			Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);

			if (readers.hasNext()) {
				this.reader = readers.next();
				reader.setInput(stream);
				
				updateImage();
			}

			else {
				throw new IllegalStateException("Unsupported image: " + path);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateImage() {
		ImageReadParam param = reader.getDefaultReadParam();
		param.setSourceRegion(sourceRegion);

		try {
			this.imageToPaint = reader.read(0, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (this.imageToPaint != null) {
			g2d.drawImage(imageToPaint, 0, 0, imageToPaint.getWidth(),
					imageToPaint.getHeight(), null);
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// créer une fenêtre
				JFrame jf = new JFrame();

				// arret en quittant
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// contenu de la fenêtre
				jf.setContentPane(new ImagePane("huge_image.jpg"));

				jf.setSize(new Dimension(800, 600));

				// positonnement
				jf.setLocationRelativeTo(null);

				jf.setVisible(true);
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
