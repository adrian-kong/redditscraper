package me.scraper;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("data")
    public Page page;

    public class Page {
        @SerializedName("children")
        public Children[] children;

        @SerializedName("after")
        public String after;
    }

    public class Children {
        @SerializedName("data")
        public ChildrenData childrenData;
    }

    public class ChildrenData {
        @SerializedName("title")
        public String title;

        @SerializedName("preview")
        public Preview preview;
    }

    public class Preview {
        @SerializedName("images")
        public Images[] images;
    }

    public class Images {
        @SerializedName("source")
        public Source source;

        @SerializedName("variants")
        public Variants variants;

        public String getURL() {
            return source.url;
        }
    }

    public class Variants {
        @SerializedName("gif")
        public Gif gif;
    }

    public class Gif {
        @SerializedName("source")
        public Source source;

        public String getURL() {
            return source.url;
        }
    }

    public class Source {
        @SerializedName("url")
        public String url;
    }

    public Children[] getChildren() {
        return page.children;
    }
}
