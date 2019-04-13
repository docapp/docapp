package es.upm.dit.isst.webLab.model;

import java.util.HashMap;
import java.util.Map;

import java.util.Collection;

public class TimeSlot {
	public HashMap<Integer, String> daySlots = new HashMap<Integer, String>();
	
	public TimeSlot() {
		//Add time slots
		daySlots.put(0, "9:00-9:30");
		daySlots.put(1, "9:30-10:00");
		daySlots.put(2, "10:00-10:30");
		daySlots.put(3, "10:30-11:00");
		daySlots.put(4, "11:00-11:30");
		daySlots.put(5, "11:30-12:00");
		daySlots.put(6, "12:00-12:30");
		daySlots.put(7, "12:30-13:00");
		daySlots.put(8, "13:00-13:30");
		daySlots.put(9, "13:30-14:00");
	}

	public HashMap<Integer, String> getDaySlots() {
		return daySlots;
	}

	public void setDaySlots(HashMap<Integer, String> daySlots) {
		this.daySlots = daySlots;
	}
	
	public HashMap<Integer, String> getAvailableTimeSlots(Collection<Appointment> chosen){
		
		for(Appointment a : chosen) {
			this.daySlots.remove(a.getStart_time());
		}
		return this.daySlots;
	}
	
	public Integer getTimeSlotKey(String time) {
		HashMap<String, Integer> v2k = new HashMap<String, Integer>();
		v2k.put("9:00-9:30", 0);
		v2k.put("9:30-10:00", 1);
		v2k.put("10:00-10:30", 2);
		v2k.put("10:30-11:00", 3);
		v2k.put("11:00-11:30", 4);
		v2k.put("11:30-12:00", 5);
		v2k.put("12:00-12:30", 6);
		v2k.put("12:30-13:00", 7);
		v2k.put("13:00-13:30", 8);
		v2k.put("13:30-14:00", 9);
		return v2k.get(time);
	}
	
}
