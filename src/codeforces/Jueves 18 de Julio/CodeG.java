import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeG 
{
	static class Scanner
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
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
		
		public long nextLong()
		{
			return Long.parseLong(next());
		}
		
		public double nextDouble()
		{
			return Double.parseDouble(next());
		}
		
		public int[] nextIntArray(int n)
		{
			int[] res = new int[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}
		
		public long[] nextLongArray(int n)
		{
			long[] res = new long[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}
		
		public double[] nextDoubleArray(int n)
		{
			double[] res = new double[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextDouble();
			return res;
		}
		public void sortIntArray(int[] array)
		{
			Integer[] vals = new Integer[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
		
		public void sortLongArray(long[] array)
		{
			Long[] vals = new Long[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
		
		public void sortDoubleArray(double[] array)
		{
			Double[] vals = new Double[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
	}
	
	
	static class Memory
	{
		int[] memory;
		int currentN = 1;
		
		void defragment()
		{
			while(true)
			{
				int primerCero = -1;
				for(int i = 0; i < memory.length; i++) if(memory[i] == 0){ primerCero = i; break; }
				if(primerCero == -1) return;
				int ultimoNoCero = -1;
				for(int i = 0; i < memory.length; i++) if(memory[i] != 0){ ultimoNoCero = i; }
				if(ultimoNoCero == -1 || ultimoNoCero < primerCero) return;
				int primerUnoDespues = primerCero;
				while(memory[primerUnoDespues] == 0) primerUnoDespues++;
				int actual = primerCero;
				for(int i = primerUnoDespues; i < memory.length; i++)
				{
					memory[actual++] = memory[i];
					memory[i] = 0;
				}
			}
		}
		
		int alloc(int n)
		{
			int escogido = -1;
			outer:
			for(int i = 0; i < memory.length && i + n - 1 < memory.length; i++)
			{
				for(int j = i; j < i + n; j++)
					if(memory[j] != 0)
						continue outer;
				escogido = i;
				break;
			}
			if(escogido == -1) return 0;
			for(int i = escogido; i < escogido + n; i++)
				memory[i] = currentN;
			return currentN++;
		}
		
		boolean erase(int id)
		{
			if(id == 0) return false;
			boolean pudo = false;
			for(int i = 0; i < memory.length; i++)
			{
				if(memory[i] == id)
				{
					pudo = true;
					memory[i] = 0;
				}
			}
			return pudo;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		int m = sc.nextInt();
		Memory memory = new Memory();
		memory.memory = new int[m];
		for(int i = 0; i < t; i++)
		{
			String e = sc.next();
			if(e.equals("alloc"))
			{
				int n = sc.nextInt();
				int r = memory.alloc(n);
				if(r == 0) System.out.println("NULL");
				else System.out.println(r);
			}
			else if(e.equals("erase"))
			{
				int n = sc.nextInt();
				boolean r = memory.erase(n);
				if(!r) System.out.println("ILLEGAL_ERASE_ARGUMENT");
			}
			else
				memory.defragment();
		}
	}
}