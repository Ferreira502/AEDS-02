import java.util.*;

class Jogador implements Comparable<Jogador> {
    String nome;
    int totalGols;
    int totalAssistencias;
    int partidas;
    double media;

    public Jogador(String nome, int totalGols, int totalAssistencias, int partidas) {
        this.nome = nome;
        this.totalGols = totalGols;
        this.totalAssistencias = totalAssistencias;
        this.partidas = partidas;
        this.media = (double)(totalGols + totalAssistencias) / partidas;
    }

    @Override
    public int compareTo(Jogador outro) {
        // 1. Média (descendente)
        if (Double.compare(outro.media, this.media) != 0) {
            return Double.compare(outro.media, this.media);
        }
        // 2. Mais gols primeiro
        if (outro.totalGols != this.totalGols) {
            return outro.totalGols - this.totalGols;
        }
        // 3. Nome em ordem alfabética
        return this.nome.compareToIgnoreCase(outro.nome);
    }
}

public class Ranking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int quantidade = Integer.parseInt(sc.nextLine());
        List<Jogador> jogadores = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            String linha = sc.nextLine();
            String[] dados = linha.split(" ");
            String nome = dados[0];

            int golsTotais = 0;
            int assistTotais = 0;
            int partidas = (dados.length - 1) / 2;

            for (int j = 1; j < dados.length; j += 2) {
                int gols = Integer.parseInt(dados[j]);
                int assist = Integer.parseInt(dados[j + 1]);
                golsTotais += gols;
                assistTotais += assist;
            }

            jogadores.add(new Jogador(nome, golsTotais, assistTotais, partidas));
        }

        // Ordenar jogadores
        Collections.sort(jogadores);

        // Exibir resultado
        System.out.println("Ranking de jogadores:");
        int pos = 1;
        for (Jogador j : jogadores) {
            String mediaFormatada = String.format("%.2f", j.media);
            System.out.println(pos + ". " + j.nome + " - Média: " + mediaFormatada + " - Gols: " + j.totalGols);
            pos++;
        }

        sc.close();
    }
}
