package selcore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class WDriver {

	public static void main(String[] args) {

		WDriver wd = new WDriver();

	}

	public WDriver() {
		this.driver = openBrowser();
	}

	public final WebDriver driver;

	public List<String> elementsAttributes(WebDriver driver, String selector, String attr) {
		List<String> attrs = new ArrayList<String>();
		for (WebElement we : elements(selector)) {
			String _attr = we.getAttribute("href");
			if (_attr != null)
				attrs.add(_attr);
		}
		return attrs;
	}

	public List<String> elementsAttributes_xpath(WebDriver driver, String xpath, String attr) {
		List<String> attrs = new ArrayList<String>();
		for (WebElement we : elements_xpath(xpath)) {
			String _attr = we.getAttribute("href");
			if (_attr != null)
				attrs.add(_attr);
		}
		return attrs;
	}

	public List<WebElement> elements(String selector) {
		return driver.findElements(By.cssSelector(selector));
	}

	public List<WebElement> elements_xpath(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public WebElement element(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	public String getSelectedText(Select select) {
		return select.getFirstSelectedOption().getText();
	}

	public List<String> getListOptionText(Select options) {
		List<String> rooms = new ArrayList<String>();
		for (WebElement el : options.getOptions()) {
			String _el = el.getAttribute("disabled");
			// p("check-"+el.getText());
			// p("check-dis-"+_el);

			if (_el == null || _el.trim().equals("disabled"))
				rooms.add(el.getText());
			else
				p("not-" + el.getText());

		}
		return rooms;
	}

	public static void p(List<String> rooms) {
		for (String r : rooms)
			p(r);
	}

	public static void p(String text) {
		System.out.println(text);
	}

	public static void pw(String text) {
		System.out.print(text);
	}

	public Select select(String selector) {
		return new Select(driver.findElement(By.cssSelector(selector)));
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object js(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}

	public void open(String url) {
		driver.get(url);
	}

	public WebDriver openBrowser() {
		return openBrowser(null, 30);
	}

	public WebDriver openBrowser(String proxy, int maxTimeSecToWork) {
		WebDriver _driver = null;
		if (driver == null) {
			_driver = (proxy == null) ? new FirefoxDriver() : newProxyDriver(proxy);
			if (maxTimeSecToWork < 0)
				maxTimeSecToWork = 30;
			_driver.manage().timeouts().implicitlyWait(maxTimeSecToWork, TimeUnit.SECONDS);
			return _driver;
		}
		return driver;
	}

	private WebDriver newProxyDriver(String proxyIPwithPort) {
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(proxyIPwithPort);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		WebDriver driver = new FirefoxDriver(capabilities);
		return driver;
	}

	public void quit() {
		driver.quit();
	}

}