/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Resevierung_constructor_Command extends ServiceCommand<Resevierung>{
   private static final long serialVersionUID = -2106068228L;
   private Kategorie kategorie;
   private String  name;
   private Integer  anzahlPlaetze;
   private Vorfuehrung  vorfuehrung;
   public Resevierung_constructor_Command(Kategorie kategorie, String  name, Integer  anzahlPlaetze, Vorfuehrung  vorfuehrung){
      super();
      this.kategorie = kategorie;
      this.name = name;
      this.anzahlPlaetze = anzahlPlaetze;
      this.vorfuehrung = vorfuehrung;
   }
   public void execute(){
      try{this.result = Resevierung.createFresh(kategorie, name, anzahlPlaetze, vorfuehrung);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
