package eg.edu.alexu.csd.datastructure.maze.cs31_cs60;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import eg.edu.alexu.csd.datastructure.linkedList.cs31_cs60.Double_Linked_List;
import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs31_cs60.Queue_LinkedList;
import eg.edu.alexu.csd.datastructure.stack.cs60.Stack;

/**
 * . Maze
 *
 * @author EL-hamd
 *
 */
public class Maze implements IMazeSolver {
	/**.
	 * . int
	 */
	private int nr = 0, nc = 0;
	/**
	 * . boolean
	 */
	private boolean flag = false;
	/**
	 * . string
	 */
	private String[] str;
	/**
	 * . term
	 */
	private term t = new term(0, 0, 0, 0);
	/**
	 * . queue
	 */
	private Queue_LinkedList q = new Queue_LinkedList();
	/**
	 * . stack
	 */
	private Stack s3 = new Stack();
	/**
	 * . stack
	 */
	private Stack s2 = new Stack();

	/**
	 * . point
	 */
	private Point pp = new Point();
	/**
	 * . int
	 */
	private int i = 0, j = 0;
	/**
	 * . boolean
	 */
	private Boolean goal = false;
	/**
	 * . visit
	 */
	private boolean[][] visited;

	@Override
	public final int[][] solveBFS(final File maze) {
		// TODO Auto-generated method stub
		try {
			str = readFile(maze);
		} catch (Exception e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}
		if (nr <= 0 || nc <= 0 || nc != str[0].length()) {
			throw new UnsupportedOperationException();
		}
		for (i = 0; i < str.length; i++) {
			for (j = 0; j < str[i].length(); j++) {
				if (str[i].charAt(j) == 'E') {
					flag = true;
					break;
				}

			}
			if (flag) {
				break;
			}
		}
		if (i == str.length && j == str[i].length()) {
			throw new UnsupportedOperationException();
		}
		flag = false;
		for (i = 0; i < str.length; i++) {
			for (j = 0; j < str[i].length(); j++) {
				if (str[i].charAt(j) == 'S') {
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		if (i == str.length && j == str[i].length()) {
			throw new UnsupportedOperationException();
		}
		visited = new boolean[str.length][str[0].length()];
		Point p = new Point(i, j);
		q.enqueue(p);
		s3.push(new term(i, j, 0, 0));
		while (!q.isEmpty()) {
			p = (Point) q.dequeue();
			i = p.x;
			j = p.y;

			visited[i][j] = true;
			if (isEnd(i, j)) {
				break;
			}
			if (!(i < 0 
					||
					i >= str.length 
					||
					j - 1 < 0 
					||
					j - 1 >= str[i].length())) {
				if (str[i].charAt(j - 1) != '#' 
						&&
						visited[i][j - 1] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i;
					p.y = j - 1;
					q.enqueue(p);
					t.x = i;
					t.y = j - 1;
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);
				}
			}

			if (!(i + 1 < 0
					||
					i + 1 >= str.length
					||
					j < 0
					||
					j >= str[i].length())) {
				if (str[i + 1].charAt(j) != '#'
						&&
						visited[i + 1][j] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i + 1;
					p.y = j;
					q.enqueue(p);
					t.x = i + 1;
					t.y = j;
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);

				}
			}
			if (!(i - 1 < 0
					||
					i - 1 >= str.length
					||
					j < 0
					||
					j >= str[i].length())) {
				if (str[i - 1].charAt(j) != '#'
						&&
						visited[i - 1][j] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i - 1;
					p.y = j;
					q.enqueue(p);
					t.x = i - 1;
					t.y = j;
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);

				}
			}

			if (!(i < 0
					||
					i >= str.length
					||
					j + 1 < 0
					||
					j + 1 >= str[i].length())) {
				if (str[i].charAt(j + 1) != '#'
						&&
						visited[i][j + 1] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i;
					p.y = j + 1;
					t.x = i;
					t.y = j + 1;
					q.enqueue(p);
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);

				}
			}

		}
	    int[][] arr = new int[s2.size()][2];
		for (int index = 0; !s2.isEmpty(); index++) {
			Point ptemp = (Point) s2.pop();
			arr[index][0] = ptemp.x;
			arr[index][1] = ptemp.y;

		}
		if (goal) {
			return arr;
		}
		return null;
	}

	@Override
	public final int[][] solveDFS(final File maze) {
		// TODO Auto-generated method stub

		try {
			str = readFile(maze);
		} catch (Exception e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}
		Stack s = new Stack();
		if (nr <= 0 || nc <= 0 || nc != str[0].length()) {
			throw new UnsupportedOperationException();
		}
		visited = new boolean[str.length][str[0].length()];
		for (i = 0; i < str.length; i++) {
			for (j = 0; j < str[i].length(); j++) {
				if (str[i].charAt(j) == 'E') {
					flag = true;
					break;
				}

			}
			if (flag) {
				break;
			}
		}
		if (i == str.length && j == str[i].length()) {
			throw new UnsupportedOperationException();
		}
		flag = false;
		for (i = 0; i < str.length; i++) {
			for (j = 0; j < str[i].length(); j++) {
				if (str[i].charAt(j) == 'S') {
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		if (i == str.length && j == str[i].length()) {
			throw new UnsupportedOperationException();
		}
		Point p = new Point(i, j);
		s.push(p);
		s3.push(new term(i, j, 0, 0));
		while (!s.isEmpty()) {
			p = (Point) s.pop();
			i = p.x;
			j = p.y;

			visited[i][j] = true;
			if (isEnd(i, j)) {
				break;
			}
			if (!(i < 0
					||
					i >= str.length
					||
					j + 1 < 0
					||
					j + 1 >= str[i].length())) {
				if (str[i].charAt(j + 1) != '#'
						&&
						visited[i][j + 1] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i;
					p.y = j + 1;
					t.x = i;
					t.y = j + 1;
					s.push(p);
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);
				}
			}

			if (!(i < 0
					||
					i >= str.length
					||
					j - 1 < 0
					||
					j - 1 >= str[i].length())) {
				if (str[i].charAt(j - 1) != '#'
						&&
						visited[i][j - 1] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i;
					p.y = j - 1;
					s.push(p);
					t.x = i;
					t.y = j - 1;
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);

				}
			}
			if (!(i - 1 < 0
					||
					i - 1 >= str.length
					||
					j < 0
					||
					j >= str[i].length())) {
				if (str[i - 1].charAt(j) != '#'
						&&
						visited[i - 1][j] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i - 1;
					p.y = j;
					s.push(p);
					t.x = i - 1;
					t.y = j;
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);

				}
			}

			if (!(i + 1 < 0
					||
					i + 1 >= str.length
					||
					j < 0
					||
					j >= str[i].length())) {
				if (str[i + 1].charAt(j) != '#'
						&&
						visited[i + 1][j] == false) {
					t = new term(0, 0, 0, 0);
					p = new Point(0, 0);
					p.x = i + 1;
					p.y = j;
					s.push(p);
					t.x = i + 1;
					t.y = j;
					pp = new Point(0, 0);
					pp.x = i;
					pp.y = j;
					t.parent = new Point(i, j);
					s3.push(t);

				}
			}

		}
		int[][] arr = new int[s2.size()][2];
		for (int index = 0; !s2.isEmpty(); index++) {
			Point ptemp = (Point) s2.pop();
			arr[index][0] = ptemp.x;
			arr[index][1] = ptemp.y;

		}
		if (goal) {
			return arr;
		}
		return null;
	}

	/**
	 * . readfile
	 *
	 * @param m
	 *            first
	 * @return stringarray
	 * @throws Exception
	 *             null
	 */
	public String[] readFile(final File m) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(m));
		String s;
		LinkedList<String> r = new LinkedList<String>();
		s = in.readLine();
		int i;
		for (i = 0; i < s.length(); i++) {
			if (s.substring(i, i + 1).equals(" ")) {
				break;
			}
		}
		nr = Integer.valueOf(s.substring(0, i));
		nc = Integer.valueOf(s.substring(i + 1));
		while ((s = in.readLine()) != null) {
			r.add(s);
		}
		in.close();
		return r.toArray(new String[r.size()]);
	}

	/**
	 * . private
	 *
	 * @param i
	 *            first
	 * @param j
	 *            second
	 * @return boolean
	 */
	private Boolean isEnd(int i, int j) {
		if (str[i].charAt(j) == 'E') {
			goal = true;
			while (!s3.isEmpty()) {
				term temp2 = new term(0, 0, 0, 0);
				temp2 = (term) s3.pop();
				if (i == temp2.x && j == temp2.y) {
					Point p2 = new Point(i, j);
					s2.push(p2);
					i = (temp2.parent).x;
					j = (temp2.parent).y;
				}

			}
			return true;
		}
		return false;
	}
}
