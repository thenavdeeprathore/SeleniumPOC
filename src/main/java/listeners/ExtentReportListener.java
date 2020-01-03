package listeners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import logger.Log;
import org.apache.commons.lang3.time.StopWatch;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.Constants;
import utilities.CommonUtils;

public class ExtentReportListener extends CommonUtils implements ITestListener, IInvokedMethodListener {

    StopWatch watch = new StopWatch();

    public synchronized void onStart(ITestContext context) {
        String suiteName = context.getSuite().getName();
        System.out.println("[SUITE]<<<<<----------- Suite Name: "+suiteName+" ----------->>>>>");
    }

    public synchronized void onFinish(ITestContext context) {
        String suiteName = context.getSuite().getName();
        System.out.println("[SUITE]<<<<<----------- Test "+suiteName+" Ends !!! ----------->>>>>");

        Constants.extent.flush();
//		extent.removeTest(test);
        Constants.test.remove();
    }

    public synchronized void onTestStart(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();

        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);
        System.out.println("[TEST] <=========== "+methodName +" STARTED ===========>");
        ExtentTest extentTest = Constants.extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        System.out.println(("Test Description: "+result.getMethod().getDescription()));
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        Constants.test.set(extentTest);
        Constants.test.get().getModel().setStartTime(getTime(result.getStartMillis()));

    }

    public synchronized void onTestSuccess(ITestResult result) {
        Log.PASS(result.getMethod().getMethodName() +" PASSED ===========>");
        Constants.test.get().log(Status.INFO,
                MarkupHelper.createLabel("Test Description: "+result.getMethod().getDescription(), ExtentColor.WHITE));
        Constants.test.get().pass("Test passed");
        Constants.test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result) {
        Log.FAIL("Test: "+ result.getMethod().getMethodName() +" FAILED ===========>");
        try {
            Constants.test.get().log(Status.INFO,
                    MarkupHelper.createLabel("Test Description: "+result.getMethod().getDescription(), ExtentColor.WHITE));
            Constants.test.get().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        } catch (IOException e) {
            System.err
                    .println("Exception thrown while updating test fail status " + Arrays.toString(e.getStackTrace()));
        }
        Constants.test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        try {
            Constants.test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
            Log.INFO("Error: "+result.getThrowable());
        } catch (Exception e) {
            System.err.println("Exception thrown while updating test skip status " + Arrays.toString(e.getStackTrace()));
            Log.INFO("Skip Exception thrown: "+Arrays.toString(e.getStackTrace()));
        }
        Constants.test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        watch.start();
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        double seconds = (double)watch.getTime() / 1000.0;
        watch.reset();
        System.out.println("*********************************************************");
        System.out.println("Time taken to exeute this method was: " + seconds + " seconds");
        System.out.println("*********************************************************");
    }

}
