package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {
    public static void main(String[] args) {
        // ایجاد دو شیء GPSPoint با مقادیر دلخواه
        GPSPoint point1 = new GPSPoint(31946, 60.385390, 5.217217, 61.9);
        GPSPoint point2 = new GPSPoint(31950, 60.385500, 5.217300, 62.0);

        // ایجاد یک شیء GPSData با ظرفیت دو نقطه GPS
        GPSData gpsData = new GPSData(2);

        // اضافه کردن نقاط GPS به GPSData
        gpsData.insertGPS(point1);
        gpsData.insertGPS(point2);

        // چاپ اطلاعات نقاط GPS
        gpsData.print();
    }

    public void print() {
//    System.out.println("====== GPS Data - START ======");
//    for (int i = 0; i < antall; i++) {
//        System.out.println(gpspoints[i].toString()); // استفاده از toString برای نمایش اطلاعات
//    }
//    System.out.println("====== GPS Data - SLUTT ======");
}
}

