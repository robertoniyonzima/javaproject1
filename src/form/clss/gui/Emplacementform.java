package form.clss.gui;

import form.clss.cde.Emplacement;
import form.clss.cde.Guichet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Emplacementform extends JFrame implements ActionListener {
    JLabel Lidbanque;
    JLabel Lidguichet;
    JLabel Lempla;
    JLabel Lidempla;
    JTextField Tidbanque;
    JTextField Tidguichet;
    JTextField Templa;
    JTextField Tidempla;
    JButton Bajouter;
    JButton Breset;
    JButton Visualiser;
    JButton bupdate;
    JButton bdelete;
    JButton btnRecherche;
    private List<Emplacement> emplas;

    public Emplacementform() {
        super("AJOUTER UNE EMPLACEMENT");
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
        Lempla = new JLabel("EMPLACEMENT:");
        Templa = new JTextField();
        Lidempla = new JLabel("ID EMPLACEMENT:");
        Tidempla = new JTextField();
        Bajouter = new JButton("AJOUTER");
        Breset = new JButton("RESET");
        Visualiser = new JButton("VISUALISER");
        bupdate = new JButton("UPDATE");
        bdelete = new JButton("DELETE");
        btnRecherche = new JButton("RECHERCHE");

        // Ajout des composants de la fenêtre
        cpane.add(Lidempla);
        cpane.add(Tidempla);
        cpane.add(Lempla);
        cpane.add(Templa);
        cpane.add(Lidbanque);
        cpane.add(Tidbanque);
        cpane.add(Lidguichet);
        cpane.add(Tidguichet);
        cpane.add(Bajouter);
        cpane.add(Breset);
        cpane.add(Visualiser);
        cpane.add(bupdate);
        cpane.add(bdelete);
        cpane.add(btnRecherche);

        emplas = new ArrayList<Emplacement>();

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
                int idempla = Integer.parseInt(Tidempla.getText());
                String emplacement = Templa.getText();
                int idbanque = Integer.parseInt(Tidbanque.getText());
                int idguichet = Integer.parseInt(Tidguichet.getText());


                Emplacement gt = new Emplacement( idempla, emplacement, idbanque, idguichet);
                emplas.add(gt);

                JOptionPane.showMessageDialog(this, "Empacemnt ajouter avec succes  !");

                // Réinitialisation des champs
                Tidbanque.setText("");
                Templa.setText("");
                Tidempla.setText("");
                Tidguichet.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour ID EMPLACEMENT et id guichet  IDGUICHET", "Erreur de format", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (event.getSource() == Breset) {
            // Réinitialiser les champs
            Tidbanque.setText("");
            Templa.setText("");
            Tidempla.setText("");
            Tidguichet.setText("");
        } else if (event.getSource() == Visualiser) {
            String[] columns = {"ID EMPLACEMENT", "EMPLACEMENT", "ID BANQUE", "ID GUICHET"};
            Object[][] data = new Object[emplas.size()][columns.length];
            for (int i = 0; i < emplas.size(); i++) {
                Emplacement gt = emplas.get(i);
                data[i][0] = gt.getIdempla();
                data[i][1] = gt.getEmplacement();
                data[i][2] = gt.getIdbanque();
                data[i][3] = gt.getIdguichet();
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Liste des emplacements", JOptionPane.INFORMATION_MESSAGE);

        } else if (event.getSource() == bdelete) {
            int selectedRow = -1;
            if (emplas.size() > 0) {
                String[] options = new String[emplas.size()];
                for (int i = 0; i < emplas.size(); i++) {
                    Emplacement gt = emplas.get(i);
                    options[i] = gt.getEmplacement() + " - " + gt.getIdempla();
                }
                selectedRow = JOptionPane.showOptionDialog(this, "Sélectionnez un emplacement à supprimer:", "Suppression d'une emplacement", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (selectedRow != -1) {
                emplas.remove(selectedRow);
                JOptionPane.showMessageDialog(this, "emplacement supprimé avec succès !");
            }

        } else if (event.getSource() == bupdate) {
            int selectedRow = -1;
            if (emplas.size() > 0) {
                String[] options = new String[emplas.size()];
                for (int i = 0; i < emplas.size(); i++) {
                    Emplacement gt = emplas.get(i);
                    options[i] = gt.getIdempla() + " - " + gt.getEmplacement();
                }
                selectedRow = JOptionPane.showOptionDialog(this, "Sélectionnez un emplcement à modifier:", "Modification de emplacement", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (selectedRow != -1) {
                Emplacement emplaToUpdate = emplas.get(selectedRow);

                // Obtenir les nouvelles valeurs des champs
                String newNumero = JOptionPane.showInputDialog(this, "Nouveau ID emplacement", String.valueOf(emplaToUpdate.getIdempla()));
                String newAdresse = JOptionPane.showInputDialog(this, "Nouveau emplacement:", String.valueOf(emplaToUpdate.getEmplacement()));
                String newProprietaire = JOptionPane.showInputDialog(this, "Nouveau ID banque", String.valueOf(emplaToUpdate.getIdbanque()));
                String newNumeroo = JOptionPane.showInputDialog(this, "Nouveau IDguichet", String.valueOf(emplaToUpdate.getIdguichet()));

                // Mettre à jour le terrain si les nouvelles valeurs sont valides
                if (newNumero != null && !newNumero.isEmpty()) {
                    emplaToUpdate.setIdempla(Integer.parseInt(newNumero));
                }
                if (newAdresse != null && !newAdresse.isEmpty()) {
                    // Corrected line:
                    emplaToUpdate.setEmplacement(newAdresse);
                }
                if (newProprietaire != null && !newProprietaire.isEmpty()) {
                    emplaToUpdate.setIdbanque(Integer.parseInt(newProprietaire));
                }

                if (newNumeroo != null && !newNumeroo.isEmpty()) {
                    emplaToUpdate.setIdguichet(Integer.parseInt(newNumeroo));
                }

                JOptionPane.showMessageDialog(this, "emplacement modifié avec succès !");
            }

        } else if (event.getSource() == btnRecherche) {
            String recherche = JOptionPane.showInputDialog(this, "Saisissez L'emplacement a  rechercher:");
            if (recherche != null && !recherche.isEmpty()) {
                List<Emplacement> foundClients = new ArrayList<>();
                for (Emplacement client : emplas) {
                    if (client.getEmplacement().toLowerCase().contains(recherche.toLowerCase())) {
                        foundClients.add(client);
                    }
                }
                if (foundClients.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aucun emplacement  trouvé avec ce localisation", "Recherche de emplacement", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String[] columns = {"ID EMPLACEMENT", "EMPLACEMENT", "ID BANQUE", "ID QUICHET"};
                    Object[][] data = new Object[foundClients.size()][columns.length];
                    for (int i = 0; i < foundClients.size(); i++) {
                        data[i][0] = foundClients.get(i).getIdempla();
                        data[i][1] = foundClients.get(i).getEmplacement();
                        data[i][2] = foundClients.get(i).getIdbanque();
                        data[i][3] = foundClients.get(i).getIdguichet();
                    }
                    JTable table = new JTable(data, columns);
                    JScrollPane scrollPane = new JScrollPane(table);
                    JOptionPane.showMessageDialog(this, scrollPane, "emplacement  trouvés", JOptionPane.INFORMATION_MESSAGE);
                }
                // Ajouter le comportement pour le bouton Visualiser si nécessaire
            }
        }
    }
}

