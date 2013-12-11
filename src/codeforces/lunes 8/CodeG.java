import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
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
				res[i] = nextLong();
			return res;
		}
		
		public String[] nextStringArray(int n)
		{
			String[] res = new String[n];
			for(int i = 0; i < res.length; i++)
				res[i] = next();
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

	static class Statue
	{
		int x;
		int y;
		
		public Statue(int xx, int yy) 
		{
			x = xx;
			y = yy;
		}

		Statue getPosition(int turn)
		{
			int nextX = x + (turn + 1) / 2;
			if(nextX > 7)
				return null;
			return new Statue(nextX, y);
		}
	}
	
	static class Respuesta
	{
		int tipo;
		boolean masculino;

		public Respuesta(int t, boolean m)
		{
			tipo = t;
			masculino = m;
		}
	}
	
	static Respuesta darRespuesta(String s)
	{
		if(s.endsWith("lios"))
			return new Respuesta(0, true);
		if(s.endsWith("liala"))
			return new Respuesta(0, false);
		if(s.endsWith("etr"))
			return new Respuesta(1, true);
		if(s.endsWith("etra"))
			return new Respuesta(1, false);
		if(s.endsWith("initis"))
			return new Respuesta(2, true);
		if(s.endsWith("inites"))
			return new Respuesta(2, false);
		return new Respuesta(-1, false);
	}
	
	static int leerVarios(LinkedList <Respuesta> enOrden, int tipo)
	{
		if(enOrden.isEmpty() || enOrden.peek().tipo != tipo)
			return 0;
		int cuenta = 0;
		while(!enOrden.isEmpty() && enOrden.peek().tipo == tipo)
		{
			cuenta++;
			enOrden.poll();
		}
		return cuenta;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		LinkedList <Respuesta> enOrden = new LinkedList <Respuesta> ();
		String[] pedazos = sc.nextLine().split(" ");
		boolean paila = false;
		Boolean queGenero = null;
		for(String s : pedazos)
		{
			Respuesta r = darRespuesta(s);
			if(r.tipo == -1)
				paila = true;
			if(queGenero != null)
			{
				if(r.masculino != queGenero.booleanValue())
					paila = true;
			}
			else
				queGenero = r.masculino;
			enOrden.add(r);
		}
		if(paila)
		{
			System.out.println("NO");
			return;
		}
		if(enOrden.size() == 1)
		{
			System.out.println("YES");
			return;
		}
		leerVarios(enOrden, 0);
		int cuentaB = leerVarios(enOrden, 1);
		leerVarios(enOrden, 2);
		if(cuentaB == 1 && enOrden.isEmpty())
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
