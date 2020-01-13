package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author asgerhs
 */
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private List<Timestamp> timestamp;
    private String name; 
    
    public Category() {
        
    }

    public Category(List<Timestamp> timestamp, String name) {
        this.timestamp = timestamp;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Timestamp> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(List<Timestamp> timestamp) {
        this.timestamp = timestamp;
    }

    
    
}
