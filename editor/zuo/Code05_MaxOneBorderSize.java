public class Code05_MaxOneBorderSize {


    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        //printMatrix(matrix);
        System.out.println(getMaxSize(matrix));
    }

    private static int getMaxSize(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        int side = Math.min(row, col);

        int[][] rightDp = new int[row][col];
        int[][] downDp = new int[row][col];

        fillDps(matrix, rightDp, downDp);


        printMatrix(matrix);
        System.out.println("~~~~~~~~~~~~~~~~~~");
        printMatrix(rightDp);
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 1; k <= side; k++) {

                    if (i + k <= row && j + k <= col) {
                        if (rightDp[i][j] >= k && downDp[i][j] >= k
                                && downDp[i][j + k - 1] >= k && rightDp[i + k - 1][j] >= k
                        ) {
                            res = Math.max(res, k );
                        }
                    }

                }
            }
        }
        return res;
    }

    //填充 包含自身在内，向右 向下连续的个数
    private static void fillDps(int[][] matrix, int[][] rightDp, int[][] downDp) {

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[row - 1][col - 1] == 1) {
            rightDp[row - 1][col - 1] = 1;
            downDp[row - 1][col - 1] = 1;
        }

        for (int c = col - 2; c >= 0; c--) {
            if(matrix[row-1][c] ==1){
                downDp[row-1][c] =1;
                rightDp[row-1][c] = rightDp[row-1][c+1] +1;
            }
        }

        for(int r = row -2;r>=0;r-- ){
            if(matrix[r][col-1] ==1){
                rightDp[r][col-1] =1;
                downDp[r][col-1]= downDp[r+1][col-1];
            }
        }
        for (int r = row-2; r >=0 ; r--) {
            for (int c = col-2; c >=0 ; c--) {

                if(matrix[r][c] ==1){
                    rightDp[r][c] = rightDp[r][c+1]+1;
                    downDp[r][c] = downDp[r+1][c] +1;
                }

            }
        }


    }
}
