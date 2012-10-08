/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "TEST_RESULT")
public class TestResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TEST_RESULT")
    private boolean test_result;
    @JoinColumn(name = "WORD", referencedColumnName = "ID")
    @ManyToOne
    private Word word;
    @JoinColumn(name = "USER_LIST", referencedColumnName = "ID")
    @ManyToOne
    private UserList userList;
    @JoinColumn(name = "TEST", referencedColumnName = "ID")
    @ManyToOne
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
    
    /**
     * Get the value of test_result
     *
     * @return the value of test_result
     */
    public boolean isTest_result() {
        return test_result;
    }

    /**
     * Set the value of test_result
     *
     * @param test_result new value of test_result
     */
    public void setTest_result(boolean test_result) {
        this.test_result = test_result;
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public UserList getUser() {
        return userList;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(UserList user) {
        this.userList = user;
    }


    /**
     * Get the value of word
     *
     * @return the value of word
     */
    public Word getWord() {
        return word;
    }

    /**
     * Set the value of word
     *
     * @param word new value of word
     */
    public void setWord(Word word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestResult)) {
            return false;
        }
        TestResult other = (TestResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Result[ id=" + id + " ]";
    }
    
}
