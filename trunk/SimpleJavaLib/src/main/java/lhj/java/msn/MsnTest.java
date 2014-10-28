package lhj.java.msn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import lhj.java.util.SP;
import sun.net.www.protocol.http.HttpURLConnection;

@SuppressWarnings("restriction")
public class MsnTest {
	private static String X_MSN_MESSENGER = "X-MSN-Messenger";
	private static String GATEWAY_SCRIPT = "/gateway/gateway.dll";
	private static String[] loginCmds = {
			"\r\nVER 1 MSNP8 CVR0\r\n",
			"\r\nCVR 2 0x0401 win 5.01 i386 LHJMSN 1.0.0000 MSMSG sealion_hunter@hotmail.co.jp\r\n" };
	String TWNServ = "loginnet.passport.com";
	String TWNPage = "/RST.srf";
	String USRName = "";
	String USRPass = "";

	String ReturnVal;

	public void AuthTWN() {

	}

	public String GetIt(String Email, String Password, String Challenge) {
		try {
			this.USRName = URLEncoder.encode(Email, "UTF-8");
			this.USRPass = URLEncoder.encode(Password, "UTF-8");

			SSLSocketFactory ssl = (SSLSocketFactory) SSLSocketFactory
					.getDefault();
			SSLSocket socket = (SSLSocket) ssl.createSocket(this.TWNServ, 443);

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);

			StringBuffer buf = new StringBuffer();
			StringBuffer buf2 = new StringBuffer();

			Challenge = URLDecoder.decode(Challenge, "UTF-8");
			Challenge = Challenge.replaceAll(",", "&");
			Challenge = Challenge.replaceAll("&", "&amp;");

			buf2.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buf2
					.append("<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsse=\"http://schemas.xmlsoap.org/ws/2003/06/secext\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2002/12/policy\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:wssc=\"http://schemas.xmlsoap.org/ws/2004/04/sc\" xmlns:wst=\"http://schemas.xmlsoap.org/ws/2004/04/trust\"><Header>");
			buf2
					.append("<ps:AuthInfo xmlns:ps=\"http://schemas.microsoft.com/Passport/SoapServices/PPCRL\" Id=\"PPAuthInfo\">");
			buf2
					.append("<ps:HostingApp>{7108E71A-9926-4FCB-BCC9-9A9D3F32E423}</ps:HostingApp>");
			buf2.append("<ps:BinaryVersion>4</ps:BinaryVersion>");
			buf2.append("<ps:UIVersion>1</ps:UIVersion>");
			buf2.append("<ps:Cookies></ps:Cookies>");
			buf2
					.append("<ps:RequestParams>AQAAAAIAAABsYwQAAAAzMDg0</ps:RequestParams>");
			buf2.append("</ps:AuthInfo>");
			buf2.append("<wsse:Security>");
			buf2.append("<wsse:UsernameToken Id=\"user\">");
			buf2.append("<wsse:Username>" + Email + "</wsse:Username>");
			buf2.append("<wsse:Password>" + Password + "</wsse:Password>");
			buf2.append("</wsse:UsernameToken>");
			buf2.append("</wsse:Security>");
			buf2.append("</Header>");
			buf2.append("<Body>");
			buf2
					.append("<ps:RequestMultipleSecurityTokens xmlns:ps=\"http://schemas.microsoft.com/Passport/SoapServices/PPCRL\" Id=\"RSTS\">");
			buf2.append("<wst:RequestSecurityToken Id=\"RST0\">");
			buf2
					.append("<wst:RequestType>http://schemas.xmlsoap.org/ws/2004/04/security/trust/Issue</wst:RequestType>");
			buf2.append("<wsp:AppliesTo>");
			buf2.append("<wsa:EndpointReference>");
			buf2.append("<wsa:Address>http://Passport.NET/tb</wsa:Address>");
			buf2.append("</wsa:EndpointReference>");
			buf2.append("</wsp:AppliesTo>");
			buf2.append("</wst:RequestSecurityToken>");
			buf2.append("<wst:RequestSecurityToken Id=\"RST1\">");
			buf2
					.append("<wst:RequestType>http://schemas.xmlsoap.org/ws/2004/04/security/trust/Issue</wst:RequestType>");
			buf2.append("<wsp:AppliesTo>");
			buf2.append("<wsa:EndpointReference>");
			buf2.append("<wsa:Address>messenger.msn.com</wsa:Address>");
			buf2.append("</wsa:EndpointReference>");
			buf2.append("</wsp:AppliesTo>");
			buf2.append("<wsse:PolicyReference URI=\"?" + Challenge
					+ "\"></wsse:PolicyReference>");
			buf2.append("</wst:RequestSecurityToken>");
			buf2.append("</ps:RequestMultipleSecurityTokens>");
			buf2.append("</Body></Envelope>");

			buf.append("POST " + TWNPage + " HTTP/1.1" + "\r\n");
			buf.append("Accept: text/*" + "\r\n");
			buf.append("User-Agent: TomsMSNBot/3.0" + "\r\n");
			buf.append("Host: " + TWNServ + "\r\n");
			buf.append("Content-Length: " + buf2.length() + "\r\n");
			buf.append("Connection: Keep-Alive" + "\r\n");
			buf.append("Cache-Control: no-cache" + "\r\n");
			buf.append("\r\n");
			buf.append(buf2.toString());
			pw.print(buf.toString());
			pw.flush();

			buf = new StringBuffer();
			buf2 = null;

			boolean DoLoop = true;
			int Length = 0;

			while (DoLoop) {
				String str = br.readLine();
				if (str.substring(0, 15).equals("Content-Length:")) {
					String[] DataArray = str.split(" ");
					Length = Integer.parseInt(DataArray[1]) + 2;
					for (int i = 0; i < Length; i++) {
						int c = br.read();
						buf.append((char) c);
					}

					String data = buf.toString();
					String[] temp = data.split("BinarySecurityToken");
					data = temp[1];
					data = data.substring(15, (data.length() - 7));

					data = data.replaceAll("&amp;", "&");

					this.ReturnVal = data;

					DoLoop = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return this.ReturnVal;
	}

	public MsnTest() {

	}

	private void printHeaders(URLConnection con) {
		Map<String, List<String>> headers = con.getHeaderFields();
		Iterator<String> keys = headers.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			List<String> value = headers.get(key);
			SP.println(key + '=' + value);
		}
	}

	public void initHttp() throws Exception {
		HttpURLConnection con = getConnection(null, null);
		OutputStream out = con.getOutputStream();
		int ver = 0;
		out.write(("\r\nVER " + ver++ + " MSNP8 CVR0\r\n").getBytes());
		out.flush();
		out.close();
		printHeaders(con);
		String[] xMSNMSG = parseXMsnMsg(con.getHeaderField(X_MSN_MESSENGER));
		InputStream in = con.getInputStream();
		printInput(in);
		in.close();
		Thread.sleep(2000);
		con = getConnection(xMSNMSG[1], xMSNMSG[0]);
		out = con.getOutputStream();
		out
				.write(("\r\nCVR " + ver++ + " 0x0401 winnt 5.01 i386 LHJMSN 1.0.0000 MSMSGS sealion_hunter@hotmail.co.jp\r\n")
						.getBytes());
		out.flush();
		out.close();
		printHeaders(con);
		xMSNMSG = parseXMsnMsg(con.getHeaderField(X_MSN_MESSENGER));
		in = con.getInputStream();
		printInput(in);
		in.close();
		con = getConnection(xMSNMSG[1], xMSNMSG[0]);
		out = con.getOutputStream();
		out
				.write(("\r\nUSR " + ver++ + " TWN I sealion_hunter@hotmail.co.jp\r\n")
						.getBytes());
		out.flush();
		out.close();
		printHeaders(con);
		xMSNMSG = parseXMsnMsg(con.getHeaderField(X_MSN_MESSENGER));
		in = con.getInputStream();
		String nsinfo = printInput(in);
		in.close();
		nsinfo = nsinfo.substring(nsinfo.indexOf("lc="));
		String ticket = GetIt("sealion_hunter@hotmail.co.jp", "wkilylhj1109",
				nsinfo);
		con = getConnection(xMSNMSG[1], xMSNMSG[0]);
		out = con.getOutputStream();
		out
				.write(("\r\nUSR " + ver++ + " TWN S " + ticket + "\r\n")
						.getBytes());
		out.flush();
		out.close();
		printHeaders(con);
		xMSNMSG = parseXMsnMsg(con.getHeaderField(X_MSN_MESSENGER));
		in = con.getInputStream();
		nsinfo = printInput(in);
		// getTicket(nsinfo);
		// }
	}

	public void initSocket() throws Exception {
		Socket socket = new Socket();
		socket.setKeepAlive(true);
		socket.connect(new InetSocketAddress("messenger.hotmail.com", 1863));
		int ver = 0;
		SP.println("socket.isConnected()" + socket.isConnected());
		OutputStream out = socket.getOutputStream();
		SP.println("socket.isOutputShutdown()" + socket.isOutputShutdown());
		out.write(loginCmds[ver++].getBytes());
		out.flush();
		SP.println("ver " + ver + "Data outputed!");
		InputStream in = socket.getInputStream();
		SP.println("socket.isInputShutdown()" + socket.isInputShutdown());
		printInput(in);
		Thread.sleep(2000);
		SP.println("socket.isOutputShutdown()" + socket.isOutputShutdown());
		out.write(loginCmds[ver++].getBytes());
		out.flush();
		SP.println("ver " + ver + " Data outputed!");
		SP.println("socket.isInputShutdown()" + socket.isInputShutdown());
		printInput(in);
		out.close();
		in.close();
		socket.close();
	}

	private HttpURLConnection getConnection(String gateway, String session)
			throws Exception {
		URL url;
		if (gateway == null) {
			url = new URL("http://gateway.messenger.hotmail.com"
					+ GATEWAY_SCRIPT
					+ "?Action=open&Server=NS&IP=messenger.hotmail.com");
		} else {
			url = new URL("http://" + gateway + GATEWAY_SCRIPT + "?" + session);
		}
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-msn-messenger");
		con.setRequestProperty("Proxy-Connection", "Keep-Alive");
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("User-Agent", "MSMSGS");
		con.setRequestProperty("Pragma", "No-Cache");
		con.setDoOutput(true);
		return con;
	}

	private String[] parseXMsnMsg(String xMsnMsg) throws Exception {
		if (xMsnMsg.indexOf("Close") != -1) {
			throw new Exception("Connection closed!");
		}
		String[] retValue = new String[3];
		int index = xMsnMsg.indexOf(';');
		retValue[0] = xMsnMsg.substring(0, index);
		retValue[1] = xMsnMsg.substring(xMsnMsg.indexOf('=', index) + 1).trim();
		return retValue;
	}

	private String printInput(InputStream in) throws Exception {
		StringBuffer sb = new StringBuffer();
		byte[] buf = new byte[4096];
		int count = 0;
		while ((count = in.read(buf, 0, 4096)) != -1) {
			sb.append(new String(buf, 0, count));
		}
		// in.close();
		SP.println(sb);
		return sb.toString();
	}

	// private String getTicket(String nsinfo) throws Exception {
	// // URL url = new URL("http://nexus.passport.com:443/rdr/pprdr.asp");
	// // URLConnection con = url.openConnection();
	// // con.setReadTimeout(100);
	// // printHeaders(con);
	// // String passprotUrl = con.getHeaderField("PassportURLs");
	// // passprotUrl = passprotUrl.substring(passprotUrl.indexOf("DALogin=") +
	// 1, passprotUrl.indexOf(','));
	// URL url = new URL("http://login.live.com/login.srf");
	// URLConnection con = url.openConnection();
	// con.setRequestProperty("Authorization",
	// "Passport1.4 OrgVerb=GET,OrgURL=http%3A%2F%2Fmessenger%2Emsn%2Ecom,sign-in=sealion_hunter%40hotmail.co.jp,pwd=wkilylhj1109,"
	// + nsinfo);
	// printHeaders(con);
	// // String authentication_Info =
	// con.getHeaderField("Authentication-Info");
	//		
	// String retValue = null;
	// return retValue;
	// }
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MsnTest test = new MsnTest();
		test.initHttp();
		// test.initSocket();
	}

}
