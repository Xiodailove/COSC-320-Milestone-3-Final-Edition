import java.util.*;

public class FlightNetwork {
   public static void main(String args[]){
      ArrayList<LinkedList<Node>> FlightMap = new ArrayList<LinkedList<Node>>();
      
      //Sample Map for testing
      FlightMap.add(new LinkedList<Node>()); // empty LinkedList for node 0
      FlightMap.add(new LinkedList<Node>());
      FlightMap.add(new LinkedList<Node>());
      FlightMap.add(new LinkedList<Node>());
      FlightMap.add(new LinkedList<Node>());
      
      Node n0 = new Node(0, 40, "0"); // node 0
      Node n1 = new Node(1, 50, "1"); // node 1
      Node n2 = new Node(2, 70, "2"); // node 2
      Node n3 = new Node(3, 90, "3"); // node 3
      Node n4 = new Node(4, 20, "4"); // node 4

      FlightMap.get(0).add(n1); // add edge between node 0 and node 1
      FlightMap.get(1).add(n0);
      FlightMap.get(1).add(n2);
      FlightMap.get(2).add(n1);
      FlightMap.get(2).add(n3);
      FlightMap.get(3).add(n2);
      FlightMap.get(3).add(n4);
      FlightMap.get(4).add(n3);
      FlightMap.get(4).add(n0);
      FlightMap.get(0).add(n4);
    
      ArrayList<LinkedList<Node>> arr = RandomMapGenerator(3500); // taking Map array of LinkedList from RandomMapGenerator
      
      long start = System.currentTimeMillis(); // start time before algorithm started.
      AlgorithmAforFlightNetwork(arr, arr.get(0).get(0), arr.get((arr.size()*3)/4).get(0)); // call Algorithm to get the minimum cost path.
      long end = System.currentTimeMillis(); // end time after algorithm finishes.
      
      long elapsedTime = end - start;
      System.out.println(elapsedTime); // print total time taken during algorithm execution.
   } 

   public static void AlgorithmAforFlightNetwork(ArrayList<LinkedList<Node>> FlightMap, Node ini, Node fin){
	   // create object of call uniform cost search and pass size and Map parameter.
	   // Call userInput method of USC class to find minimum cost path.
	   USC TEST = new USC(FlightMap.size(), FlightMap);
	   TEST.userInput(ini.getIndex(), fin.getIndex());
	   System.out.println();
   }

   public static ArrayList<LinkedList<Node>> RandomMapGenerator(int size){
	   // this method will generate random Map for testing algorithm.
	   // create random map object and taken PathCostGraph (Array of LinkedList) and assign to Map.
	   // return Map.
	   RandomMapGenerator map = new RandomMapGenerator(size);
	   LinkedList<Node>[] array = map.PathCostGraph;
	   ArrayList<LinkedList<Node>> Map = new ArrayList<LinkedList<Node>>();
	   for(int i=0; i<array.length;i++) {
		   Map.add(array[i]);
	   }
	   return Map;
   }
   
}
