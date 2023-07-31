import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> deq = new LinkedList<>();
        String ans = "yes";
        // StringTokenizer st = new StringTokenizer(br.readLine());
        
        while(true){
        	
            try{
                String str = br.readLine();
                if(str.equals(".")) break;
                for(int i=0; i<str.length(); i++){
                    char curChar = str.charAt(i);
                    if(curChar == '(' || curChar == '[') {
                        deq.add(curChar);
                    }
                    
                    else if(curChar == ')'){
                        if(deq.peekLast()==null || deq.peekLast() != '('){
                            ans = "no";
                            break;
                        }
                        else{
                            deq.pollLast();
                        }
                            
                    }
                    
                    else if(curChar == ']'){
                        if(deq.peekLast() == null || deq.peekLast() != '['){
                            ans = "no";
                            break;
                        }
                        else{
                            deq.pollLast();
                        }
                    }
                }
                System.out.println(ans);
                ans = "yes";
                deq.clear();
            }
            catch(Exception e){
                
            }
        }
        
        
    }
}