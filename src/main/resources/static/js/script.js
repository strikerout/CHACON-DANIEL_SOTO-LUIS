document.addEventListener('DOMContentLoaded', function () {
    // Detectar la página actual y mostrar la primera sección por defecto
    if (document.querySelector('#registro-paciente')) {
        mostrarSeccion('registro-paciente', '.formulario-paciente');
    } else if (document.querySelector('#registro-odontologo')) {
        mostrarSeccion('registro-odontologo', '.formulario-odontologo');
    } else if (document.querySelector('#registro-turno')){
        mostrarSeccion('registro-turno')
    }
});

function mostrarSeccion(seccionId, claseSeccion) {
    // Ocultar todas las secciones específicas de la página
    var secciones = document.querySelectorAll(claseSeccion);
    secciones.forEach(function (seccion) {
        seccion.classList.remove('mostrar');
    });

    // Mostrar la sección seleccionada
    var seccion = document.getElementById(seccionId);
    if (seccion) {
        seccion.classList.add('mostrar');
    }
}
