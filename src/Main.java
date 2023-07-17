import com.engeto.restaurant.Menu;
import com.engeto.restaurant.Order;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.engeto.restaurant.Menu.getMenuDescription;

public class Main {
    public static void main(String[] args) {
        try {
            /*Menu kofolaVelka = new Menu("Kofola velká", 1, new BigDecimal("30.00"));
            Menu pizza = new Menu("Pizza Grande", 1, new BigDecimal("130.00"));
            Menu nanuk = new Menu("Nanuk Míša", 1, new BigDecimal("30.00"));

            List<Menu> menuList = new ArrayList<>();
            menuList.add(kofolaVelka);
            menuList.add(pizza);
            menuList.add(nanuk);

            getMenuDescription(menuList);*/



        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(System.err);
        }


    }
}
