package no.hild1.bank.telepay;

import no.hild1.bank.TelepayParserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Betfor00 extends Betfor {

    public Betfor00(BetforHeader header, String stringRecord) throws TelepayParserException {
        super(header, stringRecord);
        log.debug(this.stringRecord);
        m = this.betforPattern.matcher(this.stringRecord);

        log.debug(this.betforRegexp);
        if (m.matches()) {
            log.info("Record #" + header.getRecordNum()
                    + " (staring at line " + (header.getRecordNum()*4) + ") is a BETFOR00");
        } else {
            String error = "Failed to parse record #" + header.getRecordNum()
                    + " (staring at line " + (header.getRecordNum()*4) + ") as a BETFOR00";
            log.error(error);
            throw new TelepayParserException(header.getRecordNum(),
                    error);
        }
    }

    @Override
    public Color getColor(ElementInterface e) {
        switch (((Element)e)) {
            case ENTERPRISENUMBER:
                return Color.MAGENTA;
            default:
                return null;
        }
    }

    /* makeBetforData.sh START */
	/* Generated by makeBetforData.sh */
	private static Log log = LogFactory.getLog(Betfor00.class);
	private static String betforRegexp = "^(?<"+ Element.APPLICATIONHEADER.name() + ">.{40})"
		+ "(?<"+ Element.TRANSACTIONCODE.name() + ">.{8})"
		+ "(?<"+ Element.ENTERPRISENUMBER.name() + ">.{11})"
		+ "(?<"+ Element.DIVISION.name() + ">.{11})"
		+ "(?<"+ Element.SEQUENCECONTROL.name() + ">.{4})"
		+ "(?<"+ Element.RESERVED1.name() + ">.{6})"
		+ "(?<"+ Element.PRODUCTIONDATE.name() + ">.{4})"
		+ "(?<"+ Element.PASSWORD.name() + ">.{10})"
		+ "(?<"+ Element.VERSION.name() + ">.{10})"
		+ "(?<"+ Element.NEWPASSWORD.name() + ">.{10})"
		+ "(?<"+ Element.OPERATORNO.name() + ">.{11})"
		+ "(?<"+ Element.SIGILLSEALUSE.name() + ">.{1})"
		+ "(?<"+ Element.SIGILLSEALDATE.name() + ">.{6})"
		+ "(?<"+ Element.SIGILLPARTKEY.name() + ">.{20})"
		+ "(?<"+ Element.SIGILLSEALHOW.name() + ">.{1})"
		+ "(?<"+ Element.RESERVED2.name() + ">.{143})"
		+ "(?<"+ Element.OWNREFERENCEBATCH.name() + ">.{15})"
		+ "(?<"+ Element.RESERVED3.name() + ">.{9})"
		+ "$";
	public static Pattern betforPattern = Pattern.compile(betforRegexp);
	public enum Element implements ElementInterface {
		APPLICATIONHEADER, TRANSACTIONCODE, ENTERPRISENUMBER, 
		DIVISION, SEQUENCECONTROL, RESERVED1, 
		PRODUCTIONDATE, PASSWORD, VERSION, 
		NEWPASSWORD, OPERATORNO, SIGILLSEALUSE, 
		SIGILLSEALDATE, SIGILLPARTKEY, SIGILLSEALHOW, 
		RESERVED2, OWNREFERENCEBATCH, RESERVED3
	}
	public ElementInterface[] getElements() { return Element.values(); }
    /* makeBetforData.sh STOP */
}
