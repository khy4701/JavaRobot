package MacroFullVersion.Util;

import java.awt.Point;

public class EventHandle {
	
	private String event_name;
	private Point position;
	private String key_str;
	
	public EventHandle(String event_name, Point position)
	{
		this.event_name = event_name;
		this.position = position;
	}
		
	public EventHandle(String event_name, String key_str)
	{
		this.event_name = event_name;
		this.key_str = key_str;
	}
	
	public String getEventName()
	{
		return event_name;
	}

	public Point getPosition()
	{
		return position;
	}
	
	public String getPressKeyStr()
	{
		return key_str;
	}
}
