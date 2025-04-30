import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class ComputacionParalela {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de datos del arreglo:");
        int cantidad = leer.nextInt();
        
        // Crear arreglo con valores aleatorios
        int[] arregloOriginal = generarArregloAleatorio(cantidad);
        
        // Mostrar arreglo antes de ordenar
        System.out.println("\nArreglo antes de ordenar:");
        mostrarArreglo(arregloOriginal);
        
        // Ordenación secuencial
        OrdenacionSecuencial sec = new OrdenacionSecuencial(arregloOriginal.clone());
        System.out.println("\nIniciando ordenación secuencial...");
        long inicioSecuencial = System.currentTimeMillis();
        sec.ordenar();
        long finSecuencial = System.currentTimeMillis();
        System.out.println("Arreglo después de ordenación secuencial:");
        mostrarArreglo(sec.getArreglo());
        System.out.println("Tiempo secuencial: " + (finSecuencial - inicioSecuencial) + " ms");
        
        // Ordenación paralela
        System.out.println("\nIngrese la cantidad de hilos que desea crear:");
        int tareas = leer.nextInt();
        
        int[] arregloParalelo = arregloOriginal.clone();
        System.out.println("\nIniciando ordenación paralela...");
        long inicioParalelo = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(tareas);
        OrdenacionParalela par = new OrdenacionParalela(arregloParalelo, 0, arregloParalelo.length - 1);
        pool.invoke(par);
        long finParalelo = System.currentTimeMillis();
        System.out.println("Arreglo después de ordenación paralela:");
        mostrarArreglo(arregloParalelo);
        System.out.println("Tiempo paralelo: " + (finParalelo - inicioParalelo) + " ms");
    }
    
    private static int[] generarArregloAleatorio(int tamaño) {
        Random rand = new Random();
        int[] arreglo = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = rand.nextInt(100); // Números aleatorios entre 0 y 99
        }
        return arreglo;
    }
    
    private static void mostrarArreglo(int[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
        
        // Mostrar índices
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print((i+1) + " ");
        }
        System.out.println();
    }
}