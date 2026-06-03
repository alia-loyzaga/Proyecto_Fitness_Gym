package dominio;

import java.time.LocalDate;

import enumerados.EstadoPago;
import enumerados.TipoPago;

/**
 * Clase que representa un pago dentro del sistema Fitness Gym.
 * 
 * Gestiona la información relacionada con los pagos realizados
 * por los socios, incluyendo importe, estado y control
 * de confirmación de operaciones económicas.
 * 
 * Forma parte de la lógica de gestión financiera del gimnasio.
 * 
 * @author Alia
 * @version 1.0
 */
public class Pago {

	private Reserva reserva;
	private Socio socio;
	private LocalDate fecha;
	private double cantidad;
	private EstadoPago estado;
	private TipoPago tipoPago;
	
	
	public Pago(Reserva reserva, Socio socio, LocalDate fecha, double cantidad, EstadoPago estado, TipoPago tipoPago) {
		
		this.reserva = reserva;
		this.socio = socio;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.estado = estado;
		this.tipoPago = tipoPago;
	}

	
	public Pago() {
	
	}

	//Métodos de acceso a atributos

	public Reserva getReserva() {
		return reserva;
	}


	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}


	public Socio getSocio() {
		return socio;
	}


	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public EstadoPago getEstado() {
		return estado;
	}


	public void setEstado(EstadoPago estado) {
		this.estado = estado;
	}


	public TipoPago getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}


	@Override
	public String toString() {
		return "Pago [reserva=" + reserva + ", socio=" + socio + ", fecha=" + fecha + ", cantidad=" + cantidad
				+ ", estado=" + estado + ", tipoPago=" + tipoPago + "]";
	}
	
	
	//Métodos de dominio
	/**
	 * Confirma un pago pendiente cambiando su estado a confirmado.
	 * 
	 * Solo podrá confirmarse si el pago se encuentra
	 * actualmente en estado pendiente.
	 * 
	 * @return true si el pago se confirma correctamente,
	 *         false si ya estaba confirmado o no es válido
	 */
	public boolean confirmarPago() {

	    boolean confirmado;

	    if (estado == EstadoPago.PENDIENTE) {
	        estado = EstadoPago.CONFIRMADO;
	        confirmado = true;
	    } else {
	        confirmado = false;
	    }

	    return confirmado;
	}
	
	
	
	
	
	
	
}
