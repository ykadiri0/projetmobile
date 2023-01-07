package com.project.retrofit.model;


import java.util.Date;
import java.util.List;
import java.util.Set;

public class PharmacieGard {

    private Integer id;
    private Date date_debut;
    private Date date_fin;
    private List<Pharmacie> pharmacie;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public PharmacieGard(Integer id, Date date_debut, Date date_fin, List<Pharmacie> pharmacie, List<Gard> gard, String photo) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.pharmacie = pharmacie;
        this.photo = photo;
        this.gard = gard;
    }

    private List<Gard> gard;

    public List<Pharmacie> getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(List<Pharmacie> pharmacie) {
        this.pharmacie = pharmacie;
    }

    public List<Gard> getGard() {
        return gard;
    }

    public void setGard(List<Gard> gard) {
        this.gard = gard;
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "PharmacieGard{" +
                "id=" + id +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", pharmacie=" + pharmacie +
                ", photo='" + photo + '\'' +
                ", gard=" + gard +
                '}';
    }
}
