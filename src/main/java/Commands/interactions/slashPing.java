package Commands.interactions;

import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

public class slashPing implements SlashCommandCreateListener {
    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        String interaction = event.getSlashCommandInteraction().getCommandName();
        if (interaction.equalsIgnoreCase("ping")){
            event.getInteraction().createImmediateResponder().setContent("Pong!").respond();
        }
    }
}
