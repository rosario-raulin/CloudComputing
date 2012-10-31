package de.raulin.rosario.helloCloud.sessions;

import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public final class SessionHandler {

	private static final int UUID_LEN = 64;

	private static final Map<String, Session> sessions;
	private static final Map<String, SecureSession> secureSessions;

	static {
		sessions = new Hashtable<String, Session>();
		secureSessions = new Hashtable<String, SecureSession>();
	}

	private SessionHandler() {
	}

	private static String generateUuid() {
		final SecureRandom rg = new SecureRandom();
		final byte[] raw = new byte[UUID_LEN];
		rg.nextBytes(raw);
		return new BASE64Encoder().encode(raw).substring(0, UUID_LEN - 1);
	}

	private static Session createSession(boolean secure, HttpServletRequest req,
			HttpServletResponse resp) {
		String uuid = null;
		while (uuid == null || sessions.containsKey(uuid)
				|| secureSessions.containsKey(uuid)) {
			uuid = generateUuid();
		}
		
		final Session s;
		final Cookie c;

		if (secure) {
			s = new SecureSession(uuid);
			secureSessions.put(uuid, (SecureSession) s);
			c = new Cookie("SecureUUID", uuid);
			c.setSecure(true);
		} else {
			s = new Session(uuid);
			sessions.put(uuid, s);
			c = new Cookie("SessionUUID", uuid);
		}

		c.setPath(req.getContextPath());
		resp.addCookie(c);

		return s;
	}

	private static Cookie getUuidCookie(boolean secure, HttpServletRequest req) {
		if (req.getCookies() != null) {
			for (final Cookie c : req.getCookies()) {
				if (c.getName().equals(secure ? "SecureUUID" : "SessionUUID")) {
					return c;
				}
			}
		}
		return null;
	}

	private static String getCookieUuid(boolean secure, HttpServletRequest req) {
		final Cookie c = getUuidCookie(secure, req);
		return (c != null ? c.getValue() : null);
	}

	private static Session getSession(boolean secure, HttpServletRequest req,
			HttpServletResponse resp) {
		final String uuid = getCookieUuid(secure, req);

		if (uuid == null
				|| (secure ? !secureSessions.containsKey(uuid) : !sessions
						.containsKey(uuid))) {
			return createSession(secure, req, resp);
		} else {
			return (secure ? secureSessions.get(uuid) : sessions.get(uuid));
		}
	}

	public static Session getSession(HttpServletRequest req,
			HttpServletResponse resp) {
		return getSession(false, req, resp);
	}

	public static SecureSession getSecureSession(HttpServletRequest req,
			HttpServletResponse resp) throws InsecureConnectionException {
		if (!req.isSecure()) {
			throw new InsecureConnectionException();
		}
		return (SecureSession) getSession(true, req, resp);
	}

	private static void cancelSession(boolean secure, HttpServletRequest req,
			HttpServletResponse resp) {
		final Cookie c = getUuidCookie(secure, req);
		if (c != null) {
			final String uuid = c.getValue();
			if (secure) {
				secureSessions.remove(uuid);

			} else {
				sessions.remove(uuid);
			}
			c.setMaxAge(0);
			resp.addCookie(c);
		}
	}

	public static void cancelSession(HttpServletRequest req,
			HttpServletResponse resp) {
		cancelSession(false, req, resp);
	}

	public static void cancleSecureSession(HttpServletRequest req,
			HttpServletResponse resp) {
		cancelSession(true, req, resp);
	}

	public static void cancelAllSessions(HttpServletRequest req,
			HttpServletResponse resp) {
		cancelSession(false, req, resp);
		cancelSession(true, req, resp);
	}
}
