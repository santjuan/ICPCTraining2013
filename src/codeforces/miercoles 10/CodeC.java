import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeC
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
				res[i] = nextLong();
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
	}

	static int k;
	static int n;
	
	static Integer[] dp;
	
	static int donde(int turno)
	{
		if(dp[turno] != null)
			return dp[turno];
		if(turno == 0)
			return dp[turno] = k;
		int anterior = donde(turno - 1);
		if(anterior < 0)
		{
			int abs = Math.abs(anterior);
			abs--;
			if(abs == 0)
				anterior = 2;
			else
				anterior = -abs;
		}
		else
		{
			int abs = Math.abs(anterior);
			abs++;
			if(abs == n + 1)
				anterior = -(n - 1);
			else
				anterior = abs;
		}
		return dp[turno] = anterior;
	}
	
	
	static class Estado
	{
		int posicion;
		int turno;
		boolean quienJuega;
		
		public Estado(int pos, int tur, boolean qui) 
		{
			posicion = pos;
			turno = tur;
			quienJuega = qui;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		int m = sc.nextInt();
		k = sc.nextInt();
		if(sc.nextLine().equals("to head"))
			k = -k;
		char[] state = sc.next().toCharArray();
		dp = new Integer[state.length + 10];
		LinkedList <Estado> cola = new LinkedList <Estado> ();
		boolean[][][] visitados = new boolean[n + 10][state.length + 10][2];
		visitados[m][0][1] = true;
		cola.add(new Estado(m, 0, true));
		boolean gano = false;
		int mejorTurno = -1;
		while(!cola.isEmpty())
		{
			Estado actual = cola.poll();
			if(actual.turno == state.length)
			{
				gano = true;
				break;
			}
			if(Math.abs(donde(actual.turno)) == actual.posicion && (actual.quienJuega || state[actual.turno] != '1'))
					continue;
			mejorTurno = Math.max(mejorTurno, actual.turno);
			if(state[actual.turno] == '1')
			{
				if(actual.quienJuega)
				{
					for(int i = 1; i <= n; i++)
					{
						if(!visitados[i][actual.turno + 1][1])
						{
							visitados[i][actual.turno + 1][1] = true;
							cola.add(new Estado(i, actual.turno + 1, true));
						}
					}
				}
				else
				{
					if(!visitados[actual.posicion][actual.turno + 1][1])
					{
						visitados[actual.posicion][actual.turno + 1][1] = true;
						cola.add(new Estado(actual.posicion, actual.turno + 1, true));
					}
				}
			}
			else
			{
				if(actual.quienJuega)
				{
					ArrayList<Integer> posibles = new ArrayList <Integer> ();
					posibles.add(actual.posicion);
					if(actual.posicion != 1)
						posibles.add(actual.posicion - 1);
					if(actual.posicion != n)
						posibles.add(actual.posicion + 1);
					for(int i : posibles)
						if(!visitados[i][actual.turno][0])
						{
							visitados[i][actual.turno][0] = true;
							cola.add(new Estado(i, actual.turno, false));
						}
				}
				else
				{
					if(!visitados[actual.posicion][actual.turno + 1][1])
					{
						visitados[actual.posicion][actual.turno + 1][1] = true;
						cola.add(new Estado(actual.posicion, actual.turno + 1, true));
					}
				}
			}
		}
		if(gano)
			System.out.println("Stowaway");
		else
			System.out.println("Controller " + (mejorTurno + 1));
	}

}
