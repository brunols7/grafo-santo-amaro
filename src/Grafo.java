import java.util.*;

public class Grafo<TIPO> {

    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }

    public void adicionarVertice(TIPO dado) {
        Vertice<TIPO> novoVertice  = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);;
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<TIPO> getVertice(TIPO dado){
        Vertice<TIPO> vertice = null;
        for(int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void BuscaEmLargura() {
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0) {
            Vertice<TIPO> visitado = fila.get(0);
            for(int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<TIPO> proximo = visitado.getArestasSaida().get(i).getFim();
                if(!marcados.contains(proximo)) {
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public List<List<TIPO>> encontrarCaminhos(TIPO origem, TIPO destino, int maxCaminhos) {
        List<List<TIPO>> caminhos = new ArrayList<>();
        Map<Vertice<TIPO>, Boolean> bloqueados = new HashMap<>();

        for (int i = 0; i < maxCaminhos; i++) {
            List<TIPO> caminho = dijkstra(origem, destino, bloqueados);
            if (caminho.isEmpty()) {
                break;
            }
            caminhos.add(caminho);
            if (i < maxCaminhos - 1 && caminho.size() > 2) {
                TIPO verticeBloqueado = caminho.get(1);
                bloqueados.put(getVertice(verticeBloqueado), true);
            }
        }
        return caminhos;
    }

    private List<TIPO> dijkstra(TIPO origem, TIPO destino, Map<Vertice<TIPO>, Boolean> bloqueados) {
        Map<Vertice<TIPO>, Double> distancias = new HashMap<>();
        Map<Vertice<TIPO>, Vertice<TIPO>> predecessores = new HashMap<>();
        PriorityQueue<Vertice<TIPO>> fila = new PriorityQueue<>(Comparator.comparing(v -> distancias.getOrDefault(v, Double.MAX_VALUE)));

        Vertice<TIPO> inicio = getVertice(origem);
        Vertice<TIPO> fim = getVertice(destino);

        if (inicio == null || fim == null) {
            return new ArrayList<>();
        }

        for (Vertice<TIPO> vertice : vertices) {
            distancias.put(vertice, Double.MAX_VALUE);
        }
        distancias.put(inicio, 0.0);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            Vertice<TIPO> atual = fila.poll();
            if (atual.equals(fim)) {
                break;
            }
            if (bloqueados.getOrDefault(atual, false)) {
                continue;
            }
            for (Aresta<TIPO> aresta : atual.getArestasSaida()) {
                Vertice<TIPO> vizinho = aresta.getFim();
                if (bloqueados.getOrDefault(vizinho, false)) {
                    continue;
                }
                double novaDistancia = distancias.get(atual) + aresta.getPeso();
                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    predecessores.put(vizinho, atual);
                    fila.remove(vizinho);
                    fila.add(vizinho);
                }
            }
        }

        List<TIPO> caminho = new ArrayList<>();
        Vertice<TIPO> atual = fim;
        while (atual != null && distancias.get(atual) != Double.MAX_VALUE) {
            caminho.add(atual.getDado());
            atual = predecessores.get(atual);
        }
        Collections.reverse(caminho);
        if (!caminho.isEmpty() && caminho.get(0).equals(origem)) {
            return caminho;
        }
        return new ArrayList<>();
    }

}
