import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HexArray {
  static class Hexagon {
		int cost;
		int position;
		boolean checked = false;
		Hexagon() {
			this.cost = 0;
		}
		Hexagon(int c) {
			this.cost = c;
		}
		public int getCost() {
			return this.cost;
		}
		public void setCost(int c) {
			this.cost = c;
		}
		Hexagon up = null;
		Hexagon upleft = null;
		Hexagon upright = null;
		Hexagon down = null;
		Hexagon downleft = null;
		Hexagon downright = null;
		
		int weight_up = -1; //values of -1 are "unset"
		int weight_upleft = -1;
		int weight_upright = -1;
		int weight_down = -1;
		int weight_downleft = -1;
		int weight_downright = -1;
	}
	
	Hexagon[] hex_array = new Hexagon[233];
	Hexagon start;
	Hexagon end;
	Hexagon current;
	ArrayList<Integer> leftedge = new ArrayList<Integer>();
	ArrayList<Integer> rightedge = new ArrayList<Integer>();
	
	/**
	 * add descriptive variable names.
	 * @param fileName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	HexArray(String fileName) throws IOException, FileNotFoundException {
		//create arraylists of the indicies that lie on the left and right edges.
		for (int i = 0; i < 226; i+=15) { 
			leftedge.add(i);
			rightedge.add(i+7);
		}
		//actually populating the array with default Hexagons.
		for (int i = 0; i < 233; i++) { 
			hex_array[i] = new Hexagon();
			if (i == 225) {
				start = hex_array[i];
				current = start;
			}
			if (i == 7) {
				end = hex_array[i];
			}
		}
		//defining weights for each node from the supplied file.
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		//give each hexagon its respective values
		while ((line = br.readLine()) != null) { 
			StringTokenizer st = new StringTokenizer(line);
			int index = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			hex_array[index-1].setCost(cost);
			hex_array[index-1].position = index;
			//System.out.println("Assigning hexagon "+index+" with cost "+cost+".");
		}
		
			//defining each hexagon's (up to) 6 pointers and weights (weight of -1 is "unset")
		for (int i = 0; i < 233; i++) { 
			
			//Assigns up values and weight for all indices not in the top two rows.
			if (i-15 >= 0) {
				hex_array[i].up = hex_array[i-15];
				hex_array[i].weight_up = hex_array[i].up.cost;
			}
			
			//Assigns an upright value and weight for all indices not in the top row or right edge.
			if (i-7 > 0 && !rightedge.contains(i)) {
				hex_array[i].upright = hex_array[i-7];
				hex_array[i].weight_upright = hex_array[i].upright.cost;
			}
			
			//Assigns an upleft value and weight for all indices not in the top row or left edge.
			if (i-7 > 0 && !leftedge.contains(i)) {
				hex_array[i].upleft = hex_array[i-8];
				hex_array[i].weight_upleft = hex_array[i].upleft.cost;
			}
			
			//Assigns a down value and weight for all indices not in the bottom two rows.
			if (i+15 <= 232) {
				hex_array[i].down = hex_array[i+15];
				hex_array[i].weight_down = hex_array[i].down.cost;
			}
		
			//Assigns a downright value and weight for all indices not in the bottom row or right edge.
			if (i+8 <= 232 && !rightedge.contains(i)) {
				hex_array[i].downright = hex_array[i+8];
				hex_array[i].weight_downright = hex_array[i].downright.cost;
			}
			
			//Assigns a downleft value and weight for all indices not in the bottom row or left edge.
			if (i+8 <= 232 && !leftedge.contains(i)) {
				hex_array[i].downleft = hex_array[i+7];
				hex_array[i].weight_downleft = hex_array[i].downleft.cost;
			}
			
		}
		br.close();
	}	
}
