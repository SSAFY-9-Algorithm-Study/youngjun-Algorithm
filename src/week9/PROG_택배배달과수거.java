package week9;

public class PROG_택배배달과수거 {
	
	public static void main(String[] args) {
		System.out.println(solution(4, 5, new int[] {1,0,3,1,2}, new int[] {0,3,0,4,0}));
		System.out.println(solution(2, 7, new int[] {1,0,2,0,1,0,2}, new int[] {0,2,0,1,0,2,0}));
	}
	

	static public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        //현재 남아있는 배달, 수거 개수 계산
        int delivCnt = 0;
        int pickupCnt = 0;
        for (int i = 0; i < n; i++) {
			delivCnt+=deliveries[i];
			pickupCnt+=pickups[i];
		}
        
      //init으로 현재가야할 곳 계산
        int curMaxIdx = 0;
    	for (int i = n-1; i >=0 ; i--) {
			if(deliveries[i]>0 || pickups[i]>0) {
				curMaxIdx = i;
				break;
			}
		}
        
    	//모든 수거/배달이 끝날때까지
        while(delivCnt>0 || pickupCnt>0) {
        	
        	//갔다 오는 거리만큼 더해줌
        	answer+=((curMaxIdx+1)*2);
        	// 다음 갔다 올 거리
        	int nextMaxIdx = 0;
        	
        	// 가는 길에 배달
        	int leftCaps = cap;
        	for (int i = curMaxIdx; i >=0 ; i--) {
        		//더 이상 배달이 안되면
				if(leftCaps==0) {
					//물건이 남아있는 가장 큰 인덱스 가져옴
					for (int j = i; j >= 0; j--) {
						if(deliveries[j]>0) {
							nextMaxIdx = Math.max(j, nextMaxIdx);
							break;
						}
					}
					
					break;
				}
				
				// caps를 소모하고도 남아있을 때
				if(leftCaps<deliveries[i]) {
					deliveries[i]-=leftCaps;
					delivCnt-=leftCaps;
					nextMaxIdx = Math.max(i, nextMaxIdx);
					leftCaps = 0;
					break;
				}
				// 남은 caps로 다 뺄 수 있을 때
				else {
					leftCaps-=deliveries[i];
					delivCnt-=deliveries[i];
					deliveries[i]=0;
				}
			}
        	
        	// 오는 길에 수거
        	leftCaps = cap;
        	for (int i = curMaxIdx; i >=0 ; i--) {
				if(leftCaps==0) {
					
					for (int j = i; j >= 0; j--) {
						if(pickups[j]>0) {
							nextMaxIdx = Math.max(j, nextMaxIdx);
							break;
						}
					}
					break;
				}
				
				if(leftCaps<pickups[i]) {
					pickups[i]-=leftCaps;
					pickupCnt-=leftCaps;
					nextMaxIdx = Math.max(i, nextMaxIdx);
					leftCaps = 0;
					break;
				}
				
				else {
					leftCaps-=pickups[i];
					pickupCnt-=pickups[i];
					pickups[i]=0;
				}
			}
        	
        	curMaxIdx = nextMaxIdx;
        }
        return answer;
    }

}
