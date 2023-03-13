package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20055_컨베이어 {
	static int N;
	static int K;
	static List<valRobot> valList;
	static int zeroCnt;
	static int stage = 1;
	
	static class valRobot{
		int val;
		boolean robot;
		
		
		public valRobot(int val, boolean robot) {
			super();
			this.val = val;
			this.robot = robot;
		}


		@Override
		public String toString() {
			return val + " ";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		valList = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			valList.add(new valRobot(Integer.parseInt(st.nextToken()), false));
		}
		
		while(K > zeroCnt) {
			Deque<valRobot> valDeque = new ArrayDeque<>(valList);	
			valDeque.addFirst(valDeque.pollLast());
			valList = new LinkedList<>(valDeque);
			
			valList.set(N-1, new valRobot(valList.get(N-1).val, false)); //N자리에서 로봇 내림 (컨베이어 이동하면서 로봇도 이동하므)
			
			for (int i = N-2; i >= 0; i--) {
				if(valList.get(i).robot && valList.get(i+1).val>0 && !valList.get(i+1).robot) {
					valRobot newvalRobot = valList.get(i+1);
					newvalRobot.robot = true;
					if(--newvalRobot.val == 0) zeroCnt++;
					valList.set(i+1, newvalRobot);
					valList.set(i, new valRobot(valList.get(i).val, false));//로봇 옮겨줬으므로 robot = false
				}
			}
			
			valList.set(N-1, new valRobot(valList.get(N-1).val, false)); //N자리에서 로봇 내림 (로봇만움직였으므로)
			
			
			
			if(valList.get(0).val>0) {
				if(valList.get(0).val==1) zeroCnt++;
				valList.set(0, new valRobot(--valList.get(0).val, true));
			}

			stage++;
			
//			System.out.println(valList);
			
		}
		System.out.println(stage-1);
		
	}

}
