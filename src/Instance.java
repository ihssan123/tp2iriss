import java.util.ArrayList;

public class Instance {
    private ArrayList<Double> attributs = new ArrayList<Double>();

    public Instance() {

    }

    public void addData(double data){
        attributs.add(data);
    }
    public Instance(ArrayList<Double> attributs, String valeurClasse) {
        this.attributs = attributs;
        this.valeurClasse = valeurClasse;
    }

    public ArrayList<Double> getAttributs() {
        return attributs;
    }

    public void setAttributs(ArrayList<Double> attributs) {
        this.attributs = attributs;
    }

    public String getValeurClasse() {
        return valeurClasse;
    }

    public void setValeurClasse(String valeurClasse) {
        this.valeurClasse = valeurClasse;
    }

    private String valeurClasse;
    public  instanceKNN CalculDistance(Instance realInstance, Instance PredictedInstance) {
        int dimension = realInstance.getAttributs().size();
        ArrayList<Double> realInstanceAttributs= realInstance.getAttributs();
        double result;

        ArrayList<Double> predictedInstanceAttributs=PredictedInstance.getAttributs();
        double distance = 0;

        for (int i = 0; i < dimension; i++) {

            distance += Math.pow(realInstanceAttributs.get(i) - predictedInstanceAttributs.get(i), 2);
        }


        result=Math.sqrt(distance);

        if(result == 0) {
            System.out.println();
        }
        
        return new instanceKNN(result, realInstance.getValeurClasse());
    }
    public static double  TauxErreur(int[][] ConfusionMatrix) {

        var sumMatrix = getSumMatrix(ConfusionMatrix);


        var sumNotInDiag = getSumNotInDiag(ConfusionMatrix);

        //System.out.println(sumNotInDiag);

        return sumNotInDiag/sumMatrix;
        }

    private static double getSumNotInDiag(int[][] ConfusionMatrix) {
        int somme=0;
        for (int k = 0; k < ConfusionMatrix.length; k++) {

                // iterate through each column
                for (int j = 0; j < ConfusionMatrix[k].length; j++) {
                    if (k != j) somme += ConfusionMatrix[k][j];
                }


            }
        return somme;
    }

    private static double getSumMatrix(int[][] ConfusionMatrix) {
        int som=0;
        for (int i = 0; i < ConfusionMatrix.length; i++) {
            // iterate through each column
            for (int j = 0; j < ConfusionMatrix[i].length; j++) {
                som += ConfusionMatrix[i][j];

            }}
        return som;
    }

    public static double Accuracy(int[][] ConfusionMatrix,double sizeTest){
        double som=0;
            for (int i = 0; i < ConfusionMatrix.length; i++) {
                // iterate through each column
                for (int j = 0; j < ConfusionMatrix[i].length; j++) {
                    if (i==j)
                    som += ConfusionMatrix[i][j];

                }}
            return som/sizeTest;

        }
    public double SommeLigne(int[][] ConfusionMatrix,int colonneIndex){
       double som=0;
        for(int i=0;i<ConfusionMatrix.length;i++){
            som+=ConfusionMatrix[i][colonneIndex];
        }
        return som;
    }
    public double SommeColonne(int[][] ConfusionMatrix,int ligneIndex){
        double som=0;
        for(int i=0;i<ConfusionMatrix.length;i++){
            som+=ConfusionMatrix[ligneIndex][i];
        }
        return som;
    }

    public double Precision(int i,int[][] ConfusionMatrix){
        return ConfusionMatrix[i][i]/SommeLigne(ConfusionMatrix,i);

    }
    public double Rappel(int i,int[][] ConfusionMatrix){
        return ConfusionMatrix[i][i]/SommeColonne(ConfusionMatrix,i);

    }
    public double Fmesure(int i,int[][] ConfusionMatrix){
        return (2*(Rappel(i,ConfusionMatrix)*Precision(i,ConfusionMatrix)))/(Rappel(i,ConfusionMatrix)+Precision(i,ConfusionMatrix));

    }

    }

