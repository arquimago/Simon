
public class Modo1 implements Modos {

	int dificuldade; // variavel que define a dificuldade do game
	int[] vetorGame;
	int[] vetorJogador;

	public Modo1(int dif) {
		dificuldade = dif;
		vetorGame = new int[dif];
		vetorJogador = new int[dif];

		for (int i = 0; i < dif; i++) {
			vetorGame[i] = Randomiza();
			vetorJogador[i] = 0;
		}
		
	}
	
	private int Randomiza(){
		return ((int) (Math.random() * 4) +1);
	}
	
	private boolean Compara(int i){
		if (vetorGame[i] == vetorJogador[i])
			return true;
		else
			return false;
	}

	@Override
	public boolean continuaJogo() {
		return true;
	}

	@Override
	public int qualBotaoPiscar(int i) {
		int botao;
		botao = vetorGame[i];
		return botao;
	}

	@Override
	public boolean confereBotaoApertado(int botao, int posicao) {
		boolean conferido;
		vetorJogador[posicao]=botao;
		conferido = Compara(posicao);
		return conferido;
	}

}
