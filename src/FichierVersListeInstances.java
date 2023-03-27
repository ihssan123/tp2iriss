import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class FichierVersListeInstances {
    Instance instante;
    public ArrayList<Instance> File2list() {
        ArrayList<Instance> instances=new ArrayList<Instance>();
        try {
            File file = new File("C:/Users/youss/Desktop/Data mining/TP2/tp2DM/src/iris.arff");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("@") && !line.startsWith("%") && !line.isEmpty() && !line.isBlank()) {
                    String[] parts = line.split(",");
                    Instance instance = new Instance();
                    instance.setValeurClasse(parts[parts.length-1]);
                    for (int i = 0; i < parts.length-1; i++) {
                        instance.addData(Double.parseDouble(parts[i]));
                    }
                  instances.add(instance);

                    }}

// Extract the label
                   /* String label = parts[4];

// Create a new instance with the doubles and label
                    Instance instance = new Instance(doubles, label);
                    // Add code here to write the line to another file
                    instances.add(instance);*
                }*/

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
    }
        return instances;
}}






