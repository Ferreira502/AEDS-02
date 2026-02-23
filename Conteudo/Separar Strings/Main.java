// Separar uma String em duas na Linguagem Java

// Utilizando a funcao split utilizando a biblioteca string da linguagem

// String str = "tpo ocder";

// String[] partes = str.split(" ");

// if (partes.length >= 2) 
// {
//     System.out.println(partes[0]);
//     System.out.println(partes[1]);
// } else {
//     System.out.println("A string não tem duas partes.");
// }

//------------------------------------------------------------------------------------

// Utilizando a biblioteca scanner

// import java.util.Scanner;

// public class Main 
// {
//     public static void main(String[] args) 
//     {

//         Scanner sc = new Scanner(System.in);

//         System.out.print("Digite duas palavras: ");
//         String str = sc.nextLine();

//         String[] partes = str.split("\\s+"); // separa por um ou mais espacos

//         if (partes.length >= 2) 
//         {
//             System.out.println("Primeira parte: " + partes[0]);
//             System.out.println("Segunda parte: " + partes[1]);
//         } 
//         else 
//         {
//             System.out.println("Voce nao digitou duas palavras");
//         }

//         sc.close();
//     }
// }

//------------------------------------------------------------------------------------

// Utilizando a funcao substring e indexof()

// import java.util.Scanner;

// public class Main 
// {
//     public static void main(String[] args) 
//     {

//         Scanner sc = new Scanner(System.in);

//         System.out.print("Digite duas palavras: ");
//         String str = sc.nextLine();

//         int posicaoEspaco = str.indexOf(" ");

//         if (posicaoEspaco != -1) 
//         {
//             String parte1 = str.substring(0, posicaoEspaco);
//             String parte2 = str.substring(posicaoEspaco + 1);

//             System.out.println("Primeira parte: " + parte1);
//             System.out.println("Segunda parte: " + parte2);
//         } 
//         else 
//         {
//             System.out.println("Nao existe espaco na string");
//         }

//         sc.close();
//     }
// }

//--------------------------------------------------------------------------------------

// import java.util.Scanner;

// public class Main 
// {
//     public static void main(String[] args) 
//     {

//         Scanner sc = new Scanner(System.in);

//         System.out.print("Digite duas palavras: ");
//         String str = sc.nextLine();

//         String parte1 = "";
//         String parte2 = "";

//         int i = 0;

//         // Monta a primeira parte ate encontrar o espaço
//         while (i < str.length() && str.charAt(i) != ' ') 
//         {
//             parte1 += str.charAt(i);
//             i++;
//         }

//         i++; // pula o espaco

//         // Monta a segunda parte ate o final
//         while (i < str.length()) 
//         {
//             parte2 += str.charAt(i);
//             i++;
//         }

//         System.out.println("Primeira parte: " + parte1);
//         System.out.println("Segunda parte: " + parte2);

//         sc.close();
//     }
// }