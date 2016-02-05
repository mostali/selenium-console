package selcore;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class JPanelStatus extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lbStatus;

	public JPanelStatus() {
		setLayout(null);
		setBounds(10, 0, 330, 28);

		lbStatus = new JLabel("init");
		lbStatus.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbStatus.setBounds(77, 11, 355, 14);
		add(lbStatus);

		JLabel lbStatusFor = new JLabel("Status:");
		lbStatusFor.setBounds(10, 11, 69, 14);
		add(lbStatusFor);

		lbStatusFor.setLabelFor(lbStatus);
	}

	void setStatus(String status) {
		setStatus(status, null);
	}

	void setStatus(String status, String toolTipMessage) {
		if (toolTipMessage == null)
			toolTipMessage = status;
		lbStatus.setText(status);
		lbStatus.setToolTipText(toolTipMessage);
		getParent().update(getParent().getGraphics());
	}
}
