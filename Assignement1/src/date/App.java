package date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class App {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(OOSCDateTimeTest.class, MonthTest.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests finished successfully.");
        } else {
            System.out.println("Some tests have failed.");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }

	}

}
