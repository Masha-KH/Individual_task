public class TryToSettle {

	public static void main(String[] args) throws InterruptedException {
		Hotel hoteL = new Hotel();;
		
		new RequestForH(hoteL, "asdf", 4500, "econom", 5, false);
		Thread.sleep(100);
		new RequestForH(hoteL, "qwerty", 5000, "econom", 13, false);
		Thread.currentThread().sleep(20);
		new RequestForH(hoteL, "qw", 200, "lux", 6, false);
		new RequestForH(hoteL, "ghj", 50, "econom", 5, false);
		Thread.currentThread().sleep(50);
		new RequestForH(hoteL, "nbv", 600, "standart", 5, false);
		Thread.currentThread().sleep(150);
		new RequestForH(hoteL, "iop", 500, "standart", 2, false);
		Thread.currentThread().sleep(20);
		new RequestForH(hoteL, "mnb", 1000, "econom", 2, false);
		Thread.currentThread().sleep(75);
		new RequestForH(hoteL, "zxcvb", 200, "lux", 1, false);
	}

}
