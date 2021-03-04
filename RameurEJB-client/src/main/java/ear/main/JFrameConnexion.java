package ear.main;

import ear.entities.Utilisateur;
import ear.ws.UtilisateurRestfulClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameConnexion extends JFrame {
    private JPanel mainPanel;
    private JTextField identifiant;
    private JPasswordField mdp;
    private JButton seConnecterButton;
    private JTextArea textArea1;

    public JFrameConnexion(String title)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilisateurRestfulClient urc = new UtilisateurRestfulClient();
                try {
                    Utilisateur user = urc.connexion(identifiant.getText(),mdp.getText());
                    textArea1.setText(user.toString());
                } catch (Exception exception) {
                    textArea1.setText("Erreur lors de la connexion");
                }
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrameConnexion("Connexion");
        frame.setVisible(true);
    }
}
