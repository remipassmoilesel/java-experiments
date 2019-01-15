package org.opengl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class BasicGLFrame extends JFrame implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		gl.glBegin(GL2.GL_LINES);// static field
		gl.glVertex3f(0.50f, -0.50f, 0);
		gl.glVertex3f(-0.50f, 0.50f, 0);
		gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// method body
	}

	public static void main(String[] args) {

		// getting the capabilities object of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// creating frame
		final BasicGLFrame frame = new BasicGLFrame();

		// The GLJpanel class
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(frame);
		glcanvas.setSize(400, 400);

		// adding canvas to frame
		frame.add(glcanvas);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setVisible(true);
	}

}