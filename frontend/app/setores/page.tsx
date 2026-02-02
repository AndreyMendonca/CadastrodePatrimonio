"use client"
import { Layout } from "@/components/layout";
import { DialogSetores } from "@/components/setores/dialog-setores";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { SetoresService } from "@/services/setores";
import { Setor, SetorDTO } from "@/types/setores";
import { Pencil, Trash2 } from "lucide-react";
import { useEffect, useState } from "react";
import { toast } from "sonner";
import { set } from "zod";


export default function SetoresPage() {
    const [openDialog, setOpenDialog] = useState(false);
    const service = SetoresService;
    const [setores, setSetores] = useState<Setor[]>([]);
    const [setorSelecionado, setSetorSelecionado] = useState<Setor | null>(null);

    const abrirDialog = (setor: Setor | null) => {
        setSetorSelecionado(setor);
        setOpenDialog(true);
    }

    const handleSalvar = async (id: number, setor: SetorDTO) => {
        await service.salvar(id, setor)
            .then(() => {
                toast.success("Sucesso", { description: "Setor salvo com sucesso!" })
            })
            .catch(() => toast.error("Erro", { description: "Erro ao salvar setor!" }))
            .finally(() => {
                setOpenDialog(false);
                buscarTodos();
            });
    }

    const handleDeletar = async (id: number) => {
        await service.deletar(id)
            .then(() => {
                toast.success("Sucesso", { description: "Setor deletado com sucesso!" })
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
            .then(setSetores)
            .catch(() => toast.error("API está com problemas"));
    }

    useEffect(() => {
        buscarTodos()
    }, [])

    return (
        <Layout>
            <div className="flex justify-between items-center mb-2">
                <p className="text-4xl font-bold">Setor</p>
                <Button onClick={() => abrirDialog(null)} asChild>
                    <p>Adicionar Setor</p>
                </Button>
            </div>
            <Separator />
            <Table>
                <TableCaption>Lista de setores</TableCaption>
                <TableHeader>
                    <TableRow>
                        <TableHead className="w-25">ID</TableHead>
                        <TableHead>Nome</TableHead>
                        <TableHead className="text-right">Ações</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {
                        setores.map((setor) => (
                            <TableRow key={setor.id}>
                                <TableCell className="font-medium">{setor.id}</TableCell>
                                <TableCell className="capitalize">{setor.nome}</TableCell>
                                <TableCell className="text-right flex gap-2 justify-end">
                                    <Button onClick={() => abrirDialog(setor)} size="icon" className="rounded-full">
                                        <Pencil />
                                    </Button>
                                    <Button onClick={() => handleDeletar(setor.id)} variant="destructive" size="icon" className="rounded-full">
                                        <Trash2 />
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                </TableBody>
            </Table>
            <DialogSetores open={openDialog} onOpenChange={setOpenDialog} handleSalvar={handleSalvar} setor={setorSelecionado} />
        </Layout>
    );
}
