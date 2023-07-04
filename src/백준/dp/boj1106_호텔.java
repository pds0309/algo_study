package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1106_호텔 {
    static int[][] dpArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int requestCustomerNum = infos[0];
        int caseNum = infos[1];
        dpArr = new int[requestCustomerNum + 1][caseNum + 1];
        CustomerMaker[] customerMakers = new CustomerMaker[caseNum + 1];
        for (int i = 1; i <= caseNum; i++) {
            int[] moneyInfos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            customerMakers[i] = new CustomerMaker(moneyInfos[0], moneyInfos[1]);
        }
        for (int peopleCnt = 1; peopleCnt < dpArr.length; peopleCnt++) {
            for (int j = 1; j < dpArr[peopleCnt].length; j++) {
                int restIdx = Math.max(peopleCnt - customerMakers[j].customerNum, 0);
                int prevVal = dpArr[peopleCnt][j - 1] == 0 ? Integer.MAX_VALUE : dpArr[peopleCnt][j - 1];
                dpArr[peopleCnt][j] = Math.min(dpArr[restIdx][j] + customerMakers[j].money, prevVal);
            }
        }
        System.out.println(dpArr[requestCustomerNum][caseNum]);
    }

    static class CustomerMaker {
        int money;
        int customerNum;

        public CustomerMaker(int money, int customerNum) {
            this.money = money;
            this.customerNum = customerNum;
        }
    }
}
