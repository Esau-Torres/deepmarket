// Función para hacer scroll al final del chat con animación suave
function scrollToBottom() {
    const chatContainer = document.getElementById('chatContainer');
    if (chatContainer) {
        chatContainer.scrollTo({
            top: chatContainer.scrollHeight,
            behavior: 'smooth'
        });
    }
}

// Función para mostrar/ocultar panel de contactos
function toggleContactsPanel() {
    const panel = document.getElementById('contactsPanel');
    const overlay = document.getElementById('overlay');
    const menuButton = document.getElementById('menuButton');
    const backButton = document.getElementById('backButton');

    if (panel.classList.contains('-translate-x-full')) {
        // Mostrar panel
        panel.classList.remove('-translate-x-full');
        panel.classList.add('translate-x-0', 'flex');
        overlay.classList.remove('opacity-0', 'pointer-events-none');
        overlay.classList.add('opacity-100', 'pointer-events-auto');

        // Cambiar botones
        menuButton.classList.add('hidden');
        backButton.classList.remove('hidden');

        // Cambiar ícono a X
        backButton.innerHTML = '<i class="fas fa-times"></i>';
    } else {
        // Ocultar panel
        panel.classList.add('-translate-x-full');
        panel.classList.remove('translate-x-0');
        overlay.classList.add('opacity-0', 'pointer-events-none');
        overlay.classList.remove('opacity-100', 'pointer-events-auto');

        // Cambiar botones
        menuButton.classList.remove('hidden');
        backButton.classList.add('hidden');

        // Restaurar ícono original
        backButton.innerHTML = '<i class="fas fa-arrow-left"></i>';
    }
}

// Cerrar panel al hacer click en overlay
function closeContactsPanel() {
    const panel = document.getElementById('contactsPanel');
    if (!panel.classList.contains('-translate-x-full')) {
        toggleContactsPanel();
    }
}

// Función para el botón de retroceso con confirmación
document.addEventListener('DOMContentLoaded', function() {
    const backButton = document.getElementById('backButton');
    const menuButton = document.getElementById('menuButton');
    const overlay = document.getElementById('overlay');

    // Event listener para el botón menú
    if (menuButton) {
        menuButton.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            toggleContactsPanel();
        });
    }

    // Event listener para el botón back (ahora funciona como cerrar)
    if (backButton) {
        backButton.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();

            // Agregar efecto visual al botón
            this.classList.add('animate-pulse');
            setTimeout(() => {
                this.classList.remove('animate-pulse');
            }, 300);

            // Si el panel está abierto, cerrarlo; si no, navegar atrás
            const panel = document.getElementById('contactsPanel');
            if (!panel.classList.contains('-translate-x-full')) {
                toggleContactsPanel();
            } else {
                // Mostrar confirmación para navegar atrás
                if (confirm('¿Deseas volver a la pantalla anterior?')) {
                    console.log('Navegando hacia atrás...');
                }
            }
        });
    }

    // Event listener para el overlay
    if (overlay) {
        overlay.addEventListener('click', closeContactsPanel);
    }

    // Cerrar panel al redimensionar ventana a desktop
    window.addEventListener('resize', function() {
        if (window.innerWidth >= 768) { // md breakpoint
            const panel = document.getElementById('contactsPanel');
            const overlay = document.getElementById('overlay');
            const menuButton = document.getElementById('menuButton');
            const backButton = document.getElementById('backButton');

            // Resetear estado para desktop
            panel.classList.remove('-translate-x-full', 'translate-x-0');
            overlay.classList.add('opacity-0', 'pointer-events-none');
            overlay.classList.remove('opacity-100', 'pointer-events-auto');

            if (menuButton) menuButton.classList.remove('hidden');
            if (backButton) {
                backButton.classList.add('hidden');
                backButton.innerHTML = '<i class="fas fa-arrow-left"></i>';
            }
        }
    });
});

// Scroll al cargar la página con delay para mejor UX
window.onload = function() {
    setTimeout(scrollToBottom, 100);

    // Inicializar animaciones de entrada
    initializeAnimations();
};

// Función para inicializar animaciones
function initializeAnimations() {
    const messages = document.querySelectorAll('.animate-fade-in');
    messages.forEach((message, index) => {
        setTimeout(() => {
            message.style.opacity = '1';
            message.style.transform = 'translateY(0)';
        }, index * 150);
    });
}

// Manejo mejorado del input de mensajes
document.addEventListener('DOMContentLoaded', function() {
    const messageInput = document.querySelector('input[type="text"]');
    const sendButton = document.querySelector('button[class*="bg-gradient-to-r"]');

    if (messageInput) {
        // Agregar indicadores visuales mientras se escribe
        messageInput.addEventListener('input', function() {
            const hasText = this.value.trim().length > 0;

            if (sendButton) {
                if (hasText) {
                    sendButton.classList.remove('opacity-70');
                    sendButton.classList.add('shadow-xl', 'scale-105');
                } else {
                    sendButton.classList.add('opacity-70');
                    sendButton.classList.remove('shadow-xl', 'scale-105');
                }
            }
        });

        // Mejorar el envío con Enter
        messageInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                const message = this.value.trim();

                if (message) {
                    // Efectos visuales antes del envío
                    this.style.transform = 'scale(0.98)';
                    setTimeout(() => {
                        this.style.transform = 'scale(1)';
                    }, 100);

                    // Limpiar input con animación
                    this.style.opacity = '0.5';
                    setTimeout(() => {
                        this.value = '';
                        this.style.opacity = '1';
                        this.focus();
                    }, 150);

                    // Simular envío de mensaje
                    simulateMessageSend(message);
                    scrollToBottom();
                }
            }
        });

        // Efectos de focus mejorados
        messageInput.addEventListener('focus', function() {
            this.parentElement.classList.add('ring-2', 'ring-blue-400');
        });

        messageInput.addEventListener('blur', function() {
            this.parentElement.classList.remove('ring-2', 'ring-blue-400');
        });
    }

    // Mejorar click del botón enviar
    if (sendButton) {
        sendButton.addEventListener('click', function() {
            const message = messageInput.value.trim();
            if (message) {
                // Efecto visual del botón
                this.classList.add('animate-ping');
                setTimeout(() => {
                    this.classList.remove('animate-ping');
                }, 300);

                messageInput.value = '';
                simulateMessageSend(message);
                scrollToBottom();
                messageInput.focus();
            }
        });
    }
});

// Función para simular el envío de mensajes
function simulateMessageSend(message) {
    console.log('Mensaje enviado:', message);

    // Aquí se podría agregar la lógica para enviar al servidor
    // Por ejemplo: enviarMensajeAlServidor(message);

    // Mostrar feedback visual temporal
    showMessageFeedback();
}

// Función para mostrar feedback visual
function showMessageFeedback() {
    const chatContainer = document.getElementById('chatContainer');
    const feedback = document.createElement('div');
    feedback.className = 'text-center text-gray-400 text-sm py-2 opacity-0 transition-opacity duration-300';
    feedback.textContent = 'Enviando...';

    chatContainer.appendChild(feedback);

    // Mostrar feedback
    setTimeout(() => {
        feedback.style.opacity = '1';
    }, 50);

    // Ocultar feedback después de 1 segundo
    setTimeout(() => {
        feedback.style.opacity = '0';
        setTimeout(() => {
            if (feedback.parentNode) {
                feedback.parentNode.removeChild(feedback);
            }
        }, 300);
    }, 1000);
}

// Función para mejorar la experiencia de los botones de archivos
document.addEventListener('DOMContentLoaded', function() {
    const fileButtons = document.querySelectorAll('button[class*="fa-image"], button[class*="fa-video"], button[class*="fa-paperclip"]');

    fileButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Efecto de rotación en el ícono
            const icon = this.querySelector('i');
            if (icon) {
                icon.style.transform = 'rotate(360deg)';
                icon.style.transition = 'transform 0.5s ease';

                setTimeout(() => {
                    icon.style.transform = 'rotate(0deg)';
                }, 500);
            }

            // Aquí se podría abrir un selector de archivos
            console.log('Botón de archivo clickeado:', this.className);
        });
    });
});

// Función para mejorar los efectos hover en contactos (solo en desktop)
document.addEventListener('DOMContentLoaded', function() {
    const contactItems = document.querySelectorAll('.hover\\:bg-gray-700\\/50, .hover\\:bg-blue-600\\/30');

    contactItems.forEach(contact => {
        contact.addEventListener('mouseenter', function() {
            this.style.transform = 'translateX(4px)';
            this.style.transition = 'transform 0.2s ease';
        });

        contact.addEventListener('mouseleave', function() {
            this.style.transform = 'translateX(0)';
        });
    });
});

// Función para manejar el redimensionamiento de ventana
window.addEventListener('resize', function() {
    // Recalcular scroll cuando cambie el tamaño
    setTimeout(scrollToBottom, 100);
});

// Función para agregar efectos de escritura (typing indicator)
function showTypingIndicator() {
    const chatContainer = document.getElementById('chatContainer');
    const typingDiv = document.createElement('div');
    typingDiv.id = 'typing-indicator';
    typingDiv.className = 'flex mb-4 opacity-0 transition-opacity duration-300';
    typingDiv.innerHTML = `
        <img src="https://i.pravatar.cc/150?img=5" alt="Avatar" class="w-8 h-8 rounded-full object-cover mt-2">
        <div class="ml-2 bg-white rounded-2xl px-4 py-2 shadow-sm">
            <div class="flex space-x-1">
                <div class="w-2 h-2 bg-gray-400 rounded-full animate-bounce"></div>
                <div class="w-2 h-2 bg-gray-400 rounded-full animate-bounce" style="animation-delay: 0.1s"></div>
                <div class="w-2 h-2 bg-gray-400 rounded-full animate-bounce" style="animation-delay: 0.2s"></div>
            </div>
        </div>
    `;

    chatContainer.appendChild(typingDiv);
    setTimeout(() => {
        typingDiv.style.opacity = '1';
    }, 50);

    scrollToBottom();
}

// Función para ocultar typing indicator
function hideTypingIndicator() {
    const typingIndicator = document.getElementById('typing-indicator');
    if (typingIndicator) {
        typingIndicator.style.opacity = '0';
        setTimeout(() => {
            if (typingIndicator.parentNode) {
                typingIndicator.parentNode.removeChild(typingIndicator);
            }
        }, 300);
    }
}