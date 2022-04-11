package cep;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;




public class Relatorio extends JFrame {
	
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	

	private JPanel contentPane;
	private JTable tablecep;
	private JScrollPane scrollPane;

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio frame = new Relatorio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    @SuppressWarnings("unused")
	public void prenchertabela() {
    	
    	String sql = "select * from cep6";
    	
		
		try {
			pst = conexao.prepareStatement("select * from cep6");
			rs=pst.executeQuery();
			tablecep.getModel();
			DefaultTableModel model =(DefaultTableModel) tablecep.getModel();
			model.setNumRows(0);
	  while (rs.next()) {
		  model.addRow(new Object[] 
			       { 
			          
			          rs.getString("cep"),
			          rs.getString("lagradouro"),
			          rs.getString("complemento"),
			          rs.getString("bairro"),
			          rs.getString("cidade"),
			          rs.getString("estado"),
			       });
	  }
	
	   	
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e);
		}
		
    
    
	}
	public Relatorio() {
		
		getComponents();
		conexao = cep.conexao.conector();
		System.out.println(conexao);
		
		setTitle("Relatório CEP");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 420);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextPane txtpnRelatrioCep = new JTextPane();
		txtpnRelatrioCep.setEditable(false);
		txtpnRelatrioCep.setText("Relatório de CEPs");
		txtpnRelatrioCep.setFont(new Font("Times New Roman", Font.BOLD, 27));
		txtpnRelatrioCep.setBounds(177, 11, 320, 46);
		contentPane.add(txtpnRelatrioCep);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 609, 250);
		contentPane.add(scrollPane);
		
		tablecep = new JTable();
		tablecep.setFillsViewportHeight(true);
		tablecep.setCellSelectionEnabled(true);
		tablecep.setColumnSelectionAllowed(true);
		tablecep.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(tablecep);
		tablecep.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tablecep.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				
			},
			new String[] {
				"CEP", "Lagradouro", "Complemento", "Bairro", "Cidade", "Estado"
			}
		) {
			
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablecep.getColumnModel().getColumn(0).setResizable(false);
		tablecep.getColumnModel().getColumn(1).setResizable(false);
		tablecep.getColumnModel().getColumn(2).setResizable(false);
		tablecep.getColumnModel().getColumn(3).setResizable(false);
		tablecep.getColumnModel().getColumn(4).setResizable(false);
		tablecep.getColumnModel().getColumn(5).setResizable(false);
		tablecep.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnconsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prenchertabela();
			}
		});
		btnconsultar.setBounds(275, 347, 89, 23);
		contentPane.add(btnconsultar);
	}
}
