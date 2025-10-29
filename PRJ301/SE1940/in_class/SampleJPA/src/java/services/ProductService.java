package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.ProductDTO;

public class ProductService implements IProductService<ProductDTO> {
    private EntityManagerFactory emf;
    
    public ProductService(){
        this.emf=Persistence.createEntityManagerFactory("SampleJPA");
    }
    
    @Override
    public void close(){
        if(this.emf != null && this.emf.isOpen())
            this.emf.close();
    }
    
    @Override
    public List<ProductDTO> getAll () {
        EntityManager em = this.emf.createEntityManager();
        List<ProductDTO> products = null;
        try {
            products = em.createQuery("SELECT p FROM ProductDTO p", 
                    ProductDTO.class).getResultList();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return products;
    }

    @Override
    public ProductDTO searchById (int id) {
        EntityManager em = this.emf.createEntityManager();
        ProductDTO product = null;
        try {
            product = em.find(ProductDTO.class, id);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return product;
    }

    @Override
    public boolean add (ProductDTO product) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            result = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return result;
    }
    
    public boolean update (ProductDTO product) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        try {
            ProductDTO pro = em.find(ProductDTO.class, product.getId());
            if (pro!=null) {
                em.getTransaction() .begin();
                em.merge(product);
                em.getTransaction() .commit();
                result = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return result;
    }
    
    public boolean delete (ProductDTO product) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        try {
            ProductDTO pro = em.find(ProductDTO.class, product.getId());
            if (pro!=null) {
                em.getTransaction() .begin();
                em.remove(product);
                em.getTransaction() .commit();
                result = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return result;
    }
}
