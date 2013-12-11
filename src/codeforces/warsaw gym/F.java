import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.BitSet;
import java.util.HashSet;
import java.util.StringTokenizer;


public class F 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "numbereater";
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		PrintStream out;		
		
		Scanner()
		{
			if(debug)
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				out = System.out;
			}
			else
			{
				try 
				{
					br = new BufferedReader(new FileReader(name + ".in"));
					out = new PrintStream(new BufferedOutputStream(new FileOutputStream(name + ".out")), false);
				}
				catch (FileNotFoundException e) 
				{
					throw(new RuntimeException());
				}
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
		
		public void println(String s)
		{
			out.println(s);
		}
		
		public void print(String s)
		{
			out.print(s);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] nums = new int[n];
		for(int i = 0; i < n; i++)
			nums[i] = sc.nextInt() - 1;
		HashSet <BitSet> set = new HashSet <BitSet> ();
		int[][] integral = new int[n][500];
		int[] current = new int[500];
		for(int i = 0; i < n; i++)
		{
			current[nums[i]]++;
			integral[i] = current.clone();
		}
		for(int i = 0; i < n; i++)
		{
			for(int j = i + 1; j <= n; j++)
			{
				BitSet currentB = new BitSet(500);
				for(int k = 0; k < 500; k++)
					currentB.set(k, (integral[j - 1][k] - (i == 0 ? 0 : integral[i - 1][k])) > 0);
				set.add(currentB);
			}
		}
		sc.println(set.size() + "");
		sc.out.flush();
	}

}
