package fr.eletutour;

import fr.eletutour.constants.BotConstant;
import fr.eletutour.manager.MessageManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import javax.security.auth.login.LoginException;

public class DiscordBotMain {

    private static DiscordApi api;

    public static void main(String[] args) throws LoginException {

        api = new DiscordApiBuilder()
                .setToken(BotConstant.TOKEN)
                .login()
                .join();

        api.addMessageCreateListener(MessageManager::create);
    }
}
