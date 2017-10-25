package com.globokas.entity;

/**
 *
 * @author pvasquez
 */
public class BeanReporteSeguimiento {
    
    private static final long serialVersionUID = 1L;
	private int id_rep,enter;
	private String des_rep_esp,des_rep_eng,nom_rep,nom_proc,nom_asunto_es,nom_asunto_in,porcentaje,cant_rep, cant_repCMH, cant_repCS, cant_repCNG, cant_repSCO, cant_repCSC;
	private String mail,nom_mail,ver_mail,mial_ToCcBcc,adjunto;
	
	public String getCant_repSCO() {
		return cant_repSCO;
	}
	public void setCant_repSCO(String cant_repSCO) {
		this.cant_repSCO = cant_repSCO;
	}
	public String getCant_repCSC() {
		return cant_repCSC;
	}
	public void setCant_repCSC(String cant_repCSC) {
		this.cant_repCSC = cant_repCSC;
	}
	public String getCant_repCNG() {
		return cant_repCNG;
	}
	public void setCant_repCNG(String cant_repCNG) {
		this.cant_repCNG = cant_repCNG;
	}
	public String getCant_repCS() {
		return cant_repCS;
	}
	public void setCant_repCS(String cant_repCS) {
		this.cant_repCS = cant_repCS;
	}
	public String getMail() {
		return mail;
	}
	public String getCant_repCMH() {
		return cant_repCMH;
	}
	public void setCant_repCMH(String cant_repCMH) {
		this.cant_repCMH = cant_repCMH;
	}
	public String getAdjunto() {
		return adjunto;
	}
	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNom_mail() {
		return nom_mail;
	}
	public void setNom_mail(String nom_mail) {
		this.nom_mail = nom_mail;
	}
	public String getVer_mail() {
		return ver_mail;
	}
	public void setVer_mail(String ver_mail) {
		this.ver_mail = ver_mail;
	}
	public String getCant_rep() {
		return cant_rep;
	}
	public void setCant_rep(String cant_rep) {
		this.cant_rep = cant_rep;
	}
	public int getId_rep() {
		return id_rep;
	}
	public void setId_rep(int id_rep) {
		this.id_rep = id_rep;
	}

	public String getDes_rep_esp() {
		return des_rep_esp;
	}
	public void setDes_rep_esp(String des_rep_esp) {
		this.des_rep_esp = des_rep_esp;
	}
	public String getDes_rep_eng() {
		return des_rep_eng;
	}
	public void setDes_rep_eng(String des_rep_eng) {
		this.des_rep_eng = des_rep_eng;
	}
	public String getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getNom_rep() {
		return nom_rep;
	}
	public void setNom_rep(String nom_rep) {
		this.nom_rep = nom_rep;
	}
	public String getNom_proc() {
		return nom_proc;
	}
	public void setNom_proc(String nom_proc) {
		this.nom_proc = nom_proc;
	}
	public int getEnter() {
		return enter;
	}
	public void setEnter(int enter) {
		this.enter = enter;
	}
	public String getNom_asunto_es() {
		return nom_asunto_es;
	}
	public void setNom_asunto_es(String nom_asunto_es) {
		this.nom_asunto_es = nom_asunto_es;
	}
	public String getNom_asunto_in() {
		return nom_asunto_in;
	}
	public void setNom_asunto_in(String nom_asunto_in) {
		this.nom_asunto_in = nom_asunto_in;
	}
	public String getMial_ToCcBcc() {
		return mial_ToCcBcc;
	}
	public void setMial_ToCcBcc(String mial_ToCcBcc) {
		this.mial_ToCcBcc = mial_ToCcBcc;
	}
    
}
