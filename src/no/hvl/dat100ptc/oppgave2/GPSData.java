package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	   // سازنده کلاس که آرایهای از GPSPoint با اندازهی n میسازد
    public GPSData(int n) {
        gpspoints = new GPSPoint[n];
        antall = 0;
    }

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
    protected boolean insertGPS(GPSPoint gpspoint) {
        // بررسی اینکه آیا فضا برای اضافه کردن نقطه جدید وجود دارد
        if (antall < gpspoints.length) {
            gpspoints[antall] = gpspoint; // اضافه کردن نقطه در موقعیت فعلی
            antall++; // افزایش شمارنده برای موقعیت بعدی
            return true; // بازگشت true برای موفقیت در افزودن
        } else {
            return false; // بازگشت false در صورت پر بودن آرایه
        }
    }


    // متدی که دادههای متنی GPS را به شیء GPSPoint تبدیل کرده و آن را به آرایه اضافه میکند
    public boolean insert(String time, String latitude, String longitude, String elevation) {
        int timeInSeconds = GPSDataConverter.toSeconds(time); // تبدیل زمان به ثانیهها
        double lat = Double.parseDouble(latitude);  // تبدیل عرض جغرافیایی به double
        double lon = Double.parseDouble(longitude); // تبدیل طول جغرافیایی به double
        double elev = Double.parseDouble(elevation); // تبدیل ارتفاع به double
        
        // ایجاد یک شیء جدید GPSPoint با مقادیر تبدیل شده
        GPSPoint gpspoint = new GPSPoint(timeInSeconds, lat, lon, elev);
        
        // افزودن شیء gpspoint به آرایه با استفاده از insertGPS
        return insertGPS(gpspoint);
    }

    // متدی که اطلاعات نقاط GPS ذخیره شده را چاپ میکند
    public void print() {
        System.out.println("====== GPS Data - START ======");
        
        // پیمایش از طریق آرایه و چاپ اطلاعات نقاط GPS
        for (int i = 0; i < antall; i++) {
            System.out.println((i + 1) + " " + gpspoints[i].toString());
        }
        
        System.out.println("====== GPS Data - SLUTT ======");
    }
}
