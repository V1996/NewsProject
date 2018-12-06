package test.news.news.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sports")
public class Sports implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sports_id")
    public int sports_id;

    @Column(name="category")
    public String category;

    @OneToMany(mappedBy = "sports", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SportsDataEntity> sportsDataEntities;

    public int getSports_id() {
        return sports_id;
    }

    public void setSports_id(int sports_id) {
        this.sports_id = sports_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SportsDataEntity> getSportsDataEntities() {
        return sportsDataEntities;
    }

    public void setSportsDataEntities(List<SportsDataEntity> sportsDataEntities) {
        this.sportsDataEntities = sportsDataEntities;
    }
}
