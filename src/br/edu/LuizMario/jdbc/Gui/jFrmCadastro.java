package br.edu.LuizMario.jdbc.Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import br.edu.LuizMario.jdbc.BO.ClienteBO;
import br.edu.LuizMario.jdbc.DTO.ClienteDTO;
import br.edu.LuizMario.jdbc.Excption.ClienteException;
import br.edu.LuizMario.jdbc.Excption.PersitenciaExecption;
import br.edu.LuizMario.jdbc.Excption.ValidacaoException;
import br.edu.LuizMario.jdbc.Util.MessageUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;



public class jFrmCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtObservacao;
	private JTable table;
	private JTextField txtNomePesquisa;
	private JTable tablePesquisarPor_2;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrmCadastro frame = new jFrmCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PersitenciaExecption 
	 */
	public jFrmCadastro() throws PersitenciaExecption {
		setTitle("Sisema de Cadastro Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelCadastro = new JPanel();
		tabbedPane.addTab("Cadastro", null, panelCadastro, null);
		
		JLabel lblNome = new JLabel("Nome");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel label = new JLabel("CPF");
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		
		JLabel label_1 = new JLabel("Endereco");
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observacoes");
		
		txtObservacao = new JTextField();
		txtObservacao.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			
			ClienteDTO clienteDTO = new ClienteDTO();
			try {
				
				String msg = null;
				
				msg = txtCpf.getText();

				clienteDTO.setCPF(msg);
				clienteDTO.setEndereco(txtEndereco.getText());
				clienteDTO.setNomeCliente(txtNome.getText());
				clienteDTO.setObservacao(txtObservacao.getText());

				ClienteBO clienteBO = new ClienteBO();
				
				try {
					if ( clienteBO.validarCadastro(clienteDTO) ) {
						clienteBO.Inserir(clienteDTO); 
						MessageUtil.addMesg(jFrmCadastro.this, "Cliente, Cadastrado com Sucesso !!!");
						jFrmCadastro.this.dispose();
						main(null);}
				} catch (ValidacaoException e) {
					e.printStackTrace();
					MessageUtil.addMesg(jFrmCadastro.this, e.getMessage());
				}
				
			} catch (ClienteException e) {
				e.printStackTrace();
				MessageUtil.addMesg(jFrmCadastro.this, "Dados Inválidos");
			}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				txtNome.setText("");
				txtCpf.setText("");
				txtEndereco.setText("");
				txtObservacao.setText("");
				
			}
		});
		GroupLayout gl_panelCadastro = new GroupLayout(panelCadastro);
		gl_panelCadastro.setHorizontalGroup(
			gl_panelCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCadastro.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCadastro.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panelCadastro.createSequentialGroup()
								.addComponent(lblNome)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtNome))
							.addGroup(gl_panelCadastro.createSequentialGroup()
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelCadastro.createSequentialGroup()
								.addComponent(lblObservaes)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtObservacao, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelCadastro.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCadastro.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panelCadastro.setVerticalGroup(
			gl_panelCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCadastro.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panelCadastro.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCadastro.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCadastro.createParallelGroup(Alignment.LEADING)
						.addComponent(txtObservacao, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblObservaes))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCadastro.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnLimpar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelCadastro.setLayout(gl_panelCadastro);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel panelListagem = new JPanel();
		tabbedPane.addTab("Listagem", null, panelListagem, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panelListagem = new GroupLayout(panelListagem);
		gl_panelListagem.setHorizontalGroup(
			gl_panelListagem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelListagem.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelListagem.setVerticalGroup(
			gl_panelListagem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelListagem.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		
		ClienteBO clienteBO = new ClienteBO();
		
		try {
			String[][] lista = clienteBO.listagem();
			table.setModel(new DefaultTableModel(
			lista,
			new String[] {
				"Id", "Nome", "CPF", "Endere\u00E7o", "Observa\u00E7\u00F5es"
			}
		));
		} catch (PersitenciaExecption e) {
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(table);
		panelListagem.setLayout(gl_panelListagem);
		
		JPanel panelPesquisar = new JPanel();
		tabbedPane.addTab("Pesquisar Por", null, panelPesquisar, null);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		
		txtNomePesquisa = new JTextField();
		txtNomePesquisa.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nome;
					nome = txtNomePesquisa.getText();
					ClienteBO clienteBO = new ClienteBO();
					nome = txtNomePesquisa.getText();
					
					String[][] lista = clienteBO.buscarPor(nome);
					if (lista.length > 0){
						tablePesquisarPor_2.setModel(new DefaultTableModel(
								lista,
								new String[] {
									"ID", "Nome", "CPF", "Endereço", "Observações"}
							));	
					} else {
						tablePesquisarPor_2 = new JTable();
					}
				} catch (PersitenciaExecption e) {
					e.printStackTrace();
					MessageUtil.addMesg(jFrmCadastro.this, e.getMessage());
				}
			}
		});
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listagem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panelPesquisar = new GroupLayout(panelPesquisar);
		gl_panelPesquisar.setHorizontalGroup(
			gl_panelPesquisar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPesquisar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPesquisar.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelPesquisar.createSequentialGroup()
							.addComponent(lblNome_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNomePesquisa, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPesquisar)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelPesquisar.setVerticalGroup(
			gl_panelPesquisar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPesquisar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPesquisar.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome_1)
						.addComponent(txtNomePesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JScrollPane scpPesquisaPor = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpPesquisaPor, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scpPesquisaPor, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		
		tablePesquisarPor_2 = new JTable();
		tablePesquisarPor_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CPF", "Endereço", "Observações"
			}
		));
		scpPesquisaPor.setViewportView(tablePesquisarPor_2);
		panel.setLayout(gl_panel);
		panelPesquisar.setLayout(gl_panelPesquisar);
		contentPane.setLayout(gl_contentPane);
	}
}
