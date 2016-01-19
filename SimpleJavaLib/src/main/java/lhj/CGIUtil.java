/*
 * (C) Fuji Xerox Co., Ltd. 2015
 *
 * $Id$
 */
package lhj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class CGIUtil {
	/**
	 * The name and version of the information server software answering the
	 * request (and running the gateway). Format: name/version
	 */
	public static final String SERVER_SOFTWARE = "SERVER_SOFTWARE";
	/**
	 * The server's hostname, DNS alias, or IP address as it would appear in
	 * self-referencing URLs.
	 */
	public static final String SERVER_NAME = "SERVER_NAME";
	/**
	 * The revision of the CGI specification to which this server complies.
	 * Format: CGI/revision
	 */
	public static final String GATEWAY_INTERFACE = "GATEWAY_INTERFACE";
	/**
	 * The name and revision of the information protcol this request came in
	 * with. Format: protocol/revision
	 */
	public static final String SERVER_PROTOCOL = "SERVER_PROTOCOL";
	/** The port number to which the request was sent. */
	public static final String SERVER_PORT = "SERVER_PORT";
	/**
	 * The method with which the request was made. For HTTP, this is "GET",
	 * "HEAD", "POST", etc.
	 */
	public static final String REQUEST_METHOD = "REQUEST_METHOD";
	/**
	 * The extra path information, as given by the client. In other words,
	 * scripts can be accessed by their virtual pathname, followed by extra
	 * information at the end of this path. The extra information is sent as
	 * PATH_INFO. This information should be decoded by the server if it comes
	 * from a URL before it is passed to the CGI script.
	 */
	public static final String PATH_INFO = "PATH_INFO";
	/**
	 * The server provides a translated version of PATH_INFO, which takes the
	 * path and does any virtual-to-physical mapping to it.
	 */
	public static final String PATH_TRANSLATED = "PATH_TRANSLATED";
	/**
	 * A virtual path to the script being executed, used for self-referencing
	 * URLs.
	 */
	public static final String SCRIPT_NAME = "SCRIPT_NAME";
	/**
	 * The information which follows the ? in the <a href=
	 * "/web/20070808131704/http://www.ncsa.uiuc.edu/demoweb/url-primer.html">
	 * URL</a> which referenced this script. This is the query information. It
	 * should not be decoded in any fashion. This variable should always be set
	 * when there is query information, regardless of <a href="cl.html">command
	 * line decoding</a>.
	 */
	public static final String QUERY_STRING = "QUERY_STRING";
	/**
	 * The hostname making the request. If the server does not have this
	 * information, it should set REMOTE_ADDR and leave this unset.
	 */
	public static final String REMOTE_HOST = "REMOTE_HOST";
	/** The IP address of the remote host making the request. */
	public static final String REMOTE_ADDR = "REMOTE_ADDR";
	/**
	 * If the server supports user authentication, and the script is protects,
	 * this is the protocol-specific authentication method used to validate the
	 * user.
	 */
	public static final String AUTH_TYPE = "AUTH_TYPE";
	/**
	 * If the server supports user authentication, and the script is protected,
	 * this is the username they have authenticated as.
	 */
	public static final String REMOTE_USER = "REMOTE_USER";
	/**
	 * If the HTTP server supports RFC 931 identification, then this variable
	 * will be set to the remote user name retrieved from the server. Usage of
	 * this variable should be limited to logging only.
	 */
	public static final String REMOTE_IDENT = "REMOTE_IDENT";
	/**
	 * For queries which have attached information, such as HTTP POST and PUT,
	 * this is the content type of the data.
	 */
	public static final String CONTENT_TYPE = "CONTENT_TYPE";
	/** The length of the said content as given by the client. */
	public static final String CONTENT_LENGTH = "CONTENT_LENGTH";
	/**
	 * The MIME types which the client will accept, as given by HTTP headers.
	 * Other protocols may need to get this information from elsewhere. Each
	 * item in this list should be separated by commas as per the HTTP spec.
	 * Format: type/subtype, type/subtype
	 */
	public static final String HTTP_ACCEPT = "HTTP_ACCEPT";
	/**
	 * The browser the client is using to send the request. General format:
	 * <code>software/version library/version</code>.
	 */
	public static final String HTTP_USER_AGENT = "HTTP_USER_AGENT";

	private static Map<String, String> env = System.getenv();

	public static String getServerSoftware() {
		return env.get(SERVER_SOFTWARE);
	}

	public static String getServerName() {
		return env.get(SERVER_NAME);
	}

	public static String getGatewayInterface() {
		return env.get(GATEWAY_INTERFACE);
	}

	public static String getServerProtocol() {
		return env.get(SERVER_PROTOCOL);
	}

	public static String getServerPort() {
		return env.get(SERVER_PORT);
	}

	public static String getRequestMethod() {
		return env.get(REQUEST_METHOD);
	}

	public static String getPathInfo() {
		return env.get(PATH_INFO);
	}

	public static String getPathTranslated() {
		return env.get(PATH_TRANSLATED);
	}

	public static String getScriptName() {
		return env.get(SCRIPT_NAME);
	}

	public static String getQueryString() {
		return env.get(QUERY_STRING);
	}

	public static String getRemoteHost() {
		return env.get(REMOTE_HOST);
	}

	public static String getRemoteAddr() {
		return env.get(REMOTE_ADDR);
	}

	public static String getAuthType() {
		return env.get(AUTH_TYPE);
	}

	public static String getRemoteUser() {
		return env.get(REMOTE_USER);
	}

	public static String getRemoteIdent() {
		return env.get(REMOTE_IDENT);
	}

	public static String getContentType() {
		return env.get(CONTENT_TYPE);
	}

	public static String getContentLength() {
		return env.get(CONTENT_LENGTH);
	}

	public static String getHttpAccept() {
		return env.get(HTTP_ACCEPT);
	}

	public static String getHttpUserAgent() {
		return env.get(HTTP_USER_AGENT);
	}
	
	public static String getQueryParameter(String paramName) {
		String queryString = getQueryString();
		String[] keyvalues = queryString.split("&");
		Map<String, String> params = new HashMap<String, String>();
		for (String keyvalue: keyvalues) {
			String[] temp = keyvalue.split("=");
			params.put(temp[0], temp.length > 0 ? temp[1] : "");
		}
		return params.get(paramName);
	}

	public static void error(String code, String reason) {
		sysOut.print("Content-Type: text/plain \r\n\r\n");
		sysOut.print(code + " " + reason);
	}
	
	public static void ok(String content, String contentType) {
		sysOut.print("Content-Type: " + contentType + " \r\n\r\n");
		sysOut.print(content);
	}

	public static void download(File file, String attachFilename) {
		if (!file.exists()) {
			error("404", "FILE NOT FOUND: " + file.getName());
			return;
		}
		InputStream in = null;
		try {
			sysOut.print("Content-Type: application/octet-stream \r\n");
			sysOut.print("Content-Length: " + file.length() + " \r\n");
			sysOut.print(
					"Content-Disposition: " + String.format("attachment; filename=\"" + attachFilename + "\" \r\n\r\n"));
			in = new FileInputStream(file);
			byte[] buff = new byte[8192];
			int len;
			while ((len = in.read(buff, 0, 8192)) > 0) {
				sysOut.write(buff, 0, len);
				sysOut.flush();
			}
			in.close();
		} catch (FileNotFoundException e) {
			error("404", "FILE NOT FOUND: " + file.getName());
		} catch (IOException e) {
			error("500", "Internal Error");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String contents() {
		String length = getContentLength();
		if (!env.containsKey(CONTENT_LENGTH)) {
			sysOut.println("No Content data.");
			return null;
		}
		int len = Integer.parseInt(length);
		if (len <= 0) {
			sysOut.println("No Content data. CONTENT_LENGTH == 0;");
			return null;
		}
		byte[] buff = new byte[len];
		try {
			int readLen = sysIn.read(buff, 0, len);
			String formData = new String(buff, 0, readLen);
			return formData;
		} catch (Exception e) {
			return null;
		}
	}

	private static InputStream sysIn = System.in;
	private static PrintStream sysOut = System.out;
	/**
	 * @param sysIn the sysIn to set
	 */
	public static void setSysIn(InputStream sysIn) {
		CGIUtil.sysIn = sysIn;
	}
	/**
	 * @param sysOut the sysOut to set
	 */
	public static void setSysOut(PrintStream sysOut) {
		CGIUtil.sysOut = sysOut;
	}


	public static void main(String[] args) throws Exception {
		Field[] fields = CGIUtil.class.getDeclaredFields();
		for (Field f : fields) {
			if ((f.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC) {
				String name = f.getName();
				String[] words = name.split("_");
				sysOut.println("\tpublic static String get" + join(words) + "() {\r\n\t\treturn env.get(" + name
						+ "); \r\n\t}\r\n");
			}
		}
	}

	private static String join(String[] words) {
		String result = "";
		for (int i = 0; i < words.length; i++) {
			result += words[i].charAt(0) + words[i].substring(1).toLowerCase();
		}
		return result;
	}
}
