package laba5;
public class Vector {
	
	public double[] arr;
	public int size;
	public int capacity;
	public Vector() {
		arr = new double[16];
		size = 0;
		capacity = 16;
	}
	public Vector(int capac) {
		if (size==0) {
			arr = new double[capac];
			capacity = capac;
		}
	}
	public Vector(double[] arrOfDouble) {
		capacity = arrOfDouble.length;
		arr = new double[capacity];
		arr = arrOfDouble;
		size = capacity;
	}
	public int getSize() {
		return size;
	}
	public int getCapacity() {
		return capacity;
	}
	public double get(int p) {
		return arr[p];
	}
	
	public void set(double val, int pos) {
		if (pos<capacity-1) {
			arr[pos] = val;
		}		
	}
	public void insert(double val, int pos) {		
		if ((pos>capacity-2)||(capacity==size)) {
			double[] arr2 = new double[capacity*2];	
			for (int i = 0; i<size; i++)
				arr2[i] = arr[i];
			capacity *= 2;
			arr = new double[capacity];
			arr = arr2.clone();
			for (int i = 0; i<size; i++)
				arr[i] = arr2[i];
			arr2 = null;
		}
		if (pos<size) {
			for (int i = size-1; i>=pos; i--) 
					arr[i+1] = arr[i];  
			arr[pos] = val;
		}
		else 
			arr[pos] = val;
		++size;
	}
	public void erase(int pos) {
		for (int i=pos; i<capacity-2; i++) {
			arr[i]=arr[i+1];
		}
		//double[] arr2 = new double[capacity-1];
		//arr2 = arr.clone();
		//--capacity;
		--size;
		//arr = new double[capacity];
		//for (int i=0; i<capacity; i++) {
		//	arr[i] = arr2[i];
		//}
	}
	protected void clear() {
		arr = null;
		size = 0;
		capacity = 0;
	}
	public void print() {
		if (size==0) {
			System.out.println("Empty");
		}
		else {
			for (int i = 0; i<size; i++) 
				System.out.print(arr[i] + " ");
			System.out.println();
		}
	}
	public  Vector clone() {
		Vector v2 = new Vector();		
		v2.capacity = arr.length;
		v2.size = capacity;
		v2.arr = new double[capacity];
		for (int i = 0; i<size; i++)
			v2.arr[i] = arr[i];
		return v2;
	}
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
				
		Stack s1 = new Stack(), s2 = new Stack();
		Queue q = new Queue();
		final int N = 10000;

		// push
		for(int i=0; i<N; i++){
		    s1.push(i);
		    q.enqueue(i);
		}

		// reverse
		for(int i=0; i<N; i++){
		    s2.push(s1.pop());
		}

		// check
		for(int i=0; i<N; i++){
		   assert(s2.pop() == q.dequeue());
		}

		System.out.println( "OK" );

		
		double timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("программа выполнялась " + timeSpent/1000 + " секунд");
	}

}

class Stack extends Vector{
 
	Stack() {
		super();
	}
	void push(double val) {
		super.insert(val,0);
	}
	double pop() {
		double temp = super.arr[0];
		super.erase(0);
		return temp;		
	}
	public int getSize() {
		return super.getSize();
	}
	public void clear(){
		super.clear();
	}	
}

class Queue extends Vector { 
	Queue() {
		super();
	}
	void enqueue(double val) {
		super.insert(val, size);
	}
	double dequeue() {
		double temp = super.arr[0];
		super.erase(0);
		return temp;
	}
	public int getSize() {
		return super.getSize();
	}
	public void clear() {
		super.clear();
	}
}-