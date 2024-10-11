package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		GPSPoint myObj = new GPSPoint(1, 2.0, 3.0, 5.0);
		System.out.println(myObj.getTime());
		myObj.setTime(2);
		System.out.println(myObj.getTime());
		
		System.out.println(myObj.toString());
	}

}
