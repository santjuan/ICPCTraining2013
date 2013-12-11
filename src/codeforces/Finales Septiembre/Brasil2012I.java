import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Brasil2012I 
{
	static class Scanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public Scanner()
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
            
            boolean finished = false;
            public void readNext()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            pos++;
                    if(pos == tam)
                    {
                    	   finished = true;
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
            
            public Integer nextInteger()
            {
            	readNext();
            	if(finished) return null;
            	int d = start;
            	int pos = p;
            	long r = 0;
            	boolean n = buffer[d] == '-';
            	if(n)
            		d++;
            	r -= buffer[d++] - '0';
            	while (d < pos && (r = (r << 1) + (r << 3)) <= 0)
            		r -= buffer[d++] - '0';
            	return (int) (n ? r : -r);
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
            
            public int[] nextIntArray(int n)
    		{
    			int[] res = new int[n];
    			for(int i = 0; i < res.length; i++)
    				res[i] = nextInt();
    			return res;
    		}
            
            public int[][] nextIntMatrix(int n, int m)
    		{
    			int[][] ans = new int[n][];
    			for(int i = 0; i < n; i++)
    				ans[i] = nextIntArray(m);
    			return ans;
    		}
    }
	
	static interface Segment
	{
		public long getMinArea();
		
		public long getMaxArea();
		
		public void setMinArea();
		
		public void setMaxArea();
		
		public boolean setArea(long value);
	}
	
	static class VariableSegment implements Segment
	{
		final int from;
		final int to;
		final int min;
		final int max;
		
		VariableSegment(int f, int t, int mi, int ma)
		{
			from = f;
			to = t;
			min = mi;
			max = ma;
		}

		@Override
		public long getMinArea()
		{
			long last = currentValues[from - 1];
			long total = 0;
			for(int i = from; i <= to; i++)
			{
				long mi = Math.min(last, min);
				long ma = Math.max(last, min);
				total += (mi << 1) + (ma - mi);
				last = min;
			}
			long mi = Math.min(last, currentValues[to + 1]);
			long ma = Math.max(last, currentValues[to + 1]);
			total += (mi << 1) + (ma - mi);
			return total;
		}

		@Override
		public long getMaxArea() 
		{
			long last = currentValues[from - 1];
			long total = 0;
			for(int i = from; i <= to; i++)
			{
				long mi = Math.min(last, max);
				long ma = Math.max(last, max);
				total += (mi << 1) + (ma - mi);
				last = max;
			}
			long mi = Math.min(last, currentValues[to + 1]);
			long ma = Math.max(last, currentValues[to + 1]);
			total += (mi << 1) + (ma - mi);
			return total;
		}

		@Override
		public void setMinArea() 
		{
			for(int i = from; i <= to; i++)
				currentValues[i] = min;
		}

		@Override
		public void setMaxArea() 
		{
			for(int i = from; i <= to; i++)
				currentValues[i] = max;
		}

		public long[] getAreaDecreasing(long max, long area, int size)
		{
			long last = max;
			long[] ans = new long[size];
			for(int i = 0; i < size; i++)
			{
				long current = last;
				while(true)
				{
					long currentA = (last - current) + (current << 1);
					int remaining = size - i - 1;
					// currentA += ((remaining * current) << 1);
					currentA += current;
					if(currentA < area)
					{
						if(current == last) break;
						current++;
						break;
					}
					else
					{
						if(current == 0) break;
						current--;
					}
				}
				ans[i] = current;
				area -= (last - current) + (current << 1);
				last = current;
				if(area < 0) return null;
			}
			area -= last;
			if(area != 0) 
				return null;
			else
				return ans;
		}
		
		public long[] getAreaIncreasing(long max, long area, int size)
		{
			long last = 0;
			long[] ans = new long[size];
			for(int i = 0; i < size; i++)
			{
				long current = last;
				while(true)
				{
					long currentA = i == 0 ? current : (current - last) + (last << 1);
					int remaining = size - i - 1;
					currentA += ((remaining * current) << 1);
					currentA += max - current + (current << 1);
					if(currentA > area)
					{
						current--;
						break;
					}
					else
					{
						if(current == max) return null;
						current++;
					}
				}
				ans[i] = current;
				area -= i == 0 ? current : (current - last) + (last << 1);
				last = current;
				if(area < 0) return null;
			}
			area -= max - last + (last << 1);
			if(area != 0) 
				return null;
			else
				return ans;
		}

		@Override
		public boolean setArea(long value) 
		{
			value -= ((to - from + 2) * min) << 1;
			if(currentValues[from - 1] == min)
			{
				long[] ans = getAreaDecreasing(max - min, value, to - from + 1);
				if(ans == null) return false;
				for(int i = 0; i < ans.length; i++)
					currentValues[from + i] = (int) (ans[ans.length - i - 1] + min);
				return true;
			}
			else
			{
				long[] ans = getAreaIncreasing(max - min, value, to - from + 1);
				if(ans == null) return false;
				for(int i = 0; i < ans.length; i++)
					currentValues[from + i] = (int) (ans[ans.length - i - 1] + min);
				return true;
			}
		}
	}
	
	static class ConstantSegment implements Segment
	{
		int from;
		int to;
		
		public ConstantSegment(int f, int t)
		{
			from = f;
			to = t;
		}
		
		public long getArea()
		{
			long last = currentValues[from];
			long total = 0;
			for(int i = from + 1; i <= to; i++)
			{
				long min = Math.min(last, currentValues[i]);
				long max = Math.max(last, currentValues[i]);
				total += (min << 1) + (max - min);
				last = currentValues[i];
			}
			return total;
		}

		@Override
		public long getMinArea()
		{
			return getArea();
		}

		@Override
		public long getMaxArea() 
		{
			return getArea();
		}

		@Override
		public void setMinArea() 
		{	
		}

		@Override
		public void setMaxArea() 
		{
		}

		@Override
		public boolean setArea(long value)
		{
			return false;
		}
	}
	
	static final int[] currentValues = new int[1000001];
	static final Segment[] allSegments = new Segment[1000001];
	static final int[] currentMins = new int[1000001];
	
	static int makeSegments(int n)
	{
		int j = 0;
		int index = 0;
		for(int i = 0; i <= n; i = j)
		{
			boolean isConstant = currentValues[i] != -1;
			while(j <= n)
				if((currentValues[j] != -1) == isConstant)
					j++;
				else
					break;
			if(isConstant)
				allSegments[index++] = new ConstantSegment(i, j - 1);
			else
				allSegments[index++] = new VariableSegment(i, j - 1, Math.min(currentValues[i - 1], currentValues[j]), Math.max(currentValues[i - 1], currentValues[j]));
		}
		return index;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
//		System.setIn(new FileInputStream("integral.in"));
//		PrintStream ini = System.out;
//		System.setOut(new PrintStream("integral.out"));
		Scanner sc = new Scanner();
		long total = 0;
		int nCaso = 0;
		while(true)
		{
			Integer nTmp = sc.nextInteger();
			if(nTmp == null)
			{
			//	ini.println(total);
				return;
			}
			int n = nTmp;
			int m = sc.nextInt();
			long y = sc.nextLong() * 2;
			int[][] defined = sc.nextIntMatrix(m, 2);
			for(int i = 0; i <= n; i++)
				currentValues[i] = -1;
			for(int[] v : defined)
				currentValues[v[0]] = v[1];
			long time = System.currentTimeMillis();
			total += System.currentTimeMillis() - time;
			int totalSegments = makeSegments(n);
			for(int i = 0; i < totalSegments; i++)
			{
				Segment s = allSegments[i];
				int minArea = (int) s.getMinArea();
				currentMins[i] = minArea;
				y -= minArea;
				s.setMinArea();
			}
			boolean paila = false;
			for(int i = totalSegments - 1; i >= 0; i--)
			{
				Segment sT = allSegments[i];
				if(sT instanceof ConstantSegment) continue;
				VariableSegment s = (VariableSegment) sT;
				int minArea = currentMins[i];
				long diff = s.getMaxArea() - minArea;
				if(diff < y)
				{
					y -= diff;
					s.setMaxArea();
				}
				else
				{
					if(s.setArea(y + minArea))
					{
						y = 0;
						break;
					}
					else
					{
						paila = true;
						break;
					}
				}
			}
			paila = (paila || (y != 0));
			if(paila)
				System.out.println("N");
			else
			{
				System.out.print("S");
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < totalSegments; i++)
				{
					Segment sT = allSegments[i];
					if(sT instanceof ConstantSegment) continue;
					VariableSegment variable = (VariableSegment) sT;
					for(int j = variable.from; j <= variable.to; j++)
					{
						sb.append(' ');
						sb.append(currentValues[j]);
					}
				}
				System.out.println(sb.toString());
			}
		}
	}
}