package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Test {
	
	private String repository = "E:/mavenrepo";
	private String bundle_dir = "f:/test/oss-bundle";
//	private String[] jars = {"activation-1.1.1.jar"
//			, "activemq-all-5.5.1.jar"
//			, "annogen-0.1.0.jar"
//			, "aopalliance-1.0.jar"
//			, "aspectjweaver-1.5.4.jar"
//			, "axiom-api-1.2.11.jar"
//			, "axiom-dom-1.2.11.jar"
//			, "axiom-impl-1.2.11.jar"
//			, "axis2-adb-1.5.5.jar"
//			, "axis2-adb-codegen-1.5.5.jar"
//			, "axis2-codegen-1.5.5.jar"
//			, "axis2-corba-1.5.5.jar"
//			, "axis2-fastinfoset-1.5.5.jar"
//			, "axis2-java2wsdl-1.5.5.jar"
//			, "axis2-jaxbri-1.5.5.jar"
//			, "axis2-jibx-1.5.5.jar"
//			, "axis2-json-1.5.5.jar"
//			, "axis2-kernel-1.5.5.jar"
//			, "axis2-metadata-1.5.5.jar"
//			, "axis2-mtompolicy-1.5.5.jar"
//			, "axis2-saaj-1.5.5.jar"
//			, "axis2-soapmonitor-servlet-1.5.5.jar"
//			, "axis2-spring-1.5.5.jar"
//			, "axis2-transport-all-1.0.0.jar"
//			, "axis2-transport-http-1.5.5.jar"
//			, "axis2-transport-local-1.5.5.jar"
//			, "axis2-xmlbeans-1.5.5.jar"
//			, "backport-util-concurrent-3.1.jar"
//			, "commons-beanutils-core-1.6.1.jar"
//			, "commons-betwixt-0.8.jar"
//			, "commons-codec-1.3.jar"
//			, "commons-collections-3.2.1.jar"
//			, "commons-dbcp-1.4.jar"
//			, "commons-digester-1.7.jar"
//			, "commons-discovery-0.2.jar"
//			, "commons-fileupload-1.2.1.jar"
//			, "commons-httpclient-3.1.jar"
//			, "commons-io-1.4.jar"
//			, "commons-lang-2.3.jar"
//			, "commons-logging-1.1.1.jar"
//			, "commons-pool-1.4.jar"
//			, "geronimo-annotation_1.0_spec-1.1.jar"
//			, "geronimo-jpa_3.0_spec-1.0.jar"
//			, "geronimo-jta_1.1_spec-1.1.jar"
//			, "geronimo-stax-api_1.0_spec-1.0.1.jar"
//			, "httpcore-4.0.1.jar"
//			, "httpcore-nio-4.0.1.jar"
//			, "jackson-core-asl-1.9.11.jar"
//			, "jackson-mapper-asl-1.9.11.jar"
//			, "jalopy-1.5rc3.jar"
//			, "jaxb-api-2.1.jar"
//			, "jaxb-impl-2.1.6.jar"
//			, "jaxb-xjc-2.1.6.jar"
//			, "jaxen-1.1.1.jar"
//			, "jaxrpc-api-1.1.jar"
//			, "jetspeed-api-2.0.jar"
//			, "jetspeed-commons-2.0.jar"
//			, "jetspeed-page-manager-2.0.jar"
//			, "jettison-1.0-RC2.jar"
//			, "jibx-bind-1.2.1.jar"
//			, "jibx-run-1.2.1.jar"
//			, "jstl-1.1.2.jar"
//			, "log4j-1.2.15.jar"
//			, "mail-1.4.1.jar"
//			, "neethi-2.0.4.jar"
//			, "openjpa-1.2.2.jar"
//			, "pluto-1.0.1.jar"
//			, "portals-bridges-common-1.0.jar"
//			, "portlet-api-1.0.jar"
//			, "quartz-1.8.5.jar"
//			, "saaj-1.2.jar"
//			, "serp-1.13.1.jar"
//			, "servlet-api-2.5-6.1.5.jar"
//			, "slf4j-api-1.6.0.jar"
//			, "slf4j-log4j12-1.6.0.jar"
//			, "snmp4j-1.11.3.jar"
//			, "soapmonitor-1.4.1.jar"
//			, "sojo-1.0.5.jar"
//			, "spring-2.5.6.jar"
//			, "spring-webmvc-2.5.6.jar"
//			, "spring-webmvc-portlet-2.5.6.jar"
//			, "sqljdbc4-3.0.jar"
//			, "ss_css2-1.0.jar"
//			, "standard-1.1.2.jar"
//			, "stax-api-1.0.1.jar"
//			, "velocity-dep-1.5.jar"
//			, "woden-api-1.0M8.jar"
//			, "woden-impl-dom-1.0M8.jar"
//			, "wsdl4j-1.6.2.jar"
//			, "wstx-asl-3.2.4.jar"
//			, "xalan-2.7.0.jar"
//			, "xercesImpl-2.8.1.jar"
//			, "xml-apis-1.3.04.jar"
//			, "xml-resolver-1.2.jar"
//			, "xmlbeans-2.3.0.jar"
//			, "XmlSchema-1.4.2.jar"
//			, "xmlsec-1.3.0.jar"};
	
	private String[] jars = { 
			"activation-1.1.1.jar"
	, "aopalliance-1.0.jar"
	, "axiom-api-1.2.11.jar"
	, "axiom-dom-1.2.11.jar"
	, "axiom-impl-1.2.11.jar"
	, "axis2-adb-1.5.5.jar"
	, "axis2-kernel-1.5.5.jar"
	, "axis2-transport-all-1.0.0.jar"
	, "axis2-transport-http-1.5.5.jar"
	, "axis2-transport-local-1.5.5.jar"
	, "backport-util-concurrent-3.1.jar"
	, "castor-0.9.4.3.jar"
	, "cglib-full-2.0.2.jar"
	, "commons-beanutils-1.6.1.jar"
	, "commons-codec-1.3.jar"
	, "commons-collections-3.2.1.jar"
	, "commons-configuration-1.0.jar"
	, "commons-dbcp-1.4.jar"
	, "commons-digester-1.5.jar"
	, "commons-fileupload-1.0.jar"
	, "commons-httpclient-3.1.jar"
	, "commons-io-0.1.jar"
	, "commons-lang-2.0.jar"
	, "commons-logging-1.1.1.jar"
	, "commons-pool-1.2.jar"
	, "db-ojb-1.0.3.jar"
	, "httpcore-4.0.1.jar"
	, "jdom-1.0.jar"
	, "jetspeed-capability-2.0.jar"
	, "jetspeed-cm-2.0.jar"
	, "jetspeed-components-2.0.jar"
	, "jetspeed-deploy-tools-2.0.jar"
	, "jetspeed-file-cache-2.0.jar"
	, "jetspeed-header-resource-2.0.jar"
	, "jetspeed-id-generator-2.0.jar"
	, "jetspeed-locator-2.0.jar"
	, "jetspeed-page-manager-2.0.jar"
	, "jetspeed-portal-2.0.jar"
	, "jetspeed-portal-site-2.0.jar"
	, "jetspeed-portlet-factory-2.0.jar"
	, "jetspeed-prefs-2.0.jar"
	, "jetspeed-profiler-2.0.jar"
	, "jetspeed-rdbms-2.0.jar"
	, "jetspeed-registry-2.0.jar"
	, "jetspeed-search-2.0.jar"
	, "jetspeed-security-2.0.jar"
	, "jetspeed-sso-2.0.jar"
	, "jetspeed-statistics-2.0.jar"
	, "jstl-1.0.6.jar"
	, "log4j-1.2.8.jar"
	, "lucene-1.4.1.jar"
	, "mail-1.4.1.jar"
	, "neethi-2.0.4.jar"
	, "nekohtml-0.9.3.jar"
	, "oro-2.0.7.jar"
	, "portals-bridges-velocity-1.0.jar"
	, "random-1.0.2.jar"
	, "regexp-1.2.jar"
	, "request-1.0.1.jar"
	, "saxpath-1.0-FCS.jar"
	, "spring-1.1.5.jar"
	, "standard-1.0.6.jar"
	, "swpf-i18n-1.0.2.jar"
	, "swpf-license-cache-1.0.1.jar"
	, "swpf-logging-1.0.16.jar"
	, "swpf-logging-log4j-1.0.16.jar"
	, "usernametokenclient-1.2.0.jar"
	, "velocity-1.4.jar"
	, "wsdl4j-1.6.2.jar"
	, "xalan-2.4.1.jar"
	, "xercesImpl-2.3.0.jar"
	, "xml-apis-1.0.b2.jar"
	, "XmlSchema-1.4.2.jar"};

	private void makebundles(final String jar) throws Exception {
		File r = new File(repository);
		File jarF = findJar(r, jar);
		if (jarF == null) {
			System.out.println(jar + " not found ");
			return;
		}
		
		String version = jarF.getParentFile().getName();
		String arcfactId = jarF.getParentFile().getParentFile().getName();
		String groupId = jarF.getParentFile().getParentFile().getParentFile().getAbsolutePath();
		groupId = groupId.substring(repository.length() + 1).replaceAll("/", ".").replaceAll("\\\\", ".");

//		System.out.println("        <dependency>"); 
//		System.out.println("            <groupId>" + groupId + "</groupId>");
//		System.out.println("            <artifactId>" + arcfactId + "</artifactId>");
//		System.out.println("            <version>" + version + "</version>");
//		System.out.println("            <scope>provided</scope>");
//		System.out.println("        </dependency>");
		
		
		String pom =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" 
				+ "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" 
				+ "    xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" 
				+ "    <modelVersion>4.0.0</modelVersion>\n" 
				+ "\n" 
				+ "    <groupId>jp.co.fujixerox.swpf.bundles</groupId>\n" 
				+ "    <artifactId>" + arcfactId + "-bundle</artifactId>\n" 
				+ "    <version>" + version + "</version>\n" 
				+ "    <packaging>bundle</packaging>\n" 
				+ "    <name>" + arcfactId + " :: bundle</name>\n" 
				+ "\n" 
				+ "    <url>${reportURL}/${project.artifactId}-${project.version}/${project.artifactId}</url>\n" 
				+ "    <build>\n" 
				+ "        <plugins>        \n" 
				+ "            <plugin>\n" 
				+ "                <groupId>org.apache.felix</groupId>\n" 
				+ "                <artifactId>maven-bundle-plugin</artifactId>\n" 
				+ "                <extensions>true</extensions>\n" 
				+ "                <configuration>\n" 
				+ "                    <instructions>\n" 
				+ "                        <Embed-Dependency>" + arcfactId + "</Embed-Dependency>\n" 
				+ "                        <Embed-Directory>lib</Embed-Directory>\n" 
				+ "                        <Import-Package>*</Import-Package>\n" 
				+ "                        <!-- Export-Package without copy classes -->\n" 
				+ "                        <_exportcontents>*</_exportcontents>\n" 
				+ "                    </instructions>\n" 
				+ "                </configuration>\n" 
				+ "            </plugin>\n" 
				+ "        </plugins>\n" 
				+ "    </build>\n" 
				+ "    <dependencies>\n" 
				+ "        <dependency>\n" 
				+ "            <groupId>" + groupId + "</groupId>\n" 
				+ "            <artifactId>" + arcfactId + "</artifactId>\n" 
				+ "            <version>${project.version}</version>\n" 
				+ "            <scope>provided</scope>\n" 
				+ "        </dependency>\n" 
				+ "    </dependencies>\n" 
				+ "\n" 
				+ "</project>\n" 
				+ "\n" 
				+ "\n"  ;
		
		File dest = new File(bundle_dir, arcfactId + "-bundle");
		dest.mkdirs();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dest, "pom.xml")), "UTF-8"));
		bw.write(pom);
		bw.close();
		System.out.println("<module>../" + arcfactId + "-bundle</module>");
	}
	
	private File findJar(File parent, final String jar) {
		File p = parent;
		if (p.isFile() && p.getName().equals(jar)) {
			return p;
		} else if (p.isDirectory()){
		File[] sub = p.listFiles(new FileFilter() {
			
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return true;
				}
				return pathname.getName().equals(jar);
			}
		}); 
		for (File s : sub) {
			File ss = findJar(s, jar);
			if (ss != null) {
				return ss;
			}
		}
		}
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		for (String jar : t.jars) {
			t.makebundles(jar);
		}
	}

}
