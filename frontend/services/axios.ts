import axios, { AxiosError, AxiosInstance } from "axios";

export const api: AxiosInstance = axios.create({
    baseURL: "http://localhost:8080"
});
