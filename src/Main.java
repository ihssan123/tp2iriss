import java.lang.constant.ClassDesc;
import java.util.*;

public class Main {
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
        int size1 = (int) Math.round(allList.size() * 0.7);
        int size2 = (int) Math.round(allList.size() * 0.2);
        int size3 = allList.size() - size1 - size2;

        List<Instance> trainingList = allList.subList(0, size1);
        List<Instance> testingList = allList.subList(size1, size1 + size2);
        List<Instance> validationList = allList.subList(size1 + size2, size1 + size2 + size3);



        // Print the two lists

        //arraylist of instanceKNN from traningList
        ArrayList<instanceKNN> tables;
        instanceKNN instanceknn;
        List<instanceKNN> results;
     for(Instance instance: testingList){
         tables = new ArrayList<instanceKNN>();
        for(Instance e: trainingList){
           instanceknn=e.CalculDistance(e,instance);
           tables.add(instanceknn);
        }



         results= instanceKNN.trierInstance(tables,3);


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
         System.out.println("the new element's class is: " + maxKey+" the correct class is : "+instance.getValeurClasse());

         if(maxKey.equals(instance.getValeurClasse())){
            if(maxKey.equals("Iris-virginica")) ConfusionMatrix[0][0]++ ;

              if(maxKey.equals("Iris-setosa")) ConfusionMatrix[1][1]++ ;
              if(maxKey.equals("Iris-versicolor")) ConfusionMatrix[2][2]++ ;

          }
         if( instance.getValeurClasse().equals("Iris-virginica") && maxKey.equals("Iris-setosa")) ConfusionMatrix[0][1]=ConfusionMatrix[0][1]+1;
         if( instance.getValeurClasse().equals("Iris-virginica") && maxKey.equals("Iris-versicolor")) ConfusionMatrix[0][2]=ConfusionMatrix[0][2]+1;
         if( instance.getValeurClasse().equals("Iris-setosa") && maxKey.equals("Iris-virginica")) ConfusionMatrix[1][0]=ConfusionMatrix[1][0]+1;
         if( instance.getValeurClasse().equals("Iris-setosa") && maxKey.equals("Iris-versicolor")) ConfusionMatrix[1][2]=ConfusionMatrix[1][2]+1;
         if( instance.getValeurClasse().equals("Iris-versicolor") && maxKey.equals("Iris-virginica")) ConfusionMatrix[2][0]=ConfusionMatrix[2][0]+1;
         if( instance.getValeurClasse().equals("Iris-versicolor") && maxKey.equals("Iris-setosa")) ConfusionMatrix[2][1]=ConfusionMatrix[2][1]+1;

       //System.out.println("the new element's class is: " + maxKey);
       // System.out.println("the new element's class is: " + maxKey+" the correct class is : "+instance.getValeurClasse());






}


        for (int i = 0; i < ConfusionMatrix.length; i++) {
            // iterate through each column
            for (int j = 0; j < ConfusionMatrix[i].length; j++) {
                System.out.print(ConfusionMatrix[i][j] + " ");
            }
            System.out.println(); // move to the next line after each row
        }
        System.out.println();
        double tauxErreue= Instance.TauxErreur(ConfusionMatrix);
        System.out.println("Taux d'Ereur est "+tauxErreue);
        double accuracy=Instance.Accuracy(ConfusionMatrix,(double)testingList.size());
        System.out.println("Exactitude est "+accuracy);


        for (int i = 0; i < ConfusionMatrix.length; i++) {
            // iterate through each column

                double Precision=NouveauInstance.Precision(i,ConfusionMatrix);
                double Rappel=NouveauInstance.Rappel(i,ConfusionMatrix);
                double Fmesure=NouveauInstance.Fmesure(i,ConfusionMatrix);

            System.out.println("Pour la classe "+Classes[i]+" La precision est "+Precision+" le Rappel est "+Rappel+" Fmesure est "+Fmesure);


        }


    }
    }
