import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
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
	
	static class Carta implements Comparable <Carta>
	{
		int valor;
		int pinta;
		
		@Override
		public int compareTo(Carta otra) 
		{
			if(valor < otra.valor)
				return 1;
			else if(valor > otra.valor)
				return -1;
			else
				return 0;
		}	
	}
	
	static Carta generarCarta(char [] carta)
	{
		int valor;
		try
		{
			valor = Integer.parseInt(carta[0] + "");
		}
		catch(Exception e)
		{
			if(carta[0] == 'X')
			{
				valor = 10;
			}
			else if(carta[0] == 'J')
			{
				valor = 11;
			}
			else if(carta[0] == 'Q')
			{
				valor = 12;
			}
			else if(carta[0] == 'K')
			{
				valor = 13;
			}
			else
			{
				valor = 14;
			}
		}
		int pinta;
		switch(carta[1])
		{
			case 'h': pinta = 1; break;
			case 'd': pinta = 2; break;
			case 's': pinta = 3; break;
			default: pinta = 4; break;
		}
		Carta c = new Carta();
		c.pinta = pinta;
		c.valor = valor;
		return c;
	}
	
	
	static long darValor(Carta [] mano)
	{
		Arrays.sort(mano);
		ArrayList <Integer> repetidas = new ArrayList <Integer> ();
		int tipo = 0;
		for(int i = 0; i < 5; i++)
			repetidas.add(1);
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(j != i)
				{
					if (mano[i].compareTo(mano[j]) == 0)
					{
						repetidas.set(i, repetidas.get(i) + 1);
					}
				}
			}
		}
		ArrayList <Integer> manoN = new ArrayList <Integer> ();
		if(repetidas.contains(4))
		{
			tipo = 7;
			for(int i = 0; i < 4; i++)
				manoN.add(mano[repetidas.indexOf(4)].valor);
			manoN.add(mano[repetidas.indexOf(1)].valor);
		}
		else if(repetidas.contains(3) && repetidas.contains(2))
		{
			tipo = 6;
			for(int i = 0; i < 3; i++)
				manoN.add(mano[repetidas.indexOf(3)].valor);
			for(int i = 0; i < 2; i++)
				manoN.add(mano[repetidas.indexOf(2)].valor);
		}
		else if(repetidas.contains(3))
		{
			tipo = 3;
			for(int i = 0; i < 3; i++)
				manoN.add(mano[repetidas.indexOf(3)].valor);
			manoN.add(mano[repetidas.indexOf(1)].valor);
			manoN.add(mano[repetidas.lastIndexOf(1)].valor);
		}
		else if(repetidas.contains(2))
		{
			if(repetidas.indexOf(2) + 1 != repetidas.lastIndexOf(2))
			{
				tipo = 2;
				for(int i = 0; i < 2; i++)
					manoN.add(mano[repetidas.indexOf(2)].valor);
				for(int i = 0; i < 2; i++)
					manoN.add(mano[repetidas.lastIndexOf(2)].valor);
				manoN.add(mano[repetidas.indexOf(1)].valor);
			}
			else
			{
				tipo = 1;
				for(int i = 0; i < 2; i++)
					manoN.add(mano[repetidas.indexOf(2)].valor);
				for(int i = 0; i < 5; i++)
				{
					if(repetidas.get(i) == 1)
					{
						manoN.add(mano[i].valor);
					}
				}
			}
		}
		else
		{
			for(Carta c : mano)
			{
				manoN.add(c.valor);
			}
			boolean color = true;
			int pinta = mano[0].pinta;
			for(Carta c : mano)
			{
				if(c.pinta != pinta)
					color = false;
			}
			boolean escalera = true;
			int valor = mano[0].valor;
			for(Carta c : mano)
			{
				if(c.valor != valor)
					escalera = false;
				else
					valor--;
			}
			boolean escaleraAs = mano[0].valor == 14;
			valor = 5;
			for(int i = 1; i < mano.length; i++)
			{
				if(mano[i].valor != valor)
					escaleraAs = false;
				else
					valor--;
			}
			if(escaleraAs)
			{
				manoN.clear();
				manoN.add(5);
				manoN.add(4);
				manoN.add(3);
				manoN.add(2);
				manoN.add(1);
			}
			escalera |= escaleraAs;
			if(escalera && color)
				tipo = 8;
			else if(color)
				tipo = 5;
			else if(escalera)
				tipo = 4;
		}
		long valorMano = tipo * 10000000000L;
		valorMano += manoN.get(0) * 100000000;
		valorMano += manoN.get(1) * 1000000;
		valorMano += manoN.get(2) * 10000;
		valorMano += manoN.get(3) * 100;
		valorMano += manoN.get(4);
		return valorMano;
	}
	
	static int[] mascarasCinco;
	
	static long darMejor(Carta[] comunitarias, Carta[] jugador)
	{
		int limite = 1 << 7;
		long mejor = Long.MIN_VALUE;
		for(int m : mascarasCinco)
		{
			ArrayList <Carta> manoA = new ArrayList <Carta> ();
			for(int i = 0; i < 5; i++)
			{
				if((m & 1) == 1)
					manoA.add(comunitarias[i]);
				m >>= 1;
			}
			for(int i = 0; i < 2; i++)
			{
				if((m & 1) == 1)
					manoA.add(jugador[i]);
				m >>= 1;
			}
			mejor = Math.max(mejor, darValor(manoA.toArray(new Carta[0])));
		}
		return mejor;
	}	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		ArrayList <Integer> todas = new ArrayList <Integer> ();
		int limite = 1 << 7;
		for(int i = 0; i < limite; i++)
			if(Integer.bitCount(i) == 5)
				todas.add(i);
		mascarasCinco = new int[todas.size()];
		for(int i = 0; i < mascarasCinco.length; i++)
			mascarasCinco[i] = todas.get(i);
		while(true)
		{
			Integer n = sc.nextInteger();
			if(n == null)
				return;
			Carta[] comunitarias = new Carta[5];
			for(int i = 0; i < 5; i++)
				comunitarias[i] = generarCarta(sc.next().toCharArray());
			long mejorTotal = Long.MIN_VALUE;
			ArrayList <Integer> ganadores = new ArrayList <Integer> ();
			for(int i = 0; i < n; i++)
			{
				Carta[] jugador = new Carta[]{generarCarta(sc.next().toCharArray()), generarCarta(sc.next().toCharArray())};
				long mejorEste = darMejor(comunitarias, jugador);
				if(mejorEste == mejorTotal)
					ganadores.add(i + 1);
				else if(mejorEste > mejorTotal)
				{
					mejorTotal = mejorEste;
					ganadores.clear();
					ganadores.add(i + 1);
				}
			}
			System.out.print(ganadores.get(0));
			for(int i = 1; i < ganadores.size(); i++)
				System.out.print(" " + ganadores.get(i));
			System.out.println();
		}
	}
}