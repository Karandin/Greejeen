package me.telegram.bot;

/**
 * test_bot 5886775487:AAHQeOuJz37iYtA51d0Th-DsOi1egREoYYw;
 * main_bot 5615013459:AAEcYNmeT18iRre3Y34za65cZLnC84Rn6Ao
 */

public class Main {

    private static final String BOT_TOKEN = "5886775487:AAHQeOuJz37iYtA51d0Th-DsOi1egREoYYw";

    private static final String PROVIDER_TOKEN = "1877036958:TEST:533be1fb7c0abd266c7c8d87ff053c592db708c9";

    public static void main(String[] args) {
        TelegramBotApplication application = TelegramBotApplication.builder()
                .botToken(BOT_TOKEN).providerToken(PROVIDER_TOKEN)
                .build();

        application.run();
    }

}