/*
 * CTU Open Contest 2012
 *
 * Sample solution: Cockchafer
 * Author: Martin Kacer
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class cockchaf {
	StringTokenizer st = new StringTokenizer("");
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	void useLine(String s) { st = new StringTokenizer(s); }
	void useNextLine() throws Exception { useLine(input.readLine()); }
	String nextToken() throws Exception {
		while (!st.hasMoreTokens()) st = new StringTokenizer(input.readLine());
		return st.nextToken();
	}
	int nextInt() throws Exception {
		return Integer.parseInt(nextToken());
	}
	public static void main(String[] args) throws Exception {
		cockchaf instance = new cockchaf();
		while (instance.run()) { /*repeat*/ }
	}
	
	static final double EPS = 1E-8;
	static final double INF = 1E+20;
	static final int MAXLINE = 1000;
	static final int MAXPTS = MAXLINE*2;
	static final DecimalFormat FORM = new DecimalFormat("0.0000");
	
	class Point {
		final int x, y, z;
		final List<Point> edges = new ArrayList<Point>();
		Point(int x, int y, int z) { this.x = x; this.y = y; this.z = z; }
		Point sub(Point o) { return new Point(this.x - o.x, this.y - o.y, this.z - o.z); }
		double mul(Point o) { return x * o.x + y * o.y + z * o.z;}
		double len() { return Math.sqrt(this.mul(this)); }
		double angleTo(Point o) { return Math.acos(this.mul(o) / this.len() / o.len()); }
		@Override public int hashCode() { return (x * 131 + y) * 131 + z; }
		@Override public boolean equals(Object obj) {
			if (this == obj) return true;
			if (!(obj instanceof Point)) return false;
			Point o = (Point)obj;
			return x == o.x && y == o.y && z == o.z;
		}
		@Override public String toString() { return "[" + x + ',' + y + ',' + z + ']'; }
	}
	class Position {
		final Point pos, dir; // dir = target point of the line, dir=pos ==> 
		double dist = INF;
		boolean closed = false;
		Position(Point pos, Point dir) { this.pos = pos; this.dir = dir; }
		@Override public int hashCode() { return pos.hashCode() * 31 + dir.hashCode(); }
		@Override public boolean equals(Object obj) {
			if (this == obj) return true;
			if (!(obj instanceof Position)) return false;
			Position o = (Position)obj;
			return pos.equals(o.pos) && dir.equals(o.dir);
		}
	}
	
	Map<Point,Point> points = new HashMap<Point, Point>();
	Map<Position,Position> positions = new HashMap<Position, Position>();
	
	Point nextPoint() throws Exception {
		int x = nextInt(), y = nextInt(), z = nextInt();
		Point p = new Point(x, y, z);
		if (points.containsKey(p)) p = points.get(p); else points.put(p, p);
		return p;
	}
	Position getPos(Point pos, Point dir) {
		Position p = new Position(pos, dir);
		if (positions.containsKey(p)) p = positions.get(p); else positions.put(p, p);
		return p;
	}
	
	void relax(Position p, double dist) {
		if (!p.closed && dist < p.dist) p.dist = dist;
	}

	boolean run() throws Exception {
		String in = input.readLine();
		if (in == null) return false;
		useLine(in);
		points.clear(); positions.clear();
		int n = nextInt(), s = nextInt(), t = nextInt();
		Point start = nextPoint(), end = nextPoint();
		for (int i = 0; i < n; ++i) {
			Point a = nextPoint(), b = nextPoint();
			a.edges.add(b); b.edges.add(a);
		}
		for (Point p1 : points.keySet()) {
			for (Point p2 : p1.edges) getPos(p1, p2);
			getPos(p1, p1);
		}
		getPos(start, start).dist = 0.0;
		for (Point p2 : start.edges) {
			getPos(start, p2).dist = 0.0;
		}
		Position endpos = getPos(end, end);
		while (!endpos.closed) {
			Position best = null;
			for (Position p : positions.keySet()) {
				if (!p.closed && (best == null || p.dist < best.dist)) best = p;
			}
			best.closed = true;
			if (best.pos == best.dir) continue; // direction-less positions
			Point dirvect = best.dir.sub(best.pos);
			double ptim = best.dist + dirvect.len() / s;
			relax(getPos(best.dir, best.dir), ptim);
			for (Point pd : best.dir.edges)
				relax(getPos(best.dir, pd), ptim + dirvect.angleTo(pd.sub(best.dir)) * 180/Math.PI / t);
		}
		System.out.println(FORM.format(endpos.dist));
		return true;
	}
}