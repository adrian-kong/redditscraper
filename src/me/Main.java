package me;

import me.scraper.Scraper;

import java.io.File;
import java.util.Scanner;

/**
 * subreddit scraper using gson
 *
 * @author Adrian
 * @since 17/04/2018
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the subreddit! r/");
        String subreddit = scanner.nextLine();

        File savePath = new File(subreddit);
        savePath.mkdir();

        new Scraper(subreddit).start();
    }
}
