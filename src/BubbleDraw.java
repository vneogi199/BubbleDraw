import javax.swing.JFrame;


public class BubbleDraw extends JFrame {

	public static void main(String[] args) {
		//set up frame for BubbleDraw app
		JFrame frame = new JFrame("The BubbleDraw App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//close the app on clicking exit
		
		frame.getContentPane().add(new BubblePanel());							//create a panel from BubblePanel
		
		frame.pack();																						//prepare to show
		frame.setVisible(true);																		//make frame visible
		
	}

}
