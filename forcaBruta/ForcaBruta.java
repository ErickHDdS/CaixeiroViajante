package forcaBruta;

import grafo.*;
import grafo.Grafo.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ForcaBruta {
    private Grafo grafo;
    private HashMap<Integer, ArrayList<Integer>> maps;
    private int shortestDistance = 0;

    public ForcaBruta(Grafo grafo) {
        this.grafo = grafo;
        this.maps = new HashMap<>();
    }

    public void backtracking(int pointA) {
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Aresta> arestasAdj = this.arestasAdj(pointA); // caminha de A até o vertice
        for (int i = 0; i < arestasAdj.size(); i++) {
            int pointC = arestasAdj.get(i).v2();
            visita(visited, pointA, pointA, pointC);
        }
    }

    public ArrayList<Aresta> arestasAdj(int v) {
        ArrayList<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < this.grafo.numVertices(); i++)
            if (this.grafo.getPeso(v, i) > 0)
                arestas.add(new Aresta(v, i, this.grafo.getPeso(v, i)));
        return arestas;
    }

    private void visita(final ArrayList<Integer> visited, int pontoInicial, int pointA, int pointB) {
        int totalDistance;
        ArrayList<Aresta> arestasAdj = this.arestasAdj(pointA);
        ArrayList visitedCopy = new ArrayList(visited);
        visitedCopy.add(pointA);
        for (int i = 0; i < arestasAdj.size(); i++) { // salta o vertice visitado anteriormente e pula-o
            int pointC = arestasAdj.get(i).v2(); // vertice atual -> pointC
            if (!visitedCopy.contains(pointC)) {
                if (visitedCopy.size() == this.grafo.numVertices() - 1) {
                    visitedCopy.add(pointB);
                    visitedCopy.add(pontoInicial);
                    totalDistance = 0;
                    // System.out.print("Caminho: ");
                    for (int j = 0; j < visitedCopy.size(); j++) {
                        // System.out.print(visitadosCopy.get(j) + " ");
                        if (j < visitedCopy.size() - 1)
                            totalDistance += this.grafo.getPeso((int) visitedCopy.get(j), (int) visitedCopy.get(j + 1));
                    }
                    // System.out.println("\nDistancia: " + distanciaTotal);
                    final ArrayList visitedCopy2 = new ArrayList(visitedCopy);
                    this.maps.put(totalDistance, visitedCopy2);
                    visitedCopy.remove(visitedCopy.size() - 1);
                    visitedCopy.remove(visitedCopy.size() - 1);
                } else if (pointC != pointB) {
                    visita(visitedCopy, pontoInicial, pointC, pointB);
                }
            }
        }
    }

    public void solucaoOtima() {
        this.shortestDistance = this.maps.keySet().stream().findFirst().get();
        for (Integer distance : this.maps.keySet()) // menor distancia
            if (distance < this.shortestDistance)
                this.shortestDistance = distance;
        System.out.print("\nMenor caminho: ");
        for (int j = 0; j < this.maps.get(this.shortestDistance).size(); j++)
            System.out.print(this.maps.get(this.shortestDistance).get(j) + " ");
        System.out.println("\nDistancia: " + this.shortestDistance);
    }
}