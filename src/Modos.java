
public interface Modos {
	
	public boolean continuaJogo();
	//retorna se o jogo continua TRUE ou termina FALSE

	public int qualBotaoPiscar(int i); 
	//retorna o identificador do bot�o 1, 2, 3 ou 4

	public boolean confereBotaoApertado(int botao, int posicao); 
	//recebe bot�o apertado e retorna se acertou TRUE ou errou FALSE

}
