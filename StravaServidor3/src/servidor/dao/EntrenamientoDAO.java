package servidor.dao;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import servidor.dominio.Entrenamiento;

public class EntrenamientoDAO extends DataAccessObjectBase implements IDataAccessObject<Entrenamiento> {

		private static EntrenamientoDAO instance;	
		
		private EntrenamientoDAO() { }
		
		public static EntrenamientoDAO getInstance() {
			if (instance == null) {
				instance = new EntrenamientoDAO();
			}		
			
			return instance;
		}	
		
		@Override
		public void store(Entrenamiento object) {
			Entrenamiento storedObject = instance.find(object.getTitulo());

			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			try {
				tx.begin();
				
				if (storedObject != null) {
					em.merge(object);
				} else {
					em.persist(object);
				}
				
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error storing Entrenamiento: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}
		}

		@Override
		public void delete(Entrenamiento object) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			try {
				tx.begin();
				
				Entrenamiento storedObject = (Entrenamiento) em.find(Entrenamiento.class, object.getTitulo());
				em.remove(storedObject);
				
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error removing an Entrenamiento: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Entrenamiento> findAll() {
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			List<Entrenamiento> Entrenamientos = new ArrayList<>();

			try {
				tx.begin();

				Query q = em.createQuery("SELECT u FROM Entrenamiento u");
				Entrenamientos = (List<Entrenamiento>) q.getResultList();
							
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error querying all Entrenamientos: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}

			return Entrenamientos;
		}

		@Override
		public Entrenamiento find(String param) {		
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			Entrenamiento result = null; 

			try {
				tx.begin();

				result = (Entrenamiento) em.find(Entrenamiento.class, param);
				
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error querying a Entrenamiento by email: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}

			return result;
		}
}
