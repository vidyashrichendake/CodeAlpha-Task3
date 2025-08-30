import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 

public class AIChatbot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, String> faqResponses;

    public AIChatbot() {
        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(chatArea);
        add(scroll, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

       
        faqResponses = new HashMap<>();
        faqResponses.put("hi", "Hello! How can I help you?");
        faqResponses.put("hello", "Hi there! What can I do for you?");
        faqResponses.put("how are you", "I'm just a bot, but I'm functioning well!");
        faqResponses.put("what is your name", "I'm JavaBot, your virtual assistant.");
        faqResponses.put("bye", "Goodbye! Have a great day!");
        faqResponses.put("help", "You can ask me general questions like 'What is your name?', 'How are you?', etc.");
        sendButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());
    }

    private void processInput() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) return;

        appendMessage("You: " + userInput);
        String response = getBotResponse(userInput.toLowerCase());
        appendMessage("Bot: " + response);

        inputField.setText("");
    }

    private String getBotResponse(String input) {
        
        for (String key : faqResponses.keySet()) {
            if (input.contains(key)) {
                return faqResponses.get(key);
            }
        }
		
        return "I'm not sure how to respond to that. Try asking something else!";
    }

    private void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AIChatbot().setVisible(true);
        });
    }
}
