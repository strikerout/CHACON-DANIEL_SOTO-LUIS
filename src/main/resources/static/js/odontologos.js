document.addEventListener("DOMContentLoaded", () => {
    const apiUrl = "http://localhost:8080/odontologos/";
    var seccionRegistro = document.getElementById('registro-odontologo');
    var seccionListar = document.getElementById('listar-odontologos');
    var seccionBuscar = document.getElementById('buscar-odontologo');
    var seccionModificar = document.getElementById('modificar-odontologo'); // Se añade la sección de modificar

    var botonRegistro = document.querySelector('.botones-navegacion button:nth-child(1)');
    var botonListar = document.querySelector('.botones-navegacion button:nth-child(2)');
    var botonBuscar = document.querySelector('.botones-navegacion button:nth-child(3)');

    // Asignar la función listarOdontologos al botón de listar
    if (botonListar) {
        botonListar.addEventListener('click', function () {
            // Ocultar todas las secciones excepto la de listar
            seccionRegistro.style.display = 'none';
            seccionBuscar.style.display = 'none';
            seccionListar.style.display = 'block'; // Mostrar la sección de listar
            listarOdontologos(); // Llamar a la función para listar odontólogos
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

    // Función para mostrar el formulario de modificar odontólogo
    function mostrarFormularioModificar(event) {
        const odontologoId = event.target.getAttribute('data-id');

        fetch(`${apiUrl}${odontologoId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(odontologo => {
                // Llenar el formulario con los datos del odontólogo
                document.getElementById('matriculaModificar').value = odontologo.numMatricula;
                document.getElementById('nombreModificar').value = odontologo.nombre;
                document.getElementById('apellidoModificar').value = odontologo.apellido;

                // Mostrar la sección de modificar
                seccionModificar.style.display = 'block';
                seccionRegistro.style.display = 'none';
                seccionListar.style.display = 'none';
                seccionBuscar.style.display = 'none';

                // Guardar el ID del odontólogo que se está modificando en el formulario
                formModificar.setAttribute('data-modificar-id', odontologoId);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al cargar los datos del odontólogo');
            });
    }

    // Event listener para los botones de Modificar
    document.querySelectorAll('.boton-modificar').forEach(boton => {
        boton.addEventListener('click', mostrarFormularioModificar);
    });

    // Event listener para el formulario de Modificar
    document.getElementById("formModificar").addEventListener("submit", function (event) {
        event.preventDefault();
        const odontologoId = formModificar.getAttribute('data-modificar-id');
        const numMatricula = document.getElementById("matriculaModificar").value;
        const nombre = document.getElementById("nombreModificar").value;
        const apellido = document.getElementById("apellidoModificar").value;

        const odontologoActualizado = {
            numMatricula: numMatricula,
            nombre: nombre,
            apellido: apellido
        };

        fetch(`${apiUrl}${odontologoId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(odontologoActualizado)
        })
            .then(response => response.json())
            .then(data => {
                alert("Odontólogo actualizado exitosamente!");
                listarOdontologos();
                seccionModificar.style.display = 'none'; // Ocultar el formulario de modificar después de actualizar
                seccionListar.style.display = 'block';
            })
            .catch(error => {
                console.error("Error:", error);
            });
    });

    // Función para cancelar la modificación
    window.cancelarModificacion = function () {
        seccionModificar.style.display = 'none';
        seccionListar.style.display = 'block'; // Volver a mostrar la lista después de cancelar la modificación
    };

    document.getElementById("registro-form").addEventListener("submit", function (event) {
        event.preventDefault();
        registrarOdontologo();
    });

    document.getElementById("buscar-form").addEventListener("submit", function (event) {
        event.preventDefault();
        buscarOdontologo();
    });

    function registrarOdontologo() {
        const matricula = document.getElementById("matricula").value;
        const nombre = document.getElementById("nombre").value;
        const apellido = document.getElementById("apellido").value;

        const nuevoOdontologo = {
            numMatricula: parseInt(matricula),
            nombre: nombre,
            apellido: apellido
        };

        fetch(apiUrl + 'registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoOdontologo)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(data => {
                alert("Odontologo registrado exitosamente!");
                document.getElementById("registro-form").reset();
                listarOdontologos();
            })
            .catch(error => {
                console.error('Error:', error);
                if (error.response && error.response.message) {
                    alert("Error al realizar el registro: " + error.response.message);
                } else {
                    alert("Error al realizar el registro.");
                }
                document.getElementById("registro-form").reset();
            });
    }

    // Función para listar odontólogos
    function listarOdontologos() {
        fetch(apiUrl + 'listar')
            .then(response => response.json())
            .then(data => {
                const tabla = document.getElementById("tablaOdontologos").querySelector("tbody");
                tabla.innerHTML = "";
                data.forEach(odontologo => {
                    const fila = document.createElement("tr");
                    fila.innerHTML = `
                        <td>${odontologo.id}</td>
                        <td>${odontologo.numMatricula}</td>
                        <td>${odontologo.nombre}</td>
                        <td>${odontologo.apellido}</td>
                        <td>
                            <button class="boton-modificar" data-id="${odontologo.id}">Modificar</button>
                            <button class="boton-eliminar" data-id="${odontologo.id}">Eliminar</button>
                        </td>
                    `;
                    tabla.appendChild(fila);
                });

                // Reasignar eventos a los botones de Modificar y Eliminar después de listar
                document.querySelectorAll('.boton-modificar').forEach(boton => {
                    boton.addEventListener('click', mostrarFormularioModificar);
                });

                document.querySelectorAll('.boton-eliminar').forEach(boton => {
                    boton.addEventListener('click', eliminarOdontologo);
                });
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

    // Función para eliminar odontólogo
    function eliminarOdontologo(event) {
        const odontologoId = event.target.getAttribute('data-id');
        if (confirm("¿Está seguro de que desea eliminar este odontólogo?")) {
            fetch(`${apiUrl}${odontologoId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        alert("Odontólogo eliminado exitosamente!");
                        listarOdontologos();
                    } else {
                        alert("Error al eliminar el odontólogo");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }
    }

    function buscarOdontologo() {
        const id = document.getElementById("odontologo-id").value;

        fetch(apiUrl + id)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(odontologo => {
                const resultadoBusquedaHTML = `
                    <p><strong>ID:</strong> ${odontologo.id}</p>
                    <p><strong>Matrícula:</strong> ${odontologo.numMatricula}</p>
                    <p><strong>Nombre:</strong> ${odontologo.nombre}</p>
                    <p><strong>Apellido:</strong> ${odontologo.apellido}</p>
                `;
                const datosOdontologoDiv = document.getElementById('datos-odontologo');
                datosOdontologoDiv.innerHTML = resultadoBusquedaHTML;

                // Mostrar el contenedor de la tarjeta
                document.getElementById('resultado-busqueda').style.display = 'block';

                // Limpiar el formulario después de la búsqueda
                document.getElementById('buscar-form').reset();
            })
            .catch(error => {
                console.error("Error:", error);
                alert('No se encontró ningún odontólogo con ese ID');
            });
    }

    listarOdontologos(); // Llamar a la función para listar odontólogos al cargar la página
});
