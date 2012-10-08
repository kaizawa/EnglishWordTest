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
@Table(name = "WORD")
public class Word implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ENGLISH")
    private String english;
    @Column(name = "JAPANESE")    
    private String japanese;
    

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }
    

    /**
     * Get the value of japanese
     *
     * @return the value of japanese
     */
    public String getJapanese() {
        return japanese;
    }

    /**
     * Set the value of japanese
     *
     * @param japanese new value of japanese
     */
    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }


    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (english != null ? english.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Word)) {
            return false;
        }
        Word other = (Word) object;
        if ((this.english == null && other.english != null) || (this.english != null && !this.english.equals(other.english))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Word[ id=" + english + " ]";
    }
    
}
