package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5644_무선충전 {

	static int T;
	static int moves;
	static int BCs;
	static int[] moveA;
	static int[] moveB;
	static BC[] BCList;
	static int totalSum;

	static class BC {

		int x;
		int y;
		int dist;
		int charge;

		public BC(int x, int y, int dist, int charge) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.charge = charge;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int i = 1; i < T + 1; i++) {
			st = new StringTokenizer(br.readLine());
			moves = Integer.parseInt(st.nextToken());
			BCs = Integer.parseInt(st.nextToken());
			moveA = new int[moves+1];
			moveB = new int[moves+1];
			BCList = new BC[BCs];

			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j < moves+1; j++) {
				moveA[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < moves+1; j++) {
				moveB[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < BCs; j++) {
				st = new StringTokenizer(br.readLine());
				BCList[j] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			int curXYA[] = new int[] {1,1};
			int curXYB[] = new int[] {10,10};
			for (int j = 0; j < moves+1; j++) {
				curXYA = move(curXYA,moveA[j]);
				
				curXYB = move(curXYB,moveB[j]);
				
//				System.out.println(Arrays.toString(curXYA));
//				System.out.println(Arrays.toString(curXYB));
				List<BC> possibleBCA = possibleBC(curXYA);
				
				List<BC> possibleBCB = possibleBC(curXYB);
				
				possibleBCA.add(new BC(0,0,0,0));
				possibleBCB.add(new BC(0,0,0,0));
				
				int curChargeMax = 0;
				for (int k = 0; k < possibleBCA.size(); k++) {
					for (int k2 = 0; k2 < possibleBCB.size(); k2++) {
						int curCharge;
						BC curBCA = possibleBCA.get(k);
						BC curBCB = possibleBCB.get(k2);
						if(curBCA == curBCB) {
							curCharge = (curBCA.charge + curBCB.charge) /2;
						}
						else
							curCharge = curBCA.charge + curBCB.charge;
						if(curCharge > curChargeMax)
							curChargeMax = curCharge;
					}
					
				}
//				System.out.println(curChargeMax);
				totalSum += curChargeMax;
				
				
			}
			System.out.println("#" + i + " " + totalSum);
			totalSum = 0;
		}
	}

	public static int[] move(int[]xy, int vec) {
		int x = xy[0];
		int y = xy[1];
		switch (vec) {
		case 1:
			y--;
			break;
		case 2:
			x++;
			break;
		case 3:
			y++;
			break;
		case 4:
			x--;
			break;
		case 0:
			break;
		default:
			break;
		}
		return new int[] {x,y};
	}
	
	public static List<BC> possibleBC(int[] xy){
		int x = xy[0];
		int y = xy[1];
		List<BC> possibleBCList = new ArrayList<>();
		
		for (int i = 0; i < BCs; i++) {
			if( (Math.abs(BCList[i].x - x) +  Math.abs(BCList[i].y - y)) <= BCList[i].dist) {
				possibleBCList.add(BCList[i]);
			}
		}
		return possibleBCList;
		
	}
	



}
