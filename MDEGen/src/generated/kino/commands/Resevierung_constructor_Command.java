/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Resevierung_constructor_Command extends ServiceCommand<Resevierung>{
   private static final long serialVersionUID = -1173767960L;
   private Kategorie kategorie;
   private String  name;
   private Integer  anzahlPlaetze;
   private Boolean  istBereitsEingeloest;
   private Vorfuehrung  vorfuehrung;
   public Resevierung_constructor_Command(Kategorie kategorie, String  name, Integer  anzahlPlaetze, Boolean  istBereitsEingeloest, Vorfuehrung  vorfuehrung){
      super();
      this.kategorie = kategorie;
      this.name = name;
      this.anzahlPlaetze = anzahlPlaetze;
      this.istBereitsEingeloest = istBereitsEingeloest;
      this.vorfuehrung = vorfuehrung;
   }
   public void execute(){
      try{this.result = Resevierung.createFresh(kategorie, name, anzahlPlaetze, istBereitsEingeloest, vorfuehrung);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
