import java.util.Random;
import java.util.Scanner;

public class FlotteGenerieren {

	public static void main(String[] args) {
		
		System.out.println("Flotte generieren");				//Startnachricht (STRG+F11 zum Starten Drücken!)
		
		
		
//1. Initialisierung
		int hoehe = 0;											//Initialisierung und erstmalige Deklaration der hoehe und breite
		int breite = 0;
		int maxSchiffslaenge=0;
		int startPosH = 0;
		int startPosB = 0;
		int richtung = 0;
		boolean wdhlGes = true;
		
//2. Eingabe-Abfrage am Anfang, um alle Werte festzulegen:
		
		
		
		//Höhe vom Spielfeld (hoehe)
		System.out.println("Bitte geben Sie Anzahl der Felder in die Höhe an:");
			
		do {													//"do-while"-Schleife für Wiederholungen, wenn Fehleingabe
			try {												//Versuch, den Code in "try" durchzuführen
				Scanner hoeheInput = new Scanner(System.in);
				hoehe = hoeheInput.nextInt();
				System.out.println("Höhe: "+hoehe);
			}
			catch (Exception e) {								//Falls in "try" ein Eingabefehler passiert: --> "catch"
			}
			finally{											//nach "try" oder "catch" wird "finally" durchgeführt
				
				if(hoehe<=0) {
					System.out.println("Fehler. Bitte geben Sie eine Zahl größer als Null und ohne Nachkommastellen an!");
				}
			};
		} while (hoehe<=0);										//"do-while"-Wiederholung, falls hoehe<=0 ist
		
		
		
		//Breite vom Spielfeld (breite)
			System.out.println("\nBitte geben Sie Anzahl der Felder in die Breite an:");
			
			do {		
				try {
					Scanner breiteInput = new Scanner(System.in);
					breite = breiteInput.nextInt();
					System.out.println("Breite: "+breite);
				}
				catch (Exception e) {
				}
				finally{
					
					if(breite<=0) {
						System.out.println("Fehler. Bitte geben Sie eine Zahl größer als Null und ohne Nachkommastellen an!");
					}
				};
			} while (breite<=0);
		System.out.println("\n");									//Leerzeile
		
		
		
//3. Daraus resultierende Karte:
		
		String karte[][] = new String[hoehe][breite];				//Erstellung der Karte: Vorstellbar als Fläche:
																	//  senkrecht die hoehe und waagrecht die breite
		
		
//Ausgabe Lesehilfe (ABC und Zahlen am Rand), Zeilenumbrüche)		
		System.out.print("0  | ");									//Ausgabe Alphabet, zur Orientierung
															
	    for(char c = 'A'; c <= 'Z'; ++c) {
	      System.out.print(c + "   ");
	    }
	    
	    for(char c = 'A'; c <= 'Z'; ++c) {
		      
		      for(char v = 'A'; v <= 'Z'; ++v) {
			      System.out.print(c +""+ v+ "  ");
			    }
		    }
	    System.out.println();										//"-"-Zeichen
	    
	    for (int i=0; i<=2810; i++) {
	    	System.out.print("-");
	    }
	    
			
	    
//Ausgabe Karte	    												//"for"-Schleife: Start bei h=0, bis h=hoehe (dann stopp)
			System.out.println("   | \n   |");							//nach jeder Durchführung: 	h=h+1 	(=h++)
		for (int h=0; h<hoehe; h++) {	
			
			
			
			if(h<9) {												//Veränderte Ausgabe, wenn Zahl ein-/zwei-/dreistellig ist
				System.out.print((h+1)+ "  | ");
			}
			else if(h>=9 && h<99) {
				System.out.print((h+1)+ " | ");
			}
			else {
				System.out.print((h+1)+ "| ");
			}
			
			
			for (int b =0; b<breite; b++) {
				karte[h][b] = ".";
				
				System.out.print(karte[h][b] + "   ");
			}
			System.out.print("\n   |\n");
		}
		
		
		
//4. Abfrage zu platzierende Schiffe:
		//4.1 Bis zu welcher Schiffslänge sollen die Schiffe platziert werden?
		
		System.out.println("\n\nBitte geben Sie die von Ihnen gewünschte maximale Schiffslänge eines Schiffes an:");
		
		do {		
			try {
				Scanner maxSchiffslaengeInput = new Scanner(System.in);
				maxSchiffslaenge = maxSchiffslaengeInput.nextInt();
				System.out.println("Maximale Schiffslänge: "+maxSchiffslaenge);
			}
			catch (Exception e) {
			}
			finally{
				
				if(maxSchiffslaenge<=0) {
					System.out.println("Fehler. Bitte geben Sie eine Zahl größer als Null und ohne Nachkommastellen an!");
				}
			};
		} while (maxSchiffslaenge<=0);
		System.out.println();	
		

		
		//4.2  --> Anzahl der Schiffe je Schiffslänge:				
		
		int schiffe[] = new int [maxSchiffslaenge+1];				//in dieses Array werden die Schiffsanzahlen eingetragen
		//schiffe[0]= 0 lang--> /,   schiffe[1]= 1 lang ...
		
		System.out.println("Bitte geben Sie die Anzahl der einzelnen Schiffslängen an:");
		
		for (int s=1; s<=maxSchiffslaenge;s++) {
			
			boolean wdhlSchleife = true;
			do {		
				
				try {
					System.out.println("Anzahl der "+ s +"er Schiffe:");
					
					Scanner schiffeInput = new Scanner(System.in);
					schiffe[s] = schiffeInput.nextInt();
					
					System.out.println(schiffe[s] + "x " + s + "er Schiffe");
					wdhlSchleife = false;
				}
				catch (Exception e) {
					System.out.println("Fehler. Bitte geben Sie eine Zahl ohne Nachkommastellen an!");
					wdhlSchleife = true;
				};
			} while (wdhlSchleife==true);
			
		}
		
		System.out.println("\nÜberblick über die Schiffe:");						//Ausgabe der Eingaben der einzelnen Schiffe
		for (int i =1; i<=maxSchiffslaenge; i++) {
			
			if(schiffe[i]>0) {
				System.out.println(i + "er Schiffe: " + schiffe[i] +"");
			}
		}
		System.out.println("\n");
		
		System.out.println("Die Schiffe werden nun auf zufälligen Positionen auf der Karte platziert.");
		
		
		
//5. Auswahl der Schiffe (von groß nach klein)
		
		for (int g = maxSchiffslaenge; g>0; g--) {			//Größe der Schiffe, z.B. 3 --> 2 --> 1 lang.
			
			for (int a= schiffe[g]; a>0; a--) {				//Anzahl der Schiffe einer Schiffsgröße, z.B. 2 --> 1 -->0 Stück.
			//ab hier gleicher Prozess für jedes einzelne Schiff, hintereinander:
				
				
				
		//Generator der Zufallspositionen und der Orientierung der Schiffe.
		//	Die Schiffe werden auf zufälligen Positionen auf der Karte gesetzt. Dabei wird erst getestet, ob diese Position bereits
		//	besetzt wurde, oder ob sich daneben bereits Schiffe befinden.
		//	Darauffolgend wird versucht, vom Startpunkt aus in eine zufällige Richtung (N/O/S/W) die gesamte Länge auszubreiten.
		//	Also: Neuversuch, wenn Startfeld bereits besetzt, wenn umliegende Felder bereits besetzt, oder wenn keine der zufälligen
		//	Richtungen frei ist.
				
				boolean wdhlSchleife = true; 											//Schleifenwiederholung, wenn wdhlSchleife=true
				
				//Generator Startposition	
				do{	
																
					//5.1 Startposition Starthoehe und Startbreite: Generiert Zufallsnummern zwischen 0 und "hoehe" bzw. "breite":
					
					Random rdmNummer = new Random();
					startPosH = rdmNummer.nextInt(hoehe);
					
					rdmNummer = new Random();
					startPosB = rdmNummer.nextInt(breite);
									
					
					//Test, ob außenrum 1 Kästchen frei ist:
					wdhlSchleife = false;
					//Mitte Mitte
					if (karte[startPosH][startPosB] == "x") {	wdhlSchleife = true; }
					//obere Reihe
					try {		if (karte[startPosH+1][startPosB-1] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					try {		if (karte[startPosH+1][startPosB] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					try {		if (karte[startPosH+1][startPosB+1] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					//Mitte L & R
					try {		if (karte[startPosH][startPosB-1] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					try {		if (karte[startPosH][startPosB+1] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					//Untere Reihe
					try {		if (karte[startPosH-1][startPosB-1] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					try {		if (karte[startPosH-1][startPosB+1] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
					try {		if (karte[startPosH-1][startPosB] == "x") { wdhlSchleife = true;}			}catch(Exception e) {};
		
					
				} while(wdhlSchleife==true);
				
				//Ausgabe (+1, da für den Nutzer) und Markierung der Startposition als "x"
				System.out.print((startPosH+1) + "-" + (startPosB+1) + "  ");
				karte[startPosH][startPosB] = "X";
				
				
				
				//5.2  Zufallsgenerator, um die Richtung der Ausbreitung des Schiffs von der Startposition aus zu bestimmen:
				
				
				
				do {
					wdhlGes = false;
					int run =0;
					
					do {		//1
						run++;
						wdhlSchleife=false;
						
						Random rdmRichtung = new Random();
						richtung = rdmRichtung.nextInt(4);   				//0=Nord, 1=Ost, 2=Sued, 3=West
																			//längeAktuellesSchiff = las
						
						
						for (int las=g; las>0; las--) {					

							if(richtung == 0) {				

								try {	if (karte[startPosH-las][startPosB-1] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};    //
								try {	if (karte[startPosH-las][startPosB] == "x") { wdhlSchleife = true;}		}catch(Exception e) {};
								try {	if (karte[startPosH-las][startPosB+1] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};

								if(las>1) {
									try {		
										if (karte[startPosH-las+1][startPosB] == "x") { wdhlSchleife = true;}
									} catch(Exception e) {
										wdhlSchleife=true; }
									}
								
							}
					
							else if (richtung == 1) {		//wenn Osten: Checken, ob die Felder besetzt sind
								try {	if (karte[startPosH+1][startPosB+las] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								try {	if (karte[startPosH][startPosB+las] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								try {	if (karte[startPosH-1][startPosB+las] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								if(las>1) {
								try {		
									if (karte[startPosH][startPosB-1+las] == "x") { wdhlSchleife = true;}
								} catch(Exception e) {
									wdhlSchleife=true; } 
								}
							}
					
							else if (richtung ==2) {		//wenn Süden: Checken, ob die Felder besetzt sind
								try {	if (karte[startPosH+las][startPosB-1] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								try {	if (karte[startPosH+las][startPosB] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								try {	if (karte[startPosH+las][startPosB+1] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};    
								if(las>1) {
								try {		
									if (karte[startPosH-1+las][startPosB] == "x") { wdhlSchleife = true;}
								} catch(Exception e) {
									wdhlSchleife=true; }
								}
							}
							
							else if (richtung ==3) {		//wenn Westen: Checken, ob die Felder besetzt sind
								try {	if (karte[startPosH-1][startPosB-las] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								try {	if (karte[startPosH][startPosB-las] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								try {	if (karte[startPosH+1][startPosB-las] == "x") { wdhlSchleife = true;}	}catch(Exception e) {};
								if(las>1) {
								try {		
									if (karte[startPosH][startPosB+1-las] == "x") { wdhlSchleife = true;}
								} catch(Exception e) {
									wdhlSchleife=true; }
								}
							}
						}
						
							
				}while(wdhlSchleife==true || run<5);		//1
					
					//5.3   wir haben die Richtung des Schiffs bestimmt. Nun werden die Felder mit "x" markiert.
					
					if(wdhlGes==false) {
						try {
							for(int las=g; las>0; las--) {
								
								if(richtung==0) {
									karte[startPosH-las+1][startPosB] = "x";}
								else if(richtung==1) {
									karte[startPosH][startPosB+las-1] = "x";
								}
								else if(richtung==2) {
									karte[startPosH+las-1][startPosB] = "x";
								}
								else if(richtung==3) {
									karte[startPosH][startPosB-las+1] = "x";
								}
							}
						} catch(Exception e) {
							wdhlGes=true;}
						
					}
					
				}while (wdhlGes == true);	
				
				
			} 		//for(a)
		} 			//for(g)
		
		
		
		
		//6.  erneute Ausgabe Lesehilfe (ABC und Zahlen am Rand), Zeilenumbrüche)	
		
		System.out.print("\n\n0  | ");								
	    for(char c = 'A'; c <= 'Z'; ++c) {
	      System.out.print(c + "   ");
	    }
	    for(char c = 'A'; c <= 'Z'; ++c) {
		      for(char v = 'A'; v <= 'Z'; ++v) {
			      System.out.print(c +""+ v+ "  ");
			    }
		    }
	    System.out.println();
	    for (int i=0; i<=2134; i++) {
	    	System.out.print("-");
	    }
		//Ausgabe Karte	    												
			System.out.println("   | ");							
		for (int h=0; h<hoehe; h++) {	
			System.out.println();
			if(h<9) {												
				System.out.print((h+1)+ "  | ");
			}
			else if(h>=9 && h<99) {
				System.out.print((h+1)+ " | ");
			}
			else {
				System.out.print((h+1)+ "| ");
			}
			for (int b =0; b<breite; b++) {
				System.out.print(karte[h][b] + "   ");
			}
			System.out.println();
		}
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
