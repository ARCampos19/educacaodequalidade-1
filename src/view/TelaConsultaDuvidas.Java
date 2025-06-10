package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TelaConsultaDuvidas extends JFrame {
    private static final ArrayList<String> duvidas = new ArrayList<>();
    private final JTextArea areaDuvidas;
    private JTextField campoDuvida;

    public TelaConsultaDuvidas() {
        setTitle("StudUP - Consulta de Dúvidas");
        setSize(480, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Cabeçalho, nome do app
        JLabel cabecalho = new JLabel("StudUP", SwingConstants.CENTER);
        cabecalho.setFont(new Font("Inter", Font.BOLD, 48));
        cabecalho.setForeground(new Color(43, 43, 43));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(32, 20, 40, 20));
        add(cabecalho, BorderLayout.NORTH);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        // Label para instrução
        JLabel labelDigite = new JLabel("Digite sua dúvida:");
        labelDigite.setFont(new Font("Inter", Font.BOLD, 24));
        labelDigite.setForeground(new Color(107, 114, 128));
        labelDigite.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelPrincipal.add(labelDigite);

       
        campoDuvida = new JTextField();
        campoDuvida.setFont(new Font("Inter", Font.PLAIN, 22));
        campoDuvida.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        campoDuvida.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoDuvida.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 16)));
        painelPrincipal.add(campoDuvida);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 24));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnAdicionar = criarBotao("Adicionar");
        btnAdicionar.addActionListener((ActionEvent e) -> {
            adicionarDuvida(campoDuvida.getText());
            campoDuvida.setText("");
        });

        JButton btnListar = criarBotao("Dúvidas");
        btnListar.addActionListener((ActionEvent e) -> listarDuvidas());

        JButton btnVoltar = criarBotao("Voltar");
        btnVoltar.addActionListener((ActionEvent e) -> {
            dispose();
            new TelaFeed().setVisible(true);
        });

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnVoltar);

        painelPrincipal.add(painelBotoes);

     
        areaDuvidas = new JTextArea(14, 40);
        areaDuvidas.setFont(new Font("Inter", Font.PLAIN, 20));
        areaDuvidas.setEditable(false);
        areaDuvidas.setForeground(new Color(107, 114, 128));
        areaDuvidas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)
        ));

        JScrollPane scrollPane = new JScrollPane(areaDuvidas);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setBorder(null);

        painelPrincipal.add(scrollPane);

        add(painelPrincipal, BorderLayout.CENTER);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Inter", Font.BOLD, 20));
        botao.setBackground(Color.BLACK);
        botao.setForeground(Color.BLACK);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(180, 52));
        botao.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        botao.setAlignmentY(Component.CENTER_ALIGNMENT);
      
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(33,33,33)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.BLACK); 
            }
        });
        return botao;
    }

    private void adicionarDuvida(String duvida) {
        if (!duvida.trim().isEmpty()) {
            duvidas.add(duvida);
            JOptionPane.showMessageDialog(this, "Dúvida adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma dúvida válida.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void listarDuvidas() {
        areaDuvidas.setText("");
        if (duvidas.isEmpty()) {
            areaDuvidas.setText("Nenhuma dúvida cadastrada.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String d : duvidas) {
                sb.append("• ").append(d).append("\n");
            }
            areaDuvidas.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaConsultaDuvidas());
    }
}
