/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.TestResult;
import entity.UserList;
import entity.Word;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class TestResultFacade extends AbstractFacade<TestResult> {
    @PersistenceContext(unitName = "EnglishWordTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestResultFacade() {
        super(TestResult.class);
    }

    public int getAcucurencyRateByWord(Word word) {
        Float ans = 0f;
        
        String jpqr = "select r from TestResult r "
                + "where r.word = :word";
        @SuppressWarnings("unchecked")
        List<TestResult> resultList = em.createQuery(jpqr).setParameter("word", word).getResultList();
        float total = resultList.size();
        float correct = 0f;
        for(TestResult result: resultList){
            if(result.isTest_result())
                correct++;
        }
        if(0 == total || 0 == correct) {
            return 0;
        }
        ans = (correct / total) * 100;
        return ans.intValue();
    }
    
}
