import practicas_pca.TesterPracticas;
public class EjemploDeUso {
    public static void main(String[] args) {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSort());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_25000000, 10);
    }
    public static void EvaluacionGlobal(){
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSort());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_2500000,6);
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_5000000,6);
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_12500000,6);
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_25000000,6);
    }
}