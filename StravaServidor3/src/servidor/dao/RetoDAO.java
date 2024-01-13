package servidor.dao;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import servidor.dominio.Reto;

public class RetoDAO extends DataAccessObjectBase implements IDataAccessObject<Reto> {

		private static RetoDAO instance;	
		
		private RetoDAO() { }
		
		public static RetoDAO getInstance() {
			if (instance == null) {
				instance = new RetoDAO();
			}		
			
			return instance;
		}	
		
		@Override
		public void store(Reto object) {
			Reto storedObject = instance.find(object.getNombre());

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
				System.out.println("  $ Error storing Reto: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}
		}

		@Override 
		public void delete(Reto object) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			try {
				tx.begin();
				
				Reto storedObject = (Reto) em.find(Reto.class, object.getNombre());
				em.remove(storedObject);
				
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error removing an Reto: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Reto> findAll() {
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			List<Reto> Retos = new ArrayList<>();

			try {
				tx.begin();

				Query q = em.createQuery("SELECT u FROM Reto u");
				Retos = (List<Reto>) q.getResultList();
							
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error querying all Retos: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}

			return Retos;
		}

		@Override
		public Reto find(String param) {		
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			Reto result = null; 

			try {
				tx.begin();

				result = (Reto) em.find(Reto.class, param);
				
				tx.commit();
			} catch (Exception ex) {
				System.out.println("  $ Error querying a Reto by nombre: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				em.close();
			}

			return result;
		}
}
