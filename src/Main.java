import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Grafo<String> grafo = new Grafo();

        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");
        grafo.adicionarVertice("E");
        grafo.adicionarVertice("F");
        grafo.adicionarVertice("G");
        grafo.adicionarVertice("H");
        grafo.adicionarVertice("I");
        grafo.adicionarVertice("J");
        grafo.adicionarVertice("K");
        grafo.adicionarVertice("L");
        grafo.adicionarVertice("M");
        grafo.adicionarVertice("N");
        grafo.adicionarVertice("O");
        grafo.adicionarVertice("P");
        grafo.adicionarVertice("Q");
        grafo.adicionarVertice("R");
        grafo.adicionarVertice("S");
        grafo.adicionarVertice("T");
        grafo.adicionarVertice("U");
        grafo.adicionarVertice("V");
        grafo.adicionarVertice("W");
        grafo.adicionarVertice("X");

        grafo.adicionarAresta(300.0, "A", "B");
        grafo.adicionarAresta(370.0, "A", "V");
        grafo.adicionarAresta(317.0, "A", "X");

        grafo.adicionarAresta(300.0, "B", "A");
        grafo.adicionarAresta(47.0, "B", "C");

        grafo.adicionarAresta(47.0, "C", "B");
        grafo.adicionarAresta(62.0, "C", "D");
        grafo.adicionarAresta(141.0, "C", "H");

        grafo.adicionarAresta(62.0, "D", "C");
        grafo.adicionarAresta(8.0, "D", "E");

        grafo.adicionarAresta(8.0, "E", "D");
        grafo.adicionarAresta(13.0, "E", "F");
        grafo.adicionarAresta(230.0, "E", "G");

        grafo.adicionarAresta(13.0, "F", "E");

        grafo.adicionarAresta(230.0, "G", "E");

        grafo.adicionarAresta(141.0, "H", "C");
        grafo.adicionarAresta(138.0, "H", "I");

        grafo.adicionarAresta(138.0, "I", "H");
        grafo.adicionarAresta(153.0, "I", "J");

        grafo.adicionarAresta(153.0, "J", "I");
        grafo.adicionarAresta(512.0, "J", "K");

        grafo.adicionarAresta(512.0, "K", "J");
        grafo.adicionarAresta(135.0, "K", "L");

        grafo.adicionarAresta(135.0, "L", "K");
        grafo.adicionarAresta(50.0, "L", "M");
        grafo.adicionarAresta(187.0, "L", "N");

        grafo.adicionarAresta(50.0, "M", "L");

        grafo.adicionarAresta(187.0, "N", "L");
        grafo.adicionarAresta(108.0, "N", "O");

        grafo.adicionarAresta(108.0, "O", "N");
        grafo.adicionarAresta(82.0, "O", "P");

        grafo.adicionarAresta(82.0, "P", "O");
        grafo.adicionarAresta(215.0, "P", "Q");

        grafo.adicionarAresta(215.0, "Q", "P");
        grafo.adicionarAresta(97.0, "Q", "R");

        grafo.adicionarAresta(97.0, "R", "Q");
        grafo.adicionarAresta(33.0, "R", "S");
        grafo.adicionarAresta(243.0, "R", "T");

        grafo.adicionarAresta(33.0, "S", "R");
        grafo.adicionarAresta(207.0, "S", "T");
        grafo.adicionarAresta(38.0, "S", "V");

        grafo.adicionarAresta(243.0, "T", "R");
        grafo.adicionarAresta(207.0, "T", "S");
        grafo.adicionarAresta(22.0, "T", "U");

        grafo.adicionarAresta(22.0, "U", "T");
        grafo.adicionarAresta(210.0, "U", "V");
        grafo.adicionarAresta(107.0, "U", "X");

        grafo.adicionarAresta(210.0, "V", "U");
        grafo.adicionarAresta(38.0, "V", "S");
        grafo.adicionarAresta(370.0, "V", "A");

        grafo.adicionarAresta(107.0, "X", "U");
        grafo.adicionarAresta(317.0, "X", "A");

        System.out.print("Informe o ponto de partida: ");
        String origem = sc.nextLine().toUpperCase();

        System.out.print("Informe o ponto de chegada: ");
        String destino = sc.nextLine().toUpperCase();

        List<List<String>> caminhos = grafo.encontrarCaminhos(origem, destino, 2);

        for (int i = 0; i < caminhos.size(); i++) {
            List<String> caminho = caminhos.get(i);
            if (caminho.isEmpty()) {
                System.out.println("\nCaminho " + (i + 1) + ": Nenhum caminho encontrado.");
                continue;
            }
            System.out.println("\nCaminho " + (i + 1) + ": " + String.join(" -> ", caminho));
            double distanciaTotal = 0.0;
            for (int j = 0; j < caminho.size() - 1; j++) {
                Vertice<String> atual = grafo.getVertice(caminho.get(j));
                Vertice<String> proximo = grafo.getVertice(caminho.get(j + 1));
                for (Aresta<String> aresta : atual.getArestasSaida()) {
                    if (aresta.getFim().equals(proximo)) {
                        distanciaTotal += aresta.getPeso();
                        break;
                    }
                }
            }
            System.out.println("Distância total: " + distanciaTotal + " metros");
        }
        if (caminhos.isEmpty()) {
            System.out.println("Nenhum caminho encontrado entre " + origem + " e " + destino + ".");
        }
    }

}
