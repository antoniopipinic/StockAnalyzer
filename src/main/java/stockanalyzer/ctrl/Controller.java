package stockanalyzer.ctrl;

import yahooApi.YahooFinanceException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;

public class Controller {

	Stock stock = null;

	public void process(String ticker) throws YahooFinanceException {

		try {
			stock = YahooFinance.get(ticker);

			Calendar from = Calendar.getInstance();
			from.add(Calendar.WEEK_OF_MONTH, -20); // last 8 weeks

			var result = stock.getHistory(from, Interval.DAILY).stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.max()
					.orElse(0.0);

			var result2 = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.average()
					.orElse(0.0);


			var result3 = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.count();


			//stock.getHistory().forEach(q -> System.out.println(q));

			System.out.println();
			System.out.println(ticker);
			System.out.println();

			System.out.println("--------- Max Price ---------");
			System.out.println(result);

			System.out.println();
			System.out.println("--------- Average Price ---------");
			System.out.println(result2);

			System.out.println();
			System.out.println("--------- Amount ---------");
			System.out.println(result3);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}