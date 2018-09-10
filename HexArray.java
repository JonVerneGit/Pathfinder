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
}
