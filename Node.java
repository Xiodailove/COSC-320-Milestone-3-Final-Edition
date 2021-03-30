

public class Node implements Comparable<Node>{
	public int nodeIndex;
	public double monetaryCost;
	public double waitTimeCost;
	public double flightTimeCost;
	public String path;
	public double x1,x2,x3;//xi should be the coefficients for each costs 
	
	public Node(int nodeIndex, double monetaryCost, double waitTimeCost, double flightTimeCost){
		this(nodeIndex,monetaryCost,waitTimeCost,flightTimeCost,"");
	}

	public Node(int nodeIndex, double monetaryCost, double waitTimeCost, double flightTimeCost, String path){
	//we would use this constructor for most of the time
		this.nodeIndex = nodeIndex;
		setMonetaryCost(monetaryCost);
		setWaitTimeCost(waitTimeCost);
		setFlightTimeCost(flightTimeCost);
		this.path = path;
		setX1(1);
		setX2(0);
		setX3(0);
	}

	public int getIndex(){
		return nodeIndex;
	}

	public double getCost(){
		//this provides a cost to a node
		//p = x1*f1 + x2*f2 + x3*f3
		return x1*monetaryCost+x2*waitTimeCost+x3*flightTimeCost;
	}

	public void setX1(double x1){
		//notice that by definition the coefficient of monetary cost cannot be lower that 1
		if(x1<1) this.x1 = 1;
		else this.x1 = x1;
	}

	public void setX2(double x2){
		//the coefficient for the rest should be more than 0 by definition
		if(x2<0) this.x2 = 0;
		else this.x2 = x2;
	}

	public void setX3(double x3){
		if(x3<0) this.x3 = 0;
		else this.x3 = x3;
	}

	public Node(int nodeIndex, double monetaryCost, String path) {
		this(nodeIndex,monetaryCost,0,0,path);
	}

	public int compareTo(Node o) {
		if(this.getCost() < o.getCost()) {
			return -1;
		} else{
			return 1;
		} 
	}

	public boolean equals(Object obj) {
		Node x = (Node) obj;
		if(x.nodeIndex == this.nodeIndex && x.getCost() == this.getCost()) {
			return true;
		}
		return false;
	}

	private void setFlightTimeCost(double flightTimeCost2) {
		this.flightTimeCost = flightTimeCost2>0?flightTimeCost2:0;
	}

	private void setWaitTimeCost(double waitTimeCost2) {
		this.waitTimeCost = waitTimeCost2>0?waitTimeCost2:0;
	}

	private void setMonetaryCost(double monetaryCost2) {
		this.monetaryCost = monetaryCost2>0?monetaryCost2:0;
	}
}