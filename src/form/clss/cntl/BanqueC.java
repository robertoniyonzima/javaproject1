package form.clss.cntl;

import form.clss.cde.Banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BanqueC{

    private static Connection conn = null;

    // Méthode pour obtenir la connexion (appelée une seule fois au démarrage)
    public static Connection getConnection() {
        String serveur = "localhost";
        int port = 3306;
        String db = "test";
        String user = "root";
        String pass = "";

        try {
            // Charger le driver JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + serveur + ":" + port + "/" + db + "?serverTimezone=UTC";
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour insérer une banque (utilise la connexion existante)
    public static void insert_banque(Banque j) {
        if (conn != null) {
            try (PreparedStatement pstmet = conn.prepareStatement("INSERT INTO test.banque(idbanque, nombanque, telbanque) VALUES(?, ?, ?)")) {
                pstmet.setInt(1, j.getIdbanque());
                pstmet.setString(2, j.getNombanque());
                pstmet.setInt(3, j.getTelbanque());
                pstmet.executeUpdate();
                System.out.println("Insertion réussie !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Échec de la connexion !");
        }
    }

    // Fermeture de la connexion (appelée à la fin de l'application)
    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connexion fermée !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//    public static ArrayList <Agent> view_agent(){
//        ArrayList <Agent> list_agent = new ArrayList();
//        Agent c = null;
//        try{
//            conn = getConnection();
//            stm = conn.createStatement();
//            rs = stm.executeQuery("select * from mercatojava.agent");
//            while(rs.next()){
//                c = new Agent();
//                c.setId_agent(rs.getInt("id_agent"));
//                c.setNom_agent(rs.getString("nom_agent"));
//                c.setDate_naissance(rs.getString("date_naissance"));
//                c.setSexe(rs.getString("sexe"));
//                c.setNationalite(rs.getString("nationalite"));
//                c.setNum(rs.getString("num"));
//                c.setCertification(rs.getString("certification"));
//                c.setExpiration(rs.getString("expiration"));
//                c.setEmail(rs.getString("email"));
//                c.setPhone(rs.getInt("phone"));
//                c.setId_joueur(rs.getInt("id_joueur"));
//                list_agent.add(c);
//            }
//            conn.close();
//            stm.close();
//        }
//        catch(Exception e){
//
//        }
//        return list_agent;
//    }


//    public static Banque search_banque(int idbanque){
//        Banque c = null;
//        try{
//            conn = getConnection();
//            stm = conn.createStatement();
//            rs = stm.executeQuery("select * from test.banque where idbanque='"+idbanque+"'");
//            while(rs.next()){
//                c = new Banque();
//                c.setIdbanque(rs.getInt("idbanque"));
//                c.setNombanque(rs.getString("nombanquet"));
//                c.setTelbanque(rs.getInt("telbanque"));
//
//            conn.close();
//            stm.close();
//            return c;
//        }
//        catch(Exception e){
//            return null;
//        }
//    }
//
//    public static void Delete_banque(Banque cl){
//        Banque cl = null;
//        try{
//            conn = getConnection();
//            stm = conn.createStatement();
//            String s = "delete from test.banque where idbanque='" + cl.getIdbanque() + "'";
//            stm.executeUpdate(s);
//            conn.close();
//            stm.close();
//
//        }
//        catch(Exception e){
//
//        }
//    }
//
//    public static void update_banque(int idbanque, String nombanque, int telbanque){
//        try{
//            conn = getConnection();
//            stm = conn.createStatement();
//            String s = "update test.banque
//            nom_banque='"+nombanque+"', tel_banque='"+telbanque+"'";
//            stm.executeUpdate(s);
//            conn.close();
//            stm.close();
//
//        }
//        catch(Exception e){
//
//        }
//    }
   //  main codes
//import form.clss.gui.Banqueform;
//import form.clss.gui.Emplacementform;
//import form.clss.gui.Guichetform;
//
//import javax.swing.*;
//        import java.awt.*;
//        import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Main extends JFrame implements ActionListener {
//    JButton banque;
//    JButton emplacement;
//    JButton guichet;
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(Main::new);
//    }
//
//    public Main() {
//        super("GESTION DES GUICHET AUTOMATIQUE MENU");
//
//        // Définir les couleurs personnalisées
//        Color backgroundColor = new Color(45, 52, 54); // Gris foncé
//        Color buttonColor = new Color(85, 239, 196); // Vert menthe clair
//        Color buttonHoverColor = new Color(129, 236, 236); // Bleu clair
//        Color fontColor = new Color(223, 230, 233); // Gris très clair
//
//        // Configurer la fenêtre principale
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1200, 600);
//        getContentPane().setBackground(backgroundColor);
//        setLayout(new BorderLayout());
//
//        // Panneau pour les boutons
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
//        buttonPanel.setBackground(backgroundColor);
//
//        banque = createButton("AJOUTER UNE BANQUE", buttonColor, buttonHoverColor, fontColor);
//        guichet = createButton("AJOUTER UNE GUICHET", buttonColor, buttonHoverColor, fontColor);
//        emplacement = createButton("AJOUTER UN EMPLACEMENT", buttonColor, buttonHoverColor, fontColor);
//
//        buttonPanel.add(banque);
//        buttonPanel.add(guichet);
//        buttonPanel.add(emplacement);
//
//        // Ajouter les panneaux à la fenêtre
//        add(buttonPanel, BorderLayout.CENTER);
//
//        setVisible(true);
//    }
//
//    private JButton createButton(String text, Color bgColor, Color hoverColor, Color fontColor) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Arial", Font.BOLD, 16));
//        button.setBackground(bgColor);
//        button.setForeground(fontColor);
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        // Effet de survol
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button.setBackground(hoverColor);
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button.setBackground(bgColor);
//            }
//        });
//
//        button.addActionListener(this);
//        return button;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == banque) {
//            Banqueform form1 = new Banqueform();
//            form1.setVisible(true);
//        } else if (e.getSource() == guichet) {
//            Guichetform form2 = new Guichetform();
//            form2.setVisible(true);
//        } else if (e.getSource() == emplacement) {
//            Emplacementform form3 = new Emplacementform();
//            form3.setVisible(true);
//        }
//    }
//}
