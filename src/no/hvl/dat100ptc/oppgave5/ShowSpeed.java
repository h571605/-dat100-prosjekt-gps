package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN,y;
		
		double speedTable[] = gpscomputer.speeds();
		
		for (int i = 0; i < speedTable.length; i++) {
			
			setColor(0, 0, 255);
			
			int currentSpeed = (int) Math.round(speedTable[i]*5);
			
			drawLine(x + i * 2, ybase, x + i * 2, (ybase - currentSpeed) );
		}
		
		int avarageSpeed = (int) Math.round(gpscomputer.averageSpeed());
		
		setColor(0, 255, 0);
		drawLine(x / 2, (ybase - avarageSpeed*5) , x + speedTable.length * 2, (ybase - avarageSpeed*5));
	
		// TODO
		
	}
}
