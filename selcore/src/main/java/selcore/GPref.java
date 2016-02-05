package selcore;

import java.util.prefs.Preferences;

public class GPref {

	private static final String PROP_REMEMBER = "remember";
	private static final String PROP_IS_PROXY = "isProxy";
	private static final String PROP_PROXY_LOCATION = "proxyLocation";

	private static final String PROP_USERNAME = "uname";
	private static final String PROP_UPASSWORD = "upass";

	private static final String PROP_TASK_COUNT_ = "taskCount.";

	private static final String PROP_DEF_LOCATION = "startLocation";

	private static final String PROP_DEF_CSS_SELECTOR = "selectorCSS";
	private static final String PROP_DEF_XPATH_SELECTOR = "selectorXpath";
	private static final String PROP_DEF_JAVASCRIPT = "cssJs";

	private final Preferences p;
	// private final Main m;

	private static volatile GPref instance;

	public static GPref get() {
		return instance;

	}

	public static GPref ini(Class clas) {
		GPref localInstance = instance;
		if (localInstance == null) {
			synchronized (GPref.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new GPref(clas);
				}
			}
		}
		return localInstance;

	}

	private GPref(Class clas) {
		this.p = Preferences.userNodeForPackage(clas);
		// this.m = main;

	}

	private void clear() {
		p.put(PROP_REMEMBER, "");
		p.put(PROP_IS_PROXY, "");
		p.put(PROP_PROXY_LOCATION, "");
		p.put(PROP_USERNAME, "");
		p.put(PROP_UPASSWORD, "");
		p.put(PROP_DEF_LOCATION, "");

	}

	public void setDefaultJavascript(String defaultJavascript) {
		p.put(PROP_DEF_JAVASCRIPT, defaultJavascript);
	}

	public String getDefaultJavascript(String defaultJavascript) {
		return p.get(PROP_DEF_JAVASCRIPT, defaultJavascript);
	}

	public void setDefaultXpath(String defaultSelector) {
		p.put(PROP_DEF_XPATH_SELECTOR, defaultSelector);
	}

	public String getDefaultXpath(String defaultSelector) {
		return p.get(PROP_DEF_XPATH_SELECTOR, defaultSelector);
	}

	public void setDefaultCssSelector(String defaultSelector) {
		p.put(PROP_DEF_CSS_SELECTOR, defaultSelector);
	}

	public String getDefaultCssSelector(String defaultSelector) {
		return p.get(PROP_DEF_CSS_SELECTOR, defaultSelector);
	}

	public String getDefaultLocation(String defaultLocation) {
		return p.get(PROP_DEF_LOCATION, defaultLocation);
	}

	public void setDefaultLocation(String proxyLocation) {
		p.put(PROP_DEF_LOCATION, proxyLocation);
	}

	public boolean isRemember() {
		return p.getBoolean(PROP_REMEMBER, false);
	}

	public boolean isProxy() {
		return p.getBoolean(PROP_IS_PROXY, false);
	}

	public void isRemember(boolean selected) {
		p.putBoolean(PROP_REMEMBER, selected);
	}

	public void isProxy(boolean selected) {
		p.putBoolean(PROP_IS_PROXY, selected);
	}

	public String getUserName() {
		return p.get(PROP_USERNAME, "");
	}

	public String getUserPassword() {
		return p.get(PROP_UPASSWORD, "");
	}

	public String getProxyLocation() {
		return p.get(PROP_PROXY_LOCATION, "");
	}

	public void setUserName(String uname) {
		p.put(PROP_USERNAME, uname);
	}

	public void setUserPassword(String upass) {
		p.put(PROP_UPASSWORD, upass);
	}

	public void setProxyLocation(String proxyLocation) {
		p.put(PROP_PROXY_LOCATION, proxyLocation);
	}

	public boolean isSoundOn() {
		return false;
	}

}
