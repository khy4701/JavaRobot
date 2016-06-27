package MacroFullVersion.Mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import MacroFullVersion.Util.EventHandle;

public class MouseMoveListen extends Thread{

	private ArrayList<EventHandle> arrayList;
	private Point pre_pos;
	
	public MouseMoveListen(ArrayList<EventHandle> arrayList)
	{
		this.arrayList = arrayList;
		pre_pos = new Point(-1,-1);
		System.out.println("MouseMoveListen 스레드 생성.");
	}
	
	public void run()
	{
		while (true) {
			
			try{
			Point pos = MouseInfo.getPointerInfo().getLocation();
			
			if( !pre_pos.equals(pos))
			{
				synchronized (arrayList) {

					arrayList.add(new EventHandle(new String("MouseMoved"), pos));

					System.out.println("MousePos Added : " + pos.x + "," + pos.y);
				
					pre_pos = (Point)pos.clone();
				}
			}			
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block				
				System.out.println("MouseMoveListen 스레드 종료.");
				break;
			}		
		}	
		
	}
}
