/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosgrafos;

/**
 *
 * @author marce
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int matrizAdjacencia[][] = Arquivo.lerEGravarMatrizAdjacencia();
        int matrizIncidencia[][] = Arquivo.lerEGravarMatrizIncidencia();
        int matrizAdjacenciaPonderada[][]=Arquivo.lerEGravarMatrizAdjacenciaPonderada();
        int n = matrizAdjacencia.length - 1;
        int m = matrizIncidencia.length - 1;
        Lista lista = new Lista(matrizAdjacencia, n);
        Grafo grafo = new Grafo(n, m, Boolean.FALSE, lista);
        ManipulacaoGrafo testes = new ManipulacaoGrafo(grafo);
        Boolean conexo = testes.verificaSeConexo(matrizIncidencia,matrizAdjacencia);
        System.out.println("------------------------------------");
        System.out.println("--------------Estruturas------------");
        System.out.println("N=" + n + " M= " + m);
        System.out.println("Matriz Adjacência");
        Matriz.imprimeMatriz(matrizAdjacencia, n, n);//Imprimir Matriz
        System.out.println("Matriz Adjacência Ponderada");
        Matriz.imprimeMatriz(matrizAdjacenciaPonderada, n, n);//Imprimir Matriz
        System.out.println("Matriz Incidência");
        Matriz.imprimeMatriz(matrizIncidencia, m, n);//Imprimir Matriz
        System.out.println("Lista de Adjacência");
        lista.imprimeListaDeAdjacencia();
        System.out.println("------------------------------------");
        System.out.println("-----------Tipo do Grafo------------");
        System.out.println("É simples = " + testes.verificaSimplicidade(matrizAdjacencia, matrizIncidencia));
        System.out.println("É Multigrafo = " + testes.verificaSeMultigrafo(matrizAdjacencia, matrizIncidencia));
        System.out.println("É Pseudográfico = " + testes.verificaSePseudografico(matrizAdjacencia));
        System.out.println("É Completo = " + testes.verificaSeCompleto(matrizIncidencia));
        System.out.println("------------------------------------");
        System.out.println("------Características do Grafo------");
        testes.imprimeGrauDosVertices(matrizIncidencia);
        System.out.println("Ordem do grafo = " + grafo.ordemDoGrafo());
        System.out.println("Tamanho do grafo = " + grafo.tamanhoDoGrafo());
        System.out.println("É conexo =" + conexo);
        System.out.println("É Ciclo = " + testes.verificaSeCiclo(matrizAdjacencia, matrizIncidencia, conexo));
        System.out.println("------------------------------------");
        System.out.println("----------Alacançabilidade----------\n");
        testes.fechoTransitivoDeTodosVertices();
        System.out.println("-------Fecho Transitivo do Grafo ------ ");
        String[] fechoT = new String[1];
        fechoT=testes.fechoTransitivoDeUmGrafo();
        System.out.println("\n" + fechoT[0]);
        System.out.println("------------------------------------");
        System.out.println("-------------Miniminização------------\n");
        /*System.out.println("----Caminho minimo do vertice 1 ----");
        testes.imprimeVetor(testes.caminhoMinimo_Dijkstra(matrizAdjacenciaPonderada, 1));
        System.out.println("----Caminho minimo do vertice 2 ----");
        testes.imprimeVetor(testes.caminhoMinimo_Dijkstra(matrizAdjacenciaPonderada, 2));
        System.out.println("----Caminho minimo do vertice 3 ----");
        testes.imprimeVetor(testes.caminhoMinimo_Dijkstra(matrizAdjacenciaPonderada, 3));*/
        System.out.println("\n----Matriz de Distância----");
        Matriz.imprimeMatriz(testes.matrizDeDistancia(matrizAdjacenciaPonderada), n, n);    
        int[] vet =  testes.vetorDeArestas(matrizAdjacenciaPonderada);
        System.out.println("\n------Árvore Geradora Minima------\n"+testes.arvoreGeradoraMinima(conexo, matrizAdjacenciaPonderada, vet));
        
        

        
    }
}
