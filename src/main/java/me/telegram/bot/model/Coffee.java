package me.telegram.bot.model;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Coffee {
    private int price;
    private String description;
    private String photo;
    private boolean withMilk;
    private boolean bigSize;
    private int secondPrice;
    private String name;
    private String id;

    public Coffee(String id, int price, int secondPrice, String name, String description, String photo, boolean withMilk, boolean bigSize) {
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
        return price == coffee.price && withMilk == coffee.withMilk && bigSize == coffee.bigSize && secondPrice == coffee.secondPrice && id.equals(coffee.id) && name.equals(coffee.name) && Objects.equals(description, coffee.description) && Objects.equals(photo, coffee.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, photo, withMilk, bigSize, secondPrice);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", withMilk=" + withMilk +
                ", bigSize=" + bigSize +
                ", secondPrice=" + secondPrice +
                '}';
    }
}
