package form.clss.gui;

import form.clss.cde.Banque;
import form.clss.cde.Guichet;
import form.clss.cntl.BanqueC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Guichetform extends JFrame implements ActionListener {
    JLabel Lidbanque;
    JLabel Lidguichet;
    JLabel Llocguichet;
    JLabel Ltypeguichet;
    JTextField Tidbanque;
    JTextField Tidguichet;
    JTextField Tlocguichet;
    JTextField Ttypeguichet;
    JButton Bajouter;
    JButton Breset;
    JButton Visualiser;
    JButton bupdate;
    JButton bdelete;
    JButton btnRecherche;
    private List<Guichet> guichets;

    public Guichetform() {
        super("AJOUTER UNE GUICHET");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel cpane = (JPanel) this.getContentPane();
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 20, 5));
//        setVisible(true);

        Lidbanque = new JLabel("ID BANQUE:");
        Tidbanque = new JTextField();
        Lidguichet = new JLabel("ID GUICHET:");
        Tidguichet = new JTextField();
        Llocguichet = new JLabel("LOCALISATION:");
        Tlocguichet = new JTextField();
        Ltypeguichet = new JLabel("TYPE GUICHET:");
        Ttypeguichet = new JTextField();
        Bajouter = new JButton("AJOUTER");
        Breset = new JButton("RESET");
        Visualiser = new JButton("VISUALISER");
        bupdate = new JButton("UPDATE");
        bdelete = new JButton("DELETE");
        btnRecherche = new JButton("RECHERCHE");

        // Ajout des composants de la fenêtre
        cpane.add(Lidguichet);
        cpane.add(Tidguichet);
        cpane.add(Ltypeguichet);
        cpane.add(Ttypeguichet);
        cpane.add(Lidbanque);
        cpane.add(Tidbanque);
        cpane.add(Llocguichet);
        cpane.add(Tlocguichet);
        cpane.add(Bajouter);
        cpane.add(Breset);
        cpane.add(Visualiser);
        cpane.add(bupdate);
        cpane.add(bdelete);
        cpane.add(btnRecherche);

        guichets = new ArrayList<Guichet>();

        // Ajout des listeners
        Bajouter.addActionListener(this);
        Breset.addActionListener(this);
        Visualiser.addActionListener(this);
        bupdate.addActionListener(this);
        bdelete.addActionListener(this);
        btnRecherche.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == Bajouter) {
            try {
                int idbanque = Integer.parseInt(Tidbanque.getText());
                String locaguichet = Tlocguichet.getText();
                String typeguichet = Ttypeguichet.getText();
                int idguichet = Integer.parseInt(Tidguichet.getText());


                Guichet gt = new Guichet(idguichet, locaguichet, typeguichet, idbanque);
                guichets.add(gt);

                JOptionPane.showMessageDialog(this, "Banque ajoutée avec succès !");

                // Réinitialisation des champs
                Tidbanque.setText("");
                Tlocguichet.setText("");
                Ttypeguichet.setText("");
                Tidguichet.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour ID banque et id guichet", "Erreur de format", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (event.getSource() == Breset) {
            // Réinitialiser les champs
            Tidbanque.setText("");
            Tlocguichet.setText("");
            Ttypeguichet.setText("");
            Tidguichet.setText("");
        } else if (event.getSource() == Visualiser) {
            String[] columns = {"ID GUICHET", "TYPE GUICHET", "LOCALISTION", "ID BANQUE"};
            Object[][] data = new Object[guichets.size()][columns.length];
            for (int i = 0; i < guichets.size(); i++) {
                Guichet gt = guichets.get(i);
                data[i][0] = gt.getIdguichet();
                data[i][1] = gt.getTypeguichet();
                data[i][2] = gt.getLocaguichet();
                data[i][3] = gt.getIdbanque();
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Liste des guichets", JOptionPane.INFORMATION_MESSAGE);

        } else if (event.getSource() == bdelete) {
            int selectedRow = -1;
            if (guichets.size() > 0) {
                String[] options = new String[guichets.size()];
                for (int i = 0; i < guichets.size(); i++) {
                    Guichet gt = guichets.get(i);
                    options[i] = gt.getTypeguichet() + " - " + gt.getLocaguichet();
                }
                selectedRow = JOptionPane.showOptionDialog(this, "Sélectionnez un guichet à supprimer:", "Suppression d'une guichet", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (selectedRow != -1) {
                guichets.remove(selectedRow);
                JOptionPane.showMessageDialog(this, "guichet supprimé avec succès !");
            }

        } else if (event.getSource() == bupdate) {
            int selectedRow = -1;
            if (guichets.size() > 0) {
                String[] options = new String[guichets.size()];
                for (int i = 0; i < guichets.size(); i++) {
                    Guichet gt = guichets.get(i);
                    options[i] = gt.getIdguichet() + " - " + gt.getLocaguichet();
                }
                selectedRow = JOptionPane.showOptionDialog(this, "Sélectionnez un guichet à modifier:", "Modification de guiichet", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (selectedRow != -1) {
                Guichet guichetToUpdate = guichets.get(selectedRow);

                // Obtenir les nouvelles valeurs des champs
                String newNumero = JOptionPane.showInputDialog(this, "Nouveau IDguichet", String.valueOf(guichetToUpdate.getIdbanque()));
                String newAdresse = JOptionPane.showInputDialog(this, "Nouveau TYPE:", guichetToUpdate.getTypeguichet());
                String newProprietaire = JOptionPane.showInputDialog(this, "Nouveau localisation", String.valueOf(guichetToUpdate.getLocaguichet()));
                String newNumeroo = JOptionPane.showInputDialog(this, "Nouveau IDbanque", String.valueOf(guichetToUpdate.getIdbanque()));
                // Mettre à jour le terrain si les nouvelles valeurs sont valides
                if (newNumero != null && !newNumero.isEmpty()) {
                    guichetToUpdate.setIdbanque(Integer.parseInt(newNumero));
                }
                if (newAdresse != null && !newAdresse.isEmpty()) {
                    guichetToUpdate.setTypeguichet(newAdresse);
                }
                if (newProprietaire != null && newProprietaire.isEmpty()) {
                    guichetToUpdate.setLocaguichet(newProprietaire);
                }

                if (newNumeroo  != null && !newNumeroo .isEmpty()) {
                    guichetToUpdate.setIdguichet(Integer.parseInt(newNumeroo ));
                }


                JOptionPane.showMessageDialog(this, "guichet modifié avec succès !");
            }
        } else if (event.getSource() == btnRecherche) {
            String recherche = JOptionPane.showInputDialog(this, "Saisissez La localisation de la Guichet a  rechercher:");
            if (recherche != null && !recherche.isEmpty()) {
                List<Guichet> foundClients = new ArrayList<>();
                for (Guichet client : guichets) {
                    if (client.getLocaguichet().toLowerCase().contains(recherche.toLowerCase()) || client.getTypeguichet().toLowerCase().contains(recherche.toLowerCase())) {
                        foundClients.add(client);
                    }
                }
                if (foundClients.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aucun guichet trouvé avec ce localisation ou type", "Recherche de guichet", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String[] columns = {"ID GUICHET", "TYPE GUICHET", "LOCALISATION", "ID BANQUE"};
                    Object[][] data = new Object[foundClients.size()][columns.length];
                    for (int i = 0; i < foundClients.size(); i++) {
                        data[i][0] = foundClients.get(i).getIdguichet();
                        data[i][1] = foundClients.get(i).getTypeguichet();
                        data[i][2] = foundClients.get(i).getLocaguichet();
                        data[i][3] = foundClients.get(i).getIdbanque();
                    }
                    JTable table = new JTable(data, columns);
                    JScrollPane scrollPane = new JScrollPane(table);
                    JOptionPane.showMessageDialog(this, scrollPane, "guichet  trouvés", JOptionPane.INFORMATION_MESSAGE);
                }
                // Ajouter le comportement pour le bouton Visualiser si nécessaire
            }
        }
    }
}
