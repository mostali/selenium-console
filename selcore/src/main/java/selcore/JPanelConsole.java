package selcore;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class JPanelConsole extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txConsole;

	public JPanelConsole() {
		setLayout(null);
		setBounds(10, 0, 433, 110);

		JButton btnClearConsole = new JButton("clear");
		btnClearConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanelConsole.this.txConsole.setText("");
			}

		});
		btnClearConsole.setToolTipText("Open page");
		btnClearConsole.setBounds(343, 85, 80, 20);
		add(btnClearConsole);

		txConsole = new JTextField();
		txConsole.setToolTipText("Console");
		txConsole.setBackground(SystemColor.control);
		txConsole.setBounds(10, 11, 413, 73);
		add(txConsole);
		txConsole.setColumns(10);
	}

	public void log(String message) {

		txConsole.setText(txConsole.getText() + "\n" + message);

		getParent().update(getParent().getGraphics());

	}
}
