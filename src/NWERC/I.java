import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class I
{
	static class Node implements Comparable <Node> 
	{
		   final int name;
		   ArrayList <Node> adjacents = new ArrayList <Node> (100);
		   boolean visited = false; 
		   int lowlink = -1;  
		   int index = -1;    
		   
		   public Node(final int argName) 
		   {
		       name = argName;
		   }
		   
		   public int compareTo(final Node argNode) 
		   {
		       return argNode == this ? 0 : -1;
		   }
	}
	
	static int index = 0;
	static ArrayDeque <Node> stack = new ArrayDeque <Node> ();
	static ArrayList <ArrayList <Node> > SCC = new ArrayList <ArrayList <Node> > ();
	
	public static ArrayList < ArrayList <Node> > SCC(Node[] nodes, int size)
	{
		index = 0;
		SCC.clear();
		stack.clear();
		for(int i = 0; i < size; i++)
			if(nodes[i].index == -1)
				tarjan(nodes[i]);
		return SCC;
	}
	
	public static void tarjan(Node v)
	{
		v.index = index;
		v.lowlink = index;
		index++;
		stack.push(v);
		for(Node n : v.adjacents)
		{
			if(n.index == -1)
			{
				tarjan(n);
				v.lowlink = Math.min(v.lowlink, n.lowlink);
			}
			else if(stack.contains(n))
				v.lowlink = Math.min(v.lowlink, n.index);
		}
		if(v.lowlink == v.index)
		{
			Node n;
			ArrayList <Node> component = new ArrayList <Node> ();
			do
			{
				n = stack.pop();
				component.add(n);
			}
			while(n != v);
			SCC.add(component);
		}
	}
	
	static Node[] nodes;
	static TreeSet <Integer> inComponent = new TreeSet <Integer> ();
	
	public static void main(String[] args) throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt())
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			nodes = new Node[2 * n];
			for(int i = 0; i < 2 * n; i++)
				nodes[i] = new Node(i);
			for(int i = 0; i < m; i++)
			{
				int a = sc.nextInt();
				int b = sc.nextInt();
				int aNormal = a < 0 ? ((-a) - 1) + n : (a - 1);
				int bNormal = b < 0 ? ((-b) - 1) + n : (b - 1);
				int aNegado = a < 0 ? ((-a) - 1) : ((a) - 1) + n;
				int bNegado = b < 0 ? ((-b) - 1) : ((b) - 1) + n;
				nodes[aNegado].adjacents.add(nodes[bNormal]);
				nodes[bNegado].adjacents.add(nodes[aNormal]);
			}
			nodes[n].adjacents.add(nodes[0]);
			ArrayList < ArrayList <Node> > scc = SCC(nodes, n);
			boolean isPossible = true;
			for(ArrayList <Node> ar : scc)
			{
				if(!isPossible)
					break;
				inComponent.clear();
				for(Node node : ar)
				{
					int name = node.name;
					if(name >= n)
						name -= n;
					else
						name += n;
					if(inComponent.contains(name))
					{
						isPossible = false;
						break;
					}
					inComponent.add(node.name);
					if(!isPossible)
						break;
				}
			}
			System.out.println(isPossible ? "yes" : "no");
		}
	}
}
