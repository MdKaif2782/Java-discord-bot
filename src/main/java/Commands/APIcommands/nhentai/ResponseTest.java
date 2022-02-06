package Commands.APIcommands.nhentai;

import net.beardbot.nhentai.NHentai;
import net.beardbot.nhentai.api.GalleryPage;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.message.embed.EmbedImage;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ResponseTest implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {




            String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!nhentai")) {
            AtomicReference<AtomicInteger> i = new AtomicReference<>(new AtomicInteger());

            //Nhentai Grabbing
            var nHentai = new NHentai();
            var gallery = nHentai.galleries().getGallery(Long.parseLong(msg[1]));

            List<GalleryPage> pages = gallery.get().getPages();




            event.getChannel().sendMessage("Checking connection....\nhttps://media4.giphy.com/media/g7GKcSzwQfugw/giphy.gif").thenAccept(message -> {
                message.addReaction("\uD83D\uDC48");
                message.addReaction("\uD83D\uDC49");


                // Attach a listener directly to the message

                message.addReactionAddListener(reactionEvent -> {
                    int count = 0;
                        if (reactionEvent.getEmoji().equalsEmoji("\uD83D\uDC49")) {
                            i.get().getAndIncrement();
//                            reactionEvent.editMessage("Counting -- " + i);

                            int pageno = i.get().get();
                            reactionEvent.editMessage("*Page: "+pageno+" out of "+ pages.size()+"* \n" +
                                    pages.get(pageno).getImage().getDownloadUrl());

                            System.out.println(reactionEvent.getCount().get());


                        }

                    }).removeAfter(30, TimeUnit.MINUTES);



                message.addReactionRemoveListener(reactionremove -> {
                   if (reactionremove.getEmoji().equalsEmoji("\uD83D\uDC49")){
                       int count  = reactionremove.getCount().get();
                       if (count<2){
                           i.get().getAndIncrement();
//                           reactionremove.editMessage("Counting -- " + i);
                           int pageno = i.get().get();
                           reactionremove.editMessage("*Page: "+pageno+" out of "+ pages.size()+"* \n" +
                                   pages.get(pageno).getImage().getDownloadUrl());





                       }
                   }
                }).removeAfter(30, TimeUnit.MINUTES);

                message.addReactionAddListener(reactionEvent -> {
                    int count = 0;
                    if (reactionEvent.getEmoji().equalsEmoji("\uD83D\uDC48")) {
                        i.get().getAndDecrement();
//                        reactionEvent.editMessage("Counting -- " + i);
                        int pageno = i.get().get();
                        reactionEvent.editMessage("*Page: "+pageno+" out of "+ pages.size()+"* \n" +
                                pages.get(pageno).getImage().getDownloadUrl());


                        System.out.println(reactionEvent.getCount().get());


                    }

                }).removeAfter(30, TimeUnit.MINUTES);

                message.addReactionRemoveListener(reactionremove -> {
                    if (reactionremove.getEmoji().equalsEmoji("\uD83D\uDC48")){
                        int count  = reactionremove.getCount().get();
                        if (count<2){
                            i.get().getAndDecrement();
//                            reactionremove.editMessage("Counting -- " + i);

                            int pageno = i.get().get();
                            reactionremove.editMessage(
                                    pages.get(pageno).getImage().getDownloadUrl()+" \n*Page: "+pageno+" out of "+ pages.size()+"* \n");




                        }
                    }
                }).removeAfter(30, TimeUnit.MINUTES);

                message.edit("Response OK...");



                });


        }
    }
}
