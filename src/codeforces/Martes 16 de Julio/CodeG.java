import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	
	static int actual = 0;
	static char[] entrada;
	
	static abstract class Simbolo
	{
		abstract int darMejor();
		
		abstract int darInicio();

		abstract int darFin();

	    abstract boolean esBueno();
	    
	    abstract List <Simbolo> darHijos();
	}
	
	static class Parentesis extends Simbolo
	{
		int inicio;
		int fin;
		int mejorInterno;
		Lista interna;
		boolean malo;
		
		Parentesis()
		{
			inicio = actual;
			char tipo = entrada[actual];
			if(tipo == '[') tipo = ']';
			else if(tipo == '(') tipo = ')';
			actual++;
			if(actual == entrada.length)
			{
				malo = true;
				fin = actual - 1;
				return;
			}
			interna = new Lista(true);
			if(interna.simbolos.size() == 0)
				interna = null;
			if(actual == entrada.length)
			{
				malo = true;
				fin = actual - 1;
				mejorInterno = interna == null ? 0 : interna.mejorInterno;
				return;
			}
			if(entrada[actual] == tipo)
			{
				malo = false;
				fin = actual;
				if(interna != null && interna.mala)
				{
					malo = true;
					mejorInterno = interna.mejorInterno;
				}
				else
					mejorInterno = (interna == null ? 0 : interna.mejorInterno) + (tipo == ']' ? 1 : 0);
			}
			else
			{
				malo = true;
				fin = actual;
				mejorInterno = interna == null ? 0 : interna.mejorInterno;
			}
			actual++;
//			if(actual != entrada.length && entrada[actual] != '(' && entrada[actual] != '[')
//			{
//				malo = true;
//				while(actual != entrada.length && entrada[actual] != '(' && entrada[actual] != '[')
//					actual++;
//				fin = actual - 1;
//			}
		}

		@Override
		int darMejor() 
		{
			return mejorInterno;
		}

		@Override
		int darInicio()
		{
			return inicio;
		}

		@Override
		int darFin() 
		{
			return fin;
		}

		@Override
		boolean esBueno() 
		{
			return !malo;
		}

		@Override
		List <Simbolo> darHijos()
		{
			if(interna == null) return new ArrayList <Simbolo> ();
			return Arrays.asList((Simbolo) interna);
		}
	}
	
	static class Lista extends Simbolo
	{
		int inicio;
		int fin;
		boolean mala;
		int mejorInterno;
		ArrayList <Simbolo> simbolos = new ArrayList <Simbolo> ();
		
		Lista(boolean espera)
		{
			mala = false;
			ArrayList <Parentesis> actualesBuenos = new ArrayList <Parentesis> ();
			while(actual < entrada.length)
			{
				if(entrada[actual] == ']' || entrada[actual] == ')')
				{
					if(espera) break;
					actual++;
					mala = true;
					continue;
				}
				Parentesis siguiente = new Parentesis();
				if(siguiente.malo)
				{
					guardarBuena(actualesBuenos);
					simbolos.add(siguiente);
					mala = true;
				}
				else
					actualesBuenos.add(siguiente);
			}
			if(!actualesBuenos.isEmpty())
			{
				if(simbolos.isEmpty())
					simbolos = new ArrayList <Simbolo> (actualesBuenos);
				else
					guardarBuena(actualesBuenos);
			}
			if(!mala)
				for(Simbolo s : simbolos)
					mejorInterno += s.darMejor();
			else
				for(Simbolo s : simbolos)
					mejorInterno = Math.max(mejorInterno, s.darMejor());
			if(!simbolos.isEmpty())
			{
				inicio = simbolos.get(0).darInicio();
				fin = simbolos.get(simbolos.size() - 1).darFin();
			}					
		}

		public Lista(ArrayList <Parentesis> actualesBuenos)
		{
			mala = false;
			inicio = actualesBuenos.get(0).darInicio();
			fin = actualesBuenos.get(actualesBuenos.size() - 1).darFin();
			for(Parentesis p : actualesBuenos)
				mejorInterno += p.darMejor();
			simbolos = new ArrayList <Simbolo> (actualesBuenos);
		}

		void guardarBuena(ArrayList <Parentesis> actualesBuenos) 
		{
			if(actualesBuenos.isEmpty()) return;
			simbolos.add(new Lista(actualesBuenos));
			actualesBuenos.clear();
		}

		@Override
		int darMejor()
		{
			return mejorInterno;
		}

		@Override
		int darInicio() 
		{
			return inicio;
		}

		@Override
		int darFin() 
		{
			return fin;
		}

		@Override
		boolean esBueno() 
		{
			return !mala;
		}

		@Override
		List<Simbolo> darHijos() 
		{
			return simbolos;
		}
	}
	
	static int mejorCuenta = 0;
	static int mejorInicio = -1;
	static int mejorFin = -1;
	
	static void intentar(Simbolo simbolo)
	{
		if(simbolo.esBueno())
		{
			if(simbolo.darMejor() > mejorCuenta)
			{
				mejorCuenta = simbolo.darMejor();
				mejorInicio = simbolo.darInicio();
				mejorFin = simbolo.darFin();
			}
		}
		else
			for(Simbolo s : simbolo.darHijos())
				intentar(s);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();	
		entrada = sc.next().toCharArray();
		intentar(new Lista(false));
		if(mejorInicio == -1)
		{
			System.out.println(0);
			System.out.println();
		}
		else
		{
			System.out.println(mejorCuenta);
			for(int i = mejorInicio; i <= mejorFin; i++)
				System.out.print(entrada[i]);
			System.out.println();
		}
	}
}