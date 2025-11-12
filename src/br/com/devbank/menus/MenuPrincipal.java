package br.com.devbank.menus;

import java.util.Scanner;

public class MenuPrincipal {
    public int ExibirMenu ( ) {
        Scanner menuPrincipal = new Scanner(System.in);

        String msnMenu = """
            \n======= 💰 DEVBANK 💰 =======
            Bem-vindo(a) ao seu banco digital!
            Escolha uma das opções abaixo para continuar:
            
            [1] Criar conta 🧾
            [2] Investimentos 📈
            [3] Ver Informações 📊
            [4] Sair
            """;

        System.out.println(msnMenu);
        System.out.println("Informe sua escolha: ");
        return menuPrincipal.nextInt();
    }
}
