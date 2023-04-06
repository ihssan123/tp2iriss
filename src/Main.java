import java.lang.constant.ClassDesc;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static ArrayList<Instance> flattenArrayList(ArrayList<ArrayList<Instance>> nestedList) {
        return nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public static void main(String[] args) {
        String[] Classes = {"Iris-virginica", "Iris-setosa", "Iris-versicolor"};


       Instance NouveauInstance=new Instance();
        int[][] ConfusionMatrix = {{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};



        FichierVersListeInstances a= new FichierVersListeInstances();
        var allList = a.File2list();
        //ArrayList<Instance> firstFive = new ArrayList<>(t.subList(0, 2));
        Collections.shuffle(allList);
       // ArrayList<Instance>  testingList=new ArrayList<>();
        //ArrayList<Instance>  trainingList=new ArrayList<>();

        ArrayList<Instance> list1 = new ArrayList<>();
        ArrayList<Instance> list2 = new ArrayList<>();
        ArrayList<Instance> list3 = new ArrayList<>();
        ArrayList<Instance> list4 = new ArrayList<>();
        int quarterSize = (int) Math.ceil(allList.size() / 4.0);


        list1.addAll(allList.subList(0, quarterSize));
        list2.addAll(allList.subList(quarterSize, quarterSize * 2));
        list3.addAll(allList.subList(quarterSize * 2, quarterSize * 3));
        list4.addAll(allList.subList(quarterSize * 3, allList.size()));





         //Print the two lists

        //arraylist of instanceKNN from traningList
        ArrayList<instanceKNN> tables;
        instanceKNN instanceknn;
        List<instanceKNN> results;
        int n=0;
        List<ArrayList<Instance>> lists = new ArrayList<ArrayList<Instance>>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4) ;
        var testSet = new ArrayList<Instance>();
        var trainSet = new ArrayList<Instance>();
        for (int i=0;i<lists.size();i++) {
            var otherlists = new ArrayList<ArrayList<Instance>>();
            testSet = lists.get(i);
            for (int j = 0; j < lists.size(); j++) {
                if (j != i) otherlists.add(lists.get(j));
            }
            trainSet = flattenArrayList(otherlists);


            for (Instance instance : testSet) {

                System.out.println(n++);
                System.out.println("*****************************");
                //ArrayList<Instance> trainingList = new ArrayList<>();
                tables = new ArrayList<instanceKNN>();
                for (Instance e : trainSet) {
                    instanceknn = e.CalculDistance(e, instance);
                    tables.add(instanceknn);
                }


                results = instanceKNN.trierInstance(tables, 3);


                var occurence = new HashMap<String, Integer>();
                for (instanceKNN m : results) {
                    if (occurence.containsKey(m.getClassName())) {
                        var oldValue = occurence.get(m.getClassName());
                        var newValue = oldValue + 1;
                        occurence.put(m.getClassName(), newValue);
                    } else occurence.put(m.getClassName(), 1);
                }

                String maxKey = occurence.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();
                // System.out.println("the new element's class is: " + maxKey+" the correct class is : "+instance.getValeurClasse());

                if (maxKey.equals(instance.getValeurClasse())) {
                    if (maxKey.equals("Iris-virginica")) ConfusionMatrix[0][0]++;

                    if (maxKey.equals("Iris-setosa")) ConfusionMatrix[1][1]++;
                    if (maxKey.equals("Iris-versicolor")) ConfusionMatrix[2][2]++;

                }
                if (instance.getValeurClasse().equals("Iris-virginica") && maxKey.equals("Iris-setosa"))
                    ConfusionMatrix[0][1] = ConfusionMatrix[0][1] + 1;
                if (instance.getValeurClasse().equals("Iris-virginica") && maxKey.equals("Iris-versicolor"))
                    ConfusionMatrix[0][2] = ConfusionMatrix[0][2] + 1;
                if (instance.getValeurClasse().equals("Iris-setosa") && maxKey.equals("Iris-virginica"))
                    ConfusionMatrix[1][0] = ConfusionMatrix[1][0] + 1;
                if (instance.getValeurClasse().equals("Iris-setosa") && maxKey.equals("Iris-versicolor"))
                    ConfusionMatrix[1][2] = ConfusionMatrix[1][2] + 1;
                if (instance.getValeurClasse().equals("Iris-versicolor") && maxKey.equals("Iris-virginica"))
                    ConfusionMatrix[2][0] = ConfusionMatrix[2][0] + 1;
                if (instance.getValeurClasse().equals("Iris-versicolor") && maxKey.equals("Iris-setosa"))
                    ConfusionMatrix[2][1] = ConfusionMatrix[2][1] + 1;

                //System.out.println("the new element's class is: " + maxKey);
                // System.out.println("the new element's class is: " + maxKey+" the correct class is : "+instance.getValeurClasse());


                for (int m = 0; m < ConfusionMatrix.length; m++) {
                    // iterate through each column
                    for (int j = 0; j < ConfusionMatrix[m].length; j++) {
                        System.out.print(ConfusionMatrix[m][j] + " ");
                    }
                    System.out.println(); // move to the next line after each row
                }
                System.out.println();
                double tauxErreue = Instance.TauxErreur(ConfusionMatrix);
                System.out.println("Taux d'Ereur est " + tauxErreue);
                double accuracy = Instance.Accuracy(ConfusionMatrix, testSet.size());
                System.out.println("Exactitude est " + accuracy);

                double SomPrecision=0;
                double SomRappel=0;
                double SomFmesure=0;
                for (int j = 0; j < ConfusionMatrix.length; j++) {
                    // iterate through each column

                    double Precision = NouveauInstance.Precision(j, ConfusionMatrix);
                    double Rappel = NouveauInstance.Rappel(j, ConfusionMatrix);
                    double Fmesure = NouveauInstance.Fmesure(j, ConfusionMatrix);

                    //System.out.println("Pour la classe " + Classes[j] + " La precision est " + Precision + " le Rappel est " + Rappel + " Fmesure est " + Fmesure);
                    SomPrecision=Precision+SomPrecision;
                    SomRappel+=Rappel;
                    SomFmesure+=Fmesure;

                }
                System.out.println("Precision est "+SomPrecision+" Rappel est "+SomRappel+" Fmesure est "+SomFmesure);



            }

        }

    }
    }
