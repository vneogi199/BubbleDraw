import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class BubblePanel extends JPanel {

	private ArrayList<Bubble> bubbleList;			// to store info abt bubbles
	private int size = 30;										//size of bubble
	private Timer timer;
	/**1 s = 1000 ms
	 * For 30 fps,
	 * 1000/30 = 33
	 */
	private final int DELAY = 33;	//ms of delay  for 30fps
	
	
	public BubblePanel()	{
		
		bubbleList = new ArrayList<Bubble>();		//BubblePanel should get up new ArrayList
		
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(600, 400));
		
		timer.start();
	}
	
	//overridding paintComponent()
	//paintComponent() is method of JPanel class
	public void paintComponent(Graphics page)	{
		
		//passing page component to superclass
		super.paintComponent(page);	//calling superclass
		
		//draw all the bubbles from bubbleList
		for (Bubble bubble : bubbleList) {
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size/2, bubble.y - bubble.size/2, bubble.size, bubble.size);
		}
		
		
		//write the number of bubbles on screen
		page.setColor(Color.green);
		page.drawString("Count: " + bubbleList.size(), 5, 15);
		
	}
		
	private class BubbleListener implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			timer.stop();			//stop the animation
			//add to the bubbleList my mouse location
			bubbleList.add(new Bubble(arg0.getX(), arg0.getY(), size));
			repaint();
			

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// start the animation
			timer.start();
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			
			//add to the bubbleList my mouse location
			bubbleList.add(new Bubble(arg0.getX(), arg0.getY(), size));
			repaint();
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			//change the bubble size when the mouse wheel is moved
			size -= e.getWheelRotation();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//update the location of each bubble for animation
			for (Bubble bubble : bubbleList) {
				bubble.update();
			}
			//repaint the screen
			repaint();
			
		}
		
	}
	
	
	private class Bubble	{
		/**A bubble needs an x,y location and a size */
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xspeed;
		public int yspeed;
		private final int MAX_SPEED = 5;
		
		public Bubble(int newX, int newY, int newSize)	{
			x = newX;
			y = newY;
			size = newSize;
			color = new Color( (float)Math.random(), (float)Math.random(), (float)Math.random() );
			xspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);
			yspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);

		}
		
		public void update()	{
			
			if( xspeed == 0  && yspeed == 0){
				xspeed = 2;
				yspeed = 2;
				
			}
				
			
			x += xspeed;
			y += yspeed;
			
			//	collision detection with the edges of the panel
			if	( x < 0 || x > getWidth() )
					xspeed = -xspeed;
			if	( y <0 || y > getHeight() )
					yspeed = -yspeed;
		}
	}
}
