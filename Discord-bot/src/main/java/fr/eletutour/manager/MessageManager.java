package fr.eletutour.manager;

import fr.eletutour.commands.Command;
import fr.eletutour.commands.CommandHelp;
import fr.eletutour.commands.CommandSayHello;
import fr.eletutour.constants.BotConstant;
import fr.eletutour.registry.CommandRegistry;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;

public class MessageManager {

    private static final CommandRegistry commandRegistry = new CommandRegistry();

    static {
        //TODO : add your command here
        commandRegistry.addCommand(new Command(
            "Hello",
            "say hello to the user",
            new CommandSayHello(),
            "hello"
        ));
        commandRegistry.addCommand(new Command(
                "Help",
                "display the list of command",
                new CommandHelp(commandRegistry.getCommands()),
                "help", "h?"
        ));
    }

    public static void create(MessageCreateEvent event) {
        if(event.getMessageContent().startsWith(BotConstant.PREFIX)){
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(BotConstant.PREFIX.length());
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length);

            String[] finalArgs = args;
            commandRegistry.getByAlias(commandName).ifPresentOrElse(
                    cmd -> cmd.getExecutor().run(event, cmd, finalArgs),
                    () ->event.getChannel().sendMessage("Command not found, try &help to have the list of commands")
            );
        }
    }
}
