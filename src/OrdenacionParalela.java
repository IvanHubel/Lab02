
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Usuario
 */
public class OrdenacionParalela extends RecursiveAction {
    private int[] arreglo;
    private int low;
    private int high;
    private static final int UMBRAL = 1000; // Tamaño umbral para ejecución secuencial

    public OrdenacionParalela(int[] arreglo, int low, int high) {
        this.arreglo = arreglo;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (high - low < UMBRAL) {
            quicksortSecuencial(low, high);
        } else {
            int pi = particion(low, high);
            OrdenacionParalela left = new OrdenacionParalela(arreglo, low, pi - 1);
            OrdenacionParalela right = new OrdenacionParalela(arreglo, pi + 1, high);
            invokeAll(left, right);
        }
    }
    
    private void quicksortSecuencial(int low, int high) {
        if (low < high) {
            int pi = particion(low, high);
            quicksortSecuencial(low, pi - 1);
            quicksortSecuencial(pi + 1, high);
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