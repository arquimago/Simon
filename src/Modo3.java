
public class Modo3 implements Modos {
	
	int dificuldade; // variavel que define a dificuldade do game
	int[] vetorGame;
	int[] vetorJogador;
	int[] banidas = {0,0,0};
	int contaBanidas = 0;

	public Modo3(int dif) {
		dificuldade = dif;
		vetorGame = new int[dif];
		vetorJogador = new int[dif];

		for (int i = 0; i < dif; i++) {
			vetorGame[i] = Randomiza();
			vetorJogador[i] = 0;
		}
	}
	
	private int Randomiza(){
		int rand;
		rand = ((int) (Math.random() * 4) +1);
		
		while((rand==banidas[0]) || (rand==banidas[1]) || (rand==banidas[2])){
			rand = ((int) (Math.random() * 4) +1);			
		}
		return rand;
	}
	
	private boolean Compara(int i){
		if (vetorGame[i] == vetorJogador[i])
			return true;
		else
			return false;
	}
	
	@Override
	public boolean continuaJogo(){
		if(contaBanidas==3) {
			return false;
		}
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
		vetorJogador[posicao]=botao;
		if(Compara(posicao)==false) {
			int i;
			contaBanidas++;
			for(i=0;i<3;i++) {
				//este primeiro if impede que o botao seja banido mais de uma vez
				if(banidas[i]==botao){
					contaBanidas--;
					break;
				} else {
					if(banidas[i]==0){
						banidas[i]=botao;
						break;
					}
				}
			}
			for (i=0;i<dificuldade;i++) {
				vetorGame[i] = Randomiza(); 
				vetorJogador[i] = 0;
			}
		}
		return Compara(posicao);
	}
}
