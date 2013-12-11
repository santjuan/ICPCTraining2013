import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class subtrees 
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
	
	static class IntHashMap
	{

		private Entry[] table;

	    private int count;

	    private int threshold;

	    private final float loadFactor;

	    private static class Entry {
	        final int hash;
	        int value;
	        Entry next;

	        private Entry(int hash, int key, int value, Entry next) {
	            this.hash = hash;
	            this.value = value;
	            this.next = next;
	        }
	    }
	    
	    public IntHashMap() {
	        this(20, 0.75f);
	    }

	    public IntHashMap(int initialCapacity, float loadFactor) 
	    {
	        this.loadFactor = loadFactor;
	        table = new Entry[initialCapacity];
	        threshold = (int) (initialCapacity * loadFactor);
	    }

	    /**
	     * <p>Returns the value to which the specified key is mapped in this map.</p>
	     *
	     * @param   key   a key in the hashtable.
	     * @return  the value to which the key is mapped in this hashtable;
	     *          <code>null</code> if the key is not mapped to any value in
	     *          this hashtable.
	     * @see     #put(int, Object)
	     */
	    public int get(int key) {
	        Entry tab[] = table;
	        int hash = key;
	        int index = (hash & 0x7FFFFFFF) % tab.length;
	        for (Entry e = tab[index]; e != null; e = e.next) {
	            if (e.hash == hash) {
	                return e.value;
	            }
	        }
	        return Integer.MAX_VALUE;
	    }

	    protected void rehash() {
	        int oldCapacity = table.length;
	        Entry oldMap[] = table;

	        int newCapacity = oldCapacity * 2 + 1;
	        Entry newMap[] = new Entry[newCapacity];

	        threshold = (int) (newCapacity * loadFactor);
	        table = newMap;

	        for (int i = oldCapacity; i-- > 0;) {
	            for (Entry old = oldMap[i]; old != null;) {
	                Entry e = old;
	                old = old.next;

	                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
	                e.next = newMap[index];
	                newMap[index] = e;
	            }
	        }
	    }

	    public void increase(int key) 
	    {
	        Entry tab[] = table;
	        int hash = key;
	        int index = (hash & 0x7FFFFFFF) % tab.length;
	        for (Entry e = tab[index]; e != null; e = e.next) {
	            if (e.hash == hash) {
	                Object old = e.value;
	                e.value++;
	                return;
	            }
	        }

	        if (count >= threshold) {
	            // Rehash the table if the threshold is exceeded
	            rehash();

	            tab = table;
	            index = (hash & 0x7FFFFFFF) % tab.length;
	        }

	        // Creates the new entry.
	        Entry e = new Entry(hash, key, 1, tab[index]);
	        tab[index] = e;
	        count++;
	        return;
	    }
	    
	    public void put(int key, int newVal) 
	    {
	        Entry tab[] = table;
	        int hash = key;
	        int index = (hash & 0x7FFFFFFF) % tab.length;
	        for (Entry e = tab[index]; e != null; e = e.next) {
	            if (e.hash == hash) {
	                Object old = e.value;
	                e.value = newVal;
	                return;
	            }
	        }

	        if (count >= threshold) {
	            // Rehash the table if the threshold is exceeded
	            rehash();

	            tab = table;
	            index = (hash & 0x7FFFFFFF) % tab.length;
	        }

	        // Creates the new entry.
	        Entry e = new Entry(hash, key, newVal, tab[index]);
	        tab[index] = e;
	        count++;
	        return;
	    }
	    
	    int actual = 0;
	    Entry entradaActual = null;
	    int valActual;
	    
	    void prepararIterador()
	    {
	    	actual = 0;
	    	Entry entradaActual = table[0];
	    }
	    
	    int siguiente()
	    {
	        Entry tab[] = table;
	    	while(entradaActual == null && actual < tab.length)
	    		entradaActual = table[actual++];
	    	if(entradaActual == null) return Integer.MAX_VALUE;
	    	int val = entradaActual.hash;
	    	valActual = entradaActual.value;
	    	entradaActual = entradaActual.next;
	    	return val;
	    }
	}
	
	

	static final int prime = 31;
	
	static IntHashMap mapaIds = new IntHashMap();
	static IntHashMap idsMapa = new IntHashMap();
	
	static int idActual;
	
	static int darId(int numero, int siguiente)
	{
		int llave = numero | (siguiente << 12);
		int val = mapaIds.get(llave);
		if(val == Integer.MAX_VALUE)
		{
			mapaIds.put(llave, idActual);
			idsMapa.put(idActual, llave);
			return idActual++;
		}
		else
			return val;
	}
	
	static ArrayList <Nodo> todos = new ArrayList <Nodo> ();
	
	static class Nodo implements Comparable <Nodo>
	{
		IntHashMap mapas = new IntHashMap();
		ArrayList <Nodo> hijos = new ArrayList <Nodo> ();
		int altura = 0;
		
		void procesar()
		{
			IntHashMap mio = mapas;
			IntHashMap temporal = new IntHashMap();
			for(Nodo n : hijos)
			{
				IntHashMap mapa = n.mapas;
				mapa.prepararIterador();
				int val;
				while((val = mapa.siguiente()) != Integer.MAX_VALUE)
					temporal.increase(val);
				n.mapas = null;
			}
			temporal.prepararIterador();
			int val;
			while((val = temporal.siguiente()) != Integer.MAX_VALUE)
			{
				int veces = temporal.valActual;
				for(int i = 1; i <= veces; i++)
				{
					int idN = darId(i, val);
					mio.put(idN, 1);
				}
			}
			mio.put(darId(0, 0), 1);
		}

		@Override
		public int compareTo(Nodo o) 
		{
			return o.altura - altura;
		}
	}
	
	static Nodo leerRaiz(LinkedList <Character> entrada)
	{
		entrada.poll();
		LinkedList <Nodo> nodos = new LinkedList <Nodo> ();
		Nodo raiz = new Nodo();
		todos.add(raiz);
		nodos.addFirst(raiz);
		while(!entrada.isEmpty())
		{
			Nodo actual = nodos.poll();
			if(entrada.peek() != ')')
			{
				Nodo nuevo = new Nodo();
				nuevo.altura = actual.altura + 1;
				todos.add(nuevo);
				actual.hijos.add(nuevo);
				entrada.poll();
				nodos.addFirst(actual);
				nodos.addFirst(nuevo);
			}
			else
				entrada.poll();
		}
		return raiz;
	}
	
	static final int mascaraA = (1 << 12) - 1;
	
	static class Salida implements Comparable <Salida>
	{
		ArrayList <Integer> values = new ArrayList <Integer> ();

		@Override
		public int compareTo(Salida o)
		{
			int indice = 0;
			ArrayList <Integer> valuesA = values;
			ArrayList <Integer> valuesB = o.values;
			while(indice < valuesA.size() && indice < valuesB.size())
			{
				int cmp = valuesA.get(indice) - valuesB.get(indice);
				if(cmp != 0) return cmp;
				indice++;
			}
			return valuesA.size() - valuesB.size();
		}
	}
	
	static Salida darSalida(int id)
	{
		boolean primero = true;
		Salida s = new Salida();
		while(true)
		{
			int valReal = idsMapa.get(id);
			int numero = valReal & mascaraA;
			int siguiente = valReal >>> 12;
			s.values.add(numero);
			if(numero == 0)
				return s;
			id = siguiente;
		}
	}
	
	static void imprimir(Salida s, StringBuilder sb)
	{
		boolean primero = true;
		for(int i : s.values)
		{
			if(!primero)
				sb.append(' ');
			else
				primero = false;
			sb.append(i);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			String linea = sc.nextLine();
			if(linea == null) return;
			if(linea.contains("0")) 
			{
				System.out.flush();
				return;
			}
			linea = linea.trim();
			if(linea.isEmpty()) continue;
			LinkedList <Character> entrada = new LinkedList <Character> ();
			for(char c : linea.toCharArray()) entrada.add(c);
			idActual = 0;
			mapaIds = new IntHashMap();
			idsMapa = new IntHashMap();
			todos = new ArrayList <Nodo> ();
			Nodo raiz = leerRaiz(entrada);
			Collections.sort(todos);
			for(Nodo n : todos)
				n.procesar();
			IntHashMap mapaRaiz = raiz.mapas;
			mapaRaiz.prepararIterador();
			int val;
			ArrayList <Salida> salida = new ArrayList <Salida> ();
			while((val = mapaRaiz.siguiente()) != Integer.MAX_VALUE)
				salida.add(darSalida(val));
			Collections.sort(salida);
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			for(Salida s : salida)
			{
				imprimir(s, sb1);
				sb.append(sb1.toString());
				sb1.setLength(0);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}