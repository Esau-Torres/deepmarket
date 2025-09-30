// cargar datos de usuario

document.addEventListener("alpine:init", () => {
    Alpine.store("adminuser", {
        modificarModal: false,
        userId: null,

        async openModificarModal(id) {
            console.log("Abriendo modal para ID:", id); // debug
            this.userId = id;
            this.modificarModal = true;
            await this.cargarUsuarioModal(id);
        },

        async cargarUsuarioModal(id) {
            try {
                const response = await fetch(`/Usuarios/buscar/${id}`);
                const user = await response.json();

                document.querySelector('#modificarModal input[name="id"]').value = user.id;
                document.querySelector('#modificarModal input[name="nombre"]').value = user.nombre;
                document.querySelector('#modificarModal input[name="apellido"]').value = user.apellido;
                document.querySelector('#modificarModal select[name="sexo"]').value = user.sexo;
                document.querySelector('#modificarModal input[name="fechaNacimiento"]').value = user.fechaNacimiento;
                document.querySelector('#modificarModal input[name="correo"]').value = user.correo;
                document.querySelector('#modificarModal input[name="usuario"]').value = user.usuario;
                document.querySelector('#modificarModal select[name="idRol.id"]').value = user.idRol.id;
                document.querySelector('#modificarModal select[name="idEstado.id"]').value = user.idEstado.id;
            } catch (error) {
                console.error("Error cargando usuario:", error);
            }
        }
    });
});

// cargar el id del usuario para reasignar contraseña
function cargarIdPassword(idUsuario) {
    document.getElementById("passwordUserId").value = idUsuario;
}

function ObtenerId(id) {
    document.getElementById("UserId").value = id;
}