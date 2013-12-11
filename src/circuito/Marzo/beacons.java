import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;



public class beacons 
{
	static class Interval implements Comparable<Interval> {
	    private final double low;
	    private final double high;

	    public Interval(double low, double high) {
		this.low = low;
		this.high = high;
	    }


	    public boolean equals(Object other) {
		if (this == other)
		    return true;
		if (this.getClass().equals(other.getClass())) {
		    Interval otherInterval = (Interval) other;
		    return (this.low == otherInterval.low &&
			    this.high == otherInterval.high);
		}
		return false;
	    }


	    public int hashCode() {
		return Double.valueOf(low).hashCode();
	    }


	    public int compareTo(final Interval other) {
		if (this.low < other.low)
		    return -1;
		if (this.low > other.low)
		    return 1;

		if (this.high < other.high)
		    return -1;
		if (this.high > other.high)
		    return 1;

		return 0;
	    }

	    public String toString() {
		return "Interval[" + this.low + ", " + this.high + "]";
	    }


	    /**
	     * Returns true if this interval overlaps the other.
	     */
	    public boolean overlaps(Interval other) {
		return (this.low <= other.high &&
			other.low <= this.high);
	    }


	    public double getLow() {
		return this.low;
	    }

	    public double getHigh() {
		return this.high;
	    }

	    
	}
	
	static class IntervalTree {
	    private final StatisticUpdate updater;
	    private final RbTree tree;



	    public IntervalTree() {
			this.updater = new IntervalTreeStatisticUpdate();
			this.tree = new RbTree(this.updater);
			((RbNodeInterval) RbNode.NIL).interval = null;
			((RbNodeInterval) RbNode.NIL).max = Integer.MIN_VALUE;
			((RbNodeInterval) RbNode.NIL).min = Integer.MAX_VALUE;
	    }


	    public void insert(Interval interval) {
			RbNode node = new RbNodeInterval(interval.getLow());
			((RbNodeInterval) node).interval = interval;
			this.tree.insert(node);
	    }



	    public int size() {
		return this.tree.size();
	    }



	    // Returns the first matching interval that we can find.
	    public Interval search(Interval interval) {

		RbNode node = tree.root();
		if (node.isNull())
		    return null;

		while ( (! node.isNull()) &&
			(! getInterval(node).overlaps(interval))) {
		    if (canOverlapOnLeftSide(interval, node)) {
			node = node.left;
		    } else if (canOverlapOnRightSide(interval, node)) {
			node = node.right;
		    } else {
			return null;
		    }
		}
		return getInterval(node);
	    }


	    private boolean canOverlapOnLeftSide(Interval interval,
						 RbNode node) {
		return (! node.left.isNull()) &&
		    getMax(node.left) >= interval.getLow();
	    }


	    private boolean canOverlapOnRightSide(Interval interval,
						 RbNode node) {
		return (! node.right.isNull()) &&
		    getMin(node.right) <= interval.getHigh();
	    }



	    // Returns all matches as a list of Intervals
	    public List<Interval> searchAll(Interval interval) {

		if (tree.root().isNull()) {
		    return new ArrayList<Interval>();
		}
		return this._searchAll(interval, tree.root());
	    }


	    private List<Interval> _searchAll(Interval interval, RbNode node) {
		assert (! node.isNull());


		List<Interval> results = new ArrayList<Interval>();
		if (getInterval(node).overlaps(interval)) {
		    results.add(getInterval(node));
		} else {
		}

		if (canOverlapOnLeftSide(interval, node)) {
		    results.addAll(_searchAll(interval, node.left));
		}

		if (canOverlapOnRightSide(interval, node)) {
		    results.addAll(_searchAll(interval, node.right));
		}

		return results;
	    }
  
	    public Interval getInterval(RbNode node) {
	    	return ((RbNodeInterval) node).interval;
	    }


	    public double getMax(RbNode node) {
	    	return ((RbNodeInterval)node).max;
	    }


	    private void setMax(RbNode node, double value) {
	    	((RbNodeInterval)node).max = value;
	    }


	    public double getMin(RbNode node) {
	    	return ((RbNodeInterval)node).min;
	    }


	    private void setMin(RbNode node, double value) {
	    	((RbNodeInterval)node).min = value;
	    }



	    private class IntervalTreeStatisticUpdate 
		implements StatisticUpdate {
		public void update(RbNode node) {
		    setMax(node, max(max(getMax(node.left),
					 getMax(node.right)),
				     getInterval(node).getHigh()));

		    setMin(node, min(min(getMin(node.left),
					 getMin(node.right)),
				     getInterval(node).getLow()));
		}


		private double max(double x, double y) {
		    if (x > y) { return x; }
		    return y;
		}

		private double min(double x, double y) {
		    if (x < y) { return x; }
		    return y;
		}


	    }








	    /**
	     *
	     * Test case code: check to see that the data structure follows
	     * the right constraints of interval trees:
	     *
	     *     o.  They're valid red-black trees
	     *     o.  getMax(node) is the maximum of any interval rooted at that node..
	     *
	     * This code is expensive, and only meant to be used for
	     * assertions and testing.
	     */
	    public boolean isValid() {
		return (this.tree.isValid() && 
			hasCorrectMaxFields(this.tree.root) &&
			hasCorrectMinFields(this.tree.root));
	    }


	    private boolean hasCorrectMaxFields(RbNode node) {
		if (node.isNull())
		    return true;
		return (getRealMax(node) == getMax(node) &&
			hasCorrectMaxFields(node.left) &&
			hasCorrectMaxFields(node.right));
	    }


	    private boolean hasCorrectMinFields(RbNode node) {
		if (node.isNull())
		    return true;
		return (getRealMin(node) == getMin(node) &&
			hasCorrectMinFields(node.left) &&
			hasCorrectMinFields(node.right));
	    }


	    private double getRealMax(RbNode node) {
		if (node.isNull())
		    return Integer.MIN_VALUE;	
		double leftMax = getRealMax(node.left);
		double rightMax = getRealMax(node.right);
		double nodeHigh = getInterval(node).getHigh();

		double max1 = (leftMax > rightMax ? leftMax : rightMax);
		return (max1 > nodeHigh ? max1 : nodeHigh);
	    }


	    private double getRealMin(RbNode node) {
		if (node.isNull())
		    return Integer.MAX_VALUE;	

		double leftMin = getRealMin(node.left);
		double rightMin = getRealMin(node.right);
		double nodeLow = getInterval(node).getLow();

		double min1 = (leftMin < rightMin ? leftMin : rightMin);
		return (min1 < nodeLow ? min1 : nodeLow);
	    }


	}
	
	static class RbNode {
	    public final double key;
	    public boolean color;
	    public RbNode parent;
	    public RbNode left;
	    public RbNode right;

	    public static boolean BLACK = false;
	    public static boolean RED = true;

	    private RbNode() {
	    	key = -1;
		// Default constructor is only meant to be used for the
		// construction of the NIL node.
	    }

	    private RbNode(double key) {
		this.parent = NIL;
		this.left = NIL;
		this.right = NIL;
		this.key = key;
		this.color = RED;
	    }


	    static RbNode NIL;
	    static {
		NIL = new RbNodeInterval();
		NIL.color = BLACK;
		NIL.parent = NIL;
		NIL.left = NIL;
		NIL.right = NIL;
	    }


	    public boolean isNull() {
		return this == NIL;
	    }


	    public String toString() {
		if (this == NIL) { return "nil"; }
		return 
		    "(" + this.key + " " + (this.color == RED ? "RED" : "BLACK") +
		    " (" + this.left.toString() + ", " + this.right.toString() + ")";
	    }
	}

	static class RbNodeInterval extends RbNode
	{
		Interval interval;
		double min;
		double max;
		
		public RbNodeInterval(double key) {
			super(key);
		}
		
		public RbNodeInterval() 
		{
			super();
		}
	}
	
	public interface StatisticUpdate {
	    void update(RbNode node);
	}
	
	static class RbTree {
	    RbNode root;
	    private final RbNode NIL = RbNode.NIL;
	    private final StatisticUpdate updater;

	    public RbTree(StatisticUpdate updater) {
		this.root = NIL;
		this.updater = updater;
	    }


	    public RbTree() {
		this(null);
	    }


	    public void insert(RbNode x) {
		assert (x != null);
		assert (! x.isNull());

		treeInsert(x);
		x.color = RbNode.RED;
		while (x != this.root && x.parent.color == RbNode.RED) {
		    if (x.parent == x.parent.parent.left) {
			RbNode y = x.parent.parent.right;
			if (y.color == RbNode.RED) {
			    x.parent.color = RbNode.BLACK;
			    y.color = RbNode.BLACK;
			    x.parent.parent.color = RbNode.RED;
			    x = x.parent.parent;
			} else {
			    if (x == x.parent.right) {
				x = x.parent;
				this.leftRotate(x);
			    }
			    x.parent.color = RbNode.BLACK;
			    x.parent.parent.color = RbNode.RED;
			    this.rightRotate(x.parent.parent);
			}
		    } else {
			RbNode y = x.parent.parent.left;
			if (y.color == RbNode.RED) {
			    x.parent.color = RbNode.BLACK;
			    y.color = RbNode.BLACK;
			    x.parent.parent.color = RbNode.RED;
			    x = x.parent.parent;
			} else {
			    if (x == x.parent.left) {
				x = x.parent;
				this.rightRotate(x);
			    }
			    x.parent.color = RbNode.BLACK;
			    x.parent.parent.color = RbNode.RED;
			    this.leftRotate(x.parent.parent);
			}
		    }
		}
		this.root.color = RbNode.BLACK;
	    }



	    public RbNode get(double key) {
		RbNode node = this.root;
		while (node != NIL) {
		    if (key == node.key) {
			return node; 
		    }
		    if (key < node.key)  {
			node = node.left; 
		    }
		    else {
			node = node.right; 
		    }
		}
		return NIL;
	    }


	    public RbNode root() {
		return this.root;
	    }


	    public RbNode minimum(RbNode node) {
		assert (node != null);
		assert (! node.isNull());
		while (! node.left.isNull()) {
		    node = node.left;
		}
		return node;
	    }


	    public RbNode maximum(RbNode node) {
		assert (node != null);
		assert (! node.isNull());
		while (! node.right.isNull()) {
		    node = node.right;
		}
		return node;
	    }


	    public RbNode successor(RbNode x) {
		assert (x != null);
		assert (! x.isNull());
		if (! x.right.isNull()) {
		    return this.minimum(x.right);
		}
		RbNode y = x.parent;
		while ( (! y.isNull()) && x == y.right ) {
		    x = y;
		    y = y.parent;
		}
		return y;
	    }


	    public RbNode predecessor(RbNode x) {
		assert (x != null);
		assert (! x.isNull());

		if (! x.left.isNull()) {
		    return this.maximum(x.left);
		}
		RbNode y = x.parent;
		while ( (! y.isNull()) && x == y.left ) {
		    x = y;
		    y = y.parent;
		}
		return y;
	    }




	    void leftRotate(RbNode x) {
		RbNode y = x.right;
		x.right = y.left;
		if (y.left != NIL) {
		    y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == NIL) {
		    this.root = y;
		} else {
		    if (x.parent.left == x) {
			x.parent.left = y;
		    } else {
			x.parent.right = y;
		    }
		}
		y.left = x;
		x.parent = y;

		this.applyUpdate(x);
		// no need to apply update on y, since it'll y is an ancestor
		// of x, and will be touched by applyUpdate().
	    }


	    void rightRotate(RbNode x) {
		RbNode y = x.left;
		x.left = y.right;
		if (y.right != NIL) {
		    y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == NIL) {
		    this.root = y;
		} else {
		    if (x.parent.right == x) {
			x.parent.right = y;
		    } else {
			x.parent.left = y;
		    }
		}
		y.right = x;
		x.parent = y;


		this.applyUpdate(x);
		// no need to apply update on y, since it'll y is an ancestor
		// of x, and will be touched by applyUpdate().
	    }


	    // Note: treeInsert is package protected because it does NOT
	    // maintain RB constraints.
	    void treeInsert(RbNode x) {
		RbNode node = this.root;
		RbNode y = NIL;
		while(node != NIL) {
		    y = node;
		    if (x.key <= node.key) {
			node = node.left;
		    } else {
			node = node.right;
		    }
		}
		x.parent = y;

		if (y == NIL) {
		    this.root = x;
		    x.left = x.right = NIL;
		} else {
		    if (x.key <= y.key) {
			y.left = x;
		    } else {
			y.right = x;
		    }
		}

		this.applyUpdate(x);
	    }


	    // Applies the statistic update on the node and its ancestors.
	    private void applyUpdate(RbNode node) {
		if (this.updater == null)
		    return;
		while (! node.isNull()) {
		    this.updater.update(node);
		    node = node.parent;
		}
	    }


	    /**
	     * Returns the number of nodes in the tree.
	     */
	    public int size() {
		return _size(this.root);
	    }


	    private int _size(RbNode node) {
		if (node.isNull())
		    return 0;
		return 1 + _size(node.left) + _size(node.right);
	    }




	    /**
	     *
	     * Test code: make sure that the tree has all the properties
	     * defined by Red Black trees:
	     *
	     * o.  Root is black.
	     *
	     * o.  NIL is black.
	     *
	     * o.  Red nodes have black children.
	     *
	     * o.  Every path from root to leaves contains the same number of
	     *     black nodes.
	     * 
	     * Calling this function will be expensive, as is meant for
	     * assertion or test code.
	     */
	    public boolean isValid() {
		if (this.root.color != RbNode.BLACK) {
		    return false;
		}
		if (NIL.color != RbNode.BLACK) {
		    return false;
		}
		if (allRedNodesFollowConstraints(this.root) == false) {
		    return false;
		}
		if (isBalancedBlackHeight(this.root) == false) {
		    return false;
		}
		return true;
	    }



	    private boolean allRedNodesFollowConstraints(RbNode node) {
		if (node.isNull())
		    return true;

		if (node.color == RbNode.BLACK) {
		    return (allRedNodesFollowConstraints(node.left) &&
			    allRedNodesFollowConstraints(node.right));
		}

		// At this point, we know we're on a RED node.
		return (node.left.color == RbNode.BLACK &&
			node.right.color == RbNode.BLACK &&
			allRedNodesFollowConstraints(node.left) &&
			allRedNodesFollowConstraints(node.right));
	    }


	    // Check that both ends are equally balanced in terms of black height.
	    private boolean isBalancedBlackHeight(RbNode node) {
		if (node.isNull())
		    return true;
		return (blackHeight(node.left) == blackHeight(node.right) &&
			isBalancedBlackHeight(node.left) &&
			isBalancedBlackHeight(node.right));
	    }


	    // The black height of a node should be left/right equal.
	    private int blackHeight(RbNode node) {
		if (node.isNull())
		    return 0;
		int leftBlackHeight = blackHeight(node.left);
		if (node.color == RbNode.BLACK) {
		    return leftBlackHeight + 1;
		} else {
		    return leftBlackHeight;
		}
	    }

	}
	
	static class Rango implements Comparable <Rango>
	{
		double x;
		double y;
		
		static double EPS = 1e-8;
		
		public Rango(double xx, double yy) 
		{
			x = xx;
			y = yy;
		}

		static boolean isLess(double a, double b)
		{
			return a < b - EPS;
		}
		
		@Override
		public int compareTo(Rango o) 
		{
			if(y < o.x)
				return 1;
			if(o.y < x)
				return -1;
			return 0;
		}
	}
	
	static void addRange(TreeMap <Rango, Rango> mapa, Rango aAgregar)
	{
		Rango otro;
		while((otro = mapa.remove(aAgregar)) != null)
		{
			aAgregar.x = Math.min(aAgregar.x, otro.x);
			aAgregar.y = Math.max(aAgregar.y, otro.y);
		}
		mapa.put(aAgregar, aAgregar);
	}
	
	public static class Scanner
	{
		StringTokenizer st = new StringTokenizer("");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException(e));
			}
		}
		
		String next()
		{
			while(!st.hasMoreTokens())
			{
				String linea = nextLine();
				if(linea == null)
					return null;
				st = new StringTokenizer(linea);
			}
			return st.nextToken();
		}
		
		int nextInt()
		{
			return Integer.parseInt(next());
		}
	}
	
	static class Peak
	{
		Punto point;
		int radius;
		
		public Peak(Punto point, int radius) 
		{
			this.point = point;
			this.radius = radius;
		}
	}
	
	
	static Entrada[] construirEntrada(Punto referencia, Peak p)
	{
		double anguloMedio = Math.atan2(p.point.y - referencia.y, p.point.x - referencia.x);
		double distCuad = referencia.distanciaCuadrada(p.point);
		double beta = Math.asin(p.radius / Math.sqrt(distCuad));
		if(anguloMedio < 0)
			anguloMedio += 2 * Math.PI;
		double distancia = distCuad - p.radius * p.radius;
		if(anguloMedio - beta < 0)
		{
			double ang = 2 * Math.PI + anguloMedio - beta;
			Entrada primera = new Entrada();
			primera.r = new Rango(ang, 2 * Math.PI);
			Entrada segunda = new Entrada();
			segunda.r = new Rango(0, anguloMedio + beta);
			primera.distancia = distancia;
			segunda.distancia = distancia;
			return new Entrada[]{primera, segunda};
		}
		else if(anguloMedio + beta > 2 * Math.PI)
		{
			Entrada primera = new Entrada();
			primera.r = new Rango(anguloMedio - beta, 2 * Math.PI);
			double ang = anguloMedio + beta - (2 * Math.PI);
			Entrada segunda = new Entrada();
			segunda.r = new Rango(0, ang);
			primera.distancia = distancia;
			segunda.distancia = distancia;
			return new Entrada[]{primera, segunda};
		}
		else
		{
			Entrada primera = new Entrada();
			primera.r = new Rango(anguloMedio - beta, anguloMedio + beta);
			primera.distancia = distancia;
			return new Entrada[]{primera};
		}
	}
	
	static class Entrada implements Comparable <Entrada>
	{
		Rango r;
		int cual;
		double distancia;
		double angulo;
		
		public Entrada() 
		{
		}

		public Entrada(Punto referencia, Punto este)
		{
			cual = este.indice;
			distancia = referencia.distanciaCuadrada(este);
			angulo = Math.atan2(este.y - referencia.y, este.x - referencia.x);
			if(angulo < 0)
				angulo += 2 * Math.PI;
		}

		@Override
		public int compareTo(Entrada o) 
		{
			return Double.valueOf(distancia).compareTo(o.distancia);
		}
	}
	
	static class Punto
	{
		double x;
		double y;
		int indice;
		
		Punto(double xx, double yy)
		{
			x = xx;
			y = yy;
		}
		
		double distanciaCuadrada(Punto otro)
		{
			double t = x - otro.x;
			double t1 = y - otro.y;
			return t * t + t1 * t1;
		}
	}
	
	static boolean UVA = true;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int casos = 0;
		if(UVA)
			casos = sc.nextInt();
		Punto[] restantes = new Punto[2000];
		Punto[] restantesTemp = new Punto[2000];
		Punto[] actuales = new Punto[2000];
		Entrada[] eventos = new Entrada[5000];
		Punto[] beacons = new Punto[1024];
		Peak[] peaks = new Peak[1024];
		for(int caso = 0; UVA ? caso < casos : true; caso++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0)
				return;
			for(int i = 0; i < n; i++)
			{
				beacons[i] = new Punto(sc.nextInt(), sc.nextInt());
				beacons[i].indice = i;
			}
			
			for(int i = 0; i < m; i++)
				peaks[i] = new Peak(new Punto(sc.nextInt(), sc.nextInt()), sc.nextInt());
			int tamRestantes = 0;
			int tamRestantesTemp = 0;
			int tamActuales = 0;
			int tamEventos = 0;
			for(int i = 0; i < n; i++)
				restantes[tamRestantes++] = beacons[i];
			int cuenta = 0;
			while(tamRestantes != 0)
			{
				tamActuales = 0;
				actuales[tamActuales++] = restantes[--tamRestantes];
				cuenta++;
				while(tamActuales != 0)
				{
					tamEventos = 0;
					Punto referencia = actuales[--tamActuales];
					for(int j = 0; j < m; j++)
					{
						Entrada[] arreglo = construirEntrada(referencia, peaks[j]);
						for(Entrada a : arreglo)
							eventos[tamEventos++] = a;
					}
					int faltantes = tamRestantes;
					for(int j = 0; j < tamRestantes; j++)
						eventos[tamEventos++] = new Entrada(referencia, restantes[j]);
					Arrays.sort(eventos, 0, tamEventos);
					IntervalTree obstruidos = new IntervalTree();
					for(int j = 0; j < tamEventos && faltantes != 0; j++)
					{
						Entrada e = eventos[j];
						if(e.r != null)
							obstruidos.insert(new Interval(e.r.x, e.r.y));
						else if(obstruidos.search(new Interval(e.angulo, e.angulo)) == null)
						{
							actuales[tamActuales++] = beacons[e.cual];
							faltantes--;
						}
						else
						{
							restantesTemp[tamRestantesTemp++] = beacons[e.cual];
							faltantes--;
						}
					}
					Punto[] tmp = restantes;
					restantes = restantesTemp;
					restantesTemp = tmp;
					tamRestantes = tamRestantesTemp;
					tamRestantesTemp = 0;
				}
			}
			System.out.println(cuenta - 1);
		}
	}
}