import Image from "next/image"
import { Separator } from "./ui/separator"
import { ThemeToggle } from "./theme-toggle"
import Link from "next/link"

type Props = {
    children: React.ReactNode
}
export const Layout = ({ children }: Props) => {
    return (
        <div className="min-h-screen flex flex-col">
            <header className="flex justify-center items-center py-4">
                <Link href="/">
                    <Image
                        src="/senai.svg"
                        alt="Logo SENAI"
                        width={200}
                        height={100}
                        priority
                    />
                </Link>
            </header>

            <main className="flex-1 w-full max-w-7xl mx-auto px-4">
                {children}
            </main>

            <footer className="py-4 text-center text-sm text-muted-foreground">
                <Separator className="mb-4" />
                <p>Desenvolvido por <strong>Andrey Mendonça</strong></p>
                <p><strong>SENAI - 2026</strong></p>
            </footer>
        </div>
    )
}