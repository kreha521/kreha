package gaon;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class GraphicsDrawer extends Applet{
	private static final long serialVersionUID = 7341738986888373924L;

	private Dimension size;
	private Image back;
	private Graphics buffer;
	private Applet applet;

	public <T extends Applet> GraphicsDrawer(T applet){
		size = applet.getSize();
		back = applet.createImage(size.width, size.height);
		buffer = back.getGraphics();
	}

	public Graphics getGraphics() {
		return buffer;
	}

	public void draw(Graphics g) {
		g.drawImage(back, 0, 0, applet);
	}
}
