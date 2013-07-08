package no.hild1.bank.telepay;

import no.hild1.bank.TelepayParserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetforHeader {

    private int betforType;
    private int recordNum;

    public BetforHeader(String record, int recordNum) throws TelepayParserException {
        m = betforPattern.matcher(record.substring(0,48));
        log.debug(record.substring(0,48));
        if (m.matches()) {
            String tcode = get(Element.TRANSACTIONCODE);
            log.debug("Parsing " + tcode);
            betforType = Integer.parseInt(tcode.substring(tcode.length()-2,tcode.length()));
            log.debug(get(Element.TRANSACTIONCODE) + " parsed to BETFOR" + betforType);
            this.recordNum = recordNum;
        } else {
            throw new TelepayParserException(recordNum, "Failed to find header in record #" + recordNum);
        }
    }

    public int getBetforType() {
        return betforType;
    }
    public String getBetforTypeString() {
        return String.format("%02d", getBetforType());
    }

    public int getRecordNum() {
        return recordNum;
    }

    public String get(Element e) {
        return m.group(e.name());
    }

    /* makeBetforData.sh START */
	/* Generated by makeBetforData.sh */
	private static Log log = LogFactory.getLog(BetforHeader.class);
	private static String betforRegexp = "^(?<"+ Element.AHID.name() + ">.{2})"
		+ "(?<"+ Element.AHVERSION.name() + ">.{1})"
		+ "(?<"+ Element.AHRETURNCODE.name() + ">.{2})"
		+ "(?<"+ Element.AHPROCEDUREID.name() + ">.{4})"
		+ "(?<"+ Element.AHTRANSACTIONDATE.name() + ">.{4})"
		+ "(?<"+ Element.AHSEQNO.name() + ">.{6})"
		+ "(?<"+ Element.AHTRANSCODE.name() + ">.{8})"
		+ "(?<"+ Element.AHUSERID.name() + ">.{11})"
		+ "(?<"+ Element.AHNOOF80CHAR.name() + ">.{2})"
		+ "(?<"+ Element.TRANSACTIONCODE.name() + ">.{8})";
	public static Pattern betforPattern = Pattern.compile(betforRegexp);
	Matcher m;
	public enum Element {
		AHID, AHVERSION, AHRETURNCODE, 
		AHPROCEDUREID, AHTRANSACTIONDATE, AHSEQNO, 
		AHTRANSCODE, AHUSERID, AHNOOF80CHAR, 
		TRANSACTIONCODE
	}
    /* makeBetforData.sh STOP */
}
