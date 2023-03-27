import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<instanceKNN> tables= new ArrayList<instanceKNN>();
        instanceKNN instanceknn;
        Instance nouveau=new Instance();
        ArrayList<Double> table = new ArrayList<Double>();
        table.add(3.1);
        table.add(2.7);
        table.add(1.6);
        table.add(5.9);
        nouveau.setAttributs(table);
        FichierVersListeInstances a= new FichierVersListeInstances();
        var t = a.File2list();
        for(Instance e:t){
           instanceknn=e.CalculDistance(e,nouveau);
           tables.add(instanceknn);
        }
        List<instanceKNN> results;
        results=instanceKNN.trierInstance(tables,3);
        for (instanceKNN m:results){
            System.out.println(m.getClassName()+" and "+m.getDistance());

        }



}
    }
