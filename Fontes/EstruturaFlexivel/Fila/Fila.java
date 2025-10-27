/**
 * Fila dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */

/**
 * Celula (pilha, lista e fila dinamica)
 */
class Celula {
   public int elemento; // Elemento inserido na celula.
   public Celula prox;  // Aponta a celula prox.

   /**
    * Construtor da classe.
    */
   public Celula() {
      this(0);
   }

   /**
    * Construtor da classe.
    * @param elemento int inserido na celula.
    */
   public Celula(int elemento) {
      this.elemento = elemento;
      this.prox = null;
   }
}

/**
 * Implementação de uma fila dinâmica.
 */
public class Fila {
   private Celula primeiro;
   private Celula ultimo;

   /**
    * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
    */
   public Fila() {
      primeiro = new Celula();
      ultimo = primeiro;
   }

   /**
    * Insere elemento na fila (politica FIFO).
    * @param x int elemento a inserir.
    */
   public void inserir(int x) {
      ultimo.prox = new Celula(x);
      ultimo = ultimo.prox;
   }

   /**
    * Remove elemento da fila (politica FIFO).
    * @return Elemento removido.
    * @throws Exception Se a fila nao tiver elementos.
    */
   public int remover() throws Exception {
      if (primeiro == ultimo) {
         throw new Exception("Erro ao remover! Fila vazia.");
      }

      Celula tmp = primeiro;
      primeiro = primeiro.prox;
      int resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;
      return resp;
   }

   /**
    * Mostra os elementos separados por espacos.
    */
   public void mostrar() {
      System.out.print("[ ");
      for (Celula i = primeiro.prox; i != null; i = i.prox) {
         System.out.print(i.elemento + " ");
      }
      System.out.println("]");
   }
}

/**
 * Classe principal para testar a Fila Dinâmica.
 */
class PrincipalFila {
   public static void main(String[] args) throws Exception {
      System.out.println("==== FILA FLEXIVEL ====");
      Fila fila = new Fila();
      int x1, x2, x3;

      fila.inserir(5);
      fila.inserir(7);
      fila.inserir(8);
      fila.inserir(9);
      System.out.println("Apos insercoes(5, 7, 8, 9): ");
      fila.mostrar();

      x1 = fila.remover();
      x2 = fila.remover();
      System.out.println("Apos remocoes (" + x1 + ", " + x2 + "):");
      fila.mostrar();

      fila.inserir(3);
      fila.inserir(4);
      System.out.println("Apos insercoes(3, 4): ");
      fila.mostrar();

      x1 = fila.remover();
      x2 = fila.remover();
      x3 = fila.remover();
      System.out.println("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + "):");
      fila.mostrar();

      fila.inserir(4);
      fila.inserir(5);
      System.out.println("Apos insercoes(4, 5): ");
      fila.mostrar();

      x1 = fila.remover();
      x2 = fila.remover();
      System.out.println("Apos remocoes (" + x1 + ", " + x2 + "):");
      fila.mostrar();

      fila.inserir(6);
      fila.inserir(7);
      System.out.println("Apos insercoes(6, 7): ");
      fila.mostrar();

      x1 = fila.remover();
      System.out.println("Apos remocao (" + x1 + "): ");
      fila.mostrar();
   }
}