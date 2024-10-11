package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {
		// TODO 

		double min = da[0];
		
		for (double number : da) {
			if(number < min) {
				min = number;
			} 
		}

		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		// TODO
		
		double[] newGpsPoints = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			double latidude = gpspoints[i].getLatitude();
			
			newGpsPoints[i] = latidude;
		}
		
		return newGpsPoints;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO
		
		double[] newGpsPoints = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			double longitude = gpspoints[i].getLongitude();
			
			newGpsPoints[i] = longitude;
		}
		
		return newGpsPoints;
	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		// TODO 

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = gpspoint1.getLatitude() * (Math.PI /180);
		longitude1 = gpspoint1.getLongitude() * (Math.PI /180);
		
		latitude2 = gpspoint2.getLatitude() * (Math.PI /180);
		longitude2 = gpspoint2.getLongitude() * (Math.PI /180);
		
		
		double latitudeDifferenceRadian = Math.abs((gpspoint2.getLatitude() - gpspoint1.getLatitude()) * (Math.PI /180));
		double longitudeDifferenceRadian = Math.abs((gpspoint2.getLongitude() - gpspoint1.getLongitude()) * (Math.PI /180));
		
		double a = Math.pow(Math.sin(latitudeDifferenceRadian / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(longitudeDifferenceRadian/2), 2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt((1 - a)));
		d = R * c;

		return d;

	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO 

	}

	private static double compute_c(double a) {

		
		throw new UnsupportedOperationException(TODO.method());
		
		
		// TODO 

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		// TODO

		int secs;
		double speed;
		
		double totalDistance = distance(gpspoint1, gpspoint2);
		
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		
		speed = totalDistance / secs;
		
		return speed;

	}

	public static String formatTime(int secs) {
		
		// TODO 

		String timestr;
		String TIMESEP = ":";
		
		int hours = secs / 3600;
		int min = (secs % 3600) / 60;
		int secLeft = (secs % 3600) % 60;
		
		timestr = String.format("%10s", String.format("%1$02d:%2$02d:%3$02d", hours, min, secLeft));
		
		return timestr;
	
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		// TODO
		
		String str;
		
		str = String.format("%10.2f", d);
		
		return str;
		
	}
}
