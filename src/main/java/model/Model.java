package model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private int NMAX;
	private int TMAX;
    private int segreto;
    private int tentativiFatti;
    private boolean inGioco= false;
    private int tentativoPrecMax=0;
    private int tentativoPrecMin=0;
    private boolean primoMin=true;
    private boolean primoMax= true;
    private Set<Integer> tentativi;
    
    
    public int getNMAX() {
		return NMAX;
	}


	public void setNMAX(int nMAX) {
		NMAX = nMAX;
	}


	public int getTMAX() {
		return TMAX;
	}


	public void setTMAX(int tMAX) {
		TMAX = tMAX;
	}


	public int getSegreto() {
		return segreto;
	}


	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}


	public int getTentativiFatti() {
		return tentativiFatti;
	}


	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}


	public boolean isInGioco() {
		return inGioco;
	}


	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}


	public int getTentativoPrecMax() {
		return tentativoPrecMax;
	}


	public void setTentativoPrecMax(int tentativoPrecMax) {
		this.tentativoPrecMax = tentativoPrecMax;
	}


	public int getTentativoPrecMin() {
		return tentativoPrecMin;
	}


	public void setTentativoPrecMin(int tentativoPrecMin) {
		this.tentativoPrecMin = tentativoPrecMin;
	}


	public boolean isPrimoMin() {
		return primoMin;
	}


	public void setPrimoMin(boolean primoMin) {
		this.primoMin = primoMin;
	}


	public boolean isPrimoMax() {
		return primoMax;
	}


	public void setPrimoMax(boolean primoMax) {
		this.primoMax = primoMax;
	}



    public void nuovaPartita() {
    	this.tentativi= new HashSet<>();
    	this.inGioco=true;
    }

	public void facile() {
    	NMAX= 100;
    	TMAX=8;
    	this.segreto= (int)(Math.random()* NMAX)+1;
    	this.tentativiFatti=0;
    
    }
	
	public void medio() {
		NMAX= 500;
    	TMAX=9;
    	this.segreto= (int)(Math.random()* NMAX)+1;
    	this.tentativiFatti=0;
    	
	}
	
	public void difficile() {
		NMAX= 2000;
    	TMAX=11;
    	this.segreto= (int)(Math.random()* NMAX)+1;
    	this.tentativiFatti=0;
    	
	}
	
	public int tentativo(int tentativo) {
		
		if(!inGioco) {
			if(tentativo != this.segreto)
			   throw new IllegalStateException("HAI PERSO, IL SEGRETO ERA: " + this.segreto);
			else {
				this.tentativiFatti ++;
				this.tentativi.add(tentativo);
				return 0;
			}
		}
		
		//controllo dell'input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e "+NMAX+",\ne non puoi inserire due volte lo stesso numero");
		}
		
		//il tentativo è valido
				this.tentativiFatti ++;
				this.tentativi.add(tentativo);
				
				if(this.tentativiFatti == (TMAX-1)) {
					this.inGioco = false;
				}
				
				if(tentativo == this.segreto) {
					this.inGioco = false;
					return 0;
				} else if(tentativo < this.segreto) {
					
					return -1;
				} else {
					return 1;
				}
		
	}
	
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo< 1 || tentativo > NMAX)
			return false;
		
		if(tentativi.contains(tentativo)) 
			return false;
		
		return true;
		
	}
}