package task7;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            String command = sc.nextLine();

            switch (command) {
                case "1":
                    addDish(sc);
                    break;
                case "2":
                    DishService.viewAllDishes();
                    break;
                case "3":
                    viewDishesByPriceRange(sc);
                    break;
                case "4":
                    DishService.viewDishesWithDiscount();
                    break;
                case "5":
                    viewDishesByWeight(sc);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Enter command: ");
        System.out.print("1 - Add dish, 2 - View all dishes, 3 - View dishes by price range, 4 - View dishes with discount, 5 - View dishes by weight, 0 - Exit: ");
    }

    private static void addDish(Scanner sc) {
        System.out.print("Enter dish name: ");
        String name = sc.nextLine();
        System.out.print("Enter dish price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Enter dish weight: ");
        double weight = Double.parseDouble(sc.nextLine());
        System.out.print("Is there a discount? (true/false): ");
        boolean discount = Boolean.parseBoolean(sc.nextLine());

        DishService.addDish(name, price, weight, discount);
    }

    private static void viewDishesByPriceRange(Scanner sc) {
        System.out.print("Enter min price: ");
        double minPrice = Double.parseDouble(sc.nextLine());
        System.out.print("Enter max price: ");
        double maxPrice = Double.parseDouble(sc.nextLine());

        DishService.viewDishesByPriceRange(minPrice, maxPrice);
    }

    private static void viewDishesByWeight(Scanner sc) {
        System.out.print("Enter max weight (kg): ");
        double maxWeight = Double.parseDouble(sc.nextLine());

        DishService.viewDishesByWeight(maxWeight);
    }
}
