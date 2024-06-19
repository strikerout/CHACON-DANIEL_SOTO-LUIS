const apiUrlTurnos = "http://localhost:8080/turnos/";

document.addEventListener("DOMContentLoaded", () => {
    var seccionRegistroTurno = document.getElementById('registro-turno');
    var seccionListarTurnos = document.getElementById('listar-turnos');
    var seccionBuscarTurno = document.getElementById('buscar-turno');

    var botonRegistroTurno = document.querySelector('.botones-navegacion button:nth-child(1)');
    var botonListarTurnos = document.querySelector('.botones-navegacion button:nth-child(2)');
    var botonBuscarTurno = document.querySelector('.botones-navegacion button:nth-child(3)');

    if (botonListarTurnos) {
        botonListarTurnos.addEventListener('click', function () {
            seccionRegistroTurno.style.display = 'none';
            seccionBuscarTurno.style.display = 'none';
            seccionListarTurnos.style.display = 'block';
            listarTurnos();
        });
    }

    if (botonRegistroTurno) {
        botonRegistroTurno.addEventListener('click', function () {
            seccionRegistroTurno.style.display = 'block';
            seccionBuscarTurno.style.display = 'none';
            seccionListarTurnos.style.display = 'none';
        });
    }

    if (botonBuscarTurno) {
        botonBuscarTurno.addEventListener('click', function () {
            seccionRegistroTurno.style.display = 'none';
            seccionBuscarTurno.style.display = 'block';
            seccionListarTurnos.style.display = 'none';
        });
    }

    document.getElementById('registro-turno-form').addEventListener('submit', function (e) {
        e.preventDefault();
        var form = e.target;
        var formData = new FormData(form);
        var fecha = formData.get('fecha');
        var hora = formData.get('hora');
        var fechaHora = `${fecha}T${hora}:00`;
        var data = {
            pacienteId: parseInt(formData.get('pacienteId')),
            odontologoId: parseInt(formData.get('odontologoId')),
            fechaYHora: fechaHora
        };

        console.log(data);
        fetch(apiUrlTurnos + 'registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(turno => {
                alert('Turno registrado con éxito');
                form.reset();
                listarTurnos();
            })
            .catch(error => {
                console.error('Error:', error);
                if (error.response && error.response.message) {
                    alert("Error al realizar el registro: " + error.response.message);
                } else {
                    alert("Error al realizar el registro.");
                }
                form.reset();
            }
            );
    });

    document.getElementById('buscar-turno-form').addEventListener('submit', function (e) {
        e.preventDefault();
        var form = e.target;
        var turnoId = form.querySelector('[name="id"]').value;
        fetch(apiUrlTurnos + turnoId)
            .then(response => response.json())
            .then(turno => {
                var resultadoBusqueda = document.getElementById('resultado-busqueda');
                resultadoBusqueda.style.display = 'block';
                var datosTurno = document.getElementById('datos-turno');
                datosTurno.innerHTML = `
                    <p><strong>ID:</strong> ${turno.id}</p>
                    <p><strong>Fecha y Hora:</strong> ${turno.fechaYHora}</p>
                    <p><strong>ID Odontólogo:</strong> ${turno.odontologoDtoSalida.id}</p>
                    <p><strong>Nombre Odontólogo:</strong> ${turno.odontologoDtoSalida.nombre} ${turno.odontologoDtoSalida.apellido}</p>
                    <p><strong>Matrícula Odontólogo:</strong> ${turno.odontologoDtoSalida.numMatricula}</p>
                    <p><strong>ID Paciente:</strong> ${turno.pacienteDtoSalida.id}</p>
                    <p><strong>Nombre Paciente:</strong> ${turno.pacienteDtoSalida.nombre} ${turno.pacienteDtoSalida.apellido}</p>
                    <p><strong>DNI Paciente:</strong> ${turno.pacienteDtoSalida.dni}</p>
                    <p><strong>Fecha Alta Paciente:</strong> ${turno.pacienteDtoSalida.fechaAlta}</p>
                    <p><strong>Domicilio Paciente:</strong></p>
                        <li><strong>Calle:</strong> ${turno.pacienteDtoSalida.domicilioDtoSalida.calle}</li>
                        <li><strong>Número:</strong> ${turno.pacienteDtoSalida.domicilioDtoSalida.numero}</li>
                        <li><strong>Localidad:</strong> ${turno.pacienteDtoSalida.domicilioDtoSalida.localidad}</li>
                        <li><strong>Provincia:</strong> ${turno.pacienteDtoSalida.domicilioDtoSalida.provincia}</li>   
                `
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Turno no encontrado');
            });
    });

    document.getElementById('formModificarTurno').addEventListener('submit', function (e) {
        e.preventDefault();
        var form = e.target;
        var turnoId = form.getAttribute('data-id');
        var formData = new FormData(form);
        var fecha = formData.get('fecha');
        var hora = formData.get('hora');
        var fechaHora = `${fecha}T${hora}:00`;
        var data = {
            pacienteId: parseInt(formData.get('pacienteId')),
            odontologoId: parseInt(formData.get('odontologoId')),
            fechaYHora: fechaHora
        };

        fetch(apiUrlTurnos + turnoId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => response.json())
            .then(turno => {
                alert('Turno modificado con éxito');
                cancelarModificacionTurno();
                listarTurnos(); // Actualizar la lista de turnos después de la modificación
            })
            .catch(error => console.error('Error:', error));
    });

});

function listarTurnos() {
    fetch(apiUrlTurnos + 'listar')
        .then(response => response.json())
        .then(turnos => {
            var tabla = document.getElementById('tablaTurnos').querySelector('tbody');
            tabla.innerHTML = '';
            turnos.forEach(turno => {
                var row = tabla.insertRow();
                row.insertCell(0).textContent = turno.id;
                row.insertCell(1).textContent = turno.fechaYHora;
                row.insertCell(2).textContent = turno.odontologoDtoSalida.id;
                row.insertCell(3).textContent = `${turno.odontologoDtoSalida.nombre} ${turno.odontologoDtoSalida.apellido}`;
                row.insertCell(4).textContent = turno.pacienteDtoSalida.id;
                row.insertCell(5).textContent = `${turno.pacienteDtoSalida.nombre} ${turno.pacienteDtoSalida.apellido}`;
                //row.style.textAlign = 'center';
                var accionesCell = row.insertCell(6);
                var btnEliminar = document.createElement('button');
                btnEliminar.textContent = 'Eliminar';
                btnEliminar.className = 'boton-eliminar';
                btnEliminar.onclick = () => eliminarTurno(turno.id);
                accionesCell.appendChild(btnEliminar);
                var btnModificar = document.createElement('button');
                btnModificar.textContent = 'Modificar';
                btnModificar.className = 'boton-modificar';
                btnModificar.onclick = () => mostrarSeccionModificarTurno(turno.id);
                accionesCell.appendChild(btnModificar);
            });
        })
        .catch(error => console.error('Error:', error));
}

function eliminarTurno(turnoId) {
    if (confirm('¿Estás seguro de que deseas eliminar este turno?')) {
        fetch(apiUrlTurnos + turnoId, { method: 'DELETE' })
            .then(() => {
                alert('Turno eliminado con éxito');
                listarTurnos();
            })
            .catch(error => console.error('Error:', error));
    }
}

function mostrarSeccionModificarTurno(turnoId) {
    fetch(apiUrlTurnos + turnoId)
        .then(response => response.json())
        .then(turno => {
            var seccionRegistroTurno = document.getElementById('registro-turno');
            var seccionListarTurnos = document.getElementById('listar-turnos');
            var seccionBuscarTurno = document.getElementById('buscar-turno');
            var seccionModificarTurno = document.getElementById('modificar-turno');

            // Asignar valores al formulario de modificación
            document.getElementById('modificar-paciente-id').value = turno.pacienteDtoSalida.id;
            document.getElementById('modificar-odontologo-id').value = turno.odontologoDtoSalida.id;

            // Separar fecha y hora del formato "YYYY-MM-DDTHH:MM:SS" recibido
            var fechaHora = turno.fechaYHora.split('T');
            var fecha = fechaHora[0];
            var hora = fechaHora[1].slice(0, 5); // Tomar solo HH:MM

            // Asignar valores a los campos de fecha y hora del formulario
            document.getElementById('modificar-fecha').value = fecha;
            document.getElementById('modificar-hora').value = hora;

            // Configurar el formulario para enviar el ID del turno
            var form = document.getElementById('formModificarTurno');
            form.setAttribute('data-id', turno.id);

            // Mostrar solo la sección de modificación
            seccionRegistroTurno.style.display = 'none';
            seccionBuscarTurno.style.display = 'none';
            seccionListarTurnos.style.display = 'none';
            seccionModificarTurno.style.display = 'block';
        })
        .catch(error => console.error('Error:', error));
}


function cancelarModificacionTurno() {
    var form = document.getElementById('formModificarTurno');
    form.reset();
    form.removeAttribute('data-id');
    var seccionRegistroTurno = document.getElementById('registro-turno');
    var seccionListarTurnos = document.getElementById('listar-turnos');
    var seccionBuscarTurno = document.getElementById('buscar-turno');
    var seccionModificarTurno = document.getElementById('modificar-turno');
    seccionRegistroTurno.style.display = 'none';
    seccionBuscarTurno.style.display = 'none';
    seccionListarTurnos.style.display = 'block';
    seccionModificarTurno.style.display = 'none';
}

function mostrarSeccion(seccionId, seccionClase) {
    var secciones = document.querySelectorAll(seccionClase);
    secciones.forEach(function (seccion) {
        seccion.style.display = 'none';
    });
    document.getElementById(seccionId).style.display = 'block';
}
