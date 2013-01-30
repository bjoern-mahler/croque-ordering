package de.lineas.croqueorder.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple order which has an amount and an included article. For simplification there are no order items or positions.
 */
@Entity
public class MealOrder extends AbstractPersistable<Long> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat();

    @Column(length = 3)
    private int amount;

    @Enumerated(EnumType.STRING)
    private Article item;

    private Date date;

    public MealOrder() {
        this(null);
    }

    public MealOrder(Long id) {
        setId(id);
    }

    public MealOrder(int amount, Article article) {
        this(null);
        this.amount = amount;
        this.item = article;
        this.date = new Date();
    }


    /* boring stuff following...  */
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Article getItem() {
        return item;
    }

    public void setItem(Article item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[]{"id"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[]{"id"});
    }



/*    public void setFdate(String fdate) {
        Date date = null;
        try {
            date = formatter.parse(fdate);
        } catch (ParseException e) {
            //who cares
        }

        setDa
    }*/
}
