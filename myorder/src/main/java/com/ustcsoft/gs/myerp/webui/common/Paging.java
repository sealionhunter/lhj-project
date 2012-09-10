package com.ustcsoft.gs.myerp.webui.common;

public class Paging {
	private int rcount = 0;
	private int pcurrent = 0;
	private int pcount = 0;
	private int percount = 10;
	private String toPage = "";

	/**
	 * @return the rcount
	 */
	public int getRcount() {
		return rcount;
	}

	/**
	 * @param rcount
	 *            the rcount to set
	 */
	public void setRcount(int rcount) {
		this.rcount = rcount;
		reset();
	}

	/**
	 * @return the current
	 */
	public int getPcurrent() {
		return pcurrent;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setPcurrent(int current) {
		this.pcurrent = current;
	}

	/**
	 * @return the pcount
	 */
	public int getPcount() {
		return pcount;
	}

	/**
	 * @param pcount
	 *            the pcount to set
	 */
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}

	/**
	 * @return the percount
	 */
	public int getPercount() {
		return percount;
	}

	/**
	 * @param percount
	 *            the percount to set
	 */
	public void setPercount(int percount) {
		this.percount = percount;
	}

	/**
	 * @return the toPage
	 */
	public String getToPage() {
		return toPage;
	}

	/**
	 * @param toPage
	 *            the toPage to set
	 */
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}

	public void first() {
		pcurrent = pcount == 0 ? 0 : 1;
	}

	public void last() {
		pcurrent = pcount;
	}

	public void next() {
		pcurrent++;
		reset();
	}

	public void back() {
		pcurrent--;
		reset();
	}

	public void toPage() {
		pcurrent = Integer.parseInt(toPage);
		reset();
	}

	public void reset() {
		if (rcount < 0) {
			rcount = 0;
		}
		pcount = rcount / percount + (rcount % percount != 0 ? 1 : 0);
		if (pcurrent < 1) {
			pcurrent = 1;
		}
		if (pcurrent > pcount) {
			pcurrent = pcount == 0 ? 0 : 1;
		}
	}

	public void clear() {
		rcount = 0;
		pcurrent = 0;
		pcount = 0;
		percount = 10;
	}
}
