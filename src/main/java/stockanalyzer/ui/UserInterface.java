package stockanalyzer.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.downloader.Downloader;
import stockanalyzer.downloader.ParallelDownloader;
import stockanalyzer.downloader.SequentialDownloader;
import yahooApi.YahooFinanceException;

public class UserInterface
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		try {
			ctrl.process("AAPL");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl2(){
		try {
			ctrl.process("AMZN");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl3() {
		try {
			ctrl.process("FB");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl4() {
		try {
			ctrl.process("EBAY");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl5() {
		try {
			ctrl.process("KO");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}
	public void getDownloadedTickersSequential() {
		try {
			Downloader downloader;
			List<String> tickers = new ArrayList<>();
			tickers.add("AAPL");
			tickers.add("AMZN");
			tickers.add("FB");
			tickers.add("EBAY");
			tickers.add("KO");

			SequentialDownloader sequentialDownloader = new SequentialDownloader();

			ctrl.downloadTickers(tickers, sequentialDownloader);

		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDownloadedTickersParallel() {
		try {
			Downloader downloader;
			List<String> tickers = new ArrayList<>();
			tickers.add("AAPL");
			tickers.add("AMZN");
			tickers.add("FB");
			tickers.add("EBAY");
			tickers.add("KO");
			tickers.add("TSLA");
			tickers.add("TME");
			tickers.add("PLUG");
			tickers.add("NVDA");


			ParallelDownloader parallelDownloader = new ParallelDownloader();

			ctrl.downloadTickers(tickers, parallelDownloader);

		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}
	public void start() throws YahooFinanceException {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Apple Inc. (AAPL)", this::getDataFromCtrl1);
		menu.insert("b", "Amazon", this::getDataFromCtrl2);
		menu.insert("c", "Facebook Inc.", this::getDataFromCtrl3);
		menu.insert("d", "eBay Inc.", this::getDataFromCtrl4);
		menu.insert("e", "The Coca-Cola Company", this::getDataFromCtrl5);
		menu.insert("q", "Quit", null);
		menu.insert("m", "Sequential Download", this::getDownloadedTickersSequential);
		menu.insert("n", "Parallel Download", this::getDownloadedTickersParallel);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine()
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit)
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}