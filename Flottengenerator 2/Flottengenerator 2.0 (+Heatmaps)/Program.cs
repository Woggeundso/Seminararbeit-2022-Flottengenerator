using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Runtime.InteropServices;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;

namespace Flottengenerator_2._0___Heatmaps_
{
    internal class Program
    {
        //Methoden
        static void LesehilfeAusgabe()
        {

            //Ausgabe Alphabet, zur Orientierung
            Console.Write("0  | ");
            for (char c = 'A'; c <= 'Z'; ++c)
            {
                Console.Write(c + "   ");
            }
            Console.WriteLine();

            //"-"-Zeichen
            for (int i = 0; i <= 106; i++)
            {
                Console.Write("-");
            }
        }
        static void HoehenAusgabeKarte(int h)
        {
            //Ausgabe der Hoehenangabe
            if (h < 9)
            {                                                               //Veränderte Ausgabe, wenn Zahl ein-/zwei-/dreistellig ist
                Console.Write((h + 1) + "  | ");
            }
            else if (h >= 9 && h < 99)
            {
                Console.Write((h + 1) + " | ");
            }
            else
            {
                Console.Write((h + 1) + "| ");
            }
        }
        static int GetRandomSeed()
        {
            byte[] bytes = new byte[4];
            RNGCryptoServiceProvider rngCsp = new RNGCryptoServiceProvider();

            rngCsp.GetBytes(bytes);
            return BitConverter.ToInt32(bytes, 0);
        }
        static int[,] SchiffsAlgorithmus(int schiffsGroesseAktuell, int hoeheKarte, int breiteKarte, int[,] karte)
        {
            bool wdhlSchleife = false;

            int vMax = 500;
            for (int v = 0; v <= vMax; v++)
            {


                do
                {
                    v++;
                    //Random Zahlen zwischen 0 und hoehe/breite generieren lassen
                    Random rand = new Random(GetRandomSeed());
                    int hoehe = rand.Next(0, hoeheKarte);
                    int breite = rand.Next(0, hoeheKarte);
                    int richtungAusbreitung_NOSW = rand.Next(1, 5);

                    //Console.WriteLine("v: " + v +"Schiffslänge" + schiffsGroesseAktuell+ ", hoehe: " + hoehe + ", breite: " + breite + ", richtungAusbreitung_NOSW: " + richtungAusbreitung_NOSW);

                    wdhlSchleife = false;
                    //alle Felder werden gecheckt (also bei nem 1er Schiff dann 9 Kästchen)
                    int hoeheEndPos = hoehe + 1;
                    int breiteEndPos = breite + 1;
                    int hoeheStartPos = hoehe - 1;
                    int breiteStartPos = breite - 1;

                    //nur die Felder mit dem Schiff drauf werden gecheckt (also bei nem 1er Schiff dann 1 Kästchen)
                    int hoeheEndPosNurSchiff = hoehe;
                    int breiteEndPosNurSchiff = breite;
                    int hoeheStartPosNurSchiff = hoehe;
                    int breiteStartPosNurSchiff = breite;

                    //Richtung NOSW:
                    switch (richtungAusbreitung_NOSW)
                    {
                        case 1:         //Norden
                                        //breite bleibt gleich
                            hoeheStartPos = hoehe - schiffsGroesseAktuell;
                            hoeheStartPosNurSchiff = hoeheStartPos + 1;

                            break;
                        case 2:         //Osten
                                        //hoehe bleibt gleich
                            breiteEndPos = breite + schiffsGroesseAktuell;
                            breiteEndPosNurSchiff = breiteEndPos - 1;
                            break;

                        case 3:         //Süden
                                        //breiteEndPos bleibt gleich
                            hoeheEndPos = hoehe + schiffsGroesseAktuell;
                            hoeheEndPosNurSchiff = hoeheEndPos - 1;
                            break;

                        case 4:         //Westen
                                        //hoehe bleibt gleich
                            breiteStartPos = breite - schiffsGroesseAktuell;
                            breiteStartPosNurSchiff = breiteStartPos + 1;
                            break;
                    }

                    //alle Kästchen um das Schiff und das Schiff selbst
                    for (int hoeheZaehler = hoeheStartPos; hoeheZaehler <= hoeheEndPos; hoeheZaehler++)                 //Hoehe
                    {
                        for (int breiteZaehler = breiteStartPos; breiteZaehler <= breiteEndPos; breiteZaehler++)        //Breite
                        {
                            try
                            {
                                if (karte[hoeheZaehler, breiteZaehler] == 1) wdhlSchleife = true;
                                //if (karte[hoehe + hoeheZaehler, breite + breiteZaehler] == 1) wdhlSchleife = true;
                            }
                            catch (Exception) { }
                        }
                    }
                    if (!wdhlSchleife)
                    {
                        //nur Kästchen, wo das Schiff ist
                        for (int hoeheZaehler = hoeheStartPosNurSchiff; hoeheZaehler <= hoeheEndPosNurSchiff; hoeheZaehler++)                 //Hoehe
                        {
                            for (int breiteZaehler = breiteStartPosNurSchiff; breiteZaehler <= breiteEndPosNurSchiff; breiteZaehler++)        //Breite
                            {
                                try
                                {
                                    if (karte[hoeheZaehler, breiteZaehler] == 1) wdhlSchleife = true; //bereits überprüft
                                }
                                catch (Exception) { wdhlSchleife = true; } //das hier ist wichtig
                            }
                        }
                    }

                    //Schiff wird platziert
                    if (!wdhlSchleife)
                    {
                        //wenn man ein Schiff platzieren könnte, so wird es nun platziert.
                        for (int hoeheZaehler = hoeheStartPosNurSchiff; hoeheZaehler <= hoeheEndPosNurSchiff; hoeheZaehler++)            //Hoehe
                        {
                            for (int breiteZaehler = breiteStartPosNurSchiff; breiteZaehler <= breiteEndPosNurSchiff; breiteZaehler++)   //Breite
                            {
                                karte[hoeheZaehler, breiteZaehler] = 1;
                            }
                        }

                    }

                    if (v == vMax || !wdhlSchleife)
                    {
                        break;
                    }

                } while (wdhlSchleife && v < vMax);

                if (v == vMax) //Temp
                {
                    Console.WriteLine("FEHLERHAFTE KARTE");
                }

                if (v == vMax || !wdhlSchleife)
                {

                    break;
                }

            }
            return karte;
        }



        //MAIN----------------------------
        static void Main(string[] args)
        {

            Console.WriteLine("Flottengenerator 2.1 Heatmap");
            int[,] heatmapArray = new int[10, 10];
            int[,] karte = new int[10, 10];

            for (int durchlaeufe = 1; durchlaeufe <= 20; durchlaeufe++)
            {

                //1. Initialisierung
                int hoehe = 10;
                int breite = 10;
                int maxSchiffslaenge = 5;

                int[] schiffe = new int[maxSchiffslaenge + 1];
                schiffe[0] = 0;
                schiffe[1] = 0;
                schiffe[2] = 4;
                schiffe[3] = 3;
                schiffe[4] = 2;
                schiffe[5] = 1;

                //Algorithmus zum Setzen der Schiffe
                for (int g = maxSchiffslaenge; g > 0; g--)
                {

                    for (int a = schiffe[g]; a > 0; a--)
                    {                                                //Anzahl der Schiffe einer Schiffsgröße, z.B.2-->1-->0 Stück.

                        karte = SchiffsAlgorithmus(g, hoehe, breite, karte);
                    }

                }


                //Werte von Karte in Heatmap
                for (int h = 0; h < 10; h++)
                {
                    for (int b = 0; b < 10; b++)
                    {
                        heatmapArray[h, b] = heatmapArray[h, b] + karte[h, b];
                        //Karte wird zur Heatmap addiert und dann genullt
                        karte[h, b] = 0;
                    }
                }
                Console.WriteLine("Karte zu Heatmap addiert und genullt. Durchläufe: " + durchlaeufe);


            } //Heatmap

            Console.Clear();
            //Ausgabe der Heatmap
            Console.WriteLine("\n\nHeatmap: ");
            for (int h = 0; h < 10; h++)
            {
                for (int b = 0; b < 10; b++)
                {
                    Console.Write(heatmapArray[h, b] + ", ");
                }
                Console.WriteLine();
            }

            //Ende des Programms
            Console.ReadKey();
            Console.ReadKey();
        }
    }
}
