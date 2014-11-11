import java.util.ArrayList;

public class Hotel {
	protected int allRoom = 22;
	protected int allPeople = 50;
	protected CreateRoom economRoom = new CreateRoom(7, 2, 5, 2, "econom");
	protected CreateRoom standartRoom = new CreateRoom(9, 3, 3, 3, "standart");
	protected CreateRoom luxRoom = new CreateRoom(6, 1, 2, 3, "lux");
	
	private ArrayList<RequestForH> hotelRoomer;				
	private ArrayList<String> roomerList;   
	Hotel(){
		hotelRoomer = new ArrayList<RequestForH>();
		roomerList = new ArrayList<String>();
	}
	public int[] Allocation(RequestForH r, CreateRoom type){
		RequestForH rr = r;
		int[] rooms = {0,0,0};
		rooms[0] = rr.numOfP/3;		
		while (true){
			if (rooms[0] >= type.P3){
				--rooms[0];
			}  else
				break;				
		}		
		rr.numOfP -= rooms[0]*3;
		rooms[1] = rr.numOfP/2;
		while (true){
			if (rooms[1] >= type.P2){
				--rooms[1];
			}  else
				break;				
		}	
		rr.numOfP -= rooms[1]*2;
		rooms[2] = rr.numOfP;
		return rooms;
	}
	
	public void DecRoom(CreateRoom t, int[] temp){
		t.P1 -= temp[0];
		t.P2 -= temp[1];
		t.P3 -= temp[2];
		t.allP -= (temp[0] + temp[1]*2 + temp[2]*3);
		t.allR -= (temp[0] + temp[1] + temp[2]);
	}
	public void IncRoom(CreateRoom t, int[] temp){
		t.P1 += temp[0];
		t.P2 += temp[1];
		t.P3 += temp[2];
		t.allP += (temp[0] + temp[1]*2 + temp[2]*3);
		t.allR += (temp[0] + temp[1] + temp[2]);
	}
	public boolean Settle(RequestForH r, int j){
		boolean trueSet = false;
		if ((r.numOfP > allRoom)){
			trueSet = false;
		} else{
			switch (r.classOfRoom){ 
			case "econom":{
				if ((economRoom.allP<r.numOfP)&&(r.variableOfTypeRoom == false)){
					trueSet = false;
				}
				else{
					int[] ec = Allocation(r, economRoom);
					if (ec[2] > economRoom.P1){
						trueSet = false;
					}
					else {
						trueSet = true;
						if (j == 1){
							DecRoom(economRoom, ec);
							r.whereLive = 1;
							r.whichRoom = ec;
						}
					}
				}				
				break;
			}
			case "standart":{
				if ((standartRoom.allP<r.numOfP)&&(r.variableOfTypeRoom == false)){
					trueSet = false;
				}
				else{
					int[] st = Allocation(r, standartRoom);
					if (st[2] > standartRoom.P1){
						trueSet = false;
						if (r.variableOfTypeRoom == true){
							int[] ec = Allocation(r, economRoom);
							if (ec[2] > economRoom.P1){
								trueSet = false;
							} else {
								trueSet = true;
								if (j == 1){
									DecRoom(economRoom, ec);
									r.whereLive = 1;
									r.whichRoom = ec;
								}
							}
						}					
					} else {
						trueSet = true;
						if (j == 1){
							DecRoom(standartRoom, st);
							r.whereLive = 2;
							r.whichRoom = st;
						}
					}
				}
				break;
			}
			case "lux":{
				if ((luxRoom.allP<r.numOfP)&&(r.variableOfTypeRoom == false)){
					trueSet = false;
				}
				else{
					int[] l = Allocation(r, luxRoom);
					if (l[2] > economRoom.P1){
						trueSet = false;
						if (r.variableOfTypeRoom == true){
							int[] st = Allocation(r, standartRoom);
							if (st[2] > standartRoom.P1){
								trueSet = false;
								int[] ec = Allocation(r, economRoom);
								if (ec[2] > economRoom.P1){
									trueSet = false;
								} else {
									trueSet = true;
									if (j == 1){
										DecRoom(economRoom, ec);
										r.whereLive = 1;
										r.whichRoom = ec;
									}
								}
							} else {
								trueSet = true;
								if (j == 1){
									DecRoom(standartRoom, st);
									r.whereLive = 2;
									r.whichRoom = st;
								}
							}
						}					
					} else{
						trueSet = true;
						if (j == 1){
							DecRoom(luxRoom, l);
							r.whereLive = 3;
							r.whichRoom = l;
						}
					}
				}
				break;
			}
			}
		}
		return trueSet;
	}
	public synchronized boolean InHotel(RequestForH r){
		if (Settle(r, 0) == true){
			roomerList.add(r.Surname);			
			Settle(r, 1);					
			r.settleB = true;
			System.out.println("Поселился:  " + r.Surname);
			return true;
		}
		else{
			r.thNotify(1);
			if (r.secTime == 0)
				hotelRoomer.add(r);
			r.secTime++;
			System.out.println("В очереди:  " + r.Surname);
			return false;
		}
	}
	public synchronized void OutHotel(RequestForH r){
		roomerList.remove(r.Surname);
		
		if (r.whereLive == 1)
			IncRoom(economRoom, r.whichRoom);
		if (r.whereLive == 2)
			IncRoom(standartRoom, r.whichRoom);
		if (r.whereLive == 3)
			IncRoom(luxRoom, r.whichRoom);

		for (int i = 0; i<hotelRoomer.size(); i++){
			hotelRoomer.get(i).thNotify(0);
		}
		System.out.println("Выселился:  " + r.Surname);
	}
}
