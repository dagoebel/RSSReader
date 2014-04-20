package sqlite;

/**
 * Created by corentin on 20/04/14.
 */
public class FluxRss {
    private int id;
    private String nom; // nom du flux (site)
    private String url; // url du flux

    public FluxRss(){}

    public FluxRss(String nom, String url){
        this.nom = nom;
        this.url = url;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString(){
        return "ID : " + id + " \n Nom: " + nom + " \n Url: " + url;
    }

}
