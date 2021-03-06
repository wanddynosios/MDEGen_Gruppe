/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Vorfuehrung_constructor_Command extends ServiceCommand<Vorfuehrung>{
   private static final long serialVersionUID = -2076315999L;
   private Film film;
   private Saal saal;
   private Integer  vorfuehrungsNummer;
   private Integer  preisParkett;
   private Integer  preisMitte;
   private Integer  preisLoge;
   private Integer  freiePlaetzeParkett;
   private Integer  freiePlaetzeMitte;
   private Integer  freiePlaetzeLoge;
   private Boolean  bereitsVorbei;
   public Vorfuehrung_constructor_Command(Film film, Saal saal, Integer  vorfuehrungsNummer, Integer  preisParkett, Integer  preisMitte, Integer  preisLoge, Integer  freiePlaetzeParkett, Integer  freiePlaetzeMitte, Integer  freiePlaetzeLoge, Boolean  bereitsVorbei){
      super();
      this.film = film;
      this.saal = saal;
      this.vorfuehrungsNummer = vorfuehrungsNummer;
      this.preisParkett = preisParkett;
      this.preisMitte = preisMitte;
      this.preisLoge = preisLoge;
      this.freiePlaetzeParkett = freiePlaetzeParkett;
      this.freiePlaetzeMitte = freiePlaetzeMitte;
      this.freiePlaetzeLoge = freiePlaetzeLoge;
      this.bereitsVorbei = bereitsVorbei;
   }
   public void execute(){
      try{this.result = Vorfuehrung.createFresh(film, saal, vorfuehrungsNummer, preisParkett, preisMitte, preisLoge, freiePlaetzeParkett, freiePlaetzeMitte, freiePlaetzeLoge, bereitsVorbei);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
