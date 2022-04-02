import axios from "axios";
import authHeader from "./auth.headers";

const API_URL = "http://localhost:8080/api/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "auth/authenticate", {
        username,
        password,
      })
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem("token", response.data.token);
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
  }

  register(username, email, password) {
    return axios.post(API_URL + "users/save", {
      id: -1,
      username,
      email,
      password,
      roleId: -1,
    });
  }

  getCurrentUser() {
    return axios
      .post(API_URL + "user", { headers: authHeader() })
      .then((response) => {
        if (response.data.user) {
          localStorage.setItem("user", JSON.stringify(response.data.user));
        }
        return response.data;
      });
  }
}
export default new AuthService();
