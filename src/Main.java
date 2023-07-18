import com.engeto.restaurant.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            //ukol1
            task1();

            //ukol2
            task2();

            //ukol3
            //task3();

            //ukol4
            task4();

            //ukol5
            task5();

            //ukol6
            //task6();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    private static void task1() throws IOException {
        DataManagement dataManagement = new DataManagement();
        dataManagement.loadOrders();
        dataManagement.loadMenu();
        dataManagement.loadRecipe();
    }

    private static void task2() throws OrderException, IOException {
        Recipe recipe1 = new Recipe("Kuřecí řízek obalovaný 150g", BigDecimal.valueOf(150), 15, new ArrayList<>());
        Recipe recipe2 = new Recipe("Hranolky 150 g", BigDecimal.valueOf(50), 5, new ArrayList<>());
        Recipe recipe3 = new Recipe("Pstruh na víně 200 g", BigDecimal.valueOf(200), 25, new ArrayList<>());
        Recipe recipe4 = new Recipe("Pivo", BigDecimal.valueOf(20), 1, new ArrayList<>());

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        recipeList.add(recipe4);

        List<Recipe> menuList = new ArrayList<>();
        menuList.add(recipe1);
        menuList.add(recipe3);
        menuList.add(recipe4);

        Table table2 = new Table(2);
        Table table15 = new Table(15);

        Menu menu = new Menu(menuList);

        Order order1 = new Order(table15, menu, recipe1, 2, LocalTime.now(), "č. 1", LocalTime.now().plusMinutes(3));
        Order order2 = new Order(table15, menu, recipe3, 2, LocalTime.now(), "č. 2");
        Order order3 = new Order(table15, menu, recipe4, 4, LocalTime.now(), "č. 3", LocalTime.now().plusMinutes(3));
        Order order4 = new Order(table15, menu, recipe4, 2, LocalTime.now(), "č. 2");
        Order order5 = new Order(table2, menu, recipe4, 2, LocalTime.now(), "č. 1");

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        DataManagement dataManagement = new DataManagement();
        dataManagement.saveOrder(orderList);
        dataManagement.saveMenu(menu);
        dataManagement.saveRecipe(recipeList);

    }
    private static void task3() throws OrderException{
        Recipe recipe1 = new Recipe("Kuřecí řízek obalovaný 150g", BigDecimal.valueOf(150),15, new ArrayList<>());
        Recipe recipe2 = new Recipe("Hranolky 150 g", BigDecimal.valueOf(50),5, new ArrayList<>());

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);

        List<Recipe> menuList = new ArrayList<>();
        menuList.add(recipe1);

        Table table15 = new Table(15);

        Menu menu = new Menu(menuList);

        Order order1 = new Order(table15,menu,recipe2,2, LocalTime.now(),"č. 1");
    }
    private static void task4() throws OrderException {
        Recipe recipe1 = new Recipe("Kuřecí řízek obalovaný 150g", BigDecimal.valueOf(150), 15, new ArrayList<>());
        Recipe recipe2 = new Recipe("Hranolky 150 g", BigDecimal.valueOf(50), 5, new ArrayList<>());
        Recipe recipe3 = new Recipe("Pstruh na víně 200 g", BigDecimal.valueOf(200), 25, new ArrayList<>());

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);

        List<Recipe> menuList = new ArrayList<>();
        menuList.add(recipe1);
        menuList.add(recipe3);

        Table table15 = new Table(15);

        Menu menu = new Menu(menuList);

        Order order1 = new Order(table15, menu, recipe1, 2, LocalTime.now(), "č. 1", LocalTime.now().plusMinutes(3));
        Order order2 = new Order(table15, menu, recipe3, 2, LocalTime.now(), "č. 2");
        order2.setFulfilmentTime(LocalTime.now().plusMinutes(20));
    }
    private static void task5() throws OrderException {
        Recipe recipe1 = new Recipe("Kuřecí řízek obalovaný 150g", BigDecimal.valueOf(150), 15, new ArrayList<>());
        Recipe recipe2 = new Recipe("Hranolky 150 g", BigDecimal.valueOf(50), 5, new ArrayList<>());
        Recipe recipe3 = new Recipe("Pstruh na víně 200 g", BigDecimal.valueOf(200), 25, new ArrayList<>());
        Recipe recipe4 = new Recipe("Pivo", BigDecimal.valueOf(20), 1, new ArrayList<>());

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        recipeList.add(recipe4);

        List<Recipe> menuList = new ArrayList<>();
        menuList.add(recipe1);
        menuList.add(recipe3);
        menuList.add(recipe4);

        Table table2 = new Table(2);
        Table table15 = new Table(15);

        Menu menu = new Menu(menuList);

        Order order1 = new Order(table15, menu, recipe1, 2, LocalTime.now(), "č. 1", LocalTime.now().plusMinutes(3));
        Order order2 = new Order(table15, menu, recipe3, 2, LocalTime.now(), "č. 2");
        Order order3 = new Order(table15, menu, recipe4, 4, LocalTime.now(), "č. 3", LocalTime.now().plusMinutes(3));
        Order order4 = new Order(table15, menu, recipe4, 2, LocalTime.now(), "č. 2");
        Order order5 = new Order(table2, menu, recipe4, 2, LocalTime.now(), "č. 1");

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        RestaurantManager restaurantManager = new RestaurantManager(orderList);
    //1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
        System.out.println("1. " + restaurantManager.getNumberOfUnfinishedOrders());
    //!!!!2. Možnost seřadit objednávky podle číšníka nebo času zadání. - nutné dát description
        System.out.println("2.a. " + restaurantManager.sortByWaiter());
        System.out.println("2.b. " + restaurantManager.sortByOrderTime());
    //3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
        System.out.println("3. " + restaurantManager.calculateTotalPricePerWaiter(orderList));
    //!!!!4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období. - hází 0 i když by to mělo být 3
        System.out.println("4. " + restaurantManager.calculateAverageProcessingTime(LocalDate.now(),LocalDate.now().plusDays(1)));
    //!!!!5. Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány. - musí se ty jídla vypisovat jednotlivě
        System.out.println("5. " + restaurantManager.getListOfTodayOrderedMeals(orderList));
    //6. Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku):
        System.out.println("6. " + restaurantManager.getOrdersForTable(table15,orderList));
        //Číslo stolu na začátku je dvojmístné, pro stoly 1-9 se před číslo umístí mezera.

    }
    private static void task6(){

    }
}
