package fr.eletutour.registry;

import fr.eletutour.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandRegistry {

    private ArrayList<Command> commands;

    public CommandRegistry() {
        this.commands = new ArrayList<>();
    }

    public List<Command> getCommands(){
        return commands;
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public void removeCommand(String id){
        commands.removeIf(cmd -> cmd.getId().equalsIgnoreCase(id));
    }

    public Optional<Command> getByAlias(String alias){
        for (Command cmd : commands){
            if (Arrays.asList(cmd.getAliases()).contains(alias)){
                return Optional.of(cmd);
            }
        }

        return Optional.empty();
    }
}
