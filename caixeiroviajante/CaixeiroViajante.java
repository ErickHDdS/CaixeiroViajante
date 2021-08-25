package caixeiroviajante;

import grafo.Grafo;
import heuristica.Heuristica;
import java.io.IOException;
import lerarquivotsp.LerArquivoTSP;

public class CaixeiroViajante {

    public static void main(String[] args) throws IOException {
        testaHeuristica();
    }
    
    public static void testaHeuristica() throws IOException {
        int[][] caminho;
        LerArquivoTSP read;
        Grafo grafo;
        Heuristica h;
        
        for (int i = 0; i < 3; i++) {
            String fileName;
            if (i == 0) {
                fileName = "si535";                
            }
            else if (i == 1) {
                fileName = "pa561";
            }
            else {
                fileName = "si1032";
            }
            read = new LerArquivoTSP(fileName);
            grafo = read.getGrafo();
            h = new Heuristica(grafo);
            caminho = h.encontraCaminho();
            System.out.println("Arquivo: " + fileName + ".tsp\nDistancia encontrada pela heuristica: " + h.getPesoTotal());
        }
        
    }

}
