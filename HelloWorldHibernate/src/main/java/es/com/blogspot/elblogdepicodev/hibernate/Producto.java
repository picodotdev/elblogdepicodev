package es.com.blogspot.elblogdepicodev.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Indexed
@AnalyzerDef(name = "textoanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
	@TokenFilterDef(factory = LowerCaseFilterFactory.class),
	@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = { @Parameter(name = "language", value = "English") }) })
public class Producto implements Serializable {

	private static final long serialVersionUID = 4301591927955774037L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "nombre")
	@Field(name = "nombre", index = Index.YES, analyze = Analyze.YES)
	@Analyzer(definition = "textoanalyzer")
	private String nombre;
	@Column(name = "descripcion", length = 5000)
	@Field(name = "descripcion", index = Index.YES, analyze = Analyze.YES)
	@Analyzer(definition = "textoanalyzer")
	private String descripcion;
	@Column(name = "cantidad")
	private Long cantidad;
	@Column(name = "fecha")
	@Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	private Date fecha;

	public Producto() {
	}

	public Producto(String nombre, String descripcion, Long cantidad, Date fecha) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
