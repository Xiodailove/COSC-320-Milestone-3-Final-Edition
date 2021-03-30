import java.util.LinkedList;

public class RandomMapGenerator {
    LinkedList<Node>[] PathCostGraph;
    int size;


    public RandomMapGenerator(int size){
        //notice that this is merely a graph of costs, so we do not have to obey any kind of triangular inequality
        PathCostGraph = new LinkedList[size];
        for(int i = 0 ; i< size ; i++){//now we initialize an array of linkedlists
            PathCostGraph[i] = new LinkedList<Node>();
            PathCostGraph[i].add(getRandomCostNode(i,80,0,0));
        }
        for(int i = 0; i< size; i++){
            int pair = i == size-1 ? 0 : i+1;
            //we want to make sure there would be a sol'n
            //namely the path between k-1, k, k+1 for all k in {0..size}
            formEdge(i,pair);
            //and three random edges
            for(int j = 0; j<3; j++)
                formEdge(i,(int)(Math.random()*size));
        }//so the head of each linkedlist represents a city, the rest illustrate the available path

    }

    public void formEdge(int index1, int index2){
        //notice that there is at MOST one undirect edge between two nodes
        //and there would be no loop (it would be awkward if it does)
        //so we will make sure that it holds using the following for loop
        for(Node i: PathCostGraph[index1])
            if(i.getIndex() == index2)
                return;
        PathCostGraph[index1].add(grcS(index2));
        PathCostGraph[index2].add(grcS(index1));
    }

    public Node getRandomCostNode(int index, int range1, int range2, int range3){//we generate a random cost node
        return new Node(index,rwr(range1),rwr(range2),rwr(range3));
    }

    public Node grcS(int index){//simple version of getRandomCostNode
        int range = (int)(50 + Math.random()*100);
        return getRandomCostNode(index, range,0,0);
    }

    public double rwr(double range){//random number with a predefined range
        if(range == 0)
            return 0;
        else
            return range+ Math.random()*range;
    }

    public LinkedList<Node> getList(){
        return null;
    }

    public String toString(){
        //we would like to print the graph
        String r = "";
        for(LinkedList<Node> n :PathCostGraph){
            for(Node i: n)
                r += i.getIndex()+", ";
            r+="\n";
        }
        return r;
    }
    
}
