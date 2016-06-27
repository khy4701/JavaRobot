package MacroFullVersion.Keyboard;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import MacroFullVersion.Util.PrintTest;

public class KeyAction {

	private Robot robot;
	private int modify_key = -1;
	private int nonmodify_key = -1;
	
	
	public KeyAction(Robot robot)
	{
		this.robot = robot;
	}
		
	
	public void KeyPressed(String str_code) {
		String[] code_split = str_code.split(" ");

	
		nonmodify_key = -1;
		
		if (code_split[0].equals("NonModifier")) {
			// Non_Modifier
			/*
			 * [A]  : NonModifier A
			 * [spacebar]  : NonModifier Space
			 * [CapsLock]  : NonModifier Caps Lock
			 */			
				
			nonmodify_key = StringToCode_NonModify(code_split[1]);		

			if( nonmodify_key != -1)
			{
				robot.keyPress(nonmodify_key);
				PrintTest.PrintSystemOrder("Pressed: " + code_split[1]);
				robot.delay(200);
			
//				robot.keyRelease(nonmodify_key);
//				PrintTest.PrintSystemOrder(" Released:" + code_split[1]);
			}

			
			
		} else if (code_split[0].equals("Modifier")) {

			// Modifier.

			/*
			 * [ctrl+A] : Modifier Ctrl A [Ctrl+Shift+A] : Modifier Ctrl+Shift A
			 * 
			 */
			
			nonmodify_key = -1;
			modify_key = -1;
			
			//System.out.println("Play Modifier :" + code_split[0] + code_split[1] + code_split[2]);
					
			modify_key = StringToCode_Modify(code_split[1]);
			
			if( modify_key != -1)
			{
				robot.keyPress(modify_key);
				PrintTest.PrintSystemOrder("Pressed:" + code_split[1]);
			}

			if (!(code_split[2].equals("Left") || code_split[2].equals("Right"))) {
								
				// ex) Modifiy Ctrl A ==> A를 읽기 위한 Method. 
				nonmodify_key = StringToCode_NonModify(code_split[2]);			
				
				if( nonmodify_key != -1)
				{
					robot.keyPress(nonmodify_key);
					PrintTest.PrintSystemOrder("Pressed: " + code_split[2]);
					robot.delay(200);
				}
				
			}
			
//			if( modify_key != -1)
//			{
//				robot.keyRelease(modify_key);
//				PrintTest.PrintSystemOrder("Released: " + code_split[1]);
//			}
//			
//			if( nonmodify_key != -1)				
//			{
//				robot.keyRelease(nonmodify_key);
//				PrintTest.PrintSystemOrder("Released: " + code_split[2]);
//			}
		}
	}
	
	public int StringToCode_NonModify(String codeStr)
	{
		int code = -1;
		
		if (codeStr.equals("Space")) {
			code = KeyEvent.VK_SPACE;
		} else if (codeStr.equals("Tab")) {
			code = KeyEvent.VK_TAB;
		} else if (codeStr.equals("Escape")) {
			code = KeyEvent.VK_ESCAPE;			
		} else if (codeStr.equals("Caps")) {
			code = KeyEvent.VK_CAPS_LOCK;
		} else if (codeStr.equals("Enter")){
			code = KeyEvent.VK_ENTER;			
		
		} else {
			code = codeStr.charAt(0);

			// Robot 클래스는 아스키 코드값이 대문자만 제공함.
			if (code > 96 && code < 123)
				code = code - 32;		
		}
		
		return code;
	}
	
	public int StringToCode_Modify(String coderStr)
	{
		int code = -1;
		
		if (coderStr.equals("Ctrl"))			
			code = KeyEvent.VK_CONTROL;				
		else if (coderStr.equals("Alt"))
			code = KeyEvent.VK_ALT;
		else if (coderStr.equals("Shift"))
			code = KeyEvent.VK_SHIFT;
		
		return code;
	}
	
	public void KeyReleased(String str)
	{
		
		String []split_str = str.split(" ");
		
		if( (split_str[0].equals("Left") || split_str[0].equals("Rigth")))
		{
			
			modify_key = StringToCode_Modify(split_str[1]);
			if( modify_key != -1)
				robot.keyRelease(modify_key);						
			PrintTest.PrintSystemOrder("Released: " + split_str[1]);

		}
		else
		{			
			nonmodify_key = StringToCode_NonModify(split_str[0]);
			if( nonmodify_key != -1)
				robot.keyRelease(nonmodify_key);
			
			PrintTest.PrintSystemOrder("Released: " + split_str[0]);
		}
		
		
		
	}
	
}
