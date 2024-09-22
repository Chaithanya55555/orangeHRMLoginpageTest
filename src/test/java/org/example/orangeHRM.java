package org.example;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class orangeHRM implements ITestListener {
    @Override
    public void  onTestStart(ITestResult e){
        System.out.println("Test started : " + e);
    }
    @Override
    public void  onTestSuccess(ITestResult e){
        System.out.println("Test success : " + e);
    }
    @Override
    public void  onTestFailure(ITestResult e){
        System.out.println("Test fail : " + e);
    }
    @Override
    public void  onTestSkipped(ITestResult e){
        System.out.println("Test skip : " + e);
    }
    @Override
    public void  onStart(ITestContext e){
        System.out.println("Test start ");
    }
    @Override
    public void  onFinish(ITestContext e){
        System.out.println("Test  : " + e);
    }

}
