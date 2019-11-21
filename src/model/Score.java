package model;

public class Score {
	
	
	private long serialVersionUID;
	public final static int LEVELS = 3;
	public final static int LIST = 10;
	private String[][] names;
	private int[][] scores;
	
	
	/**
	 * @param serialVersionUID
	 * @param name
	 * @param scores
	 */
	public Score() {
		names  = new String[LEVELS][LIST];
		scores = new int[LEVELS][LIST];
		serialVersionUID = 4615267346576535715L;
		for(int j=0;j<scores.length;j++){
			for(int i=0;i<scores[j].length;i++){
				scores[j][i] = Integer.MAX_VALUE;
				names[j][i]  = "";
			}
		}
	}
	
	
	public boolean addScore(int niv, String n, int p){
		boolean add = false;
		String tmp = "";
		int tmp1 = 0;
		int i=0;
		while(i < LIST && add == false) {
			if(scores[niv][i]>p){
				tmp = names[niv][i];
				tmp1 = scores[niv][i];
				names[niv][i] = n;
				scores[niv][i] = p;
				add = true;
			}
			i++;
		}
		
		if(add == true){
			for(int j=LIST-1;j>i;j--){
				names[niv][j] = names[niv][j-1];
				scores[niv][j] = scores[niv][j-1];
			}
			names[niv][i]  = tmp;
			scores[niv][i] = tmp1;			
		}
		return add;
	}
	
	
	public String getScore(){
		String reporte = "";
		for(int nivel=0;nivel<names.length;nivel++){
			String titulo  = String.format("%-23s", "Nivel "+nivel);
			reporte += titulo;
		}
		reporte += "\n";
		for(int i=0;i<LIST;i++){
			for(int nivel=0;nivel<names.length;nivel++){
				if(!names[nivel][i].equals("")){
					String nombre  = String.format("%-10s", names[nivel][i]);
					String puntaje = String.format("%-10s", scores[nivel][i]);
					reporte += nombre+" "+puntaje+"";
				}
			}
			reporte += "\n";
		}
		return reporte;
	}
	
	public boolean bestScores(int niv, int p){
		boolean k = false;
		if(p<scores[niv][LIST-1]){
			k = true;
		}
		return k;
	}
	
	
	
	
}
