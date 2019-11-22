package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;





public class Game {
	
	public final static String ROOT = " ./archivos";
	
	
	private ArrayList<Ball> balls;
	private Score sc;
	private int levl;
	/**
	 * @param balls
	 * @param sc
	 * @param levl
	 */
	public Game() {
		
		balls = new ArrayList<>();
		sc = new Score();
		levl = 0;
		loadScore();
	}
	
	
	
	
	/**
	 * @return the balls
	 */
	public ArrayList<Ball> getBalls() {
		return balls;
	}




	/**
	 * @param balls the balls to set
	 */
	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}




	/**
	 * @return the sc
	 */
	public Score getSc() {
		return sc;
	}




	/**
	 * @param sc the sc to set
	 */
	public void setSc(Score sc) {
		this.sc = sc;
	}




	/**
	 * @return the levl
	 */
	public int getLevl() {
		return levl;
	}




	/**
	 * @param levl the levl to set
	 */
	public void setLevl(int levl) {
		this.levl = levl;
	}




	public void loadScore() {
		
		NoacordingScore();
		

	}
	public void saveScore (){
		acordingScore();
		
		
	}
	
	public void acordingScore (){
		
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(ROOT));
			o.writeObject(sc);
			o.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void NoacordingScore (){
		
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(ROOT));
			try {
				sc = (Score) o.readObject();
				o.close();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void loadGame(File archivo) throws IOException {
		
		try {
			FileReader r = new FileReader(archivo);
			BufferedReader b = new BufferedReader(r);
			String line = "";
			boolean termino = false;

			while ((line = b.readLine()) != null && !termino) {
				if (line.charAt(0) != '#') {

					levl = Integer.parseInt(line);
					termino = true;
				}
			}
			while ((line = b.readLine()) != null ) {
				if (line.charAt(0) != '#') {

					String[] cadena = line.split("\t");

						
						int rad = Integer.parseInt(cadena[0]);
						int posx = Integer.parseInt(cadena[1]);
						int posy = Integer.parseInt(cadena [2]);
						int stop = Integer.parseInt(cadena [3]);
						String dir = cadena [4];
						int bou = Integer.parseInt(cadena [5]);
						boolean atrapada = Boolean.parseBoolean(cadena[6]);
						
						
						Ball ba = new Ball(rad*2, posx, posy, stop, dir, bou, atrapada);
						
						
						balls.add(ba);
				}
			}



		} catch (Exception e) {
			
		}
	}
	
	
	public void saveGame(File f) throws IOException {
		
			
		PrintWriter pw = new PrintWriter(f);
		pw.println(levl + "");
		
		
		for (int i = 0; i < balls.size(); i++) {
			
			int posy = balls.get(i).getPosY();
			int posx = balls.get(i).getPosX();
			int boun = balls.get(i).getBounces();
			int wait = balls.get(i).getWaitT();
			int redium = balls.get(i).getDiameter()/2;
			String dir = balls.get(i).getDirection();
			boolean stop = balls.get(i).isStop();
			
			pw.println(posy +"\t" + posx +"\t"+ boun +"\t"+ wait +"\t"+ redium+ "\t"+ dir+ "\t"+stop);
		}
		pw.close();
		
	}
	
	public int HowManyBounces() {
		int k = 0;
		for (Ball b : balls) {
			k += b.getBounces();
		}
		return k;
	}
	
	
	public void catchBall(int x, int y) {
		for (int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if (b.stoped(x, y)) {
				b.changeStop(true);
			}
		}
	}
	
	
	public boolean endGame() {
		boolean catchb = true;
		for (int i = 0; i < balls.size() && catchb; i++) {
			Ball bal = balls.get(i);
			if (!bal.isStop()) {
				catchb = false;
			}
		}
		return catchb;
	}
	
	public boolean hsaveScore() {
		int bou = HowManyBounces();
		boolean svScr = sc.bestScores(levl, bou);
		return svScr;
	}

	public void saveScoreActual(String nom) {
		sc.addScore(levl, nom, HowManyBounces());
		saveScore();
	}

	public String getScore() {
		String reportePuntaje;
		reportePuntaje = sc.getScore();
		return reportePuntaje;
	}
	
	
	
	
}
