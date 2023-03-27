import axios from "axios";
import { getToken } from "../auth";

export const BASE_URL = 'http://localhost:9090/api/';

export const myAxios = axios.create({
    baseURL:BASE_URL,
})

export const privateAxios = axios.create({
    baseURL:BASE_URL,
})

privateAxios.interceptors.request.use(
    (config) => {
      const token = getToken();
      console.log("token is: " + token);
      if (token) {
        console.log("Hi from Inside");
        config.headers = {};
        config.headers["Accept"] = "application/json, text/plain, */*";
        config.headers["Authorization"] = `Bearer ${token}`;
        console.log("Authorization : " + config.headers.Authorization);
        console.log("Config " + JSON.stringify(config));
      }
      console.log("hi");
      return config;
    },
    (error) => Promise.reject(error)
  );
  


