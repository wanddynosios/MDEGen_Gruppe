/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Vorfuehrung_constructor_Command extends ServiceCommand<Vorfuehrung>{
   private static final long serialVersionUID = 652988038L;
   private Film film;
   private Saal saal;
   private Integer  preisParkett;
   private Integer  preisMitte;
   private Integer  preisLoge;
   private Integer  freiePlaetzeParkett;
   private Integer  freiePlaetzeMitte;
   private Integer  freiePlaetzeLoge;
   public Vorfuehrung_constructor_Command(Film film, Saal saal, Integer  preisParkett, Integer  preisMitte, Integer  preisLoge, Integer  freiePlaetzeParkett, Integer  freiePlaetzeMitte, Integer  freiePlaetzeLoge){
      super();
      this.film = film;
      this.saal = saal;
      this.preisParkett = preisParkett;
      this.preisMitte = preisMitte;
      this.preisLoge = preisLoge;
      this.freiePlaetzeParkett = freiePlaetzeParkett;
      this.freiePlaetzeMitte = freiePlaetzeMitte;
      this.freiePlaetzeLoge = freiePlaetzeLoge;
   }
   public void execute(){
      try{this.result = Vorfuehrung.createFresh(film, saal, preisParkett, preisMitte, preisLoge, freiePlaetzeParkett, freiePlaetzeMitte, freiePlaetzeLoge);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
