package MacroFullVersion;

import MacroFullVersion.Keyboard.*;
import MacroFullVersion.Mouse.*;
import MacroFullVersion.Util.*;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import MacroFullVersion.Keyboard.KeyboardListen;
import MacroFullVersion.Mouse.MouseListen;
import MacroFullVersion.Mouse.MouseMoveListen;
import MacroFullVersion.Util.EventHandle;
import MacroFullVersion.Util.PrintTest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ScreenControl implements Initializable {

	@FXML
	private Button btnStart;
	@FXML
	private Button btnPlay;
	@FXML
	private Button btnStop;
	@FXML
	private TextField txtStr;

	private ArrayList<EventHandle> arrayList;

	private KeyAction k_action;
	private KeyboardListen k_listen;
	private MouseListen m_listen;

	private Robot robot;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btnStart.setOnAction(event -> ActionStart());
		btnStop.setOnAction(event -> ActionStop());
		btnPlay.setOnAction(event -> ActionPlay());

		btnStart.setDisable(false);
		btnStop.setDisable(true);
		btnPlay.setDisable(true);
		arrayList = new ArrayList<EventHandle>();

	}

	public void ActionStart() {
		btnStart.setDisable(true);
		btnStop.setDisable(false);
		btnPlay.setDisable(true);

		if (!arrayList.isEmpty()) {
			arrayList.clear();
			arrayList = new ArrayList<EventHandle>();
		}


		System.out.println("Press Start.");

		try {
			GlobalScreen.registerNativeHook();

		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		k_listen = new KeyboardListen(arrayList);
		GlobalScreen.addNativeKeyListener(k_listen);

		m_listen = new MouseListen(arrayList);
		GlobalScreen.addNativeMouseListener(m_listen);
		GlobalScreen.addNativeMouseMotionListener(m_listen);
		m_listen.MoveListenStart();
		
	

	}

	public void ActionStop() {
		btnStart.setDisable(false);
		btnStop.setDisable(true);
		btnPlay.setDisable(false);

		System.out.println("Stop");

		// Global Key Listener( Keyboard, Mouse ) 제거
		GlobalScreen.removeNativeKeyListener(k_listen);
		GlobalScreen.removeNativeMouseListener(m_listen);
		GlobalScreen.removeNativeMouseMotionListener(m_listen);
		
		m_listen.MoveListenStop();
		//
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ActionPlay() {
		btnStart.setDisable(false);
		btnStop.setDisable(false);
		btnPlay.setDisable(false);

		synchronized (arrayList) {
			try {
				robot = new Robot();
				k_action = new KeyAction(robot);

				boolean drag_status = false;
				for (EventHandle i : arrayList) {
					
					if (i.getEventName().equals("MouseClicked")) {
						Point pos = i.getPosition();
						robot.mouseMove((int) pos.getX(), (int) pos.getY());
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);

						
					} else if (i.getEventName().equals("MouseMoved")) {
						Point pos = i.getPosition();
						robot.mouseMove((int) pos.getX(), (int) pos.getY());
						
						
					} else if (i.getEventName().equals("MouseDragged"))
					{
						Point pos = i.getPosition();
										
						if(!drag_status)
						{
							robot.mousePress(InputEvent.BUTTON1_MASK);
							drag_status = true;
						}
						robot.mouseMove((int) pos.getX(), (int) pos.getY());
						
					} else if (i.getEventName().equals("MouseReleased"))
					{
						drag_status =false;
						robot.mouseRelease(InputEvent.BUTTON1_MASK);						
						
					} else if (i.getEventName().equals("KeyPressed")) {

						k_action.KeyPressed( i.getPressKeyStr());

						
						
					} else if (i.getEventName().equals("KeyReleased")) {

						k_action.KeyReleased(i.getPressKeyStr());
					}
					
					try {
						if( drag_status )
							Thread.sleep(10);  // 드래그 속도
						else Thread.sleep(80);  // 마우스 속도.
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				System.out.println("Play.");

			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
