package week1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/13015

public class P13015 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        String filledNum = "";
        String unfilledNum = "";
        String blankSpace = "";
        String startSpace = "";
        for (int i = 0; i < num; i++) {
            filledNum+="*";
            unfilledNum = (i==0 || i==num-1) ? unfilledNum+"*" : unfilledNum+" ";
            blankSpace+="  ";
        }
        String newBlankSpace = blankSpace.substring(0,2*num-3);


        System.out.println(filledNum + newBlankSpace + filledNum);

        for (int i = 1; i < num-1; i++) {
            String middleBlankSpace;
            startSpace=newBlankSpace.substring(0,i);
            if(2*num-3-2*i > 0)
                middleBlankSpace = newBlankSpace.substring(0,2*num-3-2*i);
            else middleBlankSpace = "";
            System.out.println(startSpace+unfilledNum+middleBlankSpace+unfilledNum);
        }

        for (int i = 0; i < num-1; i++) {
            System.out.print(" ");
        }

        System.out.print(unfilledNum+unfilledNum.substring(1));
        System.out.println();

        newBlankSpace = blankSpace.substring(0,2*num-3);

        for (int i = num-2; i > 0; i--) {
            String middleBlankSpace;
            startSpace=newBlankSpace.substring(0,i);
            if(2*num-3-2*i > 0)
                middleBlankSpace = newBlankSpace.substring(0,2*num-3-2*i);
            else middleBlankSpace = "";
            System.out.println(startSpace+unfilledNum+middleBlankSpace+unfilledNum);
        }

        newBlankSpace = blankSpace.substring(0,2*num-3);
        System.out.println(filledNum + newBlankSpace + filledNum);



    }
}
