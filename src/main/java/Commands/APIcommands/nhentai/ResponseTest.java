package Commands.APIcommands.nhentai;

import net.beardbot.nhentai.NHentai;
import net.beardbot.nhentai.api.GalleryPage;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.event.interaction.InteractionCreateEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.interaction.InteractionCreateListener;
import org.javacord.api.listener.message.MessageCreateListener;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ResponseTest implements MessageCreateListener, InteractionCreateListener {
    @Override
    public void onInteractionCreate(InteractionCreateEvent event) {
        String interactionName = event.getSlashCommandInteraction().get().getCommandName();
        if (interactionName.equalsIgnoreCase("nhentai")){
            event.getInteraction().createImmediateResponder().setContent("*Fetching doujin from API...*").respond().thenAccept(interactionOriginalResponseUpdater -> {
                try {
                    interactionOriginalResponseUpdater.update().get().delete();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
            Optional<Double> Scode = event.getSlashCommandInteraction().get().getOptionDecimalValueByName("code");
            Long code = Scode.get().longValue();
            AtomicReference<AtomicInteger> i = new AtomicReference<>(new AtomicInteger());
            i.get().set(0);


            //Nhentai Grabbing
            var nHentai = new NHentai();

            var gallery = nHentai.galleries().getGallery(code);

            List<GalleryPage> pages = gallery.get().getPages();

            event.getInteraction().getChannel().get().sendMessage(pages.get(0).getImage().getDownloadUrl()).thenAccept(message -> {
                message.createUpdater().addActionRow(
                        Button.create("prev_page_nhentai", ButtonStyle.PRIMARY, "Prev Page"),
                        Button.create("page_count_nhentai", ButtonStyle.PRIMARY, "Page 1", true),
                        Button.create("next_page_nhentai", ButtonStyle.PRIMARY, "Next Page")
                ).applyChanges();
                message.addButtonClickListener(button->{
                    String customID = button.getButtonInteraction().getCustomId();
                    if (customID.equalsIgnoreCase("next_page_nhentai")){
                        i.get().getAndIncrement();
                        int k = i.get().get();
                        message.edit(pages.get(i.get().get()).getImage().getDownloadUrl());
                        message.createUpdater()
                                .addActionRow(
                                        Button.create("prev_page_nhentai", ButtonStyle.PRIMARY, "Prev Page"),
                                        Button.create("page_count_nhentai", ButtonStyle.PRIMARY, (k+1)+"/"+pages.size(), true),
                                        Button.create("next_page_nhentai", ButtonStyle.PRIMARY, "Next Page")
                                ).applyChanges();
                    } else if (customID.equalsIgnoreCase("prev_page_nhentai")){
                        i.get().getAndDecrement();
                        int k = i.get().get();
                        message.edit(pages.get(i.get().get()).getImage().getDownloadUrl());
                        message.createUpdater()
                                .addActionRow(
                                        Button.create("prev_page_nhentai", ButtonStyle.PRIMARY, "Prev Page"),
                                        Button.create("page_count_nhentai", ButtonStyle.PRIMARY, (k+1)+"/"+pages.size(), true),
                                        Button.create("next_page_nhentai", ButtonStyle.PRIMARY, "Next Page")
                                ).applyChanges();
                    }
                });
            });

        }


    }
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
            String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!nhentai") && msg[1]!=null) {

            AtomicReference<Message> alert = new AtomicReference<>();
            event.getChannel().sendMessage("*Fetching doujin from API....*").thenAccept(message -> {
                alert.set(message);
            });
            AtomicReference<AtomicInteger> i = new AtomicReference<>(new AtomicInteger());
            i.get().set(0);
            long doujinCode;

            //Nhentai Grabbing
            var nHentai = new NHentai();

            var gallery = nHentai.galleries().getGallery(Long.parseLong(msg[1]));

            List<GalleryPage> pages = gallery.get().getPages();



            alert.get().delete();
            event.getChannel().sendMessage(pages.get(0).getImage().getDownloadUrl()).thenAccept(message -> {
                        message.createUpdater().addActionRow(
                                Button.create("prev_page_nhentai", ButtonStyle.PRIMARY, "Prev Page"),
                                Button.create("page_count_nhentai", ButtonStyle.PRIMARY, "Page 1", true),
                                Button.create("next_page_nhentai", ButtonStyle.PRIMARY, "Next Page")
                        ).applyChanges();
                        message.addButtonClickListener(button->{
                           String customID = button.getButtonInteraction().getCustomId();
                           if (customID.equalsIgnoreCase("next_page_nhentai")){
                               i.get().getAndIncrement();
                               int k = i.get().get();
                               message.edit(pages.get(i.get().get()).getImage().getDownloadUrl());
                               message.createUpdater()
                                       .addActionRow(
                                       Button.create("prev_page_nhentai", ButtonStyle.PRIMARY, "Prev Page"),
                                       Button.create("page_count_nhentai", ButtonStyle.PRIMARY, (k+1)+"/"+pages.size(), true),
                                       Button.create("next_page_nhentai", ButtonStyle.PRIMARY, "Next Page")
                               ).applyChanges();
                           } else if (customID.equalsIgnoreCase("prev_page_nhentai")){
                               i.get().getAndDecrement();
                               int k = i.get().get();
                               message.edit(pages.get(i.get().get()).getImage().getDownloadUrl());
                               message.createUpdater()
                                       .addActionRow(
                                               Button.create("prev_page_nhentai", ButtonStyle.PRIMARY, "Prev Page"),
                                               Button.create("page_count_nhentai", ButtonStyle.PRIMARY, (k+1)+"/"+pages.size(), true),
                                               Button.create("next_page_nhentai", ButtonStyle.PRIMARY, "Next Page")
                                       ).applyChanges();
                           }
                        });
                    });


//                message.addReaction("\u2B05\uFE0F");
//                message.addReaction("\u27A1\uFE0F");
//
//
//                // Attach a listener directly to the message
//
//                message.addReactionAddListener(reactionEvent -> {
//                    int count = 0;
//                        if (reactionEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")) {
//                            i.get().getAndIncrement();
////                            reactionEvent.editMessage("Counting -- " + i);
//
//                            int pageno = i.get().get();
//                            reactionEvent.editMessage("*Page: "+pageno+" out of "+ pages.size()+"* \n" +
//                                    pages.get(pageno).getImage().getDownloadUrl());
//
//                            System.out.println(reactionEvent.getCount().get());
//
//
//                        }
//
//                    }).removeAfter(30, TimeUnit.MINUTES);
//
//
//
//                message.addReactionRemoveListener(reactionremove -> {
//                   if (reactionremove.getEmoji().equalsEmoji("\u27A1\uFE0F")){
//                       int count  = reactionremove.getCount().get();
//                       if (count<2){
//                           i.get().getAndIncrement();
////                           reactionremove.editMessage("Counting -- " + i);
//                           int pageno = i.get().get();
//                           reactionremove.editMessage("*Page: "+pageno+" out of "+ pages.size()+"* \n" +
//                                   pages.get(pageno).getImage().getDownloadUrl());
//
//
//
//
//
//                       }
//                   }
//                }).removeAfter(30, TimeUnit.MINUTES);
//
//                message.addReactionAddListener(reactionEvent -> {
//                    int count = 0;
//                    if (reactionEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")) {
//                        i.get().getAndDecrement();
////                        reactionEvent.editMessage("Counting -- " + i);
//                        int pageno = i.get().get();
//                        reactionEvent.editMessage("*Page: "+pageno+" out of "+ pages.size()+"* \n" +
//                                pages.get(pageno).getImage().getDownloadUrl());
//
//
//                        System.out.println(reactionEvent.getCount().get());
//
//
//                    }
//
//                }).removeAfter(30, TimeUnit.MINUTES);
//
//                message.addReactionRemoveListener(reactionremove -> {
//                    if (reactionremove.getEmoji().equalsEmoji("\u2B05\uFE0F")){
//                        int count  = reactionremove.getCount().get();
//                        if (count<2){
//                            i.get().getAndDecrement();
////                            reactionremove.editMessage("Counting -- " + i);
//
//                            int pageno = i.get().get();
//                            reactionremove.editMessage(
//                                    pages.get(pageno).getImage().getDownloadUrl()+" \n*Page: "+pageno+" out of "+ pages.size()+"* \n");
//
//                        }
//                    }
//                }).removeAfter(120, TimeUnit.MINUTES);
//
//                message.edit("Response OK...");
//
//
//
//                });
//
//
//        }

            //hahahaha bye bye emoji now button is my best friend

    }
}


}