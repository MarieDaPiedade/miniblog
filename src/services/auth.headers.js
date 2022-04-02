export default function authHeader() {
    const token = localStorage.getItem('token');

    if (token) {
        return { Authorization: 'Bearer ' + token }; // for Spring Boot back-end
    } else {
        return {};
    }
}