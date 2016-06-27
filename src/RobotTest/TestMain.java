package RobotTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

public class TestMain {

	public TestMain() {
		
	

		try {
			// notepad 프로그램 활성화
			
			Robot robot = new Robot();
		
			Control_Mouse(robot);
			
			// 시간 지연
			robot.delay(1000);
			
			// 메모장 생성
			// Runtime.getRuntime().exec("notepad");
			
			Control_Keyboard(robot);
			

		} catch (AWTException ae) {
			ae.printStackTrace();
		} 

	}
	
	public void Control_Mouse(Robot robot)
	{
		// 마우스 이동
		robot.mouseMove(250, 250);

		// 버튼 클릭 -- 현재는 더블 클릭
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

//		// 마우스 오른쪽 버튼 클릭
//		robot.mousePress(InputEvent.BUTTON3_MASK);
//		robot.mouseRelease(InputEvent.BUTTON3_MASK);
		
//		// 마우스 휠 버튼 클릭
//		robot.mousePress(InputEvent.BUTTON2_MASK);
//		robot.mouseRelease(InputEvent.BUTTON2_MASK);
		
//		// 마우스 드래그
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseMove(500, 500);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
	}
	
	public void Control_Keyboard(Robot robot)
	{
		String key_input = new String("hello");
		byte[] str_input = key_input.getBytes();

		// 문자 "Hello" 입력
		for (int i = 0; i < str_input.length; i++) {

			int code = str_input[i];

			// Robot 클래스는 아스키 코드값이 대문자만 제공함.
			if (code > 96 && code < 123)
				code = code - 32;
			robot.keyPress(code);
			robot.keyRelease(code);
			// 현재 해당 쓰레드를 200ms 동안 sleep시킨다.
			robot.delay(200);
		}
		
		
		// 컨트롤 입력 (Ctrl+A)
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_A);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		robot.keyRelease(KeyEvent.VK_A);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TestMain();

	}

}
