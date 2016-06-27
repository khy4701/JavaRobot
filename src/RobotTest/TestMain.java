package RobotTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

public class TestMain {

	public TestMain() {
		
	

		try {
			// notepad ���α׷� Ȱ��ȭ
			
			Robot robot = new Robot();
		
			Control_Mouse(robot);
			
			// �ð� ����
			robot.delay(1000);
			
			// �޸��� ����
			// Runtime.getRuntime().exec("notepad");
			
			Control_Keyboard(robot);
			

		} catch (AWTException ae) {
			ae.printStackTrace();
		} 

	}
	
	public void Control_Mouse(Robot robot)
	{
		// ���콺 �̵�
		robot.mouseMove(250, 250);

		// ��ư Ŭ�� -- ����� ���� Ŭ��
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

//		// ���콺 ������ ��ư Ŭ��
//		robot.mousePress(InputEvent.BUTTON3_MASK);
//		robot.mouseRelease(InputEvent.BUTTON3_MASK);
		
//		// ���콺 �� ��ư Ŭ��
//		robot.mousePress(InputEvent.BUTTON2_MASK);
//		robot.mouseRelease(InputEvent.BUTTON2_MASK);
		
//		// ���콺 �巡��
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseMove(500, 500);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
	}
	
	public void Control_Keyboard(Robot robot)
	{
		String key_input = new String("hello");
		byte[] str_input = key_input.getBytes();

		// ���� "Hello" �Է�
		for (int i = 0; i < str_input.length; i++) {

			int code = str_input[i];

			// Robot Ŭ������ �ƽ�Ű �ڵ尪�� �빮�ڸ� ������.
			if (code > 96 && code < 123)
				code = code - 32;
			robot.keyPress(code);
			robot.keyRelease(code);
			// ���� �ش� �����带 200ms ���� sleep��Ų��.
			robot.delay(200);
		}
		
		
		// ��Ʈ�� �Է� (Ctrl+A)
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
