"use client"
import { useState } from "react";
import { Button } from "./ui/button";
import { cn } from "@/lib/utils";
import { Check, ChevronsUpDown } from "lucide-react";
import { Setor } from "@/types/setores";
import { Popover, PopoverContent, PopoverTrigger } from "./ui/popover";
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from "./ui/command";

type Props = {
    lista: Setor[];
    onChange: (value: number) => void;
    error?: boolean;
    opcSelecionada: Setor | undefined;
}

export const SelectPopover = ({ lista, onChange, error, opcSelecionada }: Props) => {
    const [open, setOpen] = useState(false);
    const [selecionado, setSelecionado] = useState<Setor | null>(opcSelecionada ?? null);
    return (
        <Popover open={open} onOpenChange={setOpen}>
            <PopoverTrigger asChild>
                <Button
                    variant="outline"
                    role="combobox"
                    aria-expanded={open}
                    className={cn(
                        "justify-between",
                        error && "border-destructive dark:border-destructive focus-visible:ring-destructive"
                    )}
                >
                    {selecionado
                        ? selecionado.nome
                        : "Selecione..."}
                    {
                        selecionado ? <Check className="opacity-50" /> : <ChevronsUpDown className="opacity-50" />
                    }

                </Button>
            </PopoverTrigger>
            <PopoverContent className="p-0">
                <Command>
                    <CommandInput placeholder="Procurar..." className="h-9" />
                    <CommandList>
                        <CommandEmpty>Nenhum resultado</CommandEmpty>
                        <CommandGroup>
                            {lista.map((obj) => (
                                <CommandItem
                                    key={obj.id}
                                    value={obj.nome}
                                    onSelect={() => {
                                        setSelecionado(obj)
                                        setOpen(false)
                                        onChange(obj.id!);
                                    }}
                                >
                                    <div>
                                        <p>{obj.nome}</p>
                                    </div>

                                    <Check
                                        className={cn(
                                            "h-4 w-4",
                                            selecionado?.id === obj.id
                                                ? "opacity-100"
                                                : "opacity-0"
                                        )}
                                    />
                                </CommandItem>
                            ))}
                        </CommandGroup>
                    </CommandList>
                </Command>
            </PopoverContent>
        </Popover>
    )
}