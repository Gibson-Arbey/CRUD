package util;



import Dao.UserDao;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Prueba {
	
	public static void main(String[] args) {
		
		
		UserDao user = new UserDao();
		User u = new User();
		u = user.find(29);
		u.setEmail("123456890@gmail.com");
		
		
		user.update(u);
		
		/* Buscar un usuario
		u = em.find(User.class, u.getId());
		System.out.println(u.toString());
		*/
		
		/* 
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		*/
		
		/*Eliminar
		u = em.find(User.class, u.getId());
		try {
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		*/
		
		/* Actualizar
		try {
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}*/
		
	}
}
