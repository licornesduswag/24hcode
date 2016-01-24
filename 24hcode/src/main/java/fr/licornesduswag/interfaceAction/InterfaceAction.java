package fr.licornesduswag.interfaceAction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.licornesduswag.hcode.data.ActionDeplacement;
import fr.licornesduswag.hcode.data.Personnage;
import fr.licornesduswag.hcode.data.Piece;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceAction extends JFrame {

	private JPanel contentPane;
	private JTextField descriptionAction;
	private JTextField newX;
	private JTextField newY;
	private JTextField nomPerso;
	private JTextField nomNouveauPerso;
	private JTextField ImagePersoSprite;
	private JTextField imagePersonnageFace;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceAction frame = new InterfaceAction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceAction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAjouterAction = new JButton("Ajouter Action");
		btnAjouterAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActionDeplacement dep = new ActionDeplacement(Integer.parseInt(newX.getText()),Integer.parseInt( newY.getText()), nomPerso.getText(), descriptionAction.getText());
				System.out.println(dep);
			}
		});
		btnAjouterAction.setBounds(236, 76, 228, 23);
		contentPane.add(btnAjouterAction);
		
		descriptionAction = new JTextField();
		descriptionAction.setBounds(107, 61, 86, 20);
		contentPane.add(descriptionAction);
		descriptionAction.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(25, 64, 64, 14);
		contentPane.add(lblDescription);
		
		newX = new JTextField();
		newX.setBounds(107, 102, 86, 20);
		contentPane.add(newX);
		newX.setColumns(10);
		
		newY = new JTextField();
		newY.setBounds(107, 147, 86, 20);
		contentPane.add(newY);
		newY.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nouveau x");
		lblNewLabel.setBounds(25, 105, 64, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nouveau Y");
		lblNewLabel_1.setBounds(25, 150, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		nomPerso = new JTextField();
		nomPerso.setBounds(107, 30, 86, 20);
		contentPane.add(nomPerso);
		nomPerso.setColumns(10);
		
		JLabel lblNomPersonnage = new JLabel("Nom Personnage");
		lblNomPersonnage.setBounds(10, 33, 87, 14);
		contentPane.add(lblNomPersonnage);
		
		JPanel panel = new JPanel();
		panel.setBounds(478, 11, 281, 397);
		contentPane.add(panel);
		panel.setLayout(null);
		
		nomNouveauPerso = new JTextField();
		nomNouveauPerso.setBounds(173, 28, 86, 20);
		panel.add(nomNouveauPerso);
		nomNouveauPerso.setColumns(10);
		
		JLabel lblNomPersonnage_1 = new JLabel("Nom Personnage");
		lblNomPersonnage_1.setBounds(25, 31, 86, 14);
		panel.add(lblNomPersonnage_1);
		
		JLabel lblImagepersonnage = new JLabel("ImagePersonnageSprite");
		lblImagepersonnage.setBounds(10, 80, 121, 14);
		panel.add(lblImagepersonnage);
		
		ImagePersoSprite = new JTextField();
		ImagePersoSprite.setBounds(173, 77, 86, 20);
		panel.add(ImagePersoSprite);
		ImagePersoSprite.setColumns(10);
		
		imagePersonnageFace = new JTextField();
		imagePersonnageFace.setBounds(173, 125, 86, 20);
		panel.add(imagePersonnageFace);
		imagePersonnageFace.setColumns(10);
		
		JLabel lblImagepersonnageface = new JLabel("ImagePersonnageFace");
		lblImagepersonnageface.setBounds(10, 128, 121, 14);
		panel.add(lblImagepersonnageface);
		
		JButton btnAjouterpersonnage = new JButton("AjouterPersonnage");
		btnAjouterpersonnage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personnage p = new Personnage(nomNouveauPerso.getText(), ImagePersoSprite.getText(), imagePersonnageFace.getText());
				Piece.toutLesPersonnagesDuMonde.put(nomNouveauPerso.getText(), p);
				Piece.personnages.add(p);
			}
		});
		btnAjouterpersonnage.setBounds(79, 177, 135, 23);
		panel.add(btnAjouterpersonnage);
	}
}
