package metodosgrafos;

import java.util.ArrayList;

public class No {

    private int pai;
    private ArrayList<Integer> listaComVerticesAdjacentes;

    public No(int pai) {
        this.pai = pai;
        this.listaComVerticesAdjacentes = new ArrayList();
    }

    public ArrayList<Integer> getListaComVerticesAdjacentes() {
        return listaComVerticesAdjacentes;
    }

    public void setListaComVerticesAdjacentes(ArrayList<Integer> listaComVerticesAdjacentes) {
        this.listaComVerticesAdjacentes = listaComVerticesAdjacentes;
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }

}
