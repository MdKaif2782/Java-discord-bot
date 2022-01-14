package Commands.domath;


import org.javacord.api.entity.message.MessageBuilder;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;





public class DoMath implements MessageCreateListener
{

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        long start = System.currentTimeMillis();

        String msg[] = event.getMessageContent().split(" ");
        int a = Integer.parseInt(msg[2]);
        int b = Integer.parseInt(msg[3]);

        float x = Float.parseFloat(msg[2]);
        float y = Float.parseFloat(msg[3]);

        String s;

        if (event.getMessageContent().equalsIgnoreCase("/Math"))
        {
            event.getChannel().sendMessage("You have entered the bot's arithmatic unit.\n 1.To add - !domath add num1 num2 ");
        } 
        else if(msg[0].equalsIgnoreCase("!domath") && msg[1].equalsIgnoreCase("add"))
        {
            int result = a + b;
            s = " + ";
            new MessageBuilder().setEmbeds(new EmbedBuilder()
            .setTitle("Calculator")
            .setDescription("Your Addition result is:\n\n" + a + s + b + " = " + result)
                    .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
            .setThumbnail("https://thumbs.dreamstime.com/b/calculation-mathematics-accountant-concept-flat-design-isolated-color-background-52017671.jpg")
            .setColor(java.awt.Color.GREEN)).send(event.getChannel());
        }
        else if(msg[0].equalsIgnoreCase("!domath") && msg[1].equalsIgnoreCase("sub"))
        {
            int result = a - b;
            s = " - ";
            new MessageBuilder().setEmbeds(new EmbedBuilder()
            .setTitle("Calculator")
            .setDescription("Your Substraction result is:\n\n" + a + s + b + " = " + result)
                    .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
            .setThumbnail("https://thumbs.dreamstime.com/b/calculation-mathematics-accountant-concept-flat-design-isolated-color-background-52017671.jpg")
            .setColor(java.awt.Color.GREEN)).send(event.getChannel());
        }
        else if(msg[0].equalsIgnoreCase("!domath") && msg[1].equalsIgnoreCase("mul"))
        {
            int result = a*b;
            s = " x ";
            new MessageBuilder().setEmbeds(new EmbedBuilder()
            .setTitle("Calculator")
            .setDescription("Your Multiplication result is:\n\n" + a + s + b + " = " + result)
                    .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
            .setThumbnail("https://thumbs.dreamstime.com/b/calculation-mathematics-accountant-concept-flat-design-isolated-color-background-52017671.jpg")
            .setColor(java.awt.Color.GREEN)).send(event.getChannel());
        }
        else if(msg[0].equalsIgnoreCase("!domath") && msg[1].equalsIgnoreCase("div"))
        {
            float result = (x/y);
            s = " ÷ ";
            new MessageBuilder().setEmbeds(new EmbedBuilder()
            .setTitle("Calculator")
            .setDescription("Your Division result is:\n\n" + a + s + b + " = " + result)
                    .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
            .setThumbnail("https://thumbs.dreamstime.com/b/calculation-mathematics-accountant-concept-flat-design-isolated-color-background-52017671.jpg")
            .setColor(java.awt.Color.GREEN)).send(event.getChannel());
            
        }

        


    }
}
