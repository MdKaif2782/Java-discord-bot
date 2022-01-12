package notForPublic;



import org.javacord.api.entity.message.MessageBuilder;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;





public class emblem implements MessageCreateListener 
{
    @Override
    public void onMessageCreate(MessageCreateEvent event)
    {
        if(event.getMessageContent().equalsIgnoreCase("/testEmbed")) 
        {
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                .setTitle("checking")
                .setDescription("its working")
                .setColor(java.awt.Color.BLUE)).send(event.getChannel());


            event.getChannel().sendMessage(String.valueOf(event.getMessageAuthor().getAvatar().getUrl()));


            
        }
    }
    
}
