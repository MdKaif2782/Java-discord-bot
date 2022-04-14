package Commands.APIcommands.nhentai;
import net.beardbot.nhentai.NHentai;
import net.beardbot.nhentai.api.Gallery;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class nQuery implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!nhsearch")){
            StringBuilder mssg= new StringBuilder(msg[1] + " ");
            for (int i=2;i< msg.length;i++){
                mssg.append(msg[i]).append(" ");
            }
            System.out.println(mssg);


            String message = mssg.toString();
            var nHentai = new NHentai();
            AtomicInteger pageNo = new AtomicInteger(1);
            var result = nHentai.galleries().search(message);
            List<Gallery> galleries = result.getGalleriesOnPage(pageNo.get());


            System.out.println(galleries.size()+" results found");
            if (galleries.isEmpty()){
                System.out.println("no result found");
                event.getChannel().sendMessage("No result found");
            }

            int num = 0;
            AtomicReference<AtomicInteger> j = new AtomicReference<>(new AtomicInteger());
            j.get().set(0);
            StringBuilder tggg= new StringBuilder(galleries.get(num).getTags().get(0).getName() + ", ");
            for (int i=0;i<galleries.get(num).getTags().size();i++){
                tggg.append(galleries.get(num).getTags().get(i).getName()).append(", ");
            }

            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setTitle(galleries.get(0).getEnglishTitle())
                    .setThumbnail(galleries.get(0).getThumbnail().getDownloadUrl())
                    .addField("Code","**"+galleries.get(0).getId()+"**")
                    .addField("Tags", tggg.toString())
                    .addField("Number of pages", String.valueOf(galleries.get(0).getPages().size()))
                    .addField("Favorites", String.valueOf(galleries.get(0).getNumberOfFavorites()))
                    .addField("Uploaded",galleries.get(0).getUploadDate().toString())
                    .setColor(Color.BLACK)).send(event.getChannel()).thenAccept(message1 -> {
                message1.addReaction("\u23EA");
                message1.addReaction("\u2B05\uFE0F");
                message1.addReaction("\u27A1\uFE0F");
                message1.addReaction("\u23E9");



                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                        j.get().getAndIncrement();
                        int k=j.get().get();

                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                        .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));
                        if ((k+1)%25==0 && k!=0){
                            pageNo.getAndIncrement();
                             galleries.addAll(nHentai.galleries().search(message).getGalleriesOnPage(pageNo.get()));
                        }

                    }
                });

                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                        j.get().getAndIncrement();
                        int k=j.get().get();
                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));
                        if ((k+1)%25==0 && k!=0){
                            pageNo.getAndIncrement();
                            galleries.addAll(nHentai.galleries().search(message).getGalleriesOnPage(pageNo.get()));
                        }
                    }
                });
                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")){
                        j.get().getAndDecrement();
                        int k=j.get().get();
                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));
                    }
                });
                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")){
                        j.get().getAndDecrement();
                        int k=j.get().get();
                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));
                    }
                });
                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u23E9") && !(reactionAddEvent.getUserId() == 924608474409742357L)){
                        System.out.println(reactionAddEvent.getUserId());
                        j.get().set(j.get().intValue()+25);
                        int k=j.get().get();
                        pageNo.getAndIncrement();
                        galleries.addAll(nHentai.galleries().search(message).getGalleriesOnPage(pageNo.get()));

                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));

                    }
                });
                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u23E9") ){
                        j.get().set(j.get().intValue()+25);
                        int k=j.get().get();
                        pageNo.getAndIncrement();
                        galleries.addAll(nHentai.galleries().search(message).getGalleriesOnPage(pageNo.get()));

                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));


                    }
                });
                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u23EA") ){
                        j.get().set(j.get().intValue()-25);
                        int k=j.get().get();
                        pageNo.getAndDecrement();
                        galleries.addAll(nHentai.galleries().search(message).getGalleriesOnPage(pageNo.get()));

                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));


                    }
                });
                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u23EA") && !(reactionAddEvent.getUserId() == 924608474409742357L)){
                        j.get().set(j.get().intValue()-25);
                        int k=j.get().get();
                        pageNo.getAndDecrement();
                        galleries.addAll(nHentai.galleries().search(message).getGalleriesOnPage(pageNo.get()));

                        StringBuilder tago= new StringBuilder(galleries.get(k).getTags().get(0).getName() + ", ");
                        for (int i = 0; i< galleries.get(k).getTags().size(); i++){
                            tago.append(galleries.get(k).getTags().get(i).getName()).append(", ");
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(galleries.get(k).getEnglishTitle())
                                .setThumbnail(galleries.get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+galleries.get(k).getId()+"**")
                                .addField("Tags", tago.toString())
                                .addField("Number of pages", String.valueOf(galleries.get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(galleries.get(k).getNumberOfFavorites()))
                                .addField("Uploaded",galleries.get(k).getUploadDate().toString())
                                .setFooter("Result no: "+(k+1))
                                .setColor(Color.BLACK));


                    }
                });

            });


        }
    }
}