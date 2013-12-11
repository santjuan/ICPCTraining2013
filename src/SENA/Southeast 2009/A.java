import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class A 
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
	
	static class Estado
	{
		char[][] tablero;
		int costo;
		
		public Estado(char[][] tableroActual, int c) 
		{
			tablero = tableroActual;
			costo = c;
		}

		private boolean vacio(int i, int j)
		{
			if(i < 0 || i >= tablero.length || j < 0 || j >= tablero[0].length || tablero[i][j] != '.')
				return false;
			return true;
		}

		private boolean valido(int i, int j)
		{
			if(i < 0 || i >= tablero.length || j < 0 || j >= tablero[0].length)
				return false;
			return true;
		}
		

		static final int[][] deltas = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		
		
		ArrayList <Estado> generarEstados()
		{
			ArrayList <Estado> siguientes = new ArrayList <Estado> ();
			for(int i = 0; i < 6; i++)
				for(int j = 0; j < 6; j++)
				{
					for(int[] delta : deltas)
					{
						char letra = tablero[i][j];
						if((!valido(i - delta[0], j - delta[1])) || (!valido(i + delta[0], j + delta[1])))
							continue;
						if(letra != '.' && tablero[i - delta[0]][j - delta[1]] == letra && tablero[i + delta[0]][j + delta[1]] == '.')
						{
							int siguienteI = i + delta[0];
							int siguienteJ = j + delta[1];
							char[][] tableroActual = clonar(tablero);
							while(vacio(siguienteI, siguienteJ))
							{
								int actualI = siguienteI - delta[0];
								int actualJ = siguienteJ - delta[1];
								while(valido(actualI, actualJ) && tableroActual[actualI][actualJ] == letra)
								{
									tableroActual[actualI + delta[0]][actualJ + delta[1]] = tableroActual[actualI][actualJ];
									actualI -= delta[0];
									actualJ -= delta[1];
								}
								actualI += delta[0];
								actualJ += delta[1];
								tableroActual[actualI][actualJ] = '.';
								siguientes.add(new Estado(tableroActual, costo + 1));
								siguienteI += delta[0];
								siguienteJ += delta[1];
								tableroActual = clonar(tableroActual);
							}
						}
					}
				}	
			return siguientes;
		}
		

		private char[][] clonar(char[][] tablero)
		{
			char[][] nuevo = new char[tablero.length][];
			for(int i = 0; i < tablero.length; i++)
				nuevo[i] = tablero[i].clone();
			return nuevo;
		}
		
		@Override
		public String toString() 
		{
			String res = "";
			for(int i = 0; i < tablero.length; i++)
				res += new String(tablero[i]) + "\n";
			return res;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.deepHashCode(tablero);
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
			if (!Arrays.deepEquals(tablero, other.tablero))
				return false;
			return true;
		}
		
	}
	
	static Estado leerTablero(Scanner sc)
	{
		char[][] tablero = new char[6][];
		for(int i = 0; i < 6; i++)
			tablero[i] = sc.next().substring(0,  6).toCharArray();
		return new Estado(tablero, 0);
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			char letra = sc.next().charAt(0);
			if(letra == '*')
				return;
			Estado inicial = leerTablero(sc);
			int cualFila = 0;
			for(int i = 0; i < 6; i++)
				for(int j = 0; j < 6; j++)
					if(inicial.tablero[i][j] == letra)
						cualFila = i;
			HashSet <Estado> visitados = new HashSet <Estado> ();
			LinkedList <Estado> cola = new LinkedList <Estado> ();
			visitados.add(inicial);
			cola.add(inicial);
			boolean pudo = false;
			while(!cola.isEmpty())	
			{
				Estado actual = cola.poll();
				if(actual.tablero[cualFila][5] == letra)
				{
					pudo = true;
					System.out.println(actual.costo == 0 ? 1 : actual.costo);
					break;
				}
				for(Estado e : actual.generarEstados())
				{
					if(visitados.contains(e))
						continue;
					visitados.add(e);
					cola.add(e);
				}
			}
			if(!pudo)
				System.out.println("-1");
		}
	}

}
