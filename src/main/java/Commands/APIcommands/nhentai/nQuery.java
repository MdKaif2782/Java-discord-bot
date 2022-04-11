package Commands.APIcommands.nhentai;

import net.beardbot.nhentai.NHentai;
import net.beardbot.nhentai.api.GalleryPage;
import net.beardbot.nhentai.api.SearchResult;
import net.beardbot.nhentai.api.params.Language;
import net.beardbot.nhentai.api.params.Query;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class nQuery implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!nhsearch")){
            String mssg=msg[1]+" ";
            for (int i=2;i< msg.length;i++){
                mssg = mssg+msg[i]+" ";
            }
            System.out.println(mssg);


            String message = mssg;
            String tittle=null;
            String tags = null;

            if (message.contains("title:")){
                String title;
                String[] titlesplit = message.split("title");
                System.out.println(titlesplit[1]);
                String[] titleSplit2 = titlesplit[1].split(":");
                String titleSplit3[] = titleSplit2[1].split(" ");

                title=titleSplit3[0]+" ";
                int times = titleSplit3.length-1;
                if (titleSplit3[titleSplit3.length-1].equalsIgnoreCase("tag")){
                    for (int i=1;i<times;i++){
                        title=title+titleSplit3[i]+" ";
                    }
                }else {
                    for (int i=1;i<times+1;i++){
                        title=title+titleSplit3[i]+" ";
                    }
                }
                tittle = title;

                System.out.println(title);
            }

            if (message.contains("tag:")){
                String title;
                String[] titlesplit = message.split("tag");
                System.out.println(titlesplit[1]);
                String[] titleSplit2 = titlesplit[1].split(":");
                String titleSplit3[] = titleSplit2[1].split(" ");
                System.out.println(titleSplit3);
                title=titleSplit3[0]+" ";
                int times = titleSplit3.length-1;
                if (titleSplit3[titleSplit3.length-1].equalsIgnoreCase("tag")
                        || titleSplit3[titleSplit3.length-1].equalsIgnoreCase("title")){
                    for (int i=1;i<times;i++){
                        title=title+titleSplit3[i]+" ";
                    }
                }else {
                    for (int i=1;i<times+1;i++){
                        title=title+titleSplit3[i]+" ";
                    }
                }
                tags=title;
                System.out.println(title);
            }
            String[] tagsArray10 = new String[10];
            if (tags!=null) {
                String tagsArray[] = tags.split(" ");

                for (int i = 0; i < tagsArray.length; i++) {
                    tagsArray10[i] = tagsArray[i];
                    System.out.println(tagsArray10[i]);
                }
                for (int i = tagsArray.length - 1; i < 10; i++) {
                    tagsArray10[i] = tagsArray[0];
                    System.out.println(tagsArray10[i]);
                }
            }

            var nHentai = new NHentai();


            var gallery = nHentai.galleries().getGallery(177013);

            List<GalleryPage> pages = gallery.get().getPages();


            var query = Query.builder()
                    .withTag(tagsArray10[0])
                    .withTag(tagsArray10[1])
                    .withTag(tagsArray10[2])
                    .withTag(tagsArray10[3])
                    .withTag(tagsArray10[4])
                    .withTag(tagsArray10[5])
                    .withTag(tagsArray10[6])
                    .withTag(tagsArray10[7])
                    .withTag(tagsArray10[8])
                    .withTag(tagsArray10[9])
                    .withKeyword(tittle)
                    .withLanguage(Language.ENGLISH)
                    .build();

            var query2 = Query.builder()
                    .withKeyword(tittle)
                    .withLanguage(Language.ENGLISH)
                    .build();

            var query3 = Query.builder()
                    .withTag(tagsArray10[0])
                    .withTag(tagsArray10[1])
                    .withTag(tagsArray10[2])
                    .withTag(tagsArray10[3])
                    .withTag(tagsArray10[4])
                    .withTag(tagsArray10[5])
                    .withTag(tagsArray10[6])
                    .withTag(tagsArray10[7])
                    .withTag(tagsArray10[8])
                    .withTag(tagsArray10[9])
                    .withLanguage(Language.ENGLISH)
                    .build();



            SearchResult result;
            result= null;
            if (tags!=null && tittle!=null){
                result=nHentai.galleries().search(query);
            } else if(tags==null){
                result=nHentai.galleries().search(query2);
            }else if(!message.contains("title")){
                result=nHentai.galleries().search(query3);
            }

            ArrayList<String> Title = new ArrayList<>();
            ArrayList<String> Codes = new ArrayList<>();
            for (int i=0; i<result.getGalleries().size();i++) {
                Title.add(result.getGalleries().get(i).getEnglishTitle());
                Codes.add(String.valueOf(result.getGalleries().get(i).getId()));

                System.out.println(result.getGalleries().get(i).getEnglishTitle() + "\n" +
                        result.getGalleries().get(i).getId() + "\n");

            }
            System.out.println(result.getGalleries().size()+" results found");
            if (result.getGalleries().isEmpty()){
                System.out.println("no result found");
            }

            int num = 0;
            AtomicReference<AtomicInteger> j = new AtomicReference<>(new AtomicInteger());
            String tggg= result.getGalleries().get(num).getTags().get(0).getName()+", ";
            for (int i=0;i<result.getGalleries().get(num).getTags().size();i++){
                tggg=tggg+result.getGalleries().get(num).getTags().get(i).getName()+", ";
            }
            String finalTggg = tggg;
            SearchResult finalResult = result;
            SearchResult finalResult1 = result;
            SearchResult finalResult2 = result;
            SearchResult finalResult3 = result;
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                            .setTitle(result.getGalleries().get(num).getEnglishTitle())
                            .setThumbnail(result.getGalleries().get(num).getThumbnail().getDownloadUrl())
                            .addField("Code","**"+Codes.get(num)+"**")
                            .addField("Tags",tggg)
                            .addField("Number of pages", String.valueOf(result.getGalleries().get(num).getPages().size()))
                            .addField("Favorites", String.valueOf(result.getGalleries().get(num).getNumberOfFavorites()))
                            .addField("Uploaded",result.getGalleries().get(num).getUploadDate().toString())
                    .setColor(Color.BLACK)).send(event.getChannel()).thenAccept(message1 -> {
                        message1.addReaction("\u2B05\uFE0F");
                        message1.addReaction("\u27A1\uFE0F");



                        message1.addReactionAddListener(reactionAddEvent -> {
                           if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                               j.get().getAndIncrement();
                               int k=j.get().get();
                               String tago= finalResult.getGalleries().get(k).getTags().get(0).getName()+", ";
                               for (int i = 0; i< finalResult.getGalleries().get(k).getTags().size(); i++){
                                   tago=tago+ finalResult.getGalleries().get(k).getTags().get(i).getName()+", ";
                               }
                               message1.edit(new EmbedBuilder()
                                       .setTitle(finalResult.getGalleries().get(k).getEnglishTitle())
                                       .setThumbnail(finalResult.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                       .addField("Code","**"+Codes.get(k)+"**")
                                       .addField("Tags", tago)
                                       .addField("Number of pages", String.valueOf(finalResult.getGalleries().get(k).getPages().size()))
                                       .addField("Favorites", String.valueOf(finalResult.getGalleries().get(k).getNumberOfFavorites()))
                                       .addField("Uploaded", finalResult.getGalleries().get(k).getUploadDate().toString())
                                       .setColor(Color.BLACK));
                           }
                        });

                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                        j.get().getAndIncrement();
                        int k=j.get().get();
                        String tago= finalResult1.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult1.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult1.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult1.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult1.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult1.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult1.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult1.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });
                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")){
                        j.get().getAndDecrement();
                        int k=j.get().get();
                        String tago= finalResult2.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult2.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult2.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult2.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult2.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult2.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult2.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult2.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });
                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")){
                        j.get().getAndDecrement();
                        int k=j.get().get();
                        String tago= finalResult3.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult3.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult3.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult3.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult3.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult3.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult3.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult3.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });
            });


        }
    }
}
