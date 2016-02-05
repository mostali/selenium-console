package selcore;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebElement;

public class Main {

	private JFrame jframeMAIN ;

	private JPanelStatus jpanelStatus;
	private JPanelLogin panOpenURL;
	private JPanelConsole jpanelConsole;

	private JPanelSelectors panAllSelectors;
	private WDriver __WD = null;

	private static final GPref __CONF = GPref.ini(Main.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.jframeMAIN.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	private void initialize() {

		initMainFrame();

		initStatusBar();

		initBrowser();

		{
			panOpenURL = new JPanelLogin() {
				private static final long serialVersionUID = 1L;

				@Override
				public void onAction(String command, AWTEvent arg0) {
					if (JPanelLogin.COMMAND_CLOSE.equals(command)) {
						if (__WD != null) {
							__WD.quit();
							__WD = null;
						}
					} else if (JPanelLogin.COMMAND_OPEN.equals(command)) {
						String url = panOpenURL.txURL.getText();
						initBrowser();
						__WD.open(url);

						__CONF.setDefaultLocation(url);
					} else if (JPanelLogin.COMMAND_RESTART.equals(command)) {

						// String url = panOpenURL.txURL.getText();
						__WD.open(__WD.driver.getCurrentUrl());
					}
				}

			};
			jframeMAIN.getContentPane().add(panOpenURL);
			panOpenURL.setBounds(10, 33, 432, 67);
		}
		{// ---------------------------Selectors
			panAllSelectors = new JPanelSelectors() {
				private static final long serialVersionUID = 1L;

				@Override
				public void onAction(String command, AWTEvent arg0) {
					try {
						if (JPanelSelectors.COMMAND_CSS.equals(command)) {

							String selector = super.txCssSelector.getText();
							__CONF.setDefaultCssSelector(selector);

							setStatus("find element by css selector [" + selector + "]");

							List<WebElement> elements = __WD.elements(selector);

							jpanelConsole.log("Find element by css selector [" + selector + "]");

							if (elements.isEmpty())
								jpanelConsole.log("Web element not found");
							else
								jpanelConsole.log("Find elements [" + elements.size() + "]");

						} else if (JPanelSelectors.COMMAND_XPATH.equals(command)) {
							String selector = super.txXpath.getText();
							__CONF.setDefaultXpath(selector);

							setStatus("find element by xpath  [" + selector + "]");

							List<WebElement> elements = __WD.elements_xpath(selector);

							jpanelConsole.log("Find element by xpath [" + selector + "]");

							if (elements.isEmpty())
								jpanelConsole.log("Web element not found");
							else
								jpanelConsole.log("Find elements [" + elements.size() + "]");

						} else if (JPanelSelectors.COMMAND_JAVASCRIPT.equals(command)) {
							String js = super.txJavascript.getText();
							__CONF.setDefaultJavascript(js);
							Object obj = __WD.js(js);
							jpanelConsole.log("return [" + obj + "]");

						}
					} catch (Throwable ex) {
						jpanelConsole.log(ex.getMessage());
					}
				}
			};
			jframeMAIN.getContentPane().add(panAllSelectors);
			panAllSelectors.setBounds(10, 111, 432, 108);

			jframeMAIN.getContentPane().add(jpanelConsole = new JPanelConsole());
			jpanelConsole.setBounds(10, 230, 432, 125);

		}

	}

	private void initBrowser() {
		setStatus("Open browser..");

		if (__WD == null)
			__WD = new WDriver();

		setStatus("Ok");
	}

	private void initStatusBar() {
		{// STATUS
			jframeMAIN.getContentPane().add(jpanelStatus = new JPanelStatus());
			jpanelStatus.setBounds(10, 0, 432, 28);
		}
	}

	private void initMainFrame() {
		jframeMAIN = new JFrame();
		jframeMAIN.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Main.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		jframeMAIN.setTitle("Selenim Console");
		{// POSITION
			Dimension jfBoxDims = new Dimension(460, 360);
			Dimension jfPositiion = DisplayUtils.locateRight(jfBoxDims, new Dimension(100, 100));
			jframeMAIN.setBounds(jfPositiion.width, jfPositiion.height, 460, 400);
		}
		jframeMAIN.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// EXIT_ON_CLOSE
		jframeMAIN.getContentPane().setLayout(null);
		{
			jframeMAIN.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					if (JOptionPane.showConfirmDialog(jframeMAIN, "Exit?", "Console dialog", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						__WD.quit();
						System.exit(0);
					}
				}
			});
		}
	}

	void setStatus(String status) {
		setStatus(status, null);
	}

	void setStatus(String status, String toolTipMessage) {
		jpanelStatus.setStatus(status, toolTipMessage);
	}

	static void p(String message) {
		System.out.println(message);
	}
}
