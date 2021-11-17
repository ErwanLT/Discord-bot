package fr.eletutour.manager;

import fr.eletutour.commands.Command;
import fr.eletutour.commands.CommandHelp;
import fr.eletutour.constants.BotConstant;
import fr.eletutour.registry.CommandRegistry;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;

public class MessageManager {

    private static final CommandRegistry commandRegistry = new CommandRegistry();

    static {
        commandRegistry.addCommand(new Command(
                "help",
                "display the list of command",
                new CommandHelp(),
                "help", "h?"
        ));
    }

    public static void create(MessageCreateEvent event) {
        if(event.getMessageContent().startsWith(BotConstant.PREFIX)){
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(BotConstant.PREFIX.length());
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length - 1);

            String[] finalArgs = args;
            commandRegistry.getByAlias(commandName).ifPresentOrElse(
                    cmd -> cmd.getExecutor().run(event, cmd, finalArgs),
                    () ->event.getChannel().sendMessage("Command not found")
            );
        }
    }
}
