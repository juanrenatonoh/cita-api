function consultarCitas() {
    var fecha = document.getElementById("fecha").value;
    var consultorio = document.getElementById("consultorio").value;

    // Realizar solicitud GET para obtener citas según la fecha y el consultorio
    axios.get('http://localhost:8080/cita', {
        params: {
            fecha: fecha,
            numeroConsultorio: consultorio
        }
    })
    .then(function (response) {
        // Mostrar las citas obtenidas en el resultado de la consulta
        var citas = response.data;
        var resultadoHTML = "<h2>Resultados de la Consulta</h2>";
        if (citas.length > 0) {
            resultadoHTML += "<ul>";
            citas.forEach(function(cita) {
                resultadoHTML += "<li>" + cita.nombrePaciente + " - " + cita.horarioConsulta + "</li>";
            });
            resultadoHTML += "</ul>";
        } else {
            resultadoHTML += "<p>No se encontraron citas para la fecha y consultorio especificados.</p>";
        }
        document.getElementById("resultadoConsulta").innerHTML = resultadoHTML;
    })
    .catch(function (error) {
        alert("Error al consultar citas.");
    });
}
