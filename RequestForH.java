public class RequestForH implements Runnable{
	protected String Surname;
	protected int timeToLive;
	protected String classOfRoom;
	protected int numOfP;
	boolean settleB;
	protected boolean waitNotify;
	protected Thread thR;
	protected boolean variableOfTypeRoom;
	protected Hotel hotel;
	protected int whereLive;
	protected int[] whichRoom;
	protected int secTime;

	RequestForH(Hotel h, String s, int time, String clR, int num, boolean v){
		hotel = h;
		Surname = s;
		timeToLive = time;
		classOfRoom = clR;
		numOfP = num;
		variableOfTypeRoom = v;
		waitNotify = false;
		settleB = false;
		secTime = 0;
		thR = new Thread(this);
		thR.start();
	}
	public synchronized void thNotify(int i){
		if (i == 0){
			waitNotify = false;
			notify();
		}
		else
			waitNotify = true;
		
	}
	public synchronized void run(){
		try {
			while(!settleB){
				hotel.InHotel(this);
				synchronized(this){
					while(waitNotify){
						wait();
					}
				}
			}
			thR.sleep(timeToLive);
			hotel.OutHotel(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
