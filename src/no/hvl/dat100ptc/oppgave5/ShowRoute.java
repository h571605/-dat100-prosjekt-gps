package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		// TODO 
		
		int startPosx = 0;
		int endPosx = 0;
		
		int startPosy = 0;
		int endPosy = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			startPosx = (int) ((gpspoints[i-1].getLongitude() - minlon) * xstep);
	        startPosy = ybase - (int) ((gpspoints[i-1].getLatitude() - minlat) * ystep);
	        endPosx = (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        endPosy = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
			
			setColor(0, 255, 0);
			
			drawCircle(endPosx, startPosy, 5);
			drawLine(startPosx, startPosy, endPosx, endPosy);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		double totalDistance = gpscomputer.totalDistance() / 1000; // km
		double totalElevation = gpscomputer.totalElevation(); // m
		double totalTime = gpscomputer.totalTime(); // s
		double maxSpeed = gpscomputer.maxSpeed(); // km/h
		double averageSpeed = gpscomputer.averageSpeed(); // km/h
		double totalKcal = gpscomputer.totalKcal(80.0); // kcal


	    drawString("==============================================", 10, 20);
	    drawString(String.format("Total distanse: %.2f km", totalDistance / 1000), 10, 40);
	    drawString(String.format("Total høydestigning: %.2f m", totalElevation), 10, 60);
	    drawString(String.format("Total tid: %.2f s", totalTime), 10, 80);
	    drawString(String.format("Maks hastighet: %.2f km/t", maxSpeed), 10, 100);
	    drawString(String.format("Gjennomsnittshastighet: %.2f km/t", averageSpeed), 10, 120);
	    drawString(String.format("Forbrente kalorier: %.2f kcal", totalKcal), 10, 140);
	    drawString("==============================================", 10, 160);
		
	}

	public void replayRoute(int ybase) {
		setColor(0, 0, 255);

		// TODO 
		int startPosx = (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
		int endPosx = 0;
		
		int startPosy = (int) ((gpspoints[0].getLatitude() - minlat) * ystep);
		int endPosy = 0;
		
		int circle = fillCircle(startPosx, startPosy, 5);
		
		for (int i = 1; i < gpspoints.length; i++) {
			startPosx = (int) ((gpspoints[i-1].getLongitude() - minlon) * xstep);
	        startPosy = ybase - (int) ((gpspoints[i-1].getLatitude() - minlat) * ystep);
	        endPosx = (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        endPosy = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
			
	        moveCircle(circle, endPosx, endPosy);
		}
		
		
	}

}
