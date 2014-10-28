/*
 * Created on 2005-9-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;

/**
 * @author IBM USER
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class LocalClassLoader extends SecureClassLoader {
	/**
	 * This class loader is used to load classes and resources from a search
	 * path of Files referring to both JAR files and directories. Any File that
	 * ends with a '/' is assumed to refer to a directory. Otherwise, the File
	 * is assumed to refer to a JAR file which will be opened as needed.
	 * <p>
	 * The AccessControlContext of the thread that created the instance of
	 * LocalClassLoader will be used when subsequently loading classes and
	 * resources.
	 * <p>
	 * The classes that are loaded are by default granted permission only to
	 * access the Files specified when the LocalClassLoader was created.
	 * 
	 * @author David Connelly
	 * @version 1.80, 06/07/03
	 * @since 1.2
	 */
	LocalClassPath fcp;

	/* The context to be used when loading classes and resources */
	private AccessControlContext acc;

	/**
	 * Constructs a new LocalClassLoader for the given Files. The Files will be
	 * searched in the order specified for classes and resources after first
	 * searching in the specified parent class loader. Any File that ends with a
	 * '/' is assumed to refer to a directory. Otherwise, the File is assumed to
	 * refer to a JAR file which will be downloaded and opened as needed.
	 * 
	 * <p>
	 * If there is a security manager, this method first calls the security
	 * manager's <code>checkCreateClassLoader</code> method to ensure creation
	 * of a class loader is allowed.
	 * 
	 * @param files
	 *            the Files from which to load classes and resources
	 * @param parent
	 *            the parent class loader for delegation
	 * @exception SecurityException
	 *                if a security manager exists and its
	 *                <code>checkCreateClassLoader</code> method doesn't allow
	 *                creation of a class loader.
	 * @see SecurityManager#checkCreateClassLoader
	 */
	public LocalClassLoader(File[] files, ClassLoader parent) {
		super(parent);
		// this is to make the stack depth consistent with 1.1
		SecurityManager security = System.getSecurityManager();
		if (security != null) {
			security.checkCreateClassLoader();
		}
		fcp = new LocalClassPath(files);
		acc = AccessController.getContext();
	}

	/**
	 * Constructs a new LocalClassLoader for the specified Files using the
	 * default delegation parent <code>ClassLoader</code>. The Files will be
	 * searched in the order specified for classes and resources after first
	 * searching in the parent class loader. Any File that ends with a '/' is
	 * assumed to refer to a directory. Otherwise, the File is assumed to refer
	 * to a JAR file which will be downloaded and opened as needed.
	 * 
	 * <p>
	 * If there is a security manager, this method first calls the security
	 * manager's <code>checkCreateClassLoader</code> method to ensure creation
	 * of a class loader is allowed.
	 * 
	 * @param files
	 *            the Files from which to load classes and resources
	 * 
	 * @exception SecurityException
	 *                if a security manager exists and its
	 *                <code>checkCreateClassLoader</code> method doesn't allow
	 *                creation of a class loader.
	 * @see SecurityManager#checkCreateClassLoader
	 */
	public LocalClassLoader(File[] files) {
		super();
		// this is to make the stack depth consistent with 1.1
		SecurityManager security = System.getSecurityManager();
		if (security != null) {
			security.checkCreateClassLoader();
		}
		fcp = new LocalClassPath(files);
		acc = AccessController.getContext();
	}

	/**
	 * Appends the specified File to the list of Files to search for classes and
	 * resources.
	 * 
	 * @param file
	 *            the File to be added to the search path of Files
	 */
	protected void addFile(File file) {
		fcp.addFile(file);
	}

	/**
	 * Returns the search path of Files for loading classes and resources. This
	 * includes the original list of Files specified to the constructor, along
	 * with any Files subsequently appended by the addFile() method.
	 * 
	 * @return the search path of Files for loading classes and resources.
	 */
	public File[] getFiles() {
		return fcp.getFiles();
	}

	// public Class loadClass(String name) throws ClassNotFoundException {
	// return loadClass(name, true);
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized Class loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		// First, check if the class has already been loaded
		Class c = findLoadedClass(name);
		if (c == null) {
			try {
				ClassLoader scl = getSystemClassLoader();
				if (scl != null)
					c = scl.loadClass(name);
			} catch (ClassNotFoundException cnfe) {
				c = findClass(name);
			}
		}
		if (resolve) {
			resolveClass(c);
		}
		// TODO Auto-generated method stub
		return c;
	}

	/**
	 * Finds and loads the class with the specified name from the File search
	 * path. Any Files referring to JAR files are loaded and opened as needed
	 * until the class is found.
	 * 
	 * @param name
	 *            the name of the class
	 * @return the resulting class
	 * @exception ClassNotFoundException
	 *                if the class could not be found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class findClass(final String name) throws ClassNotFoundException {
		try {
			return (Class) AccessController.doPrivileged(
					new PrivilegedExceptionAction() {
						public Object run() throws ClassNotFoundException {
							String path = name.replace('.', '/');
							LocalResource res = fcp.getResource(path, false);
							if (res != null) {
								try {
									return defineClass(name, res);
								} catch (IOException e) {
									throw new ClassNotFoundException(name, e);
								}
							} else {
								throw new ClassNotFoundException(name);
							}
						}
					}, acc);
		} catch (java.security.PrivilegedActionException pae) {
			throw (ClassNotFoundException) pae.getException();
		}
	}

	/*
	 * Defines a Class using the class bytes obtained from the specified
	 * LocalResource. The resulting Class must be resolved before it can be
	 * used.
	 */
	@SuppressWarnings("rawtypes")
	private Class defineClass(String name, LocalResource res)
			throws IOException {
		int i = name.lastIndexOf('.');
		// File file = res.getCodeSourceFile();
		if (i != -1) {
			String pkgname = name.substring(0, i);
			// Check if package already loaded.
			Package pkg = getPackage(pkgname);
			// Manifest man = res.getManifest();
			if (pkg != null) {
				// Package found, so check package sealing.
				if (pkg.isSealed()) {
					// Verify that code source File is the same.
					if (!pkg.isSealed()) {
						throw new SecurityException(
								"sealing violation: package " + pkgname
										+ " is sealed");
					}

				}
			} else {
				definePackage(pkgname, null, null, null, null, null, null, null);
			}
		}
		// Now read the class bytes and define the class
		byte[] b = res.getBytes();
		return defineClass(name, b, 0, b.length);
	}

	/**
	 * Defines a new package by name in this ClassLoader. The attributes
	 * contained in the specified Manifest will be used to obtain package
	 * version and sealing information. For sealed packages, the additional File
	 * specifies the code source File from which the package was loaded.
	 * 
	 * @param name
	 *            the package name
	 * @param man
	 *            the Manifest containing package version and sealing
	 *            information
	 * @param file
	 *            the code source file for the package, or null if none
	 * @exception IllegalArgumentException
	 *                if the package name duplicates an existing package either
	 *                in this class loader or one of its ancestors
	 * @return the newly defined Package object
	 */
	protected Package definePackage(String name, Manifest man, File file)
			throws IllegalArgumentException {
		String path = name.replace('.', '/').concat("/");
		String specTitle = null, specVersion = null, specVendor = null;
		String implTitle = null, implVersion = null, implVendor = null;
		String sealed = null;
		File sealBase = null;

		Attributes attr = man.getAttributes(path);
		if (attr != null) {
			specTitle = attr.getValue(Name.SPECIFICATION_TITLE);
			specVersion = attr.getValue(Name.SPECIFICATION_VERSION);
			specVendor = attr.getValue(Name.SPECIFICATION_VENDOR);
			implTitle = attr.getValue(Name.IMPLEMENTATION_TITLE);
			implVersion = attr.getValue(Name.IMPLEMENTATION_VERSION);
			implVendor = attr.getValue(Name.IMPLEMENTATION_VENDOR);
			sealed = attr.getValue(Name.SEALED);
		}
		attr = man.getMainAttributes();
		if (attr != null) {
			if (specTitle == null) {
				specTitle = attr.getValue(Name.SPECIFICATION_TITLE);
			}
			if (specVersion == null) {
				specVersion = attr.getValue(Name.SPECIFICATION_VERSION);
			}
			if (specVendor == null) {
				specVendor = attr.getValue(Name.SPECIFICATION_VENDOR);
			}
			if (implTitle == null) {
				implTitle = attr.getValue(Name.IMPLEMENTATION_TITLE);
			}
			if (implVersion == null) {
				implVersion = attr.getValue(Name.IMPLEMENTATION_VERSION);
			}
			if (implVendor == null) {
				implVendor = attr.getValue(Name.IMPLEMENTATION_VENDOR);
			}
			if (sealed == null) {
				sealed = attr.getValue(Name.SEALED);
			}
		}
		if ("true".equalsIgnoreCase(sealed)) {
			sealBase = file;
		}
		URL url;
		try {
			url = sealBase.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			url = null;
		}
		return definePackage(name, specTitle, specVersion, specVendor,
				implTitle, implVersion, implVendor, url);
	}

	/*
	 * Returns true if the specified package name is sealed according to the
	 * given manifest.
	 */
	@SuppressWarnings("unused")
	private boolean isSealed(String name, Manifest man) {
		String path = name.replace('.', '/').concat("/");
		Attributes attr = man.getAttributes(path);
		String sealed = null;
		if (attr != null) {
			sealed = attr.getValue(Name.SEALED);
		}
		if (sealed == null) {
			if ((attr = man.getMainAttributes()) != null) {
				sealed = attr.getValue(Name.SEALED);
			}
		}
		return "true".equalsIgnoreCase(sealed);
	}

	/**
	 * Returns an Enumeration of Files representing all of the resources on the
	 * File search path having the specified name.
	 * 
	 * @param name
	 *            the resource name
	 * @exception IOException
	 *                if an I/O exception occurs
	 * @return an <code>Enumeration</code> of <code>File</code>s
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration findResources(final String name) throws IOException {
		final Enumeration e = fcp.findResources(name, true);

		return new Enumeration() {
			private File file = null;

			private boolean next() {
				if (file != null) {
					return true;
				}
				do {
					File u = (File) AccessController.doPrivileged(
							new PrivilegedAction() {
								public Object run() {
									if (!e.hasMoreElements())
										return null;
									return e.nextElement();
								}
							}, acc);
					if (u == null)
						break;
					file = fcp.checkFile(u);
				} while (file == null);
				return file != null;
			}

			public Object nextElement() {
				if (!next()) {
					throw new NoSuchElementException();
				}
				File u = file;
				file = null;
				return u;
			}

			public boolean hasMoreElements() {
				return next();
			}
		};
	}

	/**
	 * Creates a new instance of LocalClassLoader for the specified Files and
	 * parent class loader. If a security manager is installed, the
	 * <code>loadClass</code> method of the LocalClassLoader returned by this
	 * method will invoke the <code>SecurityManager.checkPackageAccess</code>
	 * method before loading the class.
	 * 
	 * @param files
	 *            the Files to search for classes and resources
	 * @param parent
	 *            the parent class loader for delegation
	 * @return the resulting class loader
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LocalClassLoader newInstance(final File[] files,
			final ClassLoader parent) {
		// Save the caller's context
		AccessControlContext acc = AccessController.getContext();
		// Need a privileged block to create the class loader
		LocalClassLoader lcl = (LocalClassLoader) AccessController
				.doPrivileged(new PrivilegedAction() {
					public Object run() {
						return new FactoryLocalClassLoader(files, parent);
					}
				});
		// Now set the context on the loader using the one we saved,
		// not the one inside the privileged block...
		lcl.acc = acc;
		return lcl;
	}

	/**
	 * Creates a new instance of LocalClassLoader for the specified Files and
	 * default parent class loader. If a security manager is installed, the
	 * <code>loadClass</code> method of the LocalClassLoader returned by this
	 * method will invoke the <code>SecurityManager.checkPackageAccess</code>
	 * before loading the class.
	 * 
	 * @param files
	 *            the Files to search for classes and resources
	 * @return the resulting class loader
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LocalClassLoader newInstance(final File[] files) {
		// Save the caller's context
		AccessControlContext acc = AccessController.getContext();
		// Need a privileged block to create the class loader
		LocalClassLoader lcl = (LocalClassLoader) AccessController
				.doPrivileged(new PrivilegedAction() {
					public Object run() {
						return new FactoryLocalClassLoader(files);
					}
				});

		// Now set the context on the loader using the one we saved,
		// not the one inside the privileged block...
		lcl.acc = acc;
		return lcl;
	}
}

final class FactoryLocalClassLoader extends LocalClassLoader {

	FactoryLocalClassLoader(File[] files, ClassLoader parent) {
		super(files, parent);
	}

	FactoryLocalClassLoader(File[] files) {
		super(files);
	}

	@SuppressWarnings("rawtypes")
	public final synchronized Class loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		// First check if we have permission to access the package. This
		// should go away once we've added support for exported packages.
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			String cname = name.replace('/', '.');
			if (cname.startsWith("[")) {
				int b = cname.lastIndexOf('[') + 2;
				if (b > 1 && b < cname.length()) {
					cname = cname.substring(b);
				}
			}
			int i = cname.lastIndexOf('.');
			if (i != -1) {
				sm.checkPackageAccess(cname.substring(0, i));
			}
		}
		return super.loadClass(name, resolve);
	}

}