import java.io.InputStreamReader;


public class boxes 
{
	static class SuperScanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public SuperScanner()
            {
                    buffer = new char[15000000];
                    isr = new InputStreamReader(System.in);         
                    read(0);
            }
            
            void read(int init)
            {
                    try
                    {
                            int tam;
                            if((tam = isr.read(buffer, init, buffer.length - init)) < buffer.length - init)
                            {
                                    if(tam < 0)
                                            tam = 0;
                                    buffer[tam] = '\0';
                            }
                            else
                            	throw(new RuntimeException());
                    }
                    catch(Exception e)
                    {
                            throw(new RuntimeException());
                    }
            }
            
            public void readNext()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            pos++;
                    if(pos == tam)
                    {
                            read(0);
                            readNext();
                            return;
                    }
                    start = pos;
                    while(pos < tam && buffer[pos] > ' ')
                            pos++;
                    if(pos == tam)
                    {
                            System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                            read(buffer.length - start);
                            p = start;
                            readNext();
                            return;
                    }
                    p = pos;
            }
            
            public String next()
            {
                    readNext();
                    return new String(buffer, start, p - start);
            }
            
            
            public long nextLong()
            {
            	readNext();
            	int d = start;
            	int pos = p;
            	long r = 0;
            	boolean n = buffer[d] == '-';
            	if(n)
            		d++;
            	r -= buffer[d++] - '0';
            	while (d < pos && (r = (r << 1) + (r << 3)) <= 0)
            		r -= buffer[d++] - '0';
            	return n ? r : -r;
            }
            
            public int nextInt()
            {
                    return (int) nextLong();
            }
            
            public double nextDouble()
            {
                    return Double.parseDouble(next());
            }
            
            public String nextLine()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] != '\n')
                            pos++;
                    if(pos == tam)
                    {
                            read(0);
                            return nextLine();
                    }
                    start = ++pos;
                    while(pos < tam && buffer[pos] != '\n')
                            pos++;
                    if(pos >= tam)
                    {
                            System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                            read(buffer.length - start);
                            return nextLine();
                    }
                    p = pos;
                    return new String(buffer, start, p - start);
            }
            
            public boolean EOF()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            if(buffer[pos++] == '\0')
                                    return true;
                    pos++;
                    if(pos >= tam)
                    {
                            if(p == 0)
                                    return false;
                            System.arraycopy(buffer, p, buffer, 0, buffer.length - p);
                            read(buffer.length - p);
                            p = 0;
                            return EOF();
                    }
                    return false;
            }
    }
	
	static class City implements Comparable <City>
	{
		int personas;
		int cajas = 1;

		public City(int p)
		{
			personas = p;
		}

		@Override
		public int compareTo(City o) 
		{
			int x = personas * o.cajas;
			int y = o.personas * cajas;
			return x > y ? -1 : x == y ? 0 : 1;
		}

		public int getCount() 
		{
			return personas / cajas + ((personas % cajas == 0) ? 0 : 1);
		}
		
		public int getCount(int cajas) 
		{
			return personas / cajas + ((personas % cajas == 0) ? 0 : 1);
		}
		
		public int getBoxes(int total) 
		{
			return personas / total + ((personas % total == 0) ? 0 : 1);
		}
	}
	
	static City[] allCities;
	static int totalBoxes;
	
	static boolean isPossible(int value)
	{
		int total = 0;
		for(City c : allCities)
			total += c.getBoxes(value);
		return total <= totalBoxes;
	}
	
	static int binarySearch(int a, int b)
	{
		if(a == b)
			return a;
		if(a + 1 == b)
			return isPossible(a) ? a : b;
		int mid = (a + b) >>> 1;
        if(isPossible(mid)) return binarySearch(a, mid);
        else return binarySearch(mid + 1, b);
	}
	
	public static void main(String[] args)
	{
		SuperScanner sc = new SuperScanner();
		while(true)
		{
			int N = sc.nextInt();
			int B = sc.nextInt();
			totalBoxes = B;
			if(N == -1 && B == -1) return;
			allCities = new City[N];
			int highest = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++)
			{
				City n = new City(sc.nextInt());
				allCities[i] = n;
				highest = Math.max(highest, n.personas);
			}
			System.out.println(binarySearch(1, highest));
		}
	}
}