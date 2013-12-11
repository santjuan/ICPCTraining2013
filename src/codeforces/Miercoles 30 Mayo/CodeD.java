import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
		
		public void println(String s)
		{
			print(s);
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
	static int count(int[] fils, int[] cols, int aR, int bR, int cR)
	{
		int[] ans = new int[]{aR, bR, cR};
		Arrays.sort(ans);
		int cuenta = 0;
		for(int a = 1; a < cols.length; a++)
			for(int b = 1; b < cols.length && a + b < cols.length; b++)
			{
				int c = cols.length - (a + b);
				int aI = 0; 
				int aF = a - 1;
				int bI = aF + 1;
				int bF = bI + b - 1;
				int cI = bF + 1;
				int cF = cols.length - 1;
				int sumA = cols[aF] - (aI == 0 ? 0 : cols[aI - 1]);
				int sumB = cols[bF] - (bI == 0 ? 0 : cols[bI - 1]);
				int sumC = cols[cF] - (cI == 0 ? 0 : cols[cI - 1]);
				int[] tmp = new int[]{sumA, sumB, sumC};
				Arrays.sort(tmp);
				if(Arrays.equals(tmp, ans))
					cuenta++;
			}
		for(int a = 1; a < fils.length; a++)
			for(int b = 1; b < fils.length && a + b < fils.length; b++)
			{
				int c = fils.length - (a + b);
				int aI = 0; 
				int aF = a - 1;
				int bI = aF + 1;
				int bF = bI + b - 1;
				int cI = bF + 1;
				int cF = fils.length - 1;
				int sumA = fils[aF] - (aI == 0 ? 0 : fils[aI - 1]);
				int sumB = fils[bF] - (bI == 0 ? 0 : fils[bI - 1]);
				int sumC = fils[cF] - (cI == 0 ? 0 : fils[cI - 1]);
				int[] tmp = new int[]{sumA, sumB, sumC};
				Arrays.sort(tmp);
				if(Arrays.equals(tmp, ans))
					cuenta++;
			}
		return cuenta;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] campo = sc.nextIntMatrix(n, m);
		int[] cols = new int[m];
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				cols[i] += campo[j][i];
		int[] fils = new int[n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				fils[i] += campo[i][j];
		for(int i = 1; i < cols.length; i++)
			cols[i] += cols[i - 1];
		for(int i = 1; i < fils.length; i++)
			fils[i] += fils[i - 1];
		int aR = sc.nextInt();
		int bR = sc.nextInt(); 
		int cR = sc.nextInt();
		int cuenta = count(fils, cols, aR, bR, cR);
		sc.println(cuenta + "");
		sc.bw.flush();
		sc.bw.close();
	}
}