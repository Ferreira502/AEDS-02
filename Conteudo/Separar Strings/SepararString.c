// Separar uma String em duas na Linguagem C

// Utilizando o SSCANF

// #include <stdio.h>

// int main() {
//     char str[] = "tpo ocder";
//     char parte1[50], parte2[50];

//     sscanf(str, "%s %s", parte1, parte2);

//     printf("Primeira parte: %s\n", parte1);
//     printf("Segunda parte: %s\n", parte2);

//     return 0;
// }

//------------------------------------------------------------------------------------

// Fazendo de Forma interativa

// #include <stdio.h>
// #include <string.h>

// int main() {
//     char str[] = "tpo ocder";
//     char parte1[50], parte2[50];

//     int i = 0, j = 0;

//     while (str[i] != ' ' && str[i] != '\0') {
//         parte1[i] = str[i];
//         i++;
//     }
//     parte1[i] = '\0';

//     i++; // pula o espaço

//     while (str[i] != '\0') {
//         parte2[j] = str[i];
//         i++;
//         j++;
//     }
//     parte2[j] = '\0';

//     printf("Primeira parte: %s\n", parte1);
//     printf("Segunda parte: %s\n", parte2);

//     return 0;
// }