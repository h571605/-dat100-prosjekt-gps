package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;

		// TODO
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;

	}

	public double totalElevation() {

		double elevation = 0;

		// TODO 
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double elevationChange = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			if (elevationChange > 0) {
				elevation += elevationChange;
			}
		}
		return elevation;
		
	}

	public int totalTime() {

		// TODO
		int startTime = gpspoints[0].getTime();
		int endTime = gpspoints[gpspoints.length - 1].getTime();
		return endTime - startTime;
		
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		// TODO
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double distance = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			int time = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			speeds[i] = calculateSpeed(distance, time);
		}
		return speeds;
	}
	
	public double maxSpeed() {
		
		// TODO 
		double[] speeds = speeds();
		double maxSpeed = 0;
		for (double speed : speeds) {
			if (speed > maxSpeed) {
				maxSpeed = speed;
			}
		}
		return maxSpeed;
	
	}

	private double calculateSpeed(double distance, int time) {
		if (time > 0) {
			return (distance / 1000) * 3.6;
		} else {
			return 0;
		}
	}

	public double averageSpeed() {
		
		// TODO
		double totalDistance = totalDistance() / 1000;
		double totalTime = totalTime() / 3600.0;
		return totalDistance / totalTime;
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		// TODO 
		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph >= 10 && speedmph < 12) {
			met = 6.0;
		} else if (speedmph >= 12 && speedmph < 14) {
			met = 8.0;
		} else if (speedmph >= 14 && speedmph < 16) {
			met = 10.0;
		} else if (speedmph >= 16 && speedmph < 20) {
			met = 12.0;
		}
		kcal = met * WEIGHT * secs / 3600;
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO 
		double[] speeds = speeds();

		for (int i = 0; i < speeds.length; i++) {
			int secs = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			totalkcal += kcal(weight, secs, speeds[i]);
		}
		return totalkcal;
	}

	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		// TODO 
		double totalDistance = totalDistance() / 1000; // km
		double totalElevation = totalElevation(); // m
		double maxSpeed = maxSpeed(); // km/h
		double averageSpeed = averageSpeed(); // km/h
		int totalTime = totalTime(); // s
		double totalKcal = totalKcal(WEIGHT); // kcal

		System.out.println("==============================================");
		System.out.println("Total time         : " + GPSUtils.formatTime(totalTime));
		System.out.println("Total distance     : " + GPSUtils.formatDouble(totalDistance) + " km");
		System.out.println("Total elevation    : " + GPSUtils.formatDouble(totalElevation) + " m");
		System.out.println("Max speed          : " + GPSUtils.formatDouble(maxSpeed) + " km/t");
		System.out.println("Average speed      : " + GPSUtils.formatDouble(averageSpeed) + " km/t");
		System.out.println("Energy             : " + GPSUtils.formatDouble(totalKcal) + " kcal");
		System.out.println("==============================================");
	}

}
