package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

public class Downloader extends Thread {
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final ArrayList<ProgressListener> listeners;

    public Downloader(URL url, String outputFileName) throws IOException {
        this.inputStream = url.openConnection().getInputStream();
        this.outputStream = new FileOutputStream(outputFileName);
        listeners = new ArrayList<>();
    }

    public synchronized void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private synchronized void updateProgress(int progress) {
        ArrayList<ProgressListener> copy;

        synchronized (this) {
            copy = (ArrayList<ProgressListener>) listeners.clone();
        }
        for (ProgressListener listener : copy) {
            listener.onProgress(progress); // 외부의 메서드를 호출 중
        }
    }

    public void run() {
        int n = 0;
        int total = 0;

        byte[] buffer = new byte[1024];

        try {
            while((n = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n);
                total += n;
                updateProgress(total);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
