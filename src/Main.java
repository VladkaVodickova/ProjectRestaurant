import com.engeto.restaurant.Menu;
import com.engeto.restaurant.Order;
import com.engeto.restaurant.Recipe;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Recipe kofolaVelka = new Recipe("Kofola velká", new BigDecimal(30),1,new ArrayList<>() );
            Recipe pizza = new Recipe("Pizza Grande", new BigDecimal(130),1,new ArrayList<>() );
            Recipe nanuk = new Recipe("Nanuk Míša", new BigDecimal(30),1,new ArrayList<>() );

            List<Recipe> recipeList = new ArrayList<>();
            recipeList.add(kofolaVelka);
            recipeList.add(pizza);
            recipeList.add(nanuk);

            Menu menu = new Menu(recipeList);


        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(System.err);
        }


    }
}
