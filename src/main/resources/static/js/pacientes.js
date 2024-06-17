document.addEventListener('DOMContentLoaded', function () {
    const formRegistro = document.getElementById('formRegistro');
    const formModificar = document.getElementById('formModificar');
    const tablaPacientes = document.getElementById('tablaPacientes');

    var seccionRegistro = document.getElementById('registro-paciente');
    var seccionListar = document.getElementById('listar-pacientes');
    var seccionBuscar = document.getElementById('buscar-pacientes');
    var seccionModificar = document.getElementById('modificar-paciente');
    var botonRegistro = document.querySelector('.botones-navegacion button:nth-child(1)');
    var botonListar = document.querySelector('.botones-navegacion button:nth-child(2)');
    var botonBuscar = document.querySelector('.botones-navegacion button:nth-child(3)');

    // Asignar la función listarPacientes al botón de listar
    if (botonListar) {
        botonListar.addEventListener('click', function () {
            // Ocultar todas las secciones excepto la de listar
            seccionRegistro.style.display = 'none';
            seccionBuscar.style.display = 'none';
            seccionListar.style.display = 'block'; // Mostrar la sección de listar
            listarPacientes(); // Llamar a la función para listar pacientes
        });
    }

    // Mostrar la sección de registro al hacer clic en el botón de registro
    if (botonRegistro) {
        botonRegistro.addEventListener('click', function () {
            seccionRegistro.style.display = 'block';
            seccionBuscar.style.display = 'none';
            seccionListar.style.display = 'none';
            seccionModificar.style.display = 'none'; // Ocultar la sección de modificar también
        });
    }

    // Mostrar la sección de buscar al hacer clic en el botón de buscar
    if (botonBuscar) {
        botonBuscar.addEventListener('click', function () {
            seccionRegistro.style.display = 'none';
            seccionBuscar.style.display = 'block';
            seccionListar.style.display = 'none';
            seccionModificar.style.display = 'none'; // Ocultar la sección de modificar también
        });
    }

    formRegistro.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario

        const formData = new FormData(formRegistro);

        const data = {
            dni: formData.get('dni'),
            nombre: formData.get('nombre'),
            apellido: formData.get('apellido'),
            fechaAlta: new Date().toISOString().split('T')[0],  // Fecha actual en formato YYYY-MM-DD
            domicilioEntradaDto: {
                calle: formData.get('calle'),
                numero: formData.get('numero'),
                localidad: formData.get('localidad'),
                provincia: formData.get('provincia')
            }
        };

        fetch('http://localhost:8080/pacientes/registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(data => {
                console.log('Registro exitoso:', data);
                alert("Registro exitoso, bienvenido " + data.nombre + " " + data.apellido + ".");
                formRegistro.reset();
                listarPacientes(); // Actualizar la lista de pacientes después de registrar
            })
            .catch(error => {
                console.error('Error:', error);
                if (error.response && error.response.message) {
                    alert("Error al realizar el registro: " + error.response.message);
                } else {
                    alert("Error al realizar el registro.");
                }
                formRegistro.reset();
            });
    });

    formModificar.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario
    
        const formData = new FormData(formModificar);
        const pacienteId = formModificar.getAttribute('data-modificar-id');
    
        const data = {
            dni: formData.get('dni'),
            nombre: formData.get('nombre'),
            apellido: formData.get('apellido'),
            fechaAlta: formData.get('fechaAlta'),  // Incluir la fecha de alta del formulario
            domicilioEntradaDto: {
                calle: formData.get('calle'),
                numero: formData.get('numero'),
                localidad: formData.get('localidad'),
                provincia: formData.get('provincia')
            }
        };
    
        fetch(`http://localhost:8080/pacientes/${pacienteId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(data => {
                console.log('Modificación exitosa:', data);
                alert(`Se modificó correctamente al paciente ${data.nombre} ${data.apellido}.`); 
                listarPacientes(); // Actualizar la lista de pacientes después de modificar
                seccionModificar.style.display = 'none';
                seccionListar.style.display = 'block';
            })
            .catch(error => {
                console.error('Error:', error);
                if (error.response && error.response.message) {
                    alert("Error al modificar paciente: " + error.response.message);
                } else {
                    alert("Error al modificar paciente.");
                }
                formModificar.reset();
            });
    });
    
    function listarPacientes() {
        fetch('http://localhost:8080/pacientes/listar')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(data => {
                // Limpiar tabla antes de agregar nuevos datos
                tablaPacientes.querySelector('tbody').innerHTML = '';

                // Construir filas de la tabla con los pacientes
                data.forEach(paciente => {
                    const fila = document.createElement('tr');
                    fila.innerHTML = `
                        <td>${paciente.id}</td>
                        <td>${paciente.dni}</td>
                        <td>${paciente.nombre}</td>
                        <td>${paciente.apellido}</td>
                        <td>${paciente.domicilioDtoSalida.calle}</td>
                        <td>${paciente.domicilioDtoSalida.numero}</td>
                        <td>${paciente.domicilioDtoSalida.localidad}</td>
                        <td>${paciente.domicilioDtoSalida.provincia}</td>
                        <td>${paciente.fechaAlta}</td>
                        <td>
                            <button class="boton-modificar" data-id="${paciente.id}">Modificar</button>
                            <button class="boton-eliminar" data-id="${paciente.id}">Eliminar</button>
                        </td>
                        `
                        ;
                    tablaPacientes.querySelector('tbody').appendChild(fila);
                });

                // Agregar eventos a los botones de Modificar y Eliminar
                tablaPacientes.querySelectorAll('.boton-modificar').forEach(boton => {
                    boton.addEventListener('click', mostrarFormularioModificar);
                });

                tablaPacientes.querySelectorAll('.boton-eliminar').forEach(boton => {
                    boton.addEventListener('click', eliminarPaciente);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al listar pacientes');
            });
    }

    function mostrarFormularioModificar(event) {
        const pacienteId = event.target.getAttribute('data-id');
    
        fetch(`http://localhost:8080/pacientes/${pacienteId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(paciente => {
                // Llenar el formulario con los datos del paciente
                document.getElementById('dniModificar').value = paciente.dni;
                document.getElementById('nombreModificar').value = paciente.nombre;
                document.getElementById('apellidoModificar').value = paciente.apellido;
                document.getElementById('calleModificar').value = paciente.domicilioDtoSalida.calle;
                document.getElementById('numeroModificar').value = paciente.domicilioDtoSalida.numero;
                document.getElementById('localidadModificar').value = paciente.domicilioDtoSalida.localidad;
                document.getElementById('provinciaModificar').value = paciente.domicilioDtoSalida.provincia;
    
                // Pre-cargar la fecha de alta
                const fechaAlta = new Date(paciente.fechaAlta); // Convertir la fecha de alta a objeto Date
                const fechaAltaISO = fechaAlta.toISOString().split('T')[0]; // Convertir a formato YYYY-MM-DD
                document.getElementById('fechaAltaModificar').value = fechaAltaISO;
    
                // Mostrar la sección de modificar
                seccionModificar.style.display = 'block';
                seccionRegistro.style.display = 'none';
                seccionListar.style.display = 'none';
                seccionBuscar.style.display = 'none';
    
                // Guardar el ID del paciente que se está modificando en el formulario
                formModificar.setAttribute('data-modificar-id', pacienteId);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al cargar los datos del paciente');
            });
    }
    

    function eliminarPaciente(event) {
        const pacienteId = event.target.getAttribute('data-id');
        const confirmacion = confirm(`¿Estás seguro que deseas eliminar al paciente con ID ${pacienteId}?`);

        if (confirmacion) {
            fetch(`http://localhost:8080/pacientes/${pacienteId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al eliminar paciente');
                    }
                    // No es necesario esperar una respuesta JSON para DELETE, así que simplemente continuar
                    alert('Se eliminó correctamente al paciente');
                    // Actualizar la lista de pacientes después de eliminar
                    listarPacientes();
                })
                .catch(error => {
                    console.error('Error al eliminar paciente:', error);
                    alert('Error al eliminar paciente'); // Informar al usuario sobre el error
                    // Actualizar la lista de pacientes incluso si hubo un error
                    listarPacientes();
                });
        }
    }

    // Asignar la función listarPacientes al botón de listar
    if (botonListar) {
        botonListar.addEventListener('click', listarPacientes);
    }

});

function cancelarModificacion() {
    var seccionModificar = document.getElementById('modificar-paciente');
    var seccionListar = document.getElementById('listar-pacientes');
    seccionModificar.style.display = 'none';
    seccionListar.style.display = 'block';
    
}

function buscarPaciente() {
    const pacienteId = document.getElementById('buscar-id').value;

    fetch(`http://localhost:8080/pacientes/${pacienteId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(paciente => {
            // Construir el HTML para mostrar los datos del paciente en la tarjeta
            const datosPacienteHTML = `
                <p><strong>Nombre:</strong> ${paciente.nombre}</p>
                <p><strong>Apellido:</strong> ${paciente.apellido}</p>
                <p><strong>DNI:</strong> ${paciente.dni}</p>
                <p><strong>Domicilio:</strong> ${paciente.domicilioDtoSalida.calle} ${paciente.domicilioDtoSalida.numero}, ${paciente.domicilioDtoSalida.localidad}, ${paciente.domicilioDtoSalida.provincia}</p>
                <p><strong>Fecha de Alta:</strong> ${paciente.fechaAlta}</p>
            `;

            // Mostrar la tarjeta con los datos del paciente
            const datosPacienteDiv = document.getElementById('datos-paciente');
            datosPacienteDiv.innerHTML = datosPacienteHTML;

            // Mostrar el contenedor de la tarjeta
            document.getElementById('resultado-busqueda').style.display = 'block';

            // Limpiar el formulario después de la búsqueda
            document.getElementById('formBuscarPaciente').reset();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('No se encontró ningún paciente con ese ID');
        });
}
