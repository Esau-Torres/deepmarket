// =============================================
// Cargar el ID del post (independiente de Alpine)
// =============================================
function ObtenerPostId(postId) {
    document.getElementById("PostId").value = postId;
}

// =============================================
// Extender el componente Alpine 'app' con lógica de posts
// =============================================
document.addEventListener("alpine:init", () => {
    Alpine.store("producto", {
        PostModal: false,
        postId: null,
        imagenes: [], // array de imagenes
        indice: 0, // imagen actual

        async openPostModal(id) {
            this.postId = id;
            this.PostModal = true;
            await this.cargarUsuarioPost(id);
        },

        async cargarUsuarioPost(id) {
            try {
                const response = await fetch(`/postea/data/${id}`);
                const post = await response.json();

                // Actualizar elementos
                this.imagenes = [post.imagen, post.imagen2, post.imagen3, post.imagen4].filter(Boolean);
                this.indice = 0;

                document.getElementById("modal-titulo").textContent = post.titulo;
                document.getElementById("modal-precio").textContent = `$${Number(post.precio).toLocaleString()}`;
                document.getElementById("modal-categoria").textContent = post.idCategoria?.nombre || "Sin categoría";
                document.getElementById("modal-ubicacion").textContent = post.idCiudad?.nombre || "Sin ubicación";
                document.getElementById("modal-pais").textContent = post.idPais?.nombre || "";
                document.getElementById("modal-estado").textContent = post.idEstadoPost?.nombre || "No definido";
                document.getElementById("modal-descripcion").textContent = post.descripcion || "Sin descripción";
                document.getElementById("modal-vendedor").textContent =
                    (post.idUsuario?.nombre || "") + " " + (post.idUsuario?.apellido || "") || "Anónimo";
                document.getElementById("modal-foto-perfil").src = `/images/perfiles/${post.idUsuario?.imagen || 'default.png'}`;
            } catch (error) {
                console.error("Error cargando post:", error);
            }
        },
        siguiente() {
            if (this.imagenes.length > 0) {
                this.indice = (this.indice + 1) % this.imagenes.length;
            }
        },

        anterior() {
            if (this.imagenes.length > 0) {
                this.indice = (this.indice - 1 + this.imagenes.length) % this.imagenes.length;
            }
        },

        irA(index) {
            this.indice = index;
        }
    });
});


// =============================================
// Preview de imagen para creación de posts (independiente)
// =============================================
(function() {
    if (window.PostImagePreviewInitialized) return;
    window.PostImagePreviewInitialized = true;

    $(document).on("change", "#poster-file", function () {
        var element = this.files[0];
        var supportedImages = ["image/jpeg", "image/png", "image/gif", "image/jpg", "image/webp"];
        if (element && supportedImages.includes(element.type)) {
            var imgCodified = URL.createObjectURL(element);
            var img = $('<div class="fr-poster-preview" id="poster"><img src="' + imgCodified + '" alt="Poster" height="500px"></div>');
            $("#poster").replaceWith(img);
        } else {
            alert("El archivo que intenta cargar no es una imagen.");
        }
    });
})();