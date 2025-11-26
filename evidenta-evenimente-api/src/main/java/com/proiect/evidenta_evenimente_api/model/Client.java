package com.proiect.evidenta_evenimente_api.model;

public class Client {
    private Long id;
    private String nume;
    private String email;
    private String telefon;

    // 1. Constructor gol (Obligatoriu pentru Spring)
    public Client() {}

    // 2. Constructor complet (Opțional, util pentru teste)
    public Client(Long id, String nume, String email, String telefon) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
    }

    // 3. Getters și Setters (Esențiali pentru a accesa datele)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}