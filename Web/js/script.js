let actividades = [];
let paginaActual = 1;
const tarjetasPorPagina = 8;

async function cargarDatos() {
    try {
        const response = await fetch("json/salas_actividades.json");
        const data = await response.json();

        data.forEach(sala => {
            sala.actividades.forEach(act => {
                actividades.push({
                    sala: sala.sala,
                    aforo: sala.aforo,
                    actividad: act.actividad,
                    entrenador: act.entrenador,
                    fecha: act.fechaInicio.split("T")[0],
                    hora: act.fechaInicio.split("T")[1],
                    plazasLibres: act.plazasLibres
                });
            });
        });

        mostrarTarjetas();
        crearFiltros();

    } catch (error) {
        console.error("Error cargando JSON:", error);
    }
}

function mostrarTarjetas(lista = actividades) {
    const contenedor = document.querySelector(".tarjetas");
    contenedor.innerHTML = "";

    const inicio = (paginaActual - 1) * tarjetasPorPagina;
    const fin = inicio + tarjetasPorPagina;

    const pagina = lista.slice(inicio, fin);

    pagina.forEach(item => {
        contenedor.innerHTML += `
            <div class="tarjeta">
                <h3>${item.actividad}</h3>
                <p><strong>Sala:</strong> ${item.sala}</p>
                <p><strong>Entrenador:</strong> ${item.entrenador}</p>
                <p><strong>Fecha:</strong> ${item.fecha}</p>
                <p><strong>Hora:</strong> ${item.hora}</p>
                <p><strong>Capacidad:</strong> ${item.aforo}</p>
                <p><strong>Plazas libres:</strong> ${item.plazasLibres}</p>
            </div>
        `;
    });

    document.getElementById("paginaActual").textContent = paginaActual;
}

function paginaSiguiente() {
    if (paginaActual * tarjetasPorPagina < actividades.length) {
        paginaActual++;
        mostrarTarjetas();
    }
}

function paginaAnterior() {
    if (paginaActual > 1) {
        paginaActual--;
        mostrarTarjetas();
    }
}

function crearFiltros() {
    const filtroSala = document.getElementById("filtroSala");

    const salasUnicas = [...new Set(actividades.map(a => a.sala))];

    salasUnicas.forEach(sala => {
        filtroSala.innerHTML += `<option value="${sala}">${sala}</option>`;
    });
}

function aplicarFiltros() {
    const salaSeleccionada = document.getElementById("filtroSala").value;
    const fechaSeleccionada = document.getElementById("filtroFecha").value;

    paginaActual = 1;

    const filtradas = actividades.filter(item =>
        (salaSeleccionada !== "" && item.sala === salaSeleccionada) ||
        (fechaSeleccionada !== "" && item.fecha === fechaSeleccionada) ||
        (salaSeleccionada === "" && fechaSeleccionada === "")
    );

    mostrarTarjetas(filtradas);
}

window.onload = cargarDatos;