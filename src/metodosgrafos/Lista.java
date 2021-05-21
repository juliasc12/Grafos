package metodosgrafos;

import java.util.ArrayList;

public class Lista {

    ArrayList<No> listaDosNosComListas;

    public Lista(int matrizAdjacencia[][], int n) {
        preencheListaDeAdjacencia(matrizAdjacencia, n);
        recebeVertices(matrizAdjacencia, n);
    }

    public void preencheListaDeAdjacencia(int matrizAdjacencia[][], int n) {
        listaDosNosComListas = new ArrayList(); 
        for (int i = 1; i <= n; i++) {
            listaDosNosComListas.add(new No(i));//cria uma lista que contém todos os vertices da matriz
        }
    }

    public ArrayList<No> recebeVertices(int matrizAdjacencia[][], int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrizAdjacencia[i][j] == 1) {
                    int indice = i - 1; //array começa no indice 0, e a matriz no 1
                    listaDosNosComListas.get(indice).getListaComVerticesAdjacentes().add(j); //acessa o No que contem uma lista, e adiciona elementos nela
                                        //no        .getLista
                                            //ArrayDeNo.add
                }
            }
        }
        return listaDosNosComListas;
    }

    public void imprimeListaDeAdjacencia() {
        for (int i = 0; i < listaDosNosComListas.size(); i++) {
            System.out.print(listaDosNosComListas.get(i).getPai() + " = ");
            for (int j = 0; j < listaDosNosComListas.get(i).getListaComVerticesAdjacentes().size(); j++) {//tamanho do array do primeiro vertice
                if (j == listaDosNosComListas.get(i).getListaComVerticesAdjacentes().size() - 1) {
                    System.out.print("" + listaDosNosComListas.get(i).getListaComVerticesAdjacentes().get(j) + "");
                } else {
                    System.out.print("" + listaDosNosComListas.get(i).getListaComVerticesAdjacentes().get(j) + "->");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void imprimeFechoTransitivoDeUmGrafo(){
        ArrayList<String> fecho = new ArrayList();
        //System.out.print(listaDosNosComListas.get(i).getPai() + " = ");
        for (int i = 0; i < listaDosNosComListas.size(); i++) {
            System.out.print(listaDosNosComListas.get(i).getPai() + " = ");
            for (int j = 0; j < listaDosNosComListas.get(i).getListaComVerticesAdjacentes().size(); j++) {//tamanho do array do primeiro vertice
                 
            }
            System.out.println("");
        }
        
    }

    public ArrayList<No> getListaDosNosComListas() {
        return listaDosNosComListas;
    }

    public void setListaDosNosComListas(ArrayList<No> listaDosNosComListas) {
        this.listaDosNosComListas = listaDosNosComListas;
    }

}
