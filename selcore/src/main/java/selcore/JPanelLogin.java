package selcore;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public abstract class JPanelLogin extends JPanel implements IActionEvent {

	private static final long serialVersionUID = 1L;

	public JTextField txURL;

	JButton btConnect;

	public JTextField textProxy;
	public JCheckBox chbProxy;

	private static final String DEF_PROXY = "130.193.65.155:3128";

	protected static final String COMMAND_REMEMBER = "Remember";
	protected static final String COMMAND_PROXY = "proxy";

	protected static final String COMMAND_CLOSE = "X";
	protected static final String COMMAND_RESTART = "~";

	protected static final String COMMAND_OPEN = "Open";

	public JCheckBox chbRemember;
	private JButton btnClose;
	private JButton btnRestart;

	public JPanelLogin() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		txURL = new JTextField();
		txURL.setBounds(10, 11, 331, 20);
		add(txURL);

		String text = GPref.get().getDefaultLocation("http://www.google.com");
		txURL.setText(text);

		btConnect = new JButton(COMMAND_OPEN);
		btConnect.setToolTipText("Open page");
		btConnect.setBounds(208, 38, 98, 20);
		add(btConnect);
		btConnect.addActionListener(new OnAction(COMMAND_OPEN));
		txURL.addKeyListener(new PressEnter(COMMAND_OPEN));

		textProxy = new JTextField(DEF_PROXY);
		textProxy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textProxy.setEnabled(false);
		textProxy.setBounds(20, 38, 125, 20);
		add(textProxy);

		chbProxy = new JCheckBox(COMMAND_PROXY);
		chbProxy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chbProxy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textProxy.setEnabled(chbProxy.isSelected());
			}
		});
		chbProxy.setBounds(150, 38, 80, 23);
		add(chbProxy);

		chbRemember = new JCheckBox(COMMAND_REMEMBER);
		chbRemember.setSelected(true);
		chbRemember.setToolTipText("remember me");
		chbRemember.setVerticalAlignment(SwingConstants.BOTTOM);
		chbRemember.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chbRemember.setBounds(347, 9, 85, 23);
		add(chbRemember);

		btnClose = new JButton(COMMAND_CLOSE);
		btnClose.setToolTipText("Close Browser");
		btnClose.setBounds(376, 38, 50, 20);
		add(btnClose);
		btnClose.addActionListener(new OnAction(COMMAND_CLOSE));

		btnRestart = new JButton(COMMAND_RESTART);
		btnRestart.setToolTipText("restart page");
		btnRestart.setBounds(316, 38, 50, 20);
		add(btnRestart);
		btnRestart.addActionListener(new OnAction(COMMAND_RESTART));
	}

	public void setEnabledConnect(boolean enabled, boolean buttonActionEnabled) {
		txURL.setEnabled(enabled);
		chbRemember.setEnabled(enabled);

		btConnect.setEnabled(buttonActionEnabled);

		{// PROXY PANEL
			chbProxy.setEnabled(enabled);
			if (enabled) {
				textProxy.setEnabled(chbProxy.isSelected());
			} else
				textProxy.setEnabled(enabled);
		}
		super.setEnabled(enabled);
	}

	public String getProxyLocation() {
		if (chbProxy.isSelected())
			return textProxy.getText();
		return null;
	}

	private class PressEnter extends KeyAdapter {
		private final String command;

		public PressEnter(String command) {
			this.command = command;
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				JPanelLogin.this.onAction(command, e);
			}
		}
	}

	private class OnAction implements ActionListener {
		private final String command;

		public OnAction(String command) {
			this.command = command;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JPanelLogin.this.onAction(command, arg0);
		}
	}
}
