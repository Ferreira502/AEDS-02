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
        String[] campos = separarCaracter(linha);

        for (int i = 0; i < campos.length; i++) 
        {
            campos[i] = removerAspas(campos[i]);
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

        if (!dados[0].isEmpty()) 
        {
            g.setAppId(Integer.parseInt(dados[0]));
        } 
        else 
        {
            g.setAppId(0);
        }

        g.setName(dados[1]);
        g.setReleaseDate(dados[2]);

        if (!dados[3].isEmpty()) 
        {
            g.setEstimatedOwners(Integer.parseInt(dados[3]));
        } 
        else 
        {
            g.setEstimatedOwners(0);
        }

        if (!dados[4].isEmpty()) 
        {
            g.setPrice(Double.parseDouble(dados[4]));
        } 
        else 
        {
            g.setPrice(0.0);
        }

        if (!dados[5].isEmpty()) 
        {
            g.setSupportedLanguages(tratarArray(dados[5]));
        } 
        else 
        {
            g.setSupportedLanguages(new String[0]);
        }

        if (!dados[6].isEmpty()) 
        {
            g.setMetacriticScore(Integer.parseInt(dados[6]));
        } 
        else 
        {
            g.setMetacriticScore(0);
        }

        if (!dados[7].isEmpty()) 
        {
            g.setUserScore(Double.parseDouble(dados[7]));
        } 
        else 
        {
            g.setUserScore(0.0);
        }

        if (!dados[8].isEmpty()) 
        {
            g.setAchievements(Integer.parseInt(dados[8]));
        } 
        else 
        {
            g.setAchievements(0);
        }

        if (!dados[9].isEmpty()) 
        {
            g.setPublishers(tratarArray(dados[9]));
        } 
        else 
        {
            g.setPublishers(new String[0]);
        }

        if (!dados[10].isEmpty()) 
        {
            g.setDevelopers(tratarArray(dados[10]));
        } 
        else 
        {
            g.setDevelopers(new String[0]);
        }

        if (!dados[11].isEmpty()) 
        {
            g.setCategories(tratarArray(dados[11]));
        } 
        else 
        {
            g.setCategories(new String[0]);
        }

        if (!dados[12].isEmpty()) 
        {
            g.setGenres(tratarArray(dados[12]));
        } 
        else 
        {
            g.setGenres(new String[0]);
        }

        if (!dados[13].isEmpty()) 
        {
            g.setTags(tratarArray(dados[13]));
        } 
        else 
        {
            g.setTags(new String[0]);
        }

        return g;
    }

    private String[] separarCaracter(String linha) 
    {
        ArrayList<String> campos = new ArrayList<>();
        boolean dentroAspas = false;
        StringBuilder campoAtual = new StringBuilder();
        
        for (int i = 0; i < linha.length(); i++) 
        {
            char c = linha.charAt(i);
            
            if (c == '"') 
            {
                dentroAspas = !dentroAspas;
            } 
            else if (c == ',' && !dentroAspas) 
            {
                campos.add(campoAtual.toString());
                campoAtual = new StringBuilder();
            } 
            else 
            {
                campoAtual.append(c);
            }
        }
        campos.add(campoAtual.toString());
        
        return campos.toArray(new String[0]);
    }

    private String removerAspas(String str) 
    {
        if (str.length() >= 2 && str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"') 
        {
            return str.substring(1, str.length() - 1).trim();
        }
        return str.trim();
    }

    private String[] tratarArray(String campo) 
    {
        if (campo == null || campo.isEmpty()) return new String[0];

        String temp = campo;
        if (temp.length() >= 2 && temp.charAt(0) == '[' && temp.charAt(temp.length() - 1) == ']') 
        {
            temp = temp.substring(1, temp.length() - 1);
        }
        
        ArrayList<String> itens = new ArrayList<>();
        StringBuilder itemAtual = new StringBuilder();
        boolean dentroAspas = false;
        
        for (int i = 0; i < temp.length(); i++) 
        {
            char c = temp.charAt(i);
            
            if (c == '"') 
            {
                dentroAspas = !dentroAspas;
            } 
            else if (c == ',' && !dentroAspas) 
            {
                String item = itemAtual.toString().trim();
                item = removerAspas(item);
                if (!item.isEmpty()) 
                {
                    itens.add(item);
                }
                itemAtual = new StringBuilder();
            } 
            else 
            {
                itemAtual.append(c);
            }
        }
        
        String ultimoItem = itemAtual.toString().trim();
        ultimoItem = removerAspas(ultimoItem);
        if (!ultimoItem.isEmpty()) 
        {
            itens.add(ultimoItem);
        }

        return itens.toArray(new String[0]);
    }

    private String formatarData(String data) 
    {
        if (data == null || data.isEmpty()) 
        {
            return "";
        }

        String[] partes = separarEspacos(data);
        if (partes.length != 3) 
        {
            return data;
        }

        String mes = partes[0];
        String dia = partes[1];
        if (dia.length() > 0 && dia.charAt(dia.length() - 1) == ',') 
        {
            dia = dia.substring(0, dia.length() - 1);
        }
        String ano = partes[2];

        int diaInt = Integer.parseInt(dia);
        String diaFormatado;
        if (diaInt < 10)
        {
            diaFormatado = "0" + diaInt;
        } 
        else 
        {
            diaFormatado = String.valueOf(diaInt);
        }

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

    private String[] separarEspacos(String str) 
    {
        ArrayList<String> partes = new ArrayList<>();
        StringBuilder parteAtual = new StringBuilder();
        boolean dentroPalavra = false;
        
        for (int i = 0; i < str.length(); i++) 
        {
            char c = str.charAt(i);
            
            if (c == ' ' || c == '\t') 
            {
                if (dentroPalavra) 
                {
                    partes.add(parteAtual.toString());
                    parteAtual = new StringBuilder();
                    dentroPalavra = false;
                }
            } 
            else 
            {
                parteAtual.append(c);
                dentroPalavra = true;
            }
        }
        
        if (dentroPalavra) 
        {
            partes.add(parteAtual.toString());
        }
        
        return partes.toArray(new String[0]);
    }

    private String formatarArray(String[] arr) 
    {
        if (arr == null || arr.length == 0) 
        {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < arr.length; i++) 
        {
            if (i > 0) 
            {
                sb.append(", ");
            }
            String item = arr[i];
            if (item.length() >= 2) 
            {
                char primeiro = item.charAt(0);
                char ultimo = item.charAt(item.length() - 1);
                if ((primeiro == '\'' && ultimo == '\'') || (primeiro == '"' && ultimo == '"')) 
                {
                    item = item.substring(1, item.length() - 1);
                }
            }
            sb.append(item);
        }
        
        sb.append("]");
        return sb.toString();
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

        File arquivo = new File("/tmp/games.csv");
        Scanner leitorArquivo = null;
        
        leitorArquivo = criarScanner(arquivo);
        if (leitorArquivo != null) 
        {
            if (leitorArquivo.hasNextLine()) leitorArquivo.nextLine(); 

            while (leitorArquivo.hasNextLine() && count < lista.length) 
            {
                String linha = leitorArquivo.nextLine();
                if (linha.trim().isEmpty()) continue;

                Game g = new Game();
                g = g.ler(linha);
                lista[count] = g;
                count++;
            }
            leitorArquivo.close();
        }

        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            String entrada = sc.nextLine();

            if (entrada.length() == 3    &&
                entrada.charAt(0) == 'F' &&
                entrada.charAt(1) == 'I' &&
                entrada.charAt(2) == 'M') 
                {
                break;
            }

            if (isInteger(entrada)) 
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
            else 
            {
                System.out.println("");
            }
        }
        sc.close();
    }
    
    private static Scanner criarScanner(File arquivo) 
    {
        try 
        {
            return new Scanner(arquivo);
        } 
        catch (FileNotFoundException e) 
        {
            return null;
        }
    }
    
    private static boolean isInteger(String str) 
    {
        if (str == null || str.isEmpty()) 
        {
            return false;
        }
        for (int i = 0; i < str.length(); i++) 
        {
            if (!Character.isDigit(str.charAt(i))) 
            {
                return false;
            }
        }
        return true;
    }
}