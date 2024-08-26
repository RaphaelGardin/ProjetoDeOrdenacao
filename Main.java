import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static JFrame tela1;
    private static JFrame tela2;
    private static JFrame tela3;
    private static ArrayList<Integer> numeros = new ArrayList<>();

    public static void main(String[] args) {
        criarTela1();
    }

    private static void criarTela1() {
        tela1 = new JFrame("Tela 1 - Insercao de Numeros");
        tela1.setSize(400, 300);
        tela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela1.setLayout(new BoxLayout(tela1.getContentPane(), BoxLayout.Y_AXIS));

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        JTextField[] camposNumeros = new JTextField[8];
        for (int i = 0; i < 8; i++) {
            JPanel linha = new JPanel();
            linha.add(new JLabel("Numero " + (i + 1) + ":"));
            camposNumeros[i] = new JTextField(10);
            linha.add(camposNumeros[i]);
            painel.add(linha);
        }

        JButton botaoEntrar = new JButton("Entra");
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numeros.clear(); 
                    for (JTextField campo : camposNumeros) {
                        numeros.add(Integer.parseInt(campo.getText()));
                    }
                    bubbleSort(numeros);
                    criarTela2();
                    tela1.setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tela1, "Por favor, insira apenas numeros inteiros.");
                }
            }
        });

        painel.add(botaoEntrar);
        tela1.add(painel);
        tela1.setVisible(true);
    }

    private static void criarTela2() {
        tela2 = new JFrame("Tela 2 - Busca de Numero");
        tela2.setSize(400, 200);
        tela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela2.setLayout(new BoxLayout(tela2.getContentPane(), BoxLayout.Y_AXIS));

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        JPanel linha = new JPanel();
        linha.add(new JLabel("Digite o numero a buscar:"));
        JTextField campoBusca = new JTextField(10);
        linha.add(campoBusca);
        painel.add(linha);

        JButton botaoBuscar = new JButton("Entra");
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chave = Integer.parseInt(campoBusca.getText());
                    int posicao = buscarNumero(numeros, chave);
                    criarTela3(posicao, chave);
                    tela2.setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tela2, "Por favor, insira um numero inteiro valido.");
                }
            }
        });

        painel.add(botaoBuscar);
        tela2.add(painel);
        tela2.setVisible(true);
    }

    private static void criarTela3(int posicao, int chave) {
        tela3 = new JFrame("Tela 3 - Resultado da Busca");
        tela3.setSize(400, 300);
        tela3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela3.setLayout(new BoxLayout(tela3.getContentPane(), BoxLayout.Y_AXIS));

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        String mensagem;
        if (posicao != -1) {
            mensagem = "Numero " + chave + " encontrado na posicao " + posicao + " (índice baseado em 0).";
        } else {
            mensagem = "Numero " + chave + " nao encontrado.";
        }

        JLabel resultado = new JLabel(mensagem);
        painel.add(resultado);

        JButton botaoFinalizar = new JButton("Entra");
        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        painel.add(botaoFinalizar);
        tela3.add(painel);
        tela3.setVisible(true);
    }

    private static void bubbleSort(ArrayList<Integer> lista) {
        int n = lista.size();
        boolean trocou;
        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                if (lista.get(i) > lista.get(i + 1)) {
                    Collections.swap(lista, i, i + 1);
                    trocou = true;
                }
            }
            n--; 
        } while (trocou);
    }

    private static int buscarNumero(ArrayList<Integer> lista, int chave) {

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) == chave) {
                return i; 
            }
        }
        return -1; 
    }
}
