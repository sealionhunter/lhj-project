package test;

public class ChargeMailSend {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long ts = 0;
		long jt = 200;
		long jpt = 100;
		String jpf = "1";
		String jf = "1";
		int sc = 0;
		long ct = 25;
		long tt = 0;

		System.out.println("送信回数\t送信前TimeSum1\t送信後TimeSum1\tTimeSum2");
		while (tt <= 48000) {
			ts += ct;
			tt += ct;
			if (isNeedSendMail2(ts, jpt, jt, jpf, jf, sc)) {
				sc++;
				System.out.print(sc + "\t" + ts);
				
				ts %= time_;
				System.out.println("\t" + ts + "\t" + tt);
			}
		}
	}

	static long time_ = 0;

	/**
	 * isNeedSendMail
	 * 
	 * @param infoEntity
	 * @param notifyEntity
	 * @return
	 */
	private static boolean isNeedSendMail(long timeSum, long joblogPeriodTime,
			long joblogTime, String joblogPeriodFlag, String joblogFlag,
			int sendCount) {

		String firstFlg = joblogPeriodFlag;
		String otherFlg = joblogFlag;

		long firstTime = joblogPeriodTime;
		long otherTime = joblogTime;

		if (firstTime == 0 || otherTime == 0) {
			return false;
		}

//		firstTime = firstTime * 3600000;
//		otherTime = otherTime * 3600000;

		if (firstFlg == null && otherFlg == null) {
			return false;
		}

		if (otherFlg == null || !otherFlg.equals("1")) {
			return false;
		}

		if (firstFlg == null || !firstFlg.equals("1")) {
			if (otherTime <= timeSum) {
				time_ = firstTime;
				return true;
			}
		}

		if (firstFlg.equals("1")) {
			if (sendCount > 0) {
				if (otherTime <= timeSum) {
					time_ = firstTime;
					return true;
				} else {
					return false;
				}
			}

			if (firstTime <= timeSum) {
				time_ = firstTime;
				return true;
			}
		}

		return false;

		
		
	}
	
	/**
	 * isNeedSendMail
	 * 
	 * @param infoEntity
	 * @param notifyEntity
	 * @return
	 */
	private static boolean isNeedSendMail2(long timeSum, long joblogPeriodTime,
			long joblogTime, String joblogPeriodFlag, String joblogFlag,
			int sendCount) {
		// 初期通知フラグ
		String firstFlg = joblogFlag;
		if (firstFlg == null || !firstFlg.equals("1")) {
			return false;
		}
		// 再通知フラグ
		String otherFlg = joblogPeriodFlag;
		long firstTime = 0;
		long otherTime = 0;
//		try {
//			// 初期通知の閾値時間
//			String dbFirstTime = joblogTime;
//			// 再通知の閾値時間
//			String dbOtherTime = joblogPeriodTime;
//			if (dbFirstTime != null && dbFirstTime.trim().length() > 0) {
				firstTime = joblogTime;
//				if (firstTime == 0) {
//					return false;
//				}
//			} else {
//				return false;
//			}
//			if (dbOtherTime != null && dbOtherTime.trim().length() > 0) {
				otherTime = joblogPeriodTime;
//			}
//		}  catch (Exception e) {
//			LOG.debug(e);
//		}
		
		// 再通知が設定されていない場合
		if(otherFlg == null || !otherFlg.equals("1")) {
			if (firstTime <= timeSum
					&& sendCount == 0) {
				time_ = firstTime;
				return true;
			}
		} else {
			if (sendCount == 0) {
				if (firstTime <= timeSum) {
					time_ = firstTime;
					return true;
				}				
			} else {
				if (otherTime <= timeSum) {
					time_ = otherTime;
					return true;
				}
			}
		}
	
		return false;
		
	
	}
}
