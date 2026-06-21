package FinancialForecasting;

public class FinancialForecasting {

    // Calculates future value recursively
    // FV = PV * (1 + r)^n
    // Which can be represented recursively as:
    // f(n) = f(n-1) * (1 + r), where f(0) = PV
    public static double calculateFutureValue(double currentValue, double growthRate, int periods) {
        // Base case: if periods is 0, the value is just the current value
        if (periods == 0) {
            return currentValue;
        }
        
        // Recursive case
        return calculateFutureValue(currentValue, growthRate, periods - 1) * (1 + growthRate);
    }

    // Optimized approach using memoization or just an iterative loop is better for large 'periods'
    public static void main(String[] args) {
        double presentValue = 1000.0;
        double growthRate = 0.05; // 5% growth rate
        int periods = 10; // 10 years

        double futureValue = calculateFutureValue(presentValue, growthRate, periods);
        System.out.printf("Future value after %d periods: %.2f\n", periods, futureValue);
    }
}
