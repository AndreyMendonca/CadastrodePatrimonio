"use client";
import { Layout } from "@/components/layout";
import { DialogPatrimonio } from "@/components/patrimonios/dialog-patrimonio";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { PatrimoniosService } from "@/services/patrimonios";
import { Patrimonio, PatrimonioDTO } from "@/types/patrimonios";
import { Setor } from "@/types/setores";
import { Pencil, Trash2 } from "lucide-react";
import Link from "next/link";
import { useEffect, useState } from "react";
import { toast } from "sonner";

export default function PatrimoniosPage() {
    const [openDialog, setOpenDialog] = useState(false);
    const service = PatrimoniosService;
    const setorService = PatrimoniosService;
    const [patrimonios, setPatrimonios] = useState<Patrimonio[]>([]);
    const [patrimonioSelecionado, setPatrimonioSelecionado] = useState<Patrimonio | null>(null);

    const abrirDialog = (patrimonio: Patrimonio | null) => {
        setPatrimonioSelecionado(patrimonio);
        setOpenDialog(true);
    }

    const handleSalvar = async (id: number, patrimonio: PatrimonioDTO) => {
        await service.salvar(id, patrimonio)
            .then(() => {
                toast.success("Sucesso", { description: "Patrimonio salvo com sucesso!" })
            })
            .catch(() => toast.error("Erro", { description: "Erro ao salvar patrimonio!" }))
            .finally(() => {
                setOpenDialog(false);
                buscarTodos();
            });
    }

    const handleDeletar = async (id: number) => {
        await service.deletar(id)
            .then(() => {
                toast.success("Sucesso", { description: "Patrimonio deletado com sucesso!" })
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data && error.response.data.detail) {
                    errorMessage = error.response.data.detail;
                }
                toast.error(error.response.data.title, {
                    description: errorMessage
                });
            })
            .finally(() => {
                buscarTodos();
            });
    }

    const buscarTodos = async () => {
        await service.buscarTodos()
            .then(setPatrimonios)
            .catch(() => toast.error("API está com problemas para bsucar os patrimônios"));
    }


    useEffect(() => {
        buscarTodos()
    }, [])

    return (
        <Layout>
            <div className="flex justify-between items-center mb-2">
                <p className="text-4xl font-bold">Patrimônio</p>
                <Button onClick={() => abrirDialog(null)} asChild>
                    <p>Adicionar Patrimônio</p>
                </Button>
            </div>
            <Separator />
            <Table>
                <TableCaption>Lista de patrimonios</TableCaption>
                <TableHeader>
                    <TableRow>
                        <TableHead className="w-25">ID</TableHead>
                        <TableHead>Nome</TableHead>
                        <TableHead>Nº Patrimônio</TableHead>
                        <TableHead>Setor</TableHead>
                        <TableHead className="text-right">Ações</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {
                        patrimonios.map((patrimonio) => (
                            <TableRow key={patrimonio.id}>
                                <TableCell className="font-medium">{patrimonio.id}</TableCell>
                                <TableCell className="capitalize">{patrimonio.nome}</TableCell>
                                <TableCell className="capitalize">{patrimonio.numeroPatrimonio}</TableCell>
                                <TableCell className="capitalize">{patrimonio.setor?.nome}</TableCell>
                                <TableCell className="text-right flex gap-2 justify-end">
                                    <Button onClick={() => abrirDialog(patrimonio)} size="icon" className="rounded-full">
                                        <Pencil />
                                    </Button>
                                    <Button onClick={() => handleDeletar(patrimonio.id)} size="icon" className="rounded-full">
                                        <Trash2 />
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                </TableBody>
            </Table>
            <DialogPatrimonio open={openDialog} onOpenChange={setOpenDialog} handleSalvar={handleSalvar} patrimonio={patrimonioSelecionado}  />
        </Layout>
    );
}
