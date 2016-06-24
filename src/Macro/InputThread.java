package Macro;

import java.awt.AWTException;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class InputThread extends Thread{
	
	private ArrayList<PointerInfo> point_list;
	Scanner sc = new Scanner(System.in);
	
	public InputThread(ArrayList<PointerInfo> point_list) {
		// TODO Auto-generated constructor stub
		this.point_list = point_list;	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
				
		try {
			Robot robot = new Robot();
			
			while(true)
			{
				if (sc.next().equals("hi")) {
					synchronized(point_list)
					{
						for (PointerInfo i : point_list) {

							robot.mouseMove((int) i.getLocation().getX(), (int) i.getLocation().getY());
							System.out.println( "Pointer(X,Y) : (" +i.getLocation().getX() +","+ i.getLocation().getY() +" )");
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
									
					System.out.println("Æ÷ÀÎÆ® Size: "+ point_list.size());

				}
			}
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
