/*
    caminho mínimo
    matriz de distancia
    encontrar a avore geradora mínima
*/
package metodosgrafos;

public class Grafo {

    private int n, m;
    private Boolean direcionado;
    private Lista lista;

    public Grafo(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public Grafo(int n, int m, Boolean direcionado, Lista lista) {
        this.n = n;
        this.m = m;
        this.direcionado = direcionado;
        this.lista = lista;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public Boolean getDirecionado() {
        return direcionado;
    }

    public void setDirecionado(Boolean direcionado) {
        this.direcionado = direcionado;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public int ordemDoGrafo() {
        return n;
    }

    public int tamanhoDoGrafo() {
        return m;
    }
}
