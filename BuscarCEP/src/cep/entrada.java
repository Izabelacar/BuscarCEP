package cep;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import cep.conexao;
import cep.buscar;
import cep.consultaInterna;
import javax.swing.SwingConstants;

public class entrada extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entrada frame = new entrada();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public entrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAutoRequestFocus(false);
		setBackground(Color.WHITE);
		setTitle("Bem Vindo !");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnBuscarCep = new JTextPane();
		txtpnBuscarCep.setForeground(Color.BLUE);
		txtpnBuscarCep.setFont(new Font("Times New Roman", Font.PLAIN, 39));
		txtpnBuscarCep.setText(" Buscar CEP");
		txtpnBuscarCep.setBounds(104, 58, 228, 62);
		contentPane.add(txtpnBuscarCep);

		JButton bntbuscar = new JButton("Consultar Banco Via CEP");
		bntbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new buscar().setVisible(true);
			}
		});
		bntbuscar.setBounds(21, 154, 181, 37);
		contentPane.add(bntbuscar);

		JButton bntconsultainterna = new JButton("Consulta Banco Interno");
		bntconsultainterna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new consultaInterna().setVisible(true);
			}
		});
		bntconsultainterna.setBounds(225, 154, 199, 37);
		contentPane.add(bntconsultainterna);
	}
}
