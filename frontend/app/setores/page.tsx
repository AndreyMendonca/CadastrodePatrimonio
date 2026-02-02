import { Layout } from "@/components/layout";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function SetoresPage() {
    return (
        <Layout>
            <div className="flex flex-col sm:flex-row justify-center items-center gap-10 ">
                <Button className="p-10 rounded-2xl min-w-2xs text-center cursor-pointer">
                    <Link href="/">Cadastrar Setor</Link>
                </Button>
            </div>
        </Layout>
    );
}
