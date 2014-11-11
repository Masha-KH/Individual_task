public class CreateRoom {
	protected int allR, allP;
	protected int P1;
	protected int P2;
	protected int P3;
	protected String name;
	CreateRoom(int all, int p1, int p2, int p3, String name){
		this.allR = all;
		P1 = p1;
		P2 = p2;
		P3 = p3;
		allP = p1 + p2*2 + p3*3;
		this.name = name;
	}
}