import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class BrasilB 
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
            
            <T> T[] nextObjectArray(Class <T> clazz, int size)
    		{
    			@SuppressWarnings("unchecked")
    			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
    			for(int c = 0; c < 3; c++)
    			{
    				Constructor <T> constructor;
    				try 
    				{
    					if(c == 0)
    						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
    					else if(c == 1)
    						constructor = clazz.getDeclaredConstructor(Scanner.class);
    					else
    						constructor = clazz.getDeclaredConstructor();
    				} 
    				catch(Exception e)
    				{
    					continue;
    				}
    				try
    				{
    					for(int i = 0; i < result.length; i++)
    					{
    						if(c == 0)
    							result[i] = constructor.newInstance(this, i);
    						else if(c == 1)
    							result[i] = constructor.newInstance(this);
    						else
    							result[i] = constructor.newInstance();	
    					}
    				}
    				catch(Exception e)
    				{
    					throw new RuntimeException(e);
    				}
    				return result;
    			}
    			throw new RuntimeException("Constructor not found");
    		}
    }
	
	static class Point
	{
		int x;
		int y;
		
		Point(int xx, int yy)
		{
			x = xx;
			y = yy;
		}
		
		public Point(Scanner sc)
		{
			x = sc.nextInt();
			y = 0;
		}
	}
	
	static int currentX;
	
	static class Answer
	{
		Segment where;
		int xValue;
		
		Answer(Segment w, int x)
		{
			where = w;
			xValue = x;
		}
	}
	
	static int currentId = 0;
	
	static class Segment implements Comparable <Segment>
	{
		int id = currentId++;
		Point leftMost;
		Point rightMost;
		Segment next;
		int nextHit;
		Answer answer;
		boolean isQuery;
		boolean inMap = false;
		ArrayList <Segment> sons = new ArrayList <Segment> ();
		
		public Segment(Scanner sc)
		{
			leftMost = new Point(sc.nextInt(), sc.nextInt());
			rightMost = new Point(sc.nextInt(), sc.nextInt());
			if(leftMost.x > rightMost.x)
			{
				Point tmp = leftMost;
				leftMost = rightMost;
				rightMost = tmp;
			}
		}
		
		Segment(Point p)
		{
			leftMost = rightMost = p;
			isQuery = true;
		}
		
		double currentY()
		{
			if(rightMost.x == leftMost.x) return leftMost.y;
			double m = rightMost.y - leftMost.y;
			m /= rightMost.x - leftMost.x;
			double b = leftMost.y - m * leftMost.x;
			return m * currentX + b;
		}
		
		static int compare(double x, double y)
		{
			if(x < y) return -1;
			else if(x == y) return 0;
			else return 1;
		}
		@Override
		public int compareTo(Segment o) 
		{
			double a = currentY();
			double b = o.currentY();
			int cmp = compare(a, b);
			if(cmp == 0) return id - o.id;
			return cmp;
		}
	}
	
	static class Event implements Comparable <Event>
	{
		int xValue;
		int yValue;
		int isClosing;
		Segment segment;
		Event next;
		
		Event(int x, int y, Segment s, int sC)
		{
			xValue = x;
			yValue = y;
			segment = s;
			isClosing = sC;
		}
		
		@Override
		public int compareTo(Event o) 
		{    
			if(xValue == o.xValue)
			{
				if(isClosing == o.isClosing)
					return o.yValue - yValue;
				else
					return isClosing - o.isClosing;
			}
			else
				return xValue - o.xValue;
		}
	}
	
	static void addEvents(Segment s, Event[] events)
	{
		if(s.isQuery)
		{
			events[eventCount++] = new Event(s.rightMost.x, s.rightMost.y, s, 0);
			s.inMap = true;
		}
		else
		{
			events[eventCount++] = new Event(s.leftMost.x, s.leftMost.y, s, 0);
			events[eventCount++] = new Event(s.rightMost.x, s.rightMost.y, s, 1);
		}
	}
	
	static class FastArrayDeque
	{
		final Segment[] queue;
		int head, tail;
		
		FastArrayDeque(int size)
		{
			queue = new Segment[size];
			clear();
		}
		
		
		void add(Segment val)
		{
			queue[++tail] = val;
		}
		
		Segment poll()
		{
			return queue[head++];
		}
		
		void clear()
		{
			head = 0; tail = -1;
		}
		
		boolean isEmpty()
		{
			return head > tail;
		}
	}
	
	static int eventCount = 0;
	
	static final Event[] eventTmp = new Event[1000010];
	static final Event[] eventTmp2 = new Event[300000];
	
	
	static void sort(Event[] allEvents, int length)
	{
		Arrays.fill(eventTmp, null);
		for(int i = 0; i < length; i++)
		{
			Event e = allEvents[i];
			int index = e.xValue + 1;
			if(eventTmp[index] == null)
				eventTmp[index] = e;
			else
			{
				e.next = eventTmp[index];
				eventTmp[index] = e;
			}
		}
		int current = 0;
		for(int i = 0; i < eventTmp.length; i++)
		{
			Event e = eventTmp[i];
			if(e != null)
			{
				if(e.next == null)
					allEvents[current++] = eventTmp[i];
				else
				{
					int currentCount = 0;
					while(e != null)
					{
						eventTmp2[currentCount++] = e;
						e = e.next;
					}
					Arrays.sort(eventTmp2, 0, currentCount);
					for(int j = 0; j < currentCount; j++)
						allEvents[current++] = eventTmp2[j];
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		FastArrayDeque queue = new FastArrayDeque(200100);
		Event[] allEvents = new Event[201100];
		Segment[] allSegments = new Segment[200100];
		while(true)
		{
			Integer nTmp = sc.nextInteger();
			if(nTmp == null)
			{
				bw.flush();
				return;
			}
			int allSegmentSize = 0;
			int n = nTmp;
			int c = sc.nextInt();
			Segment[] segments = new Segment[n];
			for(int i = 0; i < n; i++)
				segments[i] = new Segment(sc);
			Point[] queries = new Point[c];
			for(int i = 0; i < c; i++)
				queries[i] = new Point(sc);
			Segment[] segmentQueries = new Segment[queries.length];
			for(int i = 0; i < queries.length; i++)
				segmentQueries[i] = new Segment(queries[i]);
			Segment ceiling = new Segment(new Point(-1, 1000001));
			ceiling.rightMost = new Point(1000001, 1000001);
			ceiling.isQuery = false;
			eventCount = 0;
			for(Segment s : segments)
			{
				addEvents(s, allEvents);
				allSegments[allSegmentSize++] = s;
			}
			for(Segment s : segmentQueries)
			{
				addEvents(s, allEvents);
				allSegments[allSegmentSize++] = s;
			}
			addEvents(ceiling, allEvents);
			allSegments[allSegmentSize++] = ceiling;
			sort(allEvents, eventCount);
			TreeSet <Segment> currentSegments = new TreeSet <Segment> ();
			//ConcurrentSkipListSet<Segment> currentSegments = new ConcurrentSkipListSet <Segment> ();
			for(int i = 0; i < eventCount; i++)
			{
				Event e = allEvents[i];
				currentX = e.xValue;
				if(e.segment.inMap)
					currentSegments.remove(e.segment);
				int max = Math.max(e.segment.leftMost.y, e.segment.rightMost.y);
				if(e.yValue == max && e.segment != ceiling && (e.segment.isQuery || e.segment.leftMost.y != e.segment.rightMost.y))
				{
					e.segment.next = currentSegments.ceiling(e.segment);
					e.segment.nextHit = e.xValue;
				}
				if(!e.segment.inMap)
				{
					e.segment.inMap = true;
					currentSegments.add(e.segment);
				}
				else
					e.segment.inMap = false;
			}
			queue.clear();
			for(int i = 0; i < allSegmentSize; i++)
			{
				Segment s = allSegments[i];
				if(s.next != null)
					s.next.sons.add(s);
				else
				{
					queue.add(s);
					s.answer = new Answer(s, -1);
				}
			}
			while(!queue.isEmpty())
			{
				Segment current = queue.poll();
				for(Segment s : current.sons)
				{
					if(s.answer == null)
					{
						if(current.answer.xValue == -1)
							s.answer = new Answer(current.answer.where, s.nextHit);
						else
							s.answer = current.answer;
						queue.add(s);
					}
				}
			}
			for(Segment s : segmentQueries)
			{
				if(s.answer.where == ceiling)
					bw.write(((int) s.answer.xValue) + "\n");
				else
					bw.write(((int) s.answer.xValue) + " " + s.answer.where.leftMost.y + "\n");
			}
		}
	}
}