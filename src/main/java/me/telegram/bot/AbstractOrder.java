
package me.telegram.bot;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;


@Getter
@Setter
public abstract class AbstractOrder {
    private static final String PROVIDER_TOKEN = "1877036958:TEST:533be1fb7c0abd266c7c8d87ff053c592db708c9";
    private static final String BOT_TOKEN = "5615013459:AAEcYNmeT18iRre3Y34za65cZLnC84Rn6Ao";
    private int orderPrice;
    private int milkPrice;
    private int sizePrice;
    private int coffeePrice;
    private String milk;
    private String size;
    private String coffeeName;
    private String getComment;


    public EditMessageCaption orderCaptionSelectedProduct(HashMap<Integer, Order> orderMap, int orderID) {

            this.orderPrice = orderMap.get(orderID).getOrderPrice();
            this.milkPrice = orderMap.get(orderID).getMilkPrice();
            this.sizePrice = orderMap.get(orderID).getSizePrice();
            this.coffeePrice = orderMap.get(orderID).getPriceCoffee();
            this.milk = orderMap.get(orderID).getWitchMilk();
            this.size = orderMap.get(orderID).getWitchSize();
            this.coffeeName = orderMap.get(orderID).getCoffeeName();
            this.getComment = orderMap.get(orderID).getComment();

            return new EditMessageCaption(orderMap.get(orderID).getOrderChatID(),
                    orderMap.get(orderID).getOrderMessageID()).caption("\n" + "› " + coffeeName
                    + ": " + coffeePrice + "₾" + "\n" + "› Молоко - " + milk + ": " + milkPrice + "₾" + "\n"
                    + "› Комментарий к заказу: " + getComment + "\n" + "› Размер - " + size + ": " + sizePrice + "₾" + "\n" + "\n" +
                    "› Итого: " + orderPrice + "₾");
        }


    public InlineKeyboardMarkup orderKeyboard(HashMap<Integer, Order> orderMap,Integer orderID) {

        CreateLink cl = new CreateLink(BOT_TOKEN);
        String invoiceLink = cl.createLink(orderMap.get(orderID), PROVIDER_TOKEN);
        orderMap.get(orderID).setInvoiceLink(invoiceLink);
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton("\uD83E\uDD5B ").callbackData("milk"),
                new InlineKeyboardButton("\uD83D\uDCAC ").callbackData("comment"),
                new InlineKeyboardButton("\uD83C\uDD99").callbackData("size"),
                new InlineKeyboardButton("\uD83C\uDF7D").switchInlineQueryCurrentChat("")).addRow(
                new InlineKeyboardButton("Оплатить " + orderMap.get(orderID).getOrderPrice() + "₾").callbackData("pay").
                        url(invoiceLink));
    }
}


