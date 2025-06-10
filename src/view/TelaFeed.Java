package view;

import view.TelaConsultaDuvidas;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.border.AbstractBorder;

public class TelaFeed extends JFrame {

    private final JPanel feedPanel;
    private final java.util.List<QuestionPanel> questionPanels = new ArrayList<>();

    public TelaFeed() {
        setTitle("Feed de Dúvidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(24, 32, 24, 32));

        JLabel titleLabel = new JLabel("StudUP");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 48));
        titleLabel.setForeground(new Color(43, 43, 43));

        JButton openConsultaButton = new JButton("Faça a sua pergunta");
        openConsultaButton.setFont(new Font("Inter", Font.BOLD, 16));
        openConsultaButton.setBackground(Color.LIGHT_GRAY);
        openConsultaButton.setForeground(Color.BLACK);
        openConsultaButton.setFocusPainted(false);
        openConsultaButton.setPreferredSize(new Dimension(220, 48));
        openConsultaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openConsultaButton.setBorder(new RoundedBorder(12));
        openConsultaButton.addActionListener(e -> {
            // Abre a tela  TelaConsultaDuvidas
            TelaConsultaDuvidas telaConsulta = new TelaConsultaDuvidas();
            telaConsulta.setVisible(true);
        });

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(openConsultaButton, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Painel do feed com scroll 
        feedPanel = new JPanel();
        feedPanel.setBackground(Color.WHITE);
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBorder(new EmptyBorder(0, 32, 32, 32));

        // Perguntas de exemplo
        addSampleQuestions();

        JScrollPane scrollPane = new JScrollPane(feedPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addSampleQuestions() {
        addQuestion("Como posso aprender Java de forma eficiente?");
        addQuestion("Qual a melhor forma de usar Java Swing para interfaces modernas?");
        addQuestion("Como implementar um sistema de login seguro em Java?");
        addQuestion("Quais as diferenças entre ArrayList e LinkedList?");
        addQuestion("Como faço para melhorar a performance de uma aplicação Java?");
    }

    private void addQuestion(String questionText) {
        QuestionPanel questionPanel = new QuestionPanel(questionText);
        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanels.add(questionPanel);
        feedPanel.add(questionPanel);
        feedPanel.add(Box.createRigidArea(new Dimension(0, 24)));
    }


    private class QuestionPanel extends JPanel {

        private final JTextArea responseArea;

        public QuestionPanel(String questionText) {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setBorder(new CompoundBorder(
                    new LineBorder(new Color(220, 220, 220), 1, true),
                    new EmptyBorder(20, 20, 20, 20)
            ));
            setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
            setOpaque(true);

            JLabel questionLabel = new JLabel("<html><body style='width:740px; font-family: Inter, sans-serif; font-size: 18px; color:#6b7280; font-weight: 600;'>"
                    + questionText + "</body></html>");
            questionLabel.setBorder(new EmptyBorder(0, 0, 16, 0));

            responseArea = new JTextArea(3, 50);
            responseArea.setFont(new Font("Inter", Font.PLAIN, 16));
            responseArea.setLineWrap(true);
            responseArea.setWrapStyleWord(true);
            responseArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            JButton respondButton = new JButton("Responder");
            respondButton.setFont(new Font("Inter", Font.BOLD, 16));
            respondButton.setBackground(new Color(0, 120, 255));
            respondButton.setForeground(Color.BLACK);
            respondButton.setFocusPainted(false);
            respondButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            respondButton.setPreferredSize(new Dimension(140, 40));
            respondButton.setBorder(new RoundedBorder(12));

            respondButton.addActionListener((ActionEvent e) -> {
                String response = responseArea.getText().trim();
                if (response.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaFeed.this,
                            "Por favor, digite uma resposta antes de enviar.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(TelaFeed.this,
                            "Resposta enviada:\n" + response,
                            "Resposta",
                            JOptionPane.INFORMATION_MESSAGE);
                    responseArea.setText("");
                }
            });

            JPanel responsePanel = new JPanel(new BorderLayout(16, 0));
            responsePanel.setBackground(Color.WHITE);
            responsePanel.add(responseArea, BorderLayout.CENTER);
            responsePanel.add(respondButton, BorderLayout.EAST);
            responsePanel.setBorder(new EmptyBorder(16, 0, 0, 0));

            add(questionLabel, BorderLayout.NORTH);
            add(responsePanel, BorderLayout.SOUTH);
        }
    }

    
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(0, 120, 255));
            g2.setStroke(new BasicStroke(1.8f));
            g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius / 2;
            return insets;
        }
    }

    public static void main(String[] args) {
        try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {}

        SwingUtilities.invokeLater(() -> {
            TelaFeed frame = new TelaFeed();
            frame.setVisible(true);
        });
    }
}

