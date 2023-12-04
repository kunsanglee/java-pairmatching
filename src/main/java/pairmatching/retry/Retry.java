package pairmatching.retry;

import java.util.function.Supplier;

public class Retry {

    public static <T> T retryGetInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
