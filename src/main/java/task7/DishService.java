package task7;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DishService {

    public static void addDish(String name, double price, double weight, boolean discount) {
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
    }

    public static void viewAllDishes() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            List<Dish> dishes = em.createQuery("SELECT d FROM Dish d", Dish.class).getResultList();
            dishes.forEach(System.out::println);
        } finally {
            EntityManagerUtil.closeEntityManager(em);
        }
    }

    public static void viewDishesByPriceRange(double minPrice, double maxPrice) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            List<Dish> priceRangeDishes = em.createQuery("SELECT d FROM Dish d WHERE d.price BETWEEN :minPrice AND :maxPrice", Dish.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
            priceRangeDishes.forEach(System.out::println);
        } finally {
            EntityManagerUtil.closeEntityManager(em);
        }
    }

    public static void viewDishesWithDiscount() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            List<Dish> discountDishes = em.createQuery("SELECT d FROM Dish d WHERE d.discount = true", Dish.class)
                    .getResultList();
            discountDishes.forEach(System.out::println);
        } finally {
            EntityManagerUtil.closeEntityManager(em);
        }
    }

    public static void viewDishesByWeight(double maxWeight) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            List<Dish> weightDishes = em.createQuery("SELECT d FROM Dish d WHERE d.weight <= :maxWeight", Dish.class)
                    .setParameter("maxWeight", maxWeight)
                    .getResultList();
            weightDishes.forEach(System.out::println);
        } finally {
            EntityManagerUtil.closeEntityManager(em);
        }
    }
}
