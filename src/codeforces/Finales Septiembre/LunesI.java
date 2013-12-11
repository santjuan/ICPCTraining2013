import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class LunesI 
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
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();
		int[] cuenta = new int[26];
		for(char x : a) cuenta[x - 'A']++;
		for(char x : b) cuenta[x - 'A']--;
		int total = 0;
		int[] hay = new int[26];
		for(char c : a)
			hay[c - 'A']++;
		for(int i = 0; i < a.length; i++)
		{
			int c = cuenta[a[i] - 'A'];
			int orig = a[i] - 'A';
			if(c > 0)
			{
				for(int j = 0; j < (c == hay[a[i] - 'A'] ? 26 : a[i] - 'A'); j++)
				{
					if(cuenta[j] < 0)
					{
						cuenta[j]++;
						cuenta[a[i] - 'A']--;
						a[i] = (char) (j + 'A');
						total++;
						break;
					}
				}
			}
			hay[orig]--;
		}
		sc.println(total + "");
		sc.println(new String(a));
		sc.bw.flush();
	}
}