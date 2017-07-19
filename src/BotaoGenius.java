import java.awt.Color;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BotaoGenius extends JButton {

	private int id;
	private String enderecoImagem = "img"
			+ System.getProperty("file.separator");

	public BotaoGenius(int numero) {
		id = numero;
		setBorderPainted(false);
		setBackground(Color.black);
		setForeground(Color.black);
		setRolloverEnabled(false);
		
		switch (numero) {
		case 1:
			this.enderecoImagem += 'b';
			break;
		case 2:
			this.enderecoImagem += 'g';
			break;
		case 3:
			this.enderecoImagem += 'r';
			break;
		default:
			this.enderecoImagem += 'y';
		}

		enderecoImagem += System.getProperty("file.separator");
		setPressedIcon(new ImageIcon(getClass().getResource(
				enderecoImagem + "pressionado.png")));
	}

	public int getId() {
		return id;
	}

	public void acender() {
		setIcon(new ImageIcon(getClass().getResource(
				enderecoImagem + "pressionado.png")));
		update(getGraphics());
	}

	public void apagar() {
		setIcon(new ImageIcon(getClass().getResource(
				enderecoImagem + "solto.png")));
		update(getGraphics());
	}
	
	public void desabilitar(){
		setIcon(null);
		update(getGraphics());
	}

	public void piscar() {
		
		acender();
		// começa som
		try {
		    File som = new File(getClass().getResource(enderecoImagem + "som.wav").toURI());
		    AudioInputStream ais;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    ais = AudioSystem.getAudioInputStream(som);
		    format = ais.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(ais);
		    clip.start();
		}
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		//termina som
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		apagar();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
