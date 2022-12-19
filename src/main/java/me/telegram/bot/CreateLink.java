package me.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.LabeledPrice;
import com.pengrad.telegrambot.request.CreateInvoiceLink;

import java.util.HashMap;

public class CreateLink extends TelegramBot {

    public CreateLink(String botToken) {
        super(botToken);
    }

    public String createLink(Order order, String PROVIDER_TOKEN) {
            CreateInvoiceLink Link = new CreateInvoiceLink(order.getCoffeeName(), order.getCoffeeDescription(), "1",
                    PROVIDER_TOKEN, "GEL",
                    new LabeledPrice(order.getCoffeeName(), order.getOrderPrice() * 100));
            return execute(Link).result();
        }
    }

