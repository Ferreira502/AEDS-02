import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

class Games {
    private int id;
    private String name;
    private String releaseDate;
    private int estimatedOwners;
    private float price;
    private List<String> supportedLanguages; 
    private int metacriticScore;
    private float userScore;
    private int achievements;
    private List<String> publishers;
    private List<String> developers;
    private List<String> categories;
    private List<String> genres;
    private List<String> tags;

    public Games() {
        this.id = 0;
        this.name = "NaN";
        this.releaseDate = "NaN";
        this.estimatedOwners = 0;
        this.price = 0.0f;
        this.supportedLanguages = new ArrayList<>();
        this.metacriticScore = -1;
        this.userScore = -1.0f;
        this.achievements = 0;
        this.publishers = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public Games(String[] dados) {
        this.id = dados[0].isEmpty() ? 0 : Integer.parseInt(dados[0]);
        this.name = dados[1].isEmpty() ? "NaN" : dados[1];

        this.releaseDate = parseReleaseDate(dados[2]);

        try {
            String ownersRaw = dados[3].replaceAll("[^0-9]", ""); 
            this.estimatedOwners = ownersRaw.isEmpty() ? 0 : Integer.parseInt(ownersRaw);
        } catch (NumberFormatException e) {
            this.estimatedOwners = 0;
        }

        if (dados[4].isEmpty() || dados[4].equalsIgnoreCase("Free to Play")) {
            this.price = 0.0f;
        } else {
            try {
                this.price = Float.parseFloat(dados[4]); 
            } catch (NumberFormatException e) {
                this.price = 0.0f;
            }
        }

        this.supportedLanguages = new ArrayList<>(Arrays.asList(parseArrayField(dados[5])));
        
        this.metacriticScore = dados[6].isEmpty() ? -1 : Integer.parseInt(dados[6]);

        if (dados[7].isEmpty() || dados[7].equalsIgnoreCase("tbd")) {
            this.userScore = -1.0f;
        } else {
            try {
                this.userScore = Float.parseFloat(dados[7]);
            } catch (NumberFormatException e) {
                this.userScore = -1.0f;
            }
        }

        this.achievements = dados[8].isEmpty() ? 0 : Integer.parseInt(dados[8]);
        
        this.publishers = new ArrayList<>(Arrays.asList(parseArrayField(dados[9])));
        this.developers = new ArrayList<>(Arrays.asList(parseArrayField(dados[10])));
        this.categories = new ArrayList<>(Arrays.asList(parseArrayField(dados[11])));
        this.genres = new ArrayList<>(Arrays.asList(parseArrayField(dados[12])));
        
        this.tags = dados.length > 13 ? new ArrayList<>(Arrays.asList(parseArrayField(dados[13]))) : new ArrayList<>();
    }

    private String parseReleaseDate(String rawDate) {
        if (rawDate == null || rawDate.isEmpty()) return "NaN";
        String cleanedDate = rawDate.replace("\"", "").trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//Espera receber as entradas assim e vai produzir as saidas assim tbm

        try {
            SimpleDateFormat parser = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
            Date date = parser.parse(cleanedDate);
            return formatter.format(date);
        } catch (java.text.ParseException e) { //Não correspondeu a exeção ent passa pro proximo try
        }

        try {
            SimpleDateFormat parser = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
            Date date = parser.parse(cleanedDate);
            return "01/" + formatter.format(date).substring(3); // terceira posição porque tem a /
        } catch (java.text.ParseException e) {
        }

        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            Date date = parser.parse(cleanedDate);
            return "01/01/" + formatter.format(date).substring(6);
        } catch (java.text.ParseException e) {
        }
        
        return "NaN";
    }

    private String[] parseArrayField(String campo) {
        if (campo == null || campo.isEmpty() || campo.equals("[]")) {
            return new String[0];
        }
        String trimmed = campo.trim();
        if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
            trimmed = trimmed.substring(1, trimmed.length() - 1);
        }
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        String[] parts = trimmed.split(",");
        List<String> list = new ArrayList<>();
        for (String part : parts) { //percorre todo o array
            String p = part.trim();
            p = p.replaceAll("^['\"]|['\"]$", "");
            if (!p.isEmpty()) {
                list.add(p);
            }
        }
        return list.toArray(new String[0]);
    }

    private String arrayToString(List<String> list) {
        if (list.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=> ").append(id).append(" ## ").append(name).append(" ## ").append(releaseDate)
          .append(" ## ").append(estimatedOwners).append(" ## ").append(String.format("%.2f", price)).append(" ## ")
          .append(arrayToString(supportedLanguages)).append(" ## ").append(metacriticScore).append(" ## ")
          
        .append(userScore == -1.0f ? "0.0" : String.format("%.1f", userScore)).append(" ## ").append(achievements).append(" ## ")
        
        .append(arrayToString(publishers)).append(" ## ").append(arrayToString(developers)).append(" ## ")
          .append(arrayToString(categories)).append(" ## ").append(arrayToString(genres)).append(" ## ")
          .append(arrayToString(tags)).append(" ##");
        return sb.toString();
    }

    public Games clone() {
        Games clone = new Games();

        clone.id = this.id; 
        clone.name = this.name; 
        clone.releaseDate = this.releaseDate;
        clone.estimatedOwners = this.estimatedOwners; 
        clone.price = this.price;  
        clone.supportedLanguages = new ArrayList<>(this.supportedLanguages);
        clone.metacriticScore = this.metacriticScore; 
        clone.userScore = this.userScore; 
        clone.achievements = this.achievements; 
        clone.publishers = new ArrayList<>(this.publishers);
        clone.developers = new ArrayList<>(this.developers); 
        clone.categories = new ArrayList<>(this.categories);
        clone.genres = new ArrayList<>(this.genres);
        clone.tags = new ArrayList<>(this.tags); 

        return clone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getReleaseDate() { return releaseDate; }
    public int getEstimatedOwners() { return estimatedOwners; }
    public float getPrice() { return price; }
    public List<String> getSupportedLanguages() { return supportedLanguages; }
    public int getMetacriticScore() { return metacriticScore; }
    public float getUserScore() { return userScore; }
    public int getAchievements() { return achievements; }
    public List<String> getPublishers() { return publishers; }
    public List<String> getDevelopers() { return developers; }
    public List<String> getCategories() { return categories; }
    public List<String> getGenres() { return genres; }
    public List<String> getTags() { return tags; }
}

class ReadCSV {
    public static List<Games> readCSV(String path) {
        List<Games> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); //linha de cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = dividirLinhaCSV(linha);
                while (partes.length < 14) {
                    partes = Arrays.copyOf(partes, partes.length + 1);
                    partes[partes.length - 1] = "";
                }
                lista.add(new Games(partes));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler CSV: " + e.getMessage(), e);
        }
        return lista;
    }

    private static String[] dividirLinhaCSV(String linha) {
        List<String> campos = new ArrayList<>();
        boolean dentroAspas = false;
        StringBuilder atual = new StringBuilder();

        for (char c : linha.toCharArray()) {
            if (c == '"') {
                dentroAspas = !dentroAspas;
            } else if (c == ',' && !dentroAspas) {
                campos.add(atual.toString().trim()); // adiciona o campo atual e remove os espaços
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }
        campos.add(atual.toString().trim());
        return campos.toArray(new String[0]);
    }
}

public class PesquisaBin {
    private static int comparacoes = 0;
    private static final String MATRICULA = "855633"; // final significa que não pode ser alterada durante a execução

    public static void main(String[] args) throws IOException {
        List<Games> gamesList = ReadCSV.readCSV("/tmp/games.csv");
        
        Scanner sc = new Scanner(System.in);
        List<Games> selecionados = new ArrayList<>();
        
        String entrada;
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            try {
                int idBusca = Integer.parseInt(entrada);
                Games encontrado = null;
                for(Games game : gamesList) { // percorre toda a lista
                    if(game.getId() == idBusca) {
                        encontrado = game;
                        break;
                    }
                }

                if (encontrado != null) {
                    selecionados.add(encontrado.clone());
                }
            } catch (NumberFormatException e) {

            }
        }

        Selecao(selecionados);
        
        List<String> nomesBusca = new ArrayList<>();
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            nomesBusca.add(entrada);
        }

        long inicioBusca = System.currentTimeMillis();
        comparacoes = 0; 
        
        for (String nome : nomesBusca) {
            boolean encontrado = pesquisaBinaria(selecionados, nome);
            System.out.println(encontrado ? " SIM" : " NAO"); 
        }

        long fimBusca = System.currentTimeMillis();
        long tempoExecucao = fimBusca - inicioBusca;
        
        criarLog(tempoExecucao, comparacoes); 
        
        sc.close();
    }

    private static void Selecao(List<Games> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).getName().compareTo(lista.get(minIdx).getName()) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Games temp = lista.get(i);
                lista.set(i, lista.get(minIdx));
                lista.set(minIdx, temp);
            }
        }
    }

    public static boolean pesquisaBinaria(List<Games> lista, String nome) {
        int esq = 0;
        int dir = lista.size() - 1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2; 
            comparacoes++;
            int comparacao = lista.get(meio).getName().compareTo(nome);
            
            if (comparacao == 0) {
                return true;
            } else if (comparacao < 0) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return false;
    }

    public static void criarLog(long tempo, int totalComparacoes) {
        try {
            FileWriter writer = new FileWriter(MATRICULA + "_binaria.txt");
            writer.write(MATRICULA + "\t" + tempo + "\t" + totalComparacoes + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}