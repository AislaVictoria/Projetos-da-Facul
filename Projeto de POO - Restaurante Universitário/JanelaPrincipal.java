
package ru;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class JanelaPrincipal extends JFrame implements ActionListener
{
    //Atributos
    private JPanel pnDados, pnBotoes;
    private JLabel lbDiaSem,lbPrincipal1, lbPrincipal2, lbProteina, lbCarboidrato, lbSalada, lbBebida, lbSobremesa, lbFruta; 
    private JComboBox<String> cbDiaSem, cbPrincipal1, cbPrincipal2, cbProteina, cbCarboidrato, cbSalada, cbBebida, cbSobremesa, cbFruta;
    private Cardapio cardapio;
    private List<Cardapio> listaCardapio = new ArrayList<>();
    private ListIterator<Cardapio> regCardapio;
    private List<Cardapio> listaCardapioOriginal = new ArrayList<>();
    private JButton btCadastrar, btAlterar, btVisualizar, btSair;
    //Dias da semana por sequência
    private final Map<String, Integer> diaSequencia = new HashMap<String, Integer>()
    {
        {
        put("Segunda",1);
        put("Terça",2);
        put("Quarta",3);
        put("Quinta",4);
        put("Sexta",5);
        }
    };
    
    public JanelaPrincipal()
    {
        //Montando a janela
        this.setSize(600,300);
        this.setTitle("CARDAPIO RESTAURANTE UNIVERSITÁRIO");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Criando os botões
        btCadastrar = new JButton ("Cadastrar");
        btAlterar = new JButton ("Alterar");
        btVisualizar = new JButton ("Visualizar");
        btSair = new JButton ("Sair");
        
        //Criando os paineis
        pnBotoes = new JPanel();
        pnBotoes.setLayout(new GridLayout(1,6));
        pnDados = new JPanel();
        pnDados.setLayout(new GridLayout(5,4));
        
        //Colocar os botoes ao painel de botoes
        pnBotoes.add(btCadastrar);
        pnBotoes.add(btAlterar);
        pnBotoes.add(btVisualizar);
        pnBotoes.add(btSair);
        
        //Configurando a janela principal
        this.setLayout(new BorderLayout());
        this.add(pnBotoes, BorderLayout.SOUTH);
        this.add(pnDados, BorderLayout.CENTER);
        
        //Elementos da janela
        lbDiaSem = new JLabel("Dia: ");
        lbPrincipal1 = new JLabel("Principal: ");
        lbPrincipal2 = new JLabel("Acompanhante: ");
        lbCarboidrato = new JLabel("Carboidrato: ");
        lbProteina = new JLabel("Proteína: ");
        lbSalada = new JLabel("Salada: ");
        lbBebida = new JLabel("Suco: ");
        lbSobremesa = new JLabel("Sobremesa: ");
        lbFruta = new JLabel("Fruta: ");
        
        //Opções que contem no cardapio
        cbDiaSem = new JComboBox<>(new String[]{"Segunda","Terça","Quarta","Quinta","Sexta","Sábado","Domingo"});
        cbPrincipal1 = new JComboBox<>(new String[]{"Arroz Branco", "Carreteiro", "Galinhada","Risoto"});
        cbPrincipal2 = new JComboBox<>(new String[]{"Feijão com caldo", "Tropeiro", "Tutu", "Strogonoff", "Feijoada"});
        cbCarboidrato = new JComboBox<>(new String[]{"Macarrão", "Batata", "Lasanha", "Nhoque", "Purê"});
        cbProteina = new JComboBox<>(new String[]{"Bife", "Costela", "Almondega", "Frango", "Fricassê", "Peixe", "Ovo"});
        cbSalada = new JComboBox<>(new String[]{"Alface", "Tomate", "Pepino", "Repolho", "Cenoura", "Beterraba", "Vinagrete"});
        cbBebida = new JComboBox<>(new String[]{"Uva","Laranja", "Morango", "Limão", "Goiaba", "Maracujá", "Abacaxi", "Melancia"});
        cbSobremesa = new JComboBox<>(new String[]{"Sorvete", "Doce", "Gelatina", "Pudim", "Mousse", "Pavê"});
        cbFruta = new JComboBox<>(new String[]{"Mamão", "Maçã", "Banana", "Laranja", "Mexerica", "Melancia", "Morango"});
        
        //Colocando dados no painel de dados
        pnDados.add(lbDiaSem);
        pnDados.add(cbDiaSem);
        pnDados.add(lbPrincipal1);
        pnDados.add(cbPrincipal1);
        pnDados.add(lbPrincipal2);
        pnDados.add(cbPrincipal2);
        pnDados.add(lbCarboidrato);
        pnDados.add(cbCarboidrato);
        pnDados.add(lbProteina);
        pnDados.add(cbProteina);
        pnDados.add(lbSalada);
        pnDados.add(cbSalada);
        pnDados.add(lbBebida);
        pnDados.add(cbBebida);
        pnDados.add(lbSobremesa);
        pnDados.add(cbSobremesa);
        pnDados.add(lbFruta);
        pnDados.add(cbFruta);
        
        //Ação dos botoes
        btCadastrar.addActionListener(this);
        btVisualizar.addActionListener(this);
        btAlterar.addActionListener(this);
        btSair.addActionListener(this);
        
        //Abrir janela
        this.setVisible(true);
        
        
        listaCardapioOriginal.addAll(listaCardapio);
}
        
        //Função de cadastrar cardapio
       private void CadastroCardapio() {
    String diaSelecionado = cbDiaSem.getSelectedItem().toString();
    String principalSelecionado = cbPrincipal1.getSelectedItem().toString();
    String acompanhanteSelecionado = cbPrincipal2.getSelectedItem().toString();
    String carboidratoSelecionado = cbCarboidrato.getSelectedItem().toString();
    String proteinaSelecionada = cbProteina.getSelectedItem().toString();
    String saladaSelecionada = cbSalada.getSelectedItem().toString();
    String bebidaSelecionada = cbBebida.getSelectedItem().toString();
    String sobremesaSelecionada = cbSobremesa.getSelectedItem().toString();
    String frutaSelecionada = cbFruta.getSelectedItem().toString();

    // Verifica se já existe um cardápio cadastrado para o dia selecionado
    for (Cardapio cardapio : listaCardapio) {
        if (cardapio.getDiaSem().equals(diaSelecionado)) {
            JOptionPane.showMessageDialog(null, "Já existe um cardápio cadastrado para este dia");
            return;
        }
    }

    // Cria um novo cardápio e adiciona à lista
    Cardapio novoCardapio = new Cardapio(
        diaSelecionado, principalSelecionado, acompanhanteSelecionado, carboidratoSelecionado,
        proteinaSelecionada, saladaSelecionada, bebidaSelecionada, sobremesaSelecionada, frutaSelecionada
    );
    listaCardapio.add(novoCardapio);
    regCardapio = listaCardapio.listIterator();
    regCardapio.forEachRemaining(p -> regCardapio.next());

    // Atualiza a lista original para backup
    listaCardapioOriginal.clear();
    listaCardapioOriginal.addAll(listaCardapio); 

    // Exibe mensagem de confirmação
    JOptionPane.showMessageDialog(null, "Cardápio cadastrado com sucesso!");
}

        //Função para encerrar programa
        private void EncerraApp()
        {
            String title = "Confirmação";
            String message = "Deseja realmente Sair?";
            int retry = JOptionPane.showConfirmDialog(null, message, getTitle(), JOptionPane.YES_NO_OPTION);
            if(retry == 0)
            {
                System.exit(0);
            }
        }
        
        //Função para visualizar cardapio
        public void visualizar() throws IOException
        {
            if(listaCardapio.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Nenhum cardápio cadastrado!");
                return;
            }
            //Ordem sequencial do carrdapio
            Collections.sort(listaCardapio, (c1,c2) -> {
                int prioridade1 = diaSequencia.get(c1.getDiaSem());
                int prioridade2 = diaSequencia.get(c2.getDiaSem());
                return Integer.compare(prioridade1, prioridade2);
            });
            listaCardapio.forEach(item -> JOptionPane.showMessageDialog(null, item));
            for(Cardapio cardapio : listaCardapio)
            {
                try{
                    FileWriter writer = new FileWriter(cardapio.getDiaSem()+".txt");
                    writer.write(cardapio.toString()+"\n");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Cardápio salvo em arquivo txt");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar o cardapio");
                }
            }
        }
        
            //Função para alterar cardapio
            private void alterar()
            {
                if(listaCardapio.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Nenhum cardapio cadastrado para fazer alteração");
                    return;
                }
                JFrame alterarFrame = new JFrame ("Alterar Cardapio");
                alterarFrame.setSize(600,300);
                alterarFrame.setLayout(new GridLayout(5,4));
                
                JComboBox<String> cbDiasAlterar = new JComboBox<>();
                for(Cardapio cardapio : listaCardapio)
                {
                    cbDiasAlterar.addItem(cardapio.getDiaSem());
                }
        JLabel novolbPrincipal1 = new JLabel("NOVO Principal: ");
                JComboBox<String> novocbPrincipal1 = new JComboBox<>(new String[]{"Arroz Branco", "Carreteiro", "Galinhada","Risoto"});
        JLabel novolbPrincipal2 = new JLabel("NOVO Acompanhante: ");
               JComboBox<String> novocbPrincipal2 = new JComboBox<>(new String[]{"Feijão com caldo", "Tropeiro", "Tutu", "Strogonoff", "Feijoada"});

        JLabel novolbCarboidrato = new JLabel("NOVO Carboidrato: ");
                JComboBox<String> novocbCarboidrato = new JComboBox<>(new String[]{"Macarrão", "Batata", "Lasanha", "Nhoque", "Purê"});

        JLabel novolbProteina = new JLabel("NOVO Proteína: ");
                JComboBox<String> novocbProteina = new JComboBox<>(new String[]{"Bife", "Costela", "Almondega", "Frango", "Fricassê", "Peixe", "Ovo"});

        JLabel novolbSalada = new JLabel("NOVO Salada: ");
                JComboBox<String> novocbSalada = new JComboBox<>(new String[]{"Alface", "Tomate", "Pepino", "Repolho", "Cenoura", "Beterraba", "Vinagrete"});

        JLabel novolbBebida = new JLabel("NOVO Suco: ");
                JComboBox<String> novocbBebida = new JComboBox<>(new String[]{"Uva","Laranja", "Morango", "Limão", "Goiaba", "Maracujá", "Abacaxi", "Melancia"});

        JLabel novolbSobremesa = new JLabel("NOVO Sobremesa: ");
                JComboBox<String> novocbSobremesa = new JComboBox<>(new String[]{"Sorvete", "Doce", "Gelatina", "Pudim", "Mousse", "Pavê"});

        JLabel novolbFruta = new JLabel("NOVO Fruta: ");
                JComboBox<String> novocbFruta = new JComboBox<>(new String[]{"Mamão", "Maçã", "Banana", "Laranja", "Mexerica", "Melancia", "Morango"});

        JButton btConfirmarAlteracao = new JButton ("Confirmar alteração");
        
        alterarFrame.add(new JLabel("Selecione o dia: "));
        alterarFrame.add(cbDiasAlterar);
        alterarFrame.add(novolbPrincipal1);
        alterarFrame.add(novocbPrincipal1);
        alterarFrame.add(novolbPrincipal2);
        alterarFrame.add(novocbPrincipal2);
        alterarFrame.add(novolbCarboidrato);
        alterarFrame.add(novocbCarboidrato);
        alterarFrame.add(novolbProteina);
        alterarFrame.add(novocbProteina);
        alterarFrame.add(novolbSalada);
        alterarFrame.add(novocbSalada);
        alterarFrame.add(novolbBebida);
        alterarFrame.add(novocbBebida);
        alterarFrame.add(novolbSobremesa);
        alterarFrame.add(novocbSobremesa);
        alterarFrame.add(novolbFruta);
        alterarFrame.add(novocbFruta);
        alterarFrame.add(btConfirmarAlteracao,BorderLayout.SOUTH);

        btConfirmarAlteracao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectdDia = cbDiasAlterar.getSelectedItem().toString();
                String selectdPrincipal = novocbPrincipal1.getSelectedItem().toString();
                String selectdAcompanhante = novocbPrincipal2.getSelectedItem().toString();
                String selectdCarboidrato = novocbCarboidrato.getSelectedItem().toString();
                String selectdProteina = novocbProteina.getSelectedItem().toString();
                String selectdSalada = novocbSalada.getSelectedItem().toString();
                String selectdBebida = novocbBebida.getSelectedItem().toString();
                String selectdSobremesa = novocbSobremesa.getSelectedItem().toString();
                String selectdFruta = novocbFruta.getSelectedItem().toString();
                for(Cardapio cardapio : listaCardapio)
                {
                    if(cardapio.getDiaSem().equals(selectdDia))
                    {
                        JOptionPane.showMessageDialog(null, "Cardápio alterado com sucesso!");
                        alterarFrame.dispose();
                        listaCardapioOriginal.clear();
                        listaCardapioOriginal.addAll(listaCardapio);
                        break;
}
}
}
});
                alterarFrame.setVisible(true);
                alterarFrame.setLocationRelativeTo(null);
                alterarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

            
        

    @Override
    public void actionPerformed(ActionEvent e) {
if(e.getSource() == btSair)
{
    EncerraApp();
} else if(e.getSource() == btCadastrar) {
    CadastroCardapio();
} else if(e.getSource() == btVisualizar)
{
    try {
        visualizar();
    } catch (IOException ex) {
        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    }
} else if(e.getSource() == btAlterar)
{
    alterar();
}   
    }
    
    
}


