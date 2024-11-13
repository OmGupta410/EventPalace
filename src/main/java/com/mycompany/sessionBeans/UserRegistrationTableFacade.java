package com.mycompany.sessionBeans;

import com.mycompany.entity.UserRegistrationTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserRegistrationTableFacade extends AbstractFacade<UserRegistrationTable> implements UserRegistrationTableFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRegistrationTableFacade() {
        super(UserRegistrationTable.class);
    }

    @Override
public UserRegistrationTable findByEmailAndPassword(String email, String password) {
    try {
        TypedQuery<UserRegistrationTable> query = em.createQuery(
            "SELECT u FROM UserRegistrationTable u WHERE u.email = :email AND u.password = :password", UserRegistrationTable.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getSingleResult();
    } catch (javax.persistence.NoResultException e) {
        return null; // Return null if no result is found
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
