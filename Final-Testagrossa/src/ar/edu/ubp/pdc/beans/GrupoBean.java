package ar.edu.ubp.pdc.beans;


import ar.edu.ubp.pdc.annotations.Column;
import ar.edu.ubp.pdc.annotations.Entity;

@Entity
public class GrupoBean {

	@Column(name="nro_tipo_certificado")
	private Short nroTipoCertificado;
	@Column(name="desc_tipo_certificado")
	private String descTipoCertificado;
	
	
	public Short getNroTipoCertificado() {
		return nroTipoCertificado;
	}
	public void setNroTipoCertificado(Short nroTipoCertificado) {
		this.nroTipoCertificado = nroTipoCertificado;
	}
	public String getDescTipoCertificado() {
		return descTipoCertificado;
	}
	public void setDescTipoCertificado(String descTipoCertificado) {
		this.descTipoCertificado = descTipoCertificado;
	}
	@Override
	public String toString() {
		return "GrupoBean [nroTipoCertificado=" + nroTipoCertificado + ", descTipoCertificado=" + descTipoCertificado
				+ "]";
	}
}
