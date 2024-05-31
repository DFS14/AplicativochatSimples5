package chatApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class ChatApp1 extends JFrame implements ActionListener {

    private static final long serialVersionUID = 232198682572775569L;
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;
    private String userName;

    public ChatApp1(String userName) {
        this.userName = userName;

        setTitle("Chat - " + userName);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(30);
        textArea = new JTextArea(10, 30);
        textArea.setBackground(new Color(255, 255, 196));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        sendButton = new JButton("Enviar");
        sendButton.setBackground(new Color(234, 253, 198));
        sendButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(218, 232, 233));
        panel.add(textField);
        panel.add(sendButton);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        String userName = JOptionPane.showInputDialog("Entre com seu nome de usuário:");
        if (userName != null && !userName.isEmpty()) {
            SwingUtilities.invokeLater(() -> new ChatApp(userName));
        } else {
            JOptionPane.showMessageDialog(null, "O nome de usuário não pode ficar vazio. Saindo...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String message = textField.getText().trim();
            if (!message.isEmpty()) {
                appendMessage(userName + ": " + message);
                textField.setText("");
            }
        }
    }

    private void appendMessage(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
