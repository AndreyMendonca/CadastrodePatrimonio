import { Layout } from "@/components/layout";
import { Button } from "@/components/ui/button";
import { HousePlug } from "lucide-react";
import Link from "next/link";

export default function Home() {
    return (
        <Layout>
            <div className="flex flex-col sm:flex-row justify-center items-center gap-10 ">
                <Button asChild className="p-10 rounded-2xl min-w-2xs text-center text-xl">
                    <Link href="/setores">Cadastrar Setor</Link>
                </Button>
                <Button asChild className="p-10 rounded-2xl min-w-2xs text-center text-xl">
                    <Link href="/patrimonios">Cadastrar Patrimônios</Link>
                </Button>
            </div>
        </Layout>
    );
}
