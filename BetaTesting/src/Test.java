import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        Filter filter = new Filter();

        try {

            ArrayList<Integer> Dif = filter.Difficulty(1);
            ArrayList<Integer> Time = filter.Time(15);

            System.out.println("Amount of Recipes for Difficulty and Time: " + Dif.size() + ", " + Time.size());
            System.out.println(Dif.toString());
            System.out.println(Time.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
