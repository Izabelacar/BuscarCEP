package cep;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;

import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cep.conexao;
import cep.buscar;

public class consultaInterna extends JFrame {

	

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private JPanel contentPane;
	private JTextField txtcep;
	private JTextField txtlagra;
	private JTextField txtcomple;
	private JTextField txtbairro;
	private JTextField txtcidade;
	private JTextField txtestado;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consultaInterna frame = new consultaInterna();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void consultar() {
		String sql = "select * from cep6 where cep=?";
				
		try {
			pst= conexao.prepareStatement(sql);
			pst.setString(1,txtcep.getText());
			rs = pst.executeQuery();
		if (rs.next()) {
			txtlagra.setText(rs.getString(2));
			txtcomple.setText(rs.getString(3));
			txtbairro.setText(rs.getString(4));
			txtcidade.setText(rs.getString(5));
			txtestado.setText(rs.getString(6));
			
		} else {
			JOptionPane.showMessageDialog(null, "Esse CEP não consta no Banco de Dados Interno");
			txtcep.setText(null);
			txtlagra.setText(null);
			txtcomple.setText(null);
			txtbairro.setText(null);
			txtcidade.setText(null);
			txtestado.setText(null);
		}
			
		
	} catch (Exception e) {

		JOptionPane.showMessageDialog(null, e);
	}
	}
	private void excluir() {
		int confirma= JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir esse CEP do Banco de Dados Interno ");
		if (confirma== JOptionPane.YES_NO_OPTION) {
			String sql = "delete from cep6 where cep=?";
			try {
			
			pst= conexao.prepareStatement(sql);
			pst.setString(1,txtcep.getText());
			int excluido = pst.executeUpdate();
		if (excluido > 0) {
			JOptionPane.showMessageDialog(null, "CEP excluido");
			txtcep.setText(null);
			txtlagra.setText(null);
			txtcomple.setText(null);
			txtbairro.setText(null);
			txtcidade.setText(null);
			txtestado.setText(null);
				}
		} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e);
		}
		}
	
			
	}
	
	public consultaInterna() {
		getComponents();
		conexao = cep.conexao.conector();
		System.out.println(conexao);
	
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setAutoRequestFocus(false);
		setResizable(false);
		setBounds(100, 100, 593, 363);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnConsultaInterna = new JTextPane();
		txtpnConsultaInterna.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtpnConsultaInterna.setText("CEP:");
		txtpnConsultaInterna.setBounds(107, 11, 54, 27);
		contentPane.add(txtpnConsultaInterna);
		
		
		txtcep = new JTextField();
		txtcep.setBounds(178, 11, 175, 27);
		contentPane.add(txtcep);
		txtcep.setColumns(10);
	
		
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
				
			}
		});
		btnConsultar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		RestrictedTextField validar = new RestrictedTextField(txtcep);
		btnConsultar.setBounds(377, 11, 102, 27);
		contentPane.add(btnConsultar);
	    validar.setOnlyNums(true);
	    validar.setLimit(8);

		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(75, 60, 422, 169);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Lagradouro:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(30, 25, 70, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Complemento:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(30, 50, 84, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Bairro:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(30, 76, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Cidade:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(30, 106, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(30, 131, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtlagra = new JTextField();
		txtlagra.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtlagra.setColumns(10);
		txtlagra.setBounds(110, 22, 268, 20);
		panel.add(txtlagra);
		
		txtcomple = new JTextField();
		txtcomple.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtcomple.setColumns(10);
		txtcomple.setBounds(127, 47, 251, 20);
		panel.add(txtcomple);
		
		txtbairro = new JTextField();
		txtbairro.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtbairro.setColumns(10);
		txtbairro.setBounds(86, 75, 177, 20);
		panel.add(txtbairro);
		
		txtcidade = new JTextField();
		txtcidade.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtcidade.setColumns(10);
		txtcidade.setBounds(86, 103, 169, 20);
		panel.add(txtcidade);
		
		txtestado = new JTextField();
		txtestado.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtestado.setColumns(10);
		txtestado.setBounds(86, 128, 169, 20);
		panel.add(txtestado);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnexcluir.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnexcluir.setBounds(247, 240, 89, 23);
		contentPane.add(btnexcluir);
		
		JButton btnlista = new JButton("Relatório de CEPs Salvos no Banco de Dados Interno");
		btnlista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Relatorio().setVisible(true);
			}
		});
		btnlista.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnlista.setBounds(108, 290, 371, 23);
		contentPane.add(btnlista);
	}
}
