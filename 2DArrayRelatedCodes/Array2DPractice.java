public class Array2DPractice {
    public static void table2D(int[][] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("[");
            for (int j = 0; j < numbers[0].length; j++) {
                System.out.print(numbers[i][j]);
                if (j < numbers[0].length-1) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int numbers [][] = {{120,250,789,7},{58,79,31,87},{8,1,2,3},{99,98,97,96}};
        System.out.println("Original 2D Array: ");
        table2D(numbers);

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                if (numbers[i][j] == 1) {
                    numbers[i][j] = 50;
                    System.out.println("Number 1 is found at indices " + i + " and " + j + ".\n");
                    break;
                }
            }
        }
        System.out.println("Updated 2D Array where number 1 is changed to 50:");
        table2D(numbers);

        for (int i = 0; i < numbers[0].length; i++) {
            numbers[1][i] = 0;
        }
        System.out.println("Updated 2D Array where row 2 is all zero:");
        table2D(numbers);
    }
}
