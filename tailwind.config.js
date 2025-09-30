/** @type {import('tailwindcss').Config} */
module.exports = {
    important: true,
    content: [
        "./src/main/resources/templates/**/*.html", // todas tus vistas Thymeleaf
        "./src/main/resources/static/**/*.js"       // si usas JS en static
    ],
    safelist: [
        'font-poppins',
        'font-dynapuff',
    ],
    theme: {
        extend: {
            colors: {
                primary: '#2563eb',
                secondary: '#64748b',
                accent: '#f59e0b'
            },
            fontFamily: {
                poppins: ['Poppins', 'sans-serif'],
                dynapuff: ['DynaPuff', 'sans-serif'],
            }
        },
    },
    plugins: [],
}