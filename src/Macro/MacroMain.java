// ���콺�� �����̸� �״�� ���� �����̴� ��ũ��.
package Macro;

import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Scanner;

public class MacroMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<PointerInfo> point_list = new ArrayList<>();		
		
		Thread th = new InputThread(point_list);		
		th.start();
		
		
		while(true)
		{				
		//	if (status == true) {
				synchronized (point_list) {

					point_list.add(MouseInfo.getPointerInfo());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
		//	}

		}
		
		
	
		
		}
	

}
