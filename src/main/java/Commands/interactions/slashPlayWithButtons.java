package Commands.interactions;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class slashPlayWithButtons implements SlashCommandCreateListener {
    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {

        if (event.getSlashCommandInteraction().getCommandName().equalsIgnoreCase("play_with_buttons")){
            new MessageBuilder()
                    .setContent("Click me please...")
                    .addComponents(ActionRow.of(
                            Button.create("show_my_avatar", ButtonStyle.PRIMARY,"Show my avatar"),
                            Button.create("show_your_avatar",ButtonStyle.PRIMARY,"Show your avatar"),
                            Button.create("this_is_disabled_button_test",ButtonStyle.PRIMARY,"Click",true)
                    )).send(event.getInteraction().getChannel().get()).thenAccept(message -> {
                        message.createUpdater().removeAllComponents().addActionRow(
                                Button.create("show_my_avatar", ButtonStyle.PRIMARY,"us"),
                                Button.create("show_your_avatar",ButtonStyle.PRIMARY,"trma"),
                                Button.create("this_is_disabled_button_test",ButtonStyle.PRIMARY,"chudi",true)
                        ).applyChanges();
                    });

            event.getInteraction()
                    .createImmediateResponder()
                    .setContent("Click me please...")
                    .addComponents(ActionRow.of(
                            Button.create("show_my_avatar", ButtonStyle.PRIMARY,"Show my avatar"),
                            Button.create("show_your_avatar",ButtonStyle.PRIMARY,"Show your avatar"),
                            Button.create("this_is_disabled_button_test",ButtonStyle.PRIMARY,"Click",true)
                    ))
                    .respond().thenAccept(res -> event.getApi().addButtonClickListener(button->{
                        String customID = button.getButtonInteraction().getCustomId();
                        if (customID.equalsIgnoreCase("show_my_avatar")){
                            String avatarURL = event.getInteraction().getUser().getAvatar(1024).getUrl().toString();

                                MessageBuilder mes = new MessageBuilder()
                                        .setContent(avatarURL)
                                        .addActionRow(
                                                Button.create("show_my_avatar", ButtonStyle.PRIMARY,"Show my avatar"),
                                                Button.create("show_your_avatar",ButtonStyle.PRIMARY,"Show your avatar"),
                                                Button.create("this_is_disabled_button_test",ButtonStyle.PRIMARY,"Mah",true)
                                        );
                            try {
                                res.update().get().createUpdater().removeAllComponents().addActionRow(
                                        Button.create("show_my_avatar", ButtonStyle.PRIMARY,"Show my avatar"),
                                        Button.create("show_your_avatar",ButtonStyle.PRIMARY,"Show your avatar"),
                                        Button.create("this_is_disabled_button_test",ButtonStyle.PRIMARY,"Mah",true)
                                ).applyChanges();
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }


                        } else if (customID.equalsIgnoreCase("show_your_avatar")){
                            String avatarURL = event.getApi().getYourself().getAvatar(1024).getUrl().toString();
                            try {
                                res.update().get().edit(avatarURL);
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }));
        };


    }
}
