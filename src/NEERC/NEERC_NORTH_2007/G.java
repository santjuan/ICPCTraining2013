import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class G 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "given";
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
	
	static boolean kmp(char t[], char p[], int fail[]) {
	    int n = t.length;
	    int m = p.length;
	    for (int i = 0, k = 0; i < n; i++) {
	      while (k >= 0 && p[k] != t[i])
	        k = fail[k];
	      if (++k >= m) {
	        return true;
	      }
	    }
	    return false;
	  }

	  static int[] buildFail(char p[]) {
	    int m = p.length;
	    int fail[] = new int[m + 1];
	    int j = fail[0] = -1;
	    for (int i = 1; i <= m; i++) {
	      while (j >= 0 && p[j] != p[i - 1])
	        j = fail[j];
	      fail[i] = ++j;
	    }
	    return fail;
	  }
	  
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		char[] t = sc.next().toCharArray();
		int[] fail = buildFail(t);
		char[] s = sc.next().toCharArray();
		char[] rotated = s.clone();
		final int n = s.length;
		char[] currentXor = new char[n << 1];
		boolean ok = false;
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
				currentXor[j] = rotated[j] == s[j] ? '0' : '1';
			for(int j = 0; j < n; j++)
				currentXor[j + n] = currentXor[j];
			if(kmp(currentXor, t, fail))
			{
				ok = true;
				break;
			}
			char tmp = rotated[n - 1];
			char tmp2 = 0;
			for(int j = 0; j < n; j++)
			{
				tmp2 = rotated[j];
				rotated[j] = tmp;
				tmp = tmp2;
			}
		}
		sc.println(ok ? "Yes" : "No");
		sc.out.flush();
	}
}