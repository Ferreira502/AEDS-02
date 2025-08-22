import java.util.*;

class Palindromo
    {
            
        public static int comparaString ( String palavra)
        {
           String str1 = "FIM";
           return palavra.compareTo(str1);
        }
        
        public static boolean ehPalindromo ( String palavra) 
        {
            int tamanho = palavra.length();
            for ( int i = 0; i < tamanho / 2; i ++)
                {
                    if ( palavra.charAt(i) != palavra.charAt(tamanho - 1 - i))
                    {
                        return false;
                    }
                }
            return true; 
        }

        public static void print( )
        {
            Scanner sc = new Scanner(System.in);
            int resultado = 1;
            while ( resultado != 0 )
                {
                    String palavra;
                    // System.out.println("Digite uma palavra: ");
                    palavra = sc.nextLine();
                    resultado = comparaString(palavra);

                    if ( resultado != 0)
                        {
                           if (ehPalindromo(palavra)) 
                           {
                               System.out.println("SIM");
                           } 
                           else 
                           {
                            System.out.println("NAO");
                           }  
                        }
                    
                }
                sc.close();
            }
        
        public static void main ( String [] args )
        {
            print();
        }
    }