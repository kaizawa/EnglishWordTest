/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name="TEST")
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="TEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date test_date;

    /**
     * Get the value of test_date
     *
     * @return the value of test_date
     */
    public Date getTest_date() {
        return test_date;
    }

    /**
     * Set the value of test_date
     *
     * @param test_date new value of test_date
     */
    public void setTest_date(Date test_date) {
        this.test_date = test_date;
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
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Test[ id=" + id + " ]";
    }
    
}
