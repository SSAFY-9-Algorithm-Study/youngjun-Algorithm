package week21;

class Solution {
	public int solution(String s) {
		return divAndCon(s) ? 1 : 0;
	}

	private boolean divAndCon(String s) {
		if(s.length()==0 || s==null) return true;
		boolean initStart = false;
		char firstChar = 'A';
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < s.length() ; i++) {
			char curChar = s.charAt(i);
			if(!initStart) {
				firstChar = curChar;
				initStart = true;
			}
			else if(curChar == firstChar) {
//				System.out.println("same");
				if(!divAndCon(sb.toString())) return false;
				sb.setLength(0);
				initStart = false;
			}
			else {
				sb.append(curChar);
			}
		}
		if(initStart) return false;
//		System.out.println("true!");
		return true;
		
	}
}

//a bbcd a a dc a


//
//class Solution {
//    public int solution(String s) {
//
//        Deque<Character> deq = new LinkedList();
//
//        for (int i = 0; i < s.length(); i++) {
//
//            char c = s.charAt(i);
//
//            if (!deq.isEmpty() && deq.peek() == c) {
//                deq.pollLast();
//            } else {
//                deq.add(c);
//            }
//        }
//
//        if (deq.isEmpty()) {
//            return 1;
//        }
//        return 0;
//    }
//}