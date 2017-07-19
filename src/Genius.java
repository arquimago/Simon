import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Genius extends JFrame {

	// Declarando botões
	BotaoGenius b, r, g, y;
	JButton start = new JButton("Iniciar");
	JButton end = new JButton("Terminar");

	// Declarando Sliders
	JSlider modo = new JSlider(JSlider.HORIZONTAL, 1, 3, 1);
	JSlider dif = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);

	// Declarando variáveis necessárias para o jogo
	int modoJogo = 1, difJogo = 8, contadorJogo = 0, contadorCliques = 0;

	// Declarando novoJogo
	Modos novoJogo;

	public Genius() {
		super("Genius");
		setSize(615, 630);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Instanciando containers
		Container c = getContentPane();
		Container titulo = new JPanel();
		Container vazio1 = new JPanel();
		Container vazio2 = new JPanel();
		Container baixo = new JPanel();
		Container menu = new JPanel();

		// setando cor de fundo
		c.setBackground(Color.black);
		titulo.setBackground(Color.black);
		vazio1.setBackground(Color.black);
		vazio2.setBackground(Color.black);
		baixo.setBackground(Color.black);
		menu.setBackground(Color.black);

		// Aqui começam os enfeites da Janela
		JLabel copyright = new JLabel("Feito por", JLabel.CENTER);
		JLabel thales = new JLabel("Thales Francisco", JLabel.CENTER);
		JLabel p2 = new JLabel(" ", JLabel.CENTER);
		JLabel p3 = new JLabel(" ", JLabel.CENTER);
		thales.setForeground(Color.WHITE);
		p2.setForeground(Color.WHITE);
		p3.setForeground(Color.WHITE);
		copyright.setForeground(Color.WHITE);
		baixo.setLayout(new BorderLayout(40, 40));
		baixo.add(BorderLayout.CENTER, copyright);
		Container nomes = new JPanel();
		nomes.setLayout(new BorderLayout());
		baixo.add(BorderLayout.SOUTH, nomes);
		baixo.add(BorderLayout.NORTH, new JLabel("  "));
		nomes.add(BorderLayout.NORTH, p3);
		nomes.add(BorderLayout.CENTER, p2);
		nomes.add(BorderLayout.SOUTH, thales);
		nomes.setForeground(Color.WHITE);
		nomes.setBackground(Color.BLACK);

		// Titulo do Jogo
		Font fonte = new Font("serif", Font.BOLD | Font.ITALIC, 28);
		JLabel simon = new JLabel("GENIUS", JLabel.CENTER);
		simon.setFont(fonte);
		simon.setForeground(Color.WHITE);
		titulo.add(simon);
		titulo.setPreferredSize(new Dimension(115, 80));

		// Aqui estão os Sliders
		Font sliderFonte = new Font("serif", Font.BOLD, 12);

		modo.setMajorTickSpacing(1);
		modo.setMinorTickSpacing(1);
		modo.setPaintLabels(true);
		modo.setPaintTicks(true);
		modo.setFont(sliderFonte);
		modo.setPreferredSize(new Dimension(170, 50));
		modo.addChangeListener(new modoListener());
		modo.setForeground(Color.WHITE);
		modo.setBackground(Color.BLACK);

		dif.setMajorTickSpacing(1);
		dif.setMinorTickSpacing(1);
		dif.setPaintLabels(true);
		dif.setPaintTicks(true);
		dif.setFont(sliderFonte);
		dif.setPreferredSize(new Dimension(170, 50));
		dif.addChangeListener(new difListener());
		dif.setForeground(Color.WHITE);
		dif.setBackground(Color.BLACK);

		JLabel modoLabel = new JLabel("Modo", JLabel.CENTER);
		JLabel difLabel = new JLabel("Dificuldade", JLabel.CENTER);
		modoLabel.setForeground(Color.WHITE);
		difLabel.setForeground(Color.WHITE);
		difLabel.setToolTipText("8, 14, 20 ou 31 toques");

		// Aqui estão os botões de inicio e fim de jogo
		start.addActionListener(new StartListener());
		start.setPreferredSize(new Dimension(100, 20));
		start.setForeground(Color.WHITE);
		start.setBackground(Color.BLACK);
		end.addActionListener(new endListener());
		end.setEnabled(false);
		end.setVisible(false);
		end.setPreferredSize(new Dimension(100, 20));
		end.setForeground(Color.WHITE);
		end.setBackground(Color.BLACK);

		// Aqui encaixo todos os controles no layout
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(modoLabel);
		menu.add(modo);
		menu.add(difLabel);
		menu.add(dif);
		menu.add(start);
		menu.add(end);
		menu.setPreferredSize(new Dimension(180, 180));

		// Instanciando os botões
		b = new BotaoGenius(1);
		g = new BotaoGenius(2);
		r = new BotaoGenius(3);
		y = new BotaoGenius(4);

		// Montando a janela
		c.setLayout(new GridLayout(3, 3, 0, 0));
		c.add(titulo);
		c.add(g);
		c.add(vazio1);
		c.add(y);
		c.add(menu);
		c.add(r);
		c.add(vazio2);
		c.add(b);
		c.add(baixo);

		// Setando a imagem dos botões
		b.apagar();
		g.apagar();
		r.apagar();
		y.apagar();

		// Indicando Listeners
		b.addActionListener(new BotaoListener());
		g.addActionListener(new BotaoListener());
		r.addActionListener(new BotaoListener());
		y.addActionListener(new BotaoListener());

	}

	// Listener do botão Iniciar
	private class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			start.setVisible(false);
			modo.setEnabled(false);
			dif.setEnabled(false);
			end.setEnabled(true);
			end.setVisible(true);

			switch (modoJogo) {
			case 1:
				novoJogo = new Modo1(difJogo);
				meioJogo();
				break;
			case 2:
				novoJogo = new Modo2(difJogo);
				meioJogo2();
				break;
			default:
				novoJogo = new Modo3(difJogo);
				meioJogo();
			}
		}
	}

	// Método auxiliar ao Modo 1 e 3
	private void meioJogo() {
		if (contadorCliques == difJogo) {
			venceu();
			return;
		}
		if (contadorCliques == contadorJogo) {
			contadorJogo++;
			mostraSequencia();
			contadorCliques = 0;
		} else {
			contadorCliques++;
			if (contadorCliques == contadorJogo) {
				try {
					Thread.sleep(400);
					update(getGraphics());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				meioJogo();
			}
		}
	}

	// Método auxiliar ao Modo 2
	private void meioJogo2() {
		if (contadorJogo == 0) {
			piscarBotao(novoJogo.qualBotaoPiscar(0));
			contadorJogo++;
			return;
		}
		if (contadorCliques < contadorJogo) {
			contadorCliques++;
			if (contadorCliques == difJogo) {
				venceu();
				return;
			}
			return;
		} else {
			contadorJogo++;
			contadorCliques = 0;
		}
	}

	// Janela de Venceu!
	private void venceu() {
		JOptionPane.showMessageDialog(null, "Você venceu o Genius!!");
		end.doClick();
	}

	// Janela de Game Over!
	private void gameOver() {
		JOptionPane.showMessageDialog(null, "Você Perdeu! Fim de jogo!");
		end.doClick();
	}

	// Método que mostra a sequencia a ser apertada
	private void mostraSequencia() {
		for (int i = 0; i < (contadorJogo); i++) {
			piscarBotao(novoJogo.qualBotaoPiscar(i));
		}
	}

	// Listener dos botões de jogo
	private class BotaoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BotaoGenius botao = (BotaoGenius) e.getSource();
			// Caso nenhum jogo tenha sido iniciado mensagem de alerta!
			if (novoJogo == null) {
				JOptionPane
						.showMessageDialog(null,
								"Jogo não iniciado, escolha modo, dificuldade e clique em Iniciar!");
				return;
			}

			botao.piscar();

			if (novoJogo.confereBotaoApertado(botao.getId(), contadorCliques)) {
				// Cada modo de jogo precisa ser tratado diferente para
				// aplicação das regras
				if (novoJogo instanceof Modo1) {
					meioJogo();
				}
				if (novoJogo instanceof Modo2) {
					meioJogo2();
				}
				if (novoJogo instanceof Modo3) {
					if (!novoJogo.continuaJogo()) {
						for (int j = 0; j < 3; j++) {
							piscarBotao(novoJogo.qualBotaoPiscar(0));
						}
						b.setEnabled(true);
						g.setEnabled(true);
						r.setEnabled(true);
						y.setEnabled(true);
						b.apagar();
						g.apagar();
						r.apagar();
						y.apagar();
						venceu();
						return;
					}
					meioJogo();
				}
			} else {
				if (novoJogo instanceof Modo3) {
					botao.setEnabled(false);
					botao.desabilitar();
					contadorCliques = 0;
					contadorJogo = 0;
					meioJogo();

				} else {
					gameOver();
					return;
				}
			}
		}
	}

	// Listener do botão Terminar
	private class endListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(true);
			start.setVisible(true);
			modo.setEnabled(true);
			dif.setEnabled(true);
			end.setEnabled(false);
			end.setVisible(false);
			novoJogo = null;
			contadorCliques = 0;
			contadorJogo = 0;
		}
	}

	// Listener do Slider de Modo
	private class modoListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			modoJogo = source.getValue();
		}
	}

	// Listener do Slider de Dificuldade
	private class difListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			switch (source.getValue()) {
			case 1:
				difJogo = 8;
				break;
			case 2:
				difJogo = 14;
				break;
			case 3:
				difJogo = 20;
				break;
			default:
				difJogo = 31;
			}
		}
	}

	// Método que manda o botão específico piscar
	private void piscarBotao(int qualBotaoPiscar) {
		switch (qualBotaoPiscar) {
		case 1:
			b.piscar();
			break;
		case 2:
			g.piscar();
			break;
		case 3:
			r.piscar();
			break;
		default:
			y.piscar();
		}
	}
}
