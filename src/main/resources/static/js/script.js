function mostrarSeccion(seccionId) {
    // Ocultar todas las secciones
    var secciones = document.querySelectorAll('.formulario');
    secciones.forEach(function (seccion) {
        seccion.classList.remove('mostrar');
    });

    // Mostrar la sección seleccionada
    var seccion = document.getElementById(seccionId);
    if (seccion) {
        seccion.classList.add('mostrar');
    }
}

// Mostrar la primera sección por defecto
document.addEventListener('DOMContentLoaded', function () {
    mostrarSeccion('registro-paciente');
});
