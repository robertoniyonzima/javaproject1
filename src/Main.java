import form.clss.gui.Banqueform;
import form.clss.gui.Emplacementform;
import form.clss.gui.Guichetform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    JButton banque;
    JButton emplacement;
    JButton guichet;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
        new Main();
    }

    public Main() {
        super("GESTION DES GUICHET AUTOMATIQUE MENU");

        // Définir les couleurs personnalisées
        Color backgroundColor = new Color(156, 228, 234, 155); // bleu cree
        Color buttonColor = new Color(39, 82, 174); // Vert menthe clair
        Color buttonHoverColor = new Color(28, 47, 172); // Bleu clair
        Color fontColor = new Color(223, 230, 233); // Gris très clair

        // Configurer la fenêtre principale
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout());

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(backgroundColor);

        banque = createButton("AJOUTER UNE BANQUE", buttonColor, buttonHoverColor, fontColor);
        guichet = createButton("AJOUTER UNE GUICHET", buttonColor, buttonHoverColor, fontColor);
        emplacement = createButton("AJOUTER UN EMPLACEMENT", buttonColor, buttonHoverColor, fontColor);

        buttonPanel.add(banque);
        buttonPanel.add(guichet);
        buttonPanel.add(emplacement);

        // Ajouter les panneaux à la fenêtre
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor, Color hoverColor, Color fontColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fontColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Effet de survol
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == banque) {
            Banqueform form1 = new Banqueform();
            form1.setVisible(true);
        } else if (e.getSource() == guichet) {
            Guichetform form2 = new Guichetform();
            form2.setVisible(true);
        } else if (e.getSource() == emplacement) {
            Emplacementform form3 = new Emplacementform();
            form3.setVisible(true);
        }
    }
}