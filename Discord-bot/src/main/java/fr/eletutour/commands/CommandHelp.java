package fr.eletutour.commands;

import fr.eletutour.registry.CommandRegistry;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandHelp implements CommandExecutor{

    private static List<Command> commands = new ArrayList<>();

    public CommandHelp(List<Command> commandList) {
        commands = commandList;
    }

    @Override
    public void run(MessageCreateEvent event, Command command, String[] args) {
        StringBuilder message = new StringBuilder()
                .append("Liste des commandes possible : \n");
        for (Command cmd : commands) {
            message.append("\t - ").append(cmd.getId()).append(" : ");
            for (String alias : cmd.getAliases()) {
                message.append(alias + " / ");
            }
            message.append(" :: ").append(cmd.getDescription());
            message.append("\n");
        }
        event.getChannel().sendMessage(message.toString());

    }
}
