package me.telegram.bot;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import me.telegram.bot.model.Coffee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.pengrad.telegrambot.model.request.ParseMode.HTML;

public class TelegramBotApplication extends TelegramBot {
    private TelegramBot bot = new TelegramBot("5615013459:AAEcYNmeT18iRre3Y34za65cZLnC84Rn6Ao");
    private String providerToken;
    int idEdm = 0;
    String userName = "";


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

        if (update.inlineQuery() != null) {
            System.out.println(inlineQuery.from().username());
            this.execute(new AnswerInlineQuery(inlineQuery.id(), r1, r2, r3, r4, r5, r6, r7, r8, r9, r10));
        }
        if (callbackQuery != null) {
            if (callbackQuery.data() != null) {
                SendMessage sm = new SendMessage(callbackQuery.message().chat().id(), "Эта кнопка пока не работает, но скоро научится :)");
                this.execute(sm);
            }
        }
        if (message != null) {
            idEdm = message.messageId();
            userName = message.chat().firstName();
            String text = message.text();

            Optional.ofNullable(text)
                    .ifPresent(commandName -> this.serveCommand(commandName, message.chat().id()));
            Optional.ofNullable(message.successfulPayment()).ifPresent(successfulPayment -> servePayment(successfulPayment));
        } else if (update.preCheckoutQuery() != null) {
            PreCheckoutQuery preCheckoutQuery = update.preCheckoutQuery();
            execute(new AnswerPreCheckoutQuery(preCheckoutQuery.id()));
        }
    }

    private void servePayment(SuccessfulPayment successfulPayment) {

    }

    private void serveCommand(String commandName, Long chatId) {
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
                bot.execute(new SendSticker(chatId, "CAACAgIAAxkBAAEGrO5jjGH0bebNuYJXT-VY_cWbgHBslgACDAADkP2aFZ9oJ0jCaOrRKwQ"));
                bot.execute(new SendMessage(chatId, "Привет, " + userName + "❤️"));
                WebAppInfo s = new WebAppInfo("https://www.instagram.com/greejeen.coffee/");

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
                    bot.execute(edm);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                    bot.execute(edm1);
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
                GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates());
                List<Update> updates = updatesResponse.updates();
                for (Update upd : updates) {
                    if (upd.chosenInlineResult() != null) {
                        for (Coffee cof : coffees) {
                            if (upd.chosenInlineResult().resultId().equals(cof.getId())) {
                                CreateInvoiceLink Link = new CreateInvoiceLink(cof.getName(), cof.getDescription(),
                                        cof.getId(), providerToken, "GEL",
                                        new LabeledPrice(cof.getName(), cof.getPrice() * 100));
                                String invoiceLink = execute(Link).result();

                                SendPhoto msg = new SendPhoto(chatId, cof.getPhoto()).caption("\n" + "› " + cof.getName() + ": " + cof.getPrice() + "₾" + "\n" +
                                        "› " + "Молоко - Обычное: +0₾" + "\n" + "› " + "Промежуточно: " + cof.getPrice() + "₾" + "\n" + "\n" + "› " + "Итого: " + cof.getPrice() + "₾").parseMode(HTML).replyMarkup(new InlineKeyboardMarkup(
                                        new InlineKeyboardButton("Оплатить " + cof.getPrice() + "₾").callbackData("otdal").url(invoiceLink)
                                ));
                                this.execute(msg);
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

            default: {
                SendMessage response = new SendMessage(chatId, "Команда не найдена");
                this.execute(response);
                break;
            }
        }
    }
}
                /*
replyMarkup(new ReplyKeyboardMarkup(new String[][]{
                        {"\uD83D\uDCA9 Язык"},//, "\uD83C\uDF7D Меню"},
                        {"\uD83D\uDD27 Поддержка"}}).resizeKeyboard(true));
                break;

                   WebAppInfo s = new WebAppInfo("https://karandin.github.io/");
    KeyboardButton newBut = new KeyboardButton("kikiri").webAppInfo(s);
                 */
