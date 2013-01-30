package de.lineas.croqueorder.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * An user can make mealOrders and have a list of made mealOrders.
 */
@Entity
public class User extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;

    private String firstname;
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MealOrder> mealOrders = new ArrayList<MealOrder>();

    public User() {
        this(null);
    }

    public User(Long id) {
        this.setId(id);
    }


    /**
     * Creates an order with today's order date and saves it in the user's list of orders.
     * @param amount of articles to order
     * @param article which concrete item to order
     */
    public void order(int amount, Article article) {
        MealOrder mealOrder = new MealOrder(amount, article);
        this.mealOrders.add(mealOrder);
    }




    /* boring stuff following...  */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<MealOrder> getMealOrders() {
        return mealOrders;
    }

    public void setMealOrders(List<MealOrder> mealOrders) {
        this.mealOrders = mealOrders;
    }


    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[]{"id", "mealOrders"});    //some odd behaviour over here...
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"id", "mealOrders"});
    }
}
