package Commands.admins;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Help implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!help"))
        {
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                            .setThumbnail("https://i.imgur.com/9XXIZvF.png")
                            .setAuthor("JavaCord","https://www.youtube.com/watch?v=dQw4w9WgXcQ","https://cdn.discordapp.com/avatars/924608474409742357/55ab8b4aadfad524d40bb37664c46b93.png")
                    .setTitle("JavaCord Help center")
                    .setDescription("\n**Welcome to JavaCord help center!**\n" +
                            "**Here are all the commands you are looking for.**\n\n")
                                    .addField("Commands:","1. ```!ping``` -> to check bot ping\n\n" +
                                            "2. ```!rate (A personality type) @someone``` -> get a rating on a personality type of him/her!\n\n" +
                                            "3. ```!height (your height in cm)``` -> see what bot thinks about your height XD\n\n" +
                                            "4. ```gay``` -> see who the gay is!\n\n" +
                                            "5. ```!urmom @someone``` -> enjoy a yomama meme about someone's mom\n\n" +
                                            "6. ```!yomama @someone``` -> enjoy a Yo Mama joke about someone's mom\n\n" +
                                            "7. ```!yodaddy @someone``` -> enjoy a Yo Daddy joke aboout someone's dad\n\n" +
                                            "8. ```!roast @someone``` -> Let bot roast someone for you!\n\n" +
                                            "9. ```!wallet show``` -> see your gained BorgarCoins!\n\n" +
                                            "10. ```!clear (number of message you want to clear)``` -> Delete a certain amount of message from chat\n\n" +
                                            "11. ```!domath (add/sub/div/mul) (num1) (num1)``` -> let bot do some calculations for you!\n\n" )
                    .addField("NOTE:","num1 and num2 represent the float value of a number\n" +
                            "and ofc write the commands without brackets!" )
                    .setColor(java.awt.Color.BLACK)).send(event.getChannel());
        }
    }
}
