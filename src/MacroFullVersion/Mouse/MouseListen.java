package MacroFullVersion.Mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import MacroFullVersion.Util.EventHandle;

public class MouseListen implements NativeMouseInputListener{

	private ArrayList<EventHandle> arrayList;
	private MouseMoveListen m_th;

	static private boolean IsDragged =false;
	static private boolean MoveListenStatus = true;
	
	public MouseListen(ArrayList<EventHandle> arrayList)
	{
		this.arrayList = arrayList;
		m_th = new MouseMoveListen(arrayList);

	}
//
	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
					
		synchronized(arrayList)
		{
			arrayList.add(new EventHandle(new String("MouseClicked"),e.getPoint()));
			System.out.println("MouseClicked!!!!");
		}
		//System.out.println(e.getX()+"," +e.getY());
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		
		if( IsDragged )
		{
			synchronized(arrayList)
			{
				m_th = new MouseMoveListen(arrayList);
				m_th.start();
				
				arrayList.add(new EventHandle(new String("MouseReleased"),e.getPoint()));
				
			}
			IsDragged = false;
			MoveListenStatus = true;

		}
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Draged!");
		
		IsDragged = true;
		synchronized(arrayList)
		{
			try {

				arrayList.add(new EventHandle(new String("MouseDragged"), e.getPoint()));
				System.out.println("MouseDragged!!!!");

				if (MoveListenStatus) {
					m_th.interrupt();
					MoveListenStatus = false;
				}

				Thread.sleep(10);
			} catch (InterruptedException t) {
				// TODO Auto-generated catch block
			}
		}
	}
	
	public void MoveListenStart()
	{
		// Mouse의 Move를 감지하기 위한 스레드 따로 생성(아마 속도 때문이였을..)
		m_th.start();		
	}
	
	public void MoveListenStop()
	{
		// MouseMoveListen의 Wait()의 예외처리 호출.
		m_th.interrupt();
	}
	
	public boolean getDragStatus()
	{
		return IsDragged;
	}
	
	
}
