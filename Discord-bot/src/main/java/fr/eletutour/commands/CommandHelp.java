package fr.eletutour.commands;

import org.javacord.api.event.message.MessageCreateEvent;

public class CommandHelp implements CommandExecutor{

    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        event.getChannel().sendMessage("Liste des commandes possible : \n" +
                "\t - Help : &help / &h?\n");
    }
}
