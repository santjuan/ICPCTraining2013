import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class D 
{
	
	static class Scanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		public String nextLine() {
			try {
				return br.readLine();
			} catch (Exception e) {
				throw (new RuntimeException());
			}
		}

		public String next() {
			while (!st.hasMoreTokens()) {
				String l = nextLine();
				if (l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public void sortIntArray(int[] array) {
			Integer[] vals = new Integer[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortLongArray(long[] array) {
			Long[] vals = new Long[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortDoubleArray(double[] array) {
			Double[] vals = new Double[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
	}

	
	static int lastStones = 0;
	static char[][] board;
	static int[][] stones;
	
	static void stones(int st, boolean put)
	{
		int stIni = st;
		if(put)
		{
			stones(lastStones, false);
			for(int i = 0; st != 0; i++)
			{
				if((st & 1) == 1)
					board[stones[i][0]][stones[i][1]] = '*';
				st >>= 1;
			}
			lastStones = stIni;
		}
		else
		{
			for(int i = 0; st != 0; i++)
			{
				if((st & 1) == 1)
					board[stones[i][0]][stones[i][1]] = '.';
				st >>= 1;
			}
		}
	}
	
	static int nextI;
	static int nextJ;
	static int stonesTaken;
	
	static void nextValidMove(int i, int j, int deltaI, int deltaJ)
	{
		if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#')
			return;
		nextI = i;
		nextJ = j;
		if(board[i][j] == '*')
		{
			for(int k = 0; k < stones.length; k++)
			{
				if(stones[k][0] == i && stones[k][1] == j)
					stonesTaken |= 1 << k;
			}
		}
		nextValidMove(i + deltaI, j + deltaJ, deltaI, deltaJ);
	}
	
	static class Estado
	{
		int i;
		int j;
		int stones;
		
		public Estado(int i, int j, int stones) 
		{
			this.i = i;
			this.j = j;
			this.stones = stones;
		}
		
		
	}
	
	static int[][] diffs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static String[] moves = new String[]{"D", "U", "R", "L"};
	
	static String bfs(int startI, int startJ)
	{
		String[][][] best = new String[board.length + 1][board[0].length + 1][1 << stones.length];
		ArrayDeque <Estado> cola = new ArrayDeque <Estado> ();
		cola.add(new Estado(startI, startJ, (1 << stones.length) - 1));
		best[startI][startJ][(1 << stones.length) - 1] = "";
		String bestOverall = null;
		while(!cola.isEmpty())
		{
			Estado actual = cola.poll();
			String actualBest = best[actual.i][actual.j][actual.stones];
			if(actual.stones == 0)
			{
				if(bestOverall == null || bestOverall.length() > actualBest.length() || (bestOverall.length() == actualBest.length() && actualBest.compareTo(bestOverall) < 0))
					bestOverall = actualBest;
				continue;
			}
			stones(actual.stones, true);
			int cur = 0;
			for(int[] d : diffs)
			{
				stonesTaken = 0;
				nextValidMove(actual.i, actual.j, d[0], d[1]);
				int nextStones = actual.stones ^ stonesTaken;
				String val = best[nextI][nextJ][nextStones];
				if(val != null && (val.length() < actualBest.length() + 1))
				{
					cur++;
					continue;
				}
				String nextBest = actualBest + moves[cur];
				if(val != null && nextBest.compareTo(val) > 0)
				{
					cur++;
					continue;
				}
				if(val == null)
					cola.add(new Estado(nextI, nextJ, nextStones));
				best[nextI][nextJ][nextStones] = nextBest;
				cur++;
			}
		}
		return bestOverall;
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner();
		int r = sc.nextInt();
		int c = sc.nextInt();
		board = new char[r][];
		for(int i = 0; i < r; i++)
			board[i] = sc.next().substring(0, c).toCharArray();
		int startI = 0;
		int startJ = 0;
		ArrayList <int[]> stonesA = new ArrayList <int[]> ();
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
			{
				if(board[i][j] == 'S')
				{
					startI = i;
					startJ = j;
					board[i][j] = '.';
				}
				if(board[i][j] == '*')
				{
					stonesA.add(new int[]{i, j});
					board[i][j] = '.';
				}
			}
		stones = stonesA.toArray(new int[0][]);
		String ans = bfs(startI, startJ);
		System.out.println(ans.length());
		ans = ans.replace("D", "Down-");
		ans = ans.replace("R", "Right-");
		ans = ans.replace("L", "Left-");
		ans = ans.replace("U", "Up-");
		if(ans.length() == 0)
			ans = "-";
		System.out.println(ans.substring(0, ans.length() - 1));
	}

}
