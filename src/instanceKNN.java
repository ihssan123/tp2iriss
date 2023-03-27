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
    public static List<instanceKNN> trierInstance(ArrayList<instanceKNN> instanceKNNS, int k){
        Comparator<instanceKNN> distanceComparator = new Comparator<instanceKNN>() {
            public int compare(instanceKNN obj1, instanceKNN obj2) {
                return Double.compare(obj1.distance, obj2.distance);
            }
        };
        Collections.sort(instanceKNNS, distanceComparator);
        if(k > instanceKNNS.size()) k= instanceKNNS.size();
        return instanceKNNS.subList(0,k);



    }
}
