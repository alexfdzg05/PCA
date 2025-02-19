// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import practicas_pca.TesterRun;

import java.util.*;

public class AlgoritmoBucketSort implements TesterRun {
    /*
    Cada bucket va asociado a cada uno de los fichero txt, es por eso que se pasa una lista de
    números.

    Cada thread debe ordenar utilizando el collections.sort una sección de cada bucket

    Thread --> Bucket --> Números
                    Parcial
    "--> == Conjunto de"



     */
    //@Override
    public ArrayList<Integer> bucketSort2(List<Integer> numbers, int num_threads) {
        ArrayList<Integer> sol = new ArrayList<>();

        // Creamos los hilos
        ArrayList<Hilo> threads = new ArrayList<>(num_threads);
        for (int i = 0; i < num_threads; i++) {
            threads.add(new Hilo(null));
        }

        // Buscamos el valor máximo para calcular los rangos
        int max = Collections.max(numbers);
        int r = (max / num_threads) + 1; // Se suma 1 para evitar divisiones por 0 cuando max < num_threads

        // Inicializamos los buckets asegurando que haya `num_threads` listas disponibles
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < num_threads; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribuimos los números en los buckets
        for (int n : numbers) {
            int index = n / r;
            if (index >= num_threads) { // Corrección: aseguramos que no acceda fuera de los límites
                index = num_threads - 1;
            }
            if (index < 0) { // Corrección: aseguramos que index nunca sea negativo
                index = 0;
            }
            buckets.get(index).add(n);
        }

        // Ejecutamos "run" directamente en cada "hilo"
        int i = 0;
        for (Hilo hilo : threads) {
            hilo.run(buckets.get(i)); // Se ejecuta en el mismo hilo principal
            i++;
        }

        // Recogemos los resultados ordenados
        for (List<Integer> bucket : buckets) {
            sol.addAll(bucket);
        }

        return sol;
    }
    @Override
    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads) { //Tengo que preprocesar los datos
        ArrayList<Integer> sol = new ArrayList<>();
        // Creamos los hilos
        ArrayList<Hilo> threads = new ArrayList<>(num_threads);

        //Inicializamos variables para dividir la lista de números
        int size = numbers.size();
        int k = size / num_threads; //Valor a añadir en cada iteración (incrementaremos n y m en un valor de k)
        int n = k; // Índice Final
        int m = 0; // Índice Inicial
        // Nos declaramos un contador para saber si es la última ejecución
        //Cada hilo se encargará de una porción del fichero de datos
        for (int i = 0; i < num_threads; i++) {
            if (i == num_threads -1){ // Caso en el que la lista sea impar al momento de dividir
                n = numbers.size();
            }
            List<Integer> splitedBucket = new ArrayList<Integer>(numbers.subList(m,n)); // Al trabajar con sublist a secas los hilos colisionaban
            m = n;
            n += k;
            threads.add(new Hilo(splitedBucket));
            Hilo hilo = threads.get(i);
            hilo.start();
            sol.addAll(splitedBucket);
            i++;
        }
        Collections.sort(sol);
        return sol;
    }
}
