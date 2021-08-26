package heuristica;

import grafo.Grafo;
import utils.ReadTSP;

import java.io.IOException;
public class TestaHeuristica {

    public static void main(String[] args) throws IOException {
        ReadTSP readFile;
        Grafo grafo;
        Heuristica heuristica;
        
        for (int i = 0; i < 3; i++) {
            String fileName;
            switch (i) {
                case 0:
                    fileName = "pa561";
                    break;
                case 1:
                    fileName = "si535";
                    break;
                case 2:
                    fileName = "si1032";
                    break;
                default:
                    fileName = null;
            }

            readFile = new ReadTSP(fileName);
            grafo = readFile.getGrafo();
            heuristica = new Heuristica(grafo);
            heuristica.encontraCaminho();

            System.out.println("Arquivo: " + fileName + ".tsp\nDistancia calculada pela Heuristica Gulosa: " + heuristica.getPesoTotal());
        }
    }
}