import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class CodeC 
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
	
	static int darSuma(int[] imagen, int a, int b)
	{
		if(b < a) return 0;
		return imagen[b] - (a == 0 ? 0 : imagen[a - 1]);
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] vals = sc.nextIntArray(n);
		int[] integralImage = new int[5001];
		for(int i : vals) integralImage[i]++;
		for(int i = 1; i < integralImage.length; i++)
			integralImage[i] += integralImage[i - 1];
		int best = Integer.MAX_VALUE;
		for(int i = 1; i < integralImage.length; i++)
		{
			if(darSuma(integralImage, i, i) > 0)
			{
				int posible = darSuma(integralImage, 0, i - 1);
				if(2 * i + 1 <= integralImage.length - 1)
					posible += darSuma(integralImage, 2 * i + 1, integralImage.length - 1);
				best = Math.min(best, posible);
			}
		}
		sc.println(best + "");
		sc.bw.flush();
	}
}