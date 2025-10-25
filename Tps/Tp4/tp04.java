/* 
Separando cada elemento do arquivo csv
AppID: 897820
Name:Reigns: Game of Thrones
Release date: "Oct 18, 2018"
Estimated owners: 75000
Price: 3.99
Supported languages: "['English', 'French', 'German', 'Spanish - Spain', 'Italian', 'Japanese', 'Korean', 'Portuguese - Brazil', 'Russian', 'Simplified Chinese', 'Traditional Chinese']"
Metacritic score: 84
User score: 0
Achievements: 10
Publishers: Devolver Digital
Developers: Nerial
Categories: "Single-player,Steam Achievements,Full controller support"
Genres: "Adventure,Indie,RPG"
Tags: "RPG,Indie,Card Game,Adventure,Choices Matter,2D,Medieval,Singleplayer,Minimalist,Choose Your Own Adventure,Strategy,Story Rich,Dark Humor,Great Soundtrack,Difficult,Dark Comedy,Funny,Comedy,Cute"
*/


import java.io.*;
import java.util.*;

class Game 
{
    private int appId;
    private String name;
    private String releaseDate;
    private int estimatedOwners;
    private double price;
    private String[] supportedLanguages;
    private int metacriticScore;
    private double userScore;
    private int achievements;
    private String[] publishers;
    private String[] developers;
    private String[] categories;
    private String[] genres;
    private String[] tags;

    public int getappId() 
    { 
        return appId; 
    }
    public void setappId(int appId) 
    { 
        this.appId = appId; 
    }

    public String getName() 
    { 
        return name; 
    }
    public void setName(String name) 
    { 
        this.name = name; 
    }

    public String getReleaseDate() 
    { 
        return releaseDate; 
    }
    public void setReleaseDate(String releaseDate) 
    { 
        this.releaseDate = releaseDate; 
    }

    public int getEstimatedOwners() 
    { 
        return estimatedOwners; 
    }
    public void setEstimatedOwners(int estimatedOwners) 
    { 
        this.estimatedOwners = estimatedOwners; 
    }

    public double getPrice() 
    { 
        return price; 
    }
    public void setPrice(double price) 
    { 
        this.price = price; 
    }
    
    public String[] getSupportedLanguages() 
    { 
        return supportedLanguages; 
    }
    public void setSupportedLanguages(String[] supportedLanguages) 
    { 
        this.supportedLanguages = supportedLanguages; 
    }

    public int getMetacriticScore() 
    { 
        return metacriticScore; 
    }
    public void setMetacriticScore(int metacriticScore) 
    { 
        this.metacriticScore = metacriticScore; 
    }

    public double getUserScore() 
    { 
        return userScore; 
    }
    public void setUserScore(double userScore) 
    { 
        this.userScore = userScore; 
    }

    public int getAchievements() 
    { 
        return achievements; 
    }
    public void setAchievements(int achievements) 
    { 
        this.achievements = achievements;
    }

    public String[] getPublishers() 
    { 
        return publishers; 
    }
    public void setPublishers(String[] publishers) 
    { 
        this.publishers = publishers; 
    }

    public String[] getDevelopers() 
    { 
        return developers; 
    }
    public void setDevelopers(String[] developers) 
    { 
        this.developers = developers; 
    }

    public String[] getCategories() 
    { 
        return categories; 
    }
    public void setCategories(String[] categories) 
    { 
        this.categories = categories; 
    }

    public String[] getGenres() 
    { 
        return genres; 
    }
    public void setGenres(String[] genres) 
    { 
        this.genres = genres; 
    }
    
    public String[] getTags() 
    { 
        return tags; 
    }
    public void setTags(String[] tags) 
    { 
        this.tags = tags; 
    }

    public static Game ler(String linha) 
    {
        Game g = new Game();
        String[] campos = splitParaLinguaguem(linha);

        g.appId = Integer.parseInt(colocarEspaco(campos, 0));
        g.name = colocarEspaco(campos, 1);
        g.releaseDate = formatarData(colocarEspaco(campos, 2));
        g.estimatedOwners = Integer.parseInt(colocarEspaco(campos, 3));
        g.price = Double.parseDouble(colocarEspaco(campos, 4));
        g.supportedLanguages = stringParaArray(colocarEspaco(campos, 5));
        g.metacriticScore = Integer.parseInt(colocarEspaco(campos, 6));
        g.userScore = Double.parseDouble(colocarEspaco(campos, 7));
        g.achievements = Integer.parseInt(colocarEspaco(campos, 8));
        g.publishers = stringParaArray(colocarEspaco(campos, 9));
        g.developers = stringParaArray(colocarEspaco(campos, 10));
        g.categories = stringParaArray(colocarEspaco(campos, 11));
        g.genres = stringParaArray(colocarEspaco(campos, 12));
        g.tags = stringParaArray(colocarEspaco(campos, 13));

        return g;
    }

    private static String[] splitParaLinguaguem(String linha) 
    {
        String[] campos = new String[100];
        int count = 0;

        StringBuilder campoAtual = new StringBuilder();
        boolean dentroAspas = false;
        int colchetes = 0;

        for (int i = 0; i < linha.length(); i++) 
        {
            char c = linha.charAt(i);

            if (c == '\"') {
                dentroAspas = !dentroAspas; 
                campoAtual.append(c);
            } else if (c == '[') {
                colchetes++;
                campoAtual.append(c);
            } else if (c == ']') {
                colchetes--;
                campoAtual.append(c);
            } else if (c == ',' && !dentroAspas && colchetes == 0) {
                campos[count++] = campoAtual.toString().trim();
                campoAtual.setLength(0);
            } else {
                campoAtual.append(c);
            }
        }

        // Adiciona o último campo
        campos[count++] = campoAtual.toString().trim();

        // Cria um array do tamanho exato
        String[] resultado = new String[count];
        for (int i = 0; i < count; i++) {
            resultado[i] = campos[i];
        }

        return resultado;
    }


    private static String formatarData(String data) 
    {
        if (data == null || data.isEmpty()) 
        {
            return "";
        }

        String[] partes = data.split(" ");
        
        if (partes.length < 3) 
        {
            return data;
        }

        String mesTexto = partes[0];
        String diaTexto = partes[1].replace(",", "");
        String anoTexto = partes[2];

        int dia = Integer.parseInt(diaTexto);

        String diaFormatado;
        if (dia < 10) 
        {
            diaFormatado = "0" + dia;
        } 
        else 
        {
            diaFormatado = String.valueOf(dia);
        }

        String mesFormatado;
        switch (mesTexto) 
        {
            case "Jan": mesFormatado = "01"; break;
            case "Feb": mesFormatado = "02"; break;
            case "Mar": mesFormatado = "03"; break;
            case "Apr": mesFormatado = "04"; break;
            case "May": mesFormatado = "05"; break;
            case "Jun": mesFormatado = "06"; break;
            case "Jul": mesFormatado = "07"; break;
            case "Aug": mesFormatado = "08"; break;
            case "Sep": mesFormatado = "09"; break;
            case "Oct": mesFormatado = "10"; break;
            case "Nov": mesFormatado = "11"; break;
            case "Dec": mesFormatado = "12"; break;
            default: mesFormatado = "00"; break;
        }

        return diaFormatado + "/" + mesFormatado + "/" + anoTexto;
    }

    private static String colocarEspaco(String[] c, int i) 
    {
        if (i >= c.length) return "";
        return c[i].replace("\"", "").trim();
    }

    private static String[] stringParaArray(String campo) 
    {
        campo = campo.replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim();
        if (campo.isEmpty()) return new String[0];
        return campo.split(",");
    }

    public String formatarSaida() 
    {
        return "=> " + appId + " ## " + name + " ## " + releaseDate + " ## " + estimatedOwners
                + " ## " + price + " ## " + Arrays.toString(supportedLanguages) + " ## " + metacriticScore
                + " ## " + userScore + " ## " + achievements + " ## " + Arrays.toString(publishers)
                + " ## " + Arrays.toString(developers) + " ## " + Arrays.toString(categories)
                + " ## " + Arrays.toString(genres) + " ## " + Arrays.toString(tags) + " ##";
    }
}

public class tp04
{
    public static void main(String[] args) 
    {
        Game[] lista = new Game[100000];
        int count = 0;

        File file = new File("games.csv");
        Scanner arquivo = null;

        try 
        {
            arquivo = new Scanner(file);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println(""); //arquivo nao encontrado
            return;
        }

        if (arquivo.hasNextLine()) //pular a primeira linha
        { 
            arquivo.nextLine();
        }   
        
        while (arquivo.hasNextLine() && count < lista.length) 
        {
            String linha = arquivo.nextLine();
            if (linha.trim().isEmpty()) continue;
            lista[count++] = Game.ler(linha);
        }
        arquivo.close();

        Scanner sc = new Scanner(System.in); //entrada do id

        while (true) 
        {
            String entrada = sc.nextLine().trim();

            if (entrada.equals("FIM")) 
            {
                break;
            }

            // Verifica se a entrada contém apenas números
            boolean entradaValida = true;
            for (int i = 0; i < entrada.length(); i++) 
            {
                char c = entrada.charAt(i);
                if (c < '0' || c > '9') 
                {
                    entradaValida = false;
                    break;
                }
            }

            if (entradaValida) 
            {
                int id = Integer.parseInt(entrada);
                Game jogoEncontrado = null;

                // Busca o jogo pelo ID
                for (int i = 0; i < count; i++) 
                {
                    if (lista[i].getappId() == id) 
                    {
                        jogoEncontrado = lista[i];
                        break;
                    }
                }

                if (jogoEncontrado != null)
                {
                    System.out.println(jogoEncontrado.formatarSaida());
                } 
                else 
                {
                    System.out.println(""); // não achou
                }

            } 
            else 
            {
                System.out.println(""); // entrada inválida
            }


        }

        sc.close();
    }
}
