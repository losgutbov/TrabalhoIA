/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Augusto
 */
public class Interface extends JFrame{
    
    private JPanel mapa = new JPanel(), controles = new JPanel(), painelInfo = new JPanel();
    private JPanel painelAtual = new JPanel(), painelLog = new JPanel();;
    private int altura, largura, posiX, posiY, indexPainelAtual, indexPainelLog;
    private ArrayList<JLabel> terrenos = new ArrayList<>();
    private ImageIcon[] tipoter = new ImageIcon[5]; 
    private ImageIcon[] tipoAvat = new ImageIcon[5]; 
    private JButton esquerda, direita, avanca, volta, lancaPokebola;
    private JLabel pontos = new JLabel();
    private RegrasFuncionamento regras;
    
    //-------------------------MÉTODOS GET E SET---------------------------------//
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getPosiX() {
        return posiX;
    }

    public void setPosiX(int posiX) {
        this.posiX = posiX;
    }

    public int getPosiY() {
        return posiY;
    }

    public void setPosiY(int posiY) {
        this.posiY = posiY;
    }

    public RegrasFuncionamento getRegras() {
        return regras;
    }

    public void setRegras(RegrasFuncionamento regras) {
        this.regras = regras;
    }

    public int getIndexPainelAtual() {
        return indexPainelAtual;
    }

    public void setIndexPainelAtual(int indexPainelAtual) {
        this.indexPainelAtual = indexPainelAtual;
    }

    public int getIndexPainelLog() {
        return indexPainelLog;
    }

    public void setIndexPainelLog(int indexPainelLog) {
        this.indexPainelLog = indexPainelLog;
    }
    
    //-------------------------FIM MÉTODOS GET E SET---------------------------------//
    
    //-------------------------MÉTODOS GENÉRICOS-------------------------------------//
    private void adicionar(Component ob){
        this.add(ob);
    }
    private void posicionarPersonagem(int i, int j){
        int indexTerrenos = (i*42)+j;
        this.terrenos.get(indexTerrenos).setIcon(tipoAvat[this.getRegras().getMatrizTerreno()[i][j]]);
        regras.setPosicaoAtual(i, j);
    }

    public void outroPosicionamento(int X, int Y){
        int i=Y,j=X;
        int indexTerrenos = (i*42)+j;
        terrenos.get(indexTerrenos).setIcon(tipoAvat[this.getRegras().getMatrizTerreno()[i][j]]);
    }
    
    private boolean movimentar(int sentidoLin, int sentidoCol){
        if((sentidoLin<0) && (sentidoCol<0)){return false;}
        int lin = this.regras.getPosicaoAtual()[0], col = this.regras.getPosicaoAtual()[1];
        int indexTerrenos = (lin*42)+col;
        this.terrenos.get(indexTerrenos).setIcon(tipoter[this.getRegras().getMatrizTerreno()[lin][col]]);
        posicionarPersonagem(sentidoLin, sentidoCol);
        return true;
    }
    //-----------------------FIM MÉTODOS GENÉRICOS-----------------------------------//
    
    //-------------------------MÉTODO CONSTRUTOR-------------------------------------//
    public Interface(int altura, int largura, RegrasFuncionamento regra){
        setAltura(altura);
        setLargura(largura);
        defineTipos();
        defineAvatar();
        this.setRegras(regra);
    }    
    //-------------------------FIM MÉTODO CONSTRUTOR--------------------------------//
    
    //--------------------MÉTODOS DE DEFINIÇÃO DE ÍCONES----------------------------//
    private void defineTipos(){ //Carrega as imagens para os icones que serão usados nos labels;
        tipoter[0] = new ImageIcon(getClass().getResource("imagens/grama.png"));
        tipoter[1] = new ImageIcon(getClass().getResource("imagens/agua.png"));
        tipoter[2] = new ImageIcon(getClass().getResource("imagens/montanha.png"));
        tipoter[3] = new ImageIcon(getClass().getResource("imagens/caverna.png"));
        tipoter[4] = new ImageIcon(getClass().getResource("imagens/vulcao.png"));
    }
    
    private void defineAvatar(){ //Carrega as imagens para os icones que serão usados nos labels quando troca o avatar;
        tipoAvat[0] = new ImageIcon(getClass().getResource("imagens/avatarGrama.png"));
        tipoAvat[1] = new ImageIcon(getClass().getResource("imagens/avatarAgua.png"));
        tipoAvat[2] = new ImageIcon(getClass().getResource("imagens/avatarMontanha.png"));
        tipoAvat[3] = new ImageIcon(getClass().getResource("imagens/avatarCaverna.png"));
        tipoAvat[4] = new ImageIcon(getClass().getResource("imagens/avatarVulcao.png"));
    }
    
    private ImageIcon definePokemon(int numPok){
        ImageIcon pokemon = new ImageIcon(getClass().getResource("imagens/pokemons/"+numPok+".png"));
        return pokemon;
    }
    //--------------------FIM MÉTODOS DE DEFINIÇÃO DE ÍCONES----------------------------//

    //--------------------MÉTODOS DE ELEMENTOS GRÁFICOS----------------------------//    
    public void geraInterface(){
        this.setSize(getLargura(), getAltura());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        adicionarMapa();
        adicionaControles();
        posicionarPersonagem(this.regras.getPosicaoAtual()[0], this.regras.getPosicaoAtual()[1]);
        adicionarPainelInfo();
        introduzPokemons(42, 42);
        this.setLayout(null);
        this.setVisible(true);     
    }
    
    private void adicionarMapa(){//Cria o mapa do jogo e chama o gerador de terrenos
        
        mapa.setSize(getLargura()-780, getAltura()-60);
        //mapa.setSize(588, 672);
        mapa.setLocation(getLargura()-(getLargura()-100), getAltura()-(getAltura()-15));
        mapa.setBackground(Color.black);
        mapa.setLayout(null);
        geraTerrenos(mapa, 42, 42);
        adicionar(mapa);
    }
    
    private void geraTerrenos(JPanel painel, int lin, int col){
        setPosiX(0);
        setPosiY(0);
        for(int i=0; i<lin; i++){
            for(int j=0; j<col; j++){
                JLabel terreno = new JLabel(tipoter[this.getRegras().getMatrizTerreno()[i][j]]);
                terreno.setBounds(getPosiX(), getPosiY(), 14, 16);
                terrenos.add(terreno);
                painel.add(terreno);
                setPosiX(getPosiX()+14);
            }
            setPosiX(0);
            setPosiY(getPosiY()+16);
        }
    }
    
    public void introduzPokemons(int lin, int col){
        for(int i=0; i<lin; i++){
            for(int j=0; j<col; j++){
                if((regras.getMatrizElementos()[i][j]>0) && (regras.getMatrizElementos()[i][j]<41)){
                    JLabel pokemon = new JLabel(definePokemon(regras.getMatrizElementos()[i][j]));
                    pokemon.setBounds(terrenos.get((i*42)+j).getBounds());
                    this.mapa.add(pokemon);
                    mapa.setComponentZOrder(pokemon, 0);
                }
            }
        }
    }
    
    private void adicionaControles(){

        controles.setSize(300, 100);
        controles.setLocation((mapa.getSize().width + 300), (mapa.getSize().height - 200));
        controles.setBackground(Color.yellow);
        controles.setLayout(null);
        adicionar(controles);
        
        ImageIcon esq = new ImageIcon(getClass().getResource("imagens/esquerda.PNG"));
        esquerda = new JButton(esq);
        esquerda.setBounds(0, (controles.getSize().height)-100, 100, 100);
        esquerda.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                regras.setSentido(regras.getSentido()+1);
                regras.adicionarCustoPontuacao(regras.MVESQUERDA);
                pontos.setText(Integer.toString(regras.getPontuacao()));
                //movimentar(regras.getPosicaoAtual()[0], regras.getPosicaoAtual()[1]-1);
            }
        });
        controles.add(esquerda);
        ImageIcon dir = new ImageIcon(getClass().getResource("imagens/direita.PNG"));
        direita = new JButton(dir);
        direita.setBounds(200, (controles.getSize().height)-100, 100, 100);
        direita.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                regras.setSentido(regras.getSentido()-1);
                regras.adicionarCustoPontuacao(regras.MVDIREITA);
                pontos.setText(Integer.toString(regras.getPontuacao()));
                //movimentar(regras.getPosicaoAtual()[0], regras.getPosicaoAtual()[1]+1);
            }
        });
        controles.add(direita);
        ImageIcon avan = new ImageIcon(getClass().getResource("imagens/avanca.PNG"));
        avanca = new JButton(avan);
        avanca.setBounds(100, (controles.getSize().height)-100, 100, 100);
        avanca.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                int[] coordenadasNovas = regras.determinarMovimento();
                if(movimentar(coordenadasNovas[0], coordenadasNovas[1])){
                    regras.adicionarCustoPontuacao(regras.MVFRENTE);
                    pontos.setText(Integer.toString(regras.getPontuacao()));
                }else{
                    JOptionPane.showMessageDialog(null, "Movimento impossível!");
                }
            }
        });
        controles.add(avanca);
    }
    
    private void adicionarPainelInfo(){//Cria o campo de informações sobre o que ocorre no jogo.
        JLabel tagPontos = new JLabel("Pontos: ");
        JLabel tagPokebolas = new JLabel("Pokébolas: ");
        JLabel tagCarga = new JLabel("Carga: ");
        
        painelInfo.setSize(610, 350);
        painelInfo.setLocation(painelInfo.getSize().width + 115, painelInfo.getSize().height - 300);
        painelInfo.setBackground(Color.white);
        painelInfo.setLayout(null);
        tagPontos.setBounds(25, 10, 120, 25);
        tagPontos.setForeground(Color.red);
        tagPontos.setFont(new Font("Dialog", Font.PLAIN, 30));
        tagPokebolas.setBounds(225, 10, 160, 25);
        tagPokebolas.setForeground(Color.red);
        tagPokebolas.setFont(new Font("Dialog", Font.PLAIN, 30));
        tagCarga.setBounds(425, 5, 100, 35);
        tagCarga.setForeground(Color.red);
        tagCarga.setFont(new Font("Dialog", Font.PLAIN, 30));
        pontos.setText("0");
        pontos.setBounds(130, 10, 120, 25);
        pontos.setForeground(Color.red);
        pontos.setFont(new Font("Dialog", Font.PLAIN, 30));
        painelAtual.setSize(560, 150);
        painelAtual.setLocation(25, 50);
        painelAtual.setBackground(Color.black);
        painelAtual.setLayout(null);
        painelLog.setSize(560, 110);
        painelLog.setLocation(25, 215);
        painelLog.setBackground(Color.yellow);
        painelLog.setLayout(null);
        painelInfo.add(tagPontos);
        painelInfo.add(tagPokebolas);
        painelInfo.add(tagCarga);
        painelInfo.add(pontos);
        painelInfo.add(painelAtual);
        setIndexPainelAtual(painelInfo.getComponentZOrder(painelAtual));
        painelInfo.add(painelLog);
        adicionar(painelInfo);
    }
    //--------------------FIM MÉTODOS DE ELEMENTOS GRÁFICOS----------------------------//    
    
}
