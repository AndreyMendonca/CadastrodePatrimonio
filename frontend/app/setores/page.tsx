"use client"
import { Layout } from "@/components/layout";
import { DialogSetores } from "@/components/setores/dialog-setores";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Pencil, Trash2 } from "lucide-react";
import { useState } from "react";


export default function SetoresPage() {
    const [openDialog, setOpenDialog] = useState(false);

    const abrirDialog = () => {
        setOpenDialog(true);
    }

    return (
        <Layout>
            <div className="flex justify-between items-center mb-2">
                <p className="text-4xl font-bold">Setor</p>
                <Button onClick={abrirDialog} asChild>
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
                    <TableRow>
                        <TableCell className="font-medium">10</TableCell>
                        <TableCell>Paid</TableCell>
                        <TableCell className="text-right flex gap-2 justify-end">
                            <Button size="icon" className="rounded-full">
                                <Pencil />
                            </Button>
                            <Button variant="destructive" size="icon" className="rounded-full">
                                <Trash2 />
                            </Button>
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="font-medium">10</TableCell>
                        <TableCell>Paid</TableCell>
                        <TableCell className="text-right flex gap-2 justify-end">
                            <Button size="icon" className="rounded-full">
                                <Pencil />
                            </Button>
                            <Button variant="destructive" size="icon" className="rounded-full">
                                <Trash2 />
                            </Button>
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
            <DialogSetores open={openDialog} onOpenChange={setOpenDialog} />
        </Layout>
    );
}
