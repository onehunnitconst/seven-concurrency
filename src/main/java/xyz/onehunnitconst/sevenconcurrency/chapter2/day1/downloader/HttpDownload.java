package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.downloader;

import java.net.URI;
import java.net.URL;

public class HttpDownload {
    public static void main(String[] args) throws Exception {
        URL from = URI.create("http://download.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles.xml.bz2").toURL();
        Downloader downloader = new Downloader(from, "download.out");
        downloader.start();
        downloader.addListener(new ProgressListener() {
            public void onProgress(int n) { System.out.print("\r"+n); System.out.flush(); }
            public void onComplete(boolean success) {}
        });
        downloader.join();
    }
}