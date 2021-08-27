package heuristica;
import grafo.Grafo;

public class HeuristicaGulosa {
    
    private Grafo grafo;
    private boolean[] VerticesVisitados;
    private int[][] MelhorCaminho;
    private int pesoTotal;
    
    public HeuristicaGulosa(Grafo grafo){
        this.grafo = grafo;
        this.VerticesVisitados = new boolean[grafo.numVertices()];
        this.MelhorCaminho = new int[grafo.numVertices()][3];

        for(int i=0; i<grafo.numVertices(); i++)
            this.VerticesVisitados[i]=false;
    }

    public boolean visitouTodos(boolean[] VerticesVisitados){
        for(int i=0; i<VerticesVisitados.length; i++){
            if(!VerticesVisitados[i])
                return false;
        }
        return true;
    }
    
    public int[][] encontraCaminho(){
        int vi=0, verticeX, pesoV, i=0;
        VerticesVisitados[0] = true;
        for(; visitouTodos(VerticesVisitados)==false; i++) {
            verticeX=grafo.menorListaAdjacencia(vi, VerticesVisitados).v2();
            pesoV=grafo.menorListaAdjacencia(vi, VerticesVisitados).peso();

            MelhorCaminho[i][0]=vi;
            MelhorCaminho[i][1]=verticeX;
            MelhorCaminho[i][2]=pesoV;

            pesoTotal+=pesoV;
            vi=verticeX;
            VerticesVisitados[verticeX] = true;
        }
        VerticesVisitados[0] = false;

        verticeX=grafo.menorListaAdjacencia(vi, VerticesVisitados).v2();
        pesoV=grafo.menorListaAdjacencia(vi, VerticesVisitados).peso();

        MelhorCaminho[i][0]=vi;
        MelhorCaminho[i][1]=verticeX;
        MelhorCaminho[i][2]=pesoV;

        pesoTotal+=pesoV;

        return MelhorCaminho;
    }
    
    public int getPesoTotal(){
        return pesoTotal;
    }
}
