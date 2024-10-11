package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		// TODO
	    // مثال: "2017-08-13T08:52:26.000Z"
	    // استخراج بخش زمان از رشته
	    String timePart = timestr.substring(11, 19); // "08:52:26"
	    
	    // تقسیم رشته به ساعت، دقیقه و ثانیه
	    String[] parts = timePart.split(":");
	    int hours = Integer.parseInt(parts[0]);    // ساعت: 08
	    int minutes = Integer.parseInt(parts[1]);  // دقیقه: 52
	    int seconds = Integer.parseInt(parts[2]);  // ثانیه: 26
	    
	    // تبدیل به ثانیهها: ساعتها به ثانیه، دقیقهها به ثانیه، و افزودن ثانیهها
	    int totalSeconds = hours * 3600 + minutes * 60 + seconds;
	    return totalSeconds;
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		GPSPoint gpspoint;

		// TODO 
	    // تبدیل زمان به ثانیهها
	    int timeInSeconds = toSeconds(timeStr);
	    
	    // تبدیل عرض جغرافیایی به double
	    double latitude = Double.parseDouble(latitudeStr);
	    // تبدیل طول جغرافیایی به double
	    double longitude = Double.parseDouble(longitudeStr);
	    // تبدیل ارتفاع به double
	    double elevation = Double.parseDouble(elevationStr);
	    
	    // ساخت شیء GPSPoint با استفاده از مقادیر تبدیل شده
	    GPSPoint gpsPoint = new GPSPoint(timeInSeconds, latitude, longitude, elevation);
	    
	    return gpsPoint;
		
	}
	
}
