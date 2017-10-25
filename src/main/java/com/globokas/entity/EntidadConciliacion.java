package com.globokas.entity;

/**
 *
 * @author pvasquez
 */
public class EntidadConciliacion {

    private static final long serialVersionUID = 1L;
    private int id_ent;
    private String des_ent;
    private int long_cta;
    private String tipo;
    private int compensa;
    private int id_ent_base;
    private int id_estado;
    private String vc_correo_aviso;
    private String vc_bin;
    private String vc_masc_cta_int;
    private String vc_masc_cta_ext;
    private String vc_ruc;
    private String vc_razon_social;
    private int in_banca_udp;
    private int in_migracion_udp;
    private int in_agente;
    private String vc_tabla_log_transac;
    private String vc_ip_banca_desarrollo;
    private String vc_host_ftp;
    private String vc_username_ftp;
    private String vc_password_ftp;
    private String vc_carpeta_destino;
    private String vc_carpeta_generacion;
    private String vc_extension_archivo;
    private String vc_cadena_conexion;
    private String in_valida_archivo;
    private String vc_descripcion_reporte;
    private String vc_modo_conexion;
    private int in_comprime_archivo;
    private int in_envio_correo;
    private int in_genera_conciliacion;
    private int in_envia_conciliacion;
    private int in_carga_conciliacion;
    private String vc_nombre_archivo_conciliacion;
    private String vc_nombre_archivo_conciliacion_ext;
    private int in_cantidad_parametros;
    private String vc_nombre_stored_sp;
    private int in_nombre_fecha_archivo;
    private int in_encripta_gpg;
    private int in_genera_archivo_ofuscado;

    public int getIn_envio_correo() {
        return in_envio_correo;
    }

    public void setIn_envio_correo(int in_envio_correo) {
        this.in_envio_correo = in_envio_correo;
    }

    public int getIn_comprime_archivo() {
        return in_comprime_archivo;
    }

    public void setIn_comprime_archivo(int in_comprime_archivo) {
        this.in_comprime_archivo = in_comprime_archivo;
    }

    public String getVc_modo_conexion() {
        return vc_modo_conexion;
    }

    public void setVc_modo_conexion(String vc_modo_conexion) {
        this.vc_modo_conexion = vc_modo_conexion;
    }

    public String getVc_descripcion_reporte() {
        return vc_descripcion_reporte;
    }

    public void setVc_descripcion_reporte(String vc_descripcion_reporte) {
        this.vc_descripcion_reporte = vc_descripcion_reporte;
    }

    public String getIn_valida_archivo() {
        return in_valida_archivo;
    }

    public void setIn_valida_archivo(String in_valida_archivo) {
        this.in_valida_archivo = in_valida_archivo;
    }

    public String getVc_cadena_conexion() {
        return vc_cadena_conexion;
    }

    public void setVc_cadena_conexion(String vc_cadena_conexion) {
        this.vc_cadena_conexion = vc_cadena_conexion;
    }

    public String getVc_extension_archivo() {
        return vc_extension_archivo;
    }

    public void setVc_extension_archivo(String vc_extension_archivo) {
        this.vc_extension_archivo = vc_extension_archivo;
    }

    public String getVc_carpeta_generacion() {
        return vc_carpeta_generacion;
    }

    public void setVc_carpeta_generacion(String vc_carpeta_generacion) {
        this.vc_carpeta_generacion = vc_carpeta_generacion;
    }

    public int getIn_carga_conciliacion() {
        return in_carga_conciliacion;
    }

    public void setIn_carga_conciliacion(int in_carga_conciliacion) {
        this.in_carga_conciliacion = in_carga_conciliacion;
    }

    public String getVc_nombre_archivo_conciliacion() {
        return vc_nombre_archivo_conciliacion;
    }

    public void setVc_nombre_archivo_conciliacion(String vc_nombre_archivo_conciliacion) {
        this.vc_nombre_archivo_conciliacion = vc_nombre_archivo_conciliacion;
    }

    public int getIn_cantidad_parametros() {
        return in_cantidad_parametros;
    }

    public void setIn_cantidad_parametros(int in_cantidad_parametros) {
        this.in_cantidad_parametros = in_cantidad_parametros;
    }

    public String getVc_nombre_stored_sp() {
        return vc_nombre_stored_sp;
    }

    public void setVc_nombre_stored_sp(String vc_nombre_stored_sp) {
        this.vc_nombre_stored_sp = vc_nombre_stored_sp;
    }

    public String getVc_host_ftp() {
        return vc_host_ftp;
    }

    public void setVc_host_ftp(String vc_host_ftp) {
        this.vc_host_ftp = vc_host_ftp;
    }

    public String getVc_username_ftp() {
        return vc_username_ftp;
    }

    public void setVc_username_ftp(String vc_username_ftp) {
        this.vc_username_ftp = vc_username_ftp;
    }

    public String getVc_password_ftp() {
        return vc_password_ftp;
    }

    public void setVc_password_ftp(String vc_password_ftp) {
        this.vc_password_ftp = vc_password_ftp;
    }

    public String getVc_carpeta_destino() {
        return vc_carpeta_destino;
    }

    public void setVc_carpeta_destino(String vc_carpeta_destino) {
        this.vc_carpeta_destino = vc_carpeta_destino;
    }

    public int getIn_genera_conciliacion() {
        return in_genera_conciliacion;
    }

    public void setIn_genera_conciliacion(int in_genera_conciliacion) {
        this.in_genera_conciliacion = in_genera_conciliacion;
    }

    public int getIn_envia_conciliacion() {
        return in_envia_conciliacion;
    }

    public void setIn_envia_conciliacion(int in_envia_conciliacion) {
        this.in_envia_conciliacion = in_envia_conciliacion;
    }

    public String getVc_ip_banca_desarrollo() {
        return vc_ip_banca_desarrollo;
    }

    public void setVc_ip_banca_desarrollo(String vc_ip_banca_desarrollo) {
        this.vc_ip_banca_desarrollo = vc_ip_banca_desarrollo;
    }

    public String getVc_razon_social() {
        return vc_razon_social;
    }

    public void setVc_razon_social(String vc_razon_social) {
        this.vc_razon_social = vc_razon_social;
    }

    public String getVc_ruc() {
        return vc_ruc;
    }

    public void setVc_ruc(String vc_ruc) {
        this.vc_ruc = vc_ruc;
    }

    public String getVc_tabla_log_transac() {
        return vc_tabla_log_transac;
    }

    public void setVc_tabla_log_transac(String vc_tabla_log_transac) {
        this.vc_tabla_log_transac = vc_tabla_log_transac;
    }

    public int getId_ent() {
        return id_ent;
    }

    public void setId_ent(int id_ent) {
        this.id_ent = id_ent;
    }

    public String getDes_ent() {
        return des_ent;
    }

    public void setDes_ent(String des_ent) {
        this.des_ent = des_ent;
    }

    public int getLong_cta() {
        return long_cta;
    }

    public void setLong_cta(int long_cta) {
        this.long_cta = long_cta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCompensa() {
        return compensa;
    }

    public void setCompensa(int compensa) {
        this.compensa = compensa;
    }

    public int getId_ent_base() {
        return id_ent_base;
    }

    public void setId_ent_base(int id_ent_base) {
        this.id_ent_base = id_ent_base;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getVc_correo_aviso() {
        return vc_correo_aviso;
    }

    public void setVc_correo_aviso(String vc_correo_aviso) {
        this.vc_correo_aviso = vc_correo_aviso;
    }

    public String getVc_bin() {
        return vc_bin;
    }

    public void setVc_bin(String vc_bin) {
        this.vc_bin = vc_bin;
    }

    public String getVc_masc_cta_int() {
        return vc_masc_cta_int;
    }

    public void setVc_masc_cta_int(String vc_masc_cta_int) {
        this.vc_masc_cta_int = vc_masc_cta_int;
    }

    public String getVc_masc_cta_ext() {
        return vc_masc_cta_ext;
    }

    public void setVc_masc_cta_ext(String vc_masc_cta_ext) {
        this.vc_masc_cta_ext = vc_masc_cta_ext;
    }

    public int getIn_banca_udp() {
        return in_banca_udp;
    }

    public void setIn_banca_udp(int in_banca_udp) {
        this.in_banca_udp = in_banca_udp;
    }

    public int getIn_migracion_udp() {
        return in_migracion_udp;
    }

    public void setIn_migracion_udp(int in_migracion_udp) {
        this.in_migracion_udp = in_migracion_udp;
    }

    public int getIn_agente() {
        return in_agente;
    }

    public void setIn_agente(int in_agente) {
        this.in_agente = in_agente;
    }

    public String getVc_nombre_archivo_conciliacion_ext() {
        return vc_nombre_archivo_conciliacion_ext;
    }

    public void setVc_nombre_archivo_conciliacion_ext(String vc_nombre_archivo_conciliacion_ext) {
        this.vc_nombre_archivo_conciliacion_ext = vc_nombre_archivo_conciliacion_ext;
    }

    public int getIn_nombre_fecha_archivo() {
        return in_nombre_fecha_archivo;
    }

    public void setIn_nombre_fecha_archivo(int in_nombre_fecha_archivo) {
        this.in_nombre_fecha_archivo = in_nombre_fecha_archivo;
    }

    public int getIn_encripta_gpg() {
        return in_encripta_gpg;
    }

    public void setIn_encripta_gpg(int in_encripta_gpg) {
        this.in_encripta_gpg = in_encripta_gpg;
    }

    public int getIn_genera_archivo_ofuscado() {
        return in_genera_archivo_ofuscado;
    }

    public void setIn_genera_archivo_ofuscado(int in_genera_archivo_ofuscado) {
        this.in_genera_archivo_ofuscado = in_genera_archivo_ofuscado;
    }
    
}
