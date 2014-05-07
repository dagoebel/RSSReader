package sqlite;


/**
 * Created by corentin on 17/04/14.
 */
public class Categorie {
    private int id;
    private String nom;
    private String description;

    public Categorie(){}

    public Categorie(String nom, String description){
        this.nom = nom;
        this.description = description;
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

    public String getDescription(){ return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        /* return "ID : " + id + " \n Nom: " + nom + "\n Description: " + description; */
        return nom;
    }
}
