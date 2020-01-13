package entities.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author asgerhs
 */
public class JokeDTO implements Serializable {

    private String category, joke;

    public JokeDTO() {
    }

    public JokeDTO(String category, String joke) {
        this.category = category;
        this.joke = joke;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    
   
    
}
