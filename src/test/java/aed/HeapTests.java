package aed;
import Heaps.Heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapTests {
    Heap<Integer> heap;
   public Boolean esHeap(ArrayList<Integer> array) {
      for(int i = 0; i <= (array.size() - 2) / 2; ++i) {
         int left = 2 * i + 1;
         int right = 2 * i + 2;
         if (left < array.size() && (Integer)array.get(i) < (Integer)array.get(left)) {
            return false;
         }

         if (right < array.size() && (Integer)array.get(i) < (Integer)array.get(right)) {
            return false;
         }
      }

      return true;
   }
   @Test
   public void testSimple() {
      heap = new Heap<>();
      int[] valores = new int[]{4, 1, 7, 3};

      for(int i = 0; i < valores.length; ++i) {
         heap.agregar(valores[i]);
      }

      ArrayList<Integer> arrayCorrecto = new ArrayList<>();
      arrayCorrecto.add(7);
      arrayCorrecto.add(3);
      arrayCorrecto.add(4);
      arrayCorrecto.add(1);
      Assertions.assertTrue(this.esHeap(heap.getHeap()));
      Assertions.assertEquals(7, (Integer)heap.extraer());
      Assertions.assertTrue(this.esHeap(heap.getHeap()));
      Assertions.assertEquals(4, (Integer)heap.extraer());
      Assertions.assertTrue(this.esHeap(heap.getHeap()));
      Assertions.assertEquals(3, (Integer)heap.extraer());
      Assertions.assertTrue(this.esHeap(heap.getHeap()));
      Assertions.assertEquals(1, (Integer)heap.extraer());
      Assertions.assertEquals(0, heap.cardinalidad());
      Assertions.assertTrue(heap.esVacio());
   }
   @Test
    public void testIsEmptyInitially() {
        heap = new Heap<>();
        Assertions.assertTrue(heap.esVacio());
    }

    @Test
   public void testDeDuplicados() {
      heap = new Heap<>();
      heap.agregar(5);
      heap.agregar(5);
      heap.agregar(5);
      assertEquals(3, heap.cardinalidad());
      assertEquals(5, heap.extraer());
      assertEquals(5, heap.extraer());
      assertEquals(5, heap.extraer());
   }
}
