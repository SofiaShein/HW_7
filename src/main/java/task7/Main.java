package task7;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: ");
            System.out.print("1 - Add dish, 2 - View all dishes, 3 - View dishes by price range, 4 - View dishes with discount, 5 - View dishes by weight, 0 - Exit: ");
            String command = sc.nextLine();

            switch (command) {
                case "1":
                    // Добавление нового блюда
                    System.out.print("Enter dish name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter dish price: ");
                    double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Enter dish weight: ");
                    double weight = Double.parseDouble(sc.nextLine());
                    System.out.print("Is there a discount? (true/false): ");
                    boolean discount = Boolean.parseBoolean(sc.nextLine());

                    EntityManager em = EntityManagerUtil.getEntityManager();
                    try {
                        EntityTransaction transaction = em.getTransaction();
                        transaction.begin();
                        Dish newDish = new Dish(name, price, weight, discount);
                        em.persist(newDish);
                        transaction.commit();
                        System.out.println("Dish added successfully.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        EntityManagerUtil.closeEntityManager(em);
                    }
                    break;

                case "2":
                    // Просмотр всех блюд
                    em = EntityManagerUtil.getEntityManager();
                    try {
                        List<Dish> dishes = em.createQuery("SELECT d FROM Dish d", Dish.class).getResultList();
                        for (Dish dish : dishes) {
                            System.out.println(dish);
                        }
                    } finally {
                        EntityManagerUtil.closeEntityManager(em);
                    }
                    break;

                case "3":
                    // Просмотр блюд по цене
                    System.out.print("Enter min price: ");
                    double minPrice = Double.parseDouble(sc.nextLine());
                    System.out.print("Enter max price: ");
                    double maxPrice = Double.parseDouble(sc.nextLine());

                    em = EntityManagerUtil.getEntityManager();
                    try {
                        List<Dish> priceRangeDishes = em.createQuery("SELECT d FROM Dish d WHERE d.price BETWEEN :minPrice AND :maxPrice", Dish.class)
                                .setParameter("minPrice", minPrice)
                                .setParameter("maxPrice", maxPrice)
                                .getResultList();
                        for (Dish dish : priceRangeDishes) {
                            System.out.println(dish);
                        }
                    } finally {
                        EntityManagerUtil.closeEntityManager(em);
                    }
                    break;

                case "4":
                    // Просмотр блюд со скидкой
                    em = EntityManagerUtil.getEntityManager();
                    try {
                        List<Dish> discountDishes = em.createQuery("SELECT d FROM Dish d WHERE d.discount = true", Dish.class)
                                .getResultList();
                        for (Dish dish : discountDishes) {
                            System.out.println(dish);
                        }
                    } finally {
                        EntityManagerUtil.closeEntityManager(em);
                    }
                    break;

                case "5":
                    // Просмотр блюд по весу
                    System.out.print("Enter max weight (kg): ");
                    double maxWeight = Double.parseDouble(sc.nextLine());

                    em = EntityManagerUtil.getEntityManager();
                    try {
                        List<Dish> weightDishes = em.createQuery("SELECT d FROM Dish d WHERE d.weight <= :maxWeight", Dish.class)
                                .setParameter("maxWeight", maxWeight)
                                .getResultList();
                        for (Dish dish : weightDishes) {
                            System.out.println(dish);
                        }
                    } finally {
                        EntityManagerUtil.closeEntityManager(em);
                    }
                    break;

                case "0":
                    // Выход
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
