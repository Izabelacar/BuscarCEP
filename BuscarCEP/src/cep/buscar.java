package cep;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import cep.conexao;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;


public class buscar extends JFrame {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JPanel contentPane;
	private JTextField txtcep;
	private JTextField txtlagradouro;
	private JTextField txtcomple;
	private JTextField txtbairro;
	private JTextField txtcidade;
	private JTextField txtestado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buscar frame = new buscar();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void adicionar() {

		String sql = "insert into cep6 (cep, lagradouro, complemento, bairro, cidade, estado) values (?,?,?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);

			pst.setString(1, txtcep.getText());
			pst.setString(2, txtlagradouro.getText());
			pst.setString(3, txtcomple.getText());
			pst.setString(4, txtbairro.getText());
			pst.setString(5, txtcidade.getText());
			pst.setString(6, txtestado.getText());

			int adicionado = pst.executeUpdate();
			if (adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
				txtcep.setText(null);
				txtlagradouro.setText(null);
				txtcomple.setText(null);
				txtbairro.setText(null);
				txtcidade.setText(null);
				txtestado.setText(null);
			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null,"CEP já consta no Banco de Dados Interno");
		}
	}

	public buscar() {
		setBackground(new Color(255, 255, 255));

		getComponents();
		conexao = cep.conexao.conector();
		System.out.println(conexao);

		setTitle(" Buscar CEP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 657, 419);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Entre com o CEP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(111, 26, 105, 27);
		contentPane.add(lblNewLabel);

		txtcep = new JTextField();
		txtcep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		txtcep.setBounds(243, 27, 120, 24);
		contentPane.add(txtcep);
		txtcep.setColumns(10);

		JButton btnbuscarcep = new JButton("Buscar CEP");
		btnbuscarcep.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnbuscarcep.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				if (txtcep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Erro");
					txtcep.requestFocus();
				} else {
					buscarCep();
				}
			}

		});
		btnbuscarcep.setBounds(403, 28, 105, 23);
		contentPane.add(btnbuscarcep);
		RestrictedTextField validar = new RestrictedTextField(txtcep);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(94, 87, 432, 148);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(24, 110, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_5 = new JLabel("Cidade:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(24, 85, 46, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_3 = new JLabel("Bairro:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(24, 60, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Complemento:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(24, 35, 84, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_2 = new JLabel("Lagradouro:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(24, 11, 70, 14);
		panel.add(lblNewLabel_2);

		txtlagradouro = new JTextField();
		txtlagradouro.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtlagradouro.setBounds(104, 8, 268, 20);
		panel.add(txtlagradouro);
		txtlagradouro.setColumns(10);

		txtcomple = new JTextField();
		txtcomple.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtcomple.setBounds(121, 32, 251, 20);
		panel.add(txtcomple);
		txtcomple.setColumns(10);

		txtbairro = new JTextField();
		txtbairro.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtbairro.setBounds(70, 57, 177, 20);
		panel.add(txtbairro);
		txtbairro.setColumns(10);

		txtcidade = new JTextField();
		txtcidade.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtcidade.setBounds(80, 82, 169, 20);
		panel.add(txtcidade);
		txtcidade.setColumns(10);

		txtestado = new JTextField();
		txtestado.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtestado.setBounds(70, 107, 177, 20);
		panel.add(txtestado);
		txtestado.setColumns(10);

		JButton salvar = new JButton("Adicionar no Banco Interno");
		salvar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		salvar.setBounds(214, 262, 208, 23);
		contentPane.add(salvar);
		validar.setOnlyNums(true);
		validar.setLimit(8);

	}

	private void buscarCep() {
		String lagradouro = "";
		String tipolagradouro = "";
		String resultado = null;
		String cep = txtcep.getText();
		try {
			URL url = new URL("http://viacep.com.br/ws/" + cep + "/xml/");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("logradouro")) {
					txtlagradouro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("complemento")) {
					txtcomple.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					txtestado.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtbairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("localidade")) {
					txtcidade.setText(element.getText());
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"CEP não existe");
		}
		System.out.println();
	}
}
