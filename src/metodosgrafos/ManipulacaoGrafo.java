package metodosgrafos;

import java.util.ArrayList;

public class ManipulacaoGrafo {

    Grafo grafo;
    int n, m;

    public ManipulacaoGrafo(Grafo grafo) {
        this.grafo = grafo;
        n = grafo.getN();
        m = grafo.getM();

    }

    public Boolean verificaSeTemLaco(int matrizAdjacencia[][]) {
        for (int i = 1; i < n; i++) {
            if (matrizAdjacencia[i][i] == 1) {
                return true;//Verificando se existe laço
            }
        }
        return false;
    }

    //É multigrafo se existe arestas paralelas
    public Boolean verificaSeMultigrafo(int matrizAdjacencia[][], int matrizIncidencia[][]) {
        int contArestaParalela = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrizAdjacencia[i][j] == 1) {//Verificando se onde tem ligaçao também existe arestas Paralelas
                    contArestaParalela = 0;
                    for (int aresta = 1; aresta <= m; aresta++)//vai percorrer todas as arestas
                    {
                        if (matrizIncidencia[aresta][i] == 1 && matrizIncidencia[aresta][j] == 1)//Verifica se a aresta liga os dois vertices
                        {
                            contArestaParalela++;
                        }
                    }
                }
            }
        }
        if (contArestaParalela >= 2) {
            return true;
        }
        return false;
    }

    //É simples se não tem laço e não tem arestas paralelas
    public Boolean verificaSimplicidade(int matrizAdjacencia[][], int matrizIncidencia[][]) {
        if (verificaSeTemLaco(matrizAdjacencia) == false
                && verificaSeMultigrafo(matrizAdjacencia, matrizIncidencia) == false) {
            return true;
        } else {
            return false;
        }
    }

    //É Pseudográgico se existe no minimo um laço
    public Boolean verificaSePseudografico(int matrizAdjacencia[][]) {
        if (verificaSeTemLaco(matrizAdjacencia) == true) {
            return true;
        } else {
            return false;
        }
    }

    //Grau do vertice é o numero de arestas que indicem nele
    public int grauDoVertice(int matrizIncidencia[][], int vertice) {
        int contGrau = 0;
        for (int i = 1; i <= m; i++) //linhas = arestas
        {
            if (matrizIncidencia[i][vertice] == 1) {
                contGrau++;
            }
        }
        return contGrau;
    }

    public void imprimeGrauDosVertices(int matrizIncidencia[][]) {
        for (int j = 1; j <= n; j++) {
            System.out.println("Grau do vertice " + j + "=" + grauDoVertice(matrizIncidencia, j));
        }
    }

    public int[] vetorGrauDosVertices(int matrizIncidencia[][]) {
        int vet[] = new int[n+1];
        for (int j = 0; j <= n; j++) {
            vet[j] = grauDoVertice(matrizIncidencia, j);
        }
        return vet;
    }

    public Boolean verificaGrauDosVertices(int grauEsperado, int matrizIncidencia[][]) {
        int vetGraus[] = vetorGrauDosVertices(matrizIncidencia);
        boolean resultado=true;
        for (int i = 1; i < vetGraus.length; i++) {
           //System.out.println("vetGraus ["+i+"] ="+vetGraus[i]);
            if (vetGraus[i] != grauEsperado) {
                //System.out.println("vetGraus ["+i+"] !="+grauEsperado);
                resultado = false;
            }
        }
        return resultado;
    }

    // É completo se a quantidade de Arestas=(n*(n-1))/2 e se todos os vértices possuem grau=n-1 <=> numero de arestas q indicem nele
    public Boolean verificaSeCompleto(int matrizIncidencia[][]) {
        int qntdEsperadaArestas = (n * (n - 1)) / 2;
        int grauVerticeEsperado = n - 1;
        Boolean grausQueNemOesperado = verificaGrauDosVertices(grauVerticeEsperado, matrizIncidencia);
        if (qntdEsperadaArestas == m && grausQueNemOesperado == true) {
            return true;
        }
        return false;
    }
    
    //É conexo se existe caminho para todo par de vertices
    public Boolean verificaSeConexo(int[][] matrizIncidencia, int[][] matrizAdjacencia) {
        int vet[] = new int[n + 1];
        if (verificaGrauDosVertices(0, matrizIncidencia)) {
            return false; // se existe algum vertice de grau 0, ele é desconexo
        }
        for (int i = 1; i <= n; i++) {// se não, verificar se todos os vertices alcançam todos os outros vertices
            vet = vetFechoTransitivoDeUmVertice(i);
            for (int j = 1; j <= n; j++) { 
                if (vet[j] != j) {
                    //System.out.println("vet["+i+"]["+j+"]="+vet[j]+" != "+j);
                    return false;
                }
            }
        }
        return true;
    }
    
    public int[][] matrizDoFechoTransitivo_Wharshal(){
        int matrizAdjacencia[][]= Arquivo.lerEGravarMatrizAdjacencia();
        for (int k = 1; k <= n; k++)//altera a matriz de adjacencia para os valores do fecho transitivo
            for (int i = 1; i <= n; i++) 
                for (int j = 1; j <= n; j++) 
                    matrizAdjacencia[i][j] = matrizAdjacencia[i][j] != 0 || (matrizAdjacencia[i][k] != 0 && matrizAdjacencia[k][j] != 0) ? 1 : 0;
           
        return matrizAdjacencia;
    }
    
    public int[][] soFechoTransitivoDeUmVertice(int matrizAdjacencia[][], int vertice_linAnt, int colAnt, int vet[]) {
        if (grafo.getDirecionado() == false) {
            for (int j = 1; j <= n; j++) {
                if (matrizAdjacencia[vertice_linAnt][j] == 1 && (j != colAnt)) {//a acoluna anterior era o i da antiga iteração, logo j tem que ser diferente do i anterior para n voltar nele mesmo . Ex [1,2] com [2,1] n pode ser executado
                    matrizAdjacencia[vertice_linAnt][j]++; // para n passar nele dnv
                    if (vet[j] == 0) {
                        vet[j] = j;
                    }
                    matrizAdjacencia = soFechoTransitivoDeUmVertice(matrizAdjacencia, j, vertice_linAnt, vet);//troca a linha pela coluna         
                }
                if (j == n) {
                    return matrizAdjacencia;//Se percorreu toda a linha, retorna para a chamada anterior
                }
            }
        }
        return null;
    }
    
    public Boolean verificaSeTemCiclo(int matrizFechoDoGrafo[][]){
        for(int i=1; i<=n; i++){
            if(matrizFechoDoGrafo[i][i]==1) return true;
        }   
        return false;
    }
    public void fechoTransitivoDeTodosVertices (){
        int matrizAdjacenciaCopia[][]=matrizDoFechoTransitivo_Wharshal();
        for (int i = 1; i <= n; i++) {//fecho transitivo de cada vetice
            System.out.print("Fecho transitivo vertice " + i + " = ");
            for (int j = 1; j <= n; j++) {
                if(matrizAdjacenciaCopia[i][j] ==1)
                    System.out.print(j+"-");    
            }
            System.out.println("\n");
        }
    }
            
    public int[] vetFechoTransitivoDeUmVertice(int vertice) {
        int matrizAdjacenciaCopia[][]=matrizDoFechoTransitivo_Wharshal();
        int vet[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            vet[i] = 0;
        }
        for (int j = 1; j <= n; j++) {
            if(matrizAdjacenciaCopia[vertice][j] ==1){
                vet[j]=j;
            }            
        }
        return vet;
    }
    
    public String[] fechoTransitivoDeUmGrafo() { 
        String[] transitivo = new String[1];
        transitivo[0] = "Fecho transitivo do grafo:\r\n";
        int matrizAdjacencia[][]=matrizDoFechoTransitivo_Wharshal();
        
        for (int i = 1; i <=n; i++) {//printa a matriz fecho transitivo do grafo e de cada vetice
            for (int j = 1; j <= n; j++) {
                transitivo[0] += "  "+matrizAdjacencia[i][j];//printa a matriz fecho transitivo do grafo
            }
            transitivo[0] += "\r\n";
        }
        return transitivo;
    }

    //O grafo é ciclo se todos seus vertices tiverem grau 2 e ele for conexo
    public Boolean verificaSeCiclo(int matrizAdjacencia[][], int matrizIncidencia[][], Boolean conexo) {
        if (conexo) {
            if (verificaGrauDosVertices(2, matrizIncidencia)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    
    public int[] dijkstraInicializacao(int valor){
        int vet[]= new int[n+1];
        for(int i=0; i<=n; i++){
            vet[i]=valor;  
        }
        return vet ;
    }
    
    public int acharOmenor(int vet[], int vetorVerticesPassados[]){
        int menor=999,indice=0;
        for(int i=1;i<vet.length;i++){
            if(vet[i]<menor && vetorVerticesPassados[i]==-1){// é menos 1 quando n calculou a distancia ate ele
                menor=vet[i];
                indice=i;
            }
        }
        return indice;
    }
    
    public int verificaVetor(int valor, int vet[]){
        int cont=0;
        for(int i=1;i<vet.length;i++){
            if(vet[i]==valor) cont++;
        }
        return cont;
    }
    
    public void imprimeVetor(int vet[]){
        for(int i=1; i<vet.length; i++){
           System.out.print("v["+i+"] = "+vet[i]+"\t"); 
        }
        System.out.println();
    }
    
    public int[] caminhoMinimo_Dijkstra(int matrizAjacenciaPonderada[][], int vInicial){
        int vetPesos[]= dijkstraInicializacao(999); // inicializa o vetor com "infinito"
        vetPesos[vInicial]=0;
        int vetorVerticesPassados[]=dijkstraInicializacao(-1); // inicializa o vetor onde indicaremos quais vertices ja passamos
        vetorVerticesPassados[vInicial]=0; //
        
        while(verificaVetor(-1, vetorVerticesPassados)!=0){ //enquanto não calculou a distancia para todos os vertices
            for(int j=1; j<=n; j++){
                if((vetPesos[vInicial] + matrizAjacenciaPonderada[vInicial][j]<vetPesos[j]) && matrizAjacenciaPonderada[vInicial][j]!=0) //verifica se a distancia para vertice anterior somada do peso da aresta atual , é menor do que a distancia encontrada anteriormente para esse vertice atual
                    vetPesos[j]= vetPesos[vInicial] + matrizAjacenciaPonderada[vInicial][j]; // recebe a menor distancia
            }
            vInicial=acharOmenor(vetPesos, vetorVerticesPassados); //recebe o vertice que fez com que o caminho fosse menor
            vetorVerticesPassados[vInicial]=vetPesos[vInicial];   
            //System.out.println("menor = "+vInicial);
        } 
        return vetorVerticesPassados;
    }
    
    public int[][] matrizDeDistancia(int matrizAjacenciaPonderada[][]){
        int matriz[][]=Matriz.criaMatriz(n, n);
        for(int i=1;i<=n; i++){
            int vet[]=caminhoMinimo_Dijkstra(matrizAjacenciaPonderada, i);
            for(int j=1; j<=n; j++){
                matriz[i][j]=vet[j];
            }
        }
        return matriz;
    }
    
 public String arvoreGeradoraMinima(Boolean verificaSeConexo, int  matrizAjacenciaPonderada[][], int [] vetArestas){
        String res     = "";
        int[][] arvoreGeradoraMinima    = new int [ n +1][n+1];
        int [] vetPai  = new int [n+1];
        if(!verificaSeConexo)
           return "grafo desconexo nao possui arvoreGeradoraMinimaore geradora mínima";
        for(int i=1; i<(n+1); i++){ 
            vetPai[i] = i; 
        }
        for(int k=0; k<vetArestas.length; k++) 
            for(int i=1; i<=n; i++) 
                for(int j=1; j<=n; j++)
                    if(vetArestas[k] ==  matrizAjacenciaPonderada[i][j])
                        if(vetPai[i] != vetPai[j]){
                            arvoreGeradoraMinima[i][j] =  matrizAjacenciaPonderada[i][j];
                            arvoreGeradoraMinima[j][i] =  matrizAjacenciaPonderada[i][j];       
                            vetPai[i] = vetPai[j];
                       }
       for(int i=1; i<=n; i++){ 
            for(int j=1; j<=n; j++){
                res += "  "+arvoreGeradoraMinima[i][j];
            }
            res += "\n";
       }return res;
   }//fim Arvore geradora minima
   
   public int [] vetorDeArestas(int  matrizAjacenciaPonderada[][]){
        int [] vet  = new int [m];   
        int matrizAdjacenciaPonderada2 [][] = new int[n+1][n+1];
        int k       = 0;
        int aux     = 0;
        for(int i=1; i<=n; i++)//clona matriz Adjacencia Ponderada
            for(int j=1; j<=n; j++)
                matrizAdjacenciaPonderada2[i][j] =  matrizAjacenciaPonderada[i][j];
        for(int i=1; i<=n; i++){ 
            for(int j=1; j<=n; j++){
                if(matrizAdjacenciaPonderada2[i][j] != 0){
                    vet[k] = matrizAdjacenciaPonderada2[i][j];
                    k++;
                    matrizAdjacenciaPonderada2[j][i] = 0;
                }
            }
        } 
        for(int i=0; i<vet.length; i++){ 
            for(int j=0; j<(vet.length-1); j++){
                if(vet[j]>vet[j+1]){
                    aux      = vet[j];
                    vet[j]   = vet[j+1];
                    vet[j+1] = aux;
                }
           }
        } 
        
      return vet;
   }// fim ordenação vetor de arestas
}
