
public interface Modos {
	
	public boolean continuaJogo();
	//retorna se o jogo continua TRUE ou termina FALSE

	public int qualBotaoPiscar(int i); 
	//retorna o identificador do botão 1, 2, 3 ou 4

	public boolean confereBotaoApertado(int botao, int posicao); 
	//recebe botão apertado e retorna se acertou TRUE ou errou FALSE

}
