package ro.wantsome.layered.service.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AsyncService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Async
    public Future<String> asyncMethodWithReturnType(final String text) {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            return new AsyncResult<>(text);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return new AsyncResult<>("");
    }

    @Async
    public Future<Integer> asyncCalculateSquareOfANumber(final Integer number) {
        System.out.println("Calculate async square of a number - " + Thread.currentThread().getName());
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return number * number;
        });
    }

    @Async
    public Future<Double> calculateComplexMathEquation(final int topLimit) throws InterruptedException {
        System.out.println("Starting to calculate complex equation for limit: " + topLimit + " - "
                + Thread.currentThread().getName());

        Thread.sleep(300);
        double max = 0;
        for(double x = -3; x <= topLimit; x += .5) {
            double top = (9 * Math.pow(x, 3)) - (27 * Math.pow(x, 2)) - (4 * x) + 12;
            double bottom = (Math.pow((3 * Math.pow(x, 2) + 1), 0.5) + Math.abs(5 - (Math.pow(x, 4))));
            double y = top / bottom;
            System.out.print("X = " + x + "\t Y = " + y + "\n");
            max = Math.max(y, max);
        }
        return new AsyncResult<>(max);
    }

    @Async
    public ListenableFuture<String> asyncMethodWithListenableFuture(final String text) {
        System.out.println("Async processing with listenable future - " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            return new AsyncResult<>(text);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return new AsyncResult<>("");
    }

    @Async
    public Future<Integer> calculateSquareCompletableFuture(final Integer number) throws InterruptedException {
        System.out.println("Calculate completable future square of a number - " + Thread.currentThread().getName());
        CompletableFuture<Integer> result = new CompletableFuture<>();
        Thread.sleep(1000);
        result.complete(number*number);
        return result;
    }

    @Async
    public CompletableFuture<String> getTextFromCompletableFuture(final String text) {
        System.out.println("Async processing with CompletableFuture for text - " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(text);
    }

    @Async
    public CompletableFuture<String> getHelloWorldMessageCustom(final String name) {
        System.out.println("Async processing with CompletableFuture for HelloWorldMessage - " + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> "Hello World, " + name + "!");
    }

    @Async
    public CompletableFuture<String> processingResultsWithReturnValue(final String name) {
        //obtain a return value
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello ");
        CompletableFuture<String> future = completableFuture.thenApply(text -> text + name + "!");
        return future;
    }


    @Async
    public CompletableFuture<Void> processingResultsWithoutReturnValue(final String name) {
        //does not have a return value down the chain
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello ");

        return completableFuture.thenAccept(text -> System.out.println("Computation returned: " + text + name +"!"));
    }

    @Async
    public CompletableFuture<Void> justProcessing(final String name) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello ");
        return completableFuture.thenRun(() -> System.out.println("Computation finished, " + name +"!" ));
    }

    @Async
    public void combiningFutures(final String name) throws ExecutionException, InterruptedException {
        //receives a function that return another object of the same type
        System.out.println("----------------ThenCompose example-------------------");
        CompletableFuture<String> completableFuture1 =  CompletableFuture.supplyAsync(() -> "Welcome to Wantsome1, ")
                .thenCompose(message -> CompletableFuture.supplyAsync(() -> message + name));
        System.out.println(completableFuture1.get());


        System.out.println("----------------ThenCombine example-------------------");
        //combine 2 independent futures and do something with their results
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Welcome to Wantsome2")
                .thenCombine(CompletableFuture.supplyAsync(() -> ", " + name ), (message1, message2) -> message1 + message2);
        System.out.println(completableFuture2.get());

        System.out.println("----------------ThenAcceptBoth example-------------------");
        CompletableFuture<Void> completableFuture3 = CompletableFuture.supplyAsync(() -> "Welcome to Wantsome3")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> ", " + name),
                        (message1, message2) -> System.out.println(message1 + message2));
        completableFuture3.get();
    }

    @Async
    public void runningMultipleFutures() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "1");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "2");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "3");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3); //allows to wait for completion of all futures

        System.out.println("Are all futures done? " + "\n" + future1.isDone() + " " + future2.isDone() + " "
                + future3.isDone());

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(", ")); //we can pass a delimiter to this method like " "

        System.out.println("Result as string: " + combined);
    }


    @Async
    public CompletableFuture<String> handleExceptionForName() throws ExecutionException, InterruptedException {
        String name = null;
//        String name = "Adrian";

        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Name is null!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        return completableFuture;
    }

    @Async
    public CompletableFuture<String> throwExceptionForName(final String name) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        if (name == null || name.isEmpty() || "EMPTY".equals(name)) {
            completableFuture.completeExceptionally(new RuntimeException("Name is not valid: " + name));
        }

        completableFuture.complete(name);

        return completableFuture;
    }
}
