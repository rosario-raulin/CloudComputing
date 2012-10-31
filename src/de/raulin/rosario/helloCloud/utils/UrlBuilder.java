package de.raulin.rosario.helloCloud.utils;

import javax.servlet.http.HttpServletRequest;

public final class UrlBuilder {

	protected static final String HTTP_PORT = "80";
	protected static final String HTTPS_PORT = "443";

	private UrlBuilder() {
	}

	private static String getUrl(HttpServletRequest req, String to,
			boolean secure) {
		final StringBuilder s = new StringBuilder();
		s.append(secure ? "https://" : "http://").append(req.getServerName());
		s.append(":").append(secure ? HTTPS_PORT : HTTP_PORT);
		s.append(req.getContextPath()).append("/").append(to);
		return s.toString();
	}

	public static String getUrl(HttpServletRequest req, String to) {
		return getUrl(req, to, false);
	}

	public static String getSecureUrl(HttpServletRequest req, String to) {
		return getUrl(req, to, true);
	}
}
