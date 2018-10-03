package maze;

import java.util.ArrayList;

public class MapSolver {
	
	static int steps = 0;
	static Map map;
	static boolean solution = false;
	
	public static ArrayList<String> visited = new ArrayList<String>();
	public static ArrayList<String> wrong = new ArrayList<String>();

	
	public static void solveMap(Map map) {
		MapSolver.map = map;
		
		int startX = map.startX();
		int startY = map.startY();
		
		solve(startX, startY, 1);
		steps = 0;
		if(!solution) visited.clear();
		solve(startX, startY, 2);
		steps = 0;
		if(!solution) visited.clear();
		solve(startX, startY, 3);
		steps = 0;
		if(!solution) visited.clear();
		solve(startX, startY, 4);
	}
	
	public static void solve(int x, int y, int dir) {
		
		if(solution) {
			return;
		}
		steps++;
		
		Block b = map.getBlock(x, y);
		
		if(b instanceof GoalBlock) {
			System.out.println(steps-1);
			solution=true;
			visited.add(x + "," + y);
			return;
		}
		else if(b == null){
			System.out.println("null bre");
			return;
		}
		else if (b instanceof FullBlock) {
			steps--;
			System.out.println("-");
			return;
		}
		else if(visited.contains(x + "," + y)) {
			steps--;
			return;
		}
		else {
			visited.add(x + "," + y);
		}
		
		if(dir == 1) System.out.println("Upp");
		if(dir == 2) System.out.println("Höger");
		if(dir == 3) System.out.println("Ner");
		if(dir == 4) System.out.println("Vänster");
		
		if(dir == 1) {
			solve(x, y - 1,1);
			if (!solution) solve(x+1, y,2);
			if (!solution) solve(x-1, y,4);
		}
		if(dir == 2) {
			solve(x + 1, y, 2);
			if (!solution) solve(x, y-1,1);
			if (!solution) solve(x, y+1,3);
		}
		if(dir == 3) {
			solve(x, y + 1, 3);
			if (!solution) solve(x-1, y,4);
			if (!solution) solve(x+1, y,2);
		}
		if(dir == 4) {
			solve(x - 1, y, 4);
			if (!solution) solve(x, y+1,3);
			if (!solution) solve(x, y-1,1);
		}
	}
}
