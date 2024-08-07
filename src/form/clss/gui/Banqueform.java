package form.clss.gui;

import form.clss.cde.Banque;
import form.clss.cntl.BanqueC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Banqueform extends JFrame implements ActionListener {
    JLabel Lidbanque;
    JLabel Lnombanque;
    JLabel Ltelbanque;
    JTextField Tidbanque;
    JTextField Tnombanque;
    JTextField Ttelbanque;
    JButton Bajouter;
    JButton Breset;
    JButton Visualiser;
    JButton bupdate;
    JButton bdelete;
    JButton btnRecherche;
    private List<Banque> banques;

    public Banqueform() {
        super("AJOUTER UNE BANQUE");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel cpane = (JPanel) this.getContentPane();
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 20, 5));
//        setVisible(true);

        Lidbanque = new JLabel("ID BANQUE:");
        Tidbanque = new JTextField();
        Lnombanque = new JLabel("NOM DE LA BANQUE:");
        Tnombanque = new JTextField();
        Ltelbanque = new JLabel("TEL DE LA BANQUE:");
        Ttelbanque = new JTextField();
        Bajouter = new JButton("AJOUTER");
        Breset = new JButton("RESET");
        Visualiser = new JButton("VISUALISER");
        bupdate = new JButton("UPDATE");
        bdelete = new JButton("DELETE");
        btnRecherche = new JButton("RECHERCHE");

        // Ajout des composants de la fenêtre
        cpane.add(Lidbanque);
        cpane.add(Tidbanque);
        cpane.add(Lnombanque);
        cpane.add(Tnombanque);
        cpane.add(Ltelbanque);
        cpane.add(Ttelbanque);
        cpane.add(Bajouter);
        cpane.add(Breset);
        cpane.add(Visualiser);
        cpane.add(bupdate);
        cpane.add(bdelete);
        cpane.add(btnRecherche);

        banques = new ArrayList<Banque>();

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
                String nombanque = Tnombanque.getText();
                int telbanque = Integer.parseInt(Ttelbanque.getText());

                System.out.println("ID: " + idbanque + ", Nom: " + nombanque + ", Tel: " + telbanque);

                Banque banque1 = new Banque(idbanque, nombanque, telbanque);
                banques.add(banque1);

                JOptionPane.showMessageDialog(this, "Banque ajoutée avec succès !");

                // Réinitialisation des champs
                Tidbanque.setText("");
                Tnombanque.setText("");
                Ttelbanque.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour ID et Téléphone.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (event.getSource() == Breset) {
            // Réinitialiser les champs
            Tidbanque.setText("");
            Tnombanque.setText("");
            Ttelbanque.setText("");
        } else if (event.getSource() == Visualiser) {
            String[] columns = {"ID BANQUE: ", "NOM BANQUE: ", "TEL BANQUE: "};
            Object[][] data = new Object[banques.size()][columns.length];
            for (int i = 0; i < banques.size(); i++) {
                Banque banque1 = banques.get(i);
                data[i][0] = banque1.getIdbanque();
                data[i][1] = banque1.getNombanque();
                data[i][2] = banque1.getTelbanque();
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Liste des Banques", JOptionPane.INFORMATION_MESSAGE);

        } else if (event.getSource() == bdelete) {
            int selectedRow = -1;
            if (banques.size() > 0) {
                String[] options = new String[banques.size()];
                for (int i = 0; i < banques.size(); i++) {
                    Banque banque1 = banques.get(i);
                    options[i] = banque1.getIdbanque() + " - " + banque1.getNombanque();
                }
                selectedRow = JOptionPane.showOptionDialog(this, "Sélectionnez un baanque à supprimer:", "Suppression d'une Banque", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (selectedRow != -1) {
                banques.remove(selectedRow);
                JOptionPane.showMessageDialog(this, "banque supprimé avec succès !");
            }

        } else if (event.getSource() == bupdate) {
            int selectedRow = -1;
            if (banques.size() > 0) {
                String[] options = new String[banques.size()];
                for (int i = 0; i < banques.size(); i++) {
                    Banque banque1 = banques.get(i);
                    options[i] = banque1.getIdbanque() + " - " + banque1.getNombanque();
                }
                selectedRow = JOptionPane.showOptionDialog(this, "Sélectionnez un banque à modifier:", "Modification de Banque", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (selectedRow != -1) {
                Banque banqueToUpdate = banques.get(selectedRow);

                // Obtenir les nouvelles valeurs des champs
                String newNumero = JOptionPane.showInputDialog(this, "Nouveau ID:", String.valueOf(banqueToUpdate.getIdbanque()));
                String newAdresse = JOptionPane.showInputDialog(this, "Nouveau NOM:", banqueToUpdate.getNombanque());
                String newProprietaire = JOptionPane.showInputDialog(this, "Nouveau TEL", String.valueOf(banqueToUpdate.getTelbanque()));

                // Mettre à jour le terrain si les nouvelles valeurs sont valides
                if (newNumero != null && !newNumero.isEmpty()) {
                    banqueToUpdate.setIdbanque(Integer.parseInt(newNumero));
                }
                if (newAdresse != null && !newAdresse.isEmpty()) {
                    banqueToUpdate.setNombanque(newAdresse);
                }
                if (newProprietaire != null && !newProprietaire.isEmpty()) {
                    banqueToUpdate.setTelbanque(Integer.parseInt(newProprietaire));
                }


                JOptionPane.showMessageDialog(this, "Banque modifié avec succès !");
            }
        } else if (event.getSource() == btnRecherche) {
            String recherche = JOptionPane.showInputDialog(this, "Saisissez Le nom de la Banque a  rechercher:");
            if (recherche != null && !recherche.isEmpty()) {
                List<Banque> foundClients = new ArrayList<>();
                for (Banque client : banques) {
                    if (client.getNombanque().toLowerCase().contains(recherche.toLowerCase())) {
                        foundClients.add(client);
                    }
                }
                if (foundClients.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aucun Banque trouvé avec ce nom ou prénom.", "Recherche de Banque", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String[] columns = {"ID Banque", "NOM BANQUE", "TEL BANQUE"};
                    Object[][] data = new Object[foundClients.size()][columns.length];
                    for (int i = 0; i < foundClients.size(); i++) {
                        data[i][0] = foundClients.get(i).getIdbanque();
                        data[i][1] = foundClients.get(i).getNombanque();
                        data[i][2] = foundClients.get(i).getTelbanque();
                    }
                    JTable table = new JTable(data, columns);
                    JScrollPane scrollPane = new JScrollPane(table);
                    JOptionPane.showMessageDialog(this, scrollPane, "Banque  trouvés", JOptionPane.INFORMATION_MESSAGE);
                }
                // Ajouter le comportement pour le bouton Visualiser si nécessaire
            }
        }
    }
}