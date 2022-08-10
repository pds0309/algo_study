package programmers.lv2;

public class 다음큰숫자 {
    public int solution(int n) {
        int bitNum = Integer.bitCount(n);
        while(true){
            n++;
            if(bitNum == Integer.bitCount(n)){
                return n;
            }
        }
    }
}
