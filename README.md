# subreddit-scraper
Uses GSON (https://github.com/google/gson) and reddit api. Loops until after=null (Meaning it can't look any further back)

Usage

```
java -jar subredditscraper.jar subreddit
```

Looks into /r/subreddit/.json?limit=100&after= "after"

Limit is max to get the most results in least http requests. "after" will return null when the page can't go any further back.
This scraper should work for every subreddit
Tested on r/memes, r/dankmemes, r/wallpaper
