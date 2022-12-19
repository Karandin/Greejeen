package me.telegram.bot;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import me.telegram.bot.model.Coffee;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pengrad.telegrambot.model.request.ParseMode.HTML;

public class TelegramBotApplication extends TelegramBot {
    private String providerToken;
    private final String botToken = "5615013459:AAEcYNmeT18iRre3Y34za65cZLnC84Rn6Ao";
    Integer orderID = 0;
    int idEdm = 0;
    Integer newOrderID = 0;
    boolean itsAComment = false;
    Order order = new Order();
    OrderAdds orderAdds = new OrderAdds();
    HashMap<Integer, Order> orderMap = new HashMap();
    HashMap<Long, Order> commentMap = new HashMap();
    private static final long ADMIN_ID = 353415726;

    Coffee c1 = new Coffee("1", 5, 0, "espresso", "молотый кофе, вода" + "\n" + "35-40g", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/632844fbad60f7d3dc2a7180_Polyakovfoto_Simple%20Coffee17793%202.jpg", false, false);
    public InlineQueryResultArticle r1 = new InlineQueryResultArticle(c1.getId(), c1.getName() + " " + c1.getPrice() + "₾", "newOrder").thumbUrl(c1.getPhoto()).description(c1.getDescription());
    Coffee c2 = new Coffee("2", 7, 9, "latte", "эспрессо, молоко 3.2%" + "\n" + "250ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/608e6eeab02dde01bd2a17e8_capuch1.jpg", true, true);
    public InlineQueryResultArticle r2 = new InlineQueryResultArticle(c2.getId(), c2.getName() + " " + c2.getPrice() + "₾", "newOrder").thumbUrl(c2.getPhoto()).description(c2.getDescription());
    Coffee c3 = new Coffee("3", 7, 9, "cappuccino", "эспрессо, молоко 3.2%" + "\n" + "200ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/632845fd4a30f55ce6011c1d_Polyakovfoto_Simple%20Coffee17803.jpg", true, true);
    public InlineQueryResultArticle r3 = new InlineQueryResultArticle(c3.getId(), c3.getName() + " " + c3.getPrice() + "₾", "newOrder").thumbUrl(c3.getPhoto()).description(c3.getDescription());
    Coffee c4 = new Coffee("4", 8, 0, "black coffee", "молотый кофе, вода" + "\n" + "220ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/608fd6042da2fc40bdeaa74c_black1.jpg", false, false);
    public InlineQueryResultArticle r4 = new InlineQueryResultArticle(c4.getId(), c4.getName() + " " + c4.getPrice() + "₾", "newOrder").thumbUrl(c4.getPhoto()).description(c4.getDescription());
    Coffee c5 = new Coffee("5", 5, 0, "tea", "чёрный чай" + "\n" + "180ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/60900aef39dd129fbe9a9c88_tea1.jpg", false, false);
    public InlineQueryResultArticle r5 = new InlineQueryResultArticle(c5.getId(), c5.getName() + " " + c5.getPrice() + "₾", "newOrder").thumbUrl(c5.getPhoto()).description(c5.getDescription());
    Coffee c6 = new Coffee("6", 8, 0, "bamble", "эспрессо, апельсиновый фреш" + "\n" + "180ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/609008308e7a077ea96ba930_cold1.jpg", false, false);
    public InlineQueryResultArticle r6 = new InlineQueryResultArticle(c6.getId(), c6.getName() + " " + c6.getPrice() + "₾", "newOrder").thumbUrl(c6.getPhoto()).description(c6.getDescription());
    Coffee c7 = new Coffee("7", 5, 0, "cocoa", "тёмный шоколад 75%, молоко 3.2%" + "\n" + "220ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/632f1db76a137188c11ef861_cacaovddd.jpg", false, false);
    public InlineQueryResultArticle r7 = new InlineQueryResultArticle(c7.getId(), c7.getName() + " " + c7.getPrice() + "₾", "newOrder").thumbUrl(c7.getPhoto()).description(c7.getDescription());
    Coffee c8 = new Coffee("8", 9, 11, "matcha", "матча, молоко 3.2%" + "\n" + "250ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/63340de4ebba5bc95270dd9d_Polyakovfoto_Simple%20Coffee178071.jpg", true, true);
    public InlineQueryResultArticle r8 = new InlineQueryResultArticle(c8.getId(), c8.getName() + " " + c8.getPrice() + "₾", "newOrder").thumbUrl(c8.getPhoto()).description(c8.getDescription());
    Coffee c9 = new Coffee("9", 10, 0, "smoothie", "малина, клубника, молоко 3.2%" + "\n" + "180ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/608fdb2d78749807e46d7af2_juice1.jpg", true, false);
    public InlineQueryResultArticle r9 = new InlineQueryResultArticle(c9.getId(), c9.getName() + " " + c9.getPrice() + "₾", "newOrder").thumbUrl(c9.getPhoto()).description(c9.getDescription());
    Coffee c10 = new Coffee("10", 8, 0, "greejeen", "эспрессо, сливки 10%, шоколад 75%" + "\n" + "180ml", "https://uploads-ssl.webflow.com/5f92b98ef775e43402afe27f/608fd1df6c9455749dd7887d_coctail1.jpg", true, false);
    public InlineQueryResultArticle r10 = new InlineQueryResultArticle(c10.getId(), c10.getName() + " " + c10.getPrice() + "₾", "newOrder").thumbUrl(c10.getPhoto()).description(c10.getDescription());

    @lombok.Builder
    public TelegramBotApplication(String botToken, String providerToken) {
        super(botToken);
        this.providerToken = Optional.ofNullable(providerToken).orElse("");
    }

    public void run() {
        this.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        Message message = update.message();
        InlineQuery inlineQuery = update.inlineQuery();
        CallbackQuery callbackQuery = update.callbackQuery();

        if (callbackQuery != null && callbackQuery.data() != null) {
            String data = callbackQuery.data();
            newOrderID = callbackQuery.message().messageId() - 1;
            /*
            Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
            Matcher matcher = pat.matcher(data);


            while (matcher.find()) {
                newOrderID = Integer.valueOf(matcher.group());
                newOrderID = callbackQuery.message().messageId();
            }

             */
            if (data.contains("milk")) {
                System.out.println(newOrderID);
                this.execute(orderAdds.orderKeyboardChooseMilk(orderMap.get(newOrderID)));
            } else if (data.contains("size")) {
                System.out.println(newOrderID + "," + orderID);
                this.execute(orderAdds.orderKeyboardChooseSize(orderMap.get(newOrderID)));
            } else if (data.contains("comment")) {
                System.out.println(newOrderID + "," + orderID);
                orderMap.get(newOrderID).setItsAComment(true);
                commentMap.put(orderMap.get(newOrderID).getOrderChatID(), orderMap.get(newOrderID));
                this.execute(orderAdds.orderWriteComment(orderMap.get(newOrderID)));
            } else if (data.contains("back")) {
                System.out.println(newOrderID + "," + orderID);
                orderMap.get(newOrderID).setComment("");
                itsAComment = false;
                this.execute(order.orderCaptionSelectedProduct(orderMap, newOrderID).
                        replyMarkup(order.orderKeyboard(orderMap, newOrderID)));
            } else if (data.contains("Milk")) {
                System.out.println(newOrderID + "," + orderID);
                this.execute(orderAdds.orderKeyboardMilkChosen(callbackQuery, orderMap, newOrderID));
            } else if (data.contains("selectedSize")) {
                System.out.println(newOrderID + "," + orderID);
                this.execute(orderAdds.orderKeyboardSizeChosen(callbackQuery, orderMap, newOrderID));
            }
        }
        if (update.inlineQuery() != null) {
            this.execute(new AnswerInlineQuery(inlineQuery.id(), r1, r2, r3, r4, r5, r6, r7, r8, r9, r10));
        }

        if (message != null) {
            idEdm = message.messageId();
            String text = message.text();

            Optional.ofNullable(text)
                    .ifPresent(commandName -> this.serveCommand(commandName, message.chat().id(), update));
            Optional.ofNullable(message.successfulPayment()).ifPresent(successfulPayment -> servePayment(successfulPayment));
        } else if (update.preCheckoutQuery() != null) {
            PreCheckoutQuery preCheckoutQuery = update.preCheckoutQuery();
            execute(new AnswerPreCheckoutQuery(preCheckoutQuery.id()));
        }
    }

    private void servePayment(SuccessfulPayment successfulPayment) {

    }

    private void serveCommand(String commandName, Long chatId, Update update) {
        switch (commandName) {
            case "/start": {
                SendMessage response = new SendMessage(chatId, "Главное меню").
                        replyMarkup(new ReplyKeyboardMarkup("\uD83C\uDF7D Меню", "☕️ О нас").resizeKeyboard(true).
                                oneTimeKeyboard(true));
                this.execute(response);
                break;
            }
            case "☕️ О нас":
            case "/about": {
                this.execute(new SendSticker(chatId, "CAACAgIAAxkBAAEGrO5jjGH0bebNuYJXT-VY_cWbgHBslgACDAADkP2aFZ9oJ0jCaOrRKwQ"));
                this.execute(new SendMessage(chatId, "Привет, " + update.message().from().firstName() + "❤️"));
                EditMessageText edm = new EditMessageText(chatId, idEdm + 2, "У нас лучший кофе в Батуми ☕️");
                EditMessageText edm1 = new EditMessageText(chatId, idEdm + 2, "А ещё мы любим домашних питомцев :) \uD83D\uDC36 ");
                EditMessageText edm2 = new EditMessageText(chatId, idEdm + 2, "Наш Инстаграм \uD83D\uDCF7" +
                        " Cделать заказ☕️  " + "\n" + "\n" +
                        "Пн-Пт 09:00-21:00 \uD83D\uDD58" + "\n" + "Сб-Вс 09:00-22:00 \uD83D\uDD59").
                        replyMarkup(new InlineKeyboardMarkup(
                                new InlineKeyboardButton("Instagram").url("https://www.instagram.com/greejeen.coffee/"),
                                new InlineKeyboardButton("Сделать заказ").switchInlineQueryCurrentChat("")));
                try {
                    TimeUnit.SECONDS.sleep(2);
                    this.execute(edm);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                    this.execute(edm1);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.execute(edm2);
                break;
            }
            case "/help": {
                SendMessage response = new SendMessage(chatId, "ℹ️Для связи с администрацией, нажимайте на кнопку ниже:").
                        replyMarkup(new InlineKeyboardMarkup(
                                new InlineKeyboardButton("\uD83E\uDDB9\u200D♂️Админ").url("https://t.me/m_karandin")));
                this.execute(response);
                break;
            }
            case "newOrder": {
                List<Coffee> coffees = new ArrayList<>();
                coffees.add(c1);
                coffees.add(c2);
                coffees.add(c3);
                coffees.add(c4);
                coffees.add(c5);
                coffees.add(c6);
                coffees.add(c7);
                coffees.add(c8);
                coffees.add(c9);
                coffees.add(c10);
                GetUpdatesResponse updatesResponse = this.execute(new GetUpdates());
                List<Update> updates = updatesResponse.updates();
                for (Update upd : updates) {
                    if (upd.chosenInlineResult() != null) {
                        for (Coffee cof : coffees) {
                            if (upd.chosenInlineResult().resultId().equals(cof.getId())) {

                                CreateInvoiceLink Link = new CreateInvoiceLink(cof.getName(), cof.getDescription(),
                                        cof.getId(), providerToken, "GEL",
                                        new LabeledPrice(cof.getName(), cof.getPrice() * 100));

                                String invoiceLink = execute(Link).result();
                                orderID++;

                                Order order = new Order(orderID, chatId, update.message().messageId() + 1, cof.getPrice(), 0, 0, cof.getPrice(), cof.getName(),
                                        cof.getDescription(), "M", "Обычное", invoiceLink, "", update.message().from().username(), false);

                                orderMap.put(update.message().messageId(), order);

                                SendPhoto response = new SendPhoto(chatId, cof.getPhoto()).caption("\n" + "› " + cof.getName()
                                        + ": " + cof.getPrice() + "₾ \n" + "› Молоко - Обычное: +0₾\n" + "› Комментарий к заказу: \n" +
                                        "› Размер - М: 0₾\n \n" + "› Итого: " +
                                        cof.getPrice() + "₾").parseMode(HTML).replyMarkup(new InlineKeyboardMarkup(
                                        new InlineKeyboardButton("\uD83E\uDD5B ").callbackData("milk "),
                                        new InlineKeyboardButton("\uD83D\uDCAC ").callbackData("comment "),
                                        new InlineKeyboardButton("\uD83C\uDD99").callbackData("size "),
                                        new InlineKeyboardButton("\uD83C\uDF7D").switchInlineQueryCurrentChat("")).addRow(
                                        new InlineKeyboardButton("Оплатить " + cof.getPrice() + "₾").callbackData("pay ").url(invoiceLink)));
                                this.execute(response);
                            }
                        }
                    }
                }
                break;
            }
            case "\uD83C\uDF7D Меню": {
                SendMessage response = new SendMessage(chatId, "\uD83C\uDF70 Homemade desserts \uD83E\uDED6 Natural tea\n" +
                        "\uD83E\uDDCB Soft drinks and more \uD83E\uDD5E Breakfast").replyMarkup(new InlineKeyboardMarkup(
                        new InlineKeyboardButton("\uD83C\uDF7D Меню").switchInlineQueryCurrentChat("")));
                this.execute(response);
                break;
            }
            case "/admin": {
                if (chatId == ADMIN_ID) {
                    for (Integer key : orderMap.keySet()) {
                        this.execute(new SendMessage(chatId, orderMap.get(key).getCustomer()));
                    }
                    break;
                } else {
                    this.execute(new SendMessage(chatId, "Команда не найдена"));
                    break;
                }
            }
            default: {
                if (commentMap.get(chatId) != null) {
                    Integer id = commentMap.get(chatId).getOrderMessageID();
                    id--;
                    CreateLink cl = new CreateLink(botToken);
                    String invoiceLink = cl.createLink(orderMap.get(id), providerToken);
                    orderMap.get(id).setInvoiceLink(invoiceLink);

                    orderMap.get(id).setComment(commandName);
                    this.execute(new DeleteMessage(chatId, update.message().messageId()));
                    this.execute(new EditMessageCaption(chatId, orderMap.get(id).getOrderMessageID()).caption(
                            "\n" + "› " + orderMap.get(id).getCoffeeName()
                                    + ": " + orderMap.get(id).getPriceCoffee() + "₾" + "\n" + "› Молоко - " + orderMap.get(id).getWitchMilk() + ": " + orderMap.get(id).getMilkPrice() + "₾" + "\n"
                                    + "› Комментарий к заказу: " + orderMap.get(id).getComment() + "\n› " + "Размер - " + orderMap.get(id).getWitchSize() + ": " + orderMap.get(id).getSizePrice() + "₾" + "\n" + "\n" +
                                    "› Итого: " + orderMap.get(id).getOrderPrice() + "₾").replyMarkup(
                            new InlineKeyboardMarkup(
                                    new InlineKeyboardButton("\uD83E\uDD5B ").callbackData("milk"),
                                    new InlineKeyboardButton("\uD83D\uDCAC ").callbackData("comment"),
                                    new InlineKeyboardButton("\uD83C\uDD99").callbackData("size"),
                                    new InlineKeyboardButton("\uD83C\uDF7D").switchInlineQueryCurrentChat("")).addRow(
                                    new InlineKeyboardButton("Оплатить " + orderMap.get(id).getOrderPrice() + "₾").
                                            callbackData("pay").url(invoiceLink)
                            )));
                    commentMap.remove(chatId);
                    break;
                }
                SendMessage response = new SendMessage(chatId, "Команда не найдена");
                this.execute(response);
                break;
            }
        }
    }
}
