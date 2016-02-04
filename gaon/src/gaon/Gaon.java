package gaon;
import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gaon extends Applet implements Runnable, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -9067656144594669347L;

	private Color col = new Color(255, 0 ,0);

	private Image image;
	private GraphicsDrawer drawer;

	private int x, y;
	private int mouse_x, mouse_y;
	private int gaon_x, gaon_y;
	private int gaon_width, gaon_height;

	private Thread thread = null;

	public void init(){
		drawer = new GraphicsDrawer(this);
		try {
			image = ImageIO.read(Gaon.class.getResource("vanila.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addEventListner();

		changeLineWeight(drawer.getGraphics());

		thread = new Thread(this);
		thread.start();
	}

	public void destroy(){
		removeEventListner();
	}

	public void addEventListner(){
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void removeEventListner(){
		removeMouseListener(this);
		removeMouseMotionListener(this);
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint(Graphics g){
		drawer.getGraphics().setColor(col);
		drawer.getGraphics().drawOval(x, y, (int) (Math.random() * 500), (int) (Math.random() * 500));
		drawer.draw(g);

		//ガオン出現
		gaon(g);
	}

	public void run(){
		while(true){
			x = (int) (Math.random() * 800);
			y = (int) (Math.random() * 800);
			col = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

			try{
				Thread.sleep(500);
			}catch (InterruptedException e){
			}

			repaint();
		}
	}

	public void gaon(Graphics g){
		g.drawImage(image, x, y, this);

		gaon_x = x;
		gaon_y = y;

		gaon_height = (int) (image.getHeight(this));
		gaon_width = (int) (image.getWidth(this));

		if(isHitGaon()){
			Graphics dg = drawer.getGraphics();
			dg.setFont(new Font("MS ゴシック", Font.BOLD, 40));;
			dg.drawString("ガオン", mouse_x, mouse_y);
		}
	}

	public void changeLineWeight(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		BasicStroke wideStroke = new BasicStroke(4.0f);
		g2.setStroke(wideStroke);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public boolean isHitGaon() {
		return (gaon_x <= mouse_x && mouse_x <= gaon_x + gaon_width
			&& gaon_y <= mouse_y && mouse_y <= gaon_y + gaon_height);
	}
}