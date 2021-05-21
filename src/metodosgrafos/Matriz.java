package metodosgrafos;

public class Matriz {

    public static int[][] criaMatriz(int lin, int col) {
        int n=lin+1, m=col+1;
        int matriz[][] = new int[n][m];//criando a matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < col; j++) {
                matriz[i][j] = 0;
            }
        }
        return matriz;
    }

    public static void imprimeMatriz(int matriz[][], int lin, int col) {
        for (int i = 1; i <= lin; i++) {
            for (int j = 1; j <= col; j++) {
                System.out.print("  " + matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
