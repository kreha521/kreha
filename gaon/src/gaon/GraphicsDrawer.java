package gaon;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class GraphicsDrawer extends Component {
	private static final long serialVersionUID = 7341738986888373924L;

	private Dimension size;
	private Image back;
	private Graphics buffer;
	private Component comp;

	public <T extends Component> GraphicsDrawer(Component comp){
		this.comp = comp;
		size = comp.getSize();
		back = comp.createImage(size.width, size.height);
		buffer = back.getGraphics();
	}

	public Graphics getGraphics() {
		return buffer;
	}

	public void draw(Graphics g) {
		g.drawImage(back, 0, 0, comp);
	}
}
