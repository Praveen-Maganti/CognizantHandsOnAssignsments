public class ObserverPatternExampleMain {
    public static void main(String[] args) {
        StockMarket appleStock = new StockMarket("Apple");

        Observer mobileApp = new MobileApp("MyTrade Mobile");
        Observer webApp = new WebApp("MyTrade Web");

        appleStock.register(mobileApp);
        appleStock.register(webApp);

        System.out.println("Setting stock price to 150.0");
        appleStock.setStockPrice(150.0);

        System.out.println("\nSetting stock price to 155.0");
        appleStock.setStockPrice(155.0);

        appleStock.deregister(mobileApp);

        System.out.println("\nSetting stock price to 160.0");
        appleStock.setStockPrice(160.0);
    }
}