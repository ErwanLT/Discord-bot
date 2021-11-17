package fr.eletutour.commands;

import org.javacord.api.event.message.MessageCreateEvent;

public class CommandSayHello implements CommandExecutor{
    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        event.getChannel().sendMessage("Hello " + event.getMessage().getAuthor().getDisplayName() + ":wave:");
    }
}
