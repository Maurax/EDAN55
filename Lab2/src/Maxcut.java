

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class Maxcut {
	
	private Random r = new Random();
	private int[][] edges;
	private int V;
	boolean[] nodes;
	
	public static void main(String[] args) {
		
		Maxcut m = new Maxcut();
		m.calcMax("pw09_100.9.txt", 100);
		//m.calcMax("matching_1000.txt", 100);
	}
	
	public void calcMax(String fileName, int iter){
		int max = readFile(fileName);
		int sum = max;
		System.out.println(max);
		for(int i = 0;i<iter;i++){
			int temp = alg();
			sum+=temp;
			System.out.println(temp);
			if(temp>max)
				max = temp;
		}
		System.out.println(fileName+":");
		System.out.println("max: " + max + "\n");
		System.out.println("avg: " + (double)sum/iter + "\n");
	}
	
	public int alg(){
		int sum=0;
		nodes = new boolean[V+1];
		for(int i = 1;i<V+1;i++){
			nodes[i] = r.nextBoolean();
		}
		
		for(int i = 0;i<edges.length;i++){
			if(nodes[edges[i][0]]!=nodes[edges[i][1]]){
				sum+=edges[i][2];
			}
		}
		return sum;
	}
	
	public int readFile(String fileName){
		int sum = 0;
		int E;
		boolean[] nodes;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName));){
			
			String line = br.readLine();
			String[] head = line.split(" ");
			V = Integer.parseInt(head[0]);
			E = Integer.parseInt(head[1]);
			
			edges = new int[E][3];
			
			nodes = new boolean[V+1];
			for(int i = 1;i<V+1;i++){
				nodes[i] = r.nextBoolean();
			}
			line = br.readLine();
			int i = 0;
			while(line!=null){
				String[] edge = line.split(" ");
				int l = Integer.parseInt(edge[0]);
				int r = Integer.parseInt(edge[1]);
				int w = Integer.parseInt(edge[2]);
				if(nodes[l]!=nodes[r]){
					sum+=w;
				}
				edges[i++]=new int[]{l, r, w};
				line = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sum;
		
	}
}
