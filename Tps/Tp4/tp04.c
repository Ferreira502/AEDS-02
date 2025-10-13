#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAX_STRING 500
#define MAX_ARRAY 100
#define MAX_LINE 5000

typedef struct 
{
    int appId;
    char name[MAX_STRING];
    char releaseDate[MAX_STRING];
    int estimatedOwners;
    double price;
    char supportedLanguages[MAX_ARRAY][MAX_STRING];
    int supportedLanguagesCount;
    int metacriticScore;
    double userScore;
    int achievements;
    char publishers[MAX_ARRAY][MAX_STRING];
    int publishersCount;
    char developers[MAX_ARRAY][MAX_STRING];
    int developersCount;
    char categories[MAX_ARRAY][MAX_STRING];
    int categoriesCount;
    char genres[MAX_ARRAY][MAX_STRING];
    int genresCount;
    char tags[MAX_ARRAY][MAX_STRING];
    int tagsCount;
} Game;

void initializeGame(Game *game) 
{
    game->appId = 0;
    strcpy(game->name, "");
    strcpy(game->releaseDate, "");
    game->estimatedOwners = 0;
    game->price = 0.0;
    game->supportedLanguagesCount = 0;
    game->metacriticScore = -1;
    game->userScore = -1.0;
    game->achievements = 0;
    game->publishersCount = 0;
    game->developersCount = 0;
    game->categoriesCount = 0;
    game->genresCount = 0;
    game->tagsCount = 0;
}

void removeAspas(char *str) 
{
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++)
        if (str[i] != '"' && str[i] != '\'')
            str[j++] = str[i];
    str[j] = '\0';
}

bool isSpace(char c) 
{
    return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
}

void trim(char *str) 
{
    int start = 0;
    while (isSpace(str[start])) start++;
    int end = strlen(str) - 1;
    while (end >= start && isSpace(str[end])) end--;

    int j = 0;
    for (int i = start; i <= end; i++) 
    {
        str[j++] = str[i];
    }
    str[j] = '\0';
}

bool isDigit(char c) 
{ 
    return (c >= '0' && c <= '9'); 
}

int stringParaInt(char *str) 
{
    int res = 0;
    int i = 0;
    while (str[i] != '\0') 
    {
        if (isDigit(str[i])) 
        {
            res = res * 10 + (str[i] - '0');
        }
        i++;
    }
    return res;
}

double stringParaDouble(char *str) 
{
    double res = 0.0;
    int i = 0;
    while (str[i] != '\0' && str[i] != '.') 
    {
        if (isDigit(str[i])) res = res * 10 + (str[i] - '0');
        i++;
    }
    if (str[i] == '.') 
    {
        i++;
        double divisor = 10.0;
        while (str[i] != '\0') 
        {
            if (isDigit(str[i])) 
            {
                res += (str[i] - '0') / divisor;
                divisor *= 10;
            }
            i++;
        }
    }
    return res;
}

int separarCamposCSV(char *linha, char campos[][MAX_STRING]) 
{
    int count = 0;
    bool dentroAspas = false;
    char campoAtual[MAX_STRING] = "";
    int pos = 0;

    for (int i = 0; linha[i] != '\0'; i++) 
    {
        char c = linha[i];
        if (c == '"') dentroAspas = !dentroAspas;
        else if (c == ',' && !dentroAspas) 
        {
            campoAtual[pos] = '\0';
            trim(campoAtual);
            strncpy(campos[count++], campoAtual, MAX_STRING - 1);
            pos = 0;
            campoAtual[0] = '\0';
        } else campoAtual[pos++] = c;
    }
    if (pos > 0) 
    {
        campoAtual[pos] = '\0';
        trim(campoAtual);
        strncpy(campos[count++], campoAtual, MAX_STRING - 1);
    }
    return count;
}

void processarArray(char *campo, char array[][MAX_STRING], int *count) 
{
    *count = 0;
    if (strlen(campo) == 0) return;

    char temp[MAX_STRING];
    int len = strlen(campo);
    int start = 0, end = len - 1;

    if (campo[0] == '[') start++;
    if (campo[len - 1] == ']') end--;

    int j = 0;
    for (int i = start; i <= end; i++) temp[j++] = campo[i];
    temp[j] = '\0';

    bool dentroAspas = false;
    char itemAtual[MAX_STRING] = "";
    int pos = 0;
    for (int i = 0; temp[i] != '\0'; i++) 
    {
        char c = temp[i];
        if (c == '"') dentroAspas = !dentroAspas;
        else if (c == ',' && !dentroAspas) 
        {
            itemAtual[pos] = '\0';
            trim(itemAtual);
            removeAspas(itemAtual);
            if (strlen(itemAtual) > 0)
                strncpy(array[(*count)++], itemAtual, MAX_STRING - 1);
            pos = 0;
            itemAtual[0] = '\0';
        } 
        else itemAtual[pos++] = c;
    }
    if (pos > 0) 
    {
        itemAtual[pos] = '\0';
        trim(itemAtual);
        removeAspas(itemAtual);
        if (strlen(itemAtual) > 0)
            strncpy(array[(*count)++], itemAtual, MAX_STRING - 1);
    }
}

void formatarData(char *data) 
{
    if (strlen(data) == 0) return;

    char mes[4] = {0}, dia[3] = {0}, ano[5] = {0};
    int i = 0, j = 0;

    for (i = 0; i < 3 && data[i]; i++) 
    {
        mes[i] = data[i];
    }
    mes[3] = '\0';

    while (data[i] && data[i] == ' ') i++;

    j = 0;
    while (data[i] && data[i] != ',' && j < 2) 
    {
        if (isDigit(data[i])) 
        {
            dia[j++] = data[i];
        }
        i++;
    }
    dia[j] = '\0';
    int diaInt = stringParaInt(dia);

    while (data[i] && (data[i] == ',' || data[i] == ' ')) i++;

    j = 0;
    while (data[i] && j < 4) 
    {
        ano[j++] = data[i++];
    }
    ano[j] = '\0';

    char *mesNum = "01";
    if (strcmp(mes, "Jan") == 0) mesNum = "01";
    else if (strcmp(mes, "Feb") == 0) mesNum = "02";
    else if (strcmp(mes, "Mar") == 0) mesNum = "03";
    else if (strcmp(mes, "Apr") == 0) mesNum = "04";
    else if (strcmp(mes, "May") == 0) mesNum = "05";
    else if (strcmp(mes, "Jun") == 0) mesNum = "06";
    else if (strcmp(mes, "Jul") == 0) mesNum = "07";
    else if (strcmp(mes, "Aug") == 0) mesNum = "08";
    else if (strcmp(mes, "Sep") == 0) mesNum = "09";
    else if (strcmp(mes, "Oct") == 0) mesNum = "10";
    else if (strcmp(mes, "Nov") == 0) mesNum = "11";
    else if (strcmp(mes, "Dec") == 0) mesNum = "12";

    char novaData[11] = {0};
    if (diaInt < 10) 
    {
        novaData[0] = '0';
        novaData[1] = diaInt + '0';
    } 
    else 
    {
        novaData[0] = (diaInt / 10) + '0';
        novaData[1] = (diaInt % 10) + '0';
    }
    novaData[2] = '/';
    novaData[3] = mesNum[0];
    novaData[4] = mesNum[1];
    novaData[5] = '/';
    novaData[6] = ano[0];
    novaData[7] = ano[1];
    novaData[8] = ano[2];
    novaData[9] = ano[3];
    novaData[10] = '\0';

    strcpy(data, novaData);
}

int parseEstimatedOwners(char *str)
{
    char temp[MAX_STRING];
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++)
        if (isDigit(str[i])) temp[j++] = str[i];
    temp[j] = '\0';
    return stringParaInt(temp);
}

void lerGame(Game *game, char *linha) 
{
    initializeGame(game);
    char campos[30][MAX_STRING];
    int numCampos = separarCamposCSV(linha, campos);
    if (numCampos > 0) game->appId = stringParaInt(campos[0]);
    if (numCampos > 1) strncpy(game->name, campos[1], MAX_STRING - 1);
    if (numCampos > 2) 
    {
        strncpy(game->releaseDate, campos[2], MAX_STRING - 1);
        formatarData(game->releaseDate);
    }
    if (numCampos > 3) game->estimatedOwners = parseEstimatedOwners(campos[3]);
    if (numCampos > 4) 
    {
        if (strcmp(campos[4], "Free to Play") == 0)
            game->price = 0.0;
        else
            game->price = stringParaDouble(campos[4]);
    }
    if (numCampos > 5) processarArray(campos[5], game->supportedLanguages, &game->supportedLanguagesCount);
    if (numCampos > 6) game->metacriticScore = stringParaInt(campos[6]);
    if (numCampos > 7 && strcmp(campos[7], "tbd") != 0) game->userScore = stringParaDouble(campos[7]);
    if (numCampos > 8) game->achievements = stringParaInt(campos[8]);
    if (numCampos > 9) processarArray(campos[9], game->publishers, &game->publishersCount);
    if (numCampos > 10) processarArray(campos[10], game->developers, &game->developersCount);
    if (numCampos > 11) processarArray(campos[11], game->categories, &game->categoriesCount);
    if (numCampos > 12) processarArray(campos[12], game->genres, &game->genresCount);
    if (numCampos > 13) processarArray(campos[13], game->tags, &game->tagsCount);
}

void formatarArrayParaSaida(char array[][MAX_STRING], int count, char *resultado) 
{
    strcpy(resultado, "[");
    for (int i = 0; i < count; i++) 
    {
        strcat(resultado, array[i]);
        if (i < count - 1) strcat(resultado, ", ");
    }
    strcat(resultado, "]");
}

void formatarSaida(Game *game) 
{
    char languages[1000], pubs[1000], devs[1000], cats[1000], gens[1000], tags[1000];
    formatarArrayParaSaida(game->supportedLanguages, game->supportedLanguagesCount, languages);
    formatarArrayParaSaida(game->publishers, game->publishersCount, pubs);
    formatarArrayParaSaida(game->developers, game->developersCount, devs);
    formatarArrayParaSaida(game->categories, game->categoriesCount, cats);
    formatarArrayParaSaida(game->genres, game->genresCount, gens);
    formatarArrayParaSaida(game->tags, game->tagsCount, tags);

    printf("=> %d ## %s ## %s ## %d ## %.2f ## %s ## %d ## %.1f ## %d ## %s ## %s ## %s ## %s ## %s ##\n",
        game->appId,
        game->name, game->releaseDate, game->estimatedOwners, game->price,
        languages, game->metacriticScore, game->userScore, game->achievements,
        pubs, devs, cats, gens, tags);
}

bool isInteger(char *str) 
{
    if (str == NULL || strlen(str) == 0) return false;
    for (int i = 0; str[i] != '\0'; i++)
        if (!isDigit(str[i])) return false;
    return true;
}

int main() 
{
    FILE *arquivo = fopen("/tmp/games.csv", "r");
    if (arquivo == NULL) 
    {
        printf("Erro ao abrir o arquivo\n");
        return 1;
    }

    char entrada[100];
    char linha[MAX_LINE];
    fgets(linha, MAX_LINE, arquivo);

    while (1) 
    {
        if (fgets(entrada, sizeof(entrada), stdin) == NULL) break;
        entrada[strcspn(entrada, "\n")] = '\0';
        if (strcmp(entrada, "FIM") == 0) break;

        if (isInteger(entrada)) 
        {
            int idProcurado = stringParaInt(entrada);
            bool encontrado = false;
            rewind(arquivo);
            fgets(linha, MAX_LINE, arquivo);

            while (fgets(linha, MAX_LINE, arquivo) != NULL) 
            {
                Game g;
                lerGame(&g, linha);
                if (g.appId == idProcurado) 
                {
                    formatarSaida(&g);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) printf("\n");
        }
    }

    fclose(arquivo);
    return 0;
}