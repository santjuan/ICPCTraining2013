import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class H
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "history";
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
	
	public static long tries(int[] puntajesAns, int currentSum, int i)
	{
		if(i == puntajesAns.length)
		{
			return currentSum != 0 ? 0 : solve(puntajesAns);
		}
		long ans = 0;
		if(puntajesAns[i] >= 3)
		{
			puntajesAns[i] -= 3;
			ans += tries(puntajesAns, currentSum, i + 1);	
			puntajesAns[i] += 3;
		}
		if(puntajesAns[i] >= 1 && currentSum >= 1)
		{
			puntajesAns[i]--;
			ans += tries(puntajesAns, currentSum - 1, i + 1);
			puntajesAns[i]++;
		}
		if(currentSum >= 3)
			ans += tries(puntajesAns, currentSum - 3, i + 1);
		return ans;
	}
	
	static class Estado
	{
		int[] puntajes;
		
		public Estado(int[] puntajesT) 
		{
			puntajes = puntajesT;
		}
		
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(puntajes);
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Estado other = (Estado) obj;
			if (!Arrays.equals(puntajes, other.puntajes))
				return false;
			return true;
		}
		
	}
	
	static HashMap <Estado, Long> mapa = new HashMap <Estado, Long> ();
	
	public static long solve(int[] puntajes)
	{
		if(puntajes.length == 0)
			return 1L;
		int[] puntajesEntrada = puntajes.clone();
		Arrays.sort(puntajesEntrada);
		Estado current = new Estado(puntajesEntrada.clone());
		if(mapa.containsKey(current))
			return mapa.get(current);
		int[] puntajesT = new int[puntajesEntrada.length - 1];
		for(int i = 1; i < puntajes.length; i++)
			puntajesT[i - 1] = puntajes[i];
		long val = tries(puntajesT, puntajes[0], 0);
		mapa.put(current, val);
		return val;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] vals = new int[n];
		for(int i = 0; i < n; i++)
			vals[i] = sc.nextInt();
		sc.println(solve(vals) + "");
		sc.out.flush();
	}
}