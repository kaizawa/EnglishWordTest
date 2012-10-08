/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.UserList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class UserListFacade extends AbstractFacade<UserList> {
    @PersistenceContext(unitName = "EnglishWordTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserListFacade() {
        super(UserList.class);
    }

    public UserList findByUsername(String username) {
        
        String jpqr = "select u from UserList u "
                + "where u.username = :username";
        
        @SuppressWarnings("unchecked")
        UserList userlist = (UserList)em.createQuery(jpqr).setParameter("username", username).getSingleResult();
        return userlist;
    }
    
}
