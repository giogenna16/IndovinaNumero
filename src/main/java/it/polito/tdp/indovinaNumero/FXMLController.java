/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.indovinaNumero;

import java.security.InvalidParameterException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Model;

public class FXMLController {
	
	private Model model;
	 private int tentativoPrecMax=0;
	    private int tentativoPrecMin=0;
	    private boolean primoMin=true;
	    private boolean primoMax= true;
	
    
    

    @FXML // fx:id="btmNuovaPartita"
    private Button btmNuovaPartita; // Value injected by FXMLLoader

    
    @FXML
    private Button btnFacile;

    @FXML
    private Button btnMedia;

    @FXML
    private Button btnDifficile;
    
    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtInserisci"
    private TextField txtInserisci; // Value injected by FXMLLoader

    @FXML // fx:id="btmProva"
    private Button btmProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    private Button btnAbbandona;
    
    @FXML
    private ProgressBar progBarTentativi;
    
      
    @FXML
    private CheckBox checkBoxModAss;

    
    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	//inizio nuova partita
    	
    	
    	this.btmNuovaPartita.setDisable(true);
    	this.txtRisultato.setText("");
    	
    	this.btnFacile.setDisable(false);
    	this.btnMedia.setDisable(false);
    	this.btnDifficile.setDisable(false);
        this.model.nuovaPartita();

    }
    
  

    @FXML
    void handleFacile(ActionEvent event) {
    	
    	this.model.facile();
    	this.txtRisultato.setText("Indovina il numero segreto nell'intervallo da 1 a 100 (se ci riesci)!");
    	
    	
    	this.btmProva.setDisable(false);
    	this.txtInserisci.setDisable(false);
    	this.btnAbbandona.setDisable(false);
    	
       this.checkBoxModAss.setDisable(false);
    	
    	this.btnFacile.setDisable(true);
    	this.btnMedia.setDisable(true);
    	this.btnDifficile.setDisable(true);
    	
    	
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()));
    	this.progBarTentativi.setProgress((this.model.getTMAX()-this.model.getTentativiFatti())/this.model.getTMAX());

    	
    }

    @FXML
    void handleMedia(ActionEvent event) {
    	this.model.medio();
    	this.txtRisultato.setText("Indovina il numero segreto nell'intervallo da 1 a 500 (se ci riesci)!");
    	
    	
    	this.btmProva.setDisable(false);
    	this.txtInserisci.setDisable(false);
    	this.btnAbbandona.setDisable(false);
    	
        this.checkBoxModAss.setDisable(false);
    	
    	this.btnFacile.setDisable(true);
    	this.btnMedia.setDisable(true);
    	this.btnDifficile.setDisable(true);
    	
    	
    	this.txtTentativi.setText(Integer.toString(model.getTMAX()));
    	this.progBarTentativi.setProgress((this.model.getTMAX()-this.model.getTentativiFatti())/this.model.getTMAX());

    }
    
    @FXML
    void handleDifficile(ActionEvent event) {
    	this.model.difficile();
    	this.txtRisultato.setText("Indovina il numero segreto nell'intervallo da 1 a 2000 (se ci riesci)!");
    	
    	
    	this.btmProva.setDisable(false);
    	this.txtInserisci.setDisable(false);
    	this.btnAbbandona.setDisable(false);
    	
        this.checkBoxModAss.setDisable(false);
    	
    	this.btnFacile.setDisable(true);
    	this.btnMedia.setDisable(true);
    	this.btnDifficile.setDisable(true);
    	
    	
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()));
    	
    	this.progBarTentativi.setProgress((this.model.getTMAX()-this.model.getTentativiFatti())/this.model.getTMAX());

    }
    
    @FXML
    void handleProva(ActionEvent event) {
    	//lettura input dell'utente
    	
    	  this.checkBoxModAss.setDisable(true);
    	  
    	String ts= txtInserisci.getText();
    	int tentativo;
    	
    	try {
    		tentativo= Integer.parseInt(ts);
    	}
    	catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		return;
    	}
    	
    	int result;
    	try {
    		result= this.model.tentativo(tentativo);
    	}
    	catch(IllegalStateException se) {
    	    this.txtRisultato.setText(se.getMessage());
    	    this.txtTentativi.setText("0");
    	    this.progBarTentativi.setProgress(0);
    	    this.txtInserisci.clear();
    	    this.txtInserisci.setDisable(true);
    	    this.btmProva.setDisable(true);
		    return;
    	}
    	catch(InvalidParameterException pe) {
    		this.txtRisultato.setText(pe.getMessage());
    		this.txtInserisci.clear();
    		return;
    	}
    	 this.txtInserisci.clear();
    	
    	
    	

    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    	this.progBarTentativi.setProgress((double)(this.model.getTMAX()-this.model.getTentativiFatti())/this.model.getTMAX());
    	
    	if(result==0) {
    		//HO indovinato
    		txtRisultato.setText("Hai vinto con "+ this.model.getTentativiFatti()+ " tentativi!!!");
    		if(this.model.getTentativiFatti()==this.model.getTentativiFatti()) {
    			 this.txtTentativi.setText("0");
    	    	 this.progBarTentativi.setProgress(0);
    	    	 this.txtInserisci.clear();
    	    	 this.btmProva.setDisable(true);
    	    	 this.txtInserisci.setDisable(true);
    			    return;
    		}
    		
    		return;
    	}
    	
    	
    	
        if(this.checkBoxModAss.isSelected()) {
        	if(result==-1) {
        		
        		if(this.tentativoPrecMin==0 && this.tentativoPrecMax==0) {
        			this.tentativoPrecMin=tentativo;
            		this.tentativoPrecMax=this.model.getNMAX();
        		  
            		txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax);
        	
                   if(tentativo==1) {
        				
        				primoMin=false;	
        			}
        		
        		}
        		else {
        			if(tentativo>=this.tentativoPrecMin)
        			   this.tentativoPrecMin=tentativo;
        			 
        			
        			if(tentativo==1) {
        				
        				primoMin=false;	
        			}
        		
        			if(this.tentativoPrecMin!=1 && this.tentativoPrecMax!=this.model.getNMAX())
        				txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin==1 && this.tentativoPrecMax!=this.model.getNMAX() && this.primoMin==true)
        				txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin==1 && this.tentativoPrecMax!=this.model.getNMAX() && this.primoMin==false)
        				txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin==1 && this.tentativoPrecMax==this.model.getNMAX() && this.primoMin==false && this.primoMax==false)
        				txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin!=1 && this.tentativoPrecMax==this.model.getNMAX()&& this.primoMax==true)
        				txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax);
        			if(this.tentativoPrecMin!=1 && this.tentativoPrecMax==this.model.getNMAX() && this.primoMax==false)
        				txtRisultato.setText("Numero troppo basso, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        				
        			
        			
        		}
        	
        		
        	}
        	else if(result==1) {
        		if(this.tentativoPrecMin==0 && this.tentativoPrecMax==0) {
        			this.tentativoPrecMin=1;
            		this.tentativoPrecMax=tentativo;
            		txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" e "+this.tentativoPrecMax+" (escluso)");
            		
                    if(tentativo==this.model.getNMAX()) {
        				
        				this.primoMax=false;
        				
        			}
        		}
        		else {
        			if(tentativo<=this.tentativoPrecMax)
        			     this.tentativoPrecMax=tentativo;
        			
        			if(tentativo==this.model.getNMAX()) {
        				
        				this.primoMax=false;
        				
        			}
        				
        			
        			if(this.tentativoPrecMin!=1 && this.tentativoPrecMax!=this.model.getNMAX())
        				txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        		    if(this.tentativoPrecMin!=1 && this.tentativoPrecMax==this.model.getNMAX() && this.primoMax==true)
        				txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax);
        			if(this.tentativoPrecMin!=1 && this.tentativoPrecMax==this.model.getNMAX() && this.primoMax==false)
        				txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin==1 && this.tentativoPrecMax==this.model.getNMAX() && this.primoMin==false && this.primoMax==false)
        				txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin==1 && this.tentativoPrecMax!=this.model.getNMAX() && this.primoMin==true)
        				txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" e "+this.tentativoPrecMax+" (escluso)");
        			if(this.tentativoPrecMin==1 && this.tentativoPrecMax!=this.model.getNMAX() && this.primoMin==false)
        				txtRisultato.setText("Numero troppo alto, riprova!\nIl numero segreto si trova tra "+this.tentativoPrecMin+" (escluso) e "+this.tentativoPrecMax+" (escluso)");
        				
        			
        		}
        		
        	}
        	
    		
    	}else {
    	
    	   if(result==-1) {
    		  txtRisultato.setText("Numero troppo basso, riprova!");
    		
    		
    		
    	   }
        	else if(result==1){
    		  txtRisultato.setText("Numero troppo alto, riprova!");
    	    }
    	
    	}
   }
    
    
   

    @FXML
    void handleAbbandona(ActionEvent event) {
    	this.btmNuovaPartita.setDisable(false);

    	this.btmProva.setDisable(true);
    	this.txtInserisci.setDisable(true);
    	this.txtRisultato.setText("");
    	this.txtTentativi.setText("");
    	this.btnAbbandona.setDisable(true);
    	this.progBarTentativi.setProgress(0);
    	
    	this.btnFacile.setDisable(true);
    	this.btnMedia.setDisable(true);
    	this.btnDifficile.setDisable(true);
    	tentativoPrecMax=0;
        tentativoPrecMin=0;
        
        primoMin=true;
        primoMax= true;
        
        
        
        this.checkBoxModAss.setSelected(false);
    }
    
    public void setModel(Model model) {
    	this.model= model;
    }
  

}
