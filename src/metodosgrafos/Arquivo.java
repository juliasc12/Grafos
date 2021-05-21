package metodosgrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {

    public static int[][] lerEGravarMatrizAdjacencia() {
        int tamanho = 0, lin = 0, col = 0;

        try {
            FileReader arq = new FileReader("grafoAdjacencia.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            tamanho = Integer.parseInt(linha);//Pegando a quantidade de vertices
            int matriz[][] = Matriz.criaMatriz(tamanho, tamanho);
            linha = lerArq.readLine();
            while (linha != null) { //while roda enquanto tiver linhas
                String[] split = linha.split("_");
                lin = Integer.parseInt(split[0]);//primeiro elemento é a linha
                col = Integer.parseInt(split[1]);//segundo elemento é a coluna
                matriz[lin][col] = 1;
                linha = lerArq.readLine();
            }
            arq.close();
            return matriz;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            return null;
        }
    }
    
    public static int[][] lerEGravarMatrizAdjacenciaPonderada() {
        int tamanho = 0, lin = 0, col = 0;

        try {
            FileReader arq = new FileReader("grafoAdjacencia.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            tamanho = Integer.parseInt(linha);//Pegando a quantidade de vertices
            int matriz[][] = Matriz.criaMatriz(tamanho, tamanho);
            linha = lerArq.readLine();
            while (linha != null) { //while roda enquanto tiver linhas
                String[] split = linha.split("_");
                lin = Integer.parseInt(split[0]);//primeiro elemento é a linha
                col = Integer.parseInt(split[1]);//segundo elemento é a coluna
                matriz[lin][col] = Integer.parseInt(split[2]);
                linha = lerArq.readLine();
            }
            arq.close();
            return matriz;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            return null;
        }
    }

    public static int[][] lerEGravarMatrizIncidencia() {
        int qntdVertice = 0, qntdAresta = 0, lin = 0, col = 0;

        try {
            FileReader arq = new FileReader("grafoIncidencia.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            qntdVertice = Integer.parseInt(lerArq.readLine());//Pegando a quantidade de vertices
            qntdAresta = Integer.parseInt(lerArq.readLine());//Pegando a quantidade de arestas
            int matriz[][] = Matriz.criaMatriz(qntdAresta, qntdVertice);

            String linha = lerArq.readLine();
            while (linha != null) { //while roda enquanto tiver linhas
                String[] split = linha.split("_");
                lin = Integer.parseInt(split[0]);
                col = Integer.parseInt(split[1]);
                matriz[lin][col] = 1;
                linha = lerArq.readLine();
            }
            //Imprimir Matriz
            arq.close();
            return matriz;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            return null;
        }
    }
}
