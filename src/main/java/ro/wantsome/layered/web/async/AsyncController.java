package ro.wantsome.layered.web.async;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.wantsome.layered.service.async.AsyncService;

import java.util.concurrent.*;

@RestController
@RequestMapping("/async")
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/future/{text}")
    public String getTextValueFromString(@PathVariable final String text) throws ExecutionException, InterruptedException {
        System.out.println("Get text value from future. Thread: " + Thread.currentThread().getName());
        Future<String> futureResult = asyncService.asyncMethodWithReturnType(text);

//        boolean cancelResult = futureResult.cancel(true);
//
//        if(cancelResult) {
//            System.out.println("Process canceled!");
//        }

        while(true) {
            if(futureResult.isDone()) {
                System.out.println("Result fetched form asynchronously process - " + futureResult.get());
                break;
            }
            System.out.println("Still calculating...");
            Thread.sleep(1000);
        }
        return "Call finished - check apps logs!";
    }

    @GetMapping("/calculateSquare/{number}")
    public String calculateSquareAsync(@PathVariable final Integer number) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("Starting to calculate square - " + Thread.currentThread().getName());
        Future<Integer> futureSquare = asyncService.asyncCalculateSquareOfANumber(number);

        while(!futureSquare.isDone()) {
            System.out.println("Still calculating...");
            Thread.sleep(500);
        }
        Integer square = futureSquare.get();
        System.out.println("Calculated square: " + square);

        System.out.println("--------------------------------------" + "\n" + "Initial thread pool is singleThreaded - "
                + Thread.currentThread().getName());
        Future<Double> singleThreadFuture1 = asyncService.calculateComplexMathEquation(4);
        Future<Double> singleThreadFuture2 = asyncService.calculateComplexMathEquation(10);

        while (!(singleThreadFuture1.isDone() && singleThreadFuture2.isDone())) {
            System.out.printf(
                    "future1 is %s and future2 is %s%n",
                    singleThreadFuture1.isDone() ? "done" : "not done",
                    singleThreadFuture2.isDone() ? "done" : "not done"
            );
            Thread.sleep(200);
        }

        Double result1 = singleThreadFuture1.get();
        Double result2 = singleThreadFuture2.get(500, TimeUnit.MILLISECONDS);

        System.out.println("\n" + result1 + " and " + result2);

        return "Process finished - check logs";
    }

    @GetMapping("/listenable/{text}")
    public String getStringFromListenableFuture(@PathVariable final String text) throws ExecutionException, InterruptedException {
        System.out.println("Get text value from listenable future. Thread: " + Thread.currentThread().getName());
        ListenableFuture<String> futureResult = asyncService.asyncMethodWithListenableFuture(text);

        futureResult.addCallback(new ListenableFutureCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.out.println("Result retrieved successfully - " + result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Process failed - " + ex.getMessage());
            }
        });
        return "Process finished - check logs";
    }

    @GetMapping("/completable/square/{number}")
    public String calculateSquareWithCompletableFuture(@PathVariable final Integer number) throws
            ExecutionException, InterruptedException {
        System.out.println("Starting to calculate square with completable future - " + Thread.currentThread().getName());
        Future<Integer> completableFuture = asyncService.calculateSquareCompletableFuture(number);

        while(!completableFuture.isDone()) {
            System.out.println("Still calculating... - " + Thread.currentThread().getName());
            Thread.sleep(500);
        }
        Integer square = completableFuture.get();
        System.out.println("Calculated square with completable future: " + square);
        return "Process finished - check logs";
    }

    @GetMapping("/completable/text/{text}")
    public String retrieveTextFromCompletableFuture(@PathVariable final String text) throws ExecutionException, InterruptedException {
        System.out.println("Get text value from completable future. Thread: " + Thread.currentThread().getName());
        CompletableFuture<String> futureString = asyncService.getTextFromCompletableFuture(text);

        return futureString.get();
    }

    @GetMapping("/completable/name/{name}")
    public String getCustomHelloWorldMessage(@PathVariable final String name) throws ExecutionException, InterruptedException {
        System.out.println("Get custom hello world message from completable future. Thread: " + Thread.currentThread().getName());
        CompletableFuture<String> futureString = asyncService.getHelloWorldMessageCustom(name);
        return futureString.get();
    }

    @GetMapping("/completable/processing/{name}")
    public String getNameAsync(@PathVariable final String name) throws ExecutionException, InterruptedException {
        System.out.println("Get hello name message in async manner with completable - " + Thread.currentThread().getName());
        CompletableFuture<String> future1 =  asyncService.processingResultsWithReturnValue(name);
        Thread.sleep(2000);
        CompletableFuture<Void> future2 = asyncService.processingResultsWithoutReturnValue(name);
        Thread.sleep(2000);
        CompletableFuture<Void> future3 = asyncService.justProcessing(name);
        Thread.sleep(2000);

        System.out.println("Future1 actual value: " + future1.get());
        future2.get();
        System.out.println("Future2 actual value: " + future2.get());
        future3.get();
        System.out.println("Future3 actual value: " + future3.get());

        return "Processing of async calls done!";
    }

    @GetMapping("/completable/combine/{name}")
    public String combineAsyncName(@PathVariable final String name) throws ExecutionException, InterruptedException {
        System.out.println("Get wantsome name message in async manner with completable - " + Thread.currentThread().getName());
        asyncService.combiningFutures(name);

        return "Combine name async done!";
    }

    @GetMapping("/completable/multiple")
    public String runningMultipleFutures() throws ExecutionException, InterruptedException {
        System.out.println("Running multiple futures...");
        asyncService.runningMultipleFutures();

        return "Running Multiple futures done!";
    }

    @GetMapping("/completable/name/handle-exception")
    public String handleNameGenerationAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = asyncService.handleExceptionForName();
        System.out.println(future.get());
        return "Handle name message generation without throwing exceptions finished";
    }

    @GetMapping("/completable/name/handle-exception-{name}")
    public String throwsExceptionNameGenerationAsync(@PathVariable final String name) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = asyncService.throwExceptionForName(name);
        System.out.println("Name is: " + future.get());
        return "Throws exception name message generation finished";
    }

}
