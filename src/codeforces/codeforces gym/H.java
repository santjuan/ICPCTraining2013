
public class H
{
	
	static int menosInf = Integer.MIN_VALUE + 1;
	
	static int maximoImpar(int n)
	{
		if(n == 1)
			return -1;
		int maximoEncontrado = -1;
		for(int i = 1; i < n; i++)
		{
			int a = maximoImpar(i);
			int b = maximoImpar(n - i);
			int c = minimoPar(i);
			int d = minimoPar(n - i);
			int x1 = a == -1 ? c : -(a + 1);
			int x2 = b == -1 ? d : -(b + 1);
			if(x1 >= 0 && x2 >= 0)
				maximoEncontrado = Math.max(maximoEncontrado, Math.max(x1, x2) + 1);
			else if(x1 >= 0)
			{
				int x2P = Math.abs(x2) - 1;
				if(x2P < x1)
					maximoEncontrado = Math.max(maximoEncontrado, x1 + 1);
			}
			else if(x2 >= 0)
			{
				int x1P = Math.abs(x1) - 1;
				if(x1P < x2)
					maximoEncontrado = Math.max(maximoEncontrado, x2 + 1);
			}
		}
		return maximoEncontrado;
	}
	
	static int maximoPar(int n)
	{
		if(n == 1)
			return 0;
		int maximoEncontrado = -1;
		for(int i = 1; i < n; i++)
		{
			int a = maximoPar(i);
			int b = maximoPar(n - i);
			int c = minimoImpar(i);
			int d = minimoImpar(n - i);
			int x1 = a == -1 ? c : -(a + 1);
			int x2 = b == -1 ? d : -(b + 1);
			if(x1 >= 0 && x2 >= 0)
				maximoEncontrado = Math.max(maximoEncontrado, Math.max(x1, x2) + 1);
			else if(x1 >= 0)
			{
				int x2P = Math.abs(x2) - 1;
				if(x2P < x1)
					maximoEncontrado = Math.max(maximoEncontrado, x1 + 1);
			}
			else if(x2 >= 0)
			{
				int x1P = Math.abs(x1) - 1;
				if(x1P < x2)
					maximoEncontrado = Math.max(maximoEncontrado, x2 + 1);
			}
		}
		return maximoEncontrado;
	}
	
	static int minimoPar(int n)
	{
		if(n == 1)
			return 0;
		int maximoEncontrado = Integer.MAX_VALUE;
		for(int i = 1; i < n; i++)
		{
			int a = maximoPar(i);
			int b = maximoPar(n - i);
			int c = maximoImpar(i);
			int d = maximoImpar(n - i);
			int x1 = a == -1 ? c : -(a + 1);
			int x2 = b == -1 ? d : -(b + 1);
			if(x1 >= 0 && x2 >= 0)
				maximoEncontrado = Math.min(maximoEncontrado, Math.min(x1, x2) + 1);
			else if(x1 >= 0)
			{
				int x2P = Math.abs(x2) - 1;
				if(x2P < x1)
					maximoEncontrado = Math.min(maximoEncontrado, x1 + 1);
			}
			else if(x2 >= 0)
			{
				int x1P = Math.abs(x1) - 1;
				if(x1P < x2)
					maximoEncontrado = Math.min(maximoEncontrado, x2 + 1);
			}
		}
		return maximoEncontrado == Integer.MAX_VALUE ? -1 : maximoEncontrado;
	}
	
	static int minimoImpar(int n)
	{
		if(n == 1)
			return -1;
		int maximoEncontrado = Integer.MAX_VALUE;
		for(int i = 1; i < n; i++)
		{
			int a = maximoImpar(i);
			int b = maximoImpar(n - i);
			int c = maximoPar(i);
			int d = maximoPar(n - i);
			int x1 = a == -1 ? c : -(a + 1);
			int x2 = b == -1 ? d : -(b + 1);
			if(x1 >= 0 && x2 >= 0)
				maximoEncontrado = Math.min(maximoEncontrado, Math.min(x1, x2) + 1);
			else if(x1 >= 0)
			{
				int x2P = Math.abs(x2) - 1;
				if(x2P < x1)
					maximoEncontrado = Math.min(maximoEncontrado, x1 + 1);
			}
			else if(x2 >= 0)
			{
				int x1P = Math.abs(x1) - 1;
				if(x1P < x2)
					maximoEncontrado = Math.min(maximoEncontrado, x2 + 1);
			}
		}
		return maximoEncontrado == Integer.MAX_VALUE ? -1 : maximoEncontrado;
	}

	
	public static void main(String[] args)
	{
		for(int i = 1; i < 101; i++)
			System.out.println(i + " " + maximoImpar(i) + " " + maximoPar(i));
	}
}