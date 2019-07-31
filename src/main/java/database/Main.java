package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(new Product("Ryż", 5.0));
        entityManager.persist(new Product("Mleko", 3.0));
        entityManager.persist(new Product("Makaron", 3.0));
        entityManager.persist(new Product("Kakao", 8));

        System.out.println("Enter selection: id, name, price: ");
        String userSelection = scan.nextLine();

        Query query = entityManager.createQuery("select p from Product p order by " + userSelection);


        for (Object reult : query.getResultList()) {
            Product product = (Product) reult;
            System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice());

        }
        entityManager.getTransaction().commit();


        entityManager.close();
        factory.close();


    }
}
