package heuristica;
import grafo.Grafo;

// graph:           Grafo
// visitsVertices:  Array auxiliar que armazena as visitas á um vertices do grafo
// bestWay:         Melhor caminho/solução
// totalDistance:   Soma do peso de todas arestas

public class HeuristicaGulosa {
    private Grafo graph;                    
    private boolean[] visitsVertices;   
    private int[][] bestWay;         
    private int shortestDistance = 0;                  
    
    public HeuristicaGulosa(Grafo graph){
        int numVertices = graph.numVertices();

        this.graph = graph;
        this.visitsVertices = new boolean[numVertices];
        this.bestWay = new int[graph.numVertices()][3];

        for(int i=0; i < numVertices; i++){
            this.visitsVertices[i] = false;
        }
    }

    public boolean visitouTodos(boolean[] visitsVertices){
        boolean result = true;
        for (boolean vertice : visitsVertices) {
            if(!vertice){
                result = false;
            }
        }
        return result;
    }
    
    public int[][] encontraCaminho(){
        int vi=0, verticeX, pesoV, i=0;
        visitsVertices[0] = true;
        for(; visitouTodos(visitsVertices) == false; i++) {
            verticeX=graph.menorListaAdjacencia(vi, visitsVertices).v2();
            pesoV=graph.menorListaAdjacencia(vi, visitsVertices).peso();

            bestWay[i][0] = vi;
            bestWay[i][1] = verticeX;
            bestWay[i][2] = pesoV;

            shortestDistance += pesoV;
            vi=verticeX;
            visitsVertices[verticeX] = true;
        }
        visitsVertices[0] = false;

        verticeX=graph.menorListaAdjacencia(vi, visitsVertices).v2();
        pesoV=graph.menorListaAdjacencia(vi, visitsVertices).peso();

        bestWay[i][0] = vi;
        bestWay[i][1] = verticeX;
        bestWay[i][2] = pesoV;

        shortestDistance += pesoV;

        return bestWay;
    }
    
    public int getPesoTotal(){
        return shortestDistance;
    }
}
