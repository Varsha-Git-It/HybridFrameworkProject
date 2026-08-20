package org.example.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.utils.ExtentManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Suite started: " + context.getName());

        String browser = context.getCurrentXmlTest().getParameter("browser");
        ExtentManager.setBrowserInfo(browser);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // include parameter values, if any, in the report entry name
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            testName = testName + " " + Arrays.toString(params);
        }

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, result.getThrowable());

        String screenshotPath = captureScreenshot(result);
        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Suite finished: " + context.getName());
    }

    private String captureScreenshot(ITestResult result) {
        WebDriver driver = getDriverFromTestClass(result);
        if (driver == null) {
            System.out.println("Could not find driver instance to capture screenshot");
            return null;
        }

        try {
            File src = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = System.getProperty("user.dir") + "/test-output/screenshots/"
                    + result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
            File dest = new File(destPath);

            dest.getParentFile().mkdirs();   // <-- creates test-output/screenshots/ if missing

            FileHandler.copy(src, dest);

            return "screenshots/" + dest.getName();
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }

    // Walks up the class hierarchy (test class -> BaseTest -> ...) to find the driver field,
    // since it's declared in BaseTest, not the individual test classes.
    private WebDriver getDriverFromTestClass(ITestResult result) {
        try {
            Class<?> clazz = result.getTestClass().getRealClass();
            Field driverField = null;

            while (clazz != null && driverField == null) {
                try {
                    driverField = clazz.getDeclaredField("driver");
                } catch (NoSuchFieldException e) {
                    clazz = clazz.getSuperclass();
                }
            }

            if (driverField == null) {
                System.out.println("driver field not found in class hierarchy");
                return null;
            }

            driverField.setAccessible(true);
            return (WebDriver) driverField.get(result.getInstance());
        } catch (Exception e) {
            System.out.println("Reflection failed to get driver field: " + e.getMessage());
            return null;
        }
    }
}