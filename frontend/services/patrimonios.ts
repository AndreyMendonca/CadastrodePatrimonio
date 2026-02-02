import { Setor, SetorDTO } from "@/types/setores";
import { api } from "./axios";
import { AxiosResponse } from "axios";
import { Patrimonio, PatrimonioDTO,  } from "@/types/patrimonios";

const URL: string = '/patrimonios';
export const PatrimoniosService = {
    salvar: async (id: number = 0, patrimonio: PatrimonioDTO): Promise<void> => {
        let response: AxiosResponse<void>;
        if (id === 0) {
            response = await api.post(URL, patrimonio);
        } else {
            response = await api.put(`${URL}/${id}`, patrimonio);
        }
        return response.data;
    },
    buscarTodos: async (): Promise<Patrimonio[]> => {
        const response: AxiosResponse<Patrimonio[]> = await api.get(URL);
        return response.data;
    },
    deletar: async (id: number): Promise<void> => {
        await api.delete(`${URL}/${id}`);
    }
}
