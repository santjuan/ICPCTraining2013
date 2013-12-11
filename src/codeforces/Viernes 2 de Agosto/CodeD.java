import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class CodeD 
{
	static boolean DEBUG = false;
	
	static class Scanner
	{	
		BufferedReader br;
		StringTokenizer st;
		BufferedWriter bw;

		public Scanner()
		{ 
			if(DEBUG)
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				bw = new BufferedWriter(new OutputStreamWriter(System.out));
			}
			else
				try 
				{
					br = new BufferedReader(new FileReader("input.txt"));
					bw = new BufferedWriter(new FileWriter("output.txt"));
				} 
				catch (Exception e) 
				{
				}
		}

		public String next()
		{

			while(st == null || !st.hasMoreTokens())
			{
				try { st = new StringTokenizer(br.readLine()); }
				catch(Exception e) { throw new RuntimeException(); }
			}
			return st.nextToken();
		}

		public int nextInt()
		{
			return Integer.parseInt(next());
		}

		public double nextDouble()
		{
			return Double.parseDouble(next());
		}

		public String nextLine()
		{
			st = null;
			try { return br.readLine(); }
			catch(Exception e) { throw new RuntimeException(); }
		}

		public boolean endLine()
		{
			try 
			{
				String next = br.readLine();
				while(next != null && next.trim().isEmpty())
					next = br.readLine();
				if(next == null)
					return true;
				st = new StringTokenizer(next);
				return st.hasMoreTokens();
			}
			catch(Exception e) { throw new RuntimeException(); }
		}
		
		public void print(String s)
		{
			try 
			{
				bw.write(s);
			} 
			catch (IOException e) 
			{
			}
		}
		
		public void println(Object s)
		{
			print(s.toString());
			print("\n");
		}
		
		public int[] nextIntArray(int n)
		{
			int[] res = new int[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}
		int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
	}
	
	static class Entrada
	{
		int i;
		int j;
		int pasos;
		
		Entrada(int ii, int jj, int p)
		{
			i = ii;
			j = jj;
			pasos = p;
		}
	}
	
	static int[][] diffs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static int solve(int[] tams, int sI, int sJ, int eI, int eJ)
	{
		boolean[][] visitados = new boolean[tams.length][];
		for(int i = 0; i < visitados.length; i++)
			visitados[i] = new boolean[tams[i] + 1]; 
		ArrayDeque <Entrada> cola = new ArrayDeque <Entrada> ();
		visitados[sI][sJ] = true;
		cola.add(new Entrada(sI, sJ, 0));
		while(!cola.isEmpty())
		{
			Entrada e = cola.poll();
			if(e.i == eI && e.j == eJ) return e.pasos;
			for(int[] d : diffs)
			{
				int iN = e.i + d[0];
				int jN = e.j + d[1];
				iN = Math.min(iN, tams.length - 1);
				iN = Math.max(iN, 0);
				jN = Math.min(jN, tams[iN]);
				jN = Math.max(jN, 0);
				if(visitados[iN][jN]) continue;
				visitados[iN][jN] = true;
				cola.add(new Entrada(iN, jN, e.pasos + 1));
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] tams = sc.nextIntArray(n);
		int iS = sc.nextInt() - 1;
		int jS = sc.nextInt() - 1;
		int iE = sc.nextInt() - 1;
		int jE = sc.nextInt() - 1;
		sc.println(solve(tams, iS, jS, iE, jE));
		sc.bw.flush();
	}
}