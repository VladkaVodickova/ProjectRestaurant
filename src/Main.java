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
            System.out.println("ukol9");
            DataManagement dataManagement = new DataManagement();
            dataManagement.loadRecipe();

            System.out.println("ukol1");
            task1();

            System.out.println("ukol2");
            task2();

            System.out.println("ukol3");
            //task3();

            System.out.println("ukol4");
            task4();

            System.out.println("ukol5");
            task5();

            System.out.println("ukol6");
            task6();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    private static void task1() throws IOException {
        DataManagement dataManagement = new DataManagement();
        dataManagement.loadRecipe();
        dataManagement.loadMenu();
        dataManagement.loadOrders();
    }

    private static void task2() throws OrderException {
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

        Order order1 = new Order(table15, menu, recipe1, 2, LocalTime.now(), "n.1", LocalTime.now().plusMinutes(3));
        Order order2 = new Order(table15, menu, recipe3, 2, LocalTime.now().plusMinutes(1), "n.2");
        Order order3 = new Order(table15, menu, recipe4, 4, LocalTime.now().plusMinutes(2), "n.3", LocalTime.now().plusMinutes(3));
        Order order4 = new Order(table15, menu, recipe4, 2, LocalTime.now().plusMinutes(4), "n.2");
        Order order5 = new Order(table2, menu, recipe4, 2, LocalTime.now().plusMinutes(10), "n.1");

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        RestaurantManager restaurantManager = new RestaurantManager(orderList);
    //1.
        System.out.println("1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.\n" + restaurantManager.getNumberOfUnfinishedOrders()+ "\n");
    //2.
        System.out.println("2.a. Možnost seřadit objednávky podle číšníka nebo času zadání." + "\n"
                + restaurantManager.getDescriptionOfList(restaurantManager.sortByWaiter()));
        System.out.println("2.b. Možnost seřadit objednávky podle číšníka nebo času zadání." + "\n"
                + restaurantManager.getDescriptionOfList(restaurantManager.sortByOrderTime()));
    //3.
        System.out.println("3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).\n"
                + restaurantManager.calculateTotalPricePerWaiter(orderList) + "\n");
    //4.
        System.out.println("4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.\n"
                + restaurantManager.calculateAverageProcessingTime(LocalDate.now(),LocalDate.now().plusDays(1)) + "\n");
    //5.
        System.out.println("5.  Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.\n"
                + restaurantManager.getListOfTodayOrderedMeals(orderList) + "\n");
    //6.
        System.out.println("6. Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku):\n"
                + restaurantManager.getOrdersForTable(table15,orderList) + "\n");
    }

    private static void task6() throws OrderException, IOException {
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

        Order order1 = new Order(table15, menu, recipe1, 2, LocalTime.now(), "n.1", LocalTime.now().plusMinutes(3));
        Order order2 = new Order(table15, menu, recipe3, 2, LocalTime.now().plusMinutes(1), "n.2");
        Order order3 = new Order(table15, menu, recipe4, 4, LocalTime.now().plusMinutes(2), "n.3", LocalTime.now().plusMinutes(3));
        Order order4 = new Order(table15, menu, recipe4, 2, LocalTime.now().plusMinutes(4), "n.2");
        Order order5 = new Order(table2, menu, recipe4, 2, LocalTime.now().plusMinutes(10), "n.1");

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        DataManagement dataManagement = new DataManagement();
        dataManagement.saveRecipe(recipeList);
        dataManagement.saveMenu(menuList);
        dataManagement.saveOrder(orderList);
    }
}
