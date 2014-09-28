package br.edu.LuizMario.jdbc.Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JInternalFrame;

import java.awt.Color;


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
	private JTable tableListagem;
	private JTextField txtNomePesquisa;
	private JTable tablePesquisarPor_2;
	private List<Integer> idPessoas = new ArrayList<Integer>();
	private JTextField txtEdicaoNome;
	private JTextField txtEdicaoCPF;
	private JTextField txtEdicaoEndereco;
	private JTextField txtEdicaoObservacao;
	private JTextField txtEdicaoId;
	private void trazerDoBanco (ClienteDTO clienteDTO){
		this.txtEdicaoNome.setText(clienteDTO.getNomeCliente());
		this.txtEdicaoCPF.setText(clienteDTO.getCPF());
		this.txtEdicaoEndereco.setText(clienteDTO.getEndereco());
		this.txtEdicaoObservacao.setText(clienteDTO.getObservacao());
		this.txtEdicaoId.setText(clienteDTO.getIDCliente().toString());
	}

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
		setBounds(100, 100, 752, 428);
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
					.addContainerGap(75, Short.MAX_VALUE))
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
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panelCadastro.setLayout(gl_panelCadastro);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		);
		
		JPanel panelListagem = new JPanel();
		tabbedPane.addTab("Listagem", null, panelListagem, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		final JInternalFrame internalFrameEdicao = new JInternalFrame("New JInternalFrame");
		internalFrameEdicao.setVisible(false);
		GroupLayout gl_panelListagem = new GroupLayout(panelListagem);
		gl_panelListagem.setHorizontalGroup(
			gl_panelListagem.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelListagem.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(internalFrameEdicao, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelListagem.setVerticalGroup(
			gl_panelListagem.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelListagem.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelListagem.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
						.addComponent(internalFrameEdicao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		
		JLabel lblEdicaoNome = new JLabel("Nome:");
		
		txtEdicaoNome = new JTextField();
		txtEdicaoNome.setColumns(10);
		
		JLabel lblEdicaoCPF = new JLabel("CPF:");
		
		txtEdicaoCPF = new JTextField();
		txtEdicaoCPF.setColumns(10);
		
		JLabel lblEdicaoEndereco = new JLabel("Endere\u00E7o:");
		
		txtEdicaoEndereco = new JTextField();
		txtEdicaoEndereco.setColumns(10);
		
		JLabel lblEdicaoObservacao = new JLabel("Observa\u00E7\u00F5es:");
		
		txtEdicaoObservacao = new JTextField();
		txtEdicaoObservacao.setColumns(10);
		
		JButton btnEdicaoSalvar = new JButton("Salvar");
		btnEdicaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteBO clienteBO = new ClienteBO();
				ClienteDTO clienteDTO = new ClienteDTO();
				try {
					clienteDTO.setNomeCliente(txtEdicaoNome.getText());
					clienteDTO.setCPF(txtEdicaoCPF.getText());
					clienteDTO.setEndereco(txtEdicaoEndereco.getText());
					clienteDTO.setObservacao(txtEdicaoObservacao.getText());
					clienteDTO.setIDCliente(Integer.parseInt(txtEdicaoId.getText()));
					
					clienteBO.alterarCliente(clienteDTO,Integer.parseInt(txtEdicaoId.getText()) );
					MessageUtil.addMesg(jFrmCadastro.this, "Cliente, Atualizado com sucesso !!!");
					
					jFrmCadastro.this.dispose();
					main(null);
					
					internalFrameEdicao.setVisible(false);
					
				}  catch (ClienteException e) {
					e.printStackTrace();
					MessageUtil.addMesg(jFrmCadastro.this, e.getMessage() );
				}
			}
		});
		
		
		
		JButton btnEdicaoCancelar = new JButton("Cancelar");
		btnEdicaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtEdicaoCPF.setText("");
				txtEdicaoNome.setText("");
				txtEdicaoObservacao.setText("");
				txtEdicaoEndereco.setText("");
				internalFrameEdicao.setVisible(false);
			}
		});
		
		JLabel lbEdicaoId = new JLabel("Id");
		lbEdicaoId.setForeground(Color.BLACK);
		
		txtEdicaoId = new JTextField();
		txtEdicaoId.setEditable(false);
		txtEdicaoId.setForeground(Color.RED);
		txtEdicaoId.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(internalFrameEdicao.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEdicaoObservacao, 204, 204, 204)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblEdicaoCPF)
									.addComponent(txtEdicaoCPF, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblEdicaoEndereco)
									.addComponent(txtEdicaoEndereco)
									.addComponent(lblEdicaoObservacao))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEdicaoSalvar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEdicaoCancelar))))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txtEdicaoNome, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
							.addComponent(lblEdicaoNome)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbEdicaoId)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEdicaoId, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(170, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbEdicaoId)
						.addComponent(txtEdicaoId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblEdicaoNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEdicaoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblEdicaoCPF)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEdicaoCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEdicaoEndereco)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEdicaoEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEdicaoObservacao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEdicaoObservacao, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdicaoSalvar)
						.addComponent(btnEdicaoCancelar))
					.addGap(20))
		);
		internalFrameEdicao.getContentPane().setLayout(groupLayout);
		
		tableListagem = new JTable();
		tableListagem.setColumnSelectionAllowed(true);
		
		ClienteBO clienteBO = new ClienteBO();
		
		try {
			String[][] lista = clienteBO.listagem(idPessoas);
			tableListagem.setModel(new DefaultTableModel(
			lista,
			new String[] {"Id", "Nome", "CPF", "Endere\u00E7o", "Observa\u00E7\u00F5es","",""}
		));
			
			Action actionEditar = new AbstractAction() {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent acEvent) {
				final ClienteBO clienteBO = new ClienteBO();
					try {
						int linha = Integer.parseInt(acEvent.getActionCommand());
						ClienteDTO clienteDTO = clienteBO.buscarEdicao(idPessoas.get(linha));
						trazerDoBanco(clienteDTO);
						internalFrameEdicao.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
						MessageUtil.addMesg(jFrmCadastro.this, e.getMessage());
					}
					
				}
			};
			
			
			
			Action actionDeletar = new AbstractAction() {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent acEvent) {
					int resp = JOptionPane.showConfirmDialog(jFrmCadastro.this, "Deseja realmente excluir ? ");
					final ClienteBO clienteBO = new ClienteBO();
					if (resp == 0){
						try {
							JTable table =(JTable) acEvent.getSource();
							int linha = Integer.parseInt(acEvent.getActionCommand());
							((DefaultTableModel) table.getModel()).removeRow(linha);
							clienteBO.removerCliente(idPessoas.get(linha));
							idPessoas.remove(linha);
							JOptionPane.showMessageDialog(jFrmCadastro.this, "Cleinte deletado com sucesso !!!");
						} catch (PersitenciaExecption e) {
							e.printStackTrace();
							MessageUtil.addMesg(jFrmCadastro.this, e.getMessage());
						}
					}
				}
			};
			
			ButtonColumn buttonColumnEditar  = new ButtonColumn(tableListagem, actionEditar, 5);
			ButtonColumn buttonColumnDeletar = new ButtonColumn(tableListagem, actionDeletar, 6);
		} catch (PersitenciaExecption e) {
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(tableListagem);
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
								new String[] {"ID", "Nome", "CPF", "Endereço", "Observações"}
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
			new String[] {"ID", "Nome", "CPF", "Endereço", "Observações"
			}
		));
		scpPesquisaPor.setViewportView(tablePesquisarPor_2);
		panel.setLayout(gl_panel);
		panelPesquisar.setLayout(gl_panelPesquisar);
		contentPane.setLayout(gl_contentPane);
		
	}

}

