package selcore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public abstract class JPanelSelectors extends JPanel implements IActionEvent {

	private static final long serialVersionUID = 1L;

	public JTextField txCssSelector;
	public JTextField txXpath;
	public JTextField txJavascript;

	JButton btFind;

	protected static final String COMMAND_CSS = "css sel.";
	protected static final String COMMAND_XPATH = "xpath";
	protected static final String COMMAND_JAVASCRIPT = "javascript";

	public JPanelSelectors() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		{
			txCssSelector = new JTextField();
			txCssSelector.setBounds(10, 10, 309, 20);
			add(txCssSelector);

			String text = GPref.get().getDefaultCssSelector("div");
			txCssSelector.setText(text);

			btFind = new JButton(COMMAND_CSS);
			btFind.setBounds(339, 10, 87, 20);
			add(btFind);
			btFind.addActionListener(new OnAction(COMMAND_CSS));
			btFind.setToolTipText("Find element by CSS Selector");
			txCssSelector.addKeyListener(new PressEnter(COMMAND_CSS));

		}
		{
			txJavascript = new JTextField();
			txJavascript.setBounds(10, 72, 309, 20);
			add(txJavascript);

			String text = GPref.get().getDefaultJavascript("alert('test');");
			txJavascript.setText(text);

			JButton btnJs = new JButton(COMMAND_JAVASCRIPT);
			btnJs.setToolTipText("execute javascript");
			btnJs.setBounds(339, 72, 87, 20);
			add(btnJs);
			btnJs.addActionListener(new OnAction(COMMAND_JAVASCRIPT));
			txJavascript.addKeyListener(new PressEnter(COMMAND_JAVASCRIPT));
		}
		{
			txXpath = new JTextField();
			txXpath.setBounds(10, 41, 309, 20);
			add(txXpath);

			String text = GPref.get().getDefaultXpath("//body");
			txXpath.setText(text);

			JButton btnXpath = new JButton(COMMAND_XPATH);
			btnXpath.setToolTipText("Find element by XPath");
			btnXpath.setBounds(339, 41, 87, 20);
			add(btnXpath);
			btnXpath.addActionListener(new OnAction(COMMAND_XPATH));
			txXpath.addKeyListener(new PressEnter(COMMAND_XPATH));

		}
	}

	private class PressEnter extends KeyAdapter {
		private final String command;

		public PressEnter(String command) {
			this.command = command;
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				JPanelSelectors.this.onAction(command, e);
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
			JPanelSelectors.this.onAction(command, arg0);
		}
	}
}
