import java.util.*;

public class instanceKNN {
    private double distance;
    private String ClassName;


    public instanceKNN(double distance, String className) {
        this.distance = distance;
        ClassName = className;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }
    public static List<instanceKNN> trierInstance(ArrayList<instanceKNN> c, int k){
        Collections.sort(c, Comparator.comparingDouble(instanceKNN::getDistance));
        if(k > c.size()) k= c.size();
        return c.subList(0,k);



    }
}
