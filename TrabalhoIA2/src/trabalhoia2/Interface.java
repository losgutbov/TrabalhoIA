/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Augusto
 */
public class Interface extends JFrame{
    
    private JPanel mapa = new JPanel(), controles = new JPanel();
    private int altura, largura, posiX, posiY;
    private ArrayList<JLabel> terrenos = new ArrayList<>();
    private ImageIcon[] tipoter = new ImageIcon[5]; 
    private JButton esquerda, direita, avanca, volta, lancaPokebola;
    
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
            
    public Interface(int altura, int largura){
        setAltura(altura);
        setLargura(largura);
        defineTipos();
    }
    
    private void defineTipos(){
        tipoter[0] = new ImageIcon(getClass().getResource("imagens/grama.png"));
        tipoter[1] = new ImageIcon(getClass().getResource("imagens/agua.png"));
        tipoter[2] = new ImageIcon(getClass().getResource("imagens/montanha.png"));
        tipoter[3] = new ImageIcon(getClass().getResource("imagens/caverna.png"));
        tipoter[4] = new ImageIcon(getClass().getResource("imagens/vulcao.png"));
    }
    
    private void adicionar(Component ob){
        this.add(ob);
    }
    
    public void geraInterface(){
        this.setSize(getLargura(), getAltura());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setVisible(true);
        this.setLayout(null);
        adicionarMapa();
        adicionaControles();
    }
    
    private void adicionarMapa(){
        
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
                JLabel terreno = new JLabel(tipoter[0]);
                terreno.setBounds(getPosiX(), getPosiY(), 14, 16);
                terrenos.add(terreno);
                painel.add(terreno);
                setPosiX(getPosiX()+14);
            }
            setPosiX(0);
            setPosiY(getPosiY()+16);
        }
    }
    
    private void adicionaControles(){

        controles.setSize(300, 200);
        controles.setLocation((mapa.getSize().width + 300), (mapa.getSize().height - 200));
        controles.setBackground(Color.yellow);
        controles.setLayout(null);
        adicionar(controles);
        
        ImageIcon esq = new ImageIcon(getClass().getResource("imagens/esquerda.PNG"));
        esquerda = new JButton(esq);
        esquerda.setBounds(0, (controles.getSize().height)-100, 100, 100);
        controles.add(esquerda);
        ImageIcon volt = new ImageIcon(getClass().getResource("imagens/volta.PNG"));
        volta = new JButton(volt);
        volta.setBounds(100, (controles.getSize().height)-100, 100, 100);
        controles.add(volta);
        ImageIcon dir = new ImageIcon(getClass().getResource("imagens/direita.PNG"));
        direita = new JButton(dir);
        direita.setBounds(200, (controles.getSize().height)-100, 100, 100);
        controles.add(direita);
        ImageIcon avan = new ImageIcon(getClass().getResource("imagens/avanca.PNG"));
        avanca = new JButton(avan);
        avanca.setBounds(100, 0, 100, 100);
        controles.add(avanca);
        
    }
}
