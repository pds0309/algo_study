package history;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int x = 0;
        int y = matrix[0].length - 1;
        while (true) {
            if (!isValidRange(matrix, x, y)) {
                break;
            }
            int curVal = matrix[x][y];
            if (target < curVal) {
                y--;
                continue;
            }
            if (target > curVal) {
                x++;
                continue;
            }
            return true;
        }
        return false;
    }

    boolean isValidRange(int[][] arr, int x, int y) {
        return x >= 0 && y >= 0 && x < arr.length && y < arr[0].length;
    }
}
