"use client"
import { useForm } from "react-hook-form";
import { Button } from "../ui/button";
import { Dialog, DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "../ui/dialog";
import { Separator } from "../ui/separator";
import { set, z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "../ui/form";
import { Input } from "../ui/input";
import { useEffect, useState } from "react";
import { Patrimonio, PatrimonioDTO } from "@/types/patrimonios";
import { SelectPopover } from "../select-popover";
import { Setor } from "@/types/setores";
import { SetoresService } from "@/services/setores";
import { toast } from "sonner";


const formSchema = z.object({
    nome: z.string().min(2, { message: "O nome é obrigatório." }).max(256, { message: "Máximo de caracteres são 256." }),
    numeroPatrimonio: z.string().min(2, { message: "O número do patrimônio é obrigatório." }).max(256, { message: "Máximo de caracteres são 256." }),
    setor: z.number({ message: "Selecione uma opção" })
})


type Props = {
    open: boolean;
    onOpenChange: (open: boolean) => void;
    handleSalvar: (id: number, patrimonio: PatrimonioDTO) => Promise<void>;
    patrimonio: Patrimonio | null;
}

export const DialogPatrimonio = ({ open, onOpenChange, handleSalvar, patrimonio }: Props) => {
    const setorService = SetoresService;
    const [setores, setSetores] = useState<Setor[]>([]);

    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            nome: "",
            numeroPatrimonio: "",
        }
    });

    const onSubmit = async (values: z.infer<typeof formSchema>) => {
        if (patrimonio) {
            await handleSalvar(patrimonio.id, values);
        } else {
            await handleSalvar(0, values);
        }
        form.reset();
    }

    useEffect(() => {
        const values = {
            nome: patrimonio ? patrimonio.nome : "",
            numeroPatrimonio: patrimonio ? patrimonio.numeroPatrimonio : "",
            setor: patrimonio ? patrimonio.setor?.id : undefined
        };
        form.reset(values);
    }, [patrimonio, form.reset]);

    useEffect(() => {
        form.reset();
    }, [open])

    const buscarTodosSetores = async () => {
        await setorService.buscarTodos()
            .then(setSetores)
            .catch(() => toast.error("API está com problemas para bsucar os patrimônios"));
    }


    useEffect(() => {
        buscarTodosSetores()
    }, [])

    return (
        <Dialog open={open} onOpenChange={onOpenChange}>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Patrimônio</DialogTitle>
                </DialogHeader>
                <DialogDescription>
                </DialogDescription>
                <Separator />
                <Form {...form}>
                    <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4" autoComplete="off">
                        <FormField
                            control={form.control}
                            name="nome"
                            render={({ field }) => (
                                <FormItem className="flex-1">
                                    <FormLabel>Nome *</FormLabel>
                                    <FormControl>
                                        <Input
                                            autoFocus
                                            placeholder="Digite o nome do patrimonio"
                                            {...field}
                                        />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={form.control}
                            name="numeroPatrimonio"
                            render={({ field }) => (
                                <FormItem className="flex-1">
                                    <FormLabel>Número do Patrimônio *</FormLabel>
                                    <FormControl>
                                        <Input
                                            placeholder="Digite o número do patrimonio"
                                            {...field}
                                        />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={form.control}
                            name="setor"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Selecione o setor *</FormLabel>
                                    <FormControl>
                                        <SelectPopover
                                            lista={setores}
                                            onChange={field.onChange}
                                            error={!!form.formState.errors.setor}
                                            opcSelecionada={patrimonio?.setor}
                                        />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        <div className="flex flex-col sm:flex-row gap-2 sm:justify-end">
                            <Button type="submit" className="w-full sm:w-auto sm:px-6">
                                Salvar
                            </Button>
                            <DialogClose asChild>
                                <Button type="button" variant="outline">Cancelar</Button>
                            </DialogClose>
                        </div>
                    </form>
                </Form>
            </DialogContent>
        </Dialog>
    )
}