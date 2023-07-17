package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataManagement {
    private Order order;
    private List<Order> orderList;
    private Recipe recipe;
    private List<Recipe> recipeList;
    private Table table;
    private Menu menu;

    public void saveOrder () throws IOException {
        String filename = "orders.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(order.getTable().getTableNumber() + "\t" + order.getRecipe().getItemName()
                    + "\t" + order.getRecipe().getItemPrice() + "\t" + order.getQuantityOfItems()
                    + "\t" + order.getOrderedTime() + "\t" + order.getNameOfWaiter() + "\t" + order.getFulfilmentTime() + "\t");
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public void loadOrders(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Menu menu = loadMenu();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");

                Table table = new Table(Integer.parseInt(parts[0]));
                Recipe recipe = new Recipe((parts[1]), new BigDecimal(parts[2]));
                int quantityOfItems = Integer.parseInt(parts[3]);
                LocalTime orderedTime = LocalTime.parse(parts[4]);
                String nameOfWaiter = parts[5];
                LocalTime  fulfilmentTime = LocalTime .parse(parts[6]);

                Order order = new Order(table, menu, recipe, quantityOfItems, orderedTime, nameOfWaiter, fulfilmentTime);
                orderList.add(order);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        } catch (OrderException e) {
            e.printStackTrace();
        }
    }

    public void saveMenu () throws IOException {
        String filename = "menu.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(menu.getMenuDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public Menu loadMenu() throws IOException {
        String filename = "menu.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");

                String recipeName = parts[0];
                BigDecimal price = new BigDecimal(parts[1]);

                Recipe recipe = new Recipe(recipeName, price);
                recipeList.add(recipe);
                Menu menu = new Menu(recipeList);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filename);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filename, e);
        }
        return null;
    }

    public void setAndSaveTableNote (int tableNumber, String note) throws IOException {
        String filename = "table_notes.txt";
        Set<String> tableNotes = new HashSet<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(table.getTableNumber() + ": " + note);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public String getTableNote (int tableNumber) throws IOException {
        String filename = "table_notes.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(tableNumber + ": ")) {
                    return line.substring(line.indexOf(": ") + 2);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filename, e);
        }
        return "";
    }

    public void saveRecipe () throws IOException {
        String filename = "recipe.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(recipe.getTitle() + "\t" +  recipe.getItemPrice() + "\t" +
                    recipe.getPreparationTime() + "\t" + recipe.getImageURLS());
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public void loadRecipe (String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");

                List<URL> imageURLs = new ArrayList<>();
                if (!parts[3].isEmpty()) {
                    String[] urls = parts[3].split(",");
                    for (String urlString : urls) {
                        try {
                            URI uri = new URI(urlString);
                            URL url = uri.toURL();
                            imageURLs.add(url);
                        } catch (URISyntaxException | MalformedURLException e) {
                            throw new IOException("URISyntaxException | MalformedURLException: " + e);
                        }
                    }

                Recipe recipe = new Recipe(parts[0], new BigDecimal(parts[1]), Integer.parseInt(parts[2]), imageURLs);
                recipeList.add(recipe);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        }
    }

    public void saveRecipeList () throws IOException {
        String filename = "Recipe_list.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(recipe.getDescription() +  "\t");
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }
}
