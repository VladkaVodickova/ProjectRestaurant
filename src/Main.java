import com.engeto.restaurant.Menu;
import com.engeto.restaurant.Order;
import com.engeto.restaurant.Recipe;
import com.engeto.restaurant.Table;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Table table1 = new Table(1);
            Table table2 = new Table(2);
            Table table3 = new Table(3);
            Table table4 = new Table(4);

            Recipe kofolaVelka = new Recipe("Kofola velká", new BigDecimal(30),1,new ArrayList<>() );
            Recipe pizza = new Recipe("Pizza Grande", new BigDecimal(130),1,new ArrayList<>() );
            Recipe nanuk = new Recipe("Nanuk Míša", new BigDecimal(30),1,new ArrayList<>() );

            List<Recipe> recipeList = new ArrayList<>();
            recipeList.add(kofolaVelka);
            recipeList.add(pizza);
            recipeList.add(nanuk);

            Menu menu = new Menu(recipeList);

            Order order1 = new Order(table4,menu,pizza,1,LocalTime.now(),"č. 2", LocalTime.now().plusMinutes(3));
            Order order2 = new Order(table4,menu,kofolaVelka,4,LocalTime.now(),"č. 2", LocalTime.now().plusMinutes(3));
            Order order3 = new Order(table4,menu,nanuk,2,LocalTime.now(),"č. 2", LocalTime.now().plusMinutes(3));
            List<Order> orderList = new ArrayList<>();
            orderList.add(order1);
            orderList.add(order2);
            orderList.add(order3);
            table4.setOrderList(orderList);

            System.out.println(table4.getOrderListing());


        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(System.err);
        }


    }
}
