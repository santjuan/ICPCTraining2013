import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;


public class LiveE 
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
	
	public static class Fenwick
	{
		int[] fenwickTree;
		int tam;
		
		public Fenwick(int t)
		{
			fenwickTree = new int[t];
			tam = t;
		}
	
		void increaseRange(int a, int b, long val)
		{
			fenwickTree[a] += val;
			fenwickTree[b + 1] += -val;
		}
		
		long queryRangePoint(int a)
		{
			return fenwickTree[a];
		}

		public void close()
		{
			int acum = 0;
			for(int i = 0; i < fenwickTree.length; i++)
			{
				acum += fenwickTree[i];
				fenwickTree[i] = acum;
			}
		}
	}
	
	static class Fecha implements Comparable <Fecha>
	{
		int anio;
		int mes;
		int dia;
		
		Fecha(int a, int m, int d)
		{
			anio = a;
			mes = m;
			dia = d;
		}

		static Calendar instancia = Calendar.getInstance();
		
		Fecha siguiente()
		{
			int siguienteAnio = anio;
			int siguienteDia = dia;
			int siguienteMes = mes;
			siguienteDia++;
			boolean especial = (anio % 400 == 0) || ((anio % 4 == 0) && (anio % 100 != 0));
			if(mes == 4 || mes == 6 || mes == 9 || mes == 11)
			{
				if(siguienteDia >= 31)
				{
					siguienteMes++;
					siguienteDia = 1;
				}
			}
			else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
			{
				if(siguienteDia >= 32)
				{
					siguienteMes++;
					siguienteDia = 1;
				}
			}
			else if(mes == 2)
			{
				if(especial)
				{
					if(siguienteDia >= 30)
					{
						siguienteMes++;
						siguienteDia = 1;
					}
				}
				else
				{
					if(siguienteDia >= 29)
					{
						siguienteMes++;
						siguienteDia = 1;
					}
				}
			}
			if(siguienteMes == 13)
			{
				siguienteAnio++;
				siguienteMes = 1;
			}
			return new Fecha(siguienteAnio, siguienteMes, siguienteDia);
		}

		@Override
		public int compareTo(Fecha o) 
		{
			if(anio == o.anio)
			{
				if(mes == o.mes)
					return dia - o.dia;
				return mes - o.mes;
			}
			return anio - o.anio;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + anio;
			result = prime * result + dia;
			result = prime * result + mes;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Fecha other = (Fecha) obj;
			if (anio != other.anio)
				return false;
			if (dia != other.dia)
				return false;
			if (mes != other.mes)
				return false;
			return true;
		}

		@Override
		public String toString() 
		{
			return mes + "/" + dia + "/" + anio;
		}
	}
	
	static int parse(String s)
	{
		while(s.length() > 1 && s.charAt(0) == '0')
			s = s.substring(1);
		return Integer.parseInt(s);
	}
	
	static Fecha readDate(String s)
	{
		int year = parse(s.substring(0, 4));
		int month = parse(s.substring(4, 6));
		int day = parse(s.substring(6));
		return new Fecha(year, month, day);
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int caso = 1;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Fecha inicio = new Fecha(1700, 1, 1);
		Fecha fin = new Fecha(2100, 12, 31);
		HashMap <Fecha, Integer> todas = new HashMap <Fecha, Integer> ();
		ArrayList <Fecha> enOrden = new ArrayList <Fecha> ();
		int currentI = 0;
		while(true)
		{
			todas.put(inicio, currentI++);
			enOrden.add(inicio);
			if(inicio.compareTo(fin) == 0)
				break;
			inicio = inicio.siguiente();
		}
		while(true)
		{
			int nX = sc.nextInt();
			int nR = sc.nextInt();
			if(nX == 0 && nR == 0)
			{
				bw.flush();
				return;
			}
			Fenwick fenwickA = new Fenwick(todas.size() + 10);
			for(int i = 0; i < nX; i++)
			{
				Fecha a = readDate(sc.next());
				Fecha b = readDate(sc.next());
				fenwickA.increaseRange(todas.get(a), todas.get(b), 1);
			}
			fenwickA.close();
			Fenwick fenwickB = new Fenwick(todas.size() + 10);
			for(int i = 0; i < nR; i++)
			{
				Fecha a = readDate(sc.next());
				Fecha b = readDate(sc.next());
				fenwickB.increaseRange(todas.get(a), todas.get(b), 1);
			}
			fenwickB.close();
			ArrayList <Fecha> diferencia = new ArrayList <Fecha> ();
			int limite = todas.size();
			for(int i = 0; i < limite; i++)
			{
				if(fenwickB.queryRangePoint(i) != 0 && fenwickA.queryRangePoint(i) == 0)
					diferencia.add(enOrden.get(i));
			}
			Fecha primera = diferencia.isEmpty() ? null : diferencia.get(0);
			Fecha anterior = null;
			ArrayList <Fecha[]> resultado = new ArrayList <Fecha[]> ();
			for(Fecha f : diferencia)
			{
				if(anterior != null && f.compareTo(anterior.siguiente()) != 0)
				{
					resultado.add(new Fecha[]{primera, anterior});
					primera = f;
				}
				anterior = f;
			}
			if(primera != null && anterior != null)
				resultado.add(new Fecha[]{primera, anterior});
			if(caso != 1)
				bw.write("\n");
			bw.write("Case " + caso++ + ":\n");
			for(Fecha[] a : resultado)
			{
				if(a[0].compareTo(a[1]) == 0)
					bw.write("    " + a[0] + "\n");
				else
					bw.write("    " + a[0] + " to " + a[1] + "\n");
 			}
			if(resultado.isEmpty())
				bw.write("    No additional quotes are required.\n");
		}
	}

}
