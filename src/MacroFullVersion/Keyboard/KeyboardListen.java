package MacroFullVersion.Keyboard;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import MacroFullVersion.Util.EventHandle;

public class KeyboardListen implements NativeKeyListener {
	
	//
	static String preOrderKey = new String("");
	private ArrayList<EventHandle> arrayList; 
	
	public KeyboardListen(ArrayList<EventHandle> arrayList)
	{
		this.arrayList = arrayList;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		synchronized(arrayList)
		{
			System.out.println(NativeInputEvent.getModifiersText(e.getModifiers()));
 
			String getModifier = NativeInputEvent.getModifiersText(e.getModifiers());
			String subModifierKey;
			
			/*
			--- Modifier( Ctrl, Alt, Shift ) 		 
			 
			 1. 일 경우 getModifier 값 : Ctrl, Alt, Shift
			
			 Modifier 가 아닐 경우 getModifier 값 : 공백
			 */
			if (!getModifier.equals("")) {				
				System.out.println("Modifier Pressed");

				// subModifierKey 값 ( 최소 문자열 갯수 2개 이상)  
				// ex)  [ctrl+A]  : Modifier Ctrl A
				// ex2) [Ctrl+Shift+A]  : Modifier Ctrl+Shift A
				subModifierKey = new String("Modifier ");
				subModifierKey = subModifierKey.concat( getModifier.concat(" " + NativeKeyEvent.getKeyText(e.getKeyCode())));						

			} else {
				System.out.println("non Modifier Pressed");

				// subModifierKey 값   
				// ex)  [A]  : NonModifier A
				// ex2) [spacebar]  : NonModifier Space
				// ex3) [CapsLock]  : NonModifier Caps Lock
				subModifierKey = new String("NonModifier ");
				subModifierKey = subModifierKey.concat(NativeKeyEvent.getKeyText(e.getKeyCode()));	
				
			}
					
			arrayList.add(new EventHandle(new String("KeyPressed"), subModifierKey));
			
			System.out.println(subModifierKey);
			System.out.println("KeyPressed!!!");	
			
		}
		
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
					
		arrayList.add(new EventHandle(new String("KeyReleased"), NativeKeyEvent.getKeyText(e.getKeyCode())));
		System.out.println("KeyReleased!!!");
	} 

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
	}
}
