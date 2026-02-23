import java.util.*;
import java.io.*;

class Aluno 
{
    String nome;
    char regiao;
    int distancia;

    Aluno(String nome, char regiao, int distancia) 
    {
        this.nome = nome;
        this.regiao = regiao;
        this.distancia = distancia;
    }
}

public class Main {
    public static void main(String[] args) throws IOException 
    {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) 
        {
            if (!sc.hasNextInt()) break; // evita erro no EOF
            int q = sc.nextInt();
            sc.nextLine(); // limpa quebra de linha

            Aluno[] alunos = new Aluno[q];

            // leitura dos dados
            for (int i = 0; i < q; i++) {
                String nome = sc.next();
                char regiao = sc.next().charAt(0);
                int distancia = sc.nextInt();
                alunos[i] = new Aluno(nome, regiao, distancia);
            }

            // BUBBLE SORT
            for (int i = 0; i < q - 1; i++) {
                for (int j = 0; j < q - i - 1; j++) {
                    if (compare(alunos[j], alunos[j + 1]) > 0) {
                        Aluno temp = alunos[j];
                        alunos[j] = alunos[j + 1];
                        alunos[j + 1] = temp;
                    }
                }
            }

            // saída — usando for normal
            for (int i = 0; i < alunos.length; i++) {
                System.out.println(alunos[i].nome);
            }
        }

        sc.close();
    }

    // método de comparação (mesma lógica do Comparator)
    public static int compare(Aluno a, Aluno b) {
        if (a.distancia != b.distancia)
            return a.distancia - b.distancia; // menor distância primeiro
        if (a.regiao != b.regiao)
            return a.regiao - b.regiao;       // ordem alfabética da região
        return a.nome.compareTo(b.nome);      // ordem alfabética do nome
    }
}
