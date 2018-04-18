package me.scraper;

import com.google.gson.Gson;
import me.utils.ConnectUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Scraper {

    private final Gson gson = new Gson();

    private Map<String, String> memeData = new HashMap();
    private Response response;

    private String subreddit;
    private String url;
    private File savePath;

    public Scraper(String subreddit) {
        this.subreddit = subreddit;
        this.url = "https://www.reddit.com/r/" + subreddit + "/.json?limit=100";
        this.savePath = new File(subreddit);
    }

    public void start() throws Exception {
        System.out.println("Connected to " + url);
        response = gson.fromJson(ConnectUtils.readURLToString(url), Response.class);

        Response.Children[] children = response.getChildren();
        for (int i = 0; i < children.length; i++) {
            Response.ChildrenData childrenData = children[i].childrenData;
            if (childrenData.preview != null)
                for (int j = 0; j < childrenData.preview.images.length; j++) {
                    String title = childrenData.title.replaceAll("[^a-zA-Z0-9 ]+", "");
                    if (childrenData.preview.images[j].variants.gif != null) {
                        memeData.put(title + ".gif", childrenData.preview.images[j].variants.gif.getURL());
                        continue;
                    }
                    memeData.put(title + ".jpg", childrenData.preview.images[j].getURL());
                    System.out.println("Found " + title + " {" + memeData.get(title) + "}");
                }
        }
        /**
         * need to be put in a thread
         * more efficient when multithreaded
         */
        for (String img : memeData.keySet()) {
            File imgPath = new File(savePath, img);
            if (imgPath.exists()) {
                System.out.println(img + " already exists!");
                continue;
            }
            InputStream in = new URL(memeData.get(img)).openStream();
            Files.copy(in, imgPath.toPath());
            System.out.println("Saving " + img);
        }
        if (getAfter() != null && !getAfter().equalsIgnoreCase("null")) {
            url = "https://www.reddit.com/r/" + subreddit + "/.json?limit=100&after=" + getAfter();
            start();
        }
    }

    public String getAfter() {
        return response.page.after;
    }
}
