import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class CodeJamC1 
{
	static boolean palindrome(BigInteger numero)
	{
		char[] val = (numero + "").toCharArray();
		int izq = 0;
		int der = val.length - 1;
		while(izq < der && izq != -1 && der != val.length)
		{
			if(val[izq] != val[der])
				return false;
			izq++;
			der--;
		}
		return true;
	}
	
	private static void agregarNumero(int i, boolean impar, int centro)
	{
		StringBuilder sb = new StringBuilder();
		if(!impar)
			sb.append(Integer.toBinaryString(i)).append(new StringBuilder(Integer.toBinaryString(i)).reverse());
		else
			sb.append(Integer.toBinaryString(i)).append((char) (centro + '0')).append(new StringBuilder(Integer.toBinaryString(i)).reverse());
//		if(sb.length() <= 7)
//			outOriginal.println(sb.toString());
		contar(new BigInteger(sb.toString()).pow(2));
	}
	
	static void generarUnos(int tam)
	{
		int numeros = (tam >> 1) - 1;
		int limite = 1 << numeros;
		boolean impar = (tam & 1) == 1;
		for(int i = 0; i < limite; i++)
		{
			if(impar)
			{
				agregarNumero(i + (1 << numeros), true, 0);
				agregarNumero(i + (1 << numeros), true, 1);
				agregarNumero(i + (1 << numeros), true, 2);
			}
			else
				agregarNumero(i + (1 << numeros), false, 0);
		}
	}
	
	static void generarDos(int tam)
	{
		boolean impar = (tam & 1) == 1;
		if(impar)
		{
			StringBuilder sb = new StringBuilder();
			sb.append('2');
			int tamOrig = tam;
			tam--;
			while(tam != 1)
			{
				sb.append('0');
				tam--;
			}
			sb.append('2');
//			if(sb.length() <= 7)
//				outOriginal.println(sb.toString());
			contar(new BigInteger(sb.toString()).pow(2));
			sb.setCharAt((tamOrig >> 1), '1');
//			if(sb.length() <= 7)
//				outOriginal.println(sb.toString());
			contar(new BigInteger(sb.toString()).pow(2));
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			sb.append('2');
			tam--;
			while(tam != 1)
			{
				sb.append('0');
				tam--;
			}
			sb.append('2');
//			if(sb.length() <= 7)
//				outOriginal.println(sb.toString());
			contar(new BigInteger(sb.toString()).pow(2));
		}
	}

	static TreeMap <BigInteger, Integer> fairAndSquare = new TreeMap <BigInteger, Integer> ();  
	
	static int contar(BigInteger a, BigInteger b)
	{
		BigInteger valorUno = fairAndSquare.ceilingKey(a);
		if(valorUno == null || valorUno.compareTo(b) > 0)
			return 0;
		BigInteger valorDos = fairAndSquare.floorKey(b);
		return fairAndSquare.get(valorDos) - fairAndSquare.get(valorUno) + 1;
	}

	static boolean debug = false;
	
	static BigInteger[][] queries;
	static TreeMap <BigInteger, Integer> cuentas = new TreeMap <BigInteger, Integer> (); 
	
	static void contar(BigInteger value)
	{
		if(!palindrome(value))
			return;
		BigInteger menorMayor = cuentas.floorKey(value);
		cuentas.put(menorMayor, cuentas.get(menorMayor) + 1);
	}
	
	static PrintStream outOriginal;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		cuentas.put(BigInteger.valueOf(0), 0);
		cuentas.put(BigInteger.valueOf(10).pow(101), 0);
		outOriginal = System.out;
		if(!debug)
		{
			System.setIn(new FileInputStream("C-large-2.in"));
			System.setOut(new PrintStream("C-large-2.out"));
		}
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		ArrayList <BigInteger[]> queriesA = new ArrayList <BigInteger[]> ();
		for(int i = 0; i < t; i++)
		{
			BigInteger a = sc.nextBigInteger();
			BigInteger b = sc.nextBigInteger();
			outOriginal.println((a + "").length() + " " + (b + "").length());
			queriesA.add(new BigInteger[]{a, b});
			cuentas.put(a, 0);
			cuentas.put(a.subtract(BigInteger.ONE), 0);
			cuentas.put(a.add(BigInteger.ONE), 0);
			cuentas.put(b, 0);
			cuentas.put(b.subtract(BigInteger.ONE), 0);
			cuentas.put(b.add(BigInteger.ONE), 0);
		}
		queries = queriesA.toArray(new BigInteger[0][]);
		contar(BigInteger.valueOf(1));
		contar(BigInteger.valueOf(4));
		contar(BigInteger.valueOf(9));
		long tiempoIni = System.currentTimeMillis();
		for(int i = 2; i <= 100; i++)
			generarDos(i);
		for(int i = 2; i <= 50; i++)
		{
			generarUnos(i);
			outOriginal.println("generando " + i + " " + (System.currentTimeMillis() - tiempoIni));
		}
		sc.close();
		for(int i = 0; i < t; i++)
		{
			int cuantos = 0;
			for(Map.Entry<BigInteger, Integer> e : cuentas.entrySet())
			{
				if(e.getKey().compareTo(queries[i][0]) >= 0 && e.getKey().compareTo(queries[i][1]) <= 0)
				{
					cuantos += e.getValue();
					if(i == 982)
						System.out.println(e.getKey() + " " + e.getValue());
				}
			}
			System.out.println("Case #" + (i + 1) + ": " + cuantos);
		}
		if(debug)
		{
			System.out.flush();
			System.out.close();
		}
	}
}