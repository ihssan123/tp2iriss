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
    public  instanceKNN CalculDistance(Instance e1, Instance e2) {
        int dimension1 = e1.getAttributs().size();
        ArrayList<Double> tabE1=e1.getAttributs();
        double result;

        ArrayList<Double> tabE2=e2.getAttributs();
        double distance = 0;

        for (int i = 0; i < dimension1; i++) {

            distance += Math.pow(tabE1.get(i) - tabE2.get(i), 2);
        }
        result=Math.sqrt(distance);
        return new instanceKNN(result,e1.getValeurClasse());
    }

}
