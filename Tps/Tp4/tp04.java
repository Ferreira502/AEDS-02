import java.util.*;
import java.io.*;

class Game 
{
    private int AppId;
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

    public int getAppId() 
    { 
        return AppId; 
    }
    public void setAppId(int AppId) 
    { 
        this.AppId = AppId; 
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

    public Game ler(String linha) 
    {
        Game g = new Game();
        String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < campos.length; i++) 
        {
            campos[i] = campos[i].trim().replaceAll("^\"|\"$", "");
        }

        String[] dados = new String[14];
        for (int i = 0; i < dados.length; i++) 
        {
            if (i < campos.length) 
            {
                dados[i] = campos[i];
            } 
            else 
            {
                dados[i] = "";
            }
        }

    if (!dados[0].isEmpty()) {
        g.setAppId(Integer.parseInt(dados[0]));
    } else {
        g.setAppId(0);
    }

    g.setName(dados[1]);
    g.setReleaseDate(dados[2]);

    if (!dados[3].isEmpty()) {
        g.setEstimatedOwners(Integer.parseInt(dados[3]));
    } else {
        g.setEstimatedOwners(0);
    }

    if (!dados[4].isEmpty()) {
        g.setPrice(Double.parseDouble(dados[4]));
    } else {
        g.setPrice(0.0);
    }

    if (!dados[5].isEmpty()) {
        g.setSupportedLanguages(tratarArray(dados[5]));
    } else {
        g.setSupportedLanguages(new String[0]);
    }

    if (!dados[6].isEmpty()) {
        g.setMetacriticScore(Integer.parseInt(dados[6]));
    } else {
        g.setMetacriticScore(0);
    }

    if (!dados[7].isEmpty()) {
        g.setUserScore(Double.parseDouble(dados[7]));
    } else {
        g.setUserScore(0.0);
    }

    if (!dados[8].isEmpty()) {
        g.setAchievements(Integer.parseInt(dados[8]));
    } else {
        g.setAchievements(0);
    }

    if (!dados[9].isEmpty()) {
        g.setPublishers(tratarArray(dados[9]));
    } else {
        g.setPublishers(new String[0]);
    }

    if (!dados[10].isEmpty()) {
        g.setDevelopers(tratarArray(dados[10]));
    } else {
        g.setDevelopers(new String[0]);
    }

    if (!dados[11].isEmpty()) {
        g.setCategories(tratarArray(dados[11]));
    } else {
        g.setCategories(new String[0]);
    }

    if (!dados[12].isEmpty()) {
        g.setGenres(tratarArray(dados[12]));
    } else {
        g.setGenres(new String[0]);
    }

    if (!dados[13].isEmpty()) {
        g.setTags(tratarArray(dados[13]));
    } else {
        g.setTags(new String[0]);
    }

    return g;
}
 
        private String[] tratarArray(String campo) 
        {
            if (campo == null || campo.isEmpty()) return new String[0];

            String temp = campo.replaceAll("^\\[|\\]$", "");
            
            String[] itens = temp.split(",");

            for (int i = 0; i < itens.length; i++) {
                itens[i] = itens[i].trim().replaceAll("^\"|\"$", "");
            }

            return itens;
        }

        private String formatarData(String data) 
        {
            if (data == null || data.isEmpty()) 
            {
                return "";
            }

            String[] partes = data.split(" ");
            if (partes.length != 3) 
            {
                return data;
            }

            String mes = partes[0];
            String dia = partes[1].replace(",", "");
            String ano = partes[2];

            int diaInt = Integer.parseInt(dia);
            String diaFormatado = String.format("%02d", diaInt);

            String mesNum = "";
            switch (mes) 
            {
                case "Jan": mesNum = "01"; break;
                case "Feb": mesNum = "02"; break;
                case "Mar": mesNum = "03"; break;
                case "Apr": mesNum = "04"; break;
                case "May": mesNum = "05"; break;
                case "Jun": mesNum = "06"; break;
                case "Jul": mesNum = "07"; break;
                case "Aug": mesNum = "08"; break;
                case "Sep": mesNum = "09"; break;
                case "Oct": mesNum = "10"; break;
                case "Nov": mesNum = "11"; break;
                case "Dec": mesNum = "12"; break;
                default: mesNum = "00"; break;
            }

            return diaFormatado + "/" + mesNum + "/" + ano;
        }


    private String formatarArray(String[] arr) 
    {
        if (arr == null || arr.length == 0) 
        {
            return "[]";
        }

        for (int i = 0; i < arr.length; i++) 
        {
            arr[i] = arr[i].trim().replaceAll("^['\"]|['\"]$", "");
        }

        return "[" + String.join(", ", arr) + "]";
    }


    public String formatarSaida() 
    {
        return "=> " + AppId +
                " ## " + name +
                " ## " + formatarData(releaseDate) +
                " ## " + estimatedOwners +
                " ## " + price +
                " ## " + formatarArray(supportedLanguages) +
                " ## " + metacriticScore +
                " ## " + userScore +
                " ## " + achievements +
                " ## " + formatarArray(publishers) +
                " ## " + formatarArray(developers) +
                " ## " + formatarArray(categories) +
                " ## " + formatarArray(genres) +
                " ## " + formatarArray(tags) +
                " ##";
    }
}

public class tp04 
{
    public static void main(String[] args) 
    {
        Game[] lista = new Game[100000];
        int count = 0;

        try 
        {
            Scanner arquivo = new Scanner(new File("/tmp/games.csv"));
            if (arquivo.hasNextLine()) arquivo.nextLine(); 

            while (arquivo.hasNextLine() && count < lista.length) 
            {
                String linha = arquivo.nextLine();
                if (linha.trim().isEmpty()) continue;

                Game g = new Game();
                g = g.ler(linha);
                lista[count] = g;
                count++;
            }
            arquivo.close();
        } 
        catch (Exception e) 
        {
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            String entrada = sc.nextLine();
            if (entrada.equals("FIM")) break;

            try 
            {
                int id = Integer.parseInt(entrada);
                boolean encontrado = false;

                for (int i = 0; i < count; i++) 
                {
                    if (lista[i].getAppId() == id) 
                    {
                        System.out.println(lista[i].formatarSaida());
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) 
                {
                    System.out.println("");
                }

            } 
            catch (NumberFormatException e) 
            {
                System.out.println("");
            }
        }
        sc.close();
    }
}