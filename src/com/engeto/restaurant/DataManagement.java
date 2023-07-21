package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public void saveOrder (List<Order> orderList) throws IOException {
        String filename = "orders.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : orderList) {
                writer.write(order.getTable().getTableNumberAsInt() + "\t" + order.getRecipe().getItemName() + "\t" +order.getRecipe().getItemPrice()
                        + "\t" + order.getQuantityOfItems()
                        + "\t" + order.getOrderedTime() + "\t" + order.getNameOfWaiter() + "\t" + order.getFulfilmentTime() + "\t");
                writer.newLine();
                }
            System.out.println("Orders were saved.");
            } catch (IOException e) {
                throw new IOException("Error writing to file: " + filename, e);
            }
        }

    public void loadOrders() throws IOException {
        String filename = "orders.txt";
        if(!Files.exists(Path.of(filename))) {
            return;
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSSSS");
        List<Order> orderList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                try {
                    Table table = new Table(Integer.parseInt(parts[0]));
                    Recipe recipe = new Recipe((parts[1]), new BigDecimal(parts[2]));
                    int quantityOfItems = Integer.parseInt(parts[3]);
                    LocalTime orderedTime = LocalTime.parse(parts[4], timeFormatter);
                    String nameOfWaiter = parts[5];
                    LocalTime fulfilmentTime = null;
                    if (!"null".equals(parts[6])) {
                        fulfilmentTime = LocalTime.parse(parts[6], timeFormatter);
                    }

                    Order order = new Order(table, recipe, quantityOfItems, orderedTime, nameOfWaiter, fulfilmentTime);
                    orderList.add(order);
                    System.out.println("Order was loaded " + order + ". ");
                } catch (Exception e){
                    throw new OrderException("Wrong format of file: " + e + ". ");
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filename, e);
        } catch (OrderException e) {
            e.printStackTrace();
        }
    }

    public void saveMenu (List<Recipe> recipeList) throws IOException {
        String filename = "menu.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Recipe recipe : recipeList) {
                writer.write(recipe.getTitle() + "\t" + recipe.getItemPrice() + "\t" +
                        recipe.getPreparationTime() + "\t" + recipe.getImageURLS());
                writer.newLine();
            }
            System.out.println("Menu was saved.");
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public void loadMenu() throws IOException {
        String filename = "menu.txt";
        if (!Files.exists(Path.of(filename))) {
            return;
        }
        List<Recipe> recipeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                try {
                    List<URL> imageURLs = new ArrayList<>();
                    if (!parts[3].isEmpty() && !parts[3].equals("[]")) {
                        String[] urls = parts[3].split(",");
                        for (String urlString : urls) {
                            try {
                                if (!urlString.isEmpty()) {
                                    URI uri = new URI(urlString);
                                    URL url = uri.toURL();
                                    imageURLs.add(url);
                                }
                            } catch (URISyntaxException | MalformedURLException e) {
                                throw new IOException("URISyntaxException | MalformedURLException: " + e);
                            }
                        }
                    }
                    Recipe recipe = new Recipe(parts[0], new BigDecimal(parts[1]), Integer.parseInt(parts[2]), imageURLs);
                } catch (Exception e){
                    throw new OrderException("Wrong format of file: " + e);
                }
                recipeList.add(recipe);
                System.out.println("Recipe was loaded " + recipe.getItemName() + ". ");
            }
        } catch (IOException | OrderException e) {
            throw new IOException("Error reading file: " + filename, e);
        }
    }

    public void setAndSaveTableNote (Table table, String note) throws IOException {
        String filename = "table_notes.txt";
        Set<String> tableNotes = new HashSet<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(table.getTableNumber() + ": " + note);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public String getTableNote (Table table) throws IOException {
        String filename = "table_notes.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(table + ": ")) {
                    return line.substring(line.indexOf(": ") + 2);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filename, e);
        }
        return "";
    }

    public void saveRecipe(List<Recipe> recipeList) throws IOException {
        String filename = "recipe.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Recipe recipe : recipeList) {
                writer.write(recipe.getTitle() + "\t" + recipe.getItemPrice() + "\t" +
                        recipe.getPreparationTime() + "\t" + recipe.getImageURLS());
                writer.newLine();
            }
            System.out.println("Recipe list was saved.");
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public void loadRecipe () throws IOException {
        String filename = "recipe.txt";
        if(!Files.exists(Path.of(filename))) {
            return;
        }
        List<Recipe> recipeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                try {
                    List<URL> imageURLs = new ArrayList<>();
                    if (!parts[3].isEmpty() && !parts[3].equals("[]")) {
                        String[] urls = parts[3].split(",");
                        for (String urlString : urls) {
                            try {
                                if (!urlString.isEmpty()) {
                                    URI uri = new URI(urlString);
                                    URL url = uri.toURL();
                                    imageURLs.add(url);
                                }
                            } catch (URISyntaxException | MalformedURLException e) {
                                throw new IOException("URISyntaxException | MalformedURLException: " + e);
                            }
                        }
                    }
                        Recipe recipe = new Recipe(parts[0], new BigDecimal(parts[1]), Integer.parseInt(parts[2]), imageURLs);
                } catch (Exception e){
                    throw new OrderException("Wrong format of file: " + e);
                }
                recipeList.add(recipe);
                System.out.println("Recipe was loaded " + recipe.getItemName() + ". ");
            }
        } catch (IOException | OrderException e) {
            throw new IOException("Error reading file: " + filename, e);
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
