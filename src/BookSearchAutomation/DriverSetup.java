package BookSearchAutomation;

import org.openqa.selenium.WebDriver;
import java.io.File;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.*;
public class DriverSetup {
	private static WebDriver webDriver;
	public static int driver_choice;
	public static WebDriver getWebDriver() {
		String folderPath = "C:\\Users\\2266471\\eclipse-workspace\\Book_search\\Driver";
        String ChromefileName = "chromedriver.exe";
        String FirefoxfileName = "geckodriver.exe";
        String EdgefileName = "msedgedriver.exe";
        File exe1=new File(folderPath,ChromefileName);
        File exe2=new File(folderPath,FirefoxfileName);
        File exe3=new File(folderPath,EdgefileName);
        if(exe1.exists() && exe1.isFile() && driver_choice==1) {
			System.setProperty("webdriver.chrome.driver", folderPath+"\\"+ChromefileName);
			webDriver = new ChromeDriver();
			System.out.println("--------------CHROME DRIVER------------------");
        }
        else if(exe2.exists() && exe2.isFile())
        {
        	System.setProperty("webdriver.geckodriver.driver", folderPath+"\\"+FirefoxfileName);
        	FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxProfile profile = new FirefoxProfile();
			// profile.setPreference("marionette.logging", "TRACE");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			firefoxOptions.setProfile(profile);
			webDriver = new FirefoxDriver(firefoxOptions);
			System.out.println("--------------FIREFOX DRIVER------------------");
        }
        else if(exe3.exists() && exe3.isFile() && driver_choice==2) {
			System.setProperty("webdriver.chrome.driver", folderPath+"\\"+ChromefileName);
			webDriver = new EdgeDriver();
			System.out.println("--------------EDGE DRIVER------------------");
        }
        
        else if((exe1.exists() && exe1.isFile()) && (exe2.exists() && exe2.isFile()))
        {
        	System.setProperty("webdriver.chrome.driver", folderPath+"\\"+ChromefileName);
			webDriver = new ChromeDriver();
        }
		return webDriver;
	}
}