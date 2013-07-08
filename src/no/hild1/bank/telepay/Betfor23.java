package no.hild1.bank.telepay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.hild1.bank.TelepayParserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Betfor23 extends Betfor {

    public Betfor23(BetforHeader header, String stringRecord) throws TelepayParserException {
        super(header, stringRecord);
        log.info(this.stringRecord);
        m = this.betforPattern.matcher(this.stringRecord);

        log.info(this.betforRegexp);
        if (m.matches()) {
            log.info("Found Betfor23");
        } else {
            throw new TelepayParserException(header.getRecordNum(),
                    "Tried to parse BETFOR21, but regexp match failed (HOW ?_?)");
        }
    }
    JButton showHideButton;
    JXCollapsiblePane mainCPanel;
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        JTable table = new JTable();
        table.setEnabled(false);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Key");
        model.addColumn("Value");
        String[] keyValue = new String[2];
        for(Element e: Element.values()) {
            keyValue[0] = e.name();
            keyValue[1] = get(e);
            model.addRow(keyValue);
        }
        table.setModel(model);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        showHideButton = new JButton("Record #" + header.getRecordNum() + ", BETFOR" + header.getBetforTypeString());
        showHideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainCPanel.setCollapsed(!mainCPanel.isCollapsed());
            }
        });
        panel.add(showHideButton);
        mainCPanel = new JXCollapsiblePane();
        mainCPanel.setCollapsed(true);
        mainCPanel.add(table);
        mainCPanel.setCollapsed(false);
        panel.add(mainCPanel);
        return panel;
    }
    public String get(Element e) {
        return m.group(((Element)e).name());
    }

    /* makeBetforData.sh START */
	/* Generated by makeBetforData.sh */
	private static Log log = LogFactory.getLog(Betfor23.class);
	private static String betforRegexp = "^(?<"+ Element.APPLICATIONHEADER.name() + ">.{40})"
		+ "(?<"+ Element.TRANSACTIONCODE.name() + ">.{8})"
		+ "(?<"+ Element.ENTERPRISENUMBER.name() + ">.{11})"
		+ "(?<"+ Element.ACCOUNTNUMBER.name() + ">.{11})"
		+ "(?<"+ Element.SEQUENCECONTROL.name() + ">.{4})"
		+ "(?<"+ Element.REFERENCENUMBER.name() + ">.{6})"
		+ "(?<"+ Element.PAYEEREFINVOICE.name() + ">.{120})"
		+ "(?<"+ Element.KID.name() + ">.{27})"
		+ "(?<"+ Element.OWNREFERENCEINVOICE.name() + ">.{30})"
		+ "(?<"+ Element.INVOICEAMOUNT.name() + ">.{15})"
		+ "(?<"+ Element.DEBITCREDITCODECANCELLATIONCODE.name() + ">.{1})"
		+ "(?<"+ Element.INVOICENUMBER.name() + ">.{20})"
		+ "(?<"+ Element.SERIALNUMBER.name() + ">.{3})"
		+ "(?<"+ Element.CANCELLATIONCAUSE.name() + ">.{1})"
		+ "(?<"+ Element.CUSTOMERNUMBER.name() + ">.{15})"
		+ "(?<"+ Element.INVOICEDATE.name() + ">.{8})"
		+ "$";
	public static Pattern betforPattern = Pattern.compile(betforRegexp);
	Matcher m;
	public enum Element {
		APPLICATIONHEADER, TRANSACTIONCODE, ENTERPRISENUMBER, 
		ACCOUNTNUMBER, SEQUENCECONTROL, REFERENCENUMBER, 
		PAYEEREFINVOICE, KID, OWNREFERENCEINVOICE, 
		INVOICEAMOUNT, DEBITCREDITCODECANCELLATIONCODE, INVOICENUMBER, 
		SERIALNUMBER, CANCELLATIONCAUSE, CUSTOMERNUMBER, 
		INVOICEDATE
	}
    /* makeBetforData.sh STOP */
}
