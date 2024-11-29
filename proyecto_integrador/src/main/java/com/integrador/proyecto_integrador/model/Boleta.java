package com.integrador.proyecto_integrador.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "boleta")
public class Boleta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Sirve como auto incremento y identifica que ID es la clave primaria
    @Column(name = "id_detalle_boleta")
    private Integer id_boleta;

    @Column(name = "fecha")
    private LocalDate fecha_boleta;

    @Column(name = "sub_total")
    private Double total_boleta;

    @Column(name = "metodo_pago")
    private String metpago_boleta;

    @Column(name = "dedicatoria")
    private String dedicatoria;

    @Column(name = "cantidad")
    private Integer cantidad_bol;

    public Integer getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(Integer id_boleta) {
        this.id_boleta = id_boleta;
    }

    public LocalDate getFecha_boleta() {
        return fecha_boleta;
    }

    public void setFecha_boleta(LocalDate fecha_boleta) {
        this.fecha_boleta = fecha_boleta;
    }

    public Double getTotal_boleta() {
        return total_boleta;
    }

    public void setTotal_boleta(Double total_boleta) {
        this.total_boleta = total_boleta;
    }

    public String getMetpago_boleta() {
        return metpago_boleta;
    }

    public void setMetpago_boleta(String metpago_boleta) {
        this.metpago_boleta = metpago_boleta;
    }

    public String getDedicatoria() {
        return dedicatoria;
    }

    public void setDedicatoria(String dedicatoria) {
        this.dedicatoria = dedicatoria;
    }

    @ManyToMany
    @JoinTable(
        name = "boleta_has_torta_clasica", 
        joinColumns = @JoinColumn(name = "boleta_id_detalle_boleta"), 
        inverseJoinColumns = @JoinColumn(name = "torta_clasica_id_tortac")
    )
    private List<TortaClasica> tortasClasicas= new ArrayList<>();
    public List<TortaClasica> getTortasClasicas() {
        return tortasClasicas;
    }

    public void setTortasClasicas(List<TortaClasica> tortasClasicas) {
        this.tortasClasicas = tortasClasicas;
    }
    @ManyToMany
    @JoinTable(
        name = "boleta_has_torta_especial", 
        joinColumns = @JoinColumn(name = "boleta_id_detalle_boleta"), 
        inverseJoinColumns = @JoinColumn(name = "torta_especial_id_tortae")
    )
    private List<TortaEspecial> tortaEspeciales = new ArrayList<>();
    public List<TortaEspecial> getTortaEspeciales() {
        return tortaEspeciales;
    }

    public void setTortaEspeciales(List<TortaEspecial> tortaEspeciales) {
        this.tortaEspeciales = tortaEspeciales;
    }

    @ManyToMany
    @JoinTable(
        name = "boleta_has_vela",
        joinColumns = @JoinColumn(name = "boleta_id_detalle_boleta"),
        inverseJoinColumns = @JoinColumn(name = "vela_id_vela")
    )
    private List<Vela> velas = new ArrayList<>(); // Mantén la inicialización para evitar NPE
    
    public List<Vela> getVelas() {
        return velas;
    }
    
    public void setVelas(List<Vela> velas) {
        this.velas = velas;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_dni")  // 'dni' es la clave foránea que referencia a Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "entrega_producto_id_entrega")
    private TipoEnvio tipoenvio;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoEnvio getTipoenvio() {
        return tipoenvio;
    }

    public void setTipoenvio(TipoEnvio tipoenvio) {
        this.tipoenvio = tipoenvio;
    }

    public Boleta(Integer id_boleta, LocalDate fecha_boleta, double total_boleta, String metpago_boleta,
        String dedicatoria, List<Object> productos, Integer cantidad_bol, Cliente cliente, TipoEnvio tipoenvio) {
        this.id_boleta = id_boleta;
        this.fecha_boleta = fecha_boleta;
        this.total_boleta = total_boleta;
        this.metpago_boleta = metpago_boleta;
        this.dedicatoria = dedicatoria;
        // Se utiliza una lista común de tipo Object para aceptar tanto TortaClasica como TortaEspecial
        this.tortasClasicas = new ArrayList<>();
        this.tortaEspeciales = new ArrayList<>();
        this.velas = new ArrayList<>();
        
        // Aquí separamos las tortas en su lista correspondiente
        for (Object producto : productos) {
            if (producto instanceof Vela) {
                velas.add((Vela) producto);
            }else if (producto instanceof TortaClasica) {
                tortasClasicas.add((TortaClasica) producto);
            } else if (producto instanceof TortaEspecial) {
                tortaEspeciales.add((TortaEspecial) producto);
            }
        }
    
        this.cantidad_bol = cantidad_bol;
        this.cliente = cliente;
        this.tipoenvio = tipoenvio;
    }

    
    public Boleta() {
        // Constructor por defecto para JPA
    }

    public Integer getCantidad_bol() {
        return cantidad_bol;
    }

    public void setCantidad_bol(Integer cantidad_bol) {
        this.cantidad_bol = cantidad_bol;
    }

   

    
    
}
