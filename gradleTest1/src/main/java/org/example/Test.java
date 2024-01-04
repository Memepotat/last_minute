import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        Filter filter = new Filter();

        String path = System.getProperty("user.dir");
        path = path + "/TEST";

        try {
            boolean[] time = filter.Time(30);
            System.out.println(time[0]);
            System.out.println(time[1]);
            System.out.println(time[2]);
            System.out.println(time[3]);
            System.out.println(time[4]);
        } catch (IOException e) {
        }

        try {
            filter.Save(
                    "C:/Programming/Java/Projects/Uni/3rd/Projects/Last Minute/last_minute/recipes/recipe_1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
