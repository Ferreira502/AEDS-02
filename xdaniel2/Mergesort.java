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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            SimpleDateFormat parser = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
            Date date = parser.parse(cleanedDate);
            return formatter.format(date);
        } catch (java.text.ParseException e) {
        }

        try {
            SimpleDateFormat parser = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
            Date date = parser.parse(cleanedDate);
            return "01/" + formatter.format(date).substring(3);
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
        for (String part : parts) {
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
          .append(arrayToString(supportedLanguages)).append(" ## ").append(metacriticScore).append(" ## ");
          
        sb.append(userScore == -1.0f ? "0.0" : String.format("%.1f", userScore)).append(" ## ").append(achievements).append(" ## ");
        
        sb.append(arrayToString(publishers)).append(" ## ").append(arrayToString(developers)).append(" ## ")
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
    public float getPrice() { return price; }
    public String getReleaseDate() { return releaseDate; }
    public int getEstimatedOwners() { return estimatedOwners; }
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
            br.readLine(); 
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
                campos.add(atual.toString().trim());
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }
        campos.add(atual.toString().trim());
        return campos.toArray(new String[0]);
    }
}

public class Mergesort {
    private static int comparacoes = 0;
    private static int movimentacoes = 0;
    private static final String MATRICULA = "855633"; 

    public static void main(String[] args) throws IOException {
        List<Games> gamesList = ReadCSV.readCSV("/tmp/games.csv");
        
        Scanner sc = new Scanner(System.in, "UTF-8");
        List<Games> selecionadosList = new ArrayList<>();
        
        String entrada;
        while (!(entrada = sc.nextLine()).equals("FIM")) {
            try {
                int idBusca = Integer.parseInt(entrada);
                Games encontrado = null;
                for(Games game : gamesList) {
                    if(game.getId() == idBusca) {
                        encontrado = game;
                        break;
                    }
                }

                if (encontrado != null) {
                    selecionadosList.add(encontrado.clone());
                }
            } catch (NumberFormatException e) {
            }
        }

        Games[] selecionados = selecionadosList.toArray(new Games[0]);

        long inicioOrdenacao = System.currentTimeMillis();
        comparacoes = 0;
        movimentacoes = 0;

        mergeSort(selecionados, 0, selecionados.length - 1); 
        
        long fimOrdenacao = System.currentTimeMillis();
        long tempoExecucao = fimOrdenacao - inicioOrdenacao;

        System.out.println("| 5 pre\u00E7os mais caros |");
        for (int i = selecionados.length - 1; i >= Math.max(0, selecionados.length - 5); i--) {
            System.out.println(formatarSaida(selecionados[i]));
        }

        System.out.println();

        System.out.println("| 5 pre\u00E7os mais baratos |");
        for (int i = 0; i < Math.min(5, selecionados.length); i++) {
            System.out.println(formatarSaida2(selecionados[i]));
        }

        criarLog(tempoExecucao, comparacoes, movimentacoes);
        
        sc.close();
    }

    private static String formatarSaida(Games game) {
        StringBuilder sb = new StringBuilder();
        sb.append("=> ").append(game.getId()).append(" ## ").append(game.getName()).append(" ## ").append(game.getReleaseDate())
          .append(" ## ").append(game.getEstimatedOwners()).append(" ## ").append(String.format("%.2f", game.getPrice())).append(" ## ")
          .append(arrayToString(game.getSupportedLanguages())).append(" ## ").append(game.getMetacriticScore()).append(" ## ");
          
        sb.append(game.getUserScore() == -1.0f ? "0.0" : String.format("%.1f", game.getUserScore())).append(" ## ").append(game.getAchievements()).append(" ## ");
        
        sb.append(arrayToString(game.getPublishers())).append(" ## ").append(arrayToString(game.getDevelopers())).append(" ## ")
          .append(arrayToString(game.getCategories())).append(" ## ").append(arrayToString(game.getGenres())).append(" ## ")
          .append(arrayToString(game.getTags())).append(" ##");
        return sb.toString();
    }

        private static String formatarSaida2(Games game) {
        StringBuilder sb = new StringBuilder();
        sb.append("=> ").append(game.getId()).append(" ## ").append(game.getName()).append(" ## ").append(game.getReleaseDate())
          .append(" ## ").append(game.getEstimatedOwners()).append(" ## ").append(String.format("%.1f", game.getPrice())).append(" ## ")
          .append(arrayToString(game.getSupportedLanguages())).append(" ## ").append(game.getMetacriticScore()).append(" ## ");
          
        sb.append(game.getUserScore() == -1.0f ? "0.0" : String.format("%.1f", game.getUserScore())).append(" ## ").append(game.getAchievements()).append(" ## ");
        
        sb.append(arrayToString(game.getPublishers())).append(" ## ").append(arrayToString(game.getDevelopers())).append(" ## ")
          .append(arrayToString(game.getCategories())).append(" ## ").append(arrayToString(game.getGenres())).append(" ## ")
          .append(arrayToString(game.getTags())).append(" ##");
        return sb.toString();
    }

    private static String arrayToString(List<String> list) {
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

    private static int compareGames(Games a, Games b) {
        comparacoes++; 
        if (a.getPrice() < b.getPrice()) {
            return -1;
        } else if (a.getPrice() > b.getPrice()) {
            return 1;
        } else {
            return a.getId() - b.getId();
        }
    }

    private static void mergeSort(Games[] array, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(array, esq, meio);
            mergeSort(array, meio + 1, dir);
            merge(array, esq, meio, dir);
        }
    }

    private static void merge(Games[] array, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        Games[] array1 = new Games[n1];
        Games[] array2 = new Games[n2];

        for (int i = 0; i < n1; i++) {
            array1[i] = array[esq + i];
            movimentacoes++; 
        }

        for (int i = 0; i < n2; i++) {
            array2[i] = array[meio + 1 + i];
            movimentacoes++; 
        }

        int i = 0, j = 0;
        int k = esq;

        while (i < n1 && j < n2) {
            if (compareGames(array1[i], array2[j]) <= 0) {
                array[k] = array1[i];
                i++;
            } else {
                array[k] = array2[j];
                j++;
            }
            k++;
            movimentacoes++; 
        }

        while (i < n1) {
            array[k] = array1[i];
            i++;
            k++;
            movimentacoes++; 
        }

        while (j < n2) {
            array[k] = array2[j];
            j++;
            k++;
            movimentacoes++; 
        }
    }

    public static void criarLog(long tempo, int totalComparacoes, int totalMovimentacoes) {
        try {
            FileWriter writer = new FileWriter(MATRICULA + "_mergesort.txt");
            writer.write(MATRICULA + "\t" + totalComparacoes + "\t" + totalMovimentacoes + "\t" + tempo + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}