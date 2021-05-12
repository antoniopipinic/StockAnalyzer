package stockanalyzer.downloader;

import yahooApi.YahooFinanceException;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) throws YahooFinanceException {
        long timer = System.currentTimeMillis();

        int count = 0;
        for (String ticker : tickers) {
            String fileName = saveJson2File(ticker);
            if(fileName != null)
                count++;
        }

        System.out.println("Sequential Timer: " + (System.currentTimeMillis() - timer));
        return count;

    }
}