package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA1248_공통조상 {
	
	static int T;
	static int V;
	static int E;
	static int searchNode1;
	static int searchNode2;
	static Node[] nodeList;
	static List<Integer> searchNodeParent = new ArrayList<>();
	static int sameParent;
	static int subTreeSize = 1;
	
	static class Node{
		int n;
		int parent;
		int left;
		int right;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t < T+1; t++) {
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			searchNode1 = Integer.parseInt(st.nextToken());
			searchNode2 = Integer.parseInt(st.nextToken());
			nodeList = new Node[V+1];
			
			for (int i = 1; i < V+1; i++) {
				Node node = new Node();
				node.n = i;
				nodeList[i] = node;
				
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				nodeList[child].parent = parent;
				if(nodeList[parent].left==0)
					nodeList[parent].left = child;
				else
					nodeList[parent].right = child;
			}
			
			findParent(nodeList[searchNode1]);
			findParent(nodeList[searchNode2]);
			findChild(nodeList[sameParent]);
			
			System.out.println("#" + t + " " + sameParent + " " + subTreeSize);
			subTreeSize = 1;
			searchNodeParent = new ArrayList<>();
		}
		
		
	}
	
	public static void findParent(Node node) {
		if(searchNodeParent.contains(node.parent)) {
			sameParent = node.parent;
			return;
		}
		
		else if(node.parent==1) {
			searchNodeParent.add(node.parent);
			return;
		}
			
		
		else {
			searchNodeParent.add(node.parent);
			findParent(nodeList[node.parent]);
		}
			
	}
	
	public static void findChild(Node node) {
		
		if(node.left!=0) {
			subTreeSize++;
			findChild(nodeList[node.left]);
		}
		if(node.right!=0) {
			subTreeSize++;
			findChild(nodeList[node.right]);
		}
	}

}
