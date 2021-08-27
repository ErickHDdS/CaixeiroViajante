package heuristica;

import grafo.Grafo;
import utils.ReadTSP;

import java.io.IOException;
public class TestaHeuristica {

    public static void main(String[] args) throws IOException {
        ReadTSP file;
        Grafo grafo;
        HeuristicaGulosa heuristicaGulosa;
        
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

            file = new ReadTSP(fileName);
            grafo = file.getGrafo();
            heuristicaGulosa = new HeuristicaGulosa(grafo);
            heuristicaGulosa.encontraCaminho();

            System.out.println("Arquivo: " + fileName + ".tsp\nDistancia calculada pela Heuristica Gulosa: " + heuristicaGulosa.getPesoTotal());
        }
    }
}