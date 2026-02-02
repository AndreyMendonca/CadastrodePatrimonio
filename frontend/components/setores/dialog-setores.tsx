import { useForm } from "react-hook-form";
import { Button } from "../ui/button";
import { Dialog, DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "../ui/dialog";
import { Separator } from "../ui/separator";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "../ui/form";
import { Input } from "../ui/input";


const formSchema = z.object({
    nome: z.string().min(2, { message: "O nome é obrigatório." }).max(256, { message: "Máximo de caracteres são 256." }),
})


type Props = {
    open: boolean;
    onOpenChange: (open: boolean) => void;
}

export const DialogSetores = ({ open, onOpenChange }: Props) => {

    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            nome: "",
        }
    });

    const onSubmit = async (values: z.infer<typeof formSchema>) => {
        console.log(values);
        form.reset();
    }

    return (
        <Dialog open={open} onOpenChange={onOpenChange}>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Setores</DialogTitle>
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
                                    <FormLabel>Identificação *</FormLabel>
                                    <FormControl>
                                        <Input
                                            autoFocus
                                            placeholder="Digite a identificação"
                                            {...field}
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