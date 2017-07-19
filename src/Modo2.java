
public class Modo2 implements Modos {
	
	int dificuldade; // dificudade do game
	int[] entrada1;
	
	Modo2(int dif) {
		entrada1 = new int[dif];
		dificuldade = dif;
		entrada1[0] = ((int) (Math.random() * 4) + 1);
		for (int i = 1; i < dif; i++) {
			entrada1[i]=0;
		}
	}

	@Override
	public boolean continuaJogo() {
		return true;
	}

	@Override
	public int qualBotaoPiscar(int i) {
		return entrada1[0];
	}

	@Override
	public boolean confereBotaoApertado(int botao, int posicao) {
		if(entrada1[posicao]==0){
			entrada1[posicao]=botao;
			return true;
		} else {
			if (entrada1[posicao] == botao)
				return true;
			else
				return false;
		}
	}
}
