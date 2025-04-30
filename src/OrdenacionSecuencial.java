

/**
 *
 * @author Usuario
 */
public class OrdenacionSecuencial {
    private int[] arreglo;

    public OrdenacionSecuencial(int[] arreglo) {
        this.arreglo = arreglo;
    }

    public int[] getArreglo() {
        return arreglo;
    }
    
    public void ordenar() {
        quicksort(0, arreglo.length - 1);
    }
    
    private void quicksort(int low, int high) {
        if (low < high) {
            int pi = particion(low, high);
            quicksort(low, pi - 1);
            quicksort(pi + 1, high);
        }
    }
    
    private int particion(int low, int high) {
        int pivot = arreglo[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arreglo[j] < pivot) {
                i++;
                intercambiar(i, j);
            }
        }
        
        intercambiar(i + 1, high);
        return i + 1;
    }
    
    private void intercambiar(int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
}