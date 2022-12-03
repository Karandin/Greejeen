package me.telegram.bot.model;

import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.request.InlineQueryResult;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Coffee {
    private String id;
    private String name;
    private int price;
    private String description;
    private String photo;
    private boolean withMilk;
    private boolean bigSize;
    private int secondPrice;

    public Coffee(String id ,int price, int secondPrice, String name, String description,String photo,boolean withMilk, boolean bigSize) {
        this.id = id;
        this.price = price;
        this.secondPrice = secondPrice;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.withMilk = withMilk;
        this.bigSize = bigSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Double.compare(coffee.price, price) == 0 && id.equals(coffee.id) && name.equals(coffee.name) && description.equals(coffee.description) && Objects.equals(photo, coffee.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, photo, withMilk);
    }

}
