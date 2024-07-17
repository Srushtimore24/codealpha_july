import java.util.*;

class StockMarket {
    private Map<String, List<Double>> stockData = new HashMap<>();
    private Random random = new Random();

    public StockMarket(List<String> stocks, int days) {
        for (String stock : stocks) {
            stockData.put(stock, generateStockData(days));
        }
    }

    private List<Double> generateStockData(int days) {
        List<Double> prices = new ArrayList<>();
        double price = 100 + random.nextDouble() * 400;
        for (int i = 0; i < days; i++) {
            prices.add(price);
            price += random.nextDouble() * 10 - 5;
            if (price < 0) price = 0;
        }
        return prices;
    }

    public double getPrice(String stock, int day) {
        return stockData.get(stock).get(day);
    }

    public void printStockData() {
        for (String stock : stockData.keySet()) {
            System.out.println(stock + ": " + stockData.get(stock));
        }
    }
}

class Portfolio {
    private double cash;
    private Map<String, Integer> holdings = new HashMap<>();
    private List<String> transactionHistory = new ArrayList<>();

    public Portfolio(double initialCash) {
        this.cash = initialCash;
    }

    public void buyStock(StockMarket market, String stock, int day, int shares) {
        double price = market.getPrice(stock, day);
        double totalCost = price * shares;
        if (totalCost <= cash) {
            cash -= totalCost;
            holdings.put(stock, holdings.getOrDefault(stock, 0) + shares);
            transactionHistory.add("Day " + day + ": Bought " + shares + " shares of " + stock + " at $" + price);
        } else {
            System.out.println("Not enough cash to buy " + shares + " shares of " + stock);
        }
    }

    public void sellStock(StockMarket market, String stock, int day, int shares) {
        if (holdings.getOrDefault(stock, 0) >= shares) {
            double price = market.getPrice(stock, day);
            double totalEarned = price * shares;
            cash += totalEarned;
            holdings.put(stock, holdings.get(stock) - shares);
            transactionHistory.add("Day " + day + ": Sold " + shares + " shares of " + stock + " at $" + price);
        } else {
            System.out.println("Not enough shares to sell " + shares + " shares of " + stock);
        }
    }

    public void printPortfolio() {
        System.out.println("Cash: $" + cash);
        System.out.println("Holdings: " + holdings);
        System.out.println("Transaction History: " + transactionHistory);
    }
}

public class SimulatedTradingPlatform {
    public static void main(String[] args) {
        List<String> stocks = Arrays.asList("AAPL", "GOOGL", "AMZN");
        StockMarket market = new StockMarket(stocks, 30);

        Portfolio portfolio = new Portfolio(10000);
        
        // Simulating some trades
        portfolio.buyStock(market, "AAPL", 0, 10);
        portfolio.buyStock(market, "GOOGL", 1, 5);
        portfolio.sellStock(market, "AAPL", 2, 5);
        portfolio.sellStock(market, "GOOGL", 3, 2);

        portfolio.printPortfolio();
    }
}