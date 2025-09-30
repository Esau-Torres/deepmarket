(function() {
    // Evita ejecutar múltiples veces el mismo listener
    if (window.DeepMarketEventsInitialized) {
        console.warn('Eventos de DeepMarket ya inicializados. Saliendo.');
        return;
    }
    window.DeepMarketEventsInitialized = true;

    // =============================================
    // Mobile Menu Toggle (con búsqueda)
    // =============================================
    document.getElementById('mobile-menu-button')?.addEventListener('click', function() {
        const menu = document.getElementById('mobile-menu');
        const search = document.getElementById('mobile-search');

        if (!menu || !search) return;

        if (menu.classList.contains('hidden')) {
            menu.classList.remove('hidden');
            search.classList.add('hidden');
        } else {
            menu.classList.add('hidden');
        }
    });

    // =============================================
    // para abrir los Toast + modales con alpine
    // =============================================
    document.addEventListener('alpine:init', () => {
        Alpine.data('app', () => ({
            // Estados de todos los modales
            openModal: false,
            PasswordChangeModal: false,
            agergarUsuarioModal: false,
            passwordModal: false,
            deleteModal: false,
            LogoutModal: false,

            // Estado del toast
            toast: {
                show: false,
                type: 'success',
                message: ''
            },

            // Métodos para modales
            openPasswordModal() {
                this.openModal = false;
                this.$nextTick(() => this.PasswordChangeModal = true);
            },

            // Métodos para toast
            initToast() {
                const success = window.successMessage;
                const error   = window.errorMessage;

                if (success && success !== 'null') {
                    this.showToast('success', success);
                } else if (error && error !== 'null') {
                    this.showToast('error', error);
                }
            },
            showToast(type, message) {
                this.toast.type = type;
                this.toast.message = message;
                this.toast.show = true;

                setTimeout(() => this.toast.show = false, 4000);
            },

            // Métodos prueba
            togglePassword(e) {
                const container = e.target.closest('div');
                const input = container.querySelector('input[type="password"], input[type="text"]');

                if (input) {
                    if (input.type === 'password') {
                        input.type = 'text';
                        e.target.classList.replace('fa-eye', 'fa-eye-slash');
                    } else {
                        input.type = 'password';
                        e.target.classList.replace('fa-eye-slash', 'fa-eye');
                    }
                }
            },

            // cargar imagenes
            cargarImagen(e) {
                const file = e.target.files[0];
                const supportedImages = ["image/jpeg", "image/png", "image/gif", "image/jpg", "image/webp"];

                if (file && supportedImages.includes(file.type)) {
                    this.posterPreview = URL.createObjectURL(file);
                } else {
                    alert("El archivo que intenta cargar no es una imagen válida.");
                    e.target.value = ""; // reset input
                    this.posterPreview = null;
                }
            }
        }));
    });

})();