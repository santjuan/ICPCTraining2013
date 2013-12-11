import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class C 
{
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		
		Scanner()
		{
			// br = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				br = new BufferedReader(new FileReader("blot.in"));
			}
			catch (FileNotFoundException e) 
			{
				throw(new RuntimeException());
			}
		}
		public String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException());
			}
		}
		
		public String next()
		{
			while(!st.hasMoreTokens())
			{
				String l = nextLine();
				if(l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}
		
		public int nextInt()
		{
			return Integer.parseInt(next());
		}
	}
	
	static boolean[][] tablero;
	static final int[][] diff = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static int floodFill(int i, int j)
	{
		if(!tablero[i][j])
			return 0;
		int cuenta = 0;
		LinkedList <Long> pq = new LinkedList <Long> ();
		long primero = i + (((long) j) << 32);
		pq.add(primero);
		while(!pq.isEmpty())
		{
			long val = pq.poll();
			int iA = (int) val;
			int jA = (int) (val >>> 32);
			if(!tablero[iA][jA])
				continue;
			tablero[iA][jA] = false;
			cuenta++;
			for(int[] d : diff)
			{
				int iN = iA + d[0];
				int jN = jA + d[1];
				if(iN < 0 || iN >= tablero.length || jN < 0 || jN >= tablero[0].length || !tablero[iN][jN])
					continue;
				pq.add(iN + (((long) jN) << 32));
			}
		}
		return cuenta;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//PrintStream out = System.out;
		@SuppressWarnings("resource")
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("blot.out")));
		Scanner sc = new Scanner();
		int m = sc.nextInt();
		int n = sc.nextInt();
		tablero = new boolean[m][n];
		for(int i = 0; i < m; i++)
		{
			char[] act = sc.next().toCharArray();
			for(int j = 0; j < n; j++)
				tablero[i][j] = act[j] == '1';
		}
		int numero = 0;
		int mejor = 0;
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
			{
				int res = floodFill(i, j);
				mejor = Math.max(res, mejor);
				if(res > 0)
					numero++;
			}
		out.println(numero + " " + mejor);
		out.flush();
	}
}