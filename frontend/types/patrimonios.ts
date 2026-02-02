import { Setor } from "./setores";

export type Patrimonio = {
    id: number;
    nome: string;
    numeroPatrimonio: string;
    setor: Setor;
}

export type PatrimonioDTO = {
    nome: string;
    numeroPatrimonio: string;
    setor: number;
}