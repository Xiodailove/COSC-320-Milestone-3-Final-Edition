
import java.util.*;

public class USC {

	private PriorityQueue<Node> frontier = new PriorityQueue<>();
	private int ver;// vertex numbers
	ArrayList<LinkedList<Node>> Map = new ArrayList<LinkedList<Node>>();
	ArrayList<Double> distances; // Store cost of 1 node to other node.
	HashSet<Node> settled; // HashSet to store the destination vertex in it.
	
	public USC() {

	}
	
	// Constructor will initialize all variable like Map, distances, settled..
	public USC(int ver, ArrayList<LinkedList<Node>> Map) { // load map
		this.ver = ver;
		this.Map = Map;
		this.distances = new ArrayList<Double>();
		settled = new HashSet<Node>();
	}
	
	//search method will find path between initialState and finalState with minimum cost.
	public List<Node> search(int initialState, int finalState) {
		
		List<Node> results = new ArrayList<>();
        int source = initialState; // source vertex
        int destination = finalState; // destination vertex
 
        for (int i = 0; i < ver; i++){
        	distances.add(Double.MAX_VALUE); // initialize distances with MAXIMUM value
        }
        
        distances.set(source, 0.0);
        
        for (Node n : Map.get(initialState)) { // initial frontier
			Node x = new Node(n.getIndex(), n.getCost(), initialState + "->" + n.getIndex());
			frontier.add(x);
			distances.set(n.getIndex(), n.getCost());
		}
        while (!frontier.isEmpty()){ // check if priority queue is empty or not.
            Node evaluationNode = frontier.poll();
            if (evaluationNode.nodeIndex == destination) {
				results.add(evaluationNode);
				Node newNode = frontier.peek(); // take first node from priority queue and check for its adjacent
				while (newNode != null && newNode.nodeIndex == destination && newNode.getCost() == evaluationNode.getCost()) {
					results.add(frontier.poll());
					newNode = frontier.peek();
				}
				return results;
			}
            settled.add(evaluationNode); // add visited node in settled HashSet.
            updateFrontier(evaluationNode); // update priority queue with new branch.
        }
        return null;
	}

	public void updateFrontier(Node branch) {
		for (Node n : Map.get(branch.getIndex())) {  // find new branch and add it to priority queue
			Node x = new Node(n.getIndex(), branch.getCost() + n.getCost(),branch.path + " -> " + n.getIndex());
			frontier.add(x);
		}
	}

	public void userInput(int s, int e) { 
		// this method will take initialState and finalState
		// call search method to get the minimum cost path.
		// print the total cost of path.
		List<Node> results = this.search(s, e);
		for (Node result : results) {
			System.out.println("Total Cost: " + result.getCost() + "\n" + "Total Path: " + result.path);
		}
	}

}
