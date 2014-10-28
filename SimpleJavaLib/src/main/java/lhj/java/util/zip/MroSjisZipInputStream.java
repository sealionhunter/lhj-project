/*
 * Created on 2003/08/19
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package lhj.java.util.zip;

import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.PushbackInputStream;
import java.util.zip.*;

/**
 * @author Administrator
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MroSjisZipInputStream extends InflaterInputStream implements
		MroSJisZipConstants {
	private ZipEntry entry;
	private CRC32 crc = new CRC32();
	private long remaining;
	private byte[] tmpbuf = new byte[512];

	private static final String SJIS_ENCODING = "SJIS";

	private static final int STORED = ZipEntry.STORED;
	private static final int DEFLATED = ZipEntry.DEFLATED;

	private boolean closed = false;
	// this flag is set to true after EOF has reached for
	// one entry
	private boolean entryEOF = false;

	private int flag = 0;

	/**
	 * Check to make sure that this stream has not been closed
	 */
	private void ensureOpen() throws IOException {
		if (closed) {
			throw new IOException("Stream closed");
		}
	}

	/**
	 * Creates a new ZIP input stream.
	 * 
	 * @param in
	 *            the actual input stream
	 */
	public MroSjisZipInputStream(InputStream in) {
		super(new PushbackInputStream(in, 512), new Inflater(true), 512);
		if (in == null) {
			throw new NullPointerException("in is null");
		}
	}

	/**
	 * Reads the next ZIP file entry and positions stream at the beginning of
	 * the entry data.
	 * 
	 * @return the ZipEntry just read
	 * @exception ZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public ZipEntry getNextEntry() throws IOException {
		ensureOpen();
		if (entry != null) {
			closeEntry();
		}
		crc.reset();
		inf.reset();
		if ((entry = readLOC()) == null) {
			return null;
		}
		if (entry.getMethod() == STORED) {
			remaining = entry.getSize();
		}
		entryEOF = false;
		return entry;
	}

	/**
	 * Closes the current ZIP entry and positions the stream for reading the
	 * next entry.
	 * 
	 * @exception ZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void closeEntry() throws IOException {
		ensureOpen();
		while (read(tmpbuf, 0, tmpbuf.length) != -1)
			;
		entryEOF = true;
	}

	/**
	 * Returns 0 after EOF has reached for the current entry data, otherwise
	 * always return 1.
	 * <p>
	 * Programs should not count on this method to return the actual number of
	 * bytes that could be read without blocking.
	 * 
	 * @return 1 before EOF and 0 after EOF has reached for current entry.
	 * @exception IOException
	 *                if an I/O error occurs.
	 * 
	 */
	public int available() throws IOException {
		ensureOpen();
		if (entryEOF) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Reads from the current ZIP entry into an array of bytes. Blocks until
	 * some input is available.
	 * 
	 * @param b
	 *            the buffer into which the data is read
	 * @param off
	 *            the start offset of the data
	 * @param len
	 *            the maximum number of bytes read
	 * @return the actual number of bytes read, or -1 if the end of the entry is
	 *         reached
	 * @exception ZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public int read(byte[] b, int off, int len) throws IOException {
		ensureOpen();
		if ((off < 0) || (off > b.length) || (len < 0)
				|| ((off + len) > b.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}

		if (entry == null) {
			return -1;
		}
		switch (entry.getMethod()) {
		case DEFLATED:
			len = super.read(b, off, len);
			if (len == -1) {
				readEnd(entry);
				entryEOF = true;
				entry = null;
			} else {
				crc.update(b, off, len);
			}
			return len;
		case STORED:
			if (remaining <= 0) {
				entryEOF = true;
				entry = null;
				return -1;
			}
			if (len > remaining) {
				len = (int) remaining;
			}
			len = in.read(b, off, len);
			if (len == -1) {
				throw new ZipException("unexpected EOF");
			}
			crc.update(b, off, len);
			remaining -= len;
			return len;
		default:
			throw new InternalError("invalid compression method");
		}
	}

	/**
	 * Skips specified number of bytes in the current ZIP entry.
	 * 
	 * @param n
	 *            the number of bytes to skip
	 * @return the actual number of bytes skipped
	 * @exception ZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 * @exception IllegalArgumentException
	 *                if n < 0
	 */
	public long skip(long n) throws IOException {
		if (n < 0) {
			throw new IllegalArgumentException("negative skip length");
		}
		ensureOpen();
		int max = (int) Math.min(n, Integer.MAX_VALUE);
		int total = 0;
		while (total < max) {
			int len = max - total;
			if (len > tmpbuf.length) {
				len = tmpbuf.length;
			}
			len = read(tmpbuf, 0, len);
			if (len == -1) {
				entryEOF = true;
				break;
			}
			total += len;
		}
		return total;
	}

	/**
	 * Closes the ZIP input stream.
	 * 
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void close() throws IOException {
		super.close();
		closed = true;
	}

	/*
	 * Reads local file (LOC) header for next entry.
	 */
	private ZipEntry readLOC() throws IOException {
		try {
			readFully(tmpbuf, 0, LOCHDR);
		} catch (EOFException e) {
			return null;
		}
		if (get32(tmpbuf, 0) != LOCSIG) {
			return null;
		}
		// get the entry name and create the ZipEntry first
		int len = get16(tmpbuf, LOCNAM);
		if (len == 0) {
			throw new ZipException("missing entry name");
		}
		byte[] b = new byte[len];
		readFully(b, 0, len);
		ZipEntry e = createZipEntry(getUTF8String(b, 0, len));
		// now get the remaining fields for the entry
		// e. = get16(tmpbuf, LOCVER);
		flag = get16(tmpbuf, LOCFLG);
		if ((flag & 1) == 1) {
			throw new ZipException("encrypted ZIP entry not supported");
		}
		e.setMethod(get16(tmpbuf, LOCHOW));
		e.setTime(get32(tmpbuf, LOCTIM));
		if ((flag & 8) == 8) {
			/* EXT descriptor present */
			if (e.getMethod() != DEFLATED) {
				throw new ZipException(
						"only DEFLATED entries can have EXT descriptor");
			}
		} else {
			e.setCrc(get32(tmpbuf, LOCCRC));
			e.setCompressedSize(get32(tmpbuf, LOCSIZ));
			e.setSize(get32(tmpbuf, LOCLEN));
		}
		len = get16(tmpbuf, LOCEXT);
		if (len > 0) {
			b = new byte[len];
			readFully(b, 0, len);
			e.setExtra(b);
		}
		return e;
	}

	/*
	 * Fetches a UTF8-encoded String from the specified byte array.
	 */
	private static String getUTF8String(byte[] b, int off, int len) {
		// First, count the number of characters in the sequence
//		int count = 0;
//		int max = off + len;
//		int i = off;
		String test = null;
		try {
			test = newString(b, off, len, SJIS_ENCODING);
		} catch (IOException e) {

		}
		return test;

		/*
		 * while (i < max) { int c = b[i++] & 0xff; switch (c >> 4) { case 0:
		 * case 1: case 2: case 3: case 4: case 5: case 6: case 7: // 0xxxxxxx
		 * count++; break; case 12: case 13: // 110xxxxx 10xxxxxx if
		 * ((int)(b[i++] & 0xc0) != 0x80) { throw new
		 * IllegalArgumentException(); } count++; break; case 14: // 1110xxxx
		 * 10xxxxxx 10xxxxxx if (((int)(b[i++] & 0xc0) != 0x80) || ((int)(b[i++]
		 * & 0xc0) != 0x80)) { throw new IllegalArgumentException(); } count++;
		 * break; default: // 10xxxxxx, 1111xxxx throw new
		 * IllegalArgumentException(); } } if (i != max) { throw new
		 * IllegalArgumentException(); } // Now decode the characters... char[]
		 * cs = new char[count]; i = 0; while (off < max) { int c = b[off++] &
		 * 0xff; switch (c >> 4) { case 0: case 1: case 2: case 3: case 4: case
		 * 5: case 6: case 7: // 0xxxxxxx cs[i++] = (char)c; break; case 12:
		 * case 13: // 110xxxxx 10xxxxxx cs[i++] = (char)(((c & 0x1f) << 6) |
		 * (b[off++] & 0x3f)); break; case 14: // 1110xxxx 10xxxxxx 10xxxxxx int
		 * t = (b[off++] & 0x3f) << 6; cs[i++] = (char)(((c & 0x0f) << 12) | t |
		 * (b[off++] & 0x3f)); break; default: // 10xxxxxx, 1111xxxx throw new
		 * IllegalArgumentException(); } } return new String(cs, 0, count);
		 */
	}

	/**
	 * Creates a new <code>ZipEntry</code> object for the specified entry name.
	 * 
	 * @param name
	 *            the ZIP file entry name
	 * @return the ZipEntry just created
	 */
	protected ZipEntry createZipEntry(String name) {
		return new ZipEntry(name);
	}

	/*
	 * Reads end of deflated entry as well as EXT descriptor if present.
	 */
	private void readEnd(ZipEntry e) throws IOException {
		int n = inf.getRemaining();
		if (n > 0) {
			((PushbackInputStream) in).unread(buf, len - n, n);
		}
		if ((flag & 8) == 8) {
			/* EXT descriptor present */
			readFully(tmpbuf, 0, EXTHDR);
			long sig = get32(tmpbuf, 0);
			if (sig != EXTSIG) {
				throw new ZipException("invalid EXT descriptor signature");
			}
			e.setCrc(get32(tmpbuf, EXTCRC));
			e.setCompressedSize(get32(tmpbuf, EXTSIZ));
			e.setSize(get32(tmpbuf, EXTLEN));
		}
		if (e.getSize() != inf.getTotalOut()) {
			throw new ZipException("invalid entry size (expected "
					+ e.getSize() + " but got " + inf.getTotalOut() + " bytes)");
		}
		if (e.getCompressedSize() != inf.getTotalIn()) {
			throw new ZipException("invalid entry compressed size (expected "
					+ e.getCompressedSize() + " but got " + inf.getTotalIn()
					+ " bytes)");
		}
		if (e.getCrc() != crc.getValue()) {
			throw new ZipException("invalid entry CRC (expected 0x"
					+ Long.toHexString(e.getCrc()) + " but got 0x"
					+ Long.toHexString(crc.getValue()) + ")");
		}
	}

	/*
	 * Reads bytes, blocking until all bytes are read.
	 */
	private void readFully(byte[] b, int off, int len) throws IOException {
		while (len > 0) {
			int n = in.read(b, off, len);
			if (n == -1) {
				throw new EOFException();
			}
			off += n;
			len -= n;
		}
	}

	/*
	 * Fetches unsigned 16-bit value from byte array at specified offset. The
	 * bytes are assumed to be in Intel (little-endian) byte order.
	 */
	private static final int get16(byte b[], int off) {
		return (b[off] & 0xff) | ((b[off + 1] & 0xff) << 8);
	}

	/*
	 * Fetches unsigned 32-bit value from byte array at specified offset. The
	 * bytes are assumed to be in Intel (little-endian) byte order.
	 */
	private static final long get32(byte b[], int off) {
		return get16(b, off) | ((long) get16(b, off + 2) << 16);
	}

	private static String newString(byte[] b, int off, int len, String enc)
			throws IOException {
		String ret;
		try {
			if (enc != null) {
				ret = new String(b, off, len, enc);
			} else {
				ret = new String(b, off, len);
			}
		} catch (NoSuchMethodError e) {
			ret = new String(b, off, len);
		}
		return ret;
	}
}
