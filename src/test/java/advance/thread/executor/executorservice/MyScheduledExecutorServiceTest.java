package advance.thread.executor.executorservice;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/** 
* MyScheduledExecutorService Tester. 
* 
* @author <Authors name> 
* @since <pre>11月 21, 2019</pre> 
* @version 1.0 
*/ 
public class MyScheduledExecutorServiceTest {




@Before
public void before() throws Exception {
//    scheduler = Executors.newScheduledThreadPool(1);
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: beepDelay(ScheduledExecutorService scheduler, long delay, TimeUnit timeUnit) 
* 
*/ 
@Test
public void testBeepDelay() throws Exception { 
//TODO: Test goes here...
//    myScheduledExecutorService.beepDelay(scheduler, 1, TimeUnit.SECONDS);
} 


/** 
* 
* Method: beepForAWhile(ScheduledExecutorService scheduler)
* 
*/ 
@Test
public void testBeepForAnHour() throws Exception {
    MyScheduledExecutorService myScheduledExecutorService = new MyScheduledExecutorService();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    myScheduledExecutorService.beepForAWhile();
} 


} 
