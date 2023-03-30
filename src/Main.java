import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] ConfusionMatrix = new int[3][3];
        int Nsetosa=0;
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
        Collections.shuffle(t);
        int splitIndex = (int) (t.size() * 0.96); // Calculate the index to split the list
        List<Instance> list1 = t.subList(0, splitIndex);
        List<Instance> list2 = t.subList(splitIndex, t.size());



        // Print the two lists



     for(Instance instance: list2){
        for(Instance e:t){
           instanceknn=e.CalculDistance(e,nouveau);
           tables.add(instanceknn);
        }

        List<instanceKNN> results;
        results=instanceKNN.trierInstance(tables,3);
         var occurence = new HashMap<String,Integer>();
        for (instanceKNN m:results){
            if (occurence.containsKey(m.getClassName())){
                var oldValue = occurence.get(m.getClassName());
                var newValue = oldValue+1;
                occurence.put(m.getClassName(),newValue);
            }
            else occurence.put(m.getClassName(),1);
        }

        String maxKey = occurence.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();


        //System.out.println("the new element's class is: " + maxKey);
        System.out.println("the new element's class is: " + maxKey+" the correct class is : "+instance.getValeurClasse());





}}
    }
