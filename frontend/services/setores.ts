import { Setor, SetorDTO } from "@/types/setores";
import { api } from "./axios";
import { AxiosResponse } from "axios";

const URL: string = '/setores';
export const SetoresService = {
    salvar: async (id: number = 0, setor: SetorDTO): Promise<void> => {
        let response: AxiosResponse<void>;
        if (id === 0) {
            response = await api.post(URL, setor);
        } else {
            response = await api.put(`${URL}/${id}`, setor);
        }
        return response.data;
    },
    buscarTodos: async (): Promise<Setor[]> => {
        const response: AxiosResponse<Setor[]> = await api.get(URL);
        return response.data;
    },
    deletar: async (id: number): Promise<void> => {
        await api.delete(`${URL}/${id}`);
    }
}
