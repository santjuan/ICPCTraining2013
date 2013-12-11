import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class walls 
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
	
	static class Punto
	{
		int x;
		int y;
		int mascaraActual;
		
		public Punto(int xx, int yy) 
		{
			x = xx;
			y = yy;
		}
	}
	
	static int resolver(Punto[] porX, Punto[] porY)
	{
		final int limite = 1 << 18;
		int mejor = Integer.MAX_VALUE;
		ArrayDeque <Punto> puntosX = new ArrayDeque <Punto> ();
		ArrayDeque <Punto> puntosY = new ArrayDeque <Punto> ();
		ArrayList <Punto> enX = new ArrayList <Punto> (Arrays.asList(porX));
		ArrayList <Punto> enY = new ArrayList <Punto> (Arrays.asList(porY));
		int utiles = 0;
		for(Punto p : enX)
		{
			int borde = p.x - 1;
			if(borde < 0)
				continue;
			utiles |= (1 << (borde / 2));
		}
		outer:
		for(int i = 0; i < limite; i++)
		{
			if((i & utiles) != i)
				continue;
			int mascaraActual = 1;
			int inicial = Integer.bitCount(i);
			if(inicial >= mejor)
				continue;
			puntosX.clear();
			puntosX.addAll(enX);
			for(int j = 0; j < 19; j++)
			{
				int borde = (j << 1) + 1;
				while(!puntosX.isEmpty() && puntosX.peek().x < borde)
					puntosX.poll().mascaraActual = mascaraActual;
				if((i & (1 << j)) != 0)
					mascaraActual <<= 1;
			}		
			puntosY.clear();
			puntosY.addAll(enY);
			int mascaraCurrent = 0;
			int cuenta = 0;
			for(int j = 0; j < 38; j += 2)
			{
				int mascaraEste = 0;
				while(!puntosY.isEmpty() && puntosY.peek().y == j)
				{
					int mascaraA = puntosY.poll().mascaraActual;
					if((mascaraEste & mascaraA) != 0)
						continue outer;
					mascaraEste |= mascaraA;
				}
				if((mascaraCurrent & mascaraEste) != 0)
				{
					cuenta++;
					mascaraCurrent = mascaraEste;
				}
				else
					mascaraCurrent |= mascaraEste;
			}
			if(cuenta + inicial < mejor)
				mejor = cuenta + inicial;
		}
		return mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			Punto[] puntosX = new Punto[n];
			Punto[] puntosY = new Punto[n];
			for(int i = 0; i < n; i++)
			{
				puntosX[i] = new Punto(sc.nextInt(), sc.nextInt());
				puntosY[i] = puntosX[i];
			}
			Arrays.sort(puntosX, new Comparator<Punto>() {
				@Override
				public int compare(Punto o1, Punto o2) {
					return o1.x - o2.x;
				}
			});
			Arrays.sort(puntosY, new Comparator<Punto>() {
				@Override
				public int compare(Punto o1, Punto o2) {
					return o1.y - o2.y;
				}
			});
			System.out.println(resolver(puntosX, puntosY));
		}
	}

}
