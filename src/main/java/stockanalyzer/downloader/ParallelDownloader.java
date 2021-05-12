package stockanalyzer.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) {

        long timer = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Future<String>> futureList = new ArrayList<>();

        for (String ticker : tickers) {

            Callable<String> request = () -> saveJson2File(ticker);

            Future<String> someFile = executor.submit(request);

            futureList.add(someFile);

        }

        for (Future<String> future : futureList ){
            try {
                String someFile = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Parallel Time: " + (System.currentTimeMillis() - timer));
        return futureList.size();

    }
}